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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //Obtenemos los datos ingresados por el usuario 
	    User user = new User();
	    if(request.getParameter("btnLogin") != null)
        {
	        user.setUserName(request.getParameter("txbUser"));
	        user.setPassword(request.getParameter("txbPassword"));
        }
	    // Chequeo contra la base de datos si el usuario existe, si no mensaje de error en el front
	    System.out.println("Usuario: " + user.getUserName());
	    System.out.println("password: " + user.getPassword());
	    UserNeglmpl userNeg = new UserNeglmpl();
	    if( userNeg.exists(user.getUserName()) )
	    {
	        // El usuario existe, traemos toda la info del usuario y chequeamos rol
	        user = userNeg.getUserByUsername(user.getUserName(), user.getPassword());
	    }
	    else
	    {
	        System.out.println("Usuario no existe");
	    }
	    //request.setAttribute("userList", users);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoClientes.jsp");
        //dispatcher.forward(request, response);
		doGet(request, response);
	}

}
