package com.xuehai.system.huawei_c5;

import android.content.ComponentName;
import com.huawei.android.app.admin.DeviceControlManager;
import com.huawei.android.app.admin.DeviceNetworkManager;
import com.huawei.android.app.admin.DeviceSettingsManager;
import com.huawei.android.app.admin.DeviceWifiPolicyManager;
import com.xuehai.system.common.compat.WifiPolicyDefault;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/xuehai/system/huawei_c5/WifiPolicyHWHEM;", "Lcom/xuehai/system/common/compat/WifiPolicyDefault;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/ComponentName;)V", "isRandomMacDisabled", "", "isWIFIEditDisabled", "resetNetworkSetting", "setRandomMacDisabled", "isDisabled", "setWIFIEditDisabled", "enable", "setWiFiState", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WifiPolicyHWHEM extends WifiPolicyDefault {
    private final ComponentName componentName;

    public WifiPolicyHWHEM(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.componentName = componentName;
    }

    @Override
    public boolean isRandomMacDisabled() {
        return new DeviceWifiPolicyManager().isRandomMacDisabled(this.componentName);
    }

    @Override
    public boolean isWIFIEditDisabled() {
        return new DeviceSettingsManager().isWIFIeditDisabled(this.componentName);
    }

    @Override
    public boolean resetNetworkSetting() {
        new DeviceNetworkManager().resetNetworkSetting(this.componentName);
        return true;
    }

    @Override
    public boolean setRandomMacDisabled(boolean isDisabled) {
        return new DeviceWifiPolicyManager().setRandomMacDisabled(this.componentName, isDisabled);
    }

    @Override
    public boolean setWIFIEditDisabled(boolean enable) {
        ArrayList arrayList = new ArrayList();
        if (!enable) {
            arrayList.add("wifi_proxy");
        }
        new DeviceControlManager().setCustomSettingsMenu(this.componentName, arrayList);
        return new DeviceSettingsManager().setWIFIeditDisabled(this.componentName, !enable);
    }

    @Override
    public boolean setWiFiState(boolean enable) {
        return new DeviceWifiPolicyManager().turnOnWifi(this.componentName, enable);
    }
}
