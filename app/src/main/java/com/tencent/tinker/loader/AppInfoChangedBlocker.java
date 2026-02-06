package com.tencent.tinker.loader;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Field;

public final class AppInfoChangedBlocker {
    private static final String TAG = "Tinker.AppInfoChangedBlocker";

    private static class HackerCallback implements Handler.Callback {
        private final int APPLICATION_INFO_CHANGED;
        private Handler.Callback origin;

        HackerCallback(Handler.Callback callback, Class cls) {
            int i;
            this.origin = callback;
            try {
                i = ShareReflectUtil.findField((Class<?>) cls, "APPLICATION_INFO_CHANGED").getInt(null);
            } catch (Throwable unused) {
                i = 156;
            }
            this.APPLICATION_INFO_CHANGED = i;
        }

        private boolean hackMessage(Message message) {
            if (message.what != this.APPLICATION_INFO_CHANGED) {
                return false;
            }
            Process.killProcess(Process.myPid());
            return true;
        }

        @Override
        public boolean handleMessage(Message message) {
            if (hackMessage(message)) {
                return true;
            }
            Handler.Callback callback = this.origin;
            if (callback != null) {
                return callback.handleMessage(message);
            }
            return false;
        }
    }

    private static Handler fetchMHObject(Context context) throws Exception {
        Object activityThread = ShareReflectUtil.getActivityThread(context, null);
        return (Handler) ShareReflectUtil.findField(activityThread, "mH").get(activityThread);
    }

    private static void interceptHandler(Handler handler) throws Exception {
        Field fieldFindField = ShareReflectUtil.findField((Class<?>) Handler.class, "mCallback");
        Handler.Callback callback = (Handler.Callback) fieldFindField.get(handler);
        if (callback instanceof HackerCallback) {
            Log.w("Tinker.AppInfoChangedBlocker", "Already intercepted, skip rest logic.");
        } else {
            fieldFindField.set(handler, new HackerCallback(callback, handler.getClass()));
        }
    }

    public static boolean tryStart(Application application) {
        if (Build.VERSION.SDK_INT < 26) {
            Log.i("Tinker.AppInfoChangedBlocker", "tryStart: SDK_INT is less than 26, skip rest logic.");
            return true;
        }
        try {
            Log.i("Tinker.AppInfoChangedBlocker", "tryStart called.");
            interceptHandler(fetchMHObject(application));
            Log.i("Tinker.AppInfoChangedBlocker", "tryStart done.");
            return true;
        } catch (Throwable th) {
            Log.e("Tinker.AppInfoChangedBlocker", "AppInfoChangedBlocker start failed, simply ignore.", th);
            return false;
        }
    }
}
