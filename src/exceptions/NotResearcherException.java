package exceptions;

public class NotResearcherException extends Exception {
    public NotResearcherException() {
        super();
    }

    public NotResearcherException(String message) {
        super(message);
    }
}