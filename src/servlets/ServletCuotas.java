package servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Account;
import dominio.Fee;
import dominio.Movement;
import dominio.MovementType;
import dominio.User;
import exceptions.BalanceNegativoException;
import negocio.AccountNeg;
import negocio.FeeNeg;
import negocio.MovementNeg;
import negociolmpl.AccountNegImpl;
import negociolmpl.FeeNeglmpl;
import negociolmpl.MovementNegImpl;


@WebServlet("/ServletCuotas")
public class ServletCuotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletCuotas() {
        super();
    }

    AccountNeg negAccount = new AccountNegImpl();
    FeeNeg negFee = new FeeNeglmpl();
    MovementNeg negMovement = new MovementNegImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		try {
		    User userLogin = new User();
            userLogin = (User)request.getSession().getAttribute("userLogin");
            if( userLogin == null )
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
                dispatcher.forward(request, response);
            }
            
            /*Se listan las cuentas y cuotas*/
			request.setAttribute("listaAccount", negAccount.GetAllbyDni(userLogin.getDni()));
			request.setAttribute("listaCuotas", negFee.getPendingFees(userLogin.getDni()));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoPrestamos.jsp");
			dispatcher.forward(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
		String dniUserLogin = null;
		boolean estado = false;
		try {
		    
		    User userLogin = new User();
            userLogin = (User)request.getSession().getAttribute("userLogin");
            if( userLogin == null )
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
                dispatcher.forward(request, response);
            }
            dniUserLogin = userLogin.getDni();
		    
			if(request.getParameter("btnPagar")!=null) {
				
				//Se verifica que el usuario haya seleccionado una cuenta
				if(request.getParameter("debitAccount").contentEquals("Seleccione...")) {
					request.setAttribute("selecCuenta", true);
					request.setAttribute("listaAccount", negAccount.GetAllbyDni(dniUserLogin));
					request.setAttribute("listaCuotas", negFee.getPendingFees(dniUserLogin));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoPrestamos.jsp");
					dispatcher.forward(request, response);	
				}
				
				//Se verifica que la cuenta tenga un balance suficiente para hacer el pago
				Account acc = negAccount.obtenerCuenta(Integer.parseInt(request.getParameter("debitAccount")));
				//Si se quiere ingresar un balance negativo se catchea
				acc.setBalance(acc.getBalance() - (Float.parseFloat(request.getParameter("feeAmount"))));
				
				//Se actualiza el balance de la cuenta
				negAccount.updateBalance(Float.parseFloat(request.getParameter("feeAmount"))*-1,Integer.parseInt(request.getParameter("debitAccount")));
			    
				//Se marca la cuota como paga, state = 1
				Fee f = new Fee();
				f.setFeeNumber(Integer.parseInt(request.getParameter("feeNumber")));
				f.setIdLoan(Integer.parseInt(request.getParameter("idLoan")));
				negFee.update(f);
				
				//Se registra el movimiento
				Movement mov = new Movement();
				mov.setAccountNumber(Integer.parseInt(request.getParameter("debitAccount")));
				mov.setAmount(Float.parseFloat(request.getParameter("feeAmount"))*-1);
				mov.setDetail("Pago de la cuota N� " + (request.getParameter("feeNumber").toString()) +" del pr�stamo N� " + (request.getParameter("idLoan")).toString());
				mov.setMovementDate(LocalDate.now());
				mov.setMovementType(new MovementType(3,"Pago de prestamo"));
				estado = negMovement.insert(mov);
				
				//Se vuelve a listar las cuentas y cuotas
				request.setAttribute("estadoCuota", estado);
				request.setAttribute("listaAccount", negAccount.GetAllbyDni(userLogin.getDni()));
				request.setAttribute("listaCuotas", negFee.getPendingFees(userLogin.getDni()));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoPrestamos.jsp");
				dispatcher.forward(request, response);	
			}
		} 
		catch (BalanceNegativoException e) {
			request.setAttribute("saldoNegativo", true);
			request.setAttribute("listaAccount", negAccount.GetAllbyDni(dniUserLogin));
			request.setAttribute("listaCuotas", negFee.getPendingFees(dniUserLogin));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoPrestamos.jsp");
			dispatcher.forward(request, response);	
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
