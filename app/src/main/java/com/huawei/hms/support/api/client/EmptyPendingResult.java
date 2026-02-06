package com.huawei.hms.support.api.client;

import android.os.Looper;
import com.huawei.hms.support.api.client.Result;
import java.util.concurrent.TimeUnit;

public class EmptyPendingResult<R extends Result> extends PendingResult<R> {

    private R f350a;

    @Override
    public R await() {
        return this.f350a;
    }

    @Override
    public R await(long j, TimeUnit timeUnit) {
        return this.f350a;
    }

    @Override
    public void cancel() {
    }

    public R getResult() {
        return this.f350a;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    public void setResult(R r) {
        this.f350a = r;
    }

    @Override
    public void setResultCallback(Looper looper, ResultCallback<R> resultCallback) {
    }

    @Override
    public void setResultCallback(ResultCallback<R> resultCallback) {
    }

    @Override
    public void setResultCallback(ResultCallback<R> resultCallback, long j, TimeUnit timeUnit) {
    }
}
