package io.reactivex.internal.observers;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class FutureSingleObserver<T> extends CountDownLatch implements SingleObserver<T>, Future<T>, Disposable {
    Throwable error;
    final AtomicReference<Disposable> s;
    T value;

    public FutureSingleObserver() {
        super(1);
        this.s = new AtomicReference<>();
    }

    @Override
    public boolean cancel(boolean z) {
        Disposable disposable;
        do {
            disposable = this.s.get();
            if (disposable == this || disposable == DisposableHelper.DISPOSED) {
                return false;
            }
        } while (!this.s.compareAndSet(disposable, DisposableHelper.DISPOSED));
        if (disposable != null) {
            disposable.dispose();
        }
        countDown();
        return true;
    }

    @Override
    public void dispose() {
    }

    @Override
    public T get() throws ExecutionException, InterruptedException {
        if (getCount() != 0) {
            BlockingHelper.verifyNonBlocking();
            await();
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable th = this.error;
        if (th == null) {
            return this.value;
        }
        throw new ExecutionException(th);
    }

    @Override
    public T get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        if (getCount() != 0) {
            BlockingHelper.verifyNonBlocking();
            if (!await(j, timeUnit)) {
                throw new TimeoutException();
            }
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable th = this.error;
        if (th == null) {
            return this.value;
        }
        throw new ExecutionException(th);
    }

    @Override
    public boolean isCancelled() {
        return DisposableHelper.isDisposed(this.s.get());
    }

    @Override
    public boolean isDisposed() {
        return isDone();
    }

    @Override
    public boolean isDone() {
        return getCount() == 0;
    }

    @Override
    public void onError(Throwable th) {
        Disposable disposable;
        do {
            disposable = this.s.get();
            if (disposable == DisposableHelper.DISPOSED) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
        } while (!this.s.compareAndSet(disposable, this));
        countDown();
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this.s, disposable);
    }

    @Override
    public void onSuccess(T t) {
        Disposable disposable = this.s.get();
        if (disposable == DisposableHelper.DISPOSED) {
            return;
        }
        this.value = t;
        this.s.compareAndSet(disposable, this);
        countDown();
    }
}
