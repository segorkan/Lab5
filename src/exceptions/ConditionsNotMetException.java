package exceptions;

public class ConditionsNotMetException extends RuntimeException {
    public ConditionsNotMetException() {
        super();
    }

    public ConditionsNotMetException(String message) {
        super(message);
    }
}
