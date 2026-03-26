package exceptions;

public class NoElementFoundException extends RuntimeException {
    public NoElementFoundException() {
        super();
    }

    public NoElementFoundException(String message) {
        super(message);
    }
}
