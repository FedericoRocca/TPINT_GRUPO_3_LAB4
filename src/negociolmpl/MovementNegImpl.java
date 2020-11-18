package negociolmpl;

import java.text.DecimalFormat;
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
	
	@Override
	public List<Movement> getAll() {
		// TODO Auto-generated method stub
		return movementDao.getAll();
	}

	@Override
	public boolean insert(Account accountOrigen, DecimalFormat monto) {
		// TODO Auto-generated method stub
		return movementDao.insert(accountOrigen, monto);
	}

}
