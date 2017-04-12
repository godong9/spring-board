package com.board.gd.domain.post.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by gd.godong9 on 2017. 4. 12.
 */

@Data
public class DeleteForm {
    private Long id;

    private Long userId;
}
