package negociolmpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import datos.AccountDao;
import datoslmpl.AccountDaoImpl;
import dominio.Account;
import negocio.AccountNeg;

public class AccountNegImpl implements AccountNeg{

	public AccountDao accountDao = new AccountDaoImpl();
	
	public AccountNegImpl (AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	public AccountNegImpl() {
		
	}
	
	@Override
	public ArrayList<Account> GetAllbyDni(String dni) {
		return (ArrayList<Account>) accountDao.getAllbyDni(dni);
		
	}

	@Override
	public ArrayList<Account> GetAll() {
		return (ArrayList<Account>) accountDao.getAll();
	}

	@Override
	public Boolean InsertarCuenta(Account a){
		try {
			accountDao = new AccountDaoImpl();
			accountDao.CrearCuenta(a);
			accountDao = null;
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	@Override
	public Boolean BajaCuenta(Account a){
		try {
			accountDao = new AccountDaoImpl();
			accountDao.DarBaja(a);
			accountDao = null;
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	@Override
	public int ObtenerCantCuentas(Account a) {
		try {
			return accountDao.ObtenerCantCuentas(a);
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	@Override
	public int ObtenerUltimaCuenta() {
		try {
			return accountDao.ObtenerUltimaCuenta();
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	@Override
	public Boolean ValidarCBU(Account a){
		try {
			ResultSet rs = null;
			rs = accountDao.ObtenerCuenta(false, a);
			accountDao = null;
			if(!rs.next()) {
				return true;
			}
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public Boolean updateBalance(float x, int accountNumber) {
		return accountDao.updateBalance(x, accountNumber);
	}
	
	public Boolean updateBalanceTransferenciaOrigen(float x, int accountNumerOrigen) {
		return accountDao.updateBalanceTransferenciaOrigen(x, accountNumerOrigen);
	}

	@Override
	public Account obtenerCuenta(int accountNumber) {
		return accountDao.obtenerCuenta(accountNumber);
	}
}
