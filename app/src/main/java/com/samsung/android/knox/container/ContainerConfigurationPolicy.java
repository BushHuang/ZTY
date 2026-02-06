package com.samsung.android.knox.container;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.pm.Signature;
import android.os.Bundle;
import com.samsung.android.knox.SupportLibUtils;
import java.util.List;

public class ContainerConfigurationPolicy {
    private com.sec.enterprise.knox.container.ContainerConfigurationPolicy mContainerConfigurationPolicy;

    public ContainerConfigurationPolicy(com.sec.enterprise.knox.container.ContainerConfigurationPolicy containerConfigurationPolicy) {
        this.mContainerConfigurationPolicy = containerConfigurationPolicy;
    }

    public void addCrossProfileIntentFilter(ComponentName componentName, IntentFilter intentFilter, int i) {
        try {
            this.mContainerConfigurationPolicy.addCrossProfileIntentFilter(componentName, intentFilter, i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "addCrossProfileIntentFilter", new Class[]{ComponentName.class, IntentFilter.class, Integer.TYPE}, 20));
        }
    }

    public boolean addHomeShortcutToPersonal(String str, String str2) {
        try {
            return this.mContainerConfigurationPolicy.addHomeShortcutToPersonal(str, str2);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "addHomeShortcutToPersonal", new Class[]{String.class, String.class}, 20));
        }
    }

    public boolean addNetworkSSID(String str) {
        try {
            return this.mContainerConfigurationPolicy.addNetworkSSID(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "addNetworkSSID", new Class[]{String.class}, 13));
        }
    }

    public boolean addPackageToExternalStorageSBABlackList(String str) {
        try {
            return this.mContainerConfigurationPolicy.addPackageToExternalStorageSBABlackList(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "addPackageToExternalStorageSBABlackList", new Class[]{String.class}, 13));
        }
    }

    public boolean addPackageToExternalStorageWhiteList(String str, Signature[] signatureArr) {
        try {
            return this.mContainerConfigurationPolicy.addPackageToExternalStorageWhiteList(str, signatureArr);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "addPackageToExternalStorageWhiteList", new Class[]{String.class, Signature[].class}, 13));
        }
    }

    public boolean addPackageToInstallWhiteList(String str) {
        return this.mContainerConfigurationPolicy.addPackageToInstallWhiteList(str);
    }

    public boolean allowRemoteControl(boolean z) {
        try {
            return this.mContainerConfigurationPolicy.allowRemoteControl(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "allowRemoteControl", new Class[]{Boolean.TYPE}, 13));
        }
    }

    public void clearCrossProfileIntentFilters(ComponentName componentName) {
        try {
            this.mContainerConfigurationPolicy.clearCrossProfileIntentFilters(componentName);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "clearCrossProfileIntentFilters", new Class[]{ComponentName.class}, 20));
        }
    }

    public boolean clearNetworkSSID() {
        try {
            return this.mContainerConfigurationPolicy.clearNetworkSSID();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "clearNetworkSSID", null, 13));
        }
    }

    public boolean clearPackagesFromExternalStorageSBABlackList() {
        try {
            return this.mContainerConfigurationPolicy.clearPackagesFromExternalStorageSBABlackList();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "clearPackagesFromExternalStorageSBABlackList", null, 13));
        }
    }

    public boolean clearPackagesFromExternalStorageWhiteList() {
        try {
            return this.mContainerConfigurationPolicy.clearPackagesFromExternalStorageWhiteList();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "clearPackagesFromExternalStorageWhiteList", null, 13));
        }
    }

    public boolean deleteHomeShortcutFromPersonal(String str, String str2) {
        try {
            return this.mContainerConfigurationPolicy.deleteHomeShortcutFromPersonal(str, str2);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "deleteHomeShortcutFromPersonal", new Class[]{String.class, String.class}, 20));
        }
    }

    public boolean enableBluetooth(boolean z, Bundle bundle) {
        try {
            return this.mContainerConfigurationPolicy.enableBluetooth(z, bundle);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "enableBluetooth", new Class[]{Boolean.TYPE, Bundle.class}, 15));
        }
    }

    public boolean enableExternalStorage(boolean z) {
        try {
            return this.mContainerConfigurationPolicy.enableExternalStorage(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "enableExternalStorage", new Class[]{Boolean.TYPE}, 13));
        }
    }

    public boolean enableNFC(boolean z, Bundle bundle) {
        try {
            return this.mContainerConfigurationPolicy.enableNFC(z, bundle);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "enableNFC", new Class[]{Boolean.TYPE, Bundle.class}, 15));
        }
    }

    public boolean enableUsbAccess(boolean z, Bundle bundle) {
        try {
            return this.mContainerConfigurationPolicy.enableUsbAccess(z, bundle);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "enableUsbAccess", new Class[]{Boolean.TYPE, Bundle.class}, 17));
        }
    }

    public void enforceMultifactorAuthentication(boolean z) {
        this.mContainerConfigurationPolicy.enforceMultifactorAuthentication(z);
    }

    public boolean getEnforceAuthForContainer() {
        return this.mContainerConfigurationPolicy.getEnforceAuthForContainer();
    }

    public Bundle getFIDOInfo() {
        try {
            return this.mContainerConfigurationPolicy.getFIDOInfo();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "getFIDOInfo", null, 20));
        }
    }

    public long getHibernationTimeout() {
        try {
            return this.mContainerConfigurationPolicy.getHibernationTimeout();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "getHibernationTimeout", null, 13));
        }
    }

    public List<String> getNetworkSSID() {
        try {
            return this.mContainerConfigurationPolicy.getNetworkSSID();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "getNetworkSSID", null, 13));
        }
    }

    public Signature[] getPackageSignaturesFromExternalStorageWhiteList(String str) {
        try {
            return this.mContainerConfigurationPolicy.getPackageSignaturesFromExternalStorageWhiteList(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "getPackageSignaturesFromExternalStorageWhiteList", new Class[]{String.class}, 13));
        }
    }

    public List<String> getPackagesFromExternalStorageSBABlackList() {
        try {
            return this.mContainerConfigurationPolicy.getPackagesFromExternalStorageSBABlackList();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "getPackagesFromExternalStorageSBABlackList", null, 13));
        }
    }

    public List<String> getPackagesFromExternalStorageWhiteList() {
        try {
            return this.mContainerConfigurationPolicy.getPackagesFromExternalStorageWhiteList();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "getPackagesFromExternalStorageWhiteList", null, 13));
        }
    }

    public List<String> getPackagesFromInstallWhiteList() {
        return this.mContainerConfigurationPolicy.getPackagesFromInstallWhiteList();
    }

    public boolean isBluetoothEnabled() {
        try {
            return this.mContainerConfigurationPolicy.isBluetoothEnabled();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "isBluetoothEnabled", null, 15));
        }
    }

    public boolean isContactsSharingEnabled() {
        try {
            return this.mContainerConfigurationPolicy.isContactsSharingEnabled();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "isContactsSharingEnabled", null, 20));
        }
    }

    public boolean isExternalStorageEnabled() {
        try {
            return this.mContainerConfigurationPolicy.isExternalStorageEnabled();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "isExternalStorageEnabled", null, 13));
        }
    }

    public boolean isMultifactorAuthenticationEnforced() {
        return this.mContainerConfigurationPolicy.isMultifactorAuthenticationEnforced();
    }

    public boolean isNFCEnabled() {
        try {
            return this.mContainerConfigurationPolicy.isNFCEnabled();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "isNFCEnabled", null, 15));
        }
    }

    public boolean isPackageInInstallWhiteList(String str) {
        return this.mContainerConfigurationPolicy.isPackageInInstallWhiteList(str);
    }

    public boolean isRemoteControlAllowed() {
        try {
            return this.mContainerConfigurationPolicy.isRemoteControlAllowed();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "isRemoteControlAllowed", null, 13));
        }
    }

    public boolean isResetContainerOnRebootEnabled() {
        try {
            return this.mContainerConfigurationPolicy.isResetContainerOnRebootEnabled();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "isResetContainerOnRebootEnabled", null, 12));
        }
    }

    public boolean isSettingsOptionEnabled(String str) {
        try {
            return this.mContainerConfigurationPolicy.isSettingsOptionEnabled(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "isSettingsOptionEnabled", new Class[]{String.class}, 14));
        }
    }

    public boolean isUsbAccessEnabled() {
        try {
            return this.mContainerConfigurationPolicy.isUsbAccessEnabled();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "isUsbAccessEnabled", null, 17));
        }
    }

    public boolean isUseSecureKeypadEnabled() {
        return this.mContainerConfigurationPolicy.isUseSecureKeypadEnabled();
    }

    public boolean removeNetworkSSID(String str) {
        try {
            return this.mContainerConfigurationPolicy.removeNetworkSSID(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "removeNetworkSSID", new Class[]{String.class}, 13));
        }
    }

    public boolean removePackageFromExternalStorageSBABlackList(String str) {
        try {
            return this.mContainerConfigurationPolicy.removePackageFromExternalStorageSBABlackList(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "removePackageFromExternalStorageSBABlackList", new Class[]{String.class}, 13));
        }
    }

    public boolean removePackageFromExternalStorageWhiteList(String str) {
        try {
            return this.mContainerConfigurationPolicy.removePackageFromExternalStorageWhiteList(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "removePackageFromExternalStorageWhiteList", new Class[]{String.class}, 13));
        }
    }

    public boolean removePackageFromInstallWhiteList(String str) {
        return this.mContainerConfigurationPolicy.removePackageFromInstallWhiteList(str);
    }

    public boolean resetContainerOnReboot(boolean z) {
        try {
            return this.mContainerConfigurationPolicy.resetContainerOnReboot(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "resetContainerOnReboot", new Class[]{Boolean.TYPE}, 12));
        }
    }

    public int resetContainerPassword(String str, int i) {
        try {
            return this.mContainerConfigurationPolicy.resetContainerPassword(str, i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "resetContainerPassword", new Class[]{String.class, Integer.TYPE}, 13));
        }
    }

    public boolean resetContainerPassword() {
        return this.mContainerConfigurationPolicy.resetContainerPassword();
    }

    public boolean setContactsSharingEnabled(boolean z) {
        try {
            return this.mContainerConfigurationPolicy.setContactsSharingEnabled(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "setContactsSharingEnabled", new Class[]{Boolean.TYPE}, 20));
        }
    }

    public boolean setEnforceAuthForContainer(boolean z) {
        return this.mContainerConfigurationPolicy.setEnforceAuthForContainer(z);
    }

    public boolean setFIDOInfo(Bundle bundle) {
        try {
            return this.mContainerConfigurationPolicy.setFIDOInfo(bundle);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "setFIDOInfo", new Class[]{Bundle.class}, 20));
        }
    }

    public boolean setHibernationTimeout(long j) {
        try {
            return this.mContainerConfigurationPolicy.setHibernationTimeout(j);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "setHibernationTimeout", new Class[]{Long.TYPE}, 13));
        }
    }

    public boolean setSettingsOptionEnabled(String str, boolean z) {
        try {
            return this.mContainerConfigurationPolicy.setSettingsOptionEnabled(str, z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ContainerConfigurationPolicy.class, "setSettingsOptionEnabled", new Class[]{String.class, Boolean.TYPE}, 14));
        }
    }

    public boolean setUseSecureKeypad(boolean z) {
        return this.mContainerConfigurationPolicy.setUseSecureKeypad(z);
    }
}
