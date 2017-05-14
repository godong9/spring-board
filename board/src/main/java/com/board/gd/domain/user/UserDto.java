package com.board.gd.domain.user;

import lombok.Data;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String profileImg;
    private Long companyId;
}
