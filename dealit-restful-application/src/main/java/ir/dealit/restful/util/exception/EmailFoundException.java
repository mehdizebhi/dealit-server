package ir.dealit.restful.util.exception;

public class EmailFoundException extends RuntimeException {
    public EmailFoundException() {
        super();
    }

    public EmailFoundException(String message) {
        super(message);
    }

    public EmailFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailFoundException(Throwable cause) {
        super(cause);
    }

    protected EmailFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
