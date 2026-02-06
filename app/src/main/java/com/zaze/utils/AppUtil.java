package com.zaze.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Process;
import android.text.TextUtils;
import android.util.Pair;
import com.zaze.utils.ZCommand;
import com.zaze.utils.log.ZLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Regex;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J&\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\nH\u0007J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007J\"\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0007J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ$\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u001b\u001a\u00020\nH\u0007J\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\nH\u0007J(\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` 2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u001a\u0010!\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007J\u001a\u0010\"\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007J(\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010%\u001a\u00020\u0014H\u0007J \u0010&\u001a\b\u0012\u0004\u0012\u00020$0'2\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010%\u001a\u00020\u0014H\u0007J \u0010(\u001a\b\u0012\u0004\u0012\u00020)0'2\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010%\u001a\u00020\u0014H\u0007J\u001a\u0010*\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u0014H\u0007J\u001c\u0010,\u001a\u0004\u0018\u00010)2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010-\u001a\u0004\u0018\u00010\nH\u0007J\u001a\u0010.\u001a\u0004\u0018\u00010)2\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010%\u001a\u00020\u0014J(\u0010.\u001a\u0004\u0018\u00010)2\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010%\u001a\u00020\u0014H\u0007J%\u0010/\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u0001002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u0014H\u0007¢\u0006\u0002\u00101J%\u00102\u001a\n\u0012\u0004\u0012\u000203\u0018\u0001002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00104\u001a\u000205H\u0007¢\u0006\u0002\u00106J\u0018\u00107\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00108\u001a\u00020\nH\u0007J\u0010\u00109\u001a\u00020:2\u0006\u00108\u001a\u00020\nH\u0007J\u001a\u0010;\u001a\u00020:2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010<\u001a\u0004\u0018\u00010=H\u0007J\u0018\u0010>\u001a\u00020:2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0018\u0010?\u001a\u00020:2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0018\u0010@\u001a\u00020:2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010<\u001a\u00020=H\u0007J\u001a\u0010@\u001a\u00020:2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\u0012\u0010@\u001a\u00020:2\b\u0010A\u001a\u0004\u0018\u00010$H\u0007J\u0018\u0010B\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u001e\u0010C\u001a\b\u0012\u0004\u0012\u00020D0'2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J.\u0010E\u001a\u00020:2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010G\u001a\u00020:H\u0007J\u001a\u0010H\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010I\u001a\u00020:2\u0006\u0010\t\u001a\u00020\nH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/zaze/utils/AppUtil;", "", "()V", "activityManager", "Landroid/app/ActivityManager;", "clearAppData", "", "context", "Landroid/content/Context;", "packageName", "", "findSystemApk", "Landroid/util/Pair;", "Landroid/content/res/Resources;", "action", "getActivityManager", "getAppIcon", "Landroid/graphics/drawable/Drawable;", "resources", "iconId", "", "iconDpi", "getAppMemorySize", "", "getAppMetaData", "Landroid/os/Bundle;", "getAppName", "defaultName", "getAppPid", "getAppProcess", "Ljava/util/ArrayList;", "Landroid/app/ActivityManager$RunningAppProcessInfo;", "Lkotlin/collections/ArrayList;", "getAppVersionCode", "getAppVersionName", "getApplicationInfo", "Landroid/content/pm/ApplicationInfo;", "flag", "getInstalledApplications", "", "getInstalledPackages", "Landroid/content/pm/PackageInfo;", "getNameForUid", "uid", "getPackageArchiveInfo", "fileName", "getPackageInfo", "getPackagesForUid", "", "(Landroid/content/Context;I)[Ljava/lang/String;", "getProcessMemoryInfo", "Landroid/os/Debug$MemoryInfo;", "pids", "", "(Landroid/content/Context;[I)[Landroid/os/Debug$MemoryInfo;", "install", "filePath", "installApkSilent", "", "isActivityExist", "intent", "Landroid/content/Intent;", "isAppRunning", "isInstalled", "isSystemApp", "applicationInfo", "killAppProcess", "queryMainIntentActivities", "Landroid/content/pm/ResolveInfo;", "startApplication", "bundle", "needToast", "unInstall", "unInstallApkSilent", "util_release"}, k = 1, mv = {1, 4, 1})
public final class AppUtil {
    public static final AppUtil INSTANCE = new AppUtil();
    private static ActivityManager activityManager;

    private AppUtil() {
    }

    @JvmStatic
    public static final void clearAppData(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (ZCommand.isRoot()) {
            ZCommand.execRootCmd("pm clear " + packageName);
            return;
        }
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "context.filesDir");
        sb.append(filesDir.getPath());
        sb.append("/data/");
        sb.append(packageName);
        FileUtil.deleteFile(sb.toString());
        killAppProcess(context, packageName);
    }

    @JvmStatic
    public static final Pair<String, Resources> findSystemApk(Context context, String action) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(action, "action");
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(new Intent(action), 0)) {
            if (resolveInfo.activityInfo != null && (resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                String str = resolveInfo.activityInfo.packageName;
                try {
                    Resources resourcesForApplication = packageManager.getResourcesForApplication(str);
                    Intrinsics.checkNotNullExpressionValue(resourcesForApplication, "pm.getResourcesForApplication(packageName)");
                    return Pair.create(str, resourcesForApplication);
                } catch (PackageManager.NameNotFoundException unused) {
                    ZLog.w("Debug[ZZ]", "Failed to find resources for " + str, new Object[0]);
                }
            }
        }
        return null;
    }

    @JvmStatic
    public static final ActivityManager getActivityManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (activityManager == null) {
            Object systemService = context.getSystemService("activity");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
            }
            activityManager = (ActivityManager) systemService;
        }
        ActivityManager activityManager2 = activityManager;
        Intrinsics.checkNotNull(activityManager2);
        return activityManager2;
    }

    @JvmStatic
    public static final Drawable getAppIcon(Context context) {
        return getAppIcon$default(context, null, 2, null);
    }

    @JvmStatic
    public static final Drawable getAppIcon(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            return context.getPackageManager().getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            ZLog.e("Debug[ZZ]", e.getMessage(), new Object[0]);
            return null;
        }
    }

    @Deprecated(message = "")
    @JvmStatic
    public static final Drawable getAppIcon(Resources resources, int iconId, int iconDpi) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        try {
            return Build.VERSION.SDK_INT >= 21 ? resources.getDrawableForDensity(iconId, iconDpi, null) : resources.getDrawableForDensity(iconId, iconDpi);
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    public static Drawable getAppIcon$default(Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(str, "context.packageName");
        }
        return getAppIcon(context, str);
    }

    @JvmStatic
    public static final long getAppMemorySize(final Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        long j = 0;
        while (SequencesKt.filter(SequencesKt.mapNotNull(CollectionsKt.asSequence(getAppProcess(context, packageName)), new Function1<ActivityManager.RunningAppProcessInfo, Debug.MemoryInfo[]>() {
            {
                super(1);
            }

            @Override
            public final Debug.MemoryInfo[] invoke(ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
                Intrinsics.checkNotNullParameter(runningAppProcessInfo, "it");
                return AppUtil.getProcessMemoryInfo(context, new int[]{runningAppProcessInfo.pid});
            }
        }), new Function1<Debug.MemoryInfo[], Boolean>() {
            @Override
            public Boolean invoke(Debug.MemoryInfo[] memoryInfoArr) {
                return Boolean.valueOf(invoke2(memoryInfoArr));
            }

            public final boolean invoke2(Debug.MemoryInfo[] memoryInfoArr) {
                Intrinsics.checkNotNullParameter(memoryInfoArr, "it");
                return !(memoryInfoArr.length == 0);
            }
        }).iterator().hasNext()) {
            j += ((Debug.MemoryInfo[]) r4.next())[0].dalvikPrivateDirty;
        }
        return j;
    }

    @JvmStatic
    public static final String getAppName(Context context) {
        return getAppName$default(context, null, null, 6, null);
    }

    @JvmStatic
    public static final String getAppName(Context context, String str) {
        return getAppName$default(context, str, null, 4, null);
    }

    @JvmStatic
    public static final String getAppName(Context context, String packageName, String defaultName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(defaultName, "defaultName");
        ApplicationInfo applicationInfo$default = getApplicationInfo$default(context, packageName, 0, 4, null);
        return applicationInfo$default == null ? defaultName : context.getPackageManager().getApplicationLabel(applicationInfo$default).toString();
    }

    public static String getAppName$default(Context context, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(str, "context.packageName");
        }
        if ((i & 4) != 0) {
            str2 = "未知";
        }
        return getAppName(context, str, str2);
    }

    @JvmStatic
    public static final int getAppPid(String packageName) {
        List listEmptyList;
        Object[] array;
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        ZLog.i("Debug[ZZ]", "getAppPid : " + packageName, new Object[0]);
        ZCommand.CommandResult commandResultExecCmdForRes = ZCommand.execCmdForRes("ps -A |grep " + packageName);
        if (ZCommand.isSuccess(commandResultExecCmdForRes) && commandResultExecCmdForRes.successList.size() > 0) {
            String str = commandResultExecCmdForRes.successList.get(0);
            ZLog.i("Debug[ZZ]", "getAppPid : " + str, new Object[0]);
            Intrinsics.checkNotNullExpressionValue(str, "message");
            List<String> listSplit = new Regex("\\s+").split(str, 0);
            if (listSplit.isEmpty()) {
                listEmptyList = CollectionsKt.emptyList();
                array = listEmptyList.toArray(new String[0]);
                if (array != null) {
                }
            } else {
                ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                while (listIterator.hasPrevious()) {
                    if (!(listIterator.previous().length() == 0)) {
                        listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                }
                listEmptyList = CollectionsKt.emptyList();
                array = listEmptyList.toArray(new String[0]);
                if (array != null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                String[] strArr = (String[]) array;
                if (strArr.length > 1) {
                    return ZStringUtil.parseInt(strArr[1]);
                }
            }
        }
        return 0;
    }

    @Deprecated(message = "未Root情况下, Android5.0以后只能获取到自己")
    @JvmStatic
    public static final ArrayList<ActivityManager.RunningAppProcessInfo> getAppProcess(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        ActivityManager activityManager2 = getActivityManager(context);
        ArrayList<ActivityManager.RunningAppProcessInfo> arrayList = new ArrayList<>();
        if (activityManager2.getRunningAppProcesses() != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager2.getRunningAppProcesses();
            Intrinsics.checkNotNullExpressionValue(runningAppProcesses, "activityManager.runningAppProcesses");
            for (Object obj : runningAppProcesses) {
                String[] strArr = ((ActivityManager.RunningAppProcessInfo) obj).pkgList;
                Intrinsics.checkNotNullExpressionValue(strArr, "it.pkgList");
                if (ArraysKt.contains(strArr, packageName)) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    @JvmStatic
    public static final int getAppVersionCode(Context context) {
        return getAppVersionCode$default(context, null, 2, null);
    }

    @JvmStatic
    public static final int getAppVersionCode(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        PackageInfo packageInfo$default = getPackageInfo$default(context, packageName, 0, 4, (Object) null);
        if (packageInfo$default != null) {
            return packageInfo$default.versionCode;
        }
        return 0;
    }

    public static int getAppVersionCode$default(Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(str, "context.packageName");
        }
        return getAppVersionCode(context, str);
    }

    @JvmStatic
    public static final String getAppVersionName(Context context) {
        return getAppVersionName$default(context, null, 2, null);
    }

    @JvmStatic
    public static final String getAppVersionName(Context context, String packageName) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        PackageInfo packageInfo$default = getPackageInfo$default(context, packageName, 0, 4, (Object) null);
        return (packageInfo$default == null || (str = packageInfo$default.versionName) == null) ? "" : str;
    }

    public static String getAppVersionName$default(Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(str, "context.packageName");
        }
        return getAppVersionName(context, str);
    }

    @JvmStatic
    public static final ApplicationInfo getApplicationInfo(Context context) {
        return getApplicationInfo$default(context, null, 0, 6, null);
    }

    @JvmStatic
    public static final ApplicationInfo getApplicationInfo(Context context, String str) {
        return getApplicationInfo$default(context, str, 0, 4, null);
    }

    @JvmStatic
    public static final ApplicationInfo getApplicationInfo(Context context, String packageName, int flag) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = packageName;
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            return context.getPackageManager().getApplicationInfo(packageName, flag);
        } catch (PackageManager.NameNotFoundException unused) {
            ZLog.e("App[ZZ]", "没有找到应用信息 : " + packageName, new Object[0]);
            return null;
        }
    }

    public static ApplicationInfo getApplicationInfo$default(Context context, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = context.getPackageName();
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return getApplicationInfo(context, str, i);
    }

    @JvmStatic
    public static final List<ApplicationInfo> getInstalledApplications(Context context, int flag) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(flag);
        Intrinsics.checkNotNullExpressionValue(installedApplications, "context.packageManager.g…stalledApplications(flag)");
        return installedApplications;
    }

    public static List getInstalledApplications$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return getInstalledApplications(context, i);
    }

    @JvmStatic
    public static final List<PackageInfo> getInstalledPackages(Context context, int flag) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(flag);
        Intrinsics.checkNotNullExpressionValue(installedPackages, "context.packageManager.getInstalledPackages(flag)");
        return installedPackages;
    }

    public static List getInstalledPackages$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return getInstalledPackages(context, i);
    }

    @JvmStatic
    public static final String getNameForUid(Context context, int uid) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getPackageManager().getNameForUid(uid);
    }

    @JvmStatic
    public static final PackageInfo getPackageArchiveInfo(Context context, String fileName) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = fileName;
        if (str == null || str.length() == 0) {
            return null;
        }
        return context.getPackageManager().getPackageArchiveInfo(fileName, 0);
    }

    @Deprecated(message = " use getPackageInfo with ContextWrapper")
    @JvmStatic
    public static final PackageInfo getPackageInfo(final Context context, final String packageName, int flag) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = packageName;
        if (str == null || str.length() == 0) {
            return null;
        }
        return INSTANCE.getPackageInfo(new ContextWrapper(context) {
            @Override
            public String getPackageName() {
                return packageName;
            }
        }, flag);
    }

    public static PackageInfo getPackageInfo$default(Context context, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = (String) null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return getPackageInfo(context, str, i);
    }

    public static PackageInfo getPackageInfo$default(AppUtil appUtil, Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return appUtil.getPackageInfo(context, i);
    }

    @JvmStatic
    public static final String[] getPackagesForUid(Context context, int uid) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getPackageManager().getPackagesForUid(uid);
    }

    @JvmStatic
    public static final Debug.MemoryInfo[] getProcessMemoryInfo(Context context, int[] pids) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pids, "pids");
        return getActivityManager(context).getProcessMemoryInfo(pids);
    }

    @Deprecated(message = "")
    @JvmStatic
    public static final void install(Context context, String filePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        File file = new File(filePath);
        if (!file.exists()) {
            ZLog.e("App[ZZ]", filePath + " isn't exists", new Object[0]);
            return;
        }
        ZLog.i("App[ZZ]", "准备安装 " + filePath, new Object[0]);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    @JvmStatic
    public static final boolean installApkSilent(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        ZLog.i("App[ZZ]", "开始静默安装 %s", filePath);
        if (ZCommand.isSuccess(ZCommand.execRootCmdForRes("pm install -r " + filePath))) {
            ZLog.i("App[ZZ]", "静默安装成功!", new Object[0]);
            return true;
        }
        ZLog.i("App[ZZ]", "静默安装失败!", new Object[0]);
        return false;
    }

    @JvmStatic
    public static final boolean isActivityExist(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (intent == null || context.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) ? false : true;
    }

    @JvmStatic
    public static final boolean isAppRunning(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return isInstalled(context, packageName) && (!getAppProcess(context, packageName).isEmpty() || getAppPid(packageName) > 0);
    }

    @JvmStatic
    public static final boolean isInstalled(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return getApplicationInfo$default(context, packageName, 0, 4, null) != null;
    }

    @JvmStatic
    public static final boolean isSystemApp(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        ComponentName component = intent.getComponent();
        String packageName = (String) null;
        if (component == null) {
            ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
            if ((resolveInfoResolveActivity != null ? resolveInfoResolveActivity.activityInfo : null) != null) {
                packageName = resolveInfoResolveActivity.activityInfo.packageName;
            }
        } else {
            packageName = component.getPackageName();
        }
        return isSystemApp(context, packageName);
    }

    @JvmStatic
    public static final boolean isSystemApp(Context context, String packageName) {
        PackageInfo packageInfo$default;
        Intrinsics.checkNotNullParameter(context, "context");
        if (TextUtils.isEmpty(packageName) || (packageInfo$default = getPackageInfo$default(context, packageName, 0, 4, (Object) null)) == null) {
            return false;
        }
        return isSystemApp(packageInfo$default.applicationInfo);
    }

    @JvmStatic
    public static final boolean isSystemApp(ApplicationInfo applicationInfo) {
        return (applicationInfo == null || (applicationInfo.flags & 1) == 0) ? false : true;
    }

    @JvmStatic
    public static final void killAppProcess(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        getActivityManager(context).killBackgroundProcesses(packageName);
        Iterator<ActivityManager.RunningAppProcessInfo> it = getAppProcess(context, packageName).iterator();
        while (it.hasNext()) {
            Process.killProcess(it.next().pid);
        }
    }

    @JvmStatic
    public static final List<ResolveInfo> queryMainIntentActivities(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(packageName);
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        Intrinsics.checkNotNullExpressionValue(listQueryIntentActivities, "context.packageManager.q…CH_DEFAULT_ONLY\n        )");
        return listQueryIntentActivities;
    }

    @JvmStatic
    public static final boolean startApplication(Context context, String str) {
        return startApplication$default(context, str, null, false, 12, null);
    }

    @JvmStatic
    public static final boolean startApplication(Context context, String str, Bundle bundle) {
        return startApplication$default(context, str, bundle, false, 8, null);
    }

    @JvmStatic
    public static final boolean startApplication(Context context, String packageName, Bundle bundle, boolean needToast) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(268435456);
            launchIntentForPackage.addFlags(2097152);
            context.startActivity(launchIntentForPackage, bundle);
            return true;
        }
        if (needToast) {
            if (getPackageInfo$default(context, packageName, 0, 4, (Object) null) != null) {
                ToastUtil.toast(context, '(' + packageName + ")未安装!");
            } else {
                ToastUtil.toast(context, '(' + packageName + ")不可直接打开!");
            }
        }
        return false;
    }

    public static boolean startApplication$default(Context context, String str, Bundle bundle, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            bundle = (Bundle) null;
        }
        if ((i & 8) != 0) {
            z = true;
        }
        return startApplication(context, str, bundle, z);
    }

    @Deprecated(message = "")
    @JvmStatic
    public static final void unInstall(Context context) {
        unInstall$default(context, null, 2, null);
    }

    @Deprecated(message = "")
    @JvmStatic
    public static final void unInstall(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        ZLog.i("App[ZZ]", "开始卸载 " + packageName, new Object[0]);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + packageName));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void unInstall$default(Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(str, "context.packageName");
        }
        unInstall(context, str);
    }

    @JvmStatic
    public static final boolean unInstallApkSilent(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        ZLog.i("App[ZZ]", "开始静默卸载 %s", packageName);
        if (ZCommand.isSuccess(ZCommand.execRootCmdForRes("pm uninstall " + packageName))) {
            ZLog.i("App[ZZ]", "静默卸载成功!", new Object[0]);
            return true;
        }
        ZLog.i("App[ZZ]", "静默卸载失败!", new Object[0]);
        return false;
    }

    public final Bundle getAppMetaData(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            return context.getPackageManager().getApplicationInfo(packageName, 128).metaData;
        } catch (Exception unused) {
            return null;
        }
    }

    public final PackageInfo getPackageInfo(Context context, int flag) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), flag);
        } catch (PackageManager.NameNotFoundException unused) {
            ZLog.e("Debug[ZZ]", "PackageManager.NameNotFoundException : " + context.getPackageName(), new Object[0]);
            return null;
        }
    }
}
