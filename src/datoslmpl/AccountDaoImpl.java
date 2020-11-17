package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.IAccountDao;
import dominio.Account;
import dominio.User;

public class AccountDaoImpl implements IAccountDao{

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
			        + "FROM Accounts");
			while(rs.next()) {
				Account acc = new Account();
				acc.setAccountNumber(rs.getInt("accounts.accountNumber"));
				acc.setAccountDni(rs.getString("accounts.accountDni"));
				acc.setCreationDate(rs.getDate("accounts.creationDate"));
				acc.setAccountypeid(rs.getInt("accounts.accountTypeId"));
				acc.setCbu(rs.getString("accounts.cbu"));
				acc.setBalance(rs.getFloat("accounts.balance"));
				acc.setStatus(rs.getBoolean("accounts.status"));
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
		    
			ResultSet rs= cn.query("SELECT accounts.accountNumber, accounts.accountDni, accounts.creationDate, accounts.accountTypeId, accounts.cbu, accounts.balance, accounts.status"
			        + "FROM Accounts WHERE accounts.accountDni = " + dni);
			while(rs.next()) {
				Account acc = new Account();
				acc.setAccountNumber(rs.getInt("accounts.accountNumber"));
				acc.setAccountDni(rs.getString("accounts.accountDni"));
				acc.setCreationDate(rs.getDate("accounts.creationDate"));
				acc.setAccountypeid(rs.getInt("accounts.accountTypeId"));
				acc.setCbu(rs.getString("accounts.cbu"));
				acc.setBalance(rs.getFloat("accounts.balance"));
				acc.setStatus(rs.getBoolean("accounts.status"));
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


}
