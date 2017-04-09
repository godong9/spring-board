package com.board.gd.domain.user.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by gd.godong9 on 2017. 4. 6.
 */

@Data
public class LoginForm {
    @NotNull
    private String email;

    @NotNull
    private String password;
}
