package datos;

import java.util.List;

import entidades.Phone;

public interface PhoneDao {
	
	public List<Phone> getAll(int id);
	public boolean insert(Phone phone, int dni);
	public boolean update(Phone phone, int dni);
	public boolean delete(int id);
	
}
