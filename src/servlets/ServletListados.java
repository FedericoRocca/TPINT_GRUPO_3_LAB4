package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.LisCantidadClientesPaises;
import dominio.RepBalancesMayores;
import dominio.User;
import negociolmpl.ReportesNegImpl;

@WebServlet("/ServletListados")
public class ServletListados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletListados() {
        super();
        // TODO Auto-generated constructor stub
    }

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
            
            if( request.getParameter("listados") != null )
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Listados.jsp");
                dispatcher.forward(request, response);
            }
            
            if( request.getParameter("btnListadoCantidadClientesPaises") != null )
            {
                ArrayList<LisCantidadClientesPaises> listResult = new ArrayList<LisCantidadClientesPaises>();
                ReportesNegImpl rpbmn = new ReportesNegImpl();
                listResult = rpbmn.executeReport();
                request.setAttribute("listResult", listResult);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoCantidadClientesPaises.jsp");
                dispatcher.forward(request, response);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

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
