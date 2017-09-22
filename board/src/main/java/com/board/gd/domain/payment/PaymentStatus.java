package com.board.gd.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    INIT("생성"),
    REQUESTED("요청"),
    SUCCESS("성공"),
    FAIL("실패");

    private String description;
}
