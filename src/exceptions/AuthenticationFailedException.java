package exceptions;

public class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException() {
        super();
    }

    public AuthenticationFailedException(String message) {
        super(message);
    }
}