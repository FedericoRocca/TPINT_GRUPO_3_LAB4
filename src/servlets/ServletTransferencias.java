package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

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
import negociolmpl.UserNeglmpl;

/**
 * Servlet implementation class ServletTransferencias
 */
@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencias() {
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
		// TODO Auto-generated method stub
		try {
			Movement x = new Movement();
			DecimalFormat df = new DecimalFormat("0.00");
			//LocalDate mifecha = LocalDate.Now();
			
			//x.setMovementDate(mifecha);
			//x.setDetails(details);
			//x.setAmount(request.getParameter("txtCantidad"));
			//x.setMovementTypeId(movementTypeId);
			

			AccountNeg a = new AccountNeg();
			boolean estado = false;
			String tipoEstado = "";
			String p = request.getParameter("parameter");
			String thisPage="/Cuenta.jsp?p="+p;


			


		
		request.setAttribute(tipoEstado, estado);
		RequestDispatcher dispatcher = request.getRequestDispatcher(thisPage);
		dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			request.setAttribute("Error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Cuenta.jsp?p="+request.getParameter("parameter"));
			dispatcher.forward(request, response);
		}
	}

}
