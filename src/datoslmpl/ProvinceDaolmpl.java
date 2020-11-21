package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.NationalityDao;
import datos.ProvinceDao;
import dominio.Nationality;
import dominio.Province;

public class ProvinceDaolmpl implements ProvinceDao {

	private ConnectionDB cn;
	
	@Override
	public ArrayList<Province> getAll() {
		cn = new ConnectionDB();
		cn.Open();
		
		ArrayList<Province> list = new ArrayList<Province>();
		try 
		{
			ResultSet rs= cn.query("SELECT id, description FROM provinces;");
			while(rs.next()) 
			{
			    Province provs = new Province();
			    provs.setId(rs.getInt("id"));
			    provs.setDescription(rs.getString("description"));
				list.add(provs);
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
