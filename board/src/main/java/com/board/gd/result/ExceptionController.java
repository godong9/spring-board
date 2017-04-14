package com.board.gd.result;

import com.board.gd.exception.CommentException;
import com.board.gd.exception.PostException;
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
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerResult> handleException(Exception exception) throws Exception {
        log.error("[Exception] {}\n{}", exception.getMessage(), Throwables.getStackTraceAsString(exception));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(ServerResult.error(null));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ServerResult> handleUserException(UserException exception) throws Exception {
        String message = exception.getMessage();
        log.warn("[UserException] {}\n{}", message, Throwables.getStackTraceAsString(exception));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(ServerResult.error(message));
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ServerResult> handlePostException(PostException exception) throws Exception {
        String message = exception.getMessage();
        log.warn("[PostException] {}\n{}", message, Throwables.getStackTraceAsString(exception));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(ServerResult.error(message));
    }

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ServerResult> handleCommentException(CommentException exception) throws Exception {
        String message = exception.getMessage();
        log.warn("[CommentException] {}\n{}", message, Throwables.getStackTraceAsString(exception));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(ServerResult.error(message));
    }
}
