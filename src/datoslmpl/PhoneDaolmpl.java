package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.PhoneDao;
import entidades.Phone;
import entidades.User;

public class PhoneDaolmpl implements PhoneDao {

	private ConnectionDB cn;
	
	public PhoneDaolmpl() {
		
	}
	
	@Override
	public List<Phone> getAll(int dni) { /*Todos los tel�fonos del cliente por dni*/
		cn = new ConnectionDB();
		cn.Open();
		List<Phone> list = new ArrayList<Phone>();
		try {
			ResultSet rs= cn.query("SELECT phones.number, phones.description, users.dni, users.firstName, users.lastName"
									+ "FROM Phones INNER JOIN Users ON phones.userDni = users.dni"
									+ "WHERE phones.status = true && phones.userDni =" + dni);
			while(rs.next()) {
				Phone phone = new Phone();
				phone.setNumber(rs.getInt("phones.number"));
				phone.setDescription(rs.getString("phones.description"));
				phone.setStatus(rs.getBoolean("phones.status"));
				
				User user = new User();
				user.setDni(rs.getString("users.dni"));
				user.setFirstName(rs.getString("users.firstName"));
				user.setLastName(rs.getString("users.lastName"));
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
	public boolean insert(Phone phone, int dni) {
		
		boolean status = true;
		
		cn = new ConnectionDB();
		cn.Open();
		
		String query = "INSERT INTO Phones (number, description, userDni, status) VALUES "
						+ "('"+phone.getNumber()+"', '"+phone.getDescription()+"', '"+dni+"', '"+phone.isStatus()+"')";
		System.out.println(query);
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

	@Override
	public boolean update(Phone phone, int dni) {
		
		boolean status = true;
		
		cn = new ConnectionDB();
		cn.Open();
		
		String query = "UPDATE Phones SET number = '"+ phone.getNumber() +"', '"+ phone.getDescription() +"' WHERE phone.userDni = " + dni;
		
		try
		 {
			status=cn.execute(query);
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

	@Override
	public boolean delete(int number) {
		
		boolean status = true;
		cn = new ConnectionDB();
		cn.Open();		 
		String query = "UPDATE Phones SET status=0 WHERE numberPhone=" + number;
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