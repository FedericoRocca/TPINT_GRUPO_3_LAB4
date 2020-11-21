package dominio;

public class DashboardAdmin
{
    private float totalBalance;
    private int cantidadMovimientos;
    private int movementType;
    private int movementCount;
    private int totalLoans;
    private int totalAccounts;
    
    public int getTotalAccounts()
    {
        return totalAccounts;
    }

    public void setTotalAccounts(int totalAccounts)
    {
        this.totalAccounts = totalAccounts;
    }
    
    public void setTotalLoans(int totalLoans)
    {
        this.totalLoans = totalLoans;
    }
    
    public int getTotalLoans()
    {
        return totalLoans;
    }
    
    public float getTotalBalance()
    {
        return totalBalance;
    }
    public void setTotalBalance(float totalBalance)
    {
        this.totalBalance = totalBalance;
    }
    public int getCantidadMovimientos()
    {
        return cantidadMovimientos;
    }
    public void setCantidadMovimientos(int cantidadMovimientos)
    {
        this.cantidadMovimientos = cantidadMovimientos;
    }
    public int getMovementType()
    {
        return movementType;
    }
    public void setMovementType(int movementType)
    {
        this.movementType = movementType;
    }
    public int getMovementCount()
    {
        return movementCount;
    }
    public void setMovementCount(int movementCount)
    {
        this.movementCount = movementCount;
    }
}
