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

import datoslmpl.PhoneDaolmpl;
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
import sun.font.Script;


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
	    try
        {
	        User userLogin = new User();
	        userLogin = (User)request.getSession().getAttribute("userLogin");
	        if( userLogin == null )
	        {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	            dispatcher.forward(request, response);
	        }

	        request.setAttribute("userLogin", userLogin);
    	    ArrayList<User> users = new ArrayList<>();
    		ArrayList<Nationality> nationalities = new ArrayList<>();
    		UserDaolmpl udi = new UserDaolmpl();
    		PhoneDaolmpl pdi = new PhoneDaolmpl();
    		User customer = new User();
    		User newCliente = new User();
    		User bajaUser = new User();
    		User modifUser = new User();
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		
    		if(request.getParameter("btnLogout") != null)
    		{
    		    userLogin = null;
    		    request.getSession().removeAttribute("userLogin");
    		    RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
                dispatcher.forward(request, response);
    		}
    		
    		if(request.getParameter("btnBuscar") != null)
            {
		        if( request.getParameter("textDni") != null && request.getParameter("textDni") != "" )
		        {
		            bajaUser.setDni( request.getParameter("textDni") );
		            bajaUser = udi.getUser(bajaUser.getDni());
		            request.setAttribute("usrBaja", bajaUser);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/BajaCliente.jsp");
                    dispatcher.forward(request, response);
		        }
            }
    		    		
    		if(request.getParameter("btnEliminarCliente") != null)
            {
                if( request.getParameter("textDni") != null && request.getParameter("textDni") != "" )
                {
                    //aca damos de baja al cliente
                    udi.delete(request.getParameter("textDni"));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/DashboardAdmin.jsp");
                    dispatcher.forward(request, response);
                }
            }
    		
    		if(request.getParameter("btnBuscarModificar") != null)
            {
                if( request.getParameter("textDni") != null && request.getParameter("textDni") != "" )
                {
                    modifUser.setDni( request.getParameter("textDni") );
                    modifUser = udi.getUser(modifUser.getDni());
                    nationalities = (ArrayList<Nationality>) negNatio.getAll();
                    request.setAttribute("listNat", nationalities);
                    request.setAttribute("usrModif", modifUser);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
                    dispatcher.forward(request, response);
                }
            }
    		
    		if(request.getParameter("btnModificarCliente") != null)
            {
                    if( request.getParameter("password") != request.getParameter("passwordRepeat") )
                    {
                        String passwordsNotMatch = "Las contraseñas no coinciden, por favor ingresarlas nuevamente";
                        request.setAttribute("passwordsNotMatch", passwordsNotMatch);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
                        dispatcher.forward(request, response);
                    }
                    
                    modifUser.setPassword(request.getParameter("password"));
    		        modifUser.setCuil( request.getParameter("textCuil") );
    		        modifUser.setFirstName( request.getParameter("textNombre") );
    		        modifUser.setLastName( request.getParameter("textApellido") );
    		        modifUser.setGender( request.getParameter("textGenero") );
    		        modifUser.setUserName( request.getParameter("textUsuario") );
    		        modifUser.setNacionality( request.getParameter("textNacionalidad") );
    		        modifUser.setEmail( request.getParameter("textEmail") );
    		        modifUser.setDni( request.getParameter("textDni") );
    		        modifUser.setBirthDate( formatter.parse( request.getParameter("textFechaNacimiento") ) );
    		        
    		        if( request.getParameter("textPhone1") != null )
    		        {
    		            Phone tmpPhone = new Phone();
    		            tmpPhone.setNumber(Long.parseLong(request.getParameter("textPhone1").toString()));
    		            tmpPhone.setDescription("Teléfono primario");
    		            modifUser.addPhone(tmpPhone);
    		        }
    		        
    		        if( request.getParameter("textPhone2") != null )
                    {
    		            Phone tmpPhone = new Phone();
                        tmpPhone.setNumber(Long.parseLong(request.getParameter("textPhone2").toString()));
                        tmpPhone.setDescription("Teléfono secundario");
                        modifUser.addPhone(tmpPhone);
                    }
    		        
    		        if( request.getParameter("textPhone3") != null )
                    {
    		            Phone tmpPhone = new Phone();
                        tmpPhone.setNumber(Long.parseLong(request.getParameter("textPhone3").toString()));
                        tmpPhone.setDescription("Teléfono adicional");
                        modifUser.addPhone(tmpPhone);
                    }
    		        pdi.updatePhoneForDNI(modifUser.getDni(), modifUser.getPhone());
    		        udi.update(modifUser);
    		        RequestDispatcher dispatcher = request.getRequestDispatcher("/DashboardAdmin.jsp");
                    dispatcher.forward(request, response);
            }
    		
    		if(request.getParameter("alta") != null)
    		{
    		        nationalities = (ArrayList<Nationality>) negNatio.getAll();
    		        request.setAttribute("listNat", nationalities);
    		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente.jsp?p=Alta");
    	            dispatcher.forward(request, response);
    		}
    		
    //		if(request.getParameter("modificar") != null)
    //		{
    //		        nationalities = (ArrayList<Nationality>) negNatio.getAll();
    //		        request.setAttribute("listNat", nationalities);
    //		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente.jsp?p=Modificar");
    //	            dispatcher.forward(request, response);
    //		}
    		
    		if(request.getParameter("listarCliente") != null) 
    		{
    		    nationalities = (ArrayList<Nationality>) negNatio.getAll();
                request.setAttribute("listNat", nationalities);
    			customer = negCustomer.getUser(userLogin.getDni());
    	        request.setAttribute("customer", customer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/DatosCliente.jsp");
                dispatcher.forward(request, response);
    		}
    		
    		if(request.getParameter("listar") != null)
            {
    		        users = negCustomer.GetAll();
    		        request.setAttribute("userList", users);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoClientes.jsp");
                    dispatcher.forward(request, response);
            }
    		
    		if(request.getParameter("list") != null) 
    		{		
    			users = negCustomer.GetAll();
    			
    			request.setAttribute("userList", users);
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListadoClientes.jsp");
    			dispatcher.forward(request, response);
    		}

		
		    if(request.getParameter("btnGuardar") != null) 
	        {
	            //Alta de cliente en tabla de usuarios
	            newCliente.setDni( request.getParameter("textDni") );
	            newCliente.setCuil( request.getParameter("textCuil") );
	            newCliente.setFirstName( request.getParameter("textNombre") );
	            newCliente.setLastName( request.getParameter("textApellido") );
	            newCliente.setEmail( request.getParameter("textEmail") );
	            newCliente.setNacionality( request.getParameter("textNacionalidad") );
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
                udi.insert(newCliente);
                
                request.setAttribute("userList", users);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/DashboardAdmin.jsp");
                dispatcher.forward(request, response);
	        }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    try
        {
	        User userLogin = new User();
	        userLogin = (User)request.getSession().getAttribute("userLogin");
	        if( userLogin == null )
	        {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	            dispatcher.forward(request, response);
	        }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		doGet(request, response);
	}

}
