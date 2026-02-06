package com.samsung.android.knox.nfc;

import android.app.enterprise.devicesettings.DeviceSettingsPolicy;

public class NfcPolicy {
    private DeviceSettingsPolicy mDeviceSettingsPolicy;

    public NfcPolicy(DeviceSettingsPolicy deviceSettingsPolicy) {
        this.mDeviceSettingsPolicy = deviceSettingsPolicy;
    }

    public boolean allowNFCStateChange(boolean z) {
        return this.mDeviceSettingsPolicy.allowNFCStateChange(z);
    }

    public boolean isNFCStarted() {
        return this.mDeviceSettingsPolicy.isNFCStarted();
    }

    public boolean isNFCStateChangeAllowed() {
        return this.mDeviceSettingsPolicy.isNFCStateChangeAllowed();
    }

    public boolean startNFC(boolean z) {
        return this.mDeviceSettingsPolicy.startNFC(z);
    }
}
