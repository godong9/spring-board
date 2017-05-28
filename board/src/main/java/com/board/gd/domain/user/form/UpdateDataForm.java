package com.board.gd.domain.user.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by godong9 on 2017. 5. 28..
 */

@Data
public class UpdateDataForm {
    @NotNull
    private Long id;
    @NotNull
    private String uuid;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private Long companyId;
}
