package ir.dealit.restful.util.exception;

import org.springframework.http.HttpStatus;

public class NotFoundResourceException extends DealitException {

    public NotFoundResourceException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
