package datos;
import java.util.List;

import dominio.User;

public interface UserDao {
	
	public List<User> getAll();
	public User getUser(String dni);
	public boolean insert(User user);
	public boolean update(User user);
	public boolean delete(String dni);
	public boolean exists(String userName);
	
}
