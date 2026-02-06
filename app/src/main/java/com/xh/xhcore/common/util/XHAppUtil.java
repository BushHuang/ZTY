package com.xh.xhcore.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.text.TextUtils;
import com.xh.logutils.LogManager;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.statistic.AppBury;
import com.xh.xhcore.common.statistic.BuryFileListener;
import com.xh.xhcore.common.statistic.DataCollectionUtil;
import com.xh.xhcore.common.statistic.usage.AppUsageDurationManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XHAppUtil {
    public static Boolean isProcessBeingKilling = false;
    private static String packageName = "";
    private static PackageInfo packageInfo = null;
    private static String versionName = "";
    private static int versionCode = 0;
    private static ActivityManager activityManager = null;

    private XHAppUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void finishActivityAndServiceBeforeKillProcess() {
        XhBaseApplication.getXhBaseApplication().stopLogcatService();
        isProcessBeingKilling = true;
        LogManager.getInstance().finishAllActivities();
    }

    private static ActivityManager getActivityManager(Context context) {
        if (activityManager == null) {
            activityManager = (ActivityManager) context.getSystemService("activity");
        }
        return activityManager;
    }

    public static String getAppName() {
        try {
            return XhBaseApplication.mContext.getResources().getString(XhBaseApplication.mContext.getPackageManager().getPackageInfo(XhBaseApplication.mContext.getPackageName(), 0).applicationInfo.labelRes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<ActivityManager.RunningAppProcessInfo> getAppProcess(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = getActivityManager(context).getRunningAppProcesses();
        return runningAppProcesses == null ? new ArrayList() : runningAppProcesses;
    }

    private static PackageInfo getPackageInfo() {
        if (packageInfo == null) {
            try {
                packageInfo = XhBaseApplication.mContext.getPackageManager().getPackageInfo(XhBaseApplication.mContext.getPackageName(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return packageInfo;
    }

    public static String getPackageName() {
        if (TextUtils.isEmpty(packageName)) {
            try {
                PackageInfo packageInfo2 = getPackageInfo();
                if (packageInfo2 != null && packageInfo2.packageName != null) {
                    packageName = packageInfo2.packageName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return packageName;
    }

    public static String getTinkerId() {
        return getVersionName() + "_" + getVersionCode();
    }

    public static int getVersionCode() {
        if (versionCode == 0) {
            try {
                PackageInfo packageInfo2 = getPackageInfo();
                if (packageInfo2 != null) {
                    versionCode = packageInfo2.versionCode;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return versionCode;
    }

    public static String getVersionName() {
        if (TextUtils.isEmpty(versionName)) {
            try {
                PackageInfo packageInfo2 = getPackageInfo();
                if (packageInfo2 != null && packageInfo2.versionName != null) {
                    versionName = packageInfo2.versionName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return versionName;
    }

    public static void killAllProcess() {
        killAllProcess(0L);
    }

    public static void killAllProcess(final long j) {
        saveBuryToFile(new BuryFileListener.BurySaveListener() {
            @Override
            public final void saveFinish() {
                XHAppUtil.lambda$killAllProcess$2(j);
            }
        });
    }

    private static void killMultiProcess() {
        XhBaseApplication xhBaseApplication = XhBaseApplication.getXhBaseApplication();
        getActivityManager(xhBaseApplication).killBackgroundProcesses(xhBaseApplication.getPackageName());
        Iterator<ActivityManager.RunningAppProcessInfo> it = getAppProcess(xhBaseApplication).iterator();
        while (it.hasNext()) {
            Process.killProcess(it.next().pid);
        }
    }

    public static void killProcess() {
        saveBuryToFile(new BuryFileListener.BurySaveListener() {
            @Override
            public final void saveFinish() {
                XHAppUtil.lambda$killProcess$0();
            }
        });
    }

    static void lambda$killAllProcess$1(long j) throws InterruptedException {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        killMultiProcess();
    }

    static void lambda$killAllProcess$2(final long j) {
        finishActivityAndServiceBeforeKillProcess();
        if (j <= 0) {
            killMultiProcess();
        } else {
            new Thread(new Runnable() {
                @Override
                public final void run() throws InterruptedException {
                    XHAppUtil.lambda$killAllProcess$1(j);
                }
            }).start();
        }
    }

    static void lambda$killProcess$0() {
        finishActivityAndServiceBeforeKillProcess();
        Process.killProcess(Process.myPid());
    }

    private static void saveBuryToFile(BuryFileListener.BurySaveListener burySaveListener) {
        if (!XhPermissionUtil.hasPermissions(XhBaseApplication.mContext, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            burySaveListener.saveFinish();
            return;
        }
        if (DataCollectionUtil.isOnTop.booleanValue()) {
            AppUsageDurationManager.getInstance().readAndSaveStatisticsInfo();
        }
        if (AppBury.getInstance().isBuryEventsEmpty()) {
            burySaveListener.saveFinish();
            return;
        }
        try {
            if (XHFileUtil.createOrExistsFile(DataCollectionUtil.getBuryFile().getAbsolutePath())) {
                BuryFileListener buryFileListener = new BuryFileListener(DataCollectionUtil.getBuryFile().getAbsolutePath());
                buryFileListener.setBurySaveListener(burySaveListener);
                buryFileListener.startWatching();
                DataCollectionUtil.dumpBury();
            } else {
                burySaveListener.saveFinish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            burySaveListener.saveFinish();
        }
    }
}
