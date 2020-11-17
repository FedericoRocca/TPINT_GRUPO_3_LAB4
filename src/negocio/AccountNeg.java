package negocio;

import java.util.ArrayList;


import dominio.Account;

public interface AccountNeg {
	public ArrayList<Account> GetAllbyDni(String dni);
	public ArrayList<Account> GetAll();
	public Boolean InsertarCuenta(Account a);
	public Boolean BajaCuenta(Account a);
	public int ObtenerCantCuentas(Account a);
	public int ObtenerUltimaCuenta();
	public Boolean ValidarCBU(Account a);
}
