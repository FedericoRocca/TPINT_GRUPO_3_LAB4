package dominio;

import java.sql.Date;


public class Loan {

	private int id;
	private String dni;
	private int accountNumber;
	private Date loanDate;
	private Float amountReqByCustomer;
	private Float amountInt;
	private Float monthlyFee;
	private Date paymentDeadline;
	private int amountOfFees;
	private LoanState loanState;
	
	
	public Loan() {

	}
	
	public Loan(int id, String dni, int accountNumber, Date loanDate, Float amountReqByCustomer, Float amountInt,
			Float monthlyFee, Date paymentDeadline, int amountOfFees, LoanState loanState) {
		super();
		this.id = id;
		this.dni = dni;
		this.accountNumber = accountNumber;
		this.loanDate = loanDate;
		this.amountReqByCustomer = amountReqByCustomer;
		this.amountInt = amountInt;
		this.monthlyFee = monthlyFee;
		this.paymentDeadline = paymentDeadline;
		this.amountOfFees = amountOfFees;
		this.loanState = loanState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Float getAmountReqByCustomer() {
		return amountReqByCustomer;
	}

	public void setAmountReqByCustomer(Float amountReqByCustomer) {
		this.amountReqByCustomer = amountReqByCustomer;
	}

	public Float getAmountInt() {
		return amountInt;
	}

	public void setAmountInt(Float amountInt) {
		this.amountInt = amountInt;
	}

	public Float getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(Float monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public Date getPaymentDeadline() {
		return paymentDeadline;
	}

	public void setPaymentDeadline(Date paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}

	public int getAmountOfFees() {
		return amountOfFees;
	}

	public void setAmountOfFees(int amountOfFees) {
		this.amountOfFees = amountOfFees;
	}

	public LoanState getLoanState() {
		return loanState;
	}

	public void setLoanState(LoanState loanState) {
		this.loanState = loanState;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", dni=" + dni + ", accountNumber=" + accountNumber + ", loanDate=" + loanDate
				+ ", amountReqByCustomer=" + amountReqByCustomer + ", amountInt=" + amountInt + ", monthlyFee="
				+ monthlyFee + ", paymentDeadline=" + paymentDeadline + ", amountOfFees=" + amountOfFees
				+ ", loanState=" + loanState + "]";
	}
	
	
	
}