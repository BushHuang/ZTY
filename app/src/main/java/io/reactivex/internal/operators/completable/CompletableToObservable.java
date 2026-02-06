package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicQueueDisposable;

public final class CompletableToObservable<T> extends Observable<T> {
    final CompletableSource source;

    static final class ObserverCompletableObserver extends BasicQueueDisposable<Void> implements CompletableObserver {
        final Observer<?> observer;
        Disposable upstream;

        ObserverCompletableObserver(Observer<?> observer) {
            this.observer = observer;
        }

        @Override
        public void clear() {
        }

        @Override
        public void dispose() {
            this.upstream.dispose();
        }

        @Override
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public void onComplete() {
            this.observer.onComplete();
        }

        @Override
        public void onError(Throwable th) {
            this.observer.onError(th);
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.observer.onSubscribe(this);
            }
        }

        @Override
        public Void poll() throws Exception {
            return null;
        }

        @Override
        public int requestFusion(int i) {
            return i & 2;
        }
    }

    public CompletableToObservable(CompletableSource completableSource) {
        this.source = completableSource;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new ObserverCompletableObserver(observer));
    }
}
