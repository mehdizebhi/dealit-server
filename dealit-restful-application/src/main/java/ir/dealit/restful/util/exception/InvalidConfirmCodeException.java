package ir.dealit.restful.util.exception;

import org.springframework.http.HttpStatus;

public class InvalidConfirmCodeException extends DealitException {

    public InvalidConfirmCodeException(HttpStatus httpStatus) {
        super("message", httpStatus);
    }
}
