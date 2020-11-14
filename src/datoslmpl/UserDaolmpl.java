package datoslmpl;

import java.io.Console;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.UserDao;
import dominio.Phone;
import dominio.Role;
import dominio.User;
import negociolmpl.PhoneNeglmpl;


public class UserDaolmpl implements UserDao{
	
	private ConnectionDB cn;
	
	private static final String longQuery = "SELECT u.dni, u.cuil, u.firstname, u.lastname, u.email, u.nationality, u.birthDate, u.gender, p.description as Phone, p.numberPhone as number FROM Users u LEFT JOIN phones p ON p.userDni = u.dni WHERE u.dni = ";
	
	public UserDaolmpl() {
		
	}

	@Override
	public List<User> getAll() {
		cn = new ConnectionDB();
		cn.Open();
		ArrayList<User> list = new ArrayList<>();
		ResultSet rs = null;
		try {			
			rs = cn.query("SELECT u.dni, u.cuil, u.firstname, u.lastname, u.email, u.nationality, u.birthDate, u.gender FROM Users u INNER JOIN Roles_x_Users rxu ON rxu.dni = u.dni WHERE rxu.roleId = 2");
			System.out.println(rs);
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
	public User getUser(String dni) {
		ResultSet rs = null;
		
		User user = null;

		String query = longQuery +  dni;
		System.out.println(query);
		
		try 
		{
			cn = new ConnectionDB();
			cn.Open();
			rs = cn.query(query);
			rs.next();
				if(rs.getBoolean("status")) 
				{
					user = new User(
							rs.getString("dni"),
							rs.getString("firstName"),
							rs.getString("lastName"),
							rs.getString("userName"),
							rs.getString("password"),
							rs.getString("cuil"),
							rs.getString("gender"),
							rs.getString("nationality"),
							rs.getDate("birthDate"),
							rs.getString("address"),
							rs.getString("city"),
							rs.getString("email"),
							rs.getBoolean("status")
					);
					ArrayList<Phone> phonesByDni = new ArrayList<Phone>();
					PhoneDaolmpl pdi = new PhoneDaolmpl();
					phonesByDni = pdi.getAll(Integer.parseInt(dni));
					user.setPhones(phonesByDni);
				}						
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
		
		boolean status = false;
		
		cn = new ConnectionDB();
		
		try 
		{
			CallableStatement sp = cn.Open().prepareCall("CALL SP_InsertCustomer(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			sp.setString(1, user.getDni());
			sp.setString(2, user.getFirstName());
			sp.setString(3, user.getLastName());
			sp.setString(4, user.getUserName());
			sp.setString(5, user.getPassword());
			sp.setString(6, user.getCuil());
			sp.setString(7, user.getGender());
			sp.setString(8, user.getNacionality());
			
		    java.sql.Date sqlDate = new java.sql.Date(user.getBirthDate().getTime());
		    System.out.println("utilDate:" + sqlDate);
		    System.out.println("sqlDate:" + sqlDate);
			
			sp.setDate(9, sqlDate);
			sp.setString(10, user.getAddress());
			sp.setString(11, user.getCity());
			sp.setString(12, user.getEmail());
			
			//Los teléfonos son un arraylist ahora, hay que recorrer esa lista e insertar uno a uno
			//Phone phone = new Phone();
			//sp.setLong(13, phone.getNumber());
			//sp.setString(14, phone.getDescription());
			sp.setLong(13, 123456);
            sp.setString(14, "descripc");
			status = sp.execute();
			
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
		
		boolean status = false;
		
		cn = new ConnectionDB();
		
		try 
		{
			CallableStatement sp = (CallableStatement) cn.Open().prepareCall("CALL SP_UpdateCustomer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			sp.setString(1, user.getDni());
			sp.setString(2, user.getFirstName());
			sp.setString(3, user.getLastName());
			sp.setString(4, user.getPassword());
			sp.setString(5, user.getCuil());
			sp.setString(6, user.getGender());
			sp.setString(7, user.getNacionality());
			sp.setString(8, user.getBirthDate().toString());
			sp.setString(9, user.getAddress());
			sp.setString(10, user.getCity());
			sp.setString(11, user.getEmail());
			sp.setBoolean(12, user.isStatus());
			
			Phone phone = new Phone();
			
			sp.setLong(13, phone.getNumber());
			sp.setString(14, phone.getDescription());
						
			status = sp.execute();
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
	public boolean delete(String dni) {
		
		boolean status = false;
		cn = new ConnectionDB();
				 
		try 
		 {
			CallableStatement sp = (CallableStatement) cn.Open().prepareCall("CALL SP_DeleteCustomer");
			
			sp.setString(1, dni);
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
    public boolean exists(String userName)
    {
        cn = new ConnectionDB();
        cn.Open();
        ResultSet rs = null;
        try {
            rs = cn.query("SELECT count(*) FROM Users where Users.username = '" + userName +"';");
            rs.next();
            int coincidences = rs.getInt(1);
            if( coincidences >= 1 )
            {
                return true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            cn.close();
        }
        return false;
    }

    @Override
    public User getUserByUsernameAndPassword(String userName, String password)
    {
        ResultSet rs = null;
        User user = null;
        Role rol;
        
        String query = "SELECT u.dni, u.firstname, u.lastname, u.username, u.password, u.cuil, u.gender, u.nationality, u.birthdate, u.address, u.city," + 
                " u.email, u.status, rol.id as rolid, rol.name as rolname, rol.status as rolstatus" + 
                " FROM Users u " + 
                " INNER JOIN roles_x_users rolx ON rolx.dni = u.dni" + 
                " INNER JOIN roles rol on rol.id = rolx.roleid" + 
                " WHERE u.username = '" + userName + "' and u.password = '" + password + "';";
        
        try 
        {
            cn = new ConnectionDB();
            cn.Open();
            rs = cn.query(query);
            while(rs.next())
            {
                if(rs.getBoolean("status")) 
                {
                    user = new User(
                            rs.getString("dni"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getString("cuil"),
                            rs.getString("gender"),
                            rs.getString("nationality"),
                            rs.getDate("birthDate"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("email"),
                            rs.getBoolean("status"),
                            rol = new Role(rs.getInt("rolid"),
                                           rs.getString("rolname") ,
                                           rs.getBoolean("rolstatus"))
                            
                    );
                            
                }  
            }                     
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            cn.close();
        }
        return user;
    }

}
