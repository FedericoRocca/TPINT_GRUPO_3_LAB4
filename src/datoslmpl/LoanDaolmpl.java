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
				loan.setLoanDate(rs.getDate("l.loanDate").toLocalDate());
				loan.setAmountReqByCustomer(rs.getDouble("l.amountReqByCustomer"));
				loan.setAmountInt(rs.getDouble("l.amountInt"));
				loan.setMonthlyFee(rs.getDouble("l.monthlyFee"));
				loan.setPaymentDeadline(rs.getDate("l.paymentDeadline").toLocalDate());
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
		
		boolean estado = true;
		
		cn = new ConnectionDB();
		cn.Open();
		
		try {
			
			estado = cn.execute("INSERT INTO Loan (dni,accountNumber,loanDate,amountInt,amountReqByCustomer,paymentDeadline,amountOfFees,monthlyFee,loanStateId,status)"
								+"VALUES ('"+loan.getDni()+"','"+loan.getAccountNumber()+"','"+loan.getLoanDate()+"','"+loan.getAmountInt()+"','"+loan.getAmountReqByCustomer()+"','"+loan.getPaymentDeadline()+"','"+loan.getAmountOfFees()+"','"+loan.getMonthlyFee()+"','"+loan.getLoanState().getId()+"',1);");	
		}			
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}

		return estado;
	}
	
	@Override
	public boolean update(Loan loan) {
		return true;
	}
}
