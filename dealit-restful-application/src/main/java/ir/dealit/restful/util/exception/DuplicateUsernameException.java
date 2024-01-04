package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends DealitException {

    public DuplicateUsernameException(HttpStatus httpStatus) {
        super(ExceptionMessages.USERNAME_MUST_BE_UNIQUE, httpStatus);
    }
}
