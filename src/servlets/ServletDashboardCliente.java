package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.DashboardCliente;
import dominio.User;
import negociolmpl.DashboardClienteNeglmpl;

/**
 * Servlet implementation class ServletDashboardCliente
 */
@WebServlet("/ServletDashboardCliente")
public class ServletDashboardCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDashboardCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User userLogin = new User();
	        userLogin = (User)request.getSession().getAttribute("userLogin");
	        if( userLogin == null )
	        {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	            dispatcher.forward(request, response);
	        }
	        
	        DashboardClienteNeglmpl dashboardClienteNeglmpl = new DashboardClienteNeglmpl();
	        DashboardCliente dsh = new DashboardCliente();
	        dsh.setSaldoTotal(dashboardClienteNeglmpl.getTotalBalance(userLogin.getDni()));
	        dsh.setAutorizacionesAprobadas(dashboardClienteNeglmpl.getTotalAprobaciones(userLogin.getDni()));
	        dsh.setCantidadTransferencias(dashboardClienteNeglmpl.getTotalTransferencias(userLogin.getDni()));
	        request.setAttribute("dashboardClienteData", dsh);
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/DashboardCliente.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
        {
            User userLogin = new User();
            userLogin = (User)request.getSession().getAttribute("userLogin");
            if( userLogin == null )
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
                dispatcher.forward(request, response);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		doGet(request, response);
	}

}
