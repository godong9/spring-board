package com.board.gd.domain.post;

import lombok.Data;

/**
 * Created by godong9 on 2017. 5. 14..
 */

@Data
public class PostReportDto {
    private Long id;
    private Long userId;
    private Long postId;
    private String content;
}
