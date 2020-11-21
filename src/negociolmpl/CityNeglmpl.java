package negociolmpl;

import java.util.ArrayList;
import java.util.List;

import datos.CityDao;
import datoslmpl.CityDaolmpl;
import dominio.City;
import negocio.CityNeg;

public class CityNeglmpl implements CityNeg {

	public CityDao cdao = new CityDaolmpl();

	@Override
	public ArrayList<City> getAll() {
		return cdao.getAll();
	}
	

}
