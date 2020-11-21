package datos;

import java.util.List;

import dominio.Role;

public interface RoleDao {
	
	public List<Role> getAll(String id);
	public boolean insert(Role phone, String dni);
	public boolean update(Role phone, int dni);
	public boolean delete(int id);
	
}
