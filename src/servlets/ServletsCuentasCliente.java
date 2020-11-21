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
@WebServlet("/ServletsCuentasCliente")
public class ServletsCuentasCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AccountNeg negAccount = new AccountNegImpl();

	public ServletsCuentasCliente() {
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
				if (negAccount == null) {
					negAccount = new AccountNegImpl();
				}
				ArrayList<Account> listAccount = negAccount.GetAllbyDni(userLogin.getDni());
				request.setAttribute("accountList", listAccount);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoCuentasCliente.jsp");
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

			// LISTADO DE CUENTAS
			if (request.getParameter("btnListarCuentaE") != null) {
				String parameter = request.getParameter("btnListarCuentaE");
				request.setAttribute("btnListarCuentaE", null);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/ServletCuentasDetCliente?Listarcuentanumero=" + parameter);
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
