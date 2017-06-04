package com.board.gd.iamport;

import com.board.gd.domain.payment.PaymentInfoDto;
import lombok.Data;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Data
public class SubscribeResponseDto {
    private Integer code;
    private String message;
    private PaymentInfoDto response;
}
