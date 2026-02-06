package com.tencent.matrix.trace.constants;

public class Constants {
    public static final int BUFFER_SIZE = 1000000;
    public static final int DEFAULT_ANR = 5000;
    public static final int DEFAULT_ANR_INVALID = 6000;
    public static final int DEFAULT_DROPPED_FROZEN = 42;
    public static final int DEFAULT_DROPPED_HIGH = 24;
    public static final int DEFAULT_DROPPED_MIDDLE = 9;
    public static final int DEFAULT_DROPPED_NORMAL = 3;
    public static final int DEFAULT_EVIL_METHOD_THRESHOLD_MS = 700;
    public static final int DEFAULT_FPS_TIME_SLICE_ALIVE_MS = 10000;
    public static final int DEFAULT_RELEASE_BUFFER_DELAY = 15000;
    public static final int DEFAULT_STARTUP_THRESHOLD_MS_COLD = 10000;
    public static final int DEFAULT_STARTUP_THRESHOLD_MS_WARM = 4000;
    public static final float FILTER_STACK_KEY_ALL_PERCENT = 0.3f;
    public static final float FILTER_STACK_KEY_PATENT_PERCENT = 0.8f;
    public static final int FILTER_STACK_MAX_COUNT = 60;
    public static final int LIMIT_WARM_THRESHOLD_MS = 5000;
    public static final int MAX_LIMIT_ANALYSE_STACK_KEY_NUM = 10;
    public static final int TARGET_EVIL_METHOD_STACK = 30;
    public static final int TIME_MILLIS_TO_NANO = 1000000;
    public static final int TIME_UPDATE_CYCLE_MS = 5;

    public enum Type {
        NORMAL,
        ANR,
        STARTUP
    }
}
