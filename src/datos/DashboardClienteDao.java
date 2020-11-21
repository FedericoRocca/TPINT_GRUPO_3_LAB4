package datos;

public interface DashboardClienteDao {
	public float getTotalBalance(String dni);
    public int getTotalTransfers(String dni);
    public int getAuthsApproved(String dni);
}
