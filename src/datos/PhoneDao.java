package datos;

import java.util.List;

import dominio.Phone;

public interface PhoneDao {
	
	public List<Phone> getAll(int id);
	public boolean insert(Phone phone, String dni);
	public boolean update(Phone phone, int dni);
	public boolean delete(int id);
	
}
