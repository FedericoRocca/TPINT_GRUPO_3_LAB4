package datos;

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
			query += "AccountNumber= " + a.getAccountNumber()+" AND Dni= "+a.getAccountDni();
			cn.execute(query);
			cn.close();
			cn = null;
		}
		catch(Exception e)
		{
			throw new Exception();
		}
	}
}
