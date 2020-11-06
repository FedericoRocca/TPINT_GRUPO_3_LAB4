package negocio;

import java.util.ArrayList;

import entidades.User;

public interface UserNeg {
	
	public ArrayList<User> GetAll();
	public User getUser(int id);
	public boolean insert(User user);
	public boolean edit(User user);
	public boolean delete(int id);
}
