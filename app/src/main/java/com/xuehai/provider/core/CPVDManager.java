package com.xuehai.provider.core;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import com.xuehai.platform.ResourceUpdateManager;
import com.xuehai.provider.constants.BroadcastAction;
import com.xuehai.provider.core.CPVDReceiver;
import com.xuehai.provider.core.db.CPVDCacheData;

public class CPVDManager {
    public static String appCode = "";
    public static Context context;
    public static CPVDReceiver cpvdReceiver;

    public static void clearAllCache() {
        CPVDCacheData.clearMemory();
    }

    public static Context getContext() throws RuntimeException {
        Context context2 = context;
        if (context2 != null) {
            return context2;
        }
        throw new RuntimeException("must be call CPVDManager.registerCPVD() before!");
    }

    public static void init(Application application, String str) {
        context = application.getApplicationContext();
        appCode = str;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xh.launcher.exit");
        intentFilter.addAction("com.xh.logout.launcher");
        intentFilter.addAction(ResourceUpdateManager.resourceUpdate(str));
        intentFilter.addAction("com.xuehai.cpvd.session.reset.success");
        intentFilter.addAction("com.xuehai.cpvd.cache_update");
        intentFilter.addAction("com.xuehai.cpvd.application_exit");
        intentFilter.addAction(BroadcastAction.clearAppData(context.getPackageName()));
        intentFilter.addAction(BroadcastAction.messageTrans(context.getPackageName()));
        if (cpvdReceiver == null) {
            cpvdReceiver = new CPVDReceiver();
        }
        context.registerReceiver(cpvdReceiver, intentFilter);
        clearAllCache();
    }

    @Deprecated
    public static void registerCPVD(Application application, String str, CPVDReceiver.BroadcastCallback broadcastCallback) {
        init(application, str);
        setCallback(broadcastCallback);
    }

    public static void setCallback(CPVDReceiver.BroadcastCallback broadcastCallback) {
        CPVDReceiver.setCallback(broadcastCallback);
    }

    public static void unRegisterCPVD(Context context2) {
        context2.unregisterReceiver(cpvdReceiver);
        CPVDReceiver.removeCallback();
    }
}
