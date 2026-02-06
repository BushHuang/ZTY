package io.reactivex.internal.operators.observable;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class ObservableLastMaybe<T> extends Maybe<T> {
    final ObservableSource<T> source;

    static final class LastObserver<T> implements Observer<T>, Disposable {
        final MaybeObserver<? super T> actual;
        T item;
        Disposable s;

        LastObserver(MaybeObserver<? super T> maybeObserver) {
            this.actual = maybeObserver;
        }

        @Override
        public void dispose() {
            this.s.dispose();
            this.s = DisposableHelper.DISPOSED;
        }

        @Override
        public boolean isDisposed() {
            return this.s == DisposableHelper.DISPOSED;
        }

        @Override
        public void onComplete() {
            this.s = DisposableHelper.DISPOSED;
            T t = this.item;
            if (t == null) {
                this.actual.onComplete();
            } else {
                this.item = null;
                this.actual.onSuccess(t);
            }
        }

        @Override
        public void onError(Throwable th) {
            this.s = DisposableHelper.DISPOSED;
            this.item = null;
            this.actual.onError(th);
        }

        @Override
        public void onNext(T t) {
            this.item = t;
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }
    }

    public ObservableLastMaybe(ObservableSource<T> observableSource) {
        this.source = observableSource;
    }

    @Override
    protected void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new LastObserver(maybeObserver));
    }
}
