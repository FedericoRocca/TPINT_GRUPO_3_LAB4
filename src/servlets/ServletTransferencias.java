package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datoslmpl.MovementDaoImpl;
import dominio.Account;
import dominio.Loan;
import dominio.LoanState;
import dominio.Movement;
import dominio.MovementType;
import dominio.User;
import negocio.AccountNeg;
import negocio.MovementNeg;
import negociolmpl.AccountNegImpl;
import negociolmpl.MovementNegImpl;
import negociolmpl.UserNeglmpl;


@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AccountNeg negAccount = new AccountNegImpl();

	MovementNeg negMove = new MovementNegImpl();

    public ServletTransferencias() {
        super();
        // TODO Auto-generated constructor stub
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
		    			
			if(request.getParameter("btnLogout") != null)
    		{
    		    userLogin = null;
    		    RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
                dispatcher.forward(request, response);
    		}
			
			Account account = new Account();
			ArrayList<Account> accounts = new ArrayList<Account>();
			
			if(request.getParameter("alta") != null) {
				accounts = negAccount.GetAllbyDni(userLogin.getDni());
				request.setAttribute("listAccount", accounts);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaTransferencia.jsp?p=alta");
				dispatcher.forward(request, response);
			}
			
			if(request.getParameter("transferencia") != null) {
				accounts = negAccount.GetAllbyDni(userLogin.getDni());
				request.setAttribute("listAccount", accounts);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaTransferenciaTerceros.jsp?p=transferencia");
				dispatcher.forward(request, response);
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
			
	   User userLogin = new User();
	    userLogin = (User)request.getSession().getAttribute("userLogin");
	    if( userLogin == null )
	    {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	        dispatcher.forward(request, response);
	    }
			
	    Account account = new Account();
		if(request.getParameter("updateCuentas")!=null) {
			
			//Se calcula el monto de la transferencia
			//Float monto = (Float.parseFloat(request.getParameter("montooo"))-Float.parseFloat( request.getParameter("txtOrigenModal")))/2;
		    
			//Se genera el movimiento de transferencia positivo
		    		    
			Movement mov = new Movement();
			mov.setAccountNumber(Integer.parseInt(request.getParameter("txtCtaDestino")));
			mov.setAmount(Float.parseFloat(request.getParameter("montooo")));
			mov.setDetail("Transferencia a cuenta propia");
			mov.setMovementDate(LocalDate.now());
			mov.setMovementType(new MovementType(4,"Transferencia"));
			negMove.insert(mov);
			
			//Se genera el movimiento de transferencia negativo
			Movement mov2 = new Movement();
			mov2.setAccountNumber(Integer.parseInt(request.getParameter("txtCtaOrigen")));
			mov2.setAmount(Float.parseFloat(request.getParameter("montooo"))*-1);
			mov2.setDetail("Transferencia a cuenta propia");
			mov2.setMovementDate(LocalDate.now());
			mov2.setMovementType(new MovementType(4,"Transferencia"));
			negMove.insert(mov2);
			
			//Se modifica el balance de las dos cuentas
			account.setBalance(Float.parseFloat( request.getParameter("txtOrigenModal")));
	        account.setAccountNumber(Integer.parseInt(request.getParameter("txtCtaOrigen")));
	        							
		    negAccount.updateBalanceTransferenciaOrigen(account.getBalance(),account.getAccountNumber()); 
		    
		    account.setBalance(Float.parseFloat( request.getParameter("txtDestinoModal")));
		    account.setAccountNumber(Integer.parseInt(request.getParameter("txtCtaDestino")));
		    		    
		    negAccount.updateBalanceTransferenciaOrigen(account.getBalance(),account.getAccountNumber());
		    
		    
			RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaTransferencia.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("btnPedirTransferencia")!=null) {
						
			float monto = Float.parseFloat(request.getParameter("textAmountToTransferTerModal"));
			int cuentaOrigen = Integer.parseInt(request.getParameter("txtCtaOrigenModal"));
			String cbu = request.getParameter("txtCbuModal");			
			
			if(negAccount.ValidarCBUxString(cbu) != true) {
//				response.sendRedirect("/DashboardCliente.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/DashboardCliente.jsp");
				dispatcher.forward(request, response);
			}
			
			account.setAccountNumber(cuentaOrigen);
	        account.setBalance(Float.parseFloat( request.getParameter("txtOrigenModal")));	
	        	      	        	      	        	        
	        negAccount.updateBalanceTransferenciaOrigen(account.getBalance(), cuentaOrigen); 
                	        
	        account.setCbu(cbu);
			//account.setBalance(Float.parseFloat( request.getParameter("txtDestinoModal")));
		    		    		    
		    negAccount.updateBalanceTransferenciaTercero(monto, cbu); 
		    
		    Movement mov = new Movement();
		    mov.setAccountNumber(cuentaOrigen);
		    mov.setMovementDate(LocalDate.now());
		    mov.setDetail("Transferencia");
		    mov.setAmount(monto*-1);
		    mov.setMovementType(new MovementType(4,"Transferencia"));
		    mov.setStatus(true);		    
		    negMove.insert(mov);
		    
		    mov.setAccountNumber(negAccount.obtenerNumeroCuenta(cbu));
		    mov.setMovementDate(LocalDate.now());
		    mov.setDetail("Transferencia");
		    mov.setAmount(monto);
		    mov.setMovementType(new MovementType(4,"Transferencia"));
		    mov.setStatus(true);		    
		    negMove.insert(mov);
	        	       	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaTransferencia.jsp");
			dispatcher.forward(request, response);
		}
	    
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
