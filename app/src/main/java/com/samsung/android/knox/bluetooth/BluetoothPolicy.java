package com.samsung.android.knox.bluetooth;

import android.app.enterprise.RestrictionPolicy;
import java.util.List;

public class BluetoothPolicy {
    private android.app.enterprise.BluetoothPolicy mBluetoothPolicy;
    private RestrictionPolicy mRestrictionPolicy;

    public BluetoothPolicy(android.app.enterprise.BluetoothPolicy bluetoothPolicy, RestrictionPolicy restrictionPolicy) {
        this.mBluetoothPolicy = bluetoothPolicy;
        this.mRestrictionPolicy = restrictionPolicy;
    }

    public boolean activateBluetoothDeviceRestriction(boolean z) {
        return this.mBluetoothPolicy.activateBluetoothDeviceRestriction(z);
    }

    public boolean activateBluetoothUUIDRestriction(boolean z) {
        return this.mBluetoothPolicy.activateBluetoothUUIDRestriction(z);
    }

    public boolean addBluetoothDevicesToBlackList(List<String> list) {
        return this.mBluetoothPolicy.addBluetoothDevicesToBlackList(list);
    }

    public boolean addBluetoothDevicesToWhiteList(List<String> list) {
        return this.mBluetoothPolicy.addBluetoothDevicesToWhiteList(list);
    }

    public boolean addBluetoothDevicesToWhiteList(List<String> list, boolean z) {
        return this.mBluetoothPolicy.addBluetoothDevicesToWhiteList(list, z);
    }

    public boolean addBluetoothUUIDsToBlackList(List<String> list) {
        return this.mBluetoothPolicy.addBluetoothUUIDsToBlackList(list);
    }

    public boolean addBluetoothUUIDsToWhiteList(List<String> list) {
        return this.mBluetoothPolicy.addBluetoothUUIDsToWhiteList(list);
    }

    public boolean addBluetoothUUIDsToWhiteList(List<String> list, boolean z) {
        return this.mBluetoothPolicy.addBluetoothUUIDsToWhiteList(list, z);
    }

    public boolean allowCallerIDDisplay(boolean z) {
        return this.mBluetoothPolicy.allowCallerIDDisplay(z);
    }

    public boolean allowOutgoingCalls(boolean z) {
        return this.mBluetoothPolicy.allowOutgoingCalls(z);
    }

    public boolean clearBluetoothDevicesFromBlackList() {
        return this.mBluetoothPolicy.clearBluetoothDevicesFromBlackList();
    }

    public boolean clearBluetoothDevicesFromList() {
        return this.mBluetoothPolicy.clearBluetoothDevicesFromList();
    }

    public boolean clearBluetoothDevicesFromWhiteList() {
        return this.mBluetoothPolicy.clearBluetoothDevicesFromWhiteList();
    }

    public boolean clearBluetoothUUIDsFromBlackList() {
        return this.mBluetoothPolicy.clearBluetoothUUIDsFromBlackList();
    }

    public boolean clearBluetoothUUIDsFromList() {
        return this.mBluetoothPolicy.clearBluetoothUUIDsFromList();
    }

    public boolean clearBluetoothUUIDsFromWhiteList() {
        return this.mBluetoothPolicy.clearBluetoothUUIDsFromWhiteList();
    }

    public boolean getAllowBluetoothDataTransfer() {
        return this.mBluetoothPolicy.getAllowBluetoothDataTransfer();
    }

    public List<BluetoothControlInfo> getBluetoothDevicesFromBlackLists() {
        return BluetoothControlInfo.convertToNewList(this.mBluetoothPolicy.getBluetoothDevicesFromBlackLists());
    }

    public List<BluetoothControlInfo> getBluetoothDevicesFromWhiteLists() {
        return BluetoothControlInfo.convertToNewList(this.mBluetoothPolicy.getBluetoothDevicesFromWhiteLists());
    }

    public List<String> getBluetoothLog() {
        return this.mBluetoothPolicy.getBluetoothLog();
    }

    public List<BluetoothControlInfo> getBluetoothUUIDsFromBlackLists() {
        return BluetoothControlInfo.convertToNewList(this.mBluetoothPolicy.getBluetoothUUIDsFromBlackLists());
    }

    public List<BluetoothControlInfo> getBluetoothUUIDsFromWhiteLists() {
        return BluetoothControlInfo.convertToNewList(this.mBluetoothPolicy.getBluetoothUUIDsFromWhiteLists());
    }

    public boolean isBluetoothDeviceRestrictionActive() {
        return this.mBluetoothPolicy.isBluetoothDeviceRestrictionActive();
    }

    public boolean isBluetoothLogEnabled() {
        return this.mBluetoothPolicy.isBluetoothLogEnabled();
    }

    public boolean isBluetoothUUIDRestrictionActive() {
        return this.mBluetoothPolicy.isBluetoothUUIDRestrictionActive();
    }

    public boolean isCallerIDDisplayAllowed() {
        return this.mBluetoothPolicy.isCallerIDDisplayAllowed();
    }

    public boolean isDesktopConnectivityEnabled() {
        return this.mBluetoothPolicy.isDesktopConnectivityEnabled();
    }

    public boolean isDiscoverableEnabled() {
        return this.mBluetoothPolicy.isDiscoverableEnabled();
    }

    public boolean isLimitedDiscoverableEnabled() {
        return this.mBluetoothPolicy.isLimitedDiscoverableEnabled();
    }

    public boolean isOutgoingCallsAllowed() {
        return this.mBluetoothPolicy.isOutgoingCallsAllowed();
    }

    public boolean isPairingEnabled() {
        return this.mBluetoothPolicy.isPairingEnabled();
    }

    public boolean isProfileEnabled(int i) {
        return this.mBluetoothPolicy.isProfileEnabled(i);
    }

    public boolean removeBluetoothDevicesFromBlackList(List<String> list) {
        return this.mBluetoothPolicy.removeBluetoothDevicesFromBlackList(list);
    }

    public boolean removeBluetoothDevicesFromWhiteList(List<String> list) {
        return this.mBluetoothPolicy.removeBluetoothDevicesFromWhiteList(list);
    }

    public boolean removeBluetoothUUIDsFromBlackList(List<String> list) {
        return this.mBluetoothPolicy.removeBluetoothUUIDsFromBlackList(list);
    }

    public boolean removeBluetoothUUIDsFromWhiteList(List<String> list) {
        return this.mBluetoothPolicy.removeBluetoothUUIDsFromWhiteList(list);
    }

    public boolean setAllowBluetoothDataTransfer(boolean z) {
        return this.mBluetoothPolicy.setAllowBluetoothDataTransfer(z);
    }

    public boolean setBluetoothLogEnabled(boolean z) {
        return this.mBluetoothPolicy.setBluetoothLogEnabled(z);
    }

    public boolean setDesktopConnectivityState(boolean z) {
        return this.mBluetoothPolicy.setDesktopConnectivityState(z);
    }

    public boolean setDiscoverableState(boolean z) {
        return this.mBluetoothPolicy.setDiscoverableState(z);
    }

    public boolean setLimitedDiscoverableState(boolean z) {
        return this.mBluetoothPolicy.setLimitedDiscoverableState(z);
    }

    public boolean setPairingState(boolean z) {
        return this.mBluetoothPolicy.setPairingState(z);
    }

    public boolean setProfileState(boolean z, int i) {
        return this.mBluetoothPolicy.setProfileState(z, i);
    }
}
