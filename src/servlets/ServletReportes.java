package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.RepBalancesMayores;
import dominio.User;
import negociolmpl.ReportesNegImpl;

/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportes() {
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
            
            if( request.getParameter("reportes") != null )
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Reportes.jsp");
                dispatcher.forward(request, response);
            }
            
            if( request.getParameter("btnBalanceMayorA") != null )
            {
                ArrayList<RepBalancesMayores> reportResult = new ArrayList<RepBalancesMayores>();
                ReportesNegImpl rpbmn = new ReportesNegImpl();
                reportResult = rpbmn.executeReport( Float.parseFloat(request.getParameter("inputBalance").toString()) );
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ReporteMayoresBalances.jsp");
                dispatcher.forward(request, response);
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
            
            if( request.getParameter("reportes") != null )
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Reportes.jsp");
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
