package negocio;

import java.util.ArrayList;

import dominio.Account;

public interface IAccountNeg {
	public ArrayList<Account> GetAllbyDni(String dni);
	public ArrayList<Account> GetAll();
}
