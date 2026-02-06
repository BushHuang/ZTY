package com.tencent.matrix.trace.tracer;

import com.tencent.matrix.AppActiveMatrixDelegate;
import com.tencent.matrix.trace.listeners.LooperObserver;
import com.tencent.matrix.util.MatrixLog;

public abstract class Tracer extends LooperObserver implements ITracer {
    private static final String TAG = "Matrix.Tracer";
    private volatile boolean isAlive = false;

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    public boolean isForeground() {
        return AppActiveMatrixDelegate.INSTANCE.isAppForeground();
    }

    protected void onAlive() {
        MatrixLog.i("Matrix.Tracer", "[onAlive] %s", getClass().getName());
    }

    @Override
    public final synchronized void onCloseTrace() {
        if (this.isAlive) {
            this.isAlive = false;
            onDead();
        }
    }

    protected void onDead() {
        MatrixLog.i("Matrix.Tracer", "[onDead] %s", getClass().getName());
    }

    @Override
    public void onForeground(boolean z) {
    }

    @Override
    public final synchronized void onStartTrace() {
        if (!this.isAlive) {
            this.isAlive = true;
            onAlive();
        }
    }
}
