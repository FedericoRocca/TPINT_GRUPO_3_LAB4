package datos;

import java.util.List;

import entidades.Phone;

public interface PhoneDao {
	
	public List<Phone> getAll(int id);
	public boolean insertar(Phone phone, int id);
	public boolean editar(Phone phone, int id);
	public boolean borrar(int id);
	
}
