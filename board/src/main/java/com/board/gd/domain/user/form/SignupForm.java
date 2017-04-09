package com.board.gd.domain.user.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Data
public class SignupForm {
    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
