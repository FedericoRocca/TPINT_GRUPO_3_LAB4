package dominio;

import java.time.LocalDate;


public class Fee {

	private int idLoan;
	private int feeNumber;
	private double amount;
	private LocalDate paymentDeadline;
	private int state;
	
	
	public Fee(){
		
	}

	public Fee(int idLoan, int feeNumber, double amount, LocalDate paymentDeadline, int state) {
		super();
		this.idLoan = idLoan;
		this.feeNumber = feeNumber;
		this.amount = amount;
		this.paymentDeadline = paymentDeadline;
		this.state = state;
	}

	public int getIdLoan() {
		return idLoan;
	}


	public void setIdLoan(int idLoan) {
		this.idLoan = idLoan;
	}


	public int getFeeNumber() {
		return feeNumber;
	}


	public void setFeeNumber(int feeNumber) {
		this.feeNumber = feeNumber;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public LocalDate getPaymentDeadline() {
		return paymentDeadline;
	}


	public void setPaymentDeadline(LocalDate paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}
}
