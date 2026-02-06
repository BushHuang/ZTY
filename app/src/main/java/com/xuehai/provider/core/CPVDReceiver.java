package com.xuehai.provider.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xuehai.platform.ResourceUpdateManager;
import com.xuehai.provider.constants.BroadcastAction;
import com.xuehai.provider.core.db.CPVDCacheData;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.log.ContentProviderLog;

public class CPVDReceiver extends BroadcastReceiver {
    private static BroadcastCallback callback;

    public static abstract class BroadcastCallback {
        public void onAppCacheUpdate(String str) {
        }

        public void onAppDataCleared() {
        }

        public void onApplicationExit() {
        }

        public void onLauncherLogout() {
        }

        public void onLogoutLauncher(int i, String str) {
        }

        public void onReceiverMessage(Context context) {
        }

        public void onResourceUpdate(String str) {
        }

        public void onSessionReset(Context context) {
        }
    }

    private boolean dealPushMessage(Context context, Intent intent) {
        if (!BroadcastAction.messageTrans(context.getPackageName()).equals(intent.getAction())) {
            return false;
        }
        log("message updated");
        BroadcastCallback broadcastCallback = callback;
        if (broadcastCallback == null) {
            return true;
        }
        broadcastCallback.onReceiverMessage(context);
        return true;
    }

    private void log(String str) {
        ContentProviderLog.i("CPVDReceiver", str);
    }

    public static void removeCallback() {
        callback = null;
    }

    public static void setCallback(BroadcastCallback broadcastCallback) {
        callback = broadcastCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action;
        BroadcastCallback broadcastCallback;
        String stringExtra;
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        if (action.equals("com.xh.launcher.exit")) {
            log("launcher exit!");
            BroadcastCallback broadcastCallback2 = callback;
            if (broadcastCallback2 != null) {
                broadcastCallback2.onLauncherLogout();
                return;
            }
            return;
        }
        if (action.equals("com.xh.logout.launcher")) {
            log(" logout launcher ");
            if (callback != null) {
                int intExtra = -1;
                try {
                    intExtra = intent.getIntExtra("code", -1);
                    stringExtra = intent.getStringExtra("msg");
                } catch (Exception e) {
                    e.printStackTrace();
                    stringExtra = "";
                }
                callback.onLogoutLauncher(intExtra, stringExtra);
                return;
            }
            return;
        }
        if (action.equals("com.xuehai.cpvd.application_exit")) {
            log("application exit");
            BroadcastCallback broadcastCallback3 = callback;
            if (broadcastCallback3 != null) {
                broadcastCallback3.onApplicationExit();
            }
        }
        if (action.equals(BroadcastAction.clearAppData(context.getPackageName()))) {
            log("clear app data !");
            BroadcastCallback broadcastCallback4 = callback;
            if (broadcastCallback4 != null) {
                broadcastCallback4.onAppDataCleared();
                return;
            }
            return;
        }
        if (dealPushMessage(context, intent)) {
            return;
        }
        if (action.equals(ResourceUpdateManager.resourceUpdate(CPVDManager.appCode))) {
            String stringExtra2 = intent.getStringExtra("dataCode");
            if (stringExtra2 == null) {
                return;
            }
            log("dataCode: " + stringExtra2);
            BroadcastCallback broadcastCallback5 = callback;
            if (broadcastCallback5 != null) {
                broadcastCallback5.onResourceUpdate(stringExtra2);
                return;
            }
            return;
        }
        String stringExtra3 = intent.getStringExtra("key_cache_key");
        if (TextUtils.isEmpty(stringExtra3)) {
            CPVDCacheData.clearMemory();
        } else {
            CPVDCacheData.clearMemory(stringExtra3);
        }
        CPVDUserData.clearCache(stringExtra3);
        log("clear cache " + stringExtra3);
        if (!action.equals("com.xuehai.cpvd.session.reset.success")) {
            if (!action.equals("com.xuehai.cpvd.cache_update") || (broadcastCallback = callback) == null) {
                return;
            }
            broadcastCallback.onAppCacheUpdate(stringExtra3);
            return;
        }
        log("user: " + CPVDUserData.getUser(context));
        BroadcastCallback broadcastCallback6 = callback;
        if (broadcastCallback6 != null) {
            broadcastCallback6.onSessionReset(context);
        }
    }
}
