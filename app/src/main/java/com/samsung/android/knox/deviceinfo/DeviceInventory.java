package com.samsung.android.knox.deviceinfo;

import android.app.enterprise.MiscPolicy;
import com.samsung.android.knox.SupportLibUtils;

public class DeviceInventory {
    public static final String ACTION_SIM_CARD_CHANGED = "com.samsung.android.knox.intent.action.SIM_CARD_CHANGED";
    public static final String EXTRA_SIM_CHANGE_INFO = "com.samsung.android.knox.intent.extra.SIM_CHANGE_INFO";
    private android.app.enterprise.DeviceInventory mDeviceInventory;
    private MiscPolicy mMiscPolicy;

    public DeviceInventory(android.app.enterprise.DeviceInventory deviceInventory, MiscPolicy miscPolicy) {
        this.mDeviceInventory = deviceInventory;
        this.mMiscPolicy = miscPolicy;
    }

    public long getAvailableCapacityExternal() {
        return this.mDeviceInventory.getAvailableCapacityExternal();
    }

    public long getAvailableCapacityInternal() {
        return this.mDeviceInventory.getAvailableCapacityInternal();
    }

    public String getDeviceOS() {
        return this.mDeviceInventory.getDeviceOS();
    }

    public String getDeviceOSVersion() {
        return this.mDeviceInventory.getDeviceOSVersion();
    }

    public int getDroppedCallsCount() {
        return this.mDeviceInventory.getDroppedCallsCount();
    }

    public SimChangeInfo getLastSimChangeInfo() {
        return SimChangeInfo.convertToNew(this.mMiscPolicy.getLastSimChangeInfo());
    }

    public int getMissedCallsCount() {
        return this.mDeviceInventory.getMissedCallsCount();
    }

    public String getSalesCode() {
        try {
            return this.mDeviceInventory.getSalesCode();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(DeviceInventory.class, "getSalesCode", null, 20));
        }
    }

    public String getSerialNumber() {
        return this.mDeviceInventory.getSerialNumber();
    }

    public int getSuccessCallsCount() {
        return this.mDeviceInventory.getSuccessCallsCount();
    }

    public long getTotalCapacityExternal() {
        return this.mDeviceInventory.getTotalCapacityExternal();
    }

    public long getTotalCapacityInternal() {
        return this.mDeviceInventory.getTotalCapacityInternal();
    }

    public boolean isDeviceLocked() {
        return this.mDeviceInventory.isDeviceLocked();
    }

    public boolean isDeviceSecure() {
        return this.mDeviceInventory.isDeviceSecure();
    }

    public boolean resetCallsCount() {
        return this.mDeviceInventory.resetCallsCount();
    }
}
