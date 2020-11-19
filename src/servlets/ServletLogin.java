package servlets;

import java.io.Console;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.User;
import negocio.UserNeg;
import negociolmpl.UserNeglmpl;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User userLogin = new User();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    try
        {
	        if(request.getParameter("btnLogout") != null)
	        {
	            userLogin = null;
	            request.getSession().removeAttribute("userLogin");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
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
	        if(request.getParameter("btnLogout") != null)
	        {
	            userLogin = null;
	            request.getSession().removeAttribute("userLogin");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	            dispatcher.forward(request, response);
	        }
	        RequestDispatcher dispatcher;
	        String loginError = "";
	      //Obtenemos los datos ingresados por el usuario 
	        User user = new User();
	        if(request.getParameter("btnLogin") != null)
	        {
	            user.setUserName(request.getParameter("txbUser"));
	            user.setPassword(request.getParameter("txbPassword"));
	        }
	        // Chequeo contra la base de datos si el usuario existe, si no mensaje de error en el front
	        UserNeglmpl userNeg = new UserNeglmpl();
	        if( userNeg.exists(user.getUserName()) )
	        {
	            // El usuario existe, traemos toda la info del usuario y chequeamos rol
	            user = userNeg.getUserByUsername(user.getUserName(), user.getPassword());
	            
	            if( user != null )
	            {
	                
	                //Seteamos la session que se va a usar para login/logout
	                request.getSession().setAttribute("userLogin", user);
	                
	                switch (user.getRol().getNameRole())
	                {
	                    case "Administrador":
	                            dispatcher = request.getRequestDispatcher("/DashboardAdmin.jsp");
	                            dispatcher.forward(request, response);
	                        break;
	                        
	                    case "Cliente":
	                            dispatcher = request.getRequestDispatcher("/DashboardCliente.jsp");
	                            dispatcher.forward(request, response);
	                        break;
	                    
	                    default:
	                            System.out.println("Algo raro hubo, se entró con un rol que no existe en el código");
	                        break;
	                }
	            }
	            else
	            {
	                loginError = "El usuario y/o contraseña ingresados son incorrectos";
	                request.setAttribute("loginError", loginError);
	                dispatcher = request.getRequestDispatcher("/Login.jsp");
	                dispatcher.forward(request, response);
	            }
	        }
	        else
	        {
	            loginError = "El usuario y/o contraseña ingresados son incorrectos";
	            request.setAttribute("loginError", loginError);
	            dispatcher = request.getRequestDispatcher("/Login.jsp");
                dispatcher.forward(request, response);
	        }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

}
