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
	public ArrayList<Movement> GetAllbyAccount(int accountNumber) {
		return (ArrayList<Movement>) movementDao.getAllByAccount(accountNumber);
	}
	
	@Override
	public float obtenerSaldo(int accountNumber) {
		return movementDao.obtenerSaldo(accountNumber);
	}

	@Override
	public boolean insert(Movement movement) {
		return movementDao.insert(movement);
	}

}
