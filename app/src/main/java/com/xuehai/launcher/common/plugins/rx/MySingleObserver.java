package com.xuehai.launcher.common.plugins.rx;

import com.xuehai.launcher.common.log.MyLog;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0011\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0007H\u0016J\u0015\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/xuehai/launcher/common/plugins/rx/MySingleObserver;", "T", "Lio/reactivex/SingleObserver;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "(Lio/reactivex/disposables/CompositeDisposable;)V", "disposable", "Lio/reactivex/disposables/Disposable;", "getDisposable", "()Lio/reactivex/disposables/Disposable;", "setDisposable", "(Lio/reactivex/disposables/Disposable;)V", "onError", "", "e", "", "onSubscribe", "d", "onSuccess", "result", "(Ljava/lang/Object;)V", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class MySingleObserver<T> implements SingleObserver<T> {
    private final CompositeDisposable compositeDisposable;
    private Disposable disposable;

    public MySingleObserver() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public MySingleObserver(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    public MySingleObserver(CompositeDisposable compositeDisposable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : compositeDisposable);
    }

    public final Disposable getDisposable() {
        return this.disposable;
    }

    @Override
    public void onError(Throwable e) {
        Intrinsics.checkNotNullParameter(e, "e");
        MyLog.w("Error[MDM]", "MySingleObserver.onError", e);
        DisposablePlugins.remove(this.disposable, this.compositeDisposable);
    }

    @Override
    public void onSubscribe(Disposable d) {
        Intrinsics.checkNotNullParameter(d, "d");
        this.disposable = d;
        DisposablePlugins.add(d, this.compositeDisposable);
    }

    @Override
    public void onSuccess(T result) {
        DisposablePlugins.remove(this.disposable, this.compositeDisposable);
    }

    public final void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }
}
