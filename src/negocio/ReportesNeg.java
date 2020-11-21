package negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dominio.RepBalancesMayores;
import dominio.RepIngresosInteres;
import dominio.RepMovimientosDelDia;
import dominio.LisCantidadClientesPaises;;

public interface ReportesNeg
{
    public List<RepBalancesMayores> executeReport(float valueBalance);
    public List<LisCantidadClientesPaises> executeReport();
    public RepIngresosInteres executeReport(RepIngresosInteres r);
    public ArrayList<RepMovimientosDelDia> executeReport(LocalDate date);
}
