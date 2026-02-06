package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import android.os.Build;
import com.xuehai.system.samsung.knox.v3.SamsungDevicesV3;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/SamsungDeviceProviderV3;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getDevice", "Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3;", "deviceMode", "", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SamsungDeviceProviderV3 {
    private final Context context;

    public SamsungDeviceProviderV3(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public static SamsungDevicesV3 getDevice$default(SamsungDeviceProviderV3 samsungDeviceProviderV3, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(str, "MODEL");
        }
        return samsungDeviceProviderV3.getDevice(str);
    }

    public final SamsungDevicesV3 getDevice(String deviceMode) {
        Intrinsics.checkNotNullParameter(deviceMode, "deviceMode");
        int iHashCode = deviceMode.hashCode();
        if (iHashCode != -1398169867) {
            if (iHashCode != -1398165961) {
                if (iHashCode == -393472570 && deviceMode.equals("SM-P615C")) {
                    return new SamsungDevicesV3.SM_P615C(this.context);
                }
            } else if (deviceMode.equals("SM-P620")) {
                return new SamsungDevicesV3.SM_P620(this.context);
            }
        } else if (deviceMode.equals("SM-P200")) {
            return new SamsungDevicesV3.SM_P200(this.context);
        }
        return new SamsungDevicesV3.OTHER(this.context);
    }
}
