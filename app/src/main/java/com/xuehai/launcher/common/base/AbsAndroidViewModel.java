package com.xuehai.launcher.common.base;

import android.app.Application;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u0003¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xuehai/launcher/common/base/AbsAndroidViewModel;", "Lcom/xuehai/launcher/common/base/AbsViewModel;", "mApplication", "Landroid/app/Application;", "(Landroid/app/Application;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "getApplication", "T", "()Landroid/app/Application;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbsAndroidViewModel extends AbsViewModel {
    private final String TAG;
    private final Application mApplication;

    public AbsAndroidViewModel(Application application) {
        Intrinsics.checkNotNullParameter(application, "mApplication");
        this.mApplication = application;
        this.TAG = getClass().getCanonicalName() + "[MDM]";
    }

    public final <T extends Application> T getApplication() {
        return (T) this.mApplication;
    }

    public final String getTAG() {
        return this.TAG;
    }
}
