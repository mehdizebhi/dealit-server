package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class IllegalFileFormatException extends DealitException {

    public IllegalFileFormatException(HttpStatus httpStatus) {
        super(ExceptionMessages.ILLEGAL_FILE_FORMAT, httpStatus);
    }
}
