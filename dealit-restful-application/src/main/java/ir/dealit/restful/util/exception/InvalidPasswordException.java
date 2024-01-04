package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends DealitException {

    public InvalidPasswordException(HttpStatus httpStatus) {
        super(ExceptionMessages.INVALID_PASSWORD, httpStatus);
    }
}
