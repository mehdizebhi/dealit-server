package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class UploadServiceException extends DealitException {

    public UploadServiceException(HttpStatus httpStatus) {
        super(ExceptionMessages.NOT_AVAILABLE_UPLOAD_SERVICE, httpStatus);
    }
}
