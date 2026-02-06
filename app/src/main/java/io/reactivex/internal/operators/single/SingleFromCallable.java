package io.reactivex.internal.operators.single;

import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;

public final class SingleFromCallable<T> extends Single<T> {
    final Callable<? extends T> callable;

    public SingleFromCallable(Callable<? extends T> callable) {
        this.callable = callable;
    }

    @Override
    protected void subscribeActual(SingleObserver<? super T> singleObserver) {
        Disposable disposableEmpty = Disposables.empty();
        singleObserver.onSubscribe(disposableEmpty);
        if (disposableEmpty.isDisposed()) {
            return;
        }
        try {
            C$r8$backportedMethods$utility$Long$1$hashCode c$r8$backportedMethods$utility$Long$1$hashCode = (Object) ObjectHelper.requireNonNull(this.callable.call(), "The callable returned a null value");
            if (disposableEmpty.isDisposed()) {
                return;
            }
            singleObserver.onSuccess(c$r8$backportedMethods$utility$Long$1$hashCode);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (disposableEmpty.isDisposed()) {
                RxJavaPlugins.onError(th);
            } else {
                singleObserver.onError(th);
            }
        }
    }
}
