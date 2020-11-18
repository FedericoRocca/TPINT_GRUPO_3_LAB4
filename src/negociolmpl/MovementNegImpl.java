package negociolmpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import datos.MovementDao;
import datoslmpl.MovementDaoImpl;
import dominio.Account;
import dominio.Movement;
import negocio.MovementNeg;

public class MovementNegImpl implements MovementNeg{

	public MovementDao movementDao = new MovementDaoImpl();
	
	public MovementNegImpl (MovementDao movementDao) {
		this.movementDao = movementDao;
	}
	
	public MovementNegImpl() {
		
	}
	
	@Override
	public ArrayList<Movement> getAll() {
		return (ArrayList<Movement>) movementDao.getAll();
	}

	@Override
	public boolean insert(Account accountOrigen, DecimalFormat monto) {
		return movementDao.insert(accountOrigen, monto);
	}

}
