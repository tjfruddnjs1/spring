package com.spring.socket.util;

import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtil {
    public static final String PATTERN_YMD = "yyyy-MM-dd";
    public static final String PATTERN_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_YMDAHM = "yyyy-MM-dd a hh시 mm분";
    public static final String PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static final DateTimeFormatter FORMAT_YMD = DateTimeFormatter.ofPattern(PATTERN_YMD);
    public static final DateTimeFormatter FORMAT_YMDHM = DateTimeFormatter.ofPattern(PATTERN_YMDHM);
    public static final DateTimeFormatter FORMAT_YMDAHM = DateTimeFormatter.ofPattern(PATTERN_YMDAHM);
    public static final DateTimeFormatter FORMAT_YMDHMS = DateTimeFormatter.ofPattern(PATTERN_YMDAHM);
}
