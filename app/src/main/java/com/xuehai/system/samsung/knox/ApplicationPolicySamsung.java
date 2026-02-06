package com.xuehai.system.samsung.knox;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.application.ApplicationPolicy;
import com.xuehai.system.common.compat.ApplicationPolicyDefault;
import com.xuehai.system.common.util.SignaturesUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0016\u0010\u000e\u001a\u00020\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0016\u0010\u0010\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u001e\u0010\u0011\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\bH\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0016J\u0016\u0010\u001a\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0018\u0010\u001b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016J\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0016\u0010\u001e\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0018\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\bH\u0016J\u0010\u0010#\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0010\u0010$\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0018\u0010%\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016J\u0010\u0010&\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0010\u0010'\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0010\u0010(\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0010\u0010)\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0010\u0010*\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/xuehai/system/samsung/knox/ApplicationPolicySamsung;", "Lcom/xuehai/system/common/compat/ApplicationPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/SamsungPolicy;", "addAppAlwaysRunningList", "", "packageList", "", "", "addPackageToBatteryOptimizationWhiteList", "packageName", "addPackagesToFocusMonitoringList", "packageNames", "addPackagesToNotificationWhiteList", "applyRuntimePermissions", "runtimePermissions", "clearApplicationUninstallRule", "", "clearPackagesFromNotificationList", "getAppAlwaysRunningList", "getPackagesFromBatteryOptimizationWhiteList", "installApplication", "apkFilePath", "removeAppAlwaysRunningList", "removeDefaultLauncher", "className", "removePackageFromBatteryOptimizationWhiteList", "removePackagesFromNotificationWhiteList", "setApplicationComponentState", "cmpName", "Landroid/content/ComponentName;", "state", "setApplicationUninstallationDisabled", "setApplicationUninstallationEnabled", "setDefaultLauncher", "setDisableApplication", "setEnableApplication", "stopApp", "uninstallApplication", "wipeApplicationData", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ApplicationPolicySamsung extends ApplicationPolicyDefault {
    private final Context context;
    private final SamsungPolicy policy;

    public ApplicationPolicySamsung(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.policy = new SamsungPolicy(context);
    }

    @Override
    public boolean addAppAlwaysRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        return this.policy.applicationPolicy().addPackagesToForceStopBlackList(packageList);
    }

    @Override
    public boolean addPackageToBatteryOptimizationWhiteList(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return this.policy.applicationPolicy().addPackageToBatteryOptimizationWhiteList(new AppIdentity(packageName, SignaturesUtil.INSTANCE.getPackageSignature(this.context, packageName))) == 0;
    }

    @Override
    public boolean addPackagesToFocusMonitoringList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return this.policy.applicationPolicy().addPackagesToFocusMonitoringList(packageNames);
    }

    @Override
    public boolean addPackagesToNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        return this.policy.applicationPolicy().addPackagesToNotificationWhiteList(packageList, false);
    }

    @Override
    public boolean applyRuntimePermissions(String packageName, List<String> runtimePermissions) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(runtimePermissions, "runtimePermissions");
        return this.policy.applicationPolicy().applyRuntimePermissions(new AppIdentity(packageName, (String) null), runtimePermissions, 1) == 0;
    }

    @Override
    public void clearApplicationUninstallRule() {
        List<ApplicationInfo> installedApplications;
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager == null || (installedApplications = packageManager.getInstalledApplications(0)) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : installedApplications) {
            if ((((ApplicationInfo) obj).flags & 1) == 0) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.policy.applicationPolicy().setApplicationUninstallationEnabled(((ApplicationInfo) it.next()).packageName);
        }
    }

    @Override
    public boolean clearPackagesFromNotificationList() {
        return this.policy.applicationPolicy().clearPackagesFromNotificationList();
    }

    @Override
    public List<String> getAppAlwaysRunningList() {
        List<String> packagesFromForceStopBlackList = this.policy.applicationPolicy().getPackagesFromForceStopBlackList();
        Intrinsics.checkNotNullExpressionValue(packagesFromForceStopBlackList, "policy.applicationPolicy…gesFromForceStopBlackList");
        return packagesFromForceStopBlackList;
    }

    @Override
    public List<String> getPackagesFromBatteryOptimizationWhiteList() {
        List<String> packagesFromBatteryOptimizationWhiteList = this.policy.applicationPolicy().getPackagesFromBatteryOptimizationWhiteList();
        Intrinsics.checkNotNullExpressionValue(packagesFromBatteryOptimizationWhiteList, "policy.applicationPolicy…teryOptimizationWhiteList");
        return packagesFromBatteryOptimizationWhiteList;
    }

    @Override
    public boolean installApplication(String apkFilePath) {
        Intrinsics.checkNotNullParameter(apkFilePath, "apkFilePath");
        return this.policy.applicationPolicy().installApplication(apkFilePath, false);
    }

    @Override
    public boolean removeAppAlwaysRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        return this.policy.applicationPolicy().removePackagesFromForceStopBlackList(packageList);
    }

    @Override
    public boolean removeDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        return this.policy.applicationPolicy().removeDefaultApplication(ApplicationPolicy.LAUNCHER_TASK, new ComponentName(packageName, className));
    }

    @Override
    public boolean removePackageFromBatteryOptimizationWhiteList(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return this.policy.applicationPolicy().removePackageFromBatteryOptimizationWhiteList(new AppIdentity(packageName, SignaturesUtil.INSTANCE.getPackageSignature(this.context, packageName))) == 0;
    }

    @Override
    public boolean removePackagesFromNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        return this.policy.applicationPolicy().removePackagesFromNotificationWhiteList(packageList);
    }

    @Override
    public boolean setApplicationComponentState(ComponentName cmpName, boolean state) {
        Intrinsics.checkNotNullParameter(cmpName, "cmpName");
        return this.policy.applicationPolicy().setApplicationComponentState(cmpName, state);
    }

    @Override
    public void setApplicationUninstallationDisabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.policy.applicationPolicy().setApplicationUninstallationDisabled(packageName);
    }

    @Override
    public void setApplicationUninstallationEnabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.policy.applicationPolicy().setApplicationUninstallationEnabled(packageName);
    }

    @Override
    public boolean setDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        return this.policy.applicationPolicy().setDefaultApplication(ApplicationPolicy.LAUNCHER_TASK, new ComponentName(packageName, className));
    }

    @Override
    public boolean setDisableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return this.policy.applicationPolicy().setDisableApplication(packageName);
    }

    @Override
    public boolean setEnableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return this.policy.applicationPolicy().setEnableApplication(packageName);
    }

    @Override
    public boolean stopApp(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return this.policy.applicationPolicy().stopApp(packageName);
    }

    @Override
    public boolean uninstallApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        List<String> listUninstallApplications = this.policy.applicationPolicy().uninstallApplications(Collections.singletonList(packageName));
        return listUninstallApplications != null && listUninstallApplications.size() == 1 && Intrinsics.areEqual(packageName, listUninstallApplications.get(0));
    }

    @Override
    public boolean wipeApplicationData(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return this.policy.applicationPolicy().wipeApplicationData(packageName);
    }
}
