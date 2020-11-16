package datos;
import java.util.List;

import dominio.Loan;

public interface LoanDao {

	public List<Loan> getPending();
	public boolean insert(Loan loan);
	public boolean update(Loan loan);
}
