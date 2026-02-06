package com.xuehai.provider.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.xuehai.platform.AppLockManager;
import com.xuehai.platform.PlatformManager;
import com.xuehai.platform.ResourceUpdateManager;
import com.xuehai.provider.log.ContentProviderLog;
import java.util.List;

public class CPVDProcess {
    @Deprecated
    public static boolean isAppNeedUnlock(Application application) {
        return AppLockManager.isAppNeedUnlock(application);
    }

    public static boolean isIntentEffective(PackageManager packageManager, Intent intent) {
        List<ResolveInfo> listQueryIntentActivities;
        return (packageManager == null || (listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536)) == null || listQueryIntentActivities.size() <= 0) ? false : true;
    }

    @Deprecated
    public static void reSetSessionSuccess(Context context) {
        PlatformManager.reSetSessionSuccess(context);
    }

    @Deprecated
    public static void resourceUpdate(Context context, String str, String str2) {
        ResourceUpdateManager.resourceUpdate(context, str, str2);
    }

    public static void sendBroadcast(Context context, Intent intent) {
        ContentProviderLog.i("CPVDProcess", "sendBroadcast : " + intent.getAction());
        intent.setFlags(32);
        intent.putExtra("key_from", context.getPackageName());
        context.sendBroadcast(intent);
    }

    public static void sendBroadcastCompat(Context context, Intent intent) {
        sendBroadcast(context, intent);
    }

    @Deprecated
    public static void sessionFailed(Context context) {
        PlatformManager.sessionFailed(context);
    }

    @Deprecated
    public static void showAppUnLockView(Activity activity) {
        AppLockManager.showAppUnLockView(activity);
    }
}
