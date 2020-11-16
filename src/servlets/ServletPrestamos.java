package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Loan;
import dominio.LoanState;
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
			if(request.getParameter("btnPedirPrestamo")!=null) {
				Loan ln = new Loan();
				
				
				ln.setDni("36249161"); /*Se trae de session*/
				ln.setAccountNumber(Integer.parseInt(request.getParameter("txtAccountNumber"))); /*A futuro se levanta de un dropdown*/
				ln.setAmountReqByCustomer(Double.parseDouble(request.getParameter("txtAmountReqByCustomer")));
				ln.setAmountOfFees(Integer.parseInt(request.getParameter("comboAmountOfFees")));
				ln.setLoanState(new LoanState(1,"Pendiente"));
				
				/*ToDo, Fecha actual*/
				ln.setLoanDate(new java.sql.Date(2020,10,15));
				
				/*ToDo, se suma meses segun la cantidad de cuotas más dos*/
				ln.setPaymentDeadline(new java.sql.Date(2020,10,05));

				/*Se le agrega un interes del 10% al importe pedido por el cliente*/
				ln.setAmountInt(ln.getAmountReqByCustomer() * 1.1);
				
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
