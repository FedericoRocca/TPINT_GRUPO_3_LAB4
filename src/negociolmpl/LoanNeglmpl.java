package negociolmpl;

import java.util.ArrayList;

import datos.LoanDao;
import datoslmpl.LoanDaolmpl;
import dominio.Loan;
import negocio.LoanNeg;

public class LoanNeglmpl implements LoanNeg {

	private LoanDao loanDao = new LoanDaolmpl();
	
	public LoanNeglmpl(LoanDao loanDao)
	{
		this.loanDao = loanDao;
	}
	
	public LoanNeglmpl()
	{
		
	}
	
	@Override
	public ArrayList<Loan> listPending() {
		return (ArrayList<Loan>) loanDao.getPending();
	}

	@Override
	public boolean insert(Loan loan) {
		return loanDao.insert(loan);
	}

	@Override
	public boolean updateLoanState(int idAccount, int idLoanState) {
		return loanDao.updateLoanState(idAccount, idLoanState);
	}
}
