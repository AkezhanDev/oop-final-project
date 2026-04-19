package exceptions;

public class CreditLimitExceededException extends Exception {
    public CreditLimitExceededException() {
        super();
    }

    public CreditLimitExceededException(String message) {
        super(message);
    }
}