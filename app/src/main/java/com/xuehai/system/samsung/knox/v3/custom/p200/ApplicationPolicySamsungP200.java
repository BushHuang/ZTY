package com.xuehai.system.samsung.knox.v3.custom.p200;

import android.content.Context;
import com.b2b.rom.ISamsungDevice;
import com.xuehai.system.samsung.knox.v3.ApplicationPolicySamsungV3;
import com.xuehai.system.samsung.knox.v3.custom.SamsungCustomSDK;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\n\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\r\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u000e\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p200/ApplicationPolicySamsungP200;", "Lcom/xuehai/system/samsung/knox/v3/ApplicationPolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "addAppAutoRunningList", "", "packageList", "", "", "addPackagesToNotificationWhiteList", "clearPackagesFromNotificationList", "getAppAutoRunningList", "removeAppAutoRunningList", "removePackagesFromNotificationWhiteList", "samsungDevice", "Lcom/b2b/rom/ISamsungDevice;", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class ApplicationPolicySamsungP200 extends ApplicationPolicySamsungV3 {
    private final Context context;

    public ApplicationPolicySamsungP200(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override
    public boolean addAppAutoRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        return samsungDevice().addAppAutoRunningList(packageList);
    }

    @Override
    public boolean addPackagesToNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        return samsungDevice().addPackagesToNotificationWhiteList(packageList);
    }

    @Override
    public boolean clearPackagesFromNotificationList() {
        return samsungDevice().clearPackagesFromNotificationWhiteList();
    }

    @Override
    public List<String> getAppAutoRunningList() {
        List<String> appAutoRunningList = samsungDevice().getAppAutoRunningList();
        return appAutoRunningList == null ? CollectionsKt.emptyList() : appAutoRunningList;
    }

    @Override
    public boolean removeAppAutoRunningList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        return samsungDevice().removeAppAutoRunningList(packageList);
    }

    @Override
    public boolean removePackagesFromNotificationWhiteList(List<String> packageList) {
        Intrinsics.checkNotNullParameter(packageList, "packageList");
        return samsungDevice().removePackagesFromNotificationWhiteList(packageList);
    }

    public final ISamsungDevice samsungDevice() {
        return SamsungCustomSDK.INSTANCE.samsungDevice(this.context);
    }
}
