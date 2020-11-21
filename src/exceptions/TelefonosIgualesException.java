package exceptions;

public class TelefonosIgualesException extends RuntimeException {
	
	public TelefonosIgualesException() {
		
	}

	@Override
	public String getMessage() {
		return "Uno o más teléfonos son iguales.";
	}

}
