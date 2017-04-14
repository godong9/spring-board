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
    protected static final int SUCCESS_STATUS = HttpStatus.OK.value();
    protected static final int ERROR_STATUS = HttpStatus.BAD_REQUEST.value();

    protected int status;
    protected T data;
    protected String message;
    protected JsonError error;

    public static ServerResult success() {
        return success(null);
    }

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
