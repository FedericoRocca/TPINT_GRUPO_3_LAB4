package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.ReportesDao;
import dominio.RepBalancesMayores;

public class ReportesDaoImpl  implements ReportesDao
{

    private ConnectionDB cn;
    
    @Override
    public List<RepBalancesMayores> executeReport(float valueBalance)
    {
        ArrayList<RepBalancesMayores> aux = new ArrayList<>();
        try
        {
            cn = new ConnectionDB();
            cn.Open();
            ResultSet rSet = cn.query("select us.firstName, us.lastName, us.dni, acc.accountNumber, acc.cbu, acc.balance "
                    + "from accounts acc "
                    + "inner join users us on acc.accountDni = us.dni "
                    + "where acc.balance > " + valueBalance + " "
                    + "order by acc.balance desc;");
            while(rSet.next())
            {
                RepBalancesMayores tmp = new RepBalancesMayores();
                tmp.setFirstName( rSet.getString("firstName") );
                tmp.setLastName( rSet.getString("lastName") );
                tmp.setDni( rSet.getString("dni") );
                tmp.setAccountNumber( rSet.getInt("accountNumber") );
                tmp.setCbu( rSet.getString("cbu") );
                tmp.setBalance( rSet.getLong("balance"));
                aux.add(tmp);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            cn.close();
        }
        return aux;
    }
    
}
