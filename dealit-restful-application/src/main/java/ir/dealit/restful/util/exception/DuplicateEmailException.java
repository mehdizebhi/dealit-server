package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends DealitException {

    public DuplicateEmailException(HttpStatus httpStatus) {
        super(ExceptionMessages.EMAIL_MUST_BE_UNIQUE, httpStatus);
    }
}
