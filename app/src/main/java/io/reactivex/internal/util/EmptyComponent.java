package io.reactivex.internal.util;

import io.reactivex.CompletableObserver;
import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public enum EmptyComponent implements FlowableSubscriber<Object>, Observer<Object>, MaybeObserver<Object>, SingleObserver<Object>, CompletableObserver, Subscription, Disposable {
    INSTANCE;

    public static <T> Observer<T> asObserver() {
        return INSTANCE;
    }

    public static <T> Subscriber<T> asSubscriber() {
        return INSTANCE;
    }

    @Override
    public void cancel() {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isDisposed() {
        return true;
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable th) {
        RxJavaPlugins.onError(th);
    }

    @Override
    public void onNext(Object obj) {
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        disposable.dispose();
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.cancel();
    }

    @Override
    public void onSuccess(Object obj) {
    }

    @Override
    public void request(long j) {
    }
}
