package ir.dealit.restful.util.exception;

public class UserFoundExeption extends RuntimeException{
    public UserFoundExeption() {
    }

    public UserFoundExeption(String message) {
        super(message);
    }

    public UserFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public UserFoundExeption(Throwable cause) {
        super(cause);
    }

    public UserFoundExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
