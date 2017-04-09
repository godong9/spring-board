package com.board.gd.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by gd.godong9 on 2017. 4. 7.
 */

@Getter
@AllArgsConstructor
public enum UserRoleType {
    USER("기본유저"),
    ADMIN("어드민유저");

    private String description;
}
