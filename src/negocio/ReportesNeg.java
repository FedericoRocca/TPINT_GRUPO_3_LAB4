package negocio;

import java.util.List;

import dominio.RepBalancesMayores;

public interface ReportesNeg
{
    public List<RepBalancesMayores> executeReport(float valueBalance);
}
