package com.board.gd.domain.comment.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by gd.godong9 on 2017. 4. 14.
 */

@Data
public class CreateForm {
    private Long userId;

    @NotNull
    private Long postId;

    @NotNull
    private String content;

}
