package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class InvalidAmountException extends DealitException {

    public InvalidAmountException(HttpStatus httpStatus) {
        super(ExceptionMessages.INVALID_AMOUNT, httpStatus);
    }
}
