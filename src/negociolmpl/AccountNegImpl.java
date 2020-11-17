package negociolmpl;

import java.util.ArrayList;

import datos.IAccountDao;
import datoslmpl.AccountDaoImpl;
import dominio.Account;
import negocio.IAccountNeg;

public class AccountNegImpl implements IAccountNeg{

	public IAccountDao accountDao = new AccountDaoImpl();
	
	public AccountNegImpl (IAccountDao accountDao) {
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

}
