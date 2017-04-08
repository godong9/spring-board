package com.board.gd.user;

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
        assertEquals(UserRoleType.USER.name(), "USER");
        assertEquals(UserRoleType.ADMIN.name(), "ADMIN");
    }

    @Test
    public void success_getDescription() {
        // then
        assertEquals(UserRoleType.USER.getDescription(), "기본유저");
        assertEquals(UserRoleType.ADMIN.getDescription(), "어드민유저");
    }
}
