package com.board.gd.iamport;

import lombok.Data;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Data
public class AccessTokenDto {
    private Integer code;
    private String message;
    private AccessTokenResponseDto response;
}
