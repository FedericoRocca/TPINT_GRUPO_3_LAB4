package negocio;

import java.util.ArrayList;

import dominio.Role;

public interface RoleNeg {
	public ArrayList<Role> GetAll(String dni);
	public boolean insert(Role phone, String dni);
	public boolean edit(Role phone, int dni);
	public boolean delete(int id);
}
