package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.NationalityDao;
import dominio.Nationality;

public class NationalityDaolmpl implements NationalityDao {

	private ConnectionDB cn;
	
	@Override
	public List<Nationality> getAll() {
		cn = new ConnectionDB();
		cn.Open();
		
		List<Nationality> list = new ArrayList<Nationality>();
		try 
		{
			ResultSet rs= cn.query("SELECT id, gentilic FROM Nationalities");
			while(rs.next()) 
			{
				Nationality nat = new Nationality();
				nat.setId(rs.getInt("nationalities.id"));
				nat.setDescription(rs.getString("nationalities.description"));
				list.add(nat);
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
