package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Loan;
import dominio.RepBalancesMayores;
import dominio.RepIngresosInteres;
import dominio.User;
import negociolmpl.ReportesNegImpl;


@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletReportes() {
        super();
    }

    ReportesNegImpl rpbmn = new ReportesNegImpl();
    
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
                reportResult = rpbmn.executeReport( Float.parseFloat(request.getParameter("inputBalance").toString()) );
                request.setAttribute("reportResult", reportResult);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ReporteCuentasMayoresBalances.jsp");
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
            
            if( request.getParameter("reportes") != null )
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Reportes.jsp");
                dispatcher.forward(request, response);
            }
            
            if(request.getParameter("btnRepIngresosInteres")!=null) 
            {
            	RepIngresosInteres r = new RepIngresosInteres();
            	RepIngresosInteres raux = new RepIngresosInteres();
            	
            	r.setFromDate(LocalDate.parse(request.getParameter("fromDate")));
            	r.setToDate(LocalDate.parse(request.getParameter("toDate")));
            	
            	if(r.getFromDate().isAfter(r.getToDate())) {
                    request.setAttribute("fechas", true);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Reportes.jsp");
                    dispatcher.forward(request, response);
            	}
            	
            	raux = rpbmn.executeReport(r);
            	
            	float total = 0;
            	
            	for(Loan l : raux.getLoans()) {
            		total += l.getAmountInt() - l.getAmountReqByCustomer();
            	}
            	
            	raux.setBalance(total);
            	
                request.setAttribute("reportResult", raux);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ReporteIngresosInteres.jsp");
                dispatcher.forward(request, response);
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}
