package com.xuehai.system.lenovo.csdk.tb223fc;

import android.app.csdk.CSDKManager;
import android.content.ComponentName;
import android.content.Context;
import com.xuehai.system.common.compat.ApplicationPolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.lenovo.csdk.LenovoCSDK;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 <2\u00020\u0001:\u0001<B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0016\u0010\f\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0016\u0010\u000f\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0016\u0010\u0011\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u001e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J&\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\n\u0010#\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\u000bH\u0016J\u0010\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u000bH\u0016J\u0016\u0010(\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0016\u0010)\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0018\u0010*\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000bH\u0016J\u0018\u0010,\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000bH\u0016J\u0010\u0010-\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0016\u0010.\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0018\u0010/\u001a\u00020\b2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\bH\u0016J\u0010\u00103\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u00104\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u00105\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000bH\u0016J\u0018\u00106\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000bH\u0016J\u0010\u00107\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u00108\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u00109\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010:\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010;\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/xuehai/system/lenovo/csdk/tb223fc/ApplicationPolicyLenovo223FC;", "Lcom/xuehai/system/common/compat/ApplicationPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "csdkManager", "Landroid/app/csdk/CSDKManager;", "addAppAlwaysRunningList", "", "packageList", "", "", "addAppAutoRunningList", "addPackageToBatteryOptimizationWhiteList", "packageName", "addPackagesToFocusMonitoringList", "packageNames", "addPackagesToNotificationWhiteList", "applyAllAppOpsPermissions", "", "enable", "applyAllAppRuntimePermissions", "applyRuntimePermissions", "runtimePermissions", "clearApplicationUninstallRule", "clearPackagesFromNotificationList", "getAppAlwaysRunningList", "getAppAutoRunningList", "getAppUsage", "", "", "beginTime", "endTime", "getPackagesFromBatteryOptimizationWhiteList", "getRunningAppProcesses", "getTopAppPackageName", "hasRuntimePermissions", "runtimePermission", "installApplication", "apkFilePath", "removeAppAlwaysRunningList", "removeAppAutoRunningList", "removeDefaultDeviceAssistance", "className", "removeDefaultLauncher", "removePackageFromBatteryOptimizationWhiteList", "removePackagesFromNotificationWhiteList", "setApplicationComponentState", "cmpName", "Landroid/content/ComponentName;", "state", "setApplicationUninstallationDisabled", "setApplicationUninstallationEnabled", "setDefaultDeviceAssistance", "setDefaultLauncher", "setDisableApplication", "setEnableApplication", "stopApp", "uninstallApplication", "wipeApplicationData", "Companion", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ApplicationPolicyLenovo223FC extends ApplicationPolicyDefault {
    private static final String TAG = "ApplicationPolicyLenovo223FC";
    private final CSDKManager csdkManager;

    public ApplicationPolicyLenovo223FC(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.csdkManager = LenovoCSDK.INSTANCE.getCSDKManager(context);
    }

    @Override
    public boolean addAppAlwaysRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        this.csdkManager.addPersistentAppList(packageList);
        return true;
    }

    @Override
    public boolean addAppAutoRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        MdmLog.w("ApplicationPolicyLenovo223FC", "联想未实现！ [addAppAutoRunningList(" + packageList + ")]");
        return super.addAppAutoRunningList(packageList);
    }

    @Override
    public boolean addPackageToBatteryOptimizationWhiteList(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.csdkManager.disallowDozeSetting(false);
        this.csdkManager.addDozeSettingWhiteList(CollectionsKt.mutableListOf(packageName));
        return true;
    }

    @Override
    public boolean addPackagesToFocusMonitoringList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        MdmLog.w("ApplicationPolicyLenovo223FC", "联想未实现！ [addPackagesToFocusMonitoringList(" + packageNames + ")]");
        return super.addPackagesToFocusMonitoringList(packageNames);
    }

    @Override
    public boolean addPackagesToNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        this.csdkManager.addNotificationWhiteList(packageList);
        return true;
    }

    @Override
    public void applyAllAppOpsPermissions(boolean enable) {
        this.csdkManager.setAppOpsPermissions(enable);
    }

    @Override
    public void applyAllAppRuntimePermissions(boolean enable) {
        this.csdkManager.setRuntimePermissions(enable);
    }

    @Override
    public boolean applyRuntimePermissions(String packageName, List<String> runtimePermissions) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(runtimePermissions, "runtimePermissions");
        this.csdkManager.addRuntimePermissionWhiteList(CollectionsKt.mutableListOf(packageName));
        return true;
    }

    @Override
    public void clearApplicationUninstallRule() {
        List<String> uninstallPackageWhiteList = this.csdkManager.getUninstallPackageWhiteList();
        if (uninstallPackageWhiteList != null) {
            this.csdkManager.removeUninstallPackageWhiteList(uninstallPackageWhiteList);
        }
        List<String> uninstallPackageBlackList = this.csdkManager.getUninstallPackageBlackList();
        if (uninstallPackageBlackList != null) {
            this.csdkManager.removeUninstallPackageBlackList(uninstallPackageBlackList);
        }
        this.csdkManager.disableUnInstallation(false);
    }

    @Override
    public boolean clearPackagesFromNotificationList() {
        CSDKManager cSDKManager = this.csdkManager;
        cSDKManager.removeNotificationWhiteList(cSDKManager.getNotificationWhiteList());
        return true;
    }

    @Override
    public List<String> getAppAlwaysRunningList() {
        List<String> persistentAppList = this.csdkManager.getPersistentAppList();
        List<String> list = persistentAppList;
        return list == null || list.isEmpty() ? CollectionsKt.emptyList() : persistentAppList;
    }

    @Override
    public List<String> getAppAutoRunningList() {
        MdmLog.w("ApplicationPolicyLenovo223FC", "联想未实现！ [getAppAutoRunningList()]");
        return super.getAppAutoRunningList();
    }

    @Override
    public Map<String, Long> getAppUsage(long beginTime, long endTime) {
        MdmLog.w("ApplicationPolicyLenovo223FC", "联想未实现！ [getAppUsage(" + beginTime + ',' + endTime + ")]");
        return super.getAppUsage(beginTime, endTime);
    }

    @Override
    public List<String> getPackagesFromBatteryOptimizationWhiteList() {
        List<String> dozeSettingWhiteList = this.csdkManager.getDozeSettingWhiteList();
        return dozeSettingWhiteList == null ? CollectionsKt.emptyList() : dozeSettingWhiteList;
    }

    @Override
    public List<String> getRunningAppProcesses() {
        MdmLog.w("ApplicationPolicyLenovo223FC", "联想未实现！ [getRunningAppProcesses()]");
        return super.getRunningAppProcesses();
    }

    @Override
    public String getTopAppPackageName() {
        return this.csdkManager.getRecentTasks(0);
    }

    @Override
    public boolean hasRuntimePermissions(String runtimePermission) {
        Intrinsics.checkNotNullParameter(runtimePermission, "runtimePermission");
        return super.hasRuntimePermissions(runtimePermission);
    }

    @Override
    public boolean installApplication(String apkFilePath) {
        Intrinsics.checkNotNullParameter(apkFilePath, "apkFilePath");
        this.csdkManager.installPackage(apkFilePath);
        return true;
    }

    @Override
    public boolean removeAppAlwaysRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        this.csdkManager.removePersistentAppList(packageList);
        return true;
    }

    @Override
    public boolean removeAppAutoRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        MdmLog.w("ApplicationPolicyLenovo223FC", "联想未实现！ [removeAppAutoRunningList(" + packageList + ")]");
        return super.removeAppAutoRunningList(packageList);
    }

    @Override
    public boolean removeDefaultDeviceAssistance(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        return super.removeDefaultDeviceAssistance(packageName, className);
    }

    @Override
    public boolean removeDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        this.csdkManager.setLauncher("com.tblenovo.launcher");
        return true;
    }

    @Override
    public boolean removePackageFromBatteryOptimizationWhiteList(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.csdkManager.removeDozeSettingWhiteList(CollectionsKt.mutableListOf(packageName));
        return true;
    }

    @Override
    public boolean removePackagesFromNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        this.csdkManager.removeNotificationWhiteList(packageList);
        return true;
    }

    @Override
    public boolean setApplicationComponentState(ComponentName cmpName, boolean state) {
        Intrinsics.checkNotNullParameter(cmpName, "cmpName");
        this.csdkManager.setComponentEnabled(cmpName, state ? 1 : 2, 0);
        return true;
    }

    @Override
    public void setApplicationUninstallationDisabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.csdkManager.disableUnInstallation(true);
        this.csdkManager.addUninstallPackageBlackList(CollectionsKt.mutableListOf(packageName));
    }

    @Override
    public void setApplicationUninstallationEnabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.csdkManager.removeUninstallPackageBlackList(CollectionsKt.mutableListOf(packageName));
        List<String> uninstallPackageBlackList = this.csdkManager.getUninstallPackageBlackList();
        boolean z = !(uninstallPackageBlackList == null || uninstallPackageBlackList.isEmpty());
        MdmLog.d("ApplicationPolicyLenovo223FC", z ? "启用禁止卸载应用" : "取消禁止卸载应用");
        this.csdkManager.disableUnInstallation(z);
    }

    @Override
    public boolean setDefaultDeviceAssistance(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        return super.setDefaultDeviceAssistance(packageName, className);
    }

    @Override
    public boolean setDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        this.csdkManager.setCustomLauncher(packageName, className);
        return true;
    }

    @Override
    public boolean setDisableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.csdkManager.setPackageEnabled(packageName, false);
        return true;
    }

    @Override
    public boolean setEnableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.csdkManager.setPackageEnabled(packageName, true);
        return true;
    }

    @Override
    public boolean stopApp(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.csdkManager.killApplicationProcess(packageName);
        return true;
    }

    @Override
    public boolean uninstallApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.csdkManager.uninstallPackage(packageName, false);
        return true;
    }

    @Override
    public boolean wipeApplicationData(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return super.wipeApplicationData(packageName);
    }
}
