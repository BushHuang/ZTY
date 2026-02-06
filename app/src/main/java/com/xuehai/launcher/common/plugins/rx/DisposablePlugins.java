package com.xuehai.launcher.common.plugins.rx;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007J\b\u0010\n\u001a\u00020\u0006H\u0007J\u001e\u0010\u000b\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xuehai/launcher/common/plugins/rx/DisposablePlugins;", "", "()V", "canClearDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "add", "", "d", "Lio/reactivex/disposables/Disposable;", "compositeDisposable", "clear", "remove", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DisposablePlugins {
    public static final DisposablePlugins INSTANCE = new DisposablePlugins();
    private static final CompositeDisposable canClearDisposable = new CompositeDisposable();

    private DisposablePlugins() {
    }

    @JvmStatic
    public static final void add(Disposable d, CompositeDisposable compositeDisposable) {
        if (d != null) {
            if (compositeDisposable == null) {
                compositeDisposable = canClearDisposable;
            }
            compositeDisposable.add(d);
        }
    }

    public static void add$default(Disposable disposable, CompositeDisposable compositeDisposable, int i, Object obj) {
        if ((i & 2) != 0) {
            compositeDisposable = null;
        }
        add(disposable, compositeDisposable);
    }

    @JvmStatic
    public static final void clear() {
        canClearDisposable.clear();
    }

    @JvmStatic
    public static final void remove(Disposable d, CompositeDisposable compositeDisposable) {
        if (d != null) {
            if (compositeDisposable == null) {
                compositeDisposable = canClearDisposable;
            }
            compositeDisposable.remove(d);
        }
    }

    public static void remove$default(Disposable disposable, CompositeDisposable compositeDisposable, int i, Object obj) {
        if ((i & 2) != 0) {
            compositeDisposable = null;
        }
        remove(disposable, compositeDisposable);
    }
}
