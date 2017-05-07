package com.board.gd.domain.user.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by godong9 on 2017. 5. 7..
 */

@Data
public class EmailForm {
    @NotNull
    private String email;
}
