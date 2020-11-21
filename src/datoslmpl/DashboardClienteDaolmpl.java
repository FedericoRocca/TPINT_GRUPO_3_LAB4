package datoslmpl;

import java.sql.ResultSet;

import datos.DashboardClienteDao;

public class DashboardClienteDaolmpl  implements DashboardClienteDao{
	
	private ConnectionDB cn;

	@Override
	public float getTotalBalance(String dni) {
		float result = 0;
		try {
			cn = new ConnectionDB();
			cn.Open();
			ResultSet rs = cn.query("select sum(balance) as 'totalBalance' from accounts where accountDni =" + dni );
			while(rs.next()) 
            {
                result = rs.getFloat("totalBalance");
            }
		}
		catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public int getTotalTransfers(String dni) {
		int result = 0;
        try
        {
            cn = new ConnectionDB();
            cn.Open();
            ResultSet rs= cn.query("select count(*) as 'cantidad' from movements inner join accounts on accounts.accountNumber = movements.accountNumber where MovementTypeId = 4 && accounts.accountDni = " + dni);
            while(rs.next()) 
            {
                result = rs.getInt("cantidad");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public int getAuthsApproved(String dni) {
		int result = 0;
        try
        {
            cn = new ConnectionDB();
            cn.Open();
            ResultSet rs= cn.query("select count(*) as 'cantidad' from loan where loanStateId = 2 && dni = " + dni);
            while(rs.next()) 
            {
                result = rs.getInt("cantidad");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
	}
	
}
