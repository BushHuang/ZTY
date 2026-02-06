package com.xuehai.system.samsung.knox.v3.custom.p615;

import android.content.Context;
import com.xuehai.system.samsung.knox.v3.custom.p200.ApplicationPolicySamsungP200;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000bH\u0016¨\u0006\u0011"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p615/ApplicationPolicySamsungP615;", "Lcom/xuehai/system/samsung/knox/v3/custom/p200/ApplicationPolicySamsungP200;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "controlChangeHome", "", "isEnable", "", "getRunningAppProcesses", "", "", "getTopAppPackageName", "setDefaultHome", "packageName", "className", "setUsageStatsPermission", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class ApplicationPolicySamsungP615 extends ApplicationPolicySamsungP200 {
    public ApplicationPolicySamsungP615(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public void controlChangeHome(boolean isEnable) {
        samsungDevice().controlChangeHome(isEnable);
    }

    @Override
    public List<String> getRunningAppProcesses() {
        List<String> runningTask = samsungDevice().getRunningTask();
        if (!(runningTask instanceof List)) {
            runningTask = null;
        }
        return runningTask == null ? CollectionsKt.emptyList() : runningTask;
    }

    @Override
    public String getTopAppPackageName() {
        return samsungDevice().getForegroundApp();
    }

    @Override
    public void setDefaultHome(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        samsungDevice().setDefaultHome(packageName, className);
    }

    @Override
    public void setUsageStatsPermission(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        samsungDevice().setUsageStatsPermission(packageName);
    }
}
