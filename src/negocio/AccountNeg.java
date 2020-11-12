package negocio;
import datos.AccountDao;
import dominio.Account;

public class AccountNeg {

	public Boolean InsertarCuenta(Account a){
		try {
			AccountDao ac = new AccountDao();
			ac.CrearCuenta(a);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public Boolean BajaCuenta(Account a){
		try {
			AccountDao ac = new AccountDao();
			ac.DarBaja(a);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
	