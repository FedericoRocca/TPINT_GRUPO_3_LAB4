package negociolmpl;

import java.util.ArrayList;
import java.util.List;

import datos.DashboardAdminDao;
import datos.ReportesDao;
import datoslmpl.DashboardAdminDaoImpl;
import datoslmpl.ReportesDaoImpl;
import dominio.DashboardAdmin;
import dominio.LisCantidadClientesPaises;
import dominio.RepBalancesMayores;
import dominio.RepIngresosInteres;
import negocio.DashboardAdminNeg;
import negocio.ReportesNeg;

public class DashboardAdminNegImpl implements DashboardAdminNeg
{
    public DashboardAdminDao dashboardAdminDao = new DashboardAdminDaoImpl();
    
    public DashboardAdminNegImpl()
    {
        
    }

    @Override
    public float getTotalBalance()
    {
        return dashboardAdminDao.getTotalBalance();
    }

    @Override
    public int getTotalMovements()
    {
        return dashboardAdminDao.getTotalMovements();
    }
    
    public int getTotalLoans()
    {
        return dashboardAdminDao.getTotalLoans();
    }
    
    public int getTotalAccounts()
    {
        return dashboardAdminDao.getTotalAccounts();
    }
    
}
