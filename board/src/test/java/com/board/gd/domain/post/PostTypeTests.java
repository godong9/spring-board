package com.board.gd.domain.post;

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
        assertEquals("FREE", PostType.FREE.name());
        assertEquals("PAID", PostType.PAID.name());
    }

    @Test
    public void success_getDescription() {
        // then
        assertEquals("무료", PostType.FREE.getDescription());
        assertEquals("유료", PostType.PAID.getDescription());
    }
}
