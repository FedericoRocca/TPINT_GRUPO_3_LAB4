package negociolmpl;

import java.util.ArrayList;

import datos.PhoneDao;
import datoslmpl.PhoneDaolmpl;
import dominio.Phone;
import negocio.PhoneNeg;

public class PhoneNeglmpl implements PhoneNeg {

	public PhoneDao phoneDao = new PhoneDaolmpl();
	
	public PhoneNeglmpl (PhoneDao phoneDao) {
		this.phoneDao = phoneDao;
	}
	
	@Override
	public ArrayList<Phone> GetAll(int dni) {
		return (ArrayList<Phone>) phoneDao.getAll(dni);
	}

	@Override
	public boolean insert(Phone phone, String dni) {
		return phoneDao.insert(phone, dni);
	}

	@Override
	public boolean edit(Phone phone, int dni) {
		return phoneDao.update(phone, dni);
	}

	@Override
	public boolean delete(int numberPhone) {
		return phoneDao.delete(numberPhone);
	}

}
