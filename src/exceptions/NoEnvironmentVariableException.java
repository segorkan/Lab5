package exceptions;

public class NoEnvironmentVariableException extends RuntimeException {
    public NoEnvironmentVariableException() {
        super();
    }

    public NoEnvironmentVariableException(String message) {
        super(message);
    }
}
