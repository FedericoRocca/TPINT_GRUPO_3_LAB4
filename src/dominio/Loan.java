package dominio;

import java.time.LocalDate;


public class Loan {

	private int id;
	private String dni;
	private int accountNumber;
	private LocalDate loanDate;
	private Double amountReqByCustomer;
	private Double amountInt;
	private Double monthlyFee;
	private LocalDate paymentDeadline;
	private int amountOfFees;
	private LoanState loanState;
	
	
	public Loan() {

	}

	
	public Loan(int id, String dni, int accountNumber, LocalDate loanDate, Double amountReqByCustomer, Double amountInt,
			Double monthlyFee, LocalDate paymentDeadline, int amountOfFees, LoanState loanState) {
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


	public LocalDate getLoanDate() {
		return loanDate;
	}


	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}


	public Double getAmountReqByCustomer() {
		return amountReqByCustomer;
	}


	public void setAmountReqByCustomer(Double amountReqByCustomer) {
		this.amountReqByCustomer = amountReqByCustomer;
	}


	public Double getAmountInt() {
		return amountInt;
	}


	public void setAmountInt(Double amountInt) {
		this.amountInt = amountInt;
	}


	public Double getMonthlyFee() {
		return monthlyFee;
	}


	public void setMonthlyFee(Double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}


	public LocalDate getPaymentDeadline() {
		return paymentDeadline;
	}


	public void setPaymentDeadline(LocalDate paymentDeadline) {
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