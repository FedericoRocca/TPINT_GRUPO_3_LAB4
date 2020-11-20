package dominio;

public class LisCantidadClientesPaises
{
    private String Pais;
    private int Cantidad;
    
    public LisCantidadClientesPaises()
    {
        super();
    }
    public LisCantidadClientesPaises(String pais, int cantidad)
    {
        super();
        Pais = pais;
        Cantidad = cantidad;
    }
    public String getPais()
    {
        return Pais;
    }
    public void setPais(String pais)
    {
        Pais = pais;
    }
    public int getCantidad()
    {
        return Cantidad;
    }
    public void setCantidad(int cantidad)
    {
        Cantidad = cantidad;
    }
}
