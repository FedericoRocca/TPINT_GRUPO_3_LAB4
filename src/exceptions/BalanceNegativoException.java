package exceptions;

public class BalanceNegativoException extends RuntimeException {

	public BalanceNegativoException() {
		
	}

	@Override
	public String getMessage() {
		return "El saldo no puede ser negativo.";
	}
}
