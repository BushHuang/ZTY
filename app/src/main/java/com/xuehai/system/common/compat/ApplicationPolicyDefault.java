package com.xuehai.system.common.compat;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.os.RemoteException;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.xuehai.system.common.UnSupportException;
import com.xuehai.system.common.appusage.AppUsageHelper;
import com.xuehai.system.common.policies.IApplicationPolicy;
import com.xuehai.system.mdm.AppUsage2;
import com.xuehai.system.mdm.TrafficInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\n\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u000b\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0016\u0010\u000f\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u0010\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u0011\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J\u001e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\t2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0016J\b\u0010\u0019\u001a\u00020\u0006H\u0016J\b\u0010\u001a\u001a\u00020\u0006H\u0016J\b\u0010\u001b\u001a\u00020\u0006H\u0016J\b\u0010\u001c\u001a\u00020\u0006H\u0016J\b\u0010\u001d\u001a\u00020\u0006H\u0016J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0006H\u0016J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J&\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020$\u0018\u00010#2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0016J\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\b2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0016J\b\u0010)\u001a\u00020\tH\u0016J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010+\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\n\u0010.\u001a\u0004\u0018\u00010\tH\u0016J\u000e\u0010/\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0010\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020\tH\u0016J\u0010\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\tH\u0016J,\u00104\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\t2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0016J4\u00109\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\t2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010:\u001a\u000207H\u0016J\u0016\u0010;\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010<\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0018\u0010=\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010>\u001a\u00020\tH\u0016J\u0018\u0010?\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010>\u001a\u00020\tH\u0016J\u0016\u0010@\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0010\u0010A\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0016\u0010B\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010C\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010D\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0018\u0010E\u001a\u00020\u00062\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\u0006H\u0016J\u0010\u0010I\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0010\u0010J\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0010\u0010K\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\tH\u0016J\u0018\u0010M\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010>\u001a\u00020\tH\u0016J\u0018\u0010N\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010>\u001a\u00020\tH\u0016J\u0018\u0010O\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010>\u001a\u00020\tH\u0016J\u0010\u0010P\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0010\u0010Q\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0016\u0010R\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0010\u0010S\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0010\u0010T\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0010\u0010U\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0010\u0010V\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Lcom/xuehai/system/common/compat/ApplicationPolicyDefault;", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "addAppAlwaysRunningList", "", "packageList", "", "", "addAppAutoRunningList", "addIgnoreFrequentRelaunchAppList", "packageNames", "addPackageToBatteryOptimizationWhiteList", "packageName", "addPackagesToFocusMonitoringList", "addPackagesToNotificationWhiteList", "addRuntimePermissionFixAppList", "applyAllAppOpsPermissions", "", "enable", "applyAllAppRuntimePermissions", "applyRuntimePermissions", "runtimePermissions", "clearApplicationUninstallRule", "clearComponentLaunchedByLauncher", "clearIgnoreFrequentRelaunchAppList", "clearPackagesFromNotificationList", "clearRuntimePermissionFixAppList", "clearTrustListForSystemManger", "controlChangeHome", "isEnable", "getAppAlwaysRunningList", "getAppAutoRunningList", "getAppUsage", "", "", "beginTime", "endTime", "getAppUsageV2", "Lcom/xuehai/system/mdm/AppUsage2;", "getComponentLaunchedByLauncher", "getIgnoreFrequentRelaunchAppList", "getPackagesFromBatteryOptimizationWhiteList", "getRunningAppProcesses", "getRuntimePermissionFixAppList", "getTopAppPackageName", "getTrustListForSystemManger", "hasRuntimePermissions", "runtimePermission", "installApplication", "apkFilePath", "queryTrafficDetailBySummary", "Lcom/xuehai/system/mdm/TrafficInfo;", "networkType", "", "subscriberId", "queryTrafficDetailByUid", "uid", "removeAppAlwaysRunningList", "removeAppAutoRunningList", "removeDefaultDeviceAssistance", "className", "removeDefaultLauncher", "removeIgnoreFrequentRelaunchAppList", "removePackageFromBatteryOptimizationWhiteList", "removePackagesFromNotificationWhiteList", "removeRuntimePermissionFixAppList", "removeTrustListForSystemManger", "setApplicationComponentState", "cmpName", "Landroid/content/ComponentName;", "state", "setApplicationUninstallationDisabled", "setApplicationUninstallationEnabled", "setComponentLaunchedByLauncher", "launchComponent", "setDefaultDeviceAssistance", "setDefaultHome", "setDefaultLauncher", "setDisableApplication", "setEnableApplication", "setTrustListForSystemManger", "setUsageStatsPermission", "stopApp", "uninstallApplication", "wipeApplicationData", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class ApplicationPolicyDefault implements IApplicationPolicy {
    private final Context context;

    public ApplicationPolicyDefault(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override
    public boolean addAppAlwaysRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        throw new UnSupportException();
    }

    @Override
    public boolean addAppAutoRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        throw new UnSupportException();
    }

    @Override
    public boolean addIgnoreFrequentRelaunchAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return false;
    }

    @Override
    public boolean addPackageToBatteryOptimizationWhiteList(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        throw new UnSupportException();
    }

    @Override
    public boolean addPackagesToFocusMonitoringList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        throw new UnSupportException();
    }

    @Override
    public boolean addPackagesToNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        throw new UnSupportException();
    }

    @Override
    public boolean addRuntimePermissionFixAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        throw new UnSupportException();
    }

    @Override
    public void applyAllAppOpsPermissions(boolean enable) {
    }

    @Override
    public void applyAllAppRuntimePermissions(boolean enable) {
    }

    @Override
    public boolean applyRuntimePermissions(String packageName, List<String> runtimePermissions) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(runtimePermissions, "runtimePermissions");
        return false;
    }

    @Override
    public void clearApplicationUninstallRule() {
    }

    @Override
    public boolean clearComponentLaunchedByLauncher() {
        return false;
    }

    @Override
    public boolean clearIgnoreFrequentRelaunchAppList() {
        return true;
    }

    @Override
    public boolean clearPackagesFromNotificationList() {
        throw new UnSupportException();
    }

    @Override
    public boolean clearRuntimePermissionFixAppList() {
        return true;
    }

    @Override
    public boolean clearTrustListForSystemManger() {
        return true;
    }

    @Override
    public void controlChangeHome(boolean isEnable) {
    }

    @Override
    public List<String> getAppAlwaysRunningList() {
        throw new UnSupportException();
    }

    @Override
    public List<String> getAppAutoRunningList() {
        throw new UnSupportException();
    }

    @Override
    public Map<String, Long> getAppUsage(long beginTime, long endTime) {
        HashMap map;
        if (!AppUsageHelper.INSTANCE.checkAppUsagePermission(this.context)) {
            return (Map) null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            map = new HashMap();
            List<AppUsageHelper.Stat> dailyStatsV2 = AppUsageHelper.INSTANCE.getDailyStatsV2(this.context, beginTime, endTime);
            ArrayList<AppUsageHelper.Stat> arrayList = new ArrayList();
            for (Object obj : dailyStatsV2) {
                if (((AppUsageHelper.Stat) obj).getTotalTime() > 0) {
                    arrayList.add(obj);
                }
            }
            for (AppUsageHelper.Stat stat : arrayList) {
                map.put(stat.getPackageName(), Long.valueOf(stat.getTotalTime()));
            }
        } else {
            map = (HashMap) null;
        }
        return map;
    }

    @Override
    public List<AppUsage2> getAppUsageV2(long beginTime, long endTime) {
        if (AppUsageHelper.INSTANCE.checkAppUsagePermission(this.context) && Build.VERSION.SDK_INT >= 26) {
            List<AppUsageHelper.Stat> dailyStatsV2 = AppUsageHelper.INSTANCE.getDailyStatsV2(this.context, beginTime, endTime);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(dailyStatsV2, 10));
            for (AppUsageHelper.Stat stat : dailyStatsV2) {
                arrayList.add(new AppUsage2(stat.getPackageName(), stat.getTotalTime(), stat.getLastTimeUsed()));
            }
            return arrayList;
        }
        return CollectionsKt.emptyList();
    }

    @Override
    public String getComponentLaunchedByLauncher() {
        return "";
    }

    @Override
    public List<String> getIgnoreFrequentRelaunchAppList() {
        return new ArrayList();
    }

    @Override
    public List<String> getPackagesFromBatteryOptimizationWhiteList() {
        throw new UnSupportException();
    }

    @Override
    public List<String> getRunningAppProcesses() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        HashSet hashSet = new HashSet();
        ActivityManager activityManager = (ActivityManager) this.context.getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    hashSet.add(runningAppProcessInfo.processName);
                }
            }
        }
        return CollectionsKt.toList(hashSet);
    }

    @Override
    public List<String> getRuntimePermissionFixAppList() {
        return CollectionsKt.emptyList();
    }

    @Override
    public String getTopAppPackageName() {
        return AppUsageHelper.INSTANCE.checkAppUsagePermission(this.context) ? AppUsageHelper.INSTANCE.getTopActivityPackageName(this.context) : "";
    }

    @Override
    public List<String> getTrustListForSystemManger() {
        throw new UnSupportException();
    }

    @Override
    public boolean hasRuntimePermissions(String runtimePermission) {
        Intrinsics.checkNotNullParameter(runtimePermission, "runtimePermission");
        return ContextCompat.checkSelfPermission(this.context, runtimePermission) == 0;
    }

    @Override
    public boolean installApplication(String apkFilePath) {
        Uri uriFromFile;
        Intrinsics.checkNotNullParameter(apkFilePath, "apkFilePath");
        File file = new File(apkFilePath);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.addCategory("android.intent.category.DEFAULT");
        if (Build.VERSION.SDK_INT >= 24) {
            uriFromFile = FileProvider.getUriForFile(this.context, this.context.getPackageName() + ".mdm.fileProvider", file);
            Intrinsics.checkNotNullExpressionValue(uriFromFile, "getUriForFile(context, \"….mdm.fileProvider\", file)");
            intent.addFlags(1);
        } else {
            uriFromFile = Uri.fromFile(file);
            Intrinsics.checkNotNullExpressionValue(uriFromFile, "fromFile(file)");
        }
        intent.setDataAndType(uriFromFile, "application/vnd.android.package-archive");
        Context context = this.context;
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, 2);
        } else {
            context.startActivity(intent);
        }
        return true;
    }

    @Override
    public TrafficInfo queryTrafficDetailBySummary(int networkType, String subscriberId, long beginTime, long endTime) throws RemoteException, SecurityException {
        if (!AppUsageHelper.INSTANCE.checkAppUsagePermission(this.context) || Build.VERSION.SDK_INT < 23) {
            return null;
        }
        TrafficInfo trafficInfo = new TrafficInfo();
        Object systemService = this.context.getSystemService("netstats");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.usage.NetworkStatsManager");
        }
        NetworkStats networkStatsQuerySummary = ((NetworkStatsManager) systemService).querySummary(networkType, subscriberId, beginTime, endTime);
        do {
            NetworkStats.Bucket bucket = new NetworkStats.Bucket();
            networkStatsQuerySummary.getNextBucket(bucket);
            trafficInfo.setRxBytes(trafficInfo.getRxBytes() + bucket.getRxBytes());
            trafficInfo.setTxBytes(trafficInfo.getTxBytes() + bucket.getTxBytes());
        } while (networkStatsQuerySummary.hasNextBucket());
        trafficInfo.setTotalData(trafficInfo.getRxBytes() + trafficInfo.getTxBytes());
        return trafficInfo;
    }

    @Override
    public TrafficInfo queryTrafficDetailByUid(int networkType, String subscriberId, long beginTime, long endTime, int uid) throws SecurityException {
        if (!AppUsageHelper.INSTANCE.checkAppUsagePermission(this.context) || Build.VERSION.SDK_INT < 23) {
            return null;
        }
        TrafficInfo trafficInfo = new TrafficInfo();
        Object systemService = this.context.getSystemService("netstats");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.usage.NetworkStatsManager");
        }
        NetworkStats networkStatsQueryDetailsForUid = ((NetworkStatsManager) systemService).queryDetailsForUid(networkType, subscriberId, beginTime, endTime, uid);
        do {
            NetworkStats.Bucket bucket = new NetworkStats.Bucket();
            networkStatsQueryDetailsForUid.getNextBucket(bucket);
            trafficInfo.setRxBytes(trafficInfo.getRxBytes() + bucket.getRxBytes());
            trafficInfo.setTxBytes(trafficInfo.getTxBytes() + bucket.getTxBytes());
        } while (networkStatsQueryDetailsForUid.hasNextBucket());
        trafficInfo.setUid(uid);
        trafficInfo.setTotalData(trafficInfo.getRxBytes() + trafficInfo.getTxBytes());
        return trafficInfo;
    }

    @Override
    public boolean removeAppAlwaysRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        throw new UnSupportException();
    }

    @Override
    public boolean removeAppAutoRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        throw new UnSupportException();
    }

    @Override
    public boolean removeDefaultDeviceAssistance(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        return true;
    }

    @Override
    public boolean removeDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        throw new UnSupportException();
    }

    @Override
    public boolean removeIgnoreFrequentRelaunchAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return true;
    }

    @Override
    public boolean removePackageFromBatteryOptimizationWhiteList(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        throw new UnSupportException();
    }

    @Override
    public boolean removePackagesFromNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        throw new UnSupportException();
    }

    @Override
    public boolean removeRuntimePermissionFixAppList(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return true;
    }

    @Override
    public boolean removeTrustListForSystemManger(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return true;
    }

    @Override
    public boolean setApplicationComponentState(ComponentName cmpName, boolean state) {
        Intrinsics.checkNotNullParameter(cmpName, "cmpName");
        throw new UnSupportException();
    }

    @Override
    public void setApplicationUninstallationDisabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        throw new UnSupportException();
    }

    @Override
    public void setApplicationUninstallationEnabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        throw new UnSupportException();
    }

    @Override
    public boolean setComponentLaunchedByLauncher(String launchComponent) {
        Intrinsics.checkNotNullParameter(launchComponent, "launchComponent");
        return false;
    }

    @Override
    public boolean setDefaultDeviceAssistance(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        return true;
    }

    @Override
    public void setDefaultHome(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
    }

    @Override
    public boolean setDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        throw new UnSupportException();
    }

    @Override
    public boolean setDisableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        throw new UnSupportException();
    }

    @Override
    public boolean setEnableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        throw new UnSupportException();
    }

    @Override
    public boolean setTrustListForSystemManger(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        throw new UnSupportException();
    }

    @Override
    public void setUsageStatsPermission(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
    }

    @Override
    public boolean stopApp(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        throw new UnSupportException();
    }

    @Override
    public boolean uninstallApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + packageName));
        intent.setFlags(268435456);
        this.context.startActivity(intent);
        return true;
    }

    @Override
    public boolean wipeApplicationData(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        throw new UnSupportException();
    }
}
