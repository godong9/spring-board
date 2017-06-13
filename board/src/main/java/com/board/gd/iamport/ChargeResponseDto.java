package com.board.gd.iamport;

import com.board.gd.domain.payment.PaymentResultDto;
import lombok.Data;

/**
 * Created by godong9 on 2017. 6. 12..
 */

@Data
public class ChargeResponseDto {
    private Integer code;
    private String message;
    private PaymentResultDto response;
}
