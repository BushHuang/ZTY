package com.tencent.matrix.trace.listeners;

public interface IDefaultConfig {
    boolean isAnrTraceEnable();

    boolean isDebug();

    boolean isDevEnv();

    boolean isEvilMethodTraceEnable();

    boolean isFPSEnable();
}
