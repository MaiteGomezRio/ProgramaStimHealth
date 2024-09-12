package Excepciones;

public class NotValidCodeException extends Exception{
    public NotValidCodeException() {
        super("Codigo de archivo no valido");
    }
}
