package negocio;

import java.util.ArrayList;

import dominio.Phone;

public interface PhoneNeg {
	public ArrayList<Phone> GetAll(int dni);
	public boolean insert(Phone phone, int dni);
	public boolean edit(Phone phone, int dni);
	public boolean delete(int id);
}
