package com.dovar.dtoast;

import android.app.Activity;
import android.content.Context;
import androidx.core.app.NotificationManagerCompat;
import com.dovar.dtoast.inner.ActivityToast;
import com.dovar.dtoast.inner.DovaToast;
import com.dovar.dtoast.inner.IToast;
import com.dovar.dtoast.inner.SystemToast;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DToast {
    public static final int DURATION_LONG = 3500;
    public static final int DURATION_SHORT = 2000;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public static void cancel() {
        DovaToast.cancelAll();
        SystemToast.cancelAll();
    }

    public static void cancelActivityToast(Activity activity) {
        DovaToast.cancelActivityToast(activity);
    }

    public static void enableLog(boolean z) {
        DUtil.enableLog = z;
    }

    public static IToast make(Context context) {
        if (context == null) {
            return null;
        }
        return (NotificationManagerCompat.from(context).areNotificationsEnabled() || SystemToast.isValid4HookINotificationManager() || DUtil.isWhiteList()) ? new SystemToast(context) : ((context instanceof Activity) && DovaToast.isBadChoice()) ? new ActivityToast(context) : new DovaToast(context);
    }
}
