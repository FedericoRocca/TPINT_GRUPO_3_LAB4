package negocio;

import java.util.ArrayList;
import dominio.Loan;

public interface LoanNeg {

	public ArrayList<Loan> listPending();
	public boolean insert(Loan loan);
	public boolean updateLoanState(int idLoan, int idLoanState);
}
