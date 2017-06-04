package com.board.gd.iamport;

import lombok.Data;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Data
public class AccessTokenResponseDto {
    private String accessToken;
    private String now;
    private String expiredAt;
}
