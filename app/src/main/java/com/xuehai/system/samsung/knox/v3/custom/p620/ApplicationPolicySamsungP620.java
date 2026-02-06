package com.xuehai.system.samsung.knox.v3.custom.p620;

import android.content.Context;
import com.b2b.rom.SamsungDevice;
import com.xuehai.system.samsung.knox.v3.ApplicationPolicySamsungV3;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000bH\u0016J\u0016\u0010\u000f\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p620/ApplicationPolicySamsungP620;", "Lcom/xuehai/system/samsung/knox/v3/ApplicationPolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "samsungDevice", "Lcom/b2b/rom/SamsungDevice;", "addAppAutoRunningList", "", "packageList", "", "", "getAppAutoRunningList", "getRunningAppProcesses", "getTopAppPackageName", "removeAppAutoRunningList", "setDefaultHome", "", "packageName", "className", "setUsageStatsPermission", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class ApplicationPolicySamsungP620 extends ApplicationPolicySamsungV3 {
    private final SamsungDevice samsungDevice;

    public ApplicationPolicySamsungP620(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        SamsungDevice samsungDevice = SamsungDevice.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(samsungDevice, "getInstance(context)");
        this.samsungDevice = samsungDevice;
    }

    @Override
    public boolean addAppAutoRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        return this.samsungDevice.addAppAutoRunningList(packageList);
    }

    @Override
    public List<String> getAppAutoRunningList() {
        List<String> appAutoRunningList = this.samsungDevice.getAppAutoRunningList();
        Intrinsics.checkNotNullExpressionValue(appAutoRunningList, "samsungDevice.appAutoRunningList");
        return appAutoRunningList;
    }

    @Override
    public List<String> getRunningAppProcesses() {
        List<String> runningTask = this.samsungDevice.getRunningTask();
        if (!(runningTask instanceof List)) {
            runningTask = null;
        }
        return runningTask == null ? CollectionsKt.emptyList() : runningTask;
    }

    @Override
    public String getTopAppPackageName() {
        return this.samsungDevice.getForegroundApp();
    }

    @Override
    public boolean removeAppAutoRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        return this.samsungDevice.removeAppAutoRunningList(packageList);
    }

    @Override
    public void setDefaultHome(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        setDefaultLauncher(packageName, className);
    }

    @Override
    public void setUsageStatsPermission(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.samsungDevice.setUsageStatsPermission(packageName);
    }
}
