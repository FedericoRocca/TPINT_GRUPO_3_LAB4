package negociolmpl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import datos.AccountDao;
import datoslmpl.AccountDaoImpl;
import datoslmpl.MovementDaoImpl;
import dominio.Account;
import dominio.Movement;
import dominio.MovementType;
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
			CrearMovimiento(a);
			accountDao = null;
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	void CrearMovimiento(Account a) {
		try {
			MovementNegImpl mv = new MovementNegImpl();
			Movement m = new Movement();
			MovementType mt = new MovementType();
			mt.setId(1);
			mt.setDescription("Alta Cuenta");
			LocalDate date = LocalDate.now();
			m.setAccountNumber(a.getAccountNumber());
			m.setAmount(a.getBalance());
			m.setDetail("Alta Cuenta");
			m.setMovementDate(date);
			m.setStatus(true);
			m.setMovementType(mt);
			
			boolean estado = mv.insert(m);
			mt = null;
			mv = null;
			m = null;
		}
		catch(Exception e){
			e.printStackTrace();
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
	public Boolean ValidarCBUxString(String cbu){
		try {
			return accountDao.ValidarCBUxString(cbu);
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
	
	@Override
	public Boolean updateBalanceTransferenciaOrigen(float x, int accountNumerOrigen) {
		return accountDao.updateBalanceTransferenciaOrigen(x, accountNumerOrigen);
	}
	
	@Override
	public Boolean updateBalanceTransferenciaTercero(float x, String cbu) {
		return accountDao.updateBalanceTransferenciaTercero(x, cbu);
	}

	@Override
	public Account obtenerCuenta(int accountNumber) {
		return accountDao.obtenerCuenta(accountNumber);
	}

	@Override
	public int obtenerNumeroCuenta(String cbu) {
		return accountDao.obtenerNumeroCuenta(cbu);
	}
}
