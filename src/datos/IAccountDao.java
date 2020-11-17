package datos;

import java.sql.ResultSet;
import java.util.List;

import dominio.Account;

public interface IAccountDao {
	
	public List<Account> getAll();
	public List<Account> getAllbyDni(String dni);
	public void CrearCuenta(Account a);
	public void DarBaja(Account a);
	public int ObtenerCantCuentas(Account a);
	public int ObtenerUltimaCuenta();
	public ResultSet ObtenerCuenta(boolean todas, Account a);
}
