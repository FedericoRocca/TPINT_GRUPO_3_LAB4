package negociolmpl;

import java.util.ArrayList;
import java.util.List;

import datos.ProvinceDao;
import datoslmpl.ProvinceDaolmpl;
import dominio.Province;
import negocio.ProvinceNeg;

public class ProvinceNeglmpl implements ProvinceNeg {

	public ProvinceDao pdao = new ProvinceDaolmpl();

	@Override
	public ArrayList<Province> getAll() {
		return pdao.getAll();
	}
	

}
