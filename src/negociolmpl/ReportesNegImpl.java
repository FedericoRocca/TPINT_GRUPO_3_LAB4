package negociolmpl;

import java.util.ArrayList;
import datos.ReportesDao;
import datoslmpl.ReportesDaoImpl;
import dominio.RepBalancesMayores;
import negocio.ReportesNeg;

public class ReportesNegImpl implements ReportesNeg
{
    public ReportesDao repBalancesDao = new ReportesDaoImpl();
    
    public ReportesNegImpl (ReportesDao repBalancesDao)
    {
        this.repBalancesDao = repBalancesDao;
    }
    
    public ReportesNegImpl()
    {
        
    }
    
    @Override
    public ArrayList<RepBalancesMayores> executeReport(float valueBalance)
    {
            return (ArrayList<RepBalancesMayores>)repBalancesDao.executeReport(valueBalance); 
    }
    
}
