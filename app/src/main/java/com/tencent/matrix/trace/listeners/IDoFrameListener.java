package com.tencent.matrix.trace.listeners;

import java.util.concurrent.Executor;

public class IDoFrameListener {
    private Executor executor;
    public long time;

    public IDoFrameListener() {
    }

    public IDoFrameListener(Executor executor) {
        this.executor = executor;
    }

    public void doFrameAsync(String str, long j, long j2, int i, boolean z) {
    }

    public void doFrameSync(String str, long j, long j2, int i, boolean z) {
    }

    public Executor getExecutor() {
        return this.executor;
    }
}
