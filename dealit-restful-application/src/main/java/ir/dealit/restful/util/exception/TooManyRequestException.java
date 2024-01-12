package ir.dealit.restful.util.exception;

import org.springframework.http.HttpStatus;

public class TooManyRequestException  extends DealitException {

    public TooManyRequestException(String message) {
        super(message, HttpStatus.TOO_MANY_REQUESTS);
    }
}
