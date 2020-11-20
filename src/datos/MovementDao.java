package datos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dominio.Account;
import dominio.Movement;

public interface MovementDao {

	public List<Movement> getAll();
	public boolean insert(Movement movement);
	public ArrayList<Movement> getAllByAccount(int accountNumber);
	public float obtenerSaldo(int accountNumber);
	
}
