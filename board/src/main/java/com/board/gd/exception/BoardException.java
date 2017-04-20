package com.board.gd.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by gd.godong9 on 2017. 4. 20.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardException extends RuntimeException {
    private String message = "게시판 처리 중 에러가 발생하였습니다.";
}
