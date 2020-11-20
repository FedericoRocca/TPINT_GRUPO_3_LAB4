package datos;

import java.util.List;

import dominio.RepBalancesMayores;;

public interface RepBalancesMayoresDao {
    
    public List<RepBalancesMayores> executeReport(float valueBalance);
}
