package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class InvalidConfirmCodeException extends DealitException {

    public InvalidConfirmCodeException(HttpStatus httpStatus) {
        super(ExceptionMessages.INVALID_CODE, httpStatus);
    }
}
