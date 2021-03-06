package datos;

import java.sql.ResultSet;
import java.util.List;

import dominio.Account;

public interface AccountDao {
	
	public List<Account> getAll();
	public List<Account> getAllbyDni(String dni);
	public void CrearCuenta(Account a);
	public void DarBaja(Account a);
	public int ObtenerCantCuentas(Account a);
	public int ObtenerUltimaCuenta();
	public ResultSet ObtenerCuenta(boolean todas, Account a);
	public Boolean updateBalance(float x, int accountNumber);
	public Boolean updateBalanceTransferenciaOrigen(float x, int accountNumerOrigen);
	public Boolean updateBalanceTransferenciaTercero(float x,String cbu);
	public Account obtenerCuenta(int accountNumber);
	public int obtenerNumeroCuenta(String cbu);
	boolean ValidarCBUxString(String cbu);
}
