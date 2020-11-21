package negociolmpl;

import java.util.ArrayList;

import datos.RoleDao;
import datoslmpl.RoleDaolmpl;
import dominio.Role;
import negocio.RoleNeg;

public class RoleNeglmpl implements RoleNeg {

	public RoleDao roleDao = new RoleDaolmpl();
	
	public RoleNeglmpl (RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Override
	public ArrayList<Role> GetAll(String dni) {
		return (ArrayList<Role>) roleDao.getAll(dni);
	}

	@Override
	public boolean insert(Role role, String dni) {
		return roleDao.insert(role, dni);
	}

	@Override
	public boolean edit(Role role, int dni) {
		return roleDao.update(role, dni);
	}

	@Override
	public boolean delete(int numberPhone) {
		return roleDao.delete(numberPhone);
	}

}
