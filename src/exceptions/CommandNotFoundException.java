package exceptions;

public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException() {
        super();
    }

    public CommandNotFoundException(String message) {
        super(message);
    }
}
