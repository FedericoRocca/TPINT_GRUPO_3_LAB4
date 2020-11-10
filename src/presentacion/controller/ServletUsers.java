package presentacion.controller;

import negocio.PhoneNeg;
import negocio.UserNeg;
import negociolmpl.PhoneNeglmpl;
import negociolmpl.UserNeglmpl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.User;

//import entidades.User;


@WebServlet("/ServletUsers")
public class ServletUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	PhoneNeg negPhone = new PhoneNeglmpl(null);
	UserNeg	 negUser = new UserNeglmpl();
	
	public ServletUsers() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		if(request.getParameter("Param")!=null) 
//		{
//			String option = request.getParameter("Param").toString();
//			
//			switch(option) {
//				case "list":{
//					request.setAttribute("userList", negUser.GetAll());
//					RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoClientes.jsp");
//					dispatcher.forward(request, response);
//					break;
//				}
//				default:
//					break;
//			}
//		}
	
		ArrayList<User> users = new ArrayList<>();
		if(request.getParameter("list") != null) 
		{		
			users = negUser.GetAll();
			
			request.setAttribute("userList", users);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoClientes.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}
	
}
