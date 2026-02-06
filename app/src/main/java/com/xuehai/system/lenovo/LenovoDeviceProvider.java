package com.xuehai.system.lenovo;

import android.content.Context;
import android.os.Build;
import com.xuehai.system.lenovo.LenovoDevices;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/xuehai/system/lenovo/LenovoDeviceProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getDevice", "Lcom/xuehai/system/lenovo/LenovoDevices;", "deviceMode", "", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LenovoDeviceProvider {
    private final Context context;

    public LenovoDeviceProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public static LenovoDevices getDevice$default(LenovoDeviceProvider lenovoDeviceProvider, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(str, "MODEL");
        }
        return lenovoDeviceProvider.getDevice(str);
    }

    public final LenovoDevices getDevice(String deviceMode) {
        Intrinsics.checkNotNullParameter(deviceMode, "deviceMode");
        int iHashCode = deviceMode.hashCode();
        if (iHashCode != -1279835531) {
            if (iHashCode != -1157732590) {
                if (iHashCode == -1128161597 && deviceMode.equals("Lenovo TB-X6C6F")) {
                    return new LenovoDevices.TB_X6C6F(this.context);
                }
            } else if (deviceMode.equals("Lenovo TB-8604F")) {
                return new LenovoDevices.TB_8604F(this.context);
            }
        } else if (deviceMode.equals("Lenovo TB223FC")) {
            return new LenovoDevices.TB223FC(this.context);
        }
        return new LenovoDevices.OTHER(this.context);
    }
}
