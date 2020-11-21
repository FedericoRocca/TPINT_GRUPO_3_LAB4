package datoslmpl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datos.AccountDao;
import dominio.Account;
import dominio.Movement;
import dominio.MovementType;
import dominio.User;

public class AccountDaoImpl implements AccountDao {

	private ConnectionDB cn;

	public AccountDaoImpl() {

	}

	@Override
	public List<Account> getAll() {
		cn = new ConnectionDB();
		cn.Open();
		ArrayList<Account> list = new ArrayList<Account>();
		ResultSet rs = null;
		try {
			String query = "select a.accountNumber,a.accountDni,a.creationDate,a.accountTypeId,at.description,a.cbu,a.status,Sum(m.amount) as balance from accounts a inner join movements m on a.accountNumber = m.accountNumber inner join accountstype at on a.accountTypeId = at.id Where a.status = 1 group by a.accountNumber";
			rs = cn.query(query);
			while (rs.next()) {
				Account acc = new Account();
				acc.setAccountNumber(rs.getInt("accountNumber"));
				acc.setAccountDni(rs.getString("accountDni"));
				acc.setCreationDate(rs.getDate("creationDate"));
				acc.setAccountypeid(rs.getInt("accountTypeId"));
				acc.setAccountypeDesc(rs.getString("description"));
				acc.setCbu(rs.getString("cbu"));
				acc.setBalance(rs.getFloat("balance"));
				acc.setStatus(rs.getBoolean("status"));

				list.add(acc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
			String query = "select a.accountNumber,a.accountDni,a.creationDate,a.accountTypeId,at.description,a.cbu,a.status,Sum(m.amount) as balance from accounts a inner join movements m on a.accountNumber = m.accountNumber inner join accountstype at on a.accountTypeId = at.id Where a.status = 1 ";
			query += " AND a.accountDni = '" + dni + "' group by a.accountNumber";
			ResultSet rs = cn.query(query);
			while (rs.next()) {
				Account acc = new Account();
				acc.setAccountNumber(rs.getInt("accountNumber"));
				acc.setAccountDni(rs.getString("accountDni"));
				acc.setCreationDate(rs.getDate("creationDate"));
				acc.setAccountypeid(rs.getInt("accountTypeId"));
				acc.setAccountypeDesc(rs.getString("description"));
				acc.setCbu(rs.getString("cbu"));
				acc.setBalance(rs.getFloat("balance"));
				acc.setStatus(rs.getBoolean("status"));
				list.add(acc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public void DarBaja(Account a) {
		try {
			cn = new ConnectionDB();
			cn.Open();
			String query = "Update Accounts Set Status = 0 WHERE ";
			query += "AccountNumber= " + a.getAccountNumber() + " AND accountDni= '" + a.getAccountDni() + "'";
			cn.execute(query);
			cn.close();
			cn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int ObtenerCantCuentas(Account a) {
		ResultSet rs = null;
		try {
			cn = new ConnectionDB();
			cn.Open();
			String query = "Select COUNT(*) as Cant from Accounts where status = 1 AND accountDni= '"
					+ a.getAccountDni() + "'";
			rs = cn.query(query);
			rs.next();
			return rs.getInt("Cant");
		} catch (Exception e) {
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
		} catch (Exception e) {
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
			if (!todas) {
				query += "CBU = '" + a.getCbu() + "'";
			} else {
				query += "accountDni = '" + a.getAccountDni() + "'";
			}
			return cn.query(query);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean updateBalance(float x, int accountNumber) {
		boolean estado = false;
		try {
			cn = new ConnectionDB();
			cn.Open();
			estado = cn.execute(
					"Update Accounts Set Balance = balance + (" + x + ") WHERE AccountNumber= " + accountNumber);
			cn.close();
			cn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return estado;
	}
	
	@Override
	public Boolean updateBalanceTransferenciaOrigen(float x, int accountNumberOrigen) {
		boolean estado = false;
		try {
			cn = new ConnectionDB();
			cn.Open();
			estado = cn.execute(
					"Update Accounts Set Balance = " + x + " WHERE AccountNumber= " + accountNumberOrigen);
			cn.close();
			cn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return estado;
	}

	@Override
	public Boolean updateBalanceTransferenciaTercero(float x,String cbu) {
		
		boolean estado = false;
		try {
			cn = new ConnectionDB();
			cn.Open();
			estado = cn.execute(
					"Update Accounts Set Balance = " + x + " WHERE cbu = " + cbu);
			cn.close();
			cn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return estado;
		
	}
	
	@Override
	public Account obtenerCuenta(int accountNumber) {
		Account acc = new Account();
		try {
			cn = new ConnectionDB();
			cn.Open();
			ResultSet rs = cn.query("Select accountNumber,accountDni,creationDate,accountTypeId,cbu,balance,status from Accounts where status = 1 AND AccountNumber = "+accountNumber); 

			if(rs.next()) {
				acc.setAccountNumber(rs.getInt("accountNumber"));
				acc.setAccountDni(rs.getString("accountDni"));
				acc.setCreationDate(rs.getDate("creationDate"));
				acc.setAccountypeid(rs.getInt("accountTypeId"));
				acc.setCbu(rs.getString("cbu"));
				acc.setBalance(rs.getFloat("balance"));
				acc.setStatus(rs.getBoolean("status"));
			} 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		
		return acc;
	}
}
