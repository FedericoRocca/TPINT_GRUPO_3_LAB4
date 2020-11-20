package datos;

import java.util.List;

import dominio.RepBalancesMayores;
import dominio.LisCantidadClientesPaises;

public interface ReportesDao {
    public List<RepBalancesMayores> executeReport(float valueBalance);
    public List<LisCantidadClientesPaises> executeReport();
}
