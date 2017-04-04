package com.board.gd.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object obj) {
        return toJson(obj, PropertyNamingStrategy.SNAKE_CASE);
    }

    public static String toJson(Object obj, PropertyNamingStrategy keyFormat) {
        objectMapper.setPropertyNamingStrategy(keyFormat);
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
            return null;
        }
    }
}
