package negociolmpl;

import java.util.ArrayList;
import java.util.List;

import datos.AccountDao;
import datos.RepBalancesMayoresDao;
import datoslmpl.RepBalancesMayoresDaoImpl;
import dominio.Account;
import dominio.RepBalancesMayores;
import negocio.RepBalancesMayoresNeg;

public class RepBalancesMayoresNegImpl implements RepBalancesMayoresNeg
{
    public RepBalancesMayoresDao repBalancesDao = new RepBalancesMayoresDaoImpl();
    
    public RepBalancesMayoresNegImpl (RepBalancesMayoresDao repBalancesDao)
    {
        this.repBalancesDao = repBalancesDao;
    }
    
    public RepBalancesMayoresNegImpl()
    {
        
    }
    
    @Override
    public ArrayList<RepBalancesMayores> executeReport(float valueBalance)
    {
            return (ArrayList<RepBalancesMayores>)repBalancesDao.executeReport(valueBalance); 
    }
    
}
