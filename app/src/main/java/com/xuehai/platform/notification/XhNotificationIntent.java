package com.xuehai.platform.notification;

import android.content.Context;
import android.content.Intent;
import com.xuehai.provider.utils.notification.XhNotification;

public class XhNotificationIntent {
    public static final String ACTION_APP_NOTIFICATION = "com.xuehai.launcher.app.notification.show";
    public static final String ACTION_APP_NOTIFICATION_REMOVE = "com.xuehai.launcher.app.notification.remove";
    public static final String ACTION_SEND_BANNER_NOTIFICATION = "com.xuehai.launcher.send_banner_notification";
    public static final String NOTIFICATION_ID = "notification_id";
    public static final String PACKAGE_NAME = "package_name";
    public static final String REMOVE_NOTIFICATION = "remove_notification";
    public static final String SHOW_NOTIFICATION = "show_notification";
    public static final String SYSTEM_NOTIFY_ENABLE = "system_notify_enable";

    public static Intent createBannerIntent(XhNotification xhNotification) {
        Intent intent = new Intent("com.xuehai.launcher.send_banner_notification");
        intent.putExtra("show_notification", xhNotification);
        return intent;
    }

    public static Intent createCustomIntent(XhNotification xhNotification) {
        Intent intentCreateBannerIntent = createBannerIntent(xhNotification);
        intentCreateBannerIntent.putExtra("system_notify_enable", true);
        return intentCreateBannerIntent;
    }

    public static Intent createPushIntent(Context context, long j) {
        Intent intent = new Intent("com.xuehai.launcher.app.notification.show");
        intent.putExtra("notification_id", j);
        intent.putExtra("package_name", context.getPackageName());
        return intent;
    }

    public static Intent createRemoveIntent(int i) {
        Intent intent = new Intent("com.xuehai.launcher.send_banner_notification");
        intent.putExtra("remove_notification", i);
        return intent;
    }

    public static Intent createRemovePushIntent(Context context, long j) {
        Intent intent = new Intent("com.xuehai.launcher.app.notification.remove");
        intent.putExtra("notification_id", j);
        intent.putExtra("package_name", context.getPackageName());
        return intent;
    }

    public XhNotification getNotification(Intent intent) {
        try {
            return (XhNotification) intent.getParcelableExtra("show_notification");
        } catch (Exception unused) {
            return null;
        }
    }

    public int getRemoveId(Intent intent) {
        return intent.getIntExtra("remove_notification", -1);
    }
}
