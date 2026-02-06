package com.xuehai.launcher.common.util.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.system.common.log.MdmLog;
import com.zaze.utils.AppUtil;
import com.zaze.utils.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t2\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u001a\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0007J\u0010\u0010\u0015\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005J \u0010\u001b\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u0007J \u0010\u001b\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u0007¨\u0006\u001f"}, d2 = {"Lcom/xuehai/launcher/common/util/app/ApplicationUtil;", "", "()V", "getInstalledAppPackage", "", "", "flag", "", "getInstalledApplications", "", "Landroid/content/pm/ApplicationInfo;", "getInstalledPackages", "Landroid/content/pm/PackageInfo;", "getPackageArchiveInfo", "context", "Landroid/content/Context;", "file", "Ljava/io/File;", "isAppInstalled", "", "packageName", "isSystemApp", "killProcess", "", "readAssetsApkToFile", "assetsApk", "copyPath", "startApp", "intent", "Landroid/content/Intent;", "requestCode", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ApplicationUtil {
    public static final ApplicationUtil INSTANCE = new ApplicationUtil();

    private ApplicationUtil() {
    }

    public static Set getInstalledAppPackage$default(ApplicationUtil applicationUtil, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return applicationUtil.getInstalledAppPackage(i);
    }

    public static List getInstalledApplications$default(ApplicationUtil applicationUtil, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return applicationUtil.getInstalledApplications(i);
    }

    public static List getInstalledPackages$default(ApplicationUtil applicationUtil, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return applicationUtil.getInstalledPackages(i);
    }

    public static PackageInfo getPackageArchiveInfo$default(ApplicationUtil applicationUtil, Context context, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            context = BaseApplication.INSTANCE.getInstance();
        }
        return applicationUtil.getPackageArchiveInfo(context, file);
    }

    @JvmStatic
    public static final boolean isAppInstalled(String packageName) {
        BaseApplication baseApplicationCreateDeviceProtectedStorageContext = Build.VERSION.SDK_INT >= 24 ? BaseApplication.INSTANCE.getInstance().createDeviceProtectedStorageContext() : BaseApplication.INSTANCE.getInstance();
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        Intrinsics.checkNotNullExpressionValue(baseApplicationCreateDeviceProtectedStorageContext, "ctx");
        Intrinsics.checkNotNull(packageName);
        return AppUtil.isInstalled(baseApplicationCreateDeviceProtectedStorageContext, packageName);
    }

    public static boolean startApp$default(ApplicationUtil applicationUtil, Context context, Intent intent, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = -1;
        }
        return applicationUtil.startApp(context, intent, i);
    }

    public static boolean startApp$default(ApplicationUtil applicationUtil, Context context, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = -1;
        }
        return applicationUtil.startApp(context, str, i);
    }

    public final Set<String> getInstalledAppPackage(int flag) {
        List<ApplicationInfo> installedApplications = getInstalledApplications(flag);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(installedApplications, 10));
        Iterator<T> it = installedApplications.iterator();
        while (it.hasNext()) {
            arrayList.add(((ApplicationInfo) it.next()).packageName);
        }
        return CollectionsKt.toSet(arrayList);
    }

    public final List<ApplicationInfo> getInstalledApplications(int flag) {
        List<ApplicationInfo> installedApplications = BaseApplication.INSTANCE.getInstance().getPackageManager().getInstalledApplications(flag);
        Intrinsics.checkNotNullExpressionValue(installedApplications, "BaseApplication.getInsta…stalledApplications(flag)");
        return installedApplications;
    }

    public final List<PackageInfo> getInstalledPackages(int flag) {
        List<PackageInfo> installedPackages = BaseApplication.INSTANCE.getInstance().getPackageManager().getInstalledPackages(flag);
        Intrinsics.checkNotNullExpressionValue(installedPackages, "BaseApplication.getInsta…etInstalledPackages(flag)");
        return installedPackages;
    }

    public final PackageInfo getPackageArchiveInfo(Context context, File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        return AppUtil.getPackageArchiveInfo(context, file.getAbsolutePath());
    }

    public final boolean isSystemApp(String packageName) {
        return AppUtil.isSystemApp(BaseApplication.INSTANCE.getInstance(), packageName);
    }

    public final void killProcess() {
        BaseApplication companion = BaseApplication.INSTANCE.getInstance();
        String packageName = BaseApplication.INSTANCE.getInstance().getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "BaseApplication.getInstance().packageName");
        AppUtil.killAppProcess(companion, packageName);
    }

    public final boolean readAssetsApkToFile(Context context, String assetsApk, String copyPath) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetsApk, "assetsApk");
        Intrinsics.checkNotNullParameter(copyPath, "copyPath");
        FileUtil.reCreateFile(copyPath);
        try {
            MdmLog.i("Debug[MDM]", "readAssetsApkToFile : " + copyPath);
            File file = new File(copyPath);
            InputStream inputStreamOpen = context.getAssets().open(assetsApk);
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(assetsApk)");
            FileUtil.writeToFile(file, inputStreamOpen, false);
            MdmLog.i("Debug[MDM]", "readAssetsApkToFile : " + copyPath + " end");
            return true;
        } catch (IOException e) {
            MyLog.w("Application[MDM]", "readAssetsApkToFile error", e);
            return false;
        }
    }

    public final boolean startApp(Context context, Intent intent, int requestCode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        try {
            intent.addFlags(270532608);
            if (!(context instanceof Activity) || requestCode <= 0) {
                context.startActivity(intent);
            } else {
                ((Activity) context).startActivityForResult(intent, requestCode);
            }
            return true;
        } catch (Exception e) {
            MyLog.w("Application[MDM]", "startApp failed->", e);
            return false;
        }
    }

    public final boolean startApp(Context context, String packageName, int requestCode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(packageName);
            if (launchIntentForPackage != null) {
                return INSTANCE.startApp(context, launchIntentForPackage, requestCode);
            }
            return false;
        } catch (Exception e) {
            MyLog.w("Application[MDM]", "startApp failed", e);
            return false;
        }
    }
}
