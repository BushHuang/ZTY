package com.tencent.matrix.trace.listeners;

public abstract class LooperObserver {
    private boolean isDispatchBegin = false;

    public void dispatchBegin(long j, long j2, long j3) {
        this.isDispatchBegin = true;
    }

    public void dispatchEnd(long j, long j2, long j3, long j4, long j5, boolean z) {
        this.isDispatchBegin = false;
    }

    public void doFrame(String str, long j, long j2, long j3, long j4, long j5, long j6) {
    }

    public boolean isDispatchBegin() {
        return this.isDispatchBegin;
    }
}
