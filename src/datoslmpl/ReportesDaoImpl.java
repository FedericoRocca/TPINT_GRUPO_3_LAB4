package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.ReportesDao;
import dominio.LisCantidadClientesPaises;
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

    @Override
    public List<LisCantidadClientesPaises> executeReport()
    {
        ArrayList<LisCantidadClientesPaises> aux = new ArrayList<>();
        try
        {
            cn = new ConnectionDB();
            cn.Open();
            ResultSet rSet = cn.query("select nat.country as 'Pais', count(nat.country) as 'Cantidad' from users us " + 
                                      "inner join nationalities nat on us.nationality = nat.id " + 
                                      "where us.status = 1 " + 
                                      "group by nat.country " + 
                                      "order by Cantidad desc;");
            while(rSet.next())
            {
                LisCantidadClientesPaises tmp = new LisCantidadClientesPaises();
                tmp.setPais(rSet.getString("Pais"));
                tmp.setCantidad(rSet.getInt("Cantidad"));
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
