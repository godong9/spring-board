package com.board.gd.domain.post.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by gd.godong9 on 2017. 4. 11.
 */

@Data
public class PostForm {
    private Long id;

    private Long userId;

    @NotNull
    private String title;

    @NotNull
    private String content;

}
