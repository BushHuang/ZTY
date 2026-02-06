package com.xuehai.system.common.policies;

import android.content.ComponentName;
import com.xuehai.system.common.UnSupportException;
import com.xuehai.system.mdm.AppUsage2;
import com.xuehai.system.mdm.TrafficInfo;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0010\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u000e\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H&J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H&J\u001e\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00062\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\b\u0010\u0015\u001a\u00020\u0010H&J\b\u0010\u0016\u001a\u00020\u0003H&J\b\u0010\u0017\u001a\u00020\u0003H&J\b\u0010\u0018\u001a\u00020\u0003H&J\b\u0010\u0019\u001a\u00020\u0003H&J\b\u0010\u001a\u001a\u00020\u0003H&J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0003H&J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J&\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020!\u0018\u00010 2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!H&J\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00052\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!H&J\b\u0010&\u001a\u00020\u0006H&J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\n\u0010+\u001a\u0004\u0018\u00010\u0006H&J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\u0006H&J\u0010\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u0006H&J,\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u00062\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!H&J4\u00106\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u00062\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!2\u0006\u00107\u001a\u000204H&J\u0016\u00108\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u00109\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0018\u0010:\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006H&J\u0018\u0010<\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006H&J\u0016\u0010=\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010>\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0016\u0010?\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010@\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010A\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0018\u0010B\u001a\u00020\u00032\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u0003H&J\u0010\u0010F\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010G\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010H\u001a\u00020\u00032\u0006\u0010I\u001a\u00020\u0006H&J\u0018\u0010J\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006H&J\u0018\u0010K\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006H&J\u0018\u0010L\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006H&J\u0010\u0010M\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010N\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0016\u0010O\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010P\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010Q\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010R\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010S\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&¨\u0006T"}, d2 = {"Lcom/xuehai/system/common/policies/IApplicationPolicy;", "", "addAppAlwaysRunningList", "", "packageList", "", "", "addAppAutoRunningList", "addIgnoreFrequentRelaunchAppList", "packageNames", "addPackageToBatteryOptimizationWhiteList", "packageName", "addPackagesToFocusMonitoringList", "addPackagesToNotificationWhiteList", "addRuntimePermissionFixAppList", "applyAllAppOpsPermissions", "", "enable", "applyAllAppRuntimePermissions", "applyRuntimePermissions", "runtimePermissions", "clearApplicationUninstallRule", "clearComponentLaunchedByLauncher", "clearIgnoreFrequentRelaunchAppList", "clearPackagesFromNotificationList", "clearRuntimePermissionFixAppList", "clearTrustListForSystemManger", "controlChangeHome", "isEnable", "getAppAlwaysRunningList", "getAppAutoRunningList", "getAppUsage", "", "", "beginTime", "endTime", "getAppUsageV2", "Lcom/xuehai/system/mdm/AppUsage2;", "getComponentLaunchedByLauncher", "getIgnoreFrequentRelaunchAppList", "getPackagesFromBatteryOptimizationWhiteList", "getRunningAppProcesses", "getRuntimePermissionFixAppList", "getTopAppPackageName", "getTrustListForSystemManger", "hasRuntimePermissions", "runtimePermission", "installApplication", "apkFilePath", "queryTrafficDetailBySummary", "Lcom/xuehai/system/mdm/TrafficInfo;", "networkType", "", "subscriberId", "queryTrafficDetailByUid", "uid", "removeAppAlwaysRunningList", "removeAppAutoRunningList", "removeDefaultDeviceAssistance", "className", "removeDefaultLauncher", "removeIgnoreFrequentRelaunchAppList", "removePackageFromBatteryOptimizationWhiteList", "removePackagesFromNotificationWhiteList", "removeRuntimePermissionFixAppList", "removeTrustListForSystemManger", "setApplicationComponentState", "cmpName", "Landroid/content/ComponentName;", "state", "setApplicationUninstallationDisabled", "setApplicationUninstallationEnabled", "setComponentLaunchedByLauncher", "launchComponent", "setDefaultDeviceAssistance", "setDefaultHome", "setDefaultLauncher", "setDisableApplication", "setEnableApplication", "setTrustListForSystemManger", "setUsageStatsPermission", "stopApp", "uninstallApplication", "wipeApplicationData", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface IApplicationPolicy {
    boolean addAppAlwaysRunningList(List<String> packageList);

    boolean addAppAutoRunningList(List<String> packageList);

    boolean addIgnoreFrequentRelaunchAppList(List<String> packageNames);

    boolean addPackageToBatteryOptimizationWhiteList(String packageName);

    boolean addPackagesToFocusMonitoringList(List<String> packageNames);

    boolean addPackagesToNotificationWhiteList(List<String> packageList);

    boolean addRuntimePermissionFixAppList(List<String> packageNames);

    void applyAllAppOpsPermissions(boolean enable);

    void applyAllAppRuntimePermissions(boolean enable);

    boolean applyRuntimePermissions(String packageName, List<String> runtimePermissions);

    void clearApplicationUninstallRule();

    boolean clearComponentLaunchedByLauncher();

    boolean clearIgnoreFrequentRelaunchAppList();

    boolean clearPackagesFromNotificationList();

    boolean clearRuntimePermissionFixAppList();

    boolean clearTrustListForSystemManger();

    void controlChangeHome(boolean isEnable);

    List<String> getAppAlwaysRunningList();

    List<String> getAppAutoRunningList();

    Map<String, Long> getAppUsage(long beginTime, long endTime);

    List<AppUsage2> getAppUsageV2(long beginTime, long endTime);

    String getComponentLaunchedByLauncher();

    List<String> getIgnoreFrequentRelaunchAppList();

    List<String> getPackagesFromBatteryOptimizationWhiteList() throws UnSupportException;

    List<String> getRunningAppProcesses();

    List<String> getRuntimePermissionFixAppList();

    String getTopAppPackageName();

    List<String> getTrustListForSystemManger();

    boolean hasRuntimePermissions(String runtimePermission);

    boolean installApplication(String apkFilePath);

    TrafficInfo queryTrafficDetailBySummary(int networkType, String subscriberId, long beginTime, long endTime);

    TrafficInfo queryTrafficDetailByUid(int networkType, String subscriberId, long beginTime, long endTime, int uid);

    boolean removeAppAlwaysRunningList(List<String> packageList);

    boolean removeAppAutoRunningList(List<String> packageList);

    boolean removeDefaultDeviceAssistance(String packageName, String className);

    boolean removeDefaultLauncher(String packageName, String className);

    boolean removeIgnoreFrequentRelaunchAppList(List<String> packageNames);

    boolean removePackageFromBatteryOptimizationWhiteList(String packageName);

    boolean removePackagesFromNotificationWhiteList(List<String> packageList);

    boolean removeRuntimePermissionFixAppList(List<String> packageNames);

    boolean removeTrustListForSystemManger(List<String> packageNames);

    boolean setApplicationComponentState(ComponentName cmpName, boolean state);

    void setApplicationUninstallationDisabled(String packageName);

    void setApplicationUninstallationEnabled(String packageName);

    boolean setComponentLaunchedByLauncher(String launchComponent);

    boolean setDefaultDeviceAssistance(String packageName, String className);

    void setDefaultHome(String packageName, String className);

    boolean setDefaultLauncher(String packageName, String className);

    boolean setDisableApplication(String packageName);

    boolean setEnableApplication(String packageName);

    boolean setTrustListForSystemManger(List<String> packageNames);

    void setUsageStatsPermission(String packageName);

    boolean stopApp(String packageName);

    boolean uninstallApplication(String packageName);

    boolean wipeApplicationData(String packageName);
}
