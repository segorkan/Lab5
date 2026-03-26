package exceptions;

public class WrongFormatException extends RuntimeException {
    public WrongFormatException() {
        super();
    }

    public WrongFormatException(String message) {
        super(message);
    }
}
