package datos;

import java.text.DecimalFormat;
import java.util.List;

import dominio.Account;
import dominio.Movement;

public interface MovementDao {

	public List<Movement> getAll();
	public boolean insert(Account accountOrigen, Account accountDestino,DecimalFormat monto ,String dni);

	
}