package negocio;

import java.util.List;

import dominio.RepBalancesMayores;
import dominio.LisCantidadClientesPaises;;

public interface ReportesNeg
{
    public List<RepBalancesMayores> executeReport(float valueBalance);
    public List<LisCantidadClientesPaises> executeReport();
}
