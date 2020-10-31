package dominio;

public class Nacionality
{
    String descripcion;
    boolean status;
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + (status ? 1231 : 1237);
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
        Nacionality other = (Nacionality) obj;
        if (descripcion == null)
        {
            if (other.descripcion != null)
                return false;
        }
        else if (!descripcion.equals(other.descripcion))
            return false;
        if (status != other.status)
            return false;
        return true;
    }
    public Nacionality()
    {
        super();
    }
    public Nacionality(String descripcion, boolean status)
    {
        super();
        this.descripcion = descripcion;
        this.status = status;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Nacionalidad [descripcion=" + descripcion + ", status=" + status + "]";
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
     * @return the status
     */
    public boolean isStatus()
    {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(boolean status)
    {
        this.status = status;
    }
}
