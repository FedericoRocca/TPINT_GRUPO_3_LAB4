package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.PhoneDao;
import dominio.Phone;
import dominio.User;

public class PhoneDaolmpl implements PhoneDao {

	private ConnectionDB cn;
	
	public PhoneDaolmpl() {
		
	}
	
	@Override
	public ArrayList<Phone> getAll(int dni) { /*Todos los teléfonos del cliente por dni*/
		cn = new ConnectionDB();
		cn.Open();
		ArrayList<Phone> list = new ArrayList<Phone>();
		try {
		    
			ResultSet rs= cn.query("SELECT phones.numberPhone, phones.description\r\n" + 
			        "FROM Phones \r\n" + 
			        "INNER JOIN Users ON phones.userDni = users.dni \r\n" + 
			        "WHERE phones.userDni = '" + dni + "'");
			while(rs.next()) {
				Phone phone = new Phone();
				phone.setNumber(rs.getLong("phones.numberPhone"));
				phone.setDescription(rs.getString("phones.description"));
				
				list.add(phone);
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
						+ "('"+phone.getNumber()+"', '"+phone.getDescription()+"', '"+dni+"')";
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

    public void updatePhoneForDNI(String dni, ArrayList<Phone> phone)
    {
        try
        {
            for (Phone phone2 : phone)
            {
                cn = new ConnectionDB();
                cn.Open();
                String query = "UPDATE phones SET numberPhone = '" + phone2.getNumber() + "' WHERE (description = '" + phone2.getDescription() + "') and (userDni = '" + dni + "');";
                cn.execute(query);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally 
        {
            cn.close();
        }
    }
	
}
