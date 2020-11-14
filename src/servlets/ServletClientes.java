package servlets;

import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

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
		
		try
        {
		    if(request.getParameter("btnGuardar") != null) 
	        {
	            //Alta de cliente en tabla de usuarios
	            User newCliente = new User();
	            newCliente.setDni( request.getParameter("textDni") );
	            newCliente.setCuil( request.getParameter("textCuil") );
	            newCliente.setFirstName( request.getParameter("textNombre") );
	            newCliente.setLastName( request.getParameter("textApellido") );
	            newCliente.setEmail( request.getParameter("textEmail") );
	            newCliente.setNacionality( request.getParameter("textNacionalidad") );
	            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            newCliente.setBirthDate( formatter.parse( request.getParameter("textFechaNacimiento") ) );
	            newCliente.setGender( request.getParameter("textGenero") );
	            Phone tmpPhone = new Phone();
	            tmpPhone.setNumber( Long.parseLong( request.getParameter("textPhone1").toString() ) );
	            tmpPhone.setDescription("Telefono preponderante");
	            newCliente.setPhone(tmpPhone);
	            
	            
	            System.out.println("mostramos los datos obtenidos:");
	            System.out.println("getDni: " + newCliente.getDni());
	            System.out.println("getCuil: " + newCliente.getCuil());
	            System.out.println("getFirstName: " + newCliente.getFirstName());
	            System.out.println("getLastName: " + newCliente.getLastName());
	            System.out.println("getEmail: " + newCliente.getEmail());
	            System.out.println("getNacionality: " + newCliente.getNacionality());
	            System.out.println("getBirthDate: " + newCliente.getBirthDate());
	            System.out.println("getGender: " + newCliente.getGender());
	            System.out.println("getPhone: " + newCliente.getPhone());

	        }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
