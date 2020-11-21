package dominio;

import java.time.LocalDate;

public class RepMovimientosDelDia
{
    private int account;
    private LocalDate movementDate;
    private String detail;
    private float amount;
    private String movementType;
    
    public int getAccount()
    {
        return account;
    }
    public void setAccount(int account)
    {
        this.account = account;
    }
    public LocalDate getMovementDate()
    {
        return movementDate;
    }
    public void setMovementDate(LocalDate movementDate)
    {
        this.movementDate = movementDate;
    }
    public String getDetail()
    {
        return detail;
    }
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    public float getAmount()
    {
        return amount;
    }
    public void setAmount(float amount)
    {
        this.amount = amount;
    }
    public String getMovementType()
    {
        return movementType;
    }
    public void setMovementType(String movementType)
    {
        this.movementType = movementType;
    }
    
}
