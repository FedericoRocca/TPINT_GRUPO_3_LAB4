package dominio;

import java.time.LocalDate;


public class Fee {

	private int idLoan;
	private int feeNumber;
	private LocalDate paymentDeadline;
	private int state;
	
	
	public Fee(){
		
	}
	
	public Fee(int idLoan, int feeNumber, LocalDate paymentDeadline, byte state) {
		super();
		this.idLoan = idLoan;
		this.feeNumber = feeNumber;
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
