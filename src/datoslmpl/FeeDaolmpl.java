package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.FeeDao;
import dominio.Fee;

public class FeeDaolmpl implements FeeDao {
	
	private ConnectionDB cn;
	
	public FeeDaolmpl() {
		
	}
	
	@Override
	public List<Fee> getPendingFees(String dni) {
		
		cn = new ConnectionDB();
		cn.Open();
		List<Fee> list = new ArrayList<Fee>();
		
		try {
			
			ResultSet rs = cn.query("SELECT fxl.idLoan,fxl.feeNumber,fxl.paymentDeadline,fxl.state,l.monthlyFee FROM bdbanco.fees_x_loans as fxl " 
									+"INNER JOIN bdbanco.loan as l on fxl.idLoan = l.id WHERE l.dni ="+dni+" AND fxl.state = 0 GROUP BY idLoan;");
			
			while(rs.next()) {
				Fee f = new Fee();
				f.setIdLoan(rs.getInt("fxl.idLoan"));
				f.setFeeNumber(rs.getInt("fxl.feeNumber"));
				f.setPaymentDeadline(rs.getDate("fxl.paymentDeadline").toLocalDate());
				f.setState(rs.getInt("fxl.state"));
				f.setAmount(rs.getDouble("l.monthlyFee"));
				
				list.add(f);
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
	public boolean insert(Fee fee) {
		boolean estado = true;
		
		cn = new ConnectionDB();
		cn.Open();
		
		try {
			
			estado = cn.execute("INSERT INTO fees_x_loans (idLoan,feeNumber,paymentDeadline,state)"
								+"VALUES ("+fee.getIdLoan()+","+fee.getFeeNumber()+",'"+fee.getPaymentDeadline()+"',0);");	
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
	public boolean update(Fee fee) {
		boolean estado = true;
		cn = new ConnectionDB();
		cn.Open();
		
		try {
			
			estado = cn.execute("UPDATE bdbanco.fees_x_loans SET state=1 WHERE idLoan="+fee.getIdLoan()+" and feeNumber="+fee.getFeeNumber()+";");
		}			
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}

		return estado;	
	}
}
