package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.User;
import negocio.UserNeg;
import negociolmpl.UserNeglmpl;


@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	UserNeg negCustomer = new UserNeglmpl();
    
	public ServletClientes() {
        super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<User> users = new ArrayList<>();
		if(request.getParameter("list") != null) 
		{		
			users = negCustomer.GetAll();
			
			request.setAttribute("userList", users);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoClientes.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
