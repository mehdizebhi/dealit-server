package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends DealitException {

    public InvalidCredentialsException(HttpStatus httpStatus) {
        super(ExceptionMessages.BAD_CREDENTIAL, httpStatus);
    }
}
