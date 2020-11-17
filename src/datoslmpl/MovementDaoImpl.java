package datoslmpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import datos.MovementDao;
import dominio.Account;
import dominio.Movement;


public class MovementDaoImpl implements MovementDao {

	private ConnectionDB cn;
	
	public MovementDaoImpl() {}
	
	@Override
	public List<Movement> getAll() {
		cn = new ConnectionDB();
		cn.Open();
		ArrayList<Movement> list = new ArrayList<Movement>();
		try {
		    
			ResultSet rs= cn.query("SELECT movement.id, movement.movementDate, movement.details, movement.amount, movement.MovementTypeId, movement.status"
			        + "FROM movement");
			while(rs.next()) {
				Movement mov = new Movement();
				mov.setId(rs.getInt("movement.id"));
				mov.setMovementDate(rs.getDate("movement.movementDate"));
				mov.setDetails(rs.getString("movement.details"));
				mov.setAmount(rs.getFloat("movement.amount"));
				mov.setMovementTypeId(rs.getInt("movement.MovementTypeId"));
				mov.setStatus(rs.getBoolean("movement.status"));
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
	public boolean insert(Account accountOrigen, Account accountDestino, DecimalFormat monto, String dni) {
		boolean status = true;
		
		cn = new ConnectionDB();
		cn.Open();
		
		String query = "INSERT INTO movement (id, movementDate, details, amount , MovementTypeId , status) VALUES "
				+ "('"+accountOrigen.getAccountNumber() +"', '"+accountOrigen.getCreationDate()+"', '"+monto+"', '"+accountOrigen.getAccountypeid()+"', '"+dni+"')";
		
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

	
}
