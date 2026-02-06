package com.zaze.utils.log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface ZLogLevel {
    public static final int DEBUG = 4;
    public static final int ERROR = 1;
    public static final int INFO = 3;
    public static final int NULL = 0;
    public static final int VERBOSE = 5;
    public static final int WARN = 2;
}
