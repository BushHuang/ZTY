package com.huawei.hms.common.api.internal;

import android.os.Looper;
import com.huawei.hms.common.api.OptionalPendingResult;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.ResultCallback;
import java.util.concurrent.TimeUnit;

@Deprecated
public final class OptionalPendingResultImpl<R extends Result> extends OptionalPendingResult<R> {
    private final PendingResult<R> pendingResult;

    public OptionalPendingResultImpl(PendingResult<R> pendingResult) {
        this.pendingResult = pendingResult;
    }

    public final void addStatusListener() {
    }

    @Override
    public final R await() {
        return (R) this.pendingResult.await();
    }

    @Override
    public final R await(long j, TimeUnit timeUnit) {
        return (R) this.pendingResult.await(j, timeUnit);
    }

    @Override
    public final void cancel() {
    }

    @Override
    public final R get() {
        throw new IllegalStateException("Result is not available. Check that isDone() returns true before calling get().");
    }

    @Override
    public final boolean isCanceled() {
        return false;
    }

    @Override
    public final boolean isDone() {
        return false;
    }

    @Override
    public void setResultCallback(Looper looper, ResultCallback<R> resultCallback) {
        this.pendingResult.setResultCallback(looper, resultCallback);
    }

    @Override
    public final void setResultCallback(ResultCallback<R> resultCallback) {
        this.pendingResult.setResultCallback(resultCallback);
    }

    @Override
    public final void setResultCallback(ResultCallback<R> resultCallback, long j, TimeUnit timeUnit) {
        setResultCallback(resultCallback);
    }
}
