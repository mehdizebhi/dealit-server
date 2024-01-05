package ir.dealit.restful.util.exception;

import ir.dealit.restful.util.constant.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class JobNotFoundException extends DealitException {

    public JobNotFoundException(HttpStatus httpStatus) {
        super(ExceptionMessages.JOB_AD_NOT_FOUND, httpStatus);
    }
}
