package io.reactivex.internal.observers;

public final class BlockingFirstObserver<T> extends BlockingBaseObserver<T> {
    @Override
    public void onError(Throwable th) {
        if (this.value == null) {
            this.error = th;
        }
        countDown();
    }

    @Override
    public void onNext(T t) {
        if (this.value == null) {
            this.value = t;
            this.d.dispose();
            countDown();
        }
    }
}
