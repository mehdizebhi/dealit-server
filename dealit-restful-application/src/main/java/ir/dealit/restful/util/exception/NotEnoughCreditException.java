package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class NotEnoughCreditException extends DealitException{

    public NotEnoughCreditException(HttpStatus httpStatus) {
        super(ExceptionMessages.SMS_CREDIT_NOT_ENOUGH, httpStatus);
    }
}
