package com.zaze.utils;

public class DescriptionUtil {
    public static String toByteUnit(long j) {
        long j2 = j >> 10;
        long j3 = j >> 20;
        return (j >> 30) > 0 ? ZStringUtil.format("%.3fGB", Float.valueOf((j3 * 1.0f) / 1024.0f)) : j3 > 0 ? ZStringUtil.format("%.3fMB", Float.valueOf((j2 * 1.0f) / 1024.0f)) : j2 > 0 ? ZStringUtil.format("%dKB", Long.valueOf(j2)) : ZStringUtil.format("%dB", Long.valueOf(j));
    }
}
