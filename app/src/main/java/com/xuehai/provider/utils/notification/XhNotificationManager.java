package com.xuehai.provider.utils.notification;

import android.content.Context;
import androidx.core.app.NotificationManagerCompat;
import com.xuehai.platform.notification.XhNotificationIntent;
import com.xuehai.provider.core.CPVDManager;
import com.xuehai.provider.log.ContentProviderLog;

@Deprecated
public class XhNotificationManager {
    private static final String TAG = "XhNotificationManager";

    public static void cancel(int i) {
        com.xuehai.platform.notification.XhNotificationManager.cancel(getContext(), i);
    }

    public static boolean checkPermission() {
        return NotificationManagerCompat.from(getContext()).areNotificationsEnabled();
    }

    public static Context getContext() {
        return CPVDManager.getContext();
    }

    public static void notify(XhNotification xhNotification) {
        com.xuehai.platform.notification.XhNotificationManager.notify(getContext(), xhNotification);
    }

    public static void removeCustomNotification(int i) {
        ContentProviderLog.i("XhNotificationManager", "移除自定义通知：id = " + i);
        getContext().sendBroadcast(XhNotificationIntent.createRemoveIntent(i));
    }

    public static void removeSystemNotification(int i) {
        ContentProviderLog.i("XhNotificationManager", "移除系统通知：id = " + i);
        NotificationManagerCompat.from(getContext()).cancel(i);
    }

    public static void showCustomNotification(XhNotification xhNotification) {
        ContentProviderLog.i("XhNotificationManager", "展示自定义通知：" + xhNotification.toString());
        getContext().sendBroadcast(XhNotificationIntent.createBannerIntent(xhNotification));
    }

    public static void showSystemNotification(XhNotification xhNotification) {
        ContentProviderLog.i("XhNotificationManager", "展示系统通知：" + xhNotification.toString());
        NotificationManagerCompat.from(getContext()).notify(xhNotification.getId(), xhNotification.transformSystemNotification());
    }
}
