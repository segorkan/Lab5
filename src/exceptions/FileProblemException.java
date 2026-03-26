package exceptions;

public class FileProblemException extends RuntimeException {
    public FileProblemException() {
        super();
    }

    public FileProblemException(String message) {
        super(message);
    }
}
