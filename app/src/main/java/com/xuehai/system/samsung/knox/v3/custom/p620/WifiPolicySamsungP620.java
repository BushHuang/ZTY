package com.xuehai.system.samsung.knox.v3.custom.p620;

import android.content.Context;
import com.b2b.rom.SamsungDevice;
import com.xuehai.system.samsung.knox.v3.WifiPolicySamsungV3;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p620/WifiPolicySamsungP620;", "Lcom/xuehai/system/samsung/knox/v3/WifiPolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "samsungDevice", "Lcom/b2b/rom/SamsungDevice;", "resetNetworkSetting", "", "setNetworkSpeedState", "state", "setWiFiState", "enable", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class WifiPolicySamsungP620 extends WifiPolicySamsungV3 {
    private final SamsungDevice samsungDevice;

    public WifiPolicySamsungP620(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        SamsungDevice samsungDevice = SamsungDevice.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(samsungDevice, "getInstance(context)");
        this.samsungDevice = samsungDevice;
    }

    @Override
    public boolean resetNetworkSetting() {
        this.samsungDevice.networkReset();
        return true;
    }

    @Override
    public boolean setNetworkSpeedState(boolean state) {
        return this.samsungDevice.setNetworkSpeedState(state);
    }

    @Override
    public boolean setWiFiState(boolean enable) {
        return this.samsungDevice.setWifiEnabled(enable);
    }
}
