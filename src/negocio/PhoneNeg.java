package negocio;

import java.util.ArrayList;

import entidades.Phone;

public interface PhoneNeg {
	public ArrayList<Phone> GetAll(int dni);
	public boolean insert(Phone phone, int dni);
	public boolean edit(Phone phone, int dni);
	public boolean delete(int id);
}
