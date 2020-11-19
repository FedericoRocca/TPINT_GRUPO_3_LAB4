package datoslmpl;

import java.util.List;

import datos.FeeDao;
import dominio.Fee;

public class FeeDaolmpl implements FeeDao {
	
	private ConnectionDB cn;
	public FeeDaolmpl() {
		
	}
	@Override
	public List<Fee> getPendingFees(String dni) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
