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

import datoslmpl.UserDaolmpl;
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
		if(request.getParameter("listNat") != null) 
		{
			nationalities = (ArrayList<Nationality>) negNatio.getAll();
			
			request.setAttribute("listNationalities", nationalities);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente.jsp?p="+request.getParameter("p"));
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
	            
	            if( request.getParameter("textPhone1") != null && request.getParameter("textPhone1") != "")
                {
	                Phone tmpPhonePreponderante = new Phone();
	                tmpPhonePreponderante.setNumber( Long.parseLong( request.getParameter("textPhone1").toString() ) );
    	            tmpPhonePreponderante.setDescription("Teléfono primario");
    	            newCliente.addPhone(tmpPhonePreponderante);
                }
	            
                if( request.getParameter("textPhone2") != null && request.getParameter("textPhone2") != "")
	            {
                    Phone tmpPhoneSecundario = new Phone();
	                tmpPhoneSecundario.setNumber( Long.parseLong( request.getParameter("textPhone2").toString() ) );
	                tmpPhoneSecundario.setDescription("Teléfono secundario");
	                newCliente.addPhone(tmpPhoneSecundario);
	            }
                
                if( request.getParameter("textPhone3") != null && request.getParameter("textPhone3") != "")
                {
                    Phone tmpPhoneAdicional = new Phone();
                    tmpPhoneAdicional.setNumber( Long.parseLong( request.getParameter("textPhone3").toString() ) );
                    tmpPhoneAdicional.setDescription("Teléfono adicional");
                    newCliente.addPhone(tmpPhoneAdicional);
                }
                
                newCliente.setStatus(true);
                newCliente.setUserName( request.getParameter("textUser") );
                newCliente.setPassword(newCliente.getDni().toString() );
                
                newCliente.setAddress( request.getParameter("textAddress") );
                
                //Recopilamos todos los datos del usuario, lo damos de alta...
                UserDaolmpl udi = new UserDaolmpl();
                udi.insert(newCliente);

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
