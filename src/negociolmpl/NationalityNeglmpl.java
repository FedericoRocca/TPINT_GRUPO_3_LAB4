package negociolmpl;

import java.util.List;

import datos.NationalityDao;
import datoslmpl.NationalityDaolmpl;
import dominio.Nationality;
import negocio.NationalityNeg;

public class NationalityNeglmpl implements NationalityNeg {

	public NationalityDao ndao = new NationalityDaolmpl();

	@Override
	public List<Nationality> getAll() {
		return ndao.getAll();
	}
	

}
