package com.board.gd.exception;

import com.board.gd.response.ServerResponse;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

/**
 * @apiDefine BadRequestError
 *
 * @apiError BadRequestError Bad request error
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "error": {
 *          "message": "Error Message"
 *       }
 *     }
 */

/**
 * Created by gd.godong9 on 2017. 4. 6.
 */

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerResponse> handleException(Exception exception) throws Exception {
        String message = exception.getMessage();
        log.error("[Exception] {}\n{}", exception.getMessage(), Throwables.getStackTraceAsString(exception));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(ServerResponse.error(message));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ServerResponse> handleUserException(UserException exception) throws Exception {
        String message = exception.getMessage();
        log.warn("[UserException] {}\n{}", message, Throwables.getStackTraceAsString(exception));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(ServerResponse.error(message));
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ServerResponse> handlePostException(PostException exception) throws Exception {
        String message = exception.getMessage();
        log.warn("[PostException] {}\n{}", message, Throwables.getStackTraceAsString(exception));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(ServerResponse.error(message));
    }

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ServerResponse> handleCommentException(CommentException exception) throws Exception {
        String message = exception.getMessage();
        log.warn("[CommentException] {}\n{}", message, Throwables.getStackTraceAsString(exception));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(ServerResponse.error(message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServerResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) throws Exception {
        String message = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        log.warn("[MethodArgumentNotValidException] : {}\n{}", message, Throwables.getStackTraceAsString(exception));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(ServerResponse.error(message));
    }
}
