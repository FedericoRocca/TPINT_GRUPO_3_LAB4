package dominio;

public class RepBalancesMayores
{
    private String firstName;
    private String lastName;
    private String dni;
    private int accountNumber;
    private String cbu;
    private float balance;
    
    public RepBalancesMayores()
    {
        super();
    }
    public RepBalancesMayores(String firstName, String lastName, String dni, int accountNumber, String cbu,
            float balance)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.accountNumber = accountNumber;
        this.cbu = cbu;
        this.balance = balance;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "RepBalancesMayores [firstName=" + firstName + ", lastName=" + lastName + ", dni=" + dni
                + ", accountNumber=" + accountNumber + ", cbu=" + cbu + ", balance=" + balance + "]";
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + accountNumber;
        result = prime * result + Float.floatToIntBits(balance);
        result = prime * result + ((cbu == null) ? 0 : cbu.hashCode());
        result = prime * result + ((dni == null) ? 0 : dni.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RepBalancesMayores other = (RepBalancesMayores) obj;
        if (accountNumber != other.accountNumber)
            return false;
        if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
            return false;
        if (cbu == null)
        {
            if (other.cbu != null)
                return false;
        }
        else if (!cbu.equals(other.cbu))
            return false;
        if (dni == null)
        {
            if (other.dni != null)
                return false;
        }
        else if (!dni.equals(other.dni))
            return false;
        if (firstName == null)
        {
            if (other.firstName != null)
                return false;
        }
        else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null)
        {
            if (other.lastName != null)
                return false;
        }
        else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }
    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    /**
     * @return the dni
     */
    public String getDni()
    {
        return dni;
    }
    /**
     * @param dni the dni to set
     */
    public void setDni(String dni)
    {
        this.dni = dni;
    }
    /**
     * @return the accountNumber
     */
    public int getAccountNumber()
    {
        return accountNumber;
    }
    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }
    /**
     * @return the cbu
     */
    public String getCbu()
    {
        return cbu;
    }
    /**
     * @param cbu the cbu to set
     */
    public void setCbu(String cbu)
    {
        this.cbu = cbu;
    }
    /**
     * @return the balance
     */
    public float getBalance()
    {
        return balance;
    }
    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance)
    {
        this.balance = balance;
    }
}
