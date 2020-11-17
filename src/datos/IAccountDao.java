package datos;

import java.util.List;

import dominio.Account;

public interface IAccountDao {
	
	public List<Account> getAll();
	public List<Account> getAllbyDni(String dni);
}
