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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
			
			account.setAccountNumber(cuentaOrigen);
	        account.setBalance(Float.parseFloat( request.getParameter("txtOrigenModal")));	
	        	      	        	      	        	        
	        negAccount.updateBalanceTransferenciaOrigen(account.getBalance(), cuentaOrigen); 
                	        
	        account.setCbu(cbu);
			account.setBalance(Float.parseFloat( request.getParameter("txtDestinoModal")));
		    		    		    
		    negAccount.updateBalanceTransferenciaTercero(account.getBalance() + monto, cbu); 
		    
		    Movement mov = new Movement();
		    mov.setAccountNumber(cuentaOrigen);
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
