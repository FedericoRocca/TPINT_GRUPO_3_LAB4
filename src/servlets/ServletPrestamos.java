package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.ls.LSException;

import dominio.Account;
import dominio.Fee;
import dominio.Loan;
import dominio.LoanState;
import dominio.Movement;
import dominio.MovementType;
import dominio.User;
import negocio.AccountNeg;
import negocio.FeeNeg;
import negocio.LoanNeg;
import negocio.MovementNeg;
import negociolmpl.AccountNegImpl;
import negociolmpl.FeeNeglmpl;
import negociolmpl.LoanNeglmpl;
import negociolmpl.MovementNegImpl;
import sun.rmi.server.Dispatcher;

@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoanNeg negLoan = new LoanNeglmpl();
	AccountNeg negAccount = new AccountNegImpl();
	MovementNeg negMovement = new MovementNegImpl();
	FeeNeg negFee = new FeeNeglmpl();
	
    public ServletPrestamos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    
		    User userLogin = new User();
		    userLogin = (User)request.getSession().getAttribute("userLogin");
		    if( userLogin == null )
		    {
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
		        dispatcher.forward(request, response);
		    }
            
			if(request.getParameter("listar")!=null) {
				
				request.setAttribute("listLoans", negLoan.listPending());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AutorizacionPrestamo.jsp");
				dispatcher.forward(request, response); /*TODO java.lang.IllegalStateException: Cannot forward after response has been committed*/
			}
			
			if(request.getParameter("insert")!=null) {
				request.setAttribute("listaAccount", negAccount.GetAllbyDni(userLogin.getDni())); //Este DNI se tiene que sacar por session.
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaPrestamo.jsp");
				dispatcher.forward(request, response);
			}
		} 
		catch (Exception e) {
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
			
			if(request.getParameter("btnPedirPrestamo")!=null) {
				
				Loan ln = new Loan();
				ln.setDni("36249161"); /*Se trae de session*/
				ln.setAccountNumber(Integer.parseInt(request.getParameter("comboAccountNumber")));
				ln.setAmountReqByCustomer(Double.parseDouble(request.getParameter("txtAmountReqByCustomer")));
				ln.setAmountOfFees(Integer.parseInt(request.getParameter("comboAmountOfFees")));
				ln.setLoanState(new LoanState(1,"Pendiente"));
				ln.setLoanDate(LocalDate.now());
				
				/*Se suma meses segun la cantidad de cuotas más uno*/
				ln.setPaymentDeadline(ln.getLoanDate().plusMonths(ln.getAmountOfFees() + 1));

				/*Se le agrega un interes al importe pedido por el cliente dependiendo de la cantidad de cuotas*/
				Double interes;
				switch (ln.getAmountOfFees()) {
				case 3:
					interes = 1.05;
					break;
				case 6:
					interes = 1.07;
					break;
				case 12:
					interes = 1.1;
					break;
				case 24:
					interes = 1.2;
					break;

				default:
					interes = 1.1;
					break;
				}
				
				ln.setAmountInt(ln.getAmountReqByCustomer() * interes);
				
				/*Se divide el importe con el interes por la cantidad de cuotas*/
				ln.setMonthlyFee(ln.getAmountInt()/ln.getAmountOfFees());
				
				
				boolean estado = true;
				estado = negLoan.insert(ln);
				request.setAttribute("estadoPrestamo", estado);
				//Se vuelve a cargar el descolgable
				request.setAttribute("listaAccount", negAccount.GetAllbyDni(userLogin.getDni())); //Este DNI se tiene que sacar por session.
				
				RequestDispatcher rd = request.getRequestDispatcher("/AltaPrestamo.jsp");
				rd.forward(request, response);	
			}
			
			if(request.getParameter("btnAceptado")!= null) {
				//Se cambia el estado del Prestamo
				int idLoan = Integer.parseInt(request.getParameter("idLoan").toString());
				boolean estado = true;
				estado = negLoan.updateLoanState(idLoan, 2);

				//Se modifica el balance de la cuenta
				negAccount.updateBalance(Float.parseFloat(request.getParameter("amountReqByCustomer")),Integer.parseInt(request.getParameter("accountNumber")));
				
				//Se genera el movimiento del Alta de prestamo
				Movement mov = new Movement();
				mov.setAccountNumber(Integer.parseInt(request.getParameter("accountNumber")));
				mov.setAmount(Float.parseFloat(request.getParameter("amountReqByCustomer")));
				mov.setDetail("Alta de un prestamo");
				mov.setMovementDate(LocalDate.now());
				mov.setMovementType(new MovementType(2,"Alta de prestamo"));
				
				estado = negMovement.insert(mov);
				
				int cantCuotas = Integer.parseInt(request.getParameter("amountOfFees"));
				//Se generan las cuotas
				for(int i=1;i<=cantCuotas;i++) {
					Fee f = new Fee();
					f.setIdLoan(idLoan);
					f.setFeeNumber(i);
					f.setPaymentDeadline(LocalDate.now().plusMonths(i));
					negFee.insert(f);
				}
				
				//Se vuelve a cargar la lista
				request.setAttribute("listLoans", negLoan.listPending());
				request.setAttribute("estadoPrestamo", estado);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AutorizacionPrestamo.jsp");
				dispatcher.forward(request, response);
			}
			
			if(request.getParameter("btnRechazado")!= null) {
				//Se cambia el estado del Prestamo
				int idLoan = Integer.parseInt(request.getParameter("idLoan").toString());
				boolean estado = true;
				estado = negLoan.updateLoanState(idLoan, 3);
				request.setAttribute("estadoPrestamo", estado);
				
				//Se vuelve a cargar la lista
				request.setAttribute("listLoans", negLoan.listPending());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AutorizacionPrestamo.jsp");
				dispatcher.forward(request, response);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
