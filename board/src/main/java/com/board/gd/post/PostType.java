package com.board.gd.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by gd.godong9 on 2017. 4. 4.
 */

@Getter
@AllArgsConstructor
public enum PostType {
    FREE("무료"),
    PAID("유료");

    private String description;
}
