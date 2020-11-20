package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Account;
import dominio.Movement;
import dominio.User;
import negocio.AccountNeg;
import negociolmpl.AccountNegImpl;
import negociolmpl.MovementNegImpl;
import negociolmpl.UserNeglmpl;

/**
 * Servlet implementation class ServletsCuentas
 */
@WebServlet("/ServletsCuentas")
public class ServletsCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AccountNeg negAccount = new AccountNegImpl();

	public ServletsCuentas() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			User userLogin = new User();
			userLogin = (User) request.getSession().getAttribute("userLogin");
			if (userLogin == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
				dispatcher.forward(request, response);
			}

			// CUENTAS DEL CLIENTE
			if (request.getParameter("listarCuentas") != null) {
				ArrayList<Account> listAccount = negAccount.GetAll();
				request.setAttribute("accountList", listAccount);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoCuentas.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			User userLogin = new User();
			userLogin = (User) request.getSession().getAttribute("userLogin");
			if (userLogin == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
				dispatcher.forward(request, response);
			}

			request.removeAttribute("logearError");
			request.removeAttribute("DadaAlta");
			request.removeAttribute("DadaBaja");
			String p = request.getParameter("parameter");

			// CUENTA ALTA Y BAJA
			if (p != null && (p.equals("Alta") || p.equals("Baja"))) {
				Account x = new Account();
				ArrayList<Account> listAccount = new ArrayList<Account>();
				if (negAccount == null) {
					negAccount = new AccountNegImpl();
				}
				boolean estado = false;
				String tipoEstado = "";
				String thisPage = "/Cuenta.jsp?p=" + p;

				x.setAccountDni(request.getParameter("txtDNI"));
				request.setAttribute("DNI", request.getParameter("txtDNI"));

				// BOTON ALTA Y BAJA
				if (request.getParameter("btnGestionarCuenta") != null) {
					estado = true;
					tipoEstado = "estadoGestion";

					if (p.equals("Alta")) {
						// ASIGNACION DE DATOS
						x.setCbu(request.getParameter("txtCBU"));
						request.setAttribute("CBU", request.getParameter("txtCBU"));
						x.setAccountypeid(Integer.parseInt(request.getParameter("tipoCta")));
						request.setAttribute("Cuenta", request.getParameter("tipoCta"));
						x.setBalance(Float.parseFloat(request.getParameter("txtSaldo")));
						request.setAttribute("Saldo", request.getParameter("txtSaldo"));
						// VALIDACIONES
						int cant = negAccount.ObtenerCantCuentas(x);
						if (cant >= 3 || cant < 0) {
							throw new Exception("El cliente posee 3 o mas cuentas");
						}
						int ultima = negAccount.ObtenerUltimaCuenta();
						x.setAccountNumber(ultima + 1);
						estado = negAccount.ValidarCBU(x);
						// INSERTAR
						if (estado) {
							estado = negAccount.InsertarCuenta(x);
							if (!estado) {
								throw new Exception("Hubo un problema al crear su cuenta");
							}
						} else {
							throw new Exception("Ya existe una cuenta con ese CBU");
						}
						request.setAttribute("DadaAlta", estado);
						negAccount = null;
					} else if (p.equals("Baja")) {
						x.setAccountNumber(Integer.parseInt(request.getParameter("NroCuentaBaja")));
						estado = negAccount.BajaCuenta(x);
						request.setAttribute("DadaBaja", estado);
						negAccount = null;
					}
					x = null;
					request.setAttribute(tipoEstado, estado);
					RequestDispatcher dispatcher = request.getRequestDispatcher(thisPage);
					dispatcher.forward(request, response);
				}
				// BUSQUEDA DE CLIENTES
				if (request.getParameter("BuscarExistencia") != null) {
					estado = true;
					tipoEstado = "estadoExistencia";
					User u = null;
					UserNeglmpl un = new UserNeglmpl();
					u = un.getUser(request.getParameter("txtDNI"));
					if (u == null) {
						throw new Exception("No se encuentra el usuario en la base de datos");
					}
					request.setAttribute("Nombre", "NOMBRE: " + u.getFirstName() + " " + u.getLastName());
					if (p.equals("Baja")) {
						List<Account> accounts = negAccount.GetAllbyDni(u.getDni());
						request.setAttribute("CuentaB1", "");
						request.setAttribute("CuentaB2", "");
						request.setAttribute("CuentaB3", "");
						int i = 1;
						if (!accounts.isEmpty()) {
							for (Account c : accounts) {
								request.setAttribute("CuentaB" + i, c.getAccountNumber());
								i++;
							}
						} else {
							throw new Exception("No existen cuentas para dar de baja");
						}
						request.setAttribute("List", accounts);
					}
					request.setAttribute(tipoEstado, estado);
					u = null;
					RequestDispatcher dispatcher = request.getRequestDispatcher(thisPage);
					dispatcher.forward(request, response);
				}
			}
			// LISTADO DE CUENTAS
			if (request.getParameter("btnListarCuentaE") != null) {
				String parameter = request.getParameter("btnListarCuentaE");
				request.setAttribute("btnListarCuentaE", null);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/ServletCuentasDet?Listarcuentanumero=" + parameter);
				request.removeAttribute("btnListarCuentaE");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("logearError", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Cuenta.jsp?p=" + request.getParameter("parameter"));
			dispatcher.forward(request, response);
		}
	}

}
