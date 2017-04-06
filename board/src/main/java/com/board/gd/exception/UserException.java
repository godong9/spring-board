package com.board.gd.exception;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Created by gd.godong9 on 2017. 4. 6.
 */

@Getter
@AllArgsConstructor
public class UserException extends RuntimeException {
    private String message;
}
