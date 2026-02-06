package com.xuehai.system.mdm.proxy;

import android.content.ComponentName;
import android.os.Environment;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.policies.IApplicationPolicy;
import com.xuehai.system.mdm.AppUsage2;
import com.xuehai.system.mdm.TrafficInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 V2\u00020\u0001:\u0001VB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010\t\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010\n\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u0016J\u0016\u0010\u000e\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010\u000f\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010\u0010\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005H\u0016J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005H\u0016J\u001e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\u0017\u001a\u00020\u0012H\u0016J\b\u0010\u0018\u001a\u00020\u0005H\u0016J\b\u0010\u0019\u001a\u00020\u0005H\u0016J\b\u0010\u001a\u001a\u00020\u0005H\u0016J\b\u0010\u001b\u001a\u00020\u0005H\u0016J\b\u0010\u001c\u001a\u00020\u0005H\u0016J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u0005H\u0016J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J&\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020#\u0018\u00010\"2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0016J\u001e\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u00072\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0016J\b\u0010(\u001a\u00020\bH\u0016J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u000e\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\n\u0010-\u001a\u0004\u0018\u00010\bH\u0016J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0010\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\bH\u0016J\u0010\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u00020\bH\u0016J,\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\b2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0016J4\u00108\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\b2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u00109\u001a\u000206H\u0016J\u0016\u0010:\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010;\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0018\u0010<\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\b2\u0006\u0010=\u001a\u00020\bH\u0016J\u0018\u0010>\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\b2\u0006\u0010=\u001a\u00020\bH\u0016J\u0016\u0010?\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0010\u0010@\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u0016J\u0016\u0010A\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010B\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010C\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0018\u0010D\u001a\u00020\u00052\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u0005H\u0016J\u0010\u0010H\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010I\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010J\u001a\u00020\u00052\u0006\u0010K\u001a\u00020\bH\u0016J\u0018\u0010L\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\b2\u0006\u0010=\u001a\u00020\bH\u0016J\u0018\u0010M\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\b2\u0006\u0010=\u001a\u00020\bH\u0016J\u0018\u0010N\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\b2\u0006\u0010=\u001a\u00020\bH\u0016J\u0010\u0010O\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010P\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u0016J\u0016\u0010Q\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0010\u0010R\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010S\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010T\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010U\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Lcom/xuehai/system/mdm/proxy/ApplicationPolicyProxy;", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "policy", "(Lcom/xuehai/system/common/policies/IApplicationPolicy;)V", "addAppAlwaysRunningList", "", "packageList", "", "", "addAppAutoRunningList", "addIgnoreFrequentRelaunchAppList", "packageNames", "addPackageToBatteryOptimizationWhiteList", "packageName", "addPackagesToFocusMonitoringList", "addPackagesToNotificationWhiteList", "addRuntimePermissionFixAppList", "applyAllAppOpsPermissions", "", "enable", "applyAllAppRuntimePermissions", "applyRuntimePermissions", "runtimePermissions", "clearApplicationUninstallRule", "clearComponentLaunchedByLauncher", "clearIgnoreFrequentRelaunchAppList", "clearPackagesFromNotificationList", "clearRuntimePermissionFixAppList", "clearTrustListForSystemManger", "controlChangeHome", "isEnable", "getAppAlwaysRunningList", "getAppAutoRunningList", "getAppUsage", "", "", "beginTime", "endTime", "getAppUsageV2", "Lcom/xuehai/system/mdm/AppUsage2;", "getComponentLaunchedByLauncher", "getIgnoreFrequentRelaunchAppList", "getPackagesFromBatteryOptimizationWhiteList", "getRunningAppProcesses", "getRuntimePermissionFixAppList", "getTopAppPackageName", "getTrustListForSystemManger", "hasRuntimePermissions", "runtimePermission", "installApplication", "apkFilePath", "queryTrafficDetailBySummary", "Lcom/xuehai/system/mdm/TrafficInfo;", "networkType", "", "subscriberId", "queryTrafficDetailByUid", "uid", "removeAppAlwaysRunningList", "removeAppAutoRunningList", "removeDefaultDeviceAssistance", "className", "removeDefaultLauncher", "removeIgnoreFrequentRelaunchAppList", "removePackageFromBatteryOptimizationWhiteList", "removePackagesFromNotificationWhiteList", "removeRuntimePermissionFixAppList", "removeTrustListForSystemManger", "setApplicationComponentState", "cmpName", "Landroid/content/ComponentName;", "state", "setApplicationUninstallationDisabled", "setApplicationUninstallationEnabled", "setComponentLaunchedByLauncher", "launchComponent", "setDefaultDeviceAssistance", "setDefaultHome", "setDefaultLauncher", "setDisableApplication", "setEnableApplication", "setTrustListForSystemManger", "setUsageStatsPermission", "stopApp", "uninstallApplication", "wipeApplicationData", "Companion", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ApplicationPolicyProxy implements IApplicationPolicy {
    private static final String SDCARD_ROOT = "/sdcard/";
    public static final String TAG = "MDM:ApplicationPolicy";
    private final IApplicationPolicy policy;

    public ApplicationPolicyProxy(IApplicationPolicy iApplicationPolicy) {
        Intrinsics.checkNotNullParameter(iApplicationPolicy, "policy");
        this.policy = iApplicationPolicy;
    }

    @Override
    public boolean addAppAlwaysRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        try {
            boolean zAddAppAlwaysRunningList = this.policy.addAppAlwaysRunningList(packageList);
            MdmLog.log("MDM:ApplicationPolicy", "添加应用到允许始终运行列表中 ", Boolean.valueOf(zAddAppAlwaysRunningList));
            return zAddAppAlwaysRunningList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "添加应用到允许始终运行列表中 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addAppAutoRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        try {
            boolean zAddAppAutoRunningList = this.policy.addAppAutoRunningList(packageList);
            MdmLog.log("MDM:ApplicationPolicy", "添加自动运行列表", Boolean.valueOf(zAddAppAutoRunningList));
            return zAddAppAutoRunningList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "添加自动运行列表 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addIgnoreFrequentRelaunchAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        try {
            boolean zAddIgnoreFrequentRelaunchAppList = this.policy.addIgnoreFrequentRelaunchAppList(packageNames);
            MdmLog.log$default("MDM:ApplicationPolicy", "添加第三方桌面豁免名单: " + zAddIgnoreFrequentRelaunchAppList, null, 4, null);
            return zAddIgnoreFrequentRelaunchAppList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "添加第三方桌面豁免名单 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addPackageToBatteryOptimizationWhiteList(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            boolean zAddPackageToBatteryOptimizationWhiteList = this.policy.addPackageToBatteryOptimizationWhiteList(packageName);
            MdmLog.log("MDM:ApplicationPolicy", "添加电池优化白名单" + packageName, Boolean.valueOf(zAddPackageToBatteryOptimizationWhiteList));
            return zAddPackageToBatteryOptimizationWhiteList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "添加电池优化白名单" + packageName + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addPackagesToFocusMonitoringList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        try {
            boolean zAddPackagesToFocusMonitoringList = this.policy.addPackagesToFocusMonitoringList(packageNames);
            MdmLog.log$default("MDM:ApplicationPolicy", "FocusMonitoring: " + zAddPackagesToFocusMonitoringList, null, 4, null);
            return zAddPackagesToFocusMonitoringList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "FocusMonitoring 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addPackagesToNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        try {
            boolean zAddPackagesToNotificationWhiteList = this.policy.addPackagesToNotificationWhiteList(packageList);
            MdmLog.log("MDM:ApplicationPolicy", "添加允许通知栏白名单", Boolean.valueOf(zAddPackagesToNotificationWhiteList));
            return zAddPackagesToNotificationWhiteList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "添加允许通知栏白名单 发生异常", th);
            return false;
        }
    }

    @Override
    public boolean addRuntimePermissionFixAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        try {
            boolean zAddRuntimePermissionFixAppList = this.policy.addRuntimePermissionFixAppList(packageNames);
            MdmLog.log$default("MDM:ApplicationPolicy", "添加到“禁止关闭权限”应用列表中(" + packageNames + "): " + zAddRuntimePermissionFixAppList, null, 4, null);
            return zAddRuntimePermissionFixAppList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "添加到“禁止关闭权限”应用列表中" + packageNames + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public void applyAllAppOpsPermissions(boolean enable) {
        try {
            this.policy.applyAllAppOpsPermissions(enable);
            MdmLog.log$default("MDM:ApplicationPolicy", "Granted all app's ops permissions: " + enable, null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "Granted all app's ops permissions error !!", th);
        }
    }

    @Override
    public void applyAllAppRuntimePermissions(boolean enable) {
        try {
            this.policy.applyAllAppRuntimePermissions(enable);
            MdmLog.log$default("MDM:ApplicationPolicy", "Granted all app's runtime permissions: " + enable, null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "Granted all app's runtime permissions error !!", th);
        }
    }

    @Override
    public boolean applyRuntimePermissions(String packageName, List<String> runtimePermissions) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(runtimePermissions, "runtimePermissions");
        try {
            boolean zApplyRuntimePermissions = runtimePermissions.isEmpty() ? true : this.policy.applyRuntimePermissions(packageName, runtimePermissions);
            MdmLog.log$default("MDM:ApplicationPolicy", "Granted runtime permissions " + packageName + " : " + zApplyRuntimePermissions, null, 4, null);
            return zApplyRuntimePermissions;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "Granted runtime permissions " + packageName + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public void clearApplicationUninstallRule() {
        try {
            this.policy.clearApplicationUninstallRule();
            MdmLog.log$default("MDM:ApplicationPolicy", "清空应用卸载规则", null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "清空应用卸载规则 发生异常", th);
        }
    }

    @Override
    public boolean clearComponentLaunchedByLauncher() {
        try {
            boolean zClearComponentLaunchedByLauncher = this.policy.clearComponentLaunchedByLauncher();
            MdmLog.log("MDM:ApplicationPolicy", "清除开机拉起应用组件", Boolean.valueOf(zClearComponentLaunchedByLauncher));
            return zClearComponentLaunchedByLauncher;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "清除开机拉起应用组件 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean clearIgnoreFrequentRelaunchAppList() {
        try {
            boolean zClearIgnoreFrequentRelaunchAppList = this.policy.clearIgnoreFrequentRelaunchAppList();
            MdmLog.log$default("MDM:ApplicationPolicy", "清空第三方桌面豁免名单: " + zClearIgnoreFrequentRelaunchAppList, null, 4, null);
            return zClearIgnoreFrequentRelaunchAppList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "清空第三方桌面豁免名单 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean clearPackagesFromNotificationList() {
        try {
            boolean zClearPackagesFromNotificationList = this.policy.clearPackagesFromNotificationList();
            MdmLog.log("MDM:ApplicationPolicy", "清空允许通知栏白名单", Boolean.valueOf(zClearPackagesFromNotificationList));
            return zClearPackagesFromNotificationList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "清空允许通知栏白名单 发生异常", th);
            return false;
        }
    }

    @Override
    public boolean clearRuntimePermissionFixAppList() {
        try {
            boolean zClearRuntimePermissionFixAppList = this.policy.clearRuntimePermissionFixAppList();
            MdmLog.log$default("MDM:ApplicationPolicy", "清除所有应用“禁止关闭权限”应用列表: " + zClearRuntimePermissionFixAppList, null, 4, null);
            return zClearRuntimePermissionFixAppList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "清除所有应用“禁止关闭权限”应用列表 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean clearTrustListForSystemManger() {
        try {
            boolean zClearTrustListForSystemManger = this.policy.clearTrustListForSystemManger();
            MdmLog.log$default("MDM:ApplicationPolicy", "清空可信任列表: " + zClearTrustListForSystemManger, null, 4, null);
            return zClearTrustListForSystemManger;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "清空可信任列表 发生异常！", th);
            return false;
        }
    }

    @Override
    public void controlChangeHome(boolean isEnable) {
        try {
            this.policy.controlChangeHome(isEnable);
            MdmLog.log$default("MDM:ApplicationPolicy", "controlChangeHome: " + isEnable, null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "controlChangeHome: " + isEnable + " 发生异常！", th);
        }
    }

    @Override
    public List<String> getAppAlwaysRunningList() {
        try {
            List<String> appAlwaysRunningList = this.policy.getAppAlwaysRunningList();
            MdmLog.log$default("MDM:ApplicationPolicy", "获取始终运行列表 " + appAlwaysRunningList, null, 4, null);
            return appAlwaysRunningList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取始终运行列表 发生异常！", th);
            return new ArrayList();
        }
    }

    @Override
    public List<String> getAppAutoRunningList() {
        try {
            List<String> appAutoRunningList = this.policy.getAppAutoRunningList();
            MdmLog.log$default("MDM:ApplicationPolicy", "获取自动运行列表 " + appAutoRunningList.size(), null, 4, null);
            return appAutoRunningList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取自动运行列表 发生异常！", th);
            return new ArrayList();
        }
    }

    @Override
    public Map<String, Long> getAppUsage(long beginTime, long endTime) {
        try {
            Map<String, Long> appUsage = this.policy.getAppUsage(beginTime, endTime);
            MdmLog.log$default("MDM:ApplicationPolicy", "获取应用使用数据: " + appUsage, null, 4, null);
            return appUsage;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取应用使用数据 发生异常！", th);
            return (Map) null;
        }
    }

    @Override
    public List<AppUsage2> getAppUsageV2(long beginTime, long endTime) {
        try {
            List<AppUsage2> appUsageV2 = this.policy.getAppUsageV2(beginTime, endTime);
            MdmLog.log$default("MDM:ApplicationPolicy", "获取应用使用数据(v2): " + appUsageV2, null, 4, null);
            return appUsageV2;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取应用使用数据(v2) 发生异常！", th);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public String getComponentLaunchedByLauncher() {
        try {
            String componentLaunchedByLauncher = this.policy.getComponentLaunchedByLauncher();
            MdmLog.log$default("MDM:ApplicationPolicy", "获取开机拉起应用组件->" + componentLaunchedByLauncher, null, 4, null);
            return componentLaunchedByLauncher;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取开机拉起应用组件 发生异常！", th);
            return "";
        }
    }

    @Override
    public List<String> getIgnoreFrequentRelaunchAppList() {
        try {
            List<String> ignoreFrequentRelaunchAppList = this.policy.getIgnoreFrequentRelaunchAppList();
            MdmLog.log$default("MDM:ApplicationPolicy", "获取第三方桌面豁免名单: " + ignoreFrequentRelaunchAppList, null, 4, null);
            return ignoreFrequentRelaunchAppList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取第三方桌面豁免名单 发生异常！", th);
            return new ArrayList();
        }
    }

    @Override
    public List<String> getPackagesFromBatteryOptimizationWhiteList() {
        try {
            List<String> packagesFromBatteryOptimizationWhiteList = this.policy.getPackagesFromBatteryOptimizationWhiteList();
            MdmLog.log$default("MDM:ApplicationPolicy", "获取电池优化白名单: " + packagesFromBatteryOptimizationWhiteList, null, 4, null);
            return packagesFromBatteryOptimizationWhiteList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取电池优化白名单 发生异常！", th);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public List<String> getRunningAppProcesses() {
        try {
            List<String> runningAppProcesses = this.policy.getRunningAppProcesses();
            MdmLog.log$default("MDM:ApplicationPolicy", "获取正在运行的应用: " + runningAppProcesses, null, 4, null);
            return runningAppProcesses;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取正在运行的应用 发生异常！", th);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public List<String> getRuntimePermissionFixAppList() {
        try {
            List<String> runtimePermissionFixAppList = this.policy.getRuntimePermissionFixAppList();
            MdmLog.log$default("MDM:ApplicationPolicy", "获取“禁止关闭权限”应用列表: " + runtimePermissionFixAppList, null, 4, null);
            return runtimePermissionFixAppList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取“禁止关闭权限”应用列表 发生异常！", th);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public String getTopAppPackageName() {
        try {
            return this.policy.getTopAppPackageName();
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取前台应用 发生异常！", th);
            return (String) null;
        }
    }

    @Override
    public List<String> getTrustListForSystemManger() {
        try {
            List<String> trustListForSystemManger = this.policy.getTrustListForSystemManger();
            MdmLog.log$default("MDM:ApplicationPolicy", "获取可信任列表: " + trustListForSystemManger, null, 4, null);
            return trustListForSystemManger;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取可信任列表 发生异常！", th);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public boolean hasRuntimePermissions(String runtimePermission) {
        Intrinsics.checkNotNullParameter(runtimePermission, "runtimePermission");
        try {
            boolean zHasRuntimePermissions = this.policy.hasRuntimePermissions(runtimePermission);
            MdmLog.log$default("MDM:ApplicationPolicy", "hasRuntimePermissions(" + runtimePermission + "): " + zHasRuntimePermissions, null, 4, null);
            return zHasRuntimePermissions;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "hasRuntimePermissions" + runtimePermission + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean installApplication(String apkFilePath) {
        Intrinsics.checkNotNullParameter(apkFilePath, "apkFilePath");
        if (StringsKt.startsWith$default(apkFilePath, "/sdcard/", false, 2, (Object) null)) {
            try {
                apkFilePath = StringsKt.replace$default(apkFilePath, "/sdcard/", Environment.getExternalStorageDirectory().getAbsolutePath() + '/', false, 4, (Object) null);
            } catch (Exception unused) {
            }
        }
        try {
            boolean zInstallApplication = this.policy.installApplication(apkFilePath);
            MdmLog.log("MDM:ApplicationPolicy", "静默安装 : " + apkFilePath, Boolean.valueOf(zInstallApplication));
            return zInstallApplication;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "静默安装 发生异常: " + apkFilePath, th);
            return false;
        }
    }

    @Override
    public TrafficInfo queryTrafficDetailBySummary(int networkType, String subscriberId, long beginTime, long endTime) {
        try {
            TrafficInfo trafficInfoQueryTrafficDetailBySummary = this.policy.queryTrafficDetailBySummary(networkType, subscriberId, beginTime, endTime);
            MdmLog.log$default("MDM:ApplicationPolicy", "获取手机流量使用数据: " + trafficInfoQueryTrafficDetailBySummary, null, 4, null);
            return trafficInfoQueryTrafficDetailBySummary;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取手机流量使用数据发生异常！", th);
            return (TrafficInfo) null;
        }
    }

    @Override
    public TrafficInfo queryTrafficDetailByUid(int networkType, String subscriberId, long beginTime, long endTime, int uid) {
        try {
            TrafficInfo trafficInfoQueryTrafficDetailByUid = this.policy.queryTrafficDetailByUid(networkType, subscriberId, beginTime, endTime, uid);
            MdmLog.log$default("MDM:ApplicationPolicy", "获取应用流量使用数据: " + trafficInfoQueryTrafficDetailByUid, null, 4, null);
            return trafficInfoQueryTrafficDetailByUid;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "获取应用流量使用数据发生异常！", th);
            return (TrafficInfo) null;
        }
    }

    @Override
    public boolean removeAppAlwaysRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        try {
            boolean zRemoveAppAlwaysRunningList = this.policy.removeAppAlwaysRunningList(packageList);
            MdmLog.log("MDM:ApplicationPolicy", "将应用从允许始终运行列表移除 ", Boolean.valueOf(zRemoveAppAlwaysRunningList));
            return zRemoveAppAlwaysRunningList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "将应用从允许始终运行列表移除 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean removeAppAutoRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        try {
            boolean zRemoveAppAutoRunningList = this.policy.removeAppAutoRunningList(packageList);
            MdmLog.log("MDM:ApplicationPolicy", "从自动运行列表中移除", Boolean.valueOf(zRemoveAppAutoRunningList));
            return zRemoveAppAutoRunningList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "从自动运行列表中移除 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean removeDefaultDeviceAssistance(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        try {
            boolean zRemoveDefaultDeviceAssistance = this.policy.removeDefaultDeviceAssistance(packageName, className);
            MdmLog.log("MDM:ApplicationPolicy", "移除默认设备辅助应用 (" + packageName + '/' + className + ')', Boolean.valueOf(zRemoveDefaultDeviceAssistance));
            return zRemoveDefaultDeviceAssistance;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "移除默认设备辅助应用 发生异常！ (" + packageName + '/' + className + ')', th);
            return false;
        }
    }

    @Override
    public boolean removeDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        try {
            boolean zRemoveDefaultLauncher = this.policy.removeDefaultLauncher(packageName, className);
            MdmLog.log("MDM:ApplicationPolicy", "移除默认桌面 (" + packageName + '/' + className + ')', Boolean.valueOf(zRemoveDefaultLauncher));
            return zRemoveDefaultLauncher;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "移除默认桌面 发生异常！ (" + packageName + '/' + className + ')', th);
            return false;
        }
    }

    @Override
    public boolean removeIgnoreFrequentRelaunchAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        try {
            boolean zRemoveIgnoreFrequentRelaunchAppList = this.policy.removeIgnoreFrequentRelaunchAppList(packageNames);
            MdmLog.log$default("MDM:ApplicationPolicy", "删除第三方桌面豁免名单: " + zRemoveIgnoreFrequentRelaunchAppList, null, 4, null);
            return zRemoveIgnoreFrequentRelaunchAppList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "删除第三方桌面豁免名单 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean removePackageFromBatteryOptimizationWhiteList(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            boolean zRemovePackageFromBatteryOptimizationWhiteList = this.policy.removePackageFromBatteryOptimizationWhiteList(packageName);
            MdmLog.log("MDM:ApplicationPolicy", "移除电池优化白名单" + packageName, Boolean.valueOf(zRemovePackageFromBatteryOptimizationWhiteList));
            return zRemovePackageFromBatteryOptimizationWhiteList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "移除电池优化白名单" + packageName + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean removePackagesFromNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        try {
            boolean zRemovePackagesFromNotificationWhiteList = this.policy.removePackagesFromNotificationWhiteList(packageList);
            MdmLog.log("MDM:ApplicationPolicy", "移除允许通知栏白名单", Boolean.valueOf(zRemovePackagesFromNotificationWhiteList));
            return zRemovePackagesFromNotificationWhiteList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "移除允许通知栏白名单 发生异常", th);
            return false;
        }
    }

    @Override
    public boolean removeRuntimePermissionFixAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        try {
            boolean zRemoveRuntimePermissionFixAppList = this.policy.removeRuntimePermissionFixAppList(packageNames);
            MdmLog.log$default("MDM:ApplicationPolicy", "从“禁止关闭权限”应用列表中移除(" + packageNames + "): " + zRemoveRuntimePermissionFixAppList, null, 4, null);
            return zRemoveRuntimePermissionFixAppList;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "从“禁止关闭权限”应用列表中移除" + packageNames + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean removeTrustListForSystemManger(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        try {
            boolean zRemoveTrustListForSystemManger = this.policy.removeTrustListForSystemManger(packageNames);
            MdmLog.log$default("MDM:ApplicationPolicy", "移除可信任列表: " + zRemoveTrustListForSystemManger, null, 4, null);
            return zRemoveTrustListForSystemManger;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "移除可信任列表 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setApplicationComponentState(ComponentName cmpName, boolean state) {
        StringBuilder sb;
        String str;
        Intrinsics.checkNotNullParameter(cmpName, "cmpName");
        if (state) {
            sb = new StringBuilder();
            str = "启用组件 ";
        } else {
            sb = new StringBuilder();
            str = "禁用组件 ";
        }
        sb.append(str);
        sb.append(cmpName);
        String string = sb.toString();
        try {
            boolean applicationComponentState = this.policy.setApplicationComponentState(cmpName, state);
            MdmLog.log("MDM:ApplicationPolicy", string, Boolean.valueOf(applicationComponentState));
            return applicationComponentState;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", string + " 发生异常:", th);
            return false;
        }
    }

    @Override
    public void setApplicationUninstallationDisabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            this.policy.setApplicationUninstallationDisabled(packageName);
            MdmLog.log$default("MDM:ApplicationPolicy", "禁止应用卸载 : " + packageName, null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "禁止应用卸载 发生异常: " + packageName, th);
        }
    }

    @Override
    public void setApplicationUninstallationEnabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            this.policy.setApplicationUninstallationEnabled(packageName);
            MdmLog.log$default("MDM:ApplicationPolicy", "允许应用卸载 : " + packageName, null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "允许应用卸载 发生异常: " + packageName, th);
        }
    }

    @Override
    public boolean setComponentLaunchedByLauncher(String launchComponent) {
        Intrinsics.checkNotNullParameter(launchComponent, "launchComponent");
        try {
            boolean componentLaunchedByLauncher = this.policy.setComponentLaunchedByLauncher(launchComponent);
            MdmLog.log("MDM:ApplicationPolicy", "设置开机拉起应用组件 (" + launchComponent + ')', Boolean.valueOf(componentLaunchedByLauncher));
            return componentLaunchedByLauncher;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "设置开机拉起应用组件 发生异常！ (" + launchComponent + ')', th);
            return false;
        }
    }

    @Override
    public boolean setDefaultDeviceAssistance(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        try {
            boolean defaultDeviceAssistance = this.policy.setDefaultDeviceAssistance(packageName, className);
            MdmLog.log("MDM:ApplicationPolicy", "设置默认设备辅助应用 (" + packageName + '/' + className + ')', Boolean.valueOf(defaultDeviceAssistance));
            return defaultDeviceAssistance;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "设置默认设备辅助应用 发生异常！ (" + packageName + '/' + className + ')', th);
            return false;
        }
    }

    @Override
    public void setDefaultHome(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        try {
            this.policy.setDefaultHome(packageName, className);
            MdmLog.log$default("MDM:ApplicationPolicy", "setDefaultHome 设置默认桌面[新]:  (" + packageName + '/' + className + ')', null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "setDefaultHome 设置默认桌面[新] 发生异常！ (" + packageName + '/' + className + ") ", th);
        }
    }

    @Override
    public boolean setDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        try {
            boolean defaultLauncher = this.policy.setDefaultLauncher(packageName, className);
            MdmLog.log("MDM:ApplicationPolicy", "设置默认桌面 (" + packageName + '/' + className + ')', Boolean.valueOf(defaultLauncher));
            return defaultLauncher;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "设置默认桌面 发生异常！ (" + packageName + '/' + className + ')', th);
            return false;
        }
    }

    @Override
    public boolean setDisableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            boolean disableApplication = this.policy.setDisableApplication(packageName);
            MdmLog.log("MDM:ApplicationPolicy", "设置应用不可用: " + packageName, Boolean.valueOf(disableApplication));
            return disableApplication;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "设置应用不可用 发生异常: " + packageName, th);
            return false;
        }
    }

    @Override
    public boolean setEnableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            boolean enableApplication = this.policy.setEnableApplication(packageName);
            MdmLog.log("MDM:ApplicationPolicy", "设置应用可用: " + packageName, Boolean.valueOf(enableApplication));
            return enableApplication;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "设置应用可用 发生异常: " + packageName, th);
            return false;
        }
    }

    @Override
    public boolean setTrustListForSystemManger(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        try {
            boolean trustListForSystemManger = this.policy.setTrustListForSystemManger(packageNames);
            MdmLog.log$default("MDM:ApplicationPolicy", "添加可信任列表: " + trustListForSystemManger, null, 4, null);
            return trustListForSystemManger;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "添加可信任列表 发生异常！", th);
            return false;
        }
    }

    @Override
    public void setUsageStatsPermission(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            MdmLog.log$default("MDM:ApplicationPolicy", "setUsageStatsPermission: " + packageName, null, 4, null);
            this.policy.setUsageStatsPermission(packageName);
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "setUsageStatsPermission: " + packageName + " 发生异常！", th);
        }
    }

    @Override
    public boolean stopApp(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            boolean zStopApp = this.policy.stopApp(packageName);
            MdmLog.log("MDM:ApplicationPolicy", "停止应用 : " + packageName, Boolean.valueOf(zStopApp));
            return zStopApp;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "停止应用 发生异常: " + packageName, th);
            return false;
        }
    }

    @Override
    public boolean uninstallApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            boolean zUninstallApplication = this.policy.uninstallApplication(packageName);
            MdmLog.log("MDM:ApplicationPolicy", "静默卸载 : " + packageName, Boolean.valueOf(zUninstallApplication));
            return zUninstallApplication;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "静默卸载 发生异常: " + packageName, th);
            return false;
        }
    }

    @Override
    public boolean wipeApplicationData(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            boolean zWipeApplicationData = this.policy.wipeApplicationData(packageName);
            MdmLog.log("MDM:ApplicationPolicy", "清除应用数据 : " + packageName, Boolean.valueOf(zWipeApplicationData));
            return zWipeApplicationData;
        } catch (Throwable th) {
            MdmLog.e("MDM:ApplicationPolicy", "清除应用数据 发生异常: " + packageName, th);
            return false;
        }
    }
}
