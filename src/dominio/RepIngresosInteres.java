package dominio;

import java.time.LocalDate;
import java.util.ArrayList;

public class RepIngresosInteres {

	private LocalDate fromDate;
	private LocalDate toDate;
	private ArrayList<Loan> loans;
	private float balance;
	
	public RepIngresosInteres() {
		
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public ArrayList<Loan> getLoans() {
		return loans;
	}

	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
}
