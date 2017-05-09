package com.board.gd.domain.post.form;

import lombok.Data;

/**
 * Created by godong9 on 2017. 5. 1..
 */

@Data
public class PostLikeForm {
    private Long userId;
    private Long postId;
    private Boolean unlike;
}
