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

		try {
			request.removeAttribute("logearError");
			Account x = new Account();
			x.setAccountDni(request.getParameter("txtDNI"));
			request.setAttribute("DNI", request.getParameter("txtDNI"));
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
			x.setCbu(request.getParameter("txtCBU"));
			request.setAttribute("CBU", request.getParameter("txtCBU"));
			x.setAccountypeid(Integer.parseInt(request.getParameter("tipoCta")));
			request.setAttribute("Cuenta", request.getParameter("tipoCta"));
			x.setBalance(Float.parseFloat(request.getParameter("txtSaldo")));
			request.setAttribute("Saldo", request.getParameter("txtSaldo"));
			estado=true;
			tipoEstado = "estadoGestion";
						
				if(p.equals("Alta"))
				{					
					int cant = a.ObtenerCantCuentas(x);
					if(cant >= 3 || cant < 0)
					{
						throw new Exception("El cliente posee 3 o más cuentas");
					}
					int ultima = a.ObtenerUltimaCuenta();
					x.setAccountNumber(ultima+1);
					estado = a.ValidarCBU(x);
					if(estado) 
					{
						estado= a.InsertarCuenta(x);
						if(!estado) {
							throw new Exception("Hubo un problema al crear su cuenta");
						}
					}
					else
					{
						throw new Exception("Ya existe una cuenta con ese CBU");
					}
					thisPage = "/ListadoCuentas.jsp";
					a = null;
				}
				else if(p.equals("Baja"))
				{
					estado=  a.BajaCuenta(x);
					a = null;
				}
			x = null;
			request.setAttribute(tipoEstado, estado);
		    RequestDispatcher dispatcher = request.getRequestDispatcher(thisPage);
			dispatcher.forward(request, response);
		}
		if(request.getParameter("BuscarExistencia")!=null)
		{
			estado = true;
			tipoEstado = "estadoExistencia";
				User u = null;
				UserNeglmpl un = new UserNeglmpl();
				u = un.getUser(request.getParameter("txtDNI"));
				if (u == null)
				{
					throw new Exception("No se encontró el usuario en la base de datos");
				}
				request.setAttribute("Nombre","NOMBRE: " + u.getFirstName()+" "+u.getLastName());
				if(p.equals("Baja")) {
					
				}
				request.setAttribute(tipoEstado, estado);
				u = null;
				RequestDispatcher dispatcher = request.getRequestDispatcher(thisPage);
				dispatcher.forward(request, response);
		}		
		}
		catch(Exception e)
		{
			request.setAttribute("logearError", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Cuenta.jsp?p="+request.getParameter("parameter"));
			dispatcher.forward(request, response);
		}
	}

}
 