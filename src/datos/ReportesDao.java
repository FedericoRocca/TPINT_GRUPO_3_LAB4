package datos;

import java.util.List;

import dominio.RepBalancesMayores;
import dominio.RepIngresosInteres;
import dominio.LisCantidadClientesPaises;

public interface ReportesDao {
    public List<RepBalancesMayores> executeReport(float valueBalance);
    public List<LisCantidadClientesPaises> executeReport();
    public RepIngresosInteres executeReport(RepIngresosInteres r);
}
