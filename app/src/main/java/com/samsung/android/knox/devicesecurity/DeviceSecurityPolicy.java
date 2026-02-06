package com.samsung.android.knox.devicesecurity;

import android.app.enterprise.MiscPolicy;
import android.app.enterprise.SecurityPolicy;
import android.content.ComponentName;

public class DeviceSecurityPolicy {
    public static final int WIPE_EXTERNAL_MEMORY = 2;
    public static final int WIPE_INTERNAL_EXTERNAL_MEMORY = 3;
    public static final int WIPE_INTERNAL_MEMORY = 1;
    private MiscPolicy mMiscPolicy;
    private SecurityPolicy mSecurityPolicy;

    public DeviceSecurityPolicy(MiscPolicy miscPolicy, SecurityPolicy securityPolicy) {
        this.mMiscPolicy = miscPolicy;
        this.mSecurityPolicy = securityPolicy;
    }

    public boolean addClipboardTextData(String str) {
        return this.mMiscPolicy.addClipboardTextData(str);
    }

    public boolean clearClipboardData() {
        return this.mMiscPolicy.clearClipboardData();
    }

    public String getClipboardTextData() {
        return this.mMiscPolicy.getClipboardTextData();
    }

    public boolean getRequireDeviceEncryption(ComponentName componentName) {
        return this.mSecurityPolicy.getRequireDeviceEncryption(componentName);
    }

    public boolean getRequireStorageCardEncryption(ComponentName componentName) {
        return this.mSecurityPolicy.getRequireStorageCardEncryption(componentName);
    }

    public boolean isExternalStorageEncrypted() {
        return this.mSecurityPolicy.isExternalStorageEncrypted();
    }

    public boolean isInternalStorageEncrypted() {
        return this.mSecurityPolicy.isInternalStorageEncrypted();
    }

    public void setExternalStorageEncryption(boolean z) {
        this.mSecurityPolicy.setExternalStorageEncryption(z);
    }

    public void setInternalStorageEncryption(boolean z) {
        this.mSecurityPolicy.setInternalStorageEncryption(z);
    }

    public void setRequireDeviceEncryption(ComponentName componentName, boolean z) {
        this.mSecurityPolicy.setRequireDeviceEncryption(componentName, z);
    }

    public void setRequireStorageCardEncryption(ComponentName componentName, boolean z) {
        this.mSecurityPolicy.setRequireStorageCardEncryption(componentName, z);
    }

    public boolean wipeDevice(int i) {
        return this.mSecurityPolicy.wipeDevice(i);
    }
}
