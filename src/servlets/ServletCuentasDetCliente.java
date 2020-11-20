package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Account;
import dominio.Movement;
import dominio.User;
import negociolmpl.MovementNegImpl;

@WebServlet("/ServletCuentasDetCliente")
public class ServletCuentasDetCliente extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	public ServletCuentasDetCliente() {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		//DETALLE DE CUENTA
		try {
			if (request.getParameter("Listarcuentanumero") != null) {
				MovementNegImpl negMovement = new MovementNegImpl();
				int account = Integer.parseInt(request.getParameter("Listarcuentanumero"));
				ArrayList<Movement> listmovement = negMovement.GetAllbyAccount(account);
				float saldofinal = negMovement.obtenerSaldo(account);
				request.setAttribute("listmovement", listmovement);
				request.setAttribute("Cuentanumero", account);
				request.setAttribute("Saldo", saldofinal);
				negMovement = null;
				RequestDispatcher dispatcher = request.getRequestDispatcher("/DetalleCuentaCliente.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
