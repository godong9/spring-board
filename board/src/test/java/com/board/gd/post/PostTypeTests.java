package com.board.gd.post;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

/**
 * Created by godong9 on 2017. 4. 8..
 */

@SpringBootTest
public class PostTypeTests {
    @Test
    public void success_getName() {
        // then
        assertEquals(PostType.FREE.name(), "FREE");
        assertEquals(PostType.PAID.name(), "PAID");
    }

    @Test
    public void success_getDescription() {
        // then
        assertEquals(PostType.FREE.getDescription(), "무료");
        assertEquals(PostType.PAID.getDescription(), "유료");
    }
}
