package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.UserDao;
import entidades.User;
//import entidades.Phone;


public class UserDaolmpl implements UserDao{
	
	private ConnectionDB cn;
	
	public UserDaolmpl() {
		
	}

	@Override
	public List<User> getAll() {
		cn = new ConnectionDB();
		cn.Open();
		List<User> list = new ArrayList<User>();
		try {
			ResultSet rs = cn.query("SELECT u.dni, u.cuil, u.firstname, u.lastname, u.email, u.nationality, u.birthDate, u.gender FROM Users u");
			while(rs.next()) 
			{
				User user = new User();
				user.setDni(rs.getString("u.dni"));
				user.setFirstName(rs.getString("u.firstname"));
				user.setLastName(rs.getString("u.lastname"));
				user.setEmail(rs.getString("u.email"));
				user.setNacionality(rs.getString("u.nationality"));		
				user.setBirthDate(rs.getDate("u.birthDate")); 
				user.setGender(rs.getString("u.gender"));
				
				
//				Phone phone = new Phone();
//				phone.setNumber(rs.getInt("ph.number"));
//				phone.setDescription(rs.getString("ph.description"));
//				
//				phone.setUser(user);
				
				list.add(user);				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		return list;
	}

	@Override
	public User getUser(int dni) { /* está incompleto */
		cn = new ConnectionDB();
		cn.Open();
		User user = new User();
		try 
		{
			ResultSet rs = cn.query("Select user.nombre, user.apellido, user.dni from users where user.estado=1 && user.dni=" + dni);
			rs.next();

			user.setDni(rs.getString("user.dni"));
			user.setFirstName(rs.getString("user.nombre"));							
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		
		return user;	
	}

	@Override
	public boolean insert(User user) {
		
		boolean status=true;
		
		cn = new ConnectionDB();
		cn.Open();
		
		String query = "INSERT INTO users () VALUES ('"+ user.getDni() +"', '"+ user.getFirstName() +"')";
		System.out.println(query);
		try {
			status = cn.execute(query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		return status;
	}

	@Override
	public boolean update(User user) {
		
		boolean status=true;
		
		return status;
	}

	@Override
	public boolean delete(int dni) {
		
		boolean status = true;
		cn = new ConnectionDB();
		cn.Open();		 
		String query = "UPDATE Users SET status = 0 WHERE dni=" + dni;
		try 
		 {
			status = cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return status;
	}
}
