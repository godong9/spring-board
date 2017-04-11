package com.board.gd.domain.post.form;

import javax.validation.constraints.NotNull;

/**
 * Created by gd.godong9 on 2017. 4. 11.
 */
public class UpdateForm {
    @NotNull
    private Long id;

    private Long userId;

    private String title;

    private String content;
}
