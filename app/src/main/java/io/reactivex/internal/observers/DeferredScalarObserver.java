package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public abstract class DeferredScalarObserver<T, R> extends DeferredScalarDisposable<R> implements Observer<T> {
    private static final long serialVersionUID = -266195175408988651L;
    protected Disposable s;

    public DeferredScalarObserver(Observer<? super R> observer) {
        super(observer);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.s.dispose();
    }

    @Override
    public void onComplete() {
        T t = this.value;
        if (t == null) {
            complete();
        } else {
            this.value = null;
            complete(t);
        }
    }

    @Override
    public void onError(Throwable th) {
        this.value = null;
        error(th);
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.s, disposable)) {
            this.s = disposable;
            this.actual.onSubscribe(this);
        }
    }
}
