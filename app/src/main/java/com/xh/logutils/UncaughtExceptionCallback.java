package com.xh.logutils;

public interface UncaughtExceptionCallback {
    void onCallback(Thread thread, Throwable th);
}
