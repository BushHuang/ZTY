package com.zaze.utils.task;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface MultiNum {
    public static final int DEFAULT = -1;
    public static final int FIVE = 5;
    public static final int FOUR = 4;
    public static final int KEEP = -2;
    public static final int MAX = -3;
    public static final int MIN = 1;
    public static final int THREE = 3;
    public static final int TWO = 2;
}
