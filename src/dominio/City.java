package dominio;

public class City
{
    String descripcion;
    int postalCode;
    
    public City()
    {
        super();
    }
    
    public City(String descripcion, int postalCode)
    {
        super();
        this.descripcion = descripcion;
        this.postalCode = postalCode;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "City [descripcion=" + descripcion + ", postalCode=" + postalCode + "]";
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + postalCode;
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
        City other = (City) obj;
        if (descripcion == null)
        {
            if (other.descripcion != null)
                return false;
        }
        else if (!descripcion.equals(other.descripcion))
            return false;
        if (postalCode != other.postalCode)
            return false;
        return true;
    }
    /**
     * @return the descripcion
     */
    public String getDescripcion()
    {
        return descripcion;
    }
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
    /**
     * @return the postalCode
     */
    public int getPostalCode()
    {
        return postalCode;
    }
    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(int postalCode)
    {
        this.postalCode = postalCode;
    }
}
