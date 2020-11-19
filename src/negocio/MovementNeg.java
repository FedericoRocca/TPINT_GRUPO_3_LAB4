package negocio;

import java.text.DecimalFormat;
import java.util.List;

import dominio.Account;
import dominio.Movement;

public interface MovementNeg {

	public List<Movement> getAll();
	public boolean insert(Movement movement);
}
