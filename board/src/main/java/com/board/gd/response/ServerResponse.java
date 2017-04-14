package com.board.gd.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Created by gd.godong9 on 2017. 4. 13.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerResponse<T> {
    private static final int SUCCESS_STATUS = HttpStatus.OK.value();
    private static final int ERROR_STATUS = HttpStatus.BAD_REQUEST.value();

    private int status;
    private T data;
    private String message;
    private ErrorResult error;

    public static ServerResponse success() {
        return success(null);
    }

    public static <T> ServerResponse success(T data) {
        return ServerResponse.builder()
                .status(SUCCESS_STATUS)
                .data(data)
                .build();
    }

    public static ServerResponse error() {
        return error(null);
    }

    public static ServerResponse error(String message) {
        return ServerResponse.builder()
                .status(ERROR_STATUS)
                .error(new ErrorResult(message))
                .build();
    }
}
