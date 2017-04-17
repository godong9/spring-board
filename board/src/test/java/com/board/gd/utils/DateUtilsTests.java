package com.board.gd.utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by gd.godong9 on 2017. 4. 17.
 */

@SpringBootTest
public class DateUtilsTests {
    @Test
    public void success_return_false_when_expiredDate_is_after_now() {
        // given
        Long expiredTime = new Date().getTime() + (1000 * 60 * 60);
        Date expiredDate = new Date(expiredTime);

        // then
        assertEquals(DateUtils.isExpired(expiredDate), false);
    }

    @Test
    public void success_return_true_when_expiredDate_is_before_now() {
        // given
        Long expiredTime = new Date().getTime() - (1000 * 60 * 60);
        Date expiredDate = new Date(expiredTime);

        // then
        assertEquals(DateUtils.isExpired(expiredDate), true);
    }

}
