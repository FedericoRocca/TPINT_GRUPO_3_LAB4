package negociolmpl;

import java.util.ArrayList;
import java.util.List;

import datos.FeeDao;
import datoslmpl.FeeDaolmpl;
import dominio.Fee;
import negocio.FeeNeg;

public class FeeNeglmpl implements FeeNeg {
	
	public FeeNeglmpl() {
		
	}
	
	private FeeDao feeDao = new FeeDaolmpl();

	@Override
	public ArrayList<Fee> getPendingFees(String dni) {
		return (ArrayList<Fee>) feeDao.getPendingFees(dni);
	}

	@Override
	public boolean insert(Fee fee) {
		return feeDao.insert(fee);
	}

	@Override
	public boolean update(Fee fee) {
		return feeDao.update(fee);
	}

}
