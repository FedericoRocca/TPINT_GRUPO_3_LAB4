package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.CityDao;
import dominio.City;

public class CityDaolmpl implements CityDao {

	private ConnectionDB cn;
	
	@Override
	public ArrayList<City> getAll() {
		cn = new ConnectionDB();
		cn.Open();
		
		ArrayList<City> list = new ArrayList<City>();
		try 
		{
			ResultSet rs= cn.query("SELECT id, description FROM cities;");
			while(rs.next()) 
			{
			    City cits = new City();
			    cits.setId(rs.getInt("id"));
			    cits.setDescription(rs.getString("description"));
				list.add(cits);
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			cn.close();
		}
		
		return list;
	}

}
