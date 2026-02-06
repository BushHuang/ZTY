package com.xuehai.system.samsung.knox;

import android.content.Context;
import android.os.Build;
import com.xuehai.system.samsung.knox.SamsungDevices;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/xuehai/system/samsung/knox/SamsungDeviceProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getDevice", "Lcom/xuehai/system/samsung/knox/SamsungDevices;", "deviceMode", "", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SamsungDeviceProvider {
    private final Context context;

    public SamsungDeviceProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public static SamsungDevices getDevice$default(SamsungDeviceProvider samsungDeviceProvider, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(str, "MODEL");
        }
        return samsungDeviceProvider.getDevice(str);
    }

    public final SamsungDevices getDevice(String deviceMode) {
        Intrinsics.checkNotNullParameter(deviceMode, "deviceMode");
        return new SamsungDevices.ALL(this.context);
    }
}
