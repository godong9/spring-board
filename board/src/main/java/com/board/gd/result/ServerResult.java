package com.board.gd.result;

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
public class ServerResult<T> {
    private static final int SUCCESS_STATUS = HttpStatus.OK.value();
    private static final int ERROR_STATUS = HttpStatus.BAD_REQUEST.value();

    private int status;
    private T data;
    private String message;
    private JsonError error;

    public static <T> ServerResult success(T data) {
        return ServerResult.builder()
                .status(SUCCESS_STATUS)
                .data(data)
                .build();
    }

    public static ServerResult error(String message) {
        return ServerResult.builder()
                .status(ERROR_STATUS)
                .error(new JsonError(message))
                .build();
    }
}
