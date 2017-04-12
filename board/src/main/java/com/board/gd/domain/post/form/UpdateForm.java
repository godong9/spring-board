package com.board.gd.domain.post.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by gd.godong9 on 2017. 4. 11.
 */

@Data
public class UpdateForm {
    private Long id;

    private Long userId;

    private String title;

    private String content;
}
