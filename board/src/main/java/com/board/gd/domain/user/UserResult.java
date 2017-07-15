package com.board.gd.domain.user;

import lombok.Data;

/**
 * Created by gd.godong9 on 2017. 4. 10.
 */

@Data
public class UserResult {
    private Long id;
    private String name;
    private String email;
    private String companyName;
}
