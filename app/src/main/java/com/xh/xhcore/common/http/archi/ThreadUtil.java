package com.xh.xhcore.common.http.archi;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtil {
    private static Handler mainHandler = new Handler(Looper.getMainLooper());

    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void toMainThread(boolean z, Runnable runnable) {
        if (z) {
            mainHandler.post(runnable);
        } else {
            runnable.run();
        }
    }

    public static void toMainThreadChecked(boolean z, Object obj, Runnable runnable) {
        if (obj == null) {
            return;
        }
        toMainThread(z, runnable);
    }

    public static void toMainThreadIfNeed(Runnable runnable) {
        if (isOnMainThread()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
}
