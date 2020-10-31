package dominio;

public class Role
{
    String nombre;
    boolean status;
    
    public Role()
    {
        super();
    }
    public Role(String nombre, boolean status)
    {
        super();
        this.nombre = nombre;
        this.status = status;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Role [nombre=" + nombre + ", status=" + status + "]";
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        Role other = (Role) obj;
        if (nombre == null)
        {
            if (other.nombre != null)
                return false;
        }
        else if (!nombre.equals(other.nombre))
            return false;
        if (status != other.status)
            return false;
        return true;
    }
    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
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
