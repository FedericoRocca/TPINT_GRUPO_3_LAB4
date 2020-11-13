package negocio;

import java.util.ArrayList;

import dominio.User;

public interface UserNeg {
	
	public ArrayList<User> GetAll();
	public User getUser(String dni);
	public User getUserByUsername(String userName, String password);
	public boolean insert(User user);
	public boolean edit(User user);
	public boolean delete(String dni);
	public boolean exists(String userName);
}
