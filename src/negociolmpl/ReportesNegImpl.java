package negociolmpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import datos.ReportesDao;
import datoslmpl.ReportesDaoImpl;
import dominio.LisCantidadClientesPaises;
import dominio.RepBalancesMayores;
import dominio.RepIngresosInteres;
import dominio.RepMovimientosDelDia;
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

    @Override
    public ArrayList<LisCantidadClientesPaises> executeReport()
    {
        return (ArrayList<LisCantidadClientesPaises>)repBalancesDao.executeReport(); 
    }

	@Override
	public RepIngresosInteres executeReport(RepIngresosInteres r) 
	{
		return repBalancesDao.executeReport(r);
	}

    @Override
    public ArrayList<RepMovimientosDelDia> executeReport(LocalDate date)
    {
        return repBalancesDao.executeReport(date);
    }
    
}
