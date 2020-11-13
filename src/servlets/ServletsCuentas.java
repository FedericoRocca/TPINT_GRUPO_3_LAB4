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
import dominio.User;
import negocio.AccountNeg;
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
		// TODO Auto-generated method stub
		if(request.getParameter("btnGestionarCuenta")!=null)
		{
			boolean estado=true;
			try {
				Account x = new Account();
				DecimalFormat df = new DecimalFormat("0.00");
				x.setAccountDni(Integer.parseInt(request.getParameter("txtDNI")));
				x.setCbu(Integer.parseInt(request.getParameter("txtCBU")));
				x.setAccountypeid(Integer.parseInt(request.getParameter("tipoCta")));
				AccountNeg a = new AccountNeg();				
				if(request.getParameter("p") == "Alta")
				{					
					estado=  a.InsertarCuenta(x);
					a = null;
				}
				else if(request.getParameter("p") == "Baja")
				{
					estado=  a.BajaCuenta(x);
					a = null;
				}
			}
			catch(Exception e)
			{
				estado = false;
			}

			
			request.setAttribute("estadoGestion", estado);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("ListadoCuentas.jsp");
			dispatcher.forward(request, response);

		}
		
		if(request.getParameter("BuscarExistencia")!=null)
		{
			boolean estado = true;
			try {
				User u = null;
				UserNeglmpl un = new UserNeglmpl();
				u = un.getUser(request.getParameter("txtDNI"));
				if (u == null)
				{
					estado = false;
				}
			}
			catch(Exception e)
			{
				estado = false;
			}
		
			request.setAttribute("estadoExistencia", estado);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("Cuenta.jsp?p="+request.getParameter("p"));
			dispatcher.forward(request, response);
		}
	}

}
