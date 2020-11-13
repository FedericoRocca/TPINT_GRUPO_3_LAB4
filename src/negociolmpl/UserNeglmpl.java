package negociolmpl;

import java.util.ArrayList;

import datos.UserDao;
import datoslmpl.UserDaolmpl;
import dominio.User;
import negocio.UserNeg;

public class UserNeglmpl implements UserNeg {
	
	private UserDao userDao = new UserDaolmpl();
	
	public UserNeglmpl(UserDao userDao) 
	{
		this.userDao = userDao;
	}
	
	public UserNeglmpl() 
	{
		
	}
	
	@Override
	public ArrayList<User> GetAll() {		
		return (ArrayList<User>) userDao.getAll();
	}

	@Override
	public User getUser(String dni) {
		return userDao.getUser(dni);
	}

	@Override
	public boolean insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public boolean edit(User user) {
		return userDao.update(user);
	}

	@Override
	public boolean delete(String dni) {
		return userDao.delete(dni);
	}

    @Override
    public boolean exists(String userName)
    {
        return userDao.exists(userName);
    }

    @Override
    public User getUserByUsername(String userName, String password)
    {
        return userDao.getUserByUsernameAndPassword(userName, password);
    }

}
