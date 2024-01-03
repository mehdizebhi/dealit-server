package ir.dealit.restful.util.exception;

import org.springframework.http.HttpStatus;

public class CustomDealitException extends DealitException{
    public CustomDealitException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
