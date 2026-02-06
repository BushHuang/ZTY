package com.xuehai.system.samsung.knox.v3.custom;

import android.content.Context;
import com.b2b.rom.ISamsungDevice;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/SamsungCustomSDK;", "", "()V", "samsungDevice", "Lcom/b2b/rom/ISamsungDevice;", "context", "Landroid/content/Context;", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SamsungCustomSDK {
    public static final SamsungCustomSDK INSTANCE = new SamsungCustomSDK();
    private static ISamsungDevice samsungDevice;

    private SamsungCustomSDK() {
    }

    public final ISamsungDevice samsungDevice(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ISamsungDevice iSamsungDevice = samsungDevice;
        if (iSamsungDevice != null) {
            return iSamsungDevice;
        }
        ISamsungDevice iSamsungDevice2 = new ISamsungDevice(context);
        samsungDevice = iSamsungDevice2;
        return iSamsungDevice2;
    }
}
