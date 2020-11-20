package datos;

import java.util.List;

import dominio.RepBalancesMayores;;

public interface ReportesDao {
    
    public List<RepBalancesMayores> executeReport(float valueBalance);
}
