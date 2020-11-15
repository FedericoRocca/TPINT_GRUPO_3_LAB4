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
		try {
			Account x = new Account();
			DecimalFormat df = new DecimalFormat("0.00");
			x.setAccountDni(Integer.parseInt(request.getParameter("txtDNI")));
			request.setAttribute("DNI", request.getParameter("txtDNI"));
			x.setCbu(request.getParameter("txtCBU"));
			request.setAttribute("CBU", request.getParameter("txtCBU"));
			AccountNeg a = new AccountNeg();
			boolean estado = false;
			String tipoEstado = "";
			String p = request.getParameter("parameter");
			String thisPage="/Cuenta.jsp?p="+p;
		
		// CUENTAS DEL CLIENTE		   
		if(request.getParameter("listarTiposCuenta") != null) 
		{
			
		}
			
		if(request.getParameter("btnGestionarCuenta")!=null)
		{
			x.setAccountypeid(Integer.parseInt(request.getParameter("tipoCta")));
			request.setAttribute("Cuenta", request.getParameter("tipoCta"));
			estado=true;
			tipoEstado = "estadoGestion";
						
				if(p == "Alta")
				{					
					estado=  a.InsertarCuenta(x);
					a = null;
				}
				else if(p == "Baja")
				{
					estado=  a.BajaCuenta(x);
					a = null;
				}

			request.setAttribute(tipoEstado, estado);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoCuentas.jsp");
			dispatcher.forward(request, response);
		}
		if(request.getParameter("BuscarExistencia")!=null)
		{
			estado = false;
			tipoEstado = "estadoExistencia";
				User u = null;
				UserNeglmpl un = new UserNeglmpl();
				u = un.getUser(request.getParameter("txtDNI"));
				request.setAttribute("D", "d");
				if (u == null)
				{
					estado = false;
					request.setAttribute("D", "");
				}
		}
		
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
 