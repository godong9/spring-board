package com.board.gd.utils;

import java.util.Date;

/**
 * Created by gd.godong9 on 2017. 4. 17.
 */
public class DateUtils {
    public static boolean isExpired(Date expiredDate) {
        return new Date().after(expiredDate);
    }
}
