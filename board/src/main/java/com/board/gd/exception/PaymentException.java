package com.board.gd.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentException extends RuntimeException {
    private String message = "결제 처리 중 에러가 발생하였습니다.";
}