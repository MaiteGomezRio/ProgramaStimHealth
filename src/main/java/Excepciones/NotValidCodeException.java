package Excepciones;

public class NotValidCodeException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotValidCodeException() {
        super("Codigo de archivo no valido");
    }
}
