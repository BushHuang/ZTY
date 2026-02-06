package com.xuehai.launcher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.xuehai.launcher.common.cache.SessionData;
import com.xuehai.launcher.common.constants.FilePath;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.util.AppInstallManager;
import com.xuehai.launcher.util.ZtyClientUtil;
import com.zaze.utils.FileUtil;
import com.zaze.utils.ZStringUtil;
import com.zaze.utils.date.DateUtil;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

public class PackageReceiver extends BroadcastReceiver {
    private static final Set<PackageListener> LICENSE_LISTENERS = new HashSet();
    private static final File packageChangeFile = new File(FilePath.getLogDir() + "/appChange.log");

    public interface PackageListener {
        void afterAppAdded(String str);

        void afterAppRemoved(String str);

        void afterAppReplaced(String str);
    }

    public static void addListener(PackageListener packageListener) {
        synchronized (LICENSE_LISTENERS) {
            LICENSE_LISTENERS.add(packageListener);
        }
    }

    public static IntentFilter createIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        return intentFilter;
    }

    public static void removeListener(PackageListener packageListener) {
        synchronized (LICENSE_LISTENERS) {
            LICENSE_LISTENERS.remove(packageListener);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (intent.getData() != null) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            MyLog.i("Application[MDM]", ZStringUtil.format("PackageReceiver: %s（%s）", action, schemeSpecificPart));
            if (context.getPackageName().equals(schemeSpecificPart)) {
                return;
            }
            synchronized (LICENSE_LISTENERS) {
                String str = null;
                if (TextUtils.equals(action, "android.intent.action.PACKAGE_ADDED")) {
                    str = "应用添加成功: " + schemeSpecificPart;
                    if (ZtyClientUtil.INSTANCE.isZtyClient(schemeSpecificPart)) {
                        SessionData.INSTANCE.setZtyUpdating(false);
                    }
                    Iterator<PackageListener> it = LICENSE_LISTENERS.iterator();
                    while (it.hasNext()) {
                        it.next().afterAppAdded(schemeSpecificPart);
                    }
                    AppInstallManager.INSTANCE.onAppUpdated(schemeSpecificPart);
                } else if (TextUtils.equals(action, "android.intent.action.PACKAGE_REPLACED")) {
                    str = "应用更新成功: " + schemeSpecificPart;
                    if (ZtyClientUtil.INSTANCE.isZtyClient(schemeSpecificPart)) {
                        SessionData.INSTANCE.setZtyUpdating(false);
                    }
                    Iterator<PackageListener> it2 = LICENSE_LISTENERS.iterator();
                    while (it2.hasNext()) {
                        it2.next().afterAppReplaced(schemeSpecificPart);
                    }
                    AppInstallManager.INSTANCE.onAppUpdated(schemeSpecificPart);
                } else if (TextUtils.equals(action, "android.intent.action.PACKAGE_REMOVED")) {
                    str = "应用卸载成功: " + schemeSpecificPart;
                    Iterator<PackageListener> it3 = LICENSE_LISTENERS.iterator();
                    while (it3.hasNext()) {
                        it3.next().afterAppRemoved(schemeSpecificPart);
                    }
                }
                if (str != null) {
                    FileUtil.writeToFile(packageChangeFile, DateUtil.timeMillisToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss ", TimeZone.getDefault()) + str + "\n", 10485760L);
                }
            }
        }
    }
}
