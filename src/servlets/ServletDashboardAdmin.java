package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.DashboardAdmin;
import dominio.User;
import negociolmpl.DashboardAdminNegImpl;

/**
 * Servlet implementation class ServletDashboardAdmin
 */
@WebServlet("/ServletDashboardAdmin")
public class ServletDashboardAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDashboardAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
        {
		    User userLogin = new User();
	        userLogin = (User)request.getSession().getAttribute("userLogin");
	        if( userLogin == null )
	        {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	            dispatcher.forward(request, response);
	        }
	        
	        DashboardAdminNegImpl dashboardAdminNegImpl = new DashboardAdminNegImpl();
	        DashboardAdmin data = new DashboardAdmin();
	        data.setTotalBalance( dashboardAdminNegImpl.getTotalBalance() );
	        data.setCantidadMovimientos( dashboardAdminNegImpl.getTotalMovements() );
	        data.setTotalLoans( dashboardAdminNegImpl.getTotalLoans() );
	        data.setTotalAccounts( dashboardAdminNegImpl.getTotalAccounts() );
	        request.setAttribute("dashboardAdminData", data);
	        
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/DashboardAdmin.jsp");
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
