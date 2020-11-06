package negocio;

import java.util.ArrayList;

import entidades.Phone;

public interface PhoneNeg {
	public ArrayList<Phone> GetAll();
	public boolean insert(Phone phone);
	public boolean edit(Phone phone);
	public boolean delete(int id);
}
