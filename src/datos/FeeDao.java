package datos;

import java.util.List;

import dominio.Fee;


public interface FeeDao {
	
	public List<Fee> getPendingFees(String dni);
	public boolean insert(Fee fee);
	public boolean update(Fee fee);

}
