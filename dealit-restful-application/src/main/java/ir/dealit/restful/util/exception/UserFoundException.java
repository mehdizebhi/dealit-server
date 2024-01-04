package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class UserFoundException extends DealitException {

    public UserFoundException(HttpStatus httpStatus) {
        super(ExceptionMessages.USER_FOUND, httpStatus);
    }
}
