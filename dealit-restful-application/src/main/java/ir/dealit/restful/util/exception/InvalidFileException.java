package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class InvalidFileException extends DealitException {

    public InvalidFileException(HttpStatus httpStatus) {
        super(ExceptionMessages.FILE_NOT_FOUND, httpStatus);
    }
}
