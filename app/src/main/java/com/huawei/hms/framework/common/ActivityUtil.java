package com.huawei.hms.framework.common;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.List;

public class ActivityUtil {
    private static final String TAG = "ActivityUtil";

    public static PendingIntent getActivities(Context context, int i, Intent[] intentArr, int i2) {
        if (context == null) {
            Logger.w("ActivityUtil", "context is null");
            return null;
        }
        try {
            return PendingIntent.getActivities(context, i, intentArr, i2);
        } catch (RuntimeException e) {
            Logger.e("ActivityUtil", "dealType rethrowFromSystemServer:", e);
            return null;
        }
    }

    public static boolean isForeground(Context context) {
        ActivityManager activityManager;
        if (context == null || (activityManager = (ActivityManager) ContextCompat.getSystemService(context, "activity")) == null) {
            return false;
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = null;
        try {
            runningAppProcesses = activityManager.getRunningAppProcesses();
        } catch (RuntimeException e) {
            Logger.w("ActivityUtil", "activityManager getRunningAppProcesses occur exception: ", e);
        }
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName != null && runningAppProcessInfo.processName.equals(context.getPackageName()) && runningAppProcessInfo.importance == 100) {
                Logger.v("ActivityUtil", "isForeground true");
                return true;
            }
        }
        return false;
    }
}
