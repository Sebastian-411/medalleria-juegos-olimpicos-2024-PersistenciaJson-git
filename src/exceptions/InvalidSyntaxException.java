package exceptions;

public class InvalidSyntaxException extends Exception{
    public InvalidSyntaxException() {
        super("La sintaxis ingresada no es la permitida.");
    }
}
