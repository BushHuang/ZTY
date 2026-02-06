package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class DisposeOnCancel implements Future<Object> {
    final Disposable d;

    DisposeOnCancel(Disposable disposable) {
        this.d = disposable;
    }

    @Override
    public boolean cancel(boolean z) {
        this.d.dispose();
        return false;
    }

    @Override
    public Object get() throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public Object get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return null;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
