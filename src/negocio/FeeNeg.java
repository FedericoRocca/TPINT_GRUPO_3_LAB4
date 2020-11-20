package negocio;

import java.util.ArrayList;
import java.util.List;

import dominio.Fee;

public interface FeeNeg {

	public ArrayList<Fee> getPendingFees(String dni);
	public boolean insert(Fee fee);
	public boolean update(Fee fee);
	
}
