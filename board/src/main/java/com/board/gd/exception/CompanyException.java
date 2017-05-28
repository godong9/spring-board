package com.board.gd.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by godong9 on 2017. 5. 28..
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyException extends RuntimeException {
    private String message = "회사 처리 중 에러가 발생하였습니다.";
}