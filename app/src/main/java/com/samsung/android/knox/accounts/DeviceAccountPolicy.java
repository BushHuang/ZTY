package com.samsung.android.knox.accounts;

import android.app.enterprise.SecurityPolicy;
import java.util.List;

public class DeviceAccountPolicy {
    private android.app.enterprise.DeviceAccountPolicy mDeviceAccountPolicy;
    private SecurityPolicy mSecurityPolicy;

    public DeviceAccountPolicy(android.app.enterprise.DeviceAccountPolicy deviceAccountPolicy, SecurityPolicy securityPolicy) {
        this.mDeviceAccountPolicy = deviceAccountPolicy;
        this.mSecurityPolicy = securityPolicy;
    }

    public boolean addAccountsToAdditionBlackList(String str, List<String> list) {
        return this.mDeviceAccountPolicy.addAccountsToAdditionBlackList(str, list);
    }

    public boolean addAccountsToAdditionWhiteList(String str, List<String> list) {
        return this.mDeviceAccountPolicy.addAccountsToAdditionWhiteList(str, list);
    }

    public boolean addAccountsToAdditionWhiteList(String str, List<String> list, boolean z) {
        return this.mDeviceAccountPolicy.addAccountsToAdditionWhiteList(str, list, z);
    }

    public boolean addAccountsToRemovalBlackList(String str, List<String> list) {
        return this.mDeviceAccountPolicy.addAccountsToRemovalBlackList(str, list);
    }

    public boolean addAccountsToRemovalWhiteList(String str, List<String> list) {
        return this.mDeviceAccountPolicy.addAccountsToRemovalWhiteList(str, list);
    }

    public boolean addAccountsToRemovalWhiteList(String str, List<String> list, boolean z) {
        return this.mDeviceAccountPolicy.addAccountsToRemovalWhiteList(str, list, z);
    }

    public boolean clearAccountsFromAdditionBlackList(String str) {
        return this.mDeviceAccountPolicy.clearAccountsFromAdditionBlackList(str);
    }

    public boolean clearAccountsFromAdditionList(String str) {
        return this.mDeviceAccountPolicy.clearAccountsFromAdditionList(str);
    }

    public boolean clearAccountsFromAdditionWhiteList(String str) {
        return this.mDeviceAccountPolicy.clearAccountsFromAdditionWhiteList(str);
    }

    public boolean clearAccountsFromRemovalBlackList(String str) {
        return this.mDeviceAccountPolicy.clearAccountsFromRemovalBlackList(str);
    }

    public boolean clearAccountsFromRemovalList(String str) {
        return this.mDeviceAccountPolicy.clearAccountsFromRemovalList(str);
    }

    public boolean clearAccountsFromRemovalWhiteList(String str) {
        return this.mDeviceAccountPolicy.clearAccountsFromRemovalWhiteList(str);
    }

    public List<AccountControlInfo> getAccountsFromAdditionBlackLists(String str) {
        return AccountControlInfo.convertToNewList(this.mDeviceAccountPolicy.getAccountsFromAdditionBlackLists(str));
    }

    public List<AccountControlInfo> getAccountsFromAdditionWhiteLists(String str) {
        return AccountControlInfo.convertToNewList(this.mDeviceAccountPolicy.getAccountsFromAdditionWhiteLists(str));
    }

    public List<AccountControlInfo> getAccountsFromRemovalBlackLists(String str) {
        return AccountControlInfo.convertToNewList(this.mDeviceAccountPolicy.getAccountsFromRemovalBlackLists(str));
    }

    public List<AccountControlInfo> getAccountsFromRemovalWhiteLists(String str) {
        return AccountControlInfo.convertToNewList(this.mDeviceAccountPolicy.getAccountsFromRemovalWhiteLists(str));
    }

    public List<String> getSupportedAccountTypes() {
        return this.mDeviceAccountPolicy.getSupportedAccountTypes();
    }

    public boolean removeAccountsByType(String str) {
        return this.mSecurityPolicy.removeAccountsByType(str);
    }

    public boolean removeAccountsFromAdditionBlackList(String str, List<String> list) {
        return this.mDeviceAccountPolicy.removeAccountsFromAdditionBlackList(str, list);
    }

    public boolean removeAccountsFromAdditionWhiteList(String str, List<String> list) {
        return this.mDeviceAccountPolicy.removeAccountsFromAdditionWhiteList(str, list);
    }

    public boolean removeAccountsFromRemovalBlackList(String str, List<String> list) {
        return this.mDeviceAccountPolicy.removeAccountsFromRemovalBlackList(str, list);
    }

    public boolean removeAccountsFromRemovalWhiteList(String str, List<String> list) {
        return this.mDeviceAccountPolicy.removeAccountsFromRemovalWhiteList(str, list);
    }
}
