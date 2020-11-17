package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Account;
import dominio.User;
import negocio.AccountNeg;
import negociolmpl.AccountNegImpl;
import negociolmpl.UserNeglmpl;


/**
 * Servlet implementation class ServletsCuentas
 */
@WebServlet("/ServletsCuentas")
public class ServletsCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletsCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.removeAttribute("logearError");
			request.removeAttribute("DadaAlta");
			request.removeAttribute("DadaBaja");
			Account x = new Account();
			x.setAccountDni(request.getParameter("txtDNI"));
			request.setAttribute("DNI", request.getParameter("txtDNI"));
			AccountNeg a = new AccountNegImpl();
			boolean estado = false;
			String tipoEstado = "";
			String p = request.getParameter("parameter");
			String thisPage="/Cuenta.jsp?p="+p;
		
		// CUENTAS DEL CLIENTE		   
		if(request.getParameter("listarTiposCuenta") != null) 
		{
			
		}
			
		if(request.getParameter("btnGestionarCuenta")!=null)
		{
			estado=true;
			tipoEstado = "estadoGestion";
						
				if(p.equals("Alta"))
				{					
					x.setCbu(request.getParameter("txtCBU"));
					request.setAttribute("CBU", request.getParameter("txtCBU"));
					x.setAccountypeid(Integer.parseInt(request.getParameter("tipoCta")));
					request.setAttribute("Cuenta", request.getParameter("tipoCta"));
					x.setBalance(Float.parseFloat(request.getParameter("txtSaldo")));
					request.setAttribute("Saldo", request.getParameter("txtSaldo"));
					int cant = a.ObtenerCantCuentas(x);
					if(cant >= 3 || cant < 0)
					{
						throw new Exception("El cliente posee 3 o mas cuentas");
					}
					int ultima = a.ObtenerUltimaCuenta();
					x.setAccountNumber(ultima+1);
					estado = a.ValidarCBU(x);
					if(estado) 
					{
						estado= a.InsertarCuenta(x);
						if(!estado) {
							throw new Exception("Hubo un problema al crear su cuenta");
						}
					}
					else
					{
						throw new Exception("Ya existe una cuenta con ese CBU");
					}
					request.setAttribute("DadaAlta", estado);
					a = null;
				}
				else if(p.equals("Baja"))
				{
					x.setAccountNumber(Integer.parseInt(request.getParameter("NroCuentaBaja")));
					estado=  a.BajaCuenta(x);
					request.setAttribute("DadaBaja", estado);
					a = null;
				}
			x = null;
			request.setAttribute(tipoEstado, estado);
		    RequestDispatcher dispatcher = request.getRequestDispatcher(thisPage);
			dispatcher.forward(request, response);
		}
		if(request.getParameter("BuscarExistencia")!=null)
		{
			estado = true;
			tipoEstado = "estadoExistencia";
				User u = null;
				UserNeglmpl un = new UserNeglmpl();
				u = un.getUser(request.getParameter("txtDNI"));
				if (u == null)
				{
					throw new Exception("No se encuentra el usuario en la base de datos");
				}
				request.setAttribute("Nombre","NOMBRE: " + u.getFirstName()+" "+u.getLastName());
				if(p.equals("Baja")) {
					List<Account> accounts = a.GetAllbyDni(u.getDni());
					request.setAttribute("CuentaB1","");
					request.setAttribute("CuentaB2","");
					request.setAttribute("CuentaB3","");
					int i = 1;
					if(!accounts.isEmpty())
					{
						for(Account c: accounts) {					
							request.setAttribute("CuentaB"+i,c.getAccountNumber());
							i++;
						}
					}
					else
					{
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
		catch(Exception e)
		{
			request.setAttribute("logearError", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Cuenta.jsp?p="+request.getParameter("parameter"));
			dispatcher.forward(request, response);
		}
	}

}
 