package com.board.gd.domain.post.form;

import lombok.Data;

/**
 * Created by gd.godong9 on 2017. 4. 11.
 */

@Data
public class UpdateForm {
    private Long id;
    private Long userId;
    private Long boardId;
    private String title;
    private String content;
}
