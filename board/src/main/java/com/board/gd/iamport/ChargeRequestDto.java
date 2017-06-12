package com.board.gd.iamport;

import lombok.Data;

/**
 * Created by godong9 on 2017. 6. 12..
 */

@Data
public class ChargeRequestDto {
    private String customerUid;
    private String merchantUid;
    private Double amount;
    private String name;
}
