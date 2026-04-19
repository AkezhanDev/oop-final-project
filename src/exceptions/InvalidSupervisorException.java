package exceptions;

public class InvalidSupervisorException extends Exception {
    public InvalidSupervisorException() {
        super();
    }

    public InvalidSupervisorException(String message) {
        super(message);
    }
}