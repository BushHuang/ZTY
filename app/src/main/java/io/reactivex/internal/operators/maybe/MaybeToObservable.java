package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamMaybeSource;
import io.reactivex.internal.observers.DeferredScalarDisposable;

public final class MaybeToObservable<T> extends Observable<T> implements HasUpstreamMaybeSource<T> {
    final MaybeSource<T> source;

    static final class MaybeToObservableObserver<T> extends DeferredScalarDisposable<T> implements MaybeObserver<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        Disposable d;

        MaybeToObservableObserver(Observer<? super T> observer) {
            super(observer);
        }

        @Override
        public void dispose() {
            super.dispose();
            this.d.dispose();
        }

        @Override
        public void onComplete() {
            complete();
        }

        @Override
        public void onError(Throwable th) {
            error(th);
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override
        public void onSuccess(T t) {
            complete(t);
        }
    }

    public MaybeToObservable(MaybeSource<T> maybeSource) {
        this.source = maybeSource;
    }

    public static <T> MaybeObserver<T> create(Observer<? super T> observer) {
        return new MaybeToObservableObserver(observer);
    }

    @Override
    public MaybeSource<T> source() {
        return this.source;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(create(observer));
    }
}
