package com.board.gd.iamport;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Data
public class SubscribeRequestDto {
    @NotNull(message = "카드번호는 반드시 값이 있어야 합니다.")
    private String card_number;

    @NotNull(message = "유효기간은 반드시 값이 있어야 합니다.")
    private String expiry;

    @NotNull(message = "생년월일은 반드시 값이 있어야 합니다.")
    private String birth;

    @NotNull(message = "비밀번호 앞 두자리는 반드시 값이 있어야 합니다.")
    private String pwd_2digit;

    private String customer_uid;
    private String customer_email;

    private String customer_name;
    private String customer_tel;
    private String customer_addr;
    private String customer_postcode;
}
