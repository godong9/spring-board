package com.board.gd.domain.user.form;

import lombok.Data;

/**
 * Created by gd.godong9 on 2017. 4. 11.
 */

@Data
public class UpdateForm {
    private Long id;
    private String name;
    private String password;
}
