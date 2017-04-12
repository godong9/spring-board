package com.board.gd.domain.post;

import com.board.gd.domain.user.JsonUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date updatedAt;
}
