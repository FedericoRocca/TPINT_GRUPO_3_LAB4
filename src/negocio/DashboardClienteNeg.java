package negocio;

public interface DashboardClienteNeg 
{
	public float getTotalBalance(String dni);
	public int getTotalTransferencias(String dni);
	public int getTotalAprobaciones(String dni);
}
