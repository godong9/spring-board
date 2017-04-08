package com.board.gd.error;

import com.board.gd.exception.UserException;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by gd.godong9 on 2017. 4. 6.
 */

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResult> handlePaymentException(UserException exception) throws Exception {
        String message = exception.getMessage();
        log.warn("[UserException] {}\n{}", message, Throwables.getStackTraceAsString(exception));
        ErrorResult errorResult = new ErrorResult(message);
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(errorResult);
    }
}
