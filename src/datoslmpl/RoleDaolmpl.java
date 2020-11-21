package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.RoleDao;
import dominio.Phone;
import dominio.Role;
import dominio.User;

public class RoleDaolmpl implements RoleDao {

	private ConnectionDB cn;
	
	public RoleDaolmpl() {
		
	}
	
	@Override
	public ArrayList<Role> getAll(String dni) { /*Todos los teléfonos del cliente por dni*/
		cn = new ConnectionDB();
		cn.Open();
		ArrayList<Role> list = new ArrayList<Role>();
		try {
		    
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
	public boolean insert(Role role, String dni) {
		
		boolean status = true;
		try
        {
		    ConnectionDB cnr = new ConnectionDB();
            cnr.Open();
            String insertr = "INSERT INTO roles_x_users (dni, roleId, status) VALUES ('" + dni + "', 2, 1);";
            status = cnr.execute(insertr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return status;
	}

	@Override
	public boolean update(Role role, int dni) {
		
		boolean status = true;
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
