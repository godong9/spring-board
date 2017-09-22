package com.board.gd.domain.payment;

import lombok.Data;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Data
public class PaymentInfoDto {
    private String customerUid;
    private String cardName;
    private Long userId;
}
