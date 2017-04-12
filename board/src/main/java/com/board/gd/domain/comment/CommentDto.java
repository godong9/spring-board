package com.board.gd.domain.comment;

import lombok.Data;

/**
 * Created by gd.godong9 on 2017. 4. 12.
 */

@Data
public class CommentDto {
    private Long id;
    private String content;
    private Long userId;
    private Long postId;
}
