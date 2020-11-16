package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.LoanDao;
import dominio.Loan;
import dominio.LoanState;

public class LoanDaolmpl implements LoanDao{

	private ConnectionDB cn;
	
	public LoanDaolmpl() {
		
	}
	
	@Override
	public List<Loan> getPending(){
		cn = new ConnectionDB();
		cn.Open();
		List<Loan> list = new ArrayList<Loan>();
		try {
			
			ResultSet rs = cn.query("SELECT l.id,l.dni,l.accountNumber,l.loanDate,l.amountInt,l.amountReqByCustomer,l.paymentDeadline,l.monthlyFee,l.amountOfFees,l.loanStateId,ls.state FROM loan as l INNER JOIN loanstate as ls on ls.id = l.loanStateId WHERE l.status = 1 AND loanStateId = 1");
			while (rs.next()) {
				Loan loan = new Loan();
				loan.setId(rs.getInt("l.id"));
				loan.setAccountNumber(rs.getInt("l.accountNumber"));
				loan.setDni(rs.getString("l.dni"));
				loan.setLoanDate(rs.getDate("l.loanDate"));
				loan.setAmountReqByCustomer(rs.getFloat("l.amountReqByCustomer"));
				loan.setAmountInt(rs.getFloat("l.amountInt"));
				loan.setMonthlyFee(rs.getFloat("l.monthlyFee"));
				loan.setPaymentDeadline(rs.getDate("l.paymentDeadline"));
				loan.setAmountOfFees(rs.getInt("l.amountOfFees"));
				
				LoanState loanState = new LoanState();
				loanState.setId(rs.getInt("l.loanStateId"));
				loanState.setState(rs.getString("ls.state"));
				
				loan.setLoanState(loanState);
				list.add(loan);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		return list;
	}
	
	@Override
	public boolean insert(Loan loan) {
		return true;
	}
	
	@Override
	public boolean update(Loan loan) {
		return true;
	}
}
