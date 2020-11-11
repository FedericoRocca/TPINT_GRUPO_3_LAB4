package dominio;

import java.text.DecimalFormat;
import java.util.Date;

public class Account {
	
	private int accountNumber;
    private int accountDni;
    private Date creationDate;
    private int accountypeid;
    private int cbu;
    private float balance;
    
    
    public Account(){}
    
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAccountDni() {
		return accountDni;
	}
	public void setAccountDni(int accountDni) {
		this.accountDni = accountDni;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getAccountypeid() {
		return accountypeid;
	}
	public void setAccountypeid(int accountypeid) {
		this.accountypeid = accountypeid;
	}
	public int getCbu() {
		return cbu;
	}
	public void setCbu(int cbu) {
		this.cbu = cbu;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}





	
}
