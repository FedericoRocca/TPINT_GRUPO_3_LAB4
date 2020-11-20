package dominio;

import java.text.DecimalFormat;
import java.util.Date;

import exceptions.BalanceNegativoException;

public class Account {
	
	private int accountNumber;
    private String accountDni;
    private Date creationDate;
    private int accountypeid;
    private String cbu;
    private float balance;
    private Boolean status;
    
    public Account(){}
    
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountDni() {
		return accountDni;
	}
	public void setAccountDni(String accountDni) {
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
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) throws BalanceNegativoException {
		if(balance < 0)
			throw new BalanceNegativoException();
		else
			this.balance = balance;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
