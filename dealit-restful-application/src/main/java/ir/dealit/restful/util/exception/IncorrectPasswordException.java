package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends DealitException {

    public IncorrectPasswordException(HttpStatus httpStatus) {
        super(ExceptionMessages.INCORRECT_PASSWORD, httpStatus);
    }
}
