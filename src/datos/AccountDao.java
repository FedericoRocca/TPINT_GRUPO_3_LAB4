package datos;

import java.sql.ResultSet;

import datoslmpl.ConnectionDB;
import dominio.Account;

public class AccountDao {

	private ConnectionDB cn;
	
	public void CrearCuenta(Account a) throws Exception
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
			throw new Exception();
		}
	}
	
	public void DarBaja(Account a) throws Exception 
	{
		try {	
			cn = new ConnectionDB();
			cn.Open();
			String query = "Update Accounts Set Status = 0 WHERE ";
			query += "AccountNumber= " + a.getAccountNumber()+" AND Dni= '"+a.getAccountDni()+"'";
			cn.execute(query);
			cn.close();
			cn = null;
		}
		catch(Exception e)
		{
			throw new Exception();
		}
	}
	
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
}
