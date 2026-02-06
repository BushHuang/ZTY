package com.xuehai.system.huawei_c5;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import com.xuehai.system.huawei_c5.HuaWeiHEMDevices;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/huawei_c5/HuaWeiHEMDeviceProvider;", "", "context", "Landroid/content/Context;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/Context;Landroid/content/ComponentName;)V", "getDevice", "Lcom/xuehai/system/huawei_c5/HuaWeiHEMDevices;", "deviceMode", "", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HuaWeiHEMDeviceProvider {
    private final ComponentName componentName;
    private final Context context;

    public HuaWeiHEMDeviceProvider(Context context, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.context = context;
        this.componentName = componentName;
    }

    public static HuaWeiHEMDevices getDevice$default(HuaWeiHEMDeviceProvider huaWeiHEMDeviceProvider, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(str, "MODEL");
        }
        return huaWeiHEMDeviceProvider.getDevice(str);
    }

    public final HuaWeiHEMDevices getDevice(String deviceMode) {
        Intrinsics.checkNotNullParameter(deviceMode, "deviceMode");
        return new HuaWeiHEMDevices.HuaWeiHEM(this.context, this.componentName);
    }
}
