package com.zaze.utils.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface DataLevel {
    public static final int BITMAP = 1001;
    public static final int DATA = 1000;
    public static final int HIGH_LEVEL_BITMAP = 10001;
    public static final int HIGH_LEVEL_DATA = 10000;
    public static final int LOW_LEVEL_BITMAP = 101;
    public static final int LOW_LEVEL_DATA = 100;
}
