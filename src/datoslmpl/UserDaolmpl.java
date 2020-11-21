package datoslmpl;

import java.io.Console;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import datos.UserDao;
import dominio.Nationality;
import dominio.Phone;
import dominio.Role;
import dominio.User;
import negociolmpl.PhoneNeglmpl;


public class UserDaolmpl implements UserDao{
	
	private ConnectionDB cn;
	
	private static final String longQuery = "SELECT u.dni, u.cuil, u.firstname,u.address ,u.lastname,u.userName,u.password,u.cuil,u.city, u.email, u.nationality, "
	        + "u.birthDate, u.gender, u.status, p.description as Phone, p.numberPhone as number "
	        + "FROM Users u "
	        + "LEFT JOIN phones p ON p.userDni = u.dni "
	        + "WHERE u.dni = ";
	
	public UserDaolmpl() {
		
	}

	@Override
	public List<User> getAll() {
		cn = new ConnectionDB();
		cn.Open();
		ArrayList<User> list = new ArrayList<>();
		ResultSet rs = null;
		try {	
			rs = cn.query("SELECT u.dni, u.cuil, u.firstname, u.lastname, u.email, nats.country, u.birthdate FROM Users u INNER JOIN Roles_x_Users rxu ON rxu.dni = u.dni INNER JOIN Nationalities nats on u.nationality = nats.id WHERE rxu.roleId = 2 and u.status != 0");
			while(rs.next()) 
			{
				User user = new User();
				user.setDni(rs.getString("u.dni"));
				user.setCuil(rs.getString("u.cuil"));
				user.setFirstName(rs.getString("u.firstname"));
				user.setLastName(rs.getString("u.lastname"));
				user.setEmail(rs.getString("u.email"));
				user.setNacionality(rs.getString("nats.country"));		
				user.setBirthDate(rs.getDate("u.birthDate")); 
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
		Nationality nationality = null;
		User user = null;

		String query = "SELECT u.dni, u.cuil, u.firstname,u.address ,u.lastname,u.userName,u.password,u.cuil,u.city, u.email, nats.id as 'idCountry', "
		        + "nats.gentilic, u.birthDate, u.gender, u.status, p.description as Phone, p.numberPhone as number "
		        + "FROM Users u "
		        + "LEFT JOIN phones p ON p.userDni = u.dni "
		        + "INNER JOIN nationalities nats on u.nationality = nats.id "
		        + "WHERE u.dni = '" +  dni + "';";
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
							nationality = new Nationality(rs.getInt("idCountry"),
                                    rs.getString("gentilic")),
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
		    
		    
		    java.sql.Date sqlDate = new java.sql.Date(user.getBirthDate().getTime());
	        cn = new ConnectionDB();
	        cn.Open();
	        String insert = "INSERT INTO Users (dni,firstName,lastName,userName,password,cuil,gender,nationality,birthDate,address,city,email,status) "
	                + "VALUES ('" + user.getDni() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getUserName() + 
	                "' ,'" + user.getPassword() + "' ,'" + user.getCuil() + "' ,'" + user.getGender() + "' ,'" + user.getNacionality() + "' ,'" 
	                + sqlDate + "' ,'" + user.getAddress() + "' ,'" + user.getCity() + "' ,'" + user.getEmail() + "' ,1);";
	        status = cn.execute(insert);  
	        
	        for (Phone p : user.getPhone())
            {
	            ConnectionDB cnp = new ConnectionDB();
	            cnp.Open();
	            String insertp = "INSERT INTO phones (numberPhone, description, userDni) VALUES ('" + p.getNumber() + "', '" + p.getDescription() + "', '" + user.getDni() + "');";
	            cnp.execute(insertp);  
            }
			
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
			CallableStatement sp = (CallableStatement) cn.Open().prepareCall("CALL SP_UpdateCustomer(?,?,?,?,?,?,?,?,?,?)");
			
			sp.setString(1, user.getDni());
			sp.setString(2, user.getFirstName());
			sp.setString(3, user.getLastName());
			sp.setString(4, user.getUserName());
			sp.setString(5, user.getCuil());
			sp.setString(6, user.getGender());
			sp.setString(7, user.getNacionality());
            java.sql.Date sqlDate = new java.sql.Date(user.getBirthDate().getTime());
			sp.setDate(8, sqlDate);
			sp.setString(9, user.getEmail());
			sp.setString(10, user.getPassword());
			
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
			CallableStatement sp = (CallableStatement) cn.Open().prepareCall("CALL SP_DeleteCustomer(?)");
			sp.setString(1, dni);
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
        Nationality nationality = null;
        Role rol;
        
        String query = "SELECT u.dni, u.firstname, u.lastname, u.username, u.password, u.cuil, u.gender, nats.id as 'idCountry', "
                + "nats.gentilic, u.birthdate, u.address, u.city, u.email, u.status, rol.id as rolid, rol.name as rolname, rol.status as rolstatus "
                + "FROM Users u "
                + "INNER JOIN roles_x_users rolx ON rolx.dni = u.dni "
                + "INNER JOIN roles rol on rol.id = rolx.roleid "
                + "INNER JOIN nationalities nats on u.nationality = nats.id "
                + "WHERE u.username = '" + userName + "' and u.password = '" + password + "';";
        
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
                            nationality = new Nationality(rs.getInt("idCountry"),
                                    rs.getString("gentilic")),
                            rs.getDate("birthDate"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("email"),
                            rs.getBoolean("status"),
                            rol = new Role(rs.getInt("rolid"),
                                           rs.getString("rolname") ,
                                           rs.getBoolean("rolstatus"))
                            
                    );
                    ArrayList<Phone> phonesByDni = new ArrayList<Phone>();
                    PhoneDaolmpl pdi = new PhoneDaolmpl();
                    phonesByDni = pdi.getAll(Integer.parseInt(user.getDni()));
                    user.setPhones(phonesByDni);
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
