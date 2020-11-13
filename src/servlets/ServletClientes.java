package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Nationality;
import dominio.Phone;
import dominio.User;
import negocio.NationalityNeg;
import negocio.PhoneNeg;
import negocio.UserNeg;
import negociolmpl.NationalityNeglmpl;
import negociolmpl.PhoneNeglmpl;
import negociolmpl.UserNeglmpl;


@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserNeg negCustomer = new UserNeglmpl();
//	PhoneNeg negPhone = new PhoneNeglmpl(null);
	NationalityNeg negNatio = new NationalityNeglmpl();
    
	public ServletClientes() {
        super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		boolean status = false;
		
		ArrayList<User> users = new ArrayList<>();
		ArrayList<Nationality> nationalities = new ArrayList<>();
		
		if(request.getParameter("list") != null) 
		{		
			users = negCustomer.GetAll();
			
			request.setAttribute("userList", users);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoClientes.jsp");
			dispatcher.forward(request, response);
		}
		
		//carga nacionalidades
		if(request.getParameter("loadNationalities") != null) 
		{
			nationalities = (ArrayList<Nationality>) negNatio.getAll();
			
			request.setAttribute("listNationalities", nationalities);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Cuenta.jsp");
			dispatcher.forward(request, response);
		}
		
		//carga ciudades y provincias (FALTA!!!!)
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
