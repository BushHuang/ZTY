package com.xuehai.platform.notification;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import com.xuehai.provider.core.db.CPVDCacheData;
import com.xuehai.provider.core.db.CPVDClientData;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDCache;
import com.xuehai.provider.core.dto.CPVDClient;
import com.xuehai.provider.core.dto.CPVDUser;
import com.xuehai.provider.log.ContentProviderLog;
import com.xuehai.provider.utils.JsonUtil;
import com.xuehai.provider.utils.notification.XhNotification;
import java.util.ArrayList;
import java.util.List;

public class XhNotificationManager {
    private static final String TAG = "XhNotificationManager";

    public static void cancel(Context context, int i) {
        ContentProviderLog.i("XhNotificationManager", "移除通知：id = " + i);
        context.sendBroadcast(XhNotificationIntent.createRemoveIntent(i));
        if (checkPermission(context)) {
            NotificationManagerCompat.from(context).cancel(i);
        }
    }

    public static void cancelPushNotification(Context context, long j) {
        if (isLauncherSupport(context)) {
            ContentProviderLog.i("XhNotificationManager", "取消推送通知：" + j);
            context.sendBroadcast(XhNotificationIntent.createRemovePushIntent(context, j));
        }
    }

    private static boolean checkPermission(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    public static List<XhNotificationConfig> getAllNotificationConfigs(Context context, long j) {
        if (isLauncherSupport(context) && j > 0) {
            return JsonUtil.parseJsonToList(CPVDCacheData.getCacheValue(context, getKey(j)), XhNotificationConfig.class);
        }
        return null;
    }

    private static String getKey(long j) {
        return "key_notify_config_" + j;
    }

    public static XhNotificationConfig getNotificationConfig(Context context, String str) {
        if (!isLauncherSupport(context)) {
            return null;
        }
        CPVDUser user = CPVDUserData.getUser(context);
        if (user == null) {
            ContentProviderLog.i("XhNotificationManager", "getNotificationConfig user is null");
            return null;
        }
        List<XhNotificationConfig> allNotificationConfigs = getAllNotificationConfigs(context, user.getUserId());
        if (allNotificationConfigs != null && !allNotificationConfigs.isEmpty()) {
            for (XhNotificationConfig xhNotificationConfig : allNotificationConfigs) {
                if (TextUtils.equals(xhNotificationConfig.getPackageName(), str)) {
                    return xhNotificationConfig;
                }
            }
        }
        return null;
    }

    public static boolean isLauncherSupport(Context context) {
        CPVDClient clientInfo = CPVDClientData.getClientInfo(context);
        return clientInfo != null && clientInfo.getVersionCode() >= 33800;
    }

    public static void notify(Context context, XhNotification xhNotification) {
        XhNotificationConfig notificationConfig = getNotificationConfig(context, context.getPackageName());
        if (notificationConfig == null || !notificationConfig.isForbidden()) {
            if (TextUtils.isEmpty(xhNotification.getPackageName())) {
                xhNotification.setPackageName(context.getPackageName());
            }
            if (checkPermission(context)) {
                ContentProviderLog.i("XhNotificationManager", "系统通知：" + xhNotification.toString());
                NotificationManagerCompat.from(context).notify(xhNotification.getId(), xhNotification.transformSystemNotification());
                return;
            }
            ContentProviderLog.i("XhNotificationManager", "Banner通知：" + xhNotification.toString());
            context.sendBroadcast(XhNotificationIntent.createBannerIntent(xhNotification));
        }
    }

    public static void notifyPushNotification(Context context, long j) {
        XhNotificationConfig notificationConfig = getNotificationConfig(context, context.getPackageName());
        if ((notificationConfig == null || !notificationConfig.isForbidden()) && isLauncherSupport(context)) {
            ContentProviderLog.i("XhNotificationManager", "展示推送通知：" + j);
            context.sendBroadcast(XhNotificationIntent.createPushIntent(context, j));
        }
    }

    public static boolean updateNotificationConfig(Context context, XhNotificationConfig xhNotificationConfig) {
        boolean z = false;
        if (!isLauncherSupport(context)) {
            return false;
        }
        CPVDUser user = CPVDUserData.getUser(context);
        if (user == null) {
            ContentProviderLog.i("XhNotificationManager", "updateNotificationConfig user is null");
            return false;
        }
        List<XhNotificationConfig> allNotificationConfigs = getAllNotificationConfigs(context, user.getUserId());
        if (allNotificationConfigs == null || allNotificationConfigs.isEmpty()) {
            allNotificationConfigs = new ArrayList();
            allNotificationConfigs.add(xhNotificationConfig);
        } else {
            for (XhNotificationConfig xhNotificationConfig2 : allNotificationConfigs) {
                if (TextUtils.equals(xhNotificationConfig2.getPackageName(), xhNotificationConfig.getPackageName())) {
                    xhNotificationConfig2.update(xhNotificationConfig);
                    z = true;
                }
            }
            if (!z) {
                allNotificationConfigs.add(xhNotificationConfig);
            }
        }
        return CPVDCacheData.saveCache(context, new CPVDCache(getKey(user.getUserId()), JsonUtil.objToJson(allNotificationConfigs)));
    }
}
