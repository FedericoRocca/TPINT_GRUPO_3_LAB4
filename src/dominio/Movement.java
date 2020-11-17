package dominio;

import java.text.DecimalFormat;
import java.util.Date;

public class Movement {

	private int id;
	private Date movementDate;
	private String details;
	private Float amount;
	private int MovementTypeId;
	private boolean status;
	
	public Movement() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getMovementDate() {
		return movementDate;
	}
	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public int getMovementTypeId() {
		return MovementTypeId;
	}
	public void setMovementTypeId(int movementTypeId) {
		MovementTypeId = movementTypeId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
	
	
}
