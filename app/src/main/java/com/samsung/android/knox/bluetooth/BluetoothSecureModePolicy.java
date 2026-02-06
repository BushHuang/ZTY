package com.samsung.android.knox.bluetooth;

import java.util.List;

public class BluetoothSecureModePolicy {
    private android.app.enterprise.BluetoothSecureModePolicy mBluetoothSecureModePolicy;

    public BluetoothSecureModePolicy(android.app.enterprise.BluetoothSecureModePolicy bluetoothSecureModePolicy) {
        this.mBluetoothSecureModePolicy = bluetoothSecureModePolicy;
    }

    public boolean addBluetoothDevicesToWhiteList(List<BluetoothSecureModeWhitelistConfig> list) {
        return this.mBluetoothSecureModePolicy.addBluetoothDevicesToWhiteList(BluetoothSecureModeWhitelistConfig.convertToOldList(list));
    }

    public boolean disableSecureMode() {
        return this.mBluetoothSecureModePolicy.disableSecureMode();
    }

    public boolean enableDeviceWhiteList(boolean z) {
        return this.mBluetoothSecureModePolicy.enableDeviceWhiteList(z);
    }

    public boolean enableSecureMode(BluetoothSecureModeConfig bluetoothSecureModeConfig, List<BluetoothSecureModeWhitelistConfig> list) {
        return this.mBluetoothSecureModePolicy.enableSecureMode(BluetoothSecureModeConfig.convertToOld(bluetoothSecureModeConfig), BluetoothSecureModeWhitelistConfig.convertToOldList(list));
    }

    public List<BluetoothSecureModeWhitelistConfig> getBluetoothDevicesFromWhiteList() {
        return BluetoothSecureModeWhitelistConfig.convertToNewList(this.mBluetoothSecureModePolicy.getBluetoothDevicesFromWhiteList());
    }

    public BluetoothSecureModeConfig getSecureModeConfiguration() {
        return BluetoothSecureModeConfig.convertToNew(this.mBluetoothSecureModePolicy.getSecureModeConfiguration());
    }

    public boolean isDeviceWhiteListEnabled() {
        return this.mBluetoothSecureModePolicy.isDeviceWhiteListEnabled();
    }

    public boolean isSecureModeEnabled() {
        return this.mBluetoothSecureModePolicy.isSecureModeEnabled();
    }

    public boolean removeBluetoothDevicesFromWhiteList(List<BluetoothSecureModeWhitelistConfig> list) {
        return this.mBluetoothSecureModePolicy.removeBluetoothDevicesFromWhiteList(BluetoothSecureModeWhitelistConfig.convertToOldList(list));
    }
}
