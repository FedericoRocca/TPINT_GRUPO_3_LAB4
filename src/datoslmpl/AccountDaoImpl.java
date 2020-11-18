package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.AccountDao;
import dominio.Account;
import dominio.User;

public class AccountDaoImpl implements AccountDao{

	private ConnectionDB cn;
	
	public AccountDaoImpl() {
		
	}
	
	@Override
	public List<Account> getAll() {
		cn = new ConnectionDB();
		cn.Open();
		ArrayList<Account> list = new ArrayList<Account>();
		try {
		    
			ResultSet rs= cn.query("SELECT accounts.accountNumber, accounts.accountDni, accounts.creationDate, accounts.accountTypeId, accounts.cbu, accounts.balance, accounts.status"
			        + "FROM Accounts WHERE accounts.status=1");
			while(rs.next()) {
				Account acc = new Account();
				acc.setAccountNumber(rs.getInt("accounts.accountNumber"));
				acc.setAccountDni(rs.getString("accounts.accountDni"));
				acc.setCreationDate(rs.getDate("accounts.creationDate"));
				acc.setAccountypeid(rs.getInt("accounts.accountTypeId"));
				acc.setCbu(rs.getString("accounts.cbu"));
				acc.setBalance(rs.getFloat("accounts.balance"));
				acc.setStatus(rs.getBoolean("accounts.status"));
				
				list.add(acc);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		
		return list;
	}

	@Override
	public List<Account> getAllbyDni(String dni) {
		cn = new ConnectionDB();
		cn.Open();
		ArrayList<Account> list = new ArrayList<Account>();
		try {
		    String query = "SELECT AccountNumber,accountDni,CreationDate,AccountTypeId,CBU,Balance,Status FROM Accounts";
		    query += " WHERE Status = 1 AND accountDni = '" + dni+"'";
			ResultSet rs= cn.query(query);
			while(rs.next()) {
				Account acc = new Account();
				acc.setAccountNumber(rs.getInt("accounts.accountNumber"));
				acc.setAccountDni(rs.getString("accounts.accountDni"));
				acc.setCreationDate(rs.getDate("accounts.creationDate"));
				acc.setAccountypeid(rs.getInt("accounts.accountTypeId"));
				acc.setCbu(rs.getString("accounts.cbu"));
				acc.setBalance(rs.getFloat("accounts.balance"));
				acc.setStatus(rs.getBoolean("accounts.status"));
				list.add(acc);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		
		return list;
	}
	
	@Override	
	public void CrearCuenta(Account a)
	{
		try {	
			cn = new ConnectionDB();
			cn.Open();
			String query = "Insert INTO Accounts (AccountNumber,accountDni,CreationDate,AccountTypeId,CBU,Balance,Status) VALUES (";
			query += a.getAccountNumber()+","+a.getAccountDni()+",NOW(),"+a.getAccountypeid()+","+a.getCbu()+","+a.getBalance()+",1)";
			cn.execute(query);
			cn.close();
			cn = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override	
	public void DarBaja(Account a)
	{
		try {	
			cn = new ConnectionDB();
			cn.Open();
			String query = "Update Accounts Set Status = 0 WHERE ";
			query += "AccountNumber= " + a.getAccountNumber()+" AND accountDni= '"+a.getAccountDni()+"'";
			cn.execute(query);
			cn.close();
			cn = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override	
	public int ObtenerCantCuentas(Account a) {
		ResultSet rs = null;
		try {
			cn = new ConnectionDB();
			cn.Open();
			String query = "Select COUNT(*) as Cant from Accounts where status = 1 AND accountDni= '"+a.getAccountDni()+"'";
			rs = cn.query(query);
			rs.next();
			return rs.getInt("Cant");
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	@Override	
	public int ObtenerUltimaCuenta() {
		ResultSet rs = null;
		try {
			cn = new ConnectionDB();
			cn.Open();
			String query = "Select ifnull(MAX(accountnumber),0) as Cant from Accounts";
			rs = cn.query(query);
			rs.next();
			return rs.getInt("Cant");
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	@Override	
	public ResultSet ObtenerCuenta(boolean todas, Account a) {
		ResultSet rs = null;
		try {
			cn = new ConnectionDB();
			cn.Open();
			String query = "Select AccountNumber,accountDni,CreationDate,AccountTypeId,CBU,Balance,Status from Accounts where status = 1 AND ";
			if(!todas) {
				query += "CBU = '"+a.getCbu()+"'";
			}
			else
			{
				query += "accountDni = '"+a.getAccountDni()+"'";
			}
			return cn.query(query);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public Boolean updateBalance(float x, int accountNumber) {
		boolean estado = false;
		try {	
			cn = new ConnectionDB();
			cn.Open();
			estado = cn.execute("Update Accounts Set Balance = balance + ("+ x +") WHERE AccountNumber= " + accountNumber);
			cn.close();
			cn = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return estado;
	}

}
