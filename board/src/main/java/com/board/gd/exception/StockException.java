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
public class StockException extends RuntimeException {
    private String message = "종목 처리 중 에러가 발생하였습니다.";
}
