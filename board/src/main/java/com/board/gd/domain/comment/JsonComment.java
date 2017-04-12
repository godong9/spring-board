package com.board.gd.domain.comment;

import com.board.gd.domain.user.JsonUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by gd.godong9 on 2017. 4. 12.
 */

@Data
public class JsonComment {
    private Long id;
    private String content;
    private JsonUser user;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date updatedAt;
}
