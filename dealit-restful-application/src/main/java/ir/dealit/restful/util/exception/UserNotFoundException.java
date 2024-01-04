package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends DealitException {

    public UserNotFoundException(HttpStatus httpStatus) {
        super(ExceptionMessages.USER_NOT_FOUND, httpStatus);
    }
}
