package com.tencent.matrix.trace.core;

public interface BeatLifecycle {
    boolean isAlive();

    void onStart();

    void onStop();
}
