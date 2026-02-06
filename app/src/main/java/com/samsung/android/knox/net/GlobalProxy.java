package com.samsung.android.knox.net;

import android.app.enterprise.devicesettings.DeviceSettingsPolicy;
import com.samsung.android.knox.SupportLibUtils;

public class GlobalProxy {
    private DeviceSettingsPolicy mDeviceSettingsPolicy;

    public GlobalProxy(DeviceSettingsPolicy deviceSettingsPolicy) {
        this.mDeviceSettingsPolicy = deviceSettingsPolicy;
    }

    public ProxyProperties getGlobalProxy() {
        try {
            return ProxyProperties.convertToNew(this.mDeviceSettingsPolicy.getGlobalProxy());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(GlobalProxy.class, "getGlobalProxy", null, 17));
        }
    }

    public int setGlobalProxy(ProxyProperties proxyProperties) {
        try {
            return this.mDeviceSettingsPolicy.setGlobalProxy(ProxyProperties.convertToOld(proxyProperties));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError e2) {
            throw new NoSuchMethodError(e2.getMessage());
        }
    }
}
