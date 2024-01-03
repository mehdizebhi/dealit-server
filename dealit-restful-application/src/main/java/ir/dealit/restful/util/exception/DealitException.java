package ir.dealit.restful.util.exception;

import org.springframework.http.HttpStatus;

public class DealitException extends RuntimeException {
    HttpStatus httpStatus;

    public DealitException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
