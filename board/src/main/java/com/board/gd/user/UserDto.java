package com.board.gd.user;

import lombok.Data;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String fbId;
    private String password;
    private String profileImg;
}
