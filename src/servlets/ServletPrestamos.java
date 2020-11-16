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

import dominio.Loan;
import dominio.LoanState;
import javafx.util.converter.LocalDateStringConverter;
import negocio.LoanNeg;
import negociolmpl.LoanNeglmpl;
import sun.rmi.server.Dispatcher;

@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoanNeg negLoan = new LoanNeglmpl();
	
    public ServletPrestamos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("listar")!=null) {
				
				request.setAttribute("listLoans", negLoan.listPending());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AutorizacionPrestamo.jsp");
				dispatcher.forward(request, response);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date todayDate = new Date();
			
			if(request.getParameter("btnPedirPrestamo")!=null) {
				Loan ln = new Loan();
				
				
				ln.setDni("36249161"); /*Se trae de session*/
				ln.setAccountNumber(Integer.parseInt(request.getParameter("txtAccountNumber"))); /*A futuro se levanta de un dropdown*/
				ln.setAmountReqByCustomer(Double.parseDouble(request.getParameter("txtAmountReqByCustomer")));
				ln.setAmountOfFees(Integer.parseInt(request.getParameter("comboAmountOfFees")));
				ln.setLoanState(new LoanState(1,"Pendiente"));
				ln.setLoanDate(LocalDate.now());
				
				/*Se suma meses segun la cantidad de cuotas más dos*/
				ln.setPaymentDeadline(ln.getLoanDate().plusMonths(ln.getAmountOfFees() + 2));

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
				
				RequestDispatcher rd = request.getRequestDispatcher("/AltaPrestamo.jsp");
				rd.forward(request, response);
				
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
