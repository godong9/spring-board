package com.board.gd.domain.post.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by godong9 on 2017. 5. 14..
 */

@Data
public class PostReportForm {
    private Long userId;
    private Long postId;

    @NotNull
    private String content;
}
