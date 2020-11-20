package negocio;

import java.util.List;

import dominio.RepBalancesMayores;

public interface RepBalancesMayoresNeg
{
    public List<RepBalancesMayores> executeReport(float valueBalance);
}
