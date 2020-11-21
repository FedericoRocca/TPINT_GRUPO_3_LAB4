package datos;

import java.util.List;

import dominio.DashboardAdmin;

public interface DashboardAdminDao {
    public float getTotalBalance();
    public int getTotalMovements();
    public int getTotalLoans();
    public int getTotalAccounts();
}
