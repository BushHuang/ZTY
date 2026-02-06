package io.reactivex.internal.subscribers;

public final class BlockingLastSubscriber<T> extends BlockingBaseSubscriber<T> {
    @Override
    public void onError(Throwable th) {
        this.value = null;
        this.error = th;
        countDown();
    }

    @Override
    public void onNext(T t) {
        this.value = t;
    }
}
