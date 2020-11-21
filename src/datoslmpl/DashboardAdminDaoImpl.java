package datoslmpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.DashboardAdminDao;
import datos.ReportesDao;
import dominio.City;
import dominio.DashboardAdmin;
import dominio.LisCantidadClientesPaises;
import dominio.Loan;
import dominio.RepBalancesMayores;
import dominio.RepIngresosInteres;

public class DashboardAdminDaoImpl  implements DashboardAdminDao
{

    private ConnectionDB cn;


    @Override
    public float getTotalBalance()
    {
        float result = 0;
        try
        {
            cn = new ConnectionDB();
            cn.Open();
            ResultSet rs= cn.query("select sum(balance) as 'totalBalance' from accounts;");
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
    public int getTotalMovements()
    {
        int result = 0;
        try
        {
            cn = new ConnectionDB();
            cn.Open();
            ResultSet rs= cn.query("select IFNULL(count(id), 0) as 'cantidadMovimientos' from movements;");
            while(rs.next()) 
            {
                result = rs.getInt("cantidadMovimientos");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getTotalLoans()
    {
        int result = 0;
        try
        {
            cn = new ConnectionDB();
            cn.Open();
            ResultSet rs= cn.query("select count(*) as 'cantidadPrestamos' from loan;");
            while(rs.next()) 
            {
                result = rs.getInt("cantidadPrestamos");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getTotalAccounts()
    {
        int result = 0;
        try
        {
            cn = new ConnectionDB();
            cn.Open();
            ResultSet rs= cn.query("select count(*) as 'cantidadCuentas' from accounts;");
            while(rs.next()) 
            {
                result = rs.getInt("cantidadCuentas");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
}
