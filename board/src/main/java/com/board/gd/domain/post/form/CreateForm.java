package com.board.gd.domain.post.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by gd.godong9 on 2017. 4. 11.
 */

@Data
public class CreateForm {
    private Long userId;
    private Long stockId;

    @NotNull(message = "제목은 반드시 값이 있어야 합니다.")
    private String title;

    @NotNull(message = "내용은 반드시 값이 있어야 합니다.")
    private String content;

}
