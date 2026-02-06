package io.reactivex.observers;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DisposableMaybeObserver<T> implements MaybeObserver<T>, Disposable {
    final AtomicReference<Disposable> s = new AtomicReference<>();

    @Override
    public final void dispose() {
        DisposableHelper.dispose(this.s);
    }

    @Override
    public final boolean isDisposed() {
        return this.s.get() == DisposableHelper.DISPOSED;
    }

    protected void onStart() {
    }

    @Override
    public final void onSubscribe(Disposable disposable) {
        if (EndConsumerHelper.setOnce(this.s, disposable, getClass())) {
            onStart();
        }
    }
}
