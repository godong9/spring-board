package com.board.gd.domain.payment;

import lombok.Data;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Data
public class PaymentResultDto {
    private String impUid;
    private String merchantUid;
    private String payMethod;
    private String pgProvider;
    private String pgTid;
    private String applyNum;
    private String cardName;
    private String name;
    private Double amount;
    private String status;
    private String failReason;
    private String receiptUrl;
    private String paidAt; // 타임스탬프라 String으로 받음

    private Integer code;
    private String message;
    private PaymentStatus paymentStatus;
}
