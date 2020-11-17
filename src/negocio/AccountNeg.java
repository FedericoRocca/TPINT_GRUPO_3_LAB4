package negocio;
import java.sql.ResultSet;
import datoslmpl.AccountDaoImpl;
import dominio.Account;

public class AccountNeg {
	
	private AccountDaoImpl ac = new AccountDaoImpl();
	
	public Boolean InsertarCuenta(Account a){
		try {
			ac = new AccountDaoImpl();
			ac.CrearCuenta(a);
			ac = null;
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public Boolean BajaCuenta(Account a){
		try {
			ac = new AccountDaoImpl();
			ac.DarBaja(a);
			ac = null;
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public int ObtenerCantCuentas(Account a) {
		try {
			return ac.ObtenerCantCuentas(a);
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	public int ObtenerUltimaCuenta() {
		try {
			return ac.ObtenerUltimaCuenta();
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	public Boolean ValidarCBU(Account a){
		try {
			ResultSet rs = null;
			rs = ac.ObtenerCuenta(false, a);
			ac = null;
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
}
	