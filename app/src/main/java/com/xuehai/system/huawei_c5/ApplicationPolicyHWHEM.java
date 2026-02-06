package com.xuehai.system.huawei_c5;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.content.FileProvider;
import com.huawei.android.app.admin.DeviceApplicationManager;
import com.huawei.android.app.admin.DeviceControlManager;
import com.huawei.android.app.admin.DeviceHwSystemManager;
import com.huawei.android.app.admin.DevicePackageManager;
import com.xuehai.system.common.compat.ApplicationPolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0000\u0018\u0000 H2\u00020\u0001:\u0001HB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0016\u0010\u0012\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0016\u0010\u0016\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0016\u0010\u0017\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u001e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00112\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u000eH\u0016J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\b\u0010\u001e\u001a\u00020\u000eH\u0016J\b\u0010\u001f\u001a\u00020\u000eH\u0016J\b\u0010 \u001a\u00020\u000eH\u0016J\u001e\u0010!\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u00112\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\b\u0010#\u001a\u00020\u0011H\u0016J\u0014\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110%H\u0002J\u000e\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\n\u0010)\u001a\u0004\u0018\u00010\u0011H\u0016J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0010\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u0011H\u0016J\u0018\u0010-\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u0011H\u0002J\u0012\u0010/\u001a\u00020\u000e2\b\u00100\u001a\u0004\u0018\u000101H\u0002J\u0016\u00102\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0018\u00103\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u0011H\u0016J\u0016\u00104\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0010\u00105\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0016\u00106\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0016\u00107\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0016\u00108\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0018\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u000eH\u0016J\u0010\u0010<\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0010\u0010=\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0010\u0010>\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020\u0011H\u0016J\u0018\u0010@\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u0011H\u0016J\u0018\u0010A\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u0011H\u0016J\u0010\u0010B\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0010\u0010C\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0016\u0010D\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0010\u0010E\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0010\u0010F\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0010\u0010G\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/xuehai/system/huawei_c5/ApplicationPolicyHWHEM;", "Lcom/xuehai/system/common/compat/ApplicationPolicyDefault;", "context", "Landroid/content/Context;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/Context;Landroid/content/ComponentName;)V", "applicationManager", "Lcom/huawei/android/app/admin/DeviceApplicationManager;", "deviceControlManager", "Lcom/huawei/android/app/admin/DeviceControlManager;", "devicePackageManager", "Lcom/huawei/android/app/admin/DevicePackageManager;", "addAppAlwaysRunningList", "", "packageList", "", "", "addIgnoreFrequentRelaunchAppList", "packageNames", "addPackageToBatteryOptimizationWhiteList", "packageName", "addPackagesToNotificationWhiteList", "addRuntimePermissionFixAppList", "applyRuntimePermissions", "runtimePermissions", "clearApplicationUninstallRule", "", "clearComponentLaunchedByLauncher", "clearIgnoreFrequentRelaunchAppList", "clearPackagesFromNotificationList", "clearRuntimePermissionFixAppList", "clearTrustListForSystemManger", "findFloatWindowPermission", "getAppAlwaysRunningList", "getComponentLaunchedByLauncher", "getDefaultHome", "Lkotlin/Pair;", "getIgnoreFrequentRelaunchAppList", "getPackagesFromBatteryOptimizationWhiteList", "getRuntimePermissionFixAppList", "getTopAppPackageName", "getTrustListForSystemManger", "installApplication", "apkFilePath", "isLauncherDefault", "className", "isRuntimePermission", "permissionInfo", "Landroid/content/pm/PermissionInfo;", "removeAppAlwaysRunningList", "removeDefaultLauncher", "removeIgnoreFrequentRelaunchAppList", "removePackageFromBatteryOptimizationWhiteList", "removePackagesFromNotificationWhiteList", "removeRuntimePermissionFixAppList", "removeTrustListForSystemManger", "setApplicationComponentState", "cmpName", "state", "setApplicationUninstallationDisabled", "setApplicationUninstallationEnabled", "setComponentLaunchedByLauncher", "launchComponent", "setDefaultHome", "setDefaultLauncher", "setDisableApplication", "setEnableApplication", "setTrustListForSystemManger", "stopApp", "uninstallApplication", "wipeApplicationData", "Companion", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ApplicationPolicyHWHEM extends ApplicationPolicyDefault {
    public static final String HUAWEI_LAUNCHER = "com.huawei.android.launcher";
    private static final String TAG = "ApplicationPolicyHWHEM";
    private final DeviceApplicationManager applicationManager;
    private final ComponentName componentName;
    private final Context context;
    private final DeviceControlManager deviceControlManager;
    private final DevicePackageManager devicePackageManager;

    public ApplicationPolicyHWHEM(Context context, ComponentName componentName) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.context = context;
        this.componentName = componentName;
        this.devicePackageManager = new DevicePackageManager();
        this.applicationManager = new DeviceApplicationManager();
        this.deviceControlManager = new DeviceControlManager();
    }

    private final void findFloatWindowPermission(String packageName, List<String> runtimePermissions) {
        Object obj;
        Iterator<T> it = runtimePermissions.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            String str = (String) next;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "android.permission.SYSTEM_ALERT_WINDOW", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "android.permission.SYSTEM_OVERLAY_WINDOW", false, 2, (Object) null)) {
                obj = next;
                break;
            }
        }
        if (((String) obj) != null) {
            MdmLog.i("ApplicationPolicyHWHEM", "添加应用到可信任列表中授予悬浮窗等特殊权限：" + packageName);
            setTrustListForSystemManger(CollectionsKt.arrayListOf(packageName));
        }
    }

    private final Pair<String, String> getDefaultHome() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = this.context.getPackageManager().resolveActivity(intent, 0);
        if ((resolveInfoResolveActivity != null ? resolveInfoResolveActivity.activityInfo : null) == null) {
            return new Pair<>("", "");
        }
        ActivityInfo activityInfo = resolveInfoResolveActivity.activityInfo;
        String str = activityInfo != null ? activityInfo.packageName : null;
        if (str == null) {
            str = "";
        }
        return Intrinsics.areEqual(str, "android") ? new Pair<>("", "") : new Pair<>(str, resolveInfoResolveActivity.activityInfo.name);
    }

    private final boolean isLauncherDefault(String packageName, String className) {
        ActivityInfo activityInfo;
        ActivityInfo activityInfo2;
        if (packageName.length() == 0) {
            if (className.length() == 0) {
                return false;
            }
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = this.context.getPackageManager().resolveActivity(intent, 65536);
        String str = null;
        String str2 = (resolveInfoResolveActivity == null || (activityInfo2 = resolveInfoResolveActivity.activityInfo) == null) ? null : activityInfo2.packageName;
        if (str2 == null) {
            str2 = "";
        }
        if (resolveInfoResolveActivity != null && (activityInfo = resolveInfoResolveActivity.activityInfo) != null) {
            str = activityInfo.name;
        }
        return Intrinsics.areEqual(str2, packageName) && Intrinsics.areEqual(str != null ? str : "", className);
    }

    private final boolean isRuntimePermission(PermissionInfo permissionInfo) {
        return (permissionInfo == null || (permissionInfo.protectionLevel & 1) == 0) ? false : true;
    }

    @Override
    public boolean addAppAlwaysRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        this.applicationManager.addPersistentApp(this.componentName, CollectionsKt.take(packageList, 10));
        return true;
    }

    @Override
    public boolean addIgnoreFrequentRelaunchAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return new DeviceApplicationManager().addIgnoreFrequentRelaunchAppList(this.componentName, new ArrayList(packageNames));
    }

    @Override
    public boolean addPackageToBatteryOptimizationWhiteList(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return setTrustListForSystemManger(CollectionsKt.arrayListOf(packageName));
    }

    @Override
    public boolean addPackagesToNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        this.devicePackageManager.addAllowNotificationApps(this.componentName, new ArrayList(packageList));
        return setTrustListForSystemManger(packageList);
    }

    @Override
    public boolean addRuntimePermissionFixAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return new DeviceApplicationManager().addRuntimePermissionFixAppList(this.componentName, new ArrayList(packageNames));
    }

    @Override
    public boolean applyRuntimePermissions(String packageName, List<String> runtimePermissions) {
        Object objM228constructorimpl;
        Object objM228constructorimpl2;
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(runtimePermissions, "runtimePermissions");
        findFloatWindowPermission(packageName, runtimePermissions);
        if (Build.VERSION.SDK_INT >= 23) {
            Object systemService = this.context.getApplicationContext().getSystemService("device_policy");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.admin.DevicePolicyManager");
            }
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) systemService;
            PackageManager packageManager = this.context.getPackageManager();
            for (String str : runtimePermissions) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM228constructorimpl = Result.m228constructorimpl(packageManager.getPermissionInfo(str, 0));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM228constructorimpl = Result.m228constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m231exceptionOrNullimpl(objM228constructorimpl) != null) {
                    objM228constructorimpl = null;
                }
                if (isRuntimePermission((PermissionInfo) objM228constructorimpl)) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM228constructorimpl2 = Result.m228constructorimpl(Boolean.valueOf(devicePolicyManager.setPermissionGrantState(this.componentName, packageName, str, 1)));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM228constructorimpl2 = Result.m228constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m231exceptionOrNullimpl(objM228constructorimpl2) != null) {
                        objM228constructorimpl2 = false;
                    }
                    ((Boolean) objM228constructorimpl2).booleanValue();
                }
            }
        }
        return true;
    }

    @Override
    public void clearApplicationUninstallRule() {
        List disallowedUninstallPackageList = this.devicePackageManager.getDisallowedUninstallPackageList(this.componentName);
        List list = disallowedUninstallPackageList;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.devicePackageManager.removeDisallowedUninstallPackages(this.componentName, disallowedUninstallPackageList);
    }

    @Override
    public boolean clearComponentLaunchedByLauncher() {
        return this.applicationManager.clearComponentLaunchedByLauncher(this.componentName);
    }

    @Override
    public boolean clearIgnoreFrequentRelaunchAppList() {
        return removeIgnoreFrequentRelaunchAppList(getIgnoreFrequentRelaunchAppList());
    }

    @Override
    public boolean clearPackagesFromNotificationList() {
        return removePackagesFromNotificationWhiteList(getTrustListForSystemManger());
    }

    @Override
    public boolean clearRuntimePermissionFixAppList() {
        return removeRuntimePermissionFixAppList(getRuntimePermissionFixAppList());
    }

    @Override
    public boolean clearTrustListForSystemManger() {
        return removeTrustListForSystemManger(getTrustListForSystemManger());
    }

    @Override
    public List<String> getAppAlwaysRunningList() {
        List<String> persistentApp = this.applicationManager.getPersistentApp(this.componentName);
        Intrinsics.checkNotNullExpressionValue(persistentApp, "applicationManager.getPersistentApp(componentName)");
        return persistentApp;
    }

    @Override
    public String getComponentLaunchedByLauncher() {
        String componentLaunchedByLauncher = this.applicationManager.getComponentLaunchedByLauncher(this.componentName);
        Intrinsics.checkNotNullExpressionValue(componentLaunchedByLauncher, "applicationManager.getCo…ByLauncher(componentName)");
        return componentLaunchedByLauncher;
    }

    @Override
    public List<String> getIgnoreFrequentRelaunchAppList() {
        ArrayList ignoreFrequentRelaunchAppList = new DeviceApplicationManager().getIgnoreFrequentRelaunchAppList(this.componentName);
        Intrinsics.checkNotNullExpressionValue(ignoreFrequentRelaunchAppList, "DeviceApplicationManager…nchAppList(componentName)");
        return ignoreFrequentRelaunchAppList;
    }

    @Override
    public List<String> getPackagesFromBatteryOptimizationWhiteList() {
        return getTrustListForSystemManger();
    }

    @Override
    public List<String> getRuntimePermissionFixAppList() {
        ArrayList runtimePermissionFixAppList = new DeviceApplicationManager().getRuntimePermissionFixAppList(this.componentName);
        Intrinsics.checkNotNullExpressionValue(runtimePermissionFixAppList, "DeviceApplicationManager…FixAppList(componentName)");
        return runtimePermissionFixAppList;
    }

    @Override
    public String getTopAppPackageName() {
        return this.applicationManager.getTopAppPackageName(this.componentName);
    }

    @Override
    public List<String> getTrustListForSystemManger() {
        ArrayList superTrustListForHwSystemManger = new DeviceHwSystemManager().getSuperTrustListForHwSystemManger(this.componentName);
        Intrinsics.checkNotNullExpressionValue(superTrustListForHwSystemManger, "DeviceHwSystemManager().…stemManger(componentName)");
        return superTrustListForHwSystemManger;
    }

    @Override
    public boolean installApplication(String apkFilePath) {
        Intrinsics.checkNotNullParameter(apkFilePath, "apkFilePath");
        File file = new File(apkFilePath);
        final File file2 = new File(this.context.getCacheDir(), file.getName());
        MdmLog.i("ApplicationPolicyHWHEM", "静默安装 toFile = " + file2);
        if (!file2.getAbsoluteFile().exists()) {
            file2.getAbsoluteFile().mkdirs();
        }
        if (file2.exists()) {
            file2.delete();
        }
        FilesKt.copyTo$default(file, file2, false, 0, 6, null);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle extras;
                MdmLog.i("ApplicationPolicyHWHEM", "静默安装广播接收器");
                if (intent == null || (extras = intent.getExtras()) == null) {
                    return;
                }
                String string = extras.getString("packageName");
                int i = extras.getInt("returnCode");
                if (i == 0) {
                    if ((Intrinsics.areEqual(string, "com.xuehai.launcher") || Intrinsics.areEqual(string, "com.xuehai.response_launcher_teacher")) && context != null) {
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            Context contextCreateDeviceProtectedStorageContext = Build.VERSION.SDK_INT >= 24 ? context.createDeviceProtectedStorageContext() : context;
                            Intrinsics.checkNotNullExpressionValue(contextCreateDeviceProtectedStorageContext, "ctx");
                            new HuaweiHemLicenseState(contextCreateDeviceProtectedStorageContext).setLicenseState(false);
                            Result.m228constructorimpl(Unit.INSTANCE);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            Result.m228constructorimpl(ResultKt.createFailure(th));
                        }
                    }
                    MdmLog.i("ApplicationPolicyHWHEM", "静默安装" + string + "成功");
                } else {
                    MdmLog.i("ApplicationPolicyHWHEM", "静默安装" + string + "失败，installStatus = " + i);
                }
                file2.delete();
                if (context != null) {
                    context.unregisterReceiver(this);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.intent.action.InstallMdmPackage");
        this.context.registerReceiver(broadcastReceiver, intentFilter);
        Uri uriForFile = FileProvider.getUriForFile(this.context, this.context.getPackageName() + ".mdm.fileProvider", file2);
        this.context.grantUriPermission("android", uriForFile, 1);
        String string = uriForFile.toString();
        Intrinsics.checkNotNullExpressionValue(string, "uri.toString()");
        MdmLog.i("ApplicationPolicyHWHEM", "静默安装应用，filePath = " + string);
        this.devicePackageManager.installPackage(this.componentName, string);
        return true;
    }

    @Override
    public boolean removeAppAlwaysRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        this.applicationManager.removePersistentApp(this.componentName, packageList);
        return true;
    }

    @Override
    public boolean removeDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        this.deviceControlManager.clearDefaultLauncher(this.componentName);
        return true;
    }

    @Override
    public boolean removeIgnoreFrequentRelaunchAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return new DeviceApplicationManager().removeIgnoreFrequentRelaunchAppList(this.componentName, new ArrayList(packageNames));
    }

    @Override
    public boolean removePackageFromBatteryOptimizationWhiteList(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return removeTrustListForSystemManger(CollectionsKt.arrayListOf(packageName));
    }

    @Override
    public boolean removePackagesFromNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        this.devicePackageManager.removeAllowNotificationApps(this.componentName, new ArrayList(packageList));
        return removeTrustListForSystemManger(packageList);
    }

    @Override
    public boolean removeRuntimePermissionFixAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return new DeviceApplicationManager().removeRuntimePermissionFixAppList(this.componentName, new ArrayList(packageNames));
    }

    @Override
    public boolean removeTrustListForSystemManger(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return new DeviceHwSystemManager().removeSuperTrustListForHwSystemManger(this.componentName, new ArrayList(packageNames));
    }

    @Override
    public boolean setApplicationComponentState(ComponentName cmpName, boolean state) {
        Intrinsics.checkNotNullParameter(cmpName, "cmpName");
        PackageManager packageManager = this.context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        packageManager.setComponentEnabledSetting(cmpName, state ? 1 : 2, 0);
        return true;
    }

    @Override
    public void setApplicationUninstallationDisabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.devicePackageManager.addDisallowedUninstallPackages(this.componentName, CollectionsKt.listOf(packageName));
    }

    @Override
    public void setApplicationUninstallationEnabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.devicePackageManager.removeDisallowedUninstallPackages(this.componentName, CollectionsKt.listOf(packageName));
    }

    @Override
    public boolean setComponentLaunchedByLauncher(String launchComponent) {
        Intrinsics.checkNotNullParameter(launchComponent, "launchComponent");
        return this.applicationManager.setComponentLaunchedByLauncher(this.componentName, launchComponent);
    }

    @Override
    public void setDefaultHome(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        setDefaultLauncher(packageName, className);
    }

    @Override
    public boolean setDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        if (isLauncherDefault(packageName, className)) {
            MdmLog.d("ApplicationPolicyHWHEM", packageName + '/' + className + "，已设置为我们的MDM为默认桌面，不再重复设置");
            try {
                Result.Companion companion = Result.INSTANCE;
                Result.m228constructorimpl(Boolean.valueOf(stopApp("com.huawei.android.launcher")));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m228constructorimpl(ResultKt.createFailure(th));
            }
            return true;
        }
        Pair<String, String> defaultHome = getDefaultHome();
        MdmLog.d("ApplicationPolicyHWHEM", "当前的默认桌面为：{" + defaultHome.getFirst() + '/' + defaultHome.getSecond() + "}，" + packageName + '/' + className + "，还未设置为我们的MDM为默认桌面，调用API设置为默认桌面");
        removeDefaultLauncher(packageName, className);
        this.deviceControlManager.setDefaultLauncher(this.componentName, packageName, className);
        try {
            Result.Companion companion3 = Result.INSTANCE;
            if (!Intrinsics.areEqual("com.huawei.android.launcher", packageName)) {
                stopApp("com.huawei.android.launcher");
            }
            Result.m228constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m228constructorimpl(ResultKt.createFailure(th2));
        }
        return true;
    }

    @Override
    public boolean setDisableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            PackageManager packageManager = this.context.getPackageManager();
            if (packageManager != null) {
                packageManager.setApplicationEnabledSetting(packageName, 2, 0);
            }
        } catch (Throwable th) {
            MdmLog.e("ApplicationPolicyHWHEM", "设置华为禁用应用功能出现异常", th);
        }
        this.applicationManager.addDisallowedRunningApp(this.componentName, CollectionsKt.listOf(packageName));
        return true;
    }

    @Override
    public boolean setEnableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            PackageManager packageManager = this.context.getPackageManager();
            if (packageManager != null) {
                packageManager.setApplicationEnabledSetting(packageName, 1, 0);
            }
        } catch (Throwable th) {
            MdmLog.e("ApplicationPolicyHWHEM", "设置华为启用应用功能出现异常", th);
        }
        this.applicationManager.removeDisallowedRunningApp(this.componentName, CollectionsKt.listOf(packageName));
        return true;
    }

    @Override
    public boolean setTrustListForSystemManger(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return new DeviceHwSystemManager().setSuperTrustListForHwSystemManger(this.componentName, new ArrayList(packageNames));
    }

    @Override
    public boolean stopApp(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.applicationManager.killApplicationProcess(this.componentName, packageName);
        return true;
    }

    @Override
    public boolean uninstallApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.devicePackageManager.uninstallPackage(this.componentName, packageName, false);
        return true;
    }

    @Override
    public boolean wipeApplicationData(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.devicePackageManager.clearPackageData(this.componentName, packageName);
        return true;
    }
}
