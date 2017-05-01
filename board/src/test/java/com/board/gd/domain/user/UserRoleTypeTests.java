package com.board.gd.domain.user;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

/**
 * Created by godong9 on 2017. 4. 8..
 */

@SpringBootTest
public class UserRoleTypeTests {
    @Test
    public void success_getName() {
        // then
        assertEquals("USER", UserRoleType.USER.name());
        assertEquals("ADMIN", UserRoleType.ADMIN.name());
    }

    @Test
    public void success_getDescription() {
        // then
        assertEquals("기본유저", UserRoleType.USER.getDescription());
        assertEquals("어드민유저", UserRoleType.ADMIN.getDescription());
    }
}
