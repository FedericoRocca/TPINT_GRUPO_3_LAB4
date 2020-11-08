package datos;
import java.util.List;

import entidades.User;

public interface UserDao {
	
	public List<User> getAll();
	public User getUser(int dni);
	public boolean insert(User user);
	public boolean update(User user);
	public boolean delete(int dni);
	
}
