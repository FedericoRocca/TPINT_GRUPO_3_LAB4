package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Account;


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
		// TODO Auto-generated method stub
		if(request.getParameter("btnAgregarCuenta")!=null)
		{
			Account x = new Account();
			DecimalFormat df = new DecimalFormat("0.00");
			x.setAccountDni(Integer.parseInt(request.getParameter("txtDNI")));
			x.setCbu(Integer.parseInt(request.getParameter("txtCBU")));
			x.setAccountypeid(Integer.parseInt(request.getParameter("tipoCta")));
			x.setBalance(Float.parseFloat(df.format(request.getParameter("txtSaldo"))));
			
			boolean estado=true;

		    request.setAttribute("estadoAgregar", estado);

		    RequestDispatcher dispatcher = request.getRequestDispatcher("Cuenta.jsp");
			dispatcher.forward(request, response);
		}

		if(request.getParameter("btnEliminar")!=null)
		{

			boolean estado=true;

		    request.setAttribute("estadoEliminar", estado);

			RequestDispatcher dispatcher = request.getRequestDispatcher("Cuenta.jsp");
			dispatcher.forward(request, response);
		}
		if(request.getParameter("btnModificarRegistro")!=null)
		{
			Account x = new Account();


			
		    
		
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("Cuenta.jsp");
			dispatcher.forward(request, response);
		}
		if(request.getParameter("btnListarCuentas")!=null)
		{
			Account x = new Account();
			

			RequestDispatcher dispatcher = request.getRequestDispatcher("Cuenta.jsp");
			dispatcher.forward(request, response);
		}
	}

}
