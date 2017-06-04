package com.board.gd.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    SUCCESS("성공"),
    FAIL("실패"),
    CANCEL("취소");

    private String description;
}
