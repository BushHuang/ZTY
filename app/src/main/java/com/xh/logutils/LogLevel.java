package com.xh.logutils;

import java.util.HashMap;

public enum LogLevel {
    LOG_LEVEL_NONE(-1),
    LOG_LEVEL_ERROR(0),
    LOG_LEVEL_WARNING(1),
    LOG_LEVEL_INFO(2),
    LOG_LEVEL_DEBUG(3),
    LOG_LEVEL_VERBOSE(4);

    private static HashMap<Integer, LogLevel> valuesMap = new HashMap<>();
    private final int value;

    static {
        for (LogLevel logLevel : values()) {
            valuesMap.put(Integer.valueOf(logLevel.getValue()), logLevel);
        }
    }

    LogLevel(int i) {
        this.value = i;
    }

    public static LogLevel fromValue(int i) {
        return valuesMap.get(Integer.valueOf(i));
    }

    public int getValue() {
        return this.value;
    }
}
