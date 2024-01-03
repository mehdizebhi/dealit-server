package ir.dealit.restful.config.app;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.util.exception.DealitException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(DealitException.class)
    protected ResponseEntity<ResponseModel<Void>> defaultErrorHandler(DealitException ex) {
        log.error("[Global Default Exception Handler] - {}", ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body((ResponseModel<Void>) ResponseModel.builder().error(ex).build());
    }
}
