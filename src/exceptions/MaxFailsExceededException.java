package exceptions;

public class MaxFailsExceededException extends Exception {
    public MaxFailsExceededException() {
        super();
    }

    public MaxFailsExceededException(String message) {
        super(message);
    }
}