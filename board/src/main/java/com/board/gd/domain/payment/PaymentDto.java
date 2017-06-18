package com.board.gd.domain.payment;

import lombok.Data;

/**
 * Created by godong9 on 2017. 6. 18..
 */

@Data
public class PaymentDto {
    private Long userId;
    private String name;
    private Double amount;
    private PaymentStatus status;
    private String customerUid;
}
