package negociolmpl;

import datos.DashboardClienteDao;
import datoslmpl.DashboardClienteDaolmpl;
import negocio.DashboardClienteNeg;

public class DashboardClienteNeglmpl implements DashboardClienteNeg 
{
	public DashboardClienteDao dashboardClienteDao = new DashboardClienteDaolmpl();
	
	public DashboardClienteNeglmpl() 
	{
		
	}
	
	
	@Override
	public float getTotalBalance(String dni) {
		return dashboardClienteDao.getTotalBalance(dni);
	}

	@Override
	public int getTotalTransferencias(String dni) {
		return dashboardClienteDao.getTotalTransfers(dni);
	}

	@Override
	public int getTotalAprobaciones(String dni) {
		return dashboardClienteDao.getAuthsApproved(dni);
	}
	
}
