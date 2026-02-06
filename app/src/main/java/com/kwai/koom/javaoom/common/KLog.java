package com.kwai.koom.javaoom.common;

import android.util.Log;

public class KLog {
    private static KLogger logger;

    public static class DefaultLogger implements KLogger {
        @Override
        public void d(String str, String str2) {
            Log.d(str, str2);
        }

        @Override
        public void e(String str, String str2) {
            Log.e(str, str2);
        }

        @Override
        public void i(String str, String str2) {
            Log.i(str, str2);
        }
    }

    public interface KLogger {
        void d(String str, String str2);

        void e(String str, String str2);

        void i(String str, String str2);
    }

    public static void d(String str, String str2) {
        if (logger == null) {
            init(new DefaultLogger());
        }
        logger.d(str, str2);
    }

    public static void e(String str, String str2) {
        if (logger == null) {
            init(new DefaultLogger());
        }
        logger.e(str, str2);
    }

    public static void i(String str, String str2) {
        if (logger == null) {
            init(new DefaultLogger());
        }
        logger.i(str, str2);
    }

    public static void init(KLogger kLogger) {
        logger = kLogger;
    }
}
