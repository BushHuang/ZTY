package com.xuehai.system.huawei_c5;

import android.app.admin.DevicePolicyManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import com.huawei.android.app.admin.DeviceApplicationManager;
import com.huawei.android.app.admin.DeviceCameraManager;
import com.huawei.android.app.admin.DeviceControlManager;
import com.huawei.android.app.admin.DeviceRestrictionManager;
import com.huawei.android.app.admin.DeviceSettingsManager;
import com.xuehai.system.common.compat.RestrictionPolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0000\u0018\u0000 62\u00020\u0001:\u00016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0018\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\t0 j\b\u0012\u0004\u0012\u00020\t`!H\u0002J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\b\u0010#\u001a\u00020\u0010H\u0016J\b\u0010$\u001a\u00020\u0010H\u0016J\b\u0010%\u001a\u00020\u0010H\u0016J\b\u0010&\u001a\u00020\u0010H\u0016J\b\u0010'\u001a\u00020\u0010H\u0016J\b\u0010(\u001a\u00020\u0010H\u0016J\b\u0010)\u001a\u00020\u0010H\u0002J\b\u0010*\u001a\u00020\u0010H\u0016J\b\u0010+\u001a\u00020\u0010H\u0016J\u0010\u0010,\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u0010-\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u0010.\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\tH\u0016J\u0010\u00101\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u00102\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u00103\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u00104\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\b\u00105\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u00067"}, d2 = {"Lcom/xuehai/system/huawei_c5/RestrictionPolicyHWHEM;", "Lcom/xuehai/system/common/compat/RestrictionPolicyDefault;", "context", "Landroid/content/Context;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/Context;Landroid/content/ComponentName;)V", "platformLevelApps", "", "", "kotlin.jvm.PlatformType", "getPlatformLevelApps", "()Ljava/util/Set;", "platformLevelApps$delegate", "Lkotlin/Lazy;", "allowFactoryReset", "", "allow", "allowFirmwareRecovery", "allowHardwareKey", "keyCode", "", "allowMultiWindowMode", "allowMultipleUsers", "allowOTAUpgrade", "allowOTG", "enable", "allowSafeMode", "allowStatusBarExpansion", "allowSystemAccountLogin", "getInstalledLauncherPackages", "getSystemFilterPackage", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "getUsageStatsPackages", "isClipboardAllowed", "isFactoryResetAllowed", "isFirmwareRecoveryAllowed", "isMTPAvailable", "isSafeModeAllow", "isSdCardEnabled", "isUSBOtgDisabled", "isUsbDebuggingEnabled", "multipleUsersAllowed", "setCameraState", "setClipboardEnabled", "setFileShareDisabled", "setInputMethod", "inputMethodClassName", "setMTPEnabled", "setScreenCapture", "setSdCardState", "setUsbDebuggingEnabled", "wipeRecentTasks", "Companion", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RestrictionPolicyHWHEM extends RestrictionPolicyDefault {
    private static final String TAG = "RestrictionPolicyHWHEM";
    private final ComponentName componentName;
    private final Context context;

    private final Lazy platformLevelApps;

    public RestrictionPolicyHWHEM(Context context, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.context = context;
        this.componentName = componentName;
        this.platformLevelApps = LazyKt.lazy(new Function0<Set<? extends String>>() {
            {
                super(0);
            }

            @Override
            public final Set<? extends String> invoke() {
                return SetsKt.setOf((Object[]) new String[]{this.this$0.context.getPackageName(), "com.xh.zhitongyunstu", "com.xh.zhitongyuntch", "com.xuehai.xuehailauncher_tea", "com.xuehai.xuehailauncher", "com.xuehai.launcher", "com.xuehai.response_launcher_teacher", "com.xh.zhitongyuntv"});
            }
        });
    }

    private final Set<String> getInstalledLauncherPackages() {
        Intent intent = new Intent();
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setAction("android.intent.action.MAIN");
        List<ResolveInfo> listQueryIntentActivities = this.context.getPackageManager().queryIntentActivities(intent, Build.VERSION.SDK_INT >= 23 ? 131072 : 0);
        Intrinsics.checkNotNullExpressionValue(listQueryIntentActivities, "manager.queryIntentActivities(intent, flag)");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = listQueryIntentActivities.iterator();
        while (it.hasNext()) {
            String str = ((ResolveInfo) it.next()).activityInfo.applicationInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "it.activityInfo.applicationInfo.packageName");
            linkedHashSet.add(str);
        }
        return linkedHashSet;
    }

    private final Set<String> getPlatformLevelApps() {
        return (Set) this.platformLevelApps.getValue();
    }

    private final HashSet<String> getSystemFilterPackage() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        String[] stringArray = this.context.getResources().getStringArray(R.array.hw_keep_system_apps);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…rray.hw_keep_system_apps)");
        CollectionsKt.addAll(hashSet, stringArray);
        return hashSet;
    }

    private final Set<String> getUsageStatsPackages() throws Resources.NotFoundException {
        Object systemService;
        if (Build.VERSION.SDK_INT < 22) {
            return SetsKt.emptySet();
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - 604800000;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        HashSet<String> systemFilterPackage = getSystemFilterPackage();
        try {
            systemService = this.context.getSystemService("usagestats");
        } catch (Throwable th) {
            th.printStackTrace();
            MdmLog.e("RestrictionPolicyHWHEM", "查询使用数据关闭应用异常", th);
        }
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.usage.UsageStatsManager");
        }
        for (UsageStats usageStats : ((UsageStatsManager) systemService).queryUsageStats(0, j, jCurrentTimeMillis)) {
            String packageName = usageStats.getPackageName();
            if (usageStats.getTotalTimeInForeground() > 0) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (!StringsKt.startsWith$default(packageName, "android", false, 2, (Object) null) && !StringsKt.startsWith$default(packageName, "android.", false, 2, (Object) null) && !StringsKt.startsWith$default(packageName, "system", false, 2, (Object) null) && !StringsKt.startsWith$default(packageName, "androidhwext", false, 2, (Object) null) && !StringsKt.startsWith$default(packageName, "com.android.", false, 2, (Object) null) && !systemFilterPackage.contains(packageName)) {
                    linkedHashSet.add(packageName);
                }
            }
        }
        linkedHashSet.add("com.android.htmlviewer");
        linkedHashSet.add("com.android.settings");
        return linkedHashSet;
    }

    private final boolean isUSBOtgDisabled() {
        return new DeviceRestrictionManager().isUSBOtgDisabled(this.componentName);
    }

    @Override
    public boolean allowFactoryReset(boolean allow) {
        return new DeviceSettingsManager().setRestoreFactoryDisabled(this.componentName, !allow);
    }

    @Override
    public boolean allowFirmwareRecovery(boolean allow) {
        return new DeviceRestrictionManager().setSdCardUpdateDisabled(this.componentName, !allow);
    }

    @Override
    public boolean allowHardwareKey(int keyCode, boolean allow) {
        DeviceRestrictionManager deviceRestrictionManager = new DeviceRestrictionManager();
        if (keyCode == 3) {
            deviceRestrictionManager.setHomeButtonDisabled(this.componentName, !allow);
            return true;
        }
        if (keyCode == 4) {
            deviceRestrictionManager.setBackButtonDisabled(this.componentName, !allow);
            return true;
        }
        if (keyCode != 187) {
            return false;
        }
        deviceRestrictionManager.setTaskButtonDisabled(this.componentName, !allow);
        return true;
    }

    @Override
    public boolean allowMultiWindowMode(boolean allow) {
        return new DeviceRestrictionManager().setMultiWindowDisabled(this.componentName, !allow);
    }

    @Override
    public boolean allowMultipleUsers(boolean allow) {
        return new DeviceSettingsManager().setAddUserDisabled(this.componentName, !allow);
    }

    @Override
    public boolean allowOTAUpgrade(boolean allow) {
        try {
            PackageManager packageManager = this.context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
            packageManager.setComponentEnabledSetting(new ComponentName("com.huawei.android.hwouc", "com.huawei.android.hwouc.ui.activities.MainEntranceActivity"), allow ? 1 : 2, 0);
        } catch (Throwable th) {
            MdmLog.e("RestrictionPolicyHWHEM", "设置华为C5系统更新调用组件设置失败", th);
        }
        return new DeviceRestrictionManager().setSystemUpdateDisabled(this.componentName, !allow);
    }

    @Override
    public boolean allowOTG(boolean enable) {
        new DeviceRestrictionManager().setUSBOtgDisabled(this.componentName, !enable);
        return isUSBOtgDisabled() == enable;
    }

    @Override
    public boolean allowSafeMode(boolean allow) {
        new DeviceRestrictionManager().setSafeModeDisabled(this.componentName, !allow);
        return isSafeModeAllow() == allow;
    }

    @Override
    public boolean allowStatusBarExpansion(boolean allow) {
        new DeviceRestrictionManager().setStatusBarExpandPanelDisabled(this.componentName, !allow);
        return true;
    }

    @Override
    public boolean allowSystemAccountLogin(boolean allow) {
        ArrayList arrayList = new ArrayList();
        if (!allow) {
            arrayList.add("huawei_id");
        }
        new DeviceControlManager().setCustomSettingsMenu(this.componentName, arrayList);
        PackageManager packageManager = this.context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        int i = allow ? 1 : 2;
        try {
            packageManager.setComponentEnabledSetting(new ComponentName("com.huawei.hwid", "com.huawei.hms.runtimekit.stubexplicit.HwIDStartUpGuideLoginActivity"), i, 0);
        } catch (Throwable th) {
            MdmLog.e("RestrictionPolicyHWHEM", "设置华为C5启用/禁用登录华为账号调用组件设置失败", th);
        }
        try {
            packageManager.setComponentEnabledSetting(new ComponentName("com.huawei.hwid", "com.huawei.hms.runtimekit.stubexplicit.HwIDStartUpGuideLoginActivity"), i, 0);
        } catch (Throwable th2) {
            MdmLog.e("RestrictionPolicyHWHEM", "设置华为C5启用/禁用登录华为账号调用组件设置失败", th2);
        }
        return true;
    }

    @Override
    public boolean isClipboardAllowed() {
        return !new DeviceRestrictionManager().isClipboardDisabled(this.componentName);
    }

    @Override
    public boolean isFactoryResetAllowed() {
        return !new DeviceSettingsManager().isRestoreFactoryDisabled(this.componentName);
    }

    @Override
    public boolean isFirmwareRecoveryAllowed() {
        return !new DeviceRestrictionManager().isSdCardUpdateDisabled(this.componentName);
    }

    @Override
    public boolean isMTPAvailable() {
        return !new DeviceRestrictionManager().isUSBDataDisabled(this.componentName);
    }

    @Override
    public boolean isSafeModeAllow() {
        return !new DeviceRestrictionManager().isSafeModeDisabled(this.componentName);
    }

    @Override
    public boolean isSdCardEnabled() {
        return !new DeviceRestrictionManager().isExternalStorageDisabled(this.componentName);
    }

    @Override
    public boolean isUsbDebuggingEnabled() {
        return !new DeviceRestrictionManager().isAdbDisabled(this.componentName);
    }

    @Override
    public boolean multipleUsersAllowed() {
        return !new DeviceSettingsManager().isAddUserDisabled(this.componentName);
    }

    @Override
    public boolean setCameraState(boolean enable) {
        new DeviceCameraManager().setVideoDisabled(this.componentName, !enable);
        Object systemService = this.context.getSystemService("device_policy");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.admin.DevicePolicyManager");
        }
        try {
            ((DevicePolicyManager) systemService).setCameraDisabled(this.componentName, !enable);
        } catch (Throwable th) {
            MdmLog.e("RestrictionPolicyHWHEM", "系统的禁用相机发生异常！", th);
        }
        return true;
    }

    @Override
    public boolean setClipboardEnabled(boolean enable) {
        return new DeviceRestrictionManager().setClipboardDisabled(this.componentName, !enable);
    }

    @Override
    public boolean setFileShareDisabled(boolean enable) {
        return new DeviceRestrictionManager().setFileShareDisabled(this.componentName, enable);
    }

    @Override
    public boolean setInputMethod(String inputMethodClassName) {
        Intrinsics.checkNotNullParameter(inputMethodClassName, "inputMethodClassName");
        try {
            new DeviceSettingsManager().setModifyInputMethodDisabled(this.componentName, false);
        } catch (Throwable unused) {
        }
        Settings.Secure.putString(this.context.getContentResolver(), "enabled_input_methods", inputMethodClassName);
        return Settings.Secure.putString(this.context.getContentResolver(), "default_input_method", inputMethodClassName);
    }

    @Override
    public boolean setMTPEnabled(boolean enable) {
        new DeviceRestrictionManager().setUSBDataDisabled(this.componentName, !enable);
        return isMTPAvailable() == enable;
    }

    @Override
    public boolean setScreenCapture(boolean enable) {
        try {
            PackageManager packageManager = this.context.getPackageManager();
            int i = enable ? 1 : 2;
            if (packageManager != null) {
                packageManager.setApplicationEnabledSetting("com.huawei.screenrecorder", i, 0);
            }
        } catch (Throwable th) {
            MdmLog.e("RestrictionPolicyHWHEM", "设置华为录屏功能出现异常", th);
        }
        DeviceRestrictionManager deviceRestrictionManager = new DeviceRestrictionManager();
        deviceRestrictionManager.setManualScreenCaptureDisabled(this.componentName, !enable);
        return deviceRestrictionManager.setScreenCaptureDisabled(this.componentName, !enable);
    }

    @Override
    public boolean setSdCardState(boolean enable) {
        new DeviceRestrictionManager().setExternalStorageDisabled(this.componentName, !enable);
        return isSdCardEnabled() == enable;
    }

    @Override
    public boolean setUsbDebuggingEnabled(boolean enable) {
        new DeviceRestrictionManager().setAdbDisabled(this.componentName, !enable);
        return true;
    }

    @Override
    public boolean wipeRecentTasks() throws Resources.NotFoundException {
        DeviceApplicationManager deviceApplicationManager = new DeviceApplicationManager();
        Set<String> usageStatsPackages = getUsageStatsPackages();
        ArrayList<String> arrayList = new ArrayList();
        for (Object obj : usageStatsPackages) {
            if (true ^ getPlatformLevelApps().contains((String) obj)) {
                arrayList.add(obj);
            }
        }
        for (String str : arrayList) {
            try {
                Result.Companion companion = Result.INSTANCE;
                deviceApplicationManager.killApplicationProcess(this.componentName, str);
                Result.m228constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m228constructorimpl(ResultKt.createFailure(th));
            }
        }
        return true;
    }
}
