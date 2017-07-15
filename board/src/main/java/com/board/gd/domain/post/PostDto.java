package com.board.gd.domain.post;

import lombok.Data;

/**
 * Created by gd.godong9 on 2017. 4. 4.
 */

@Data
public class PostDto {
    private Long id;
    private String title;
    private PostType type;
    private String content;
    private Long userId;
    private Long stockId;
}
