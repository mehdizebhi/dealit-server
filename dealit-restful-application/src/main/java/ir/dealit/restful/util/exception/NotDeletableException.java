package ir.dealit.restful.util.exception;

import org.springframework.http.HttpStatus;

public class NotDeletableException extends DealitException {

    public NotDeletableException(String message) {
        super(message, HttpStatus.NOT_ACCEPTABLE);
    }
}
