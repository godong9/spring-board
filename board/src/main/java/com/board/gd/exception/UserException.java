package com.board.gd.exception;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by gd.godong9 on 2017. 4. 6.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserException extends RuntimeException {
    private String message = "유저 정보 처리 중 에러가 발생하였습니다.";
}
