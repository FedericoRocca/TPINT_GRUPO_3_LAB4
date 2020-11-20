package dominio;

import java.time.LocalDate;


public class Movement {

	private int id;
	private int accountNumber;
	private LocalDate movementDate;
	private String detail;
	private Float amount;
	private MovementType movementType;
	private boolean status;
	
	public Movement() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public LocalDate getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(LocalDate movementDate) {
		this.movementDate = movementDate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String MovementTypeDesc(){
		return this.movementType.getDescription();
	}

	@Override
	public String toString() {
		return "Movement [id=" + id + ", accountNumber=" + accountNumber + ", movementDate=" + movementDate
				+ ", detail=" + detail + ", amount=" + amount + ", movementType=" + movementType + ", status=" + status
				+ "]";
	}
}
