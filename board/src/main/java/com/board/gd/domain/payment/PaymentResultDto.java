package com.board.gd.domain.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Data
public class PaymentResultDto {
    private String impUid;
    private String merchantUid;
    private String payMethod;
    private String pg_provider;
    private String pg_tid;
    private String applyNum;
    private String cardName;
    private String name;
    private Double amount;
    private String status;
    private String failReason;
    private String receiptUrl;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date paidAt;

    private Integer code;
    private String message;
}
