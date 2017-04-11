package com.board.gd.domain.post;

import com.board.gd.domain.user.JsonUser;
import lombok.Data;

/**
 * Created by gd.godong9 on 2017. 4. 10.
 */

@Data
public class JsonPost {
    private Long id;
    private String title;
    private String content;
    private Long viewCount;
    private JsonUser user;
    private String createdAt;
    private String updatedAt;
}
