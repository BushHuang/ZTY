package com.xuehai.system.mdm;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public interface MdmAidlInterface extends IInterface {

    public static class Default implements MdmAidlInterface {
        @Override
        public void activateFreeLicense() throws RemoteException {
        }

        @Override
        public void activatePayLicense(String str) throws RemoteException {
        }

        @Override
        public boolean addAppAlwaysRunningList(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean addAppAutoRunningList(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean addAppDomainsRules(List<String> list, List<String> list2, List<String> list3) throws RemoteException {
            return false;
        }

        @Override
        public boolean addDomainsRules(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean addFirewallAllowApps(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean addFirewallDenyApps(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean addIgnoreFrequentRelaunchAppList(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean addPackageToBatteryOptimizationWhiteList(String str) throws RemoteException {
            return false;
        }

        @Override
        public boolean addPackagesToNotificationWhiteList(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean addRuntimePermissionFixAppList(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean adjustStreamVolume(int i, int i2, int i3) throws RemoteException {
            return false;
        }

        @Override
        public boolean allowFactoryReset(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean allowFirmwareRecovery(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean allowHardwareKey(int i, boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean allowLocationService(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean allowMultiWindowMode(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean allowMultipleUsers(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean allowOTAUpgrade(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean allowSafeMode(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean applyRuntimePermissions(String str, List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public IBinder asBinder() {
            return null;
        }

        @Override
        public boolean cleanAppDomainRules() throws RemoteException {
            return false;
        }

        @Override
        public boolean cleanFirewallApps() throws RemoteException {
            return false;
        }

        @Override
        public void clearBootingAnimation() throws RemoteException {
        }

        @Override
        public boolean clearIgnoreFrequentRelaunchAppList() throws RemoteException {
            return false;
        }

        @Override
        public boolean clearPackagesFromNotificationList() throws RemoteException {
            return false;
        }

        @Override
        public boolean clearRuntimePermissionFixAppList() throws RemoteException {
            return false;
        }

        @Override
        public void clearShuttingDownAnimation() throws RemoteException {
        }

        @Override
        public void controlAirplaneMode(boolean z) throws RemoteException {
        }

        @Override
        public void controlChangeHome(boolean z) throws RemoteException {
        }

        @Override
        public void controlEyeMode(boolean z) throws RemoteException {
        }

        @Override
        public void controlRingerMode(int i) throws RemoteException {
        }

        @Override
        public void controlSettingsTopLevelMenu(String str) throws RemoteException {
        }

        @Override
        public boolean controlShowDeprecatedTarget(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public void deactivateDevice() throws RemoteException {
        }

        @Override
        public void dumpAppUsageEventsSnapshot(long j, long j2) throws RemoteException {
        }

        @Override
        public List<String> getAppAlwaysRunningList() throws RemoteException {
            return null;
        }

        @Override
        public List<String> getAppAutoRunningList() throws RemoteException {
            return null;
        }

        @Override
        public List<AppUsage> getAppUsage(long j, long j2) throws RemoteException {
            return null;
        }

        @Override
        public List<AppUsage2> getAppUsageV2(long j, long j2) throws RemoteException {
            return null;
        }

        @Override
        public List<NetworkStatsBean> getApplicationNetworkStats() throws RemoteException {
            return null;
        }

        @Override
        public boolean getAutomaticTime() throws RemoteException {
            return false;
        }

        @Override
        public String getDeviceMacAddress() throws RemoteException {
            return null;
        }

        @Override
        public List<String> getIgnoreFrequentRelaunchAppList() throws RemoteException {
            return null;
        }

        @Override
        public String getImei() throws RemoteException {
            return null;
        }

        @Override
        public int getIntSettingsSystemValue(int i, String str) throws RemoteException {
            return 0;
        }

        @Override
        public long getLongSettingsSystemValue(int i, String str) throws RemoteException {
            return 0L;
        }

        @Override
        public List<String> getRuntimePermissionFixAppList() throws RemoteException {
            return null;
        }

        @Override
        public String getSerialNumber() throws RemoteException {
            return null;
        }

        @Override
        public String getSimSerialNumber() throws RemoteException {
            return null;
        }

        @Override
        public String getStringSettingsSystemValue(int i, String str) throws RemoteException {
            return null;
        }

        @Override
        public String getTopAppPackageName() throws RemoteException {
            return null;
        }

        @Override
        public float gettFloatSettingsSystemValue(int i, String str) throws RemoteException {
            return 0.0f;
        }

        @Override
        public boolean installApplication(String str) throws RemoteException {
            return false;
        }

        @Override
        public boolean isBluetoothEnabled() throws RemoteException {
            return false;
        }

        @Override
        public boolean isClipboardAllowed() throws RemoteException {
            return false;
        }

        @Override
        public boolean isDeviceAdminActivated() throws RemoteException {
            return false;
        }

        @Override
        public boolean isExternalStorageEnable() throws RemoteException {
            return false;
        }

        @Override
        public boolean isEyeComfortTurnedOn() throws RemoteException {
            return false;
        }

        @Override
        public boolean isFactoryResetAllowed() throws RemoteException {
            return false;
        }

        @Override
        public boolean isFirmwareRecoveryAllowed() throws RemoteException {
            return false;
        }

        @Override
        public boolean isFreeLicenseActivated() throws RemoteException {
            return false;
        }

        @Override
        public boolean isHardwareKeyAllow(int i) throws RemoteException {
            return false;
        }

        @Override
        public boolean isMTPAvailable() throws RemoteException {
            return false;
        }

        @Override
        public boolean isMdmSupport() throws RemoteException {
            return false;
        }

        @Override
        public boolean isOsVersionRemoteValid() throws RemoteException {
            return false;
        }

        @Override
        public boolean isPayLicenseActivated() throws RemoteException {
            return false;
        }

        @Override
        public boolean isRandomMacDisabled() throws RemoteException {
            return false;
        }

        @Override
        public boolean isUsbDebuggingEnabled() throws RemoteException {
            return false;
        }

        @Override
        public void lockScreen() throws RemoteException {
        }

        @Override
        public boolean multipleUsersAllowed() throws RemoteException {
            return false;
        }

        @Override
        public void openSoftUpdateView() throws RemoteException {
        }

        @Override
        public boolean putFloatSettingsSystemValue(int i, String str, float f) throws RemoteException {
            return false;
        }

        @Override
        public boolean putIntSettingsSystemValue(int i, String str, int i2) throws RemoteException {
            return false;
        }

        @Override
        public boolean putLongSettingsSystemValue(int i, String str, long j) throws RemoteException {
            return false;
        }

        @Override
        public boolean putStringSettingsSystemValue(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override
        public TrafficInfo queryTrafficDetailBySummary(int i, String str, long j, long j2) throws RemoteException {
            return null;
        }

        @Override
        public TrafficInfo queryTrafficDetailByUid(int i, String str, long j, long j2, int i2) throws RemoteException {
            return null;
        }

        @Override
        public ParcelFileDescriptor readFileFromData(String str) throws RemoteException {
            return null;
        }

        @Override
        public void reboot() throws RemoteException {
        }

        @Override
        public boolean removeAppAlwaysRunningList(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean removeAppAutoRunningList(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean removeDefaultLauncher(String str, String str2) throws RemoteException {
            return false;
        }

        @Override
        public void removeDevicePassword() throws RemoteException {
        }

        @Override
        public boolean removeIgnoreFrequentRelaunchAppList(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean removeLockScreen() throws RemoteException {
            return false;
        }

        @Override
        public boolean removePackagesFromNotificationWhiteList(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean removeRuntimePermissionFixAppList(List<String> list) throws RemoteException {
            return false;
        }

        @Override
        public boolean resetFactory(String str) throws RemoteException {
            return false;
        }

        @Override
        public boolean resetLocalActivateState() throws RemoteException {
            return false;
        }

        @Override
        public boolean resetNetworkSetting() throws RemoteException {
            return false;
        }

        @Override
        public String runBugReport(String str) throws RemoteException {
            return null;
        }

        @Override
        public boolean setApplicationComponentState(ComponentName componentName, boolean z) throws RemoteException {
            return false;
        }

        @Override
        public void setApplicationState(String str, boolean z) throws RemoteException {
        }

        @Override
        public void setApplicationUninstalltionState(String str, boolean z) throws RemoteException {
        }

        @Override
        public boolean setAutomaticTime(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setBixbyShortcutEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public void setBluetoothEnable(boolean z) throws RemoteException {
        }

        @Override
        public void setBootingAnimation(String str, String str2, String str3, long j) throws RemoteException {
        }

        @Override
        public boolean setCameraShortcutEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setCameraState(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setClipboardEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setDateTimeChangeEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public void setDefaultHome(String str, String str2) throws RemoteException {
        }

        @Override
        public boolean setDefaultLauncher(String str, String str2) throws RemoteException {
            return false;
        }

        @Override
        public boolean setDevelopHiddenState(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setDropMenuHiddenState(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setEmergencyCallOnly(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setFileShareDisabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setInputMethod(String str) throws RemoteException {
            return false;
        }

        @Override
        public boolean setIptablesOption(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setKanbanEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setLockScreenItems(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setMTPEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setMobileDataState(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setNetworkSpeedState(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public void setPowerSavingMode(int i) throws RemoteException {
        }

        @Override
        public boolean setRandomMacDisabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setScreenCapture(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setSdCardState(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setSettingEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setSettingsHiddenState(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public void setShuttingDownAnimation(String str, String str2) throws RemoteException {
        }

        @Override
        public boolean setSpenPointImageState(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public void setStatusBarButtonState(Map map) throws RemoteException {
        }

        @Override
        public boolean setStreamVolume(int i, int i2, int i3) throws RemoteException {
            return false;
        }

        @Override
        public boolean setSwitchInputMethodEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setSystemTime(long j) throws RemoteException {
            return false;
        }

        @Override
        public boolean setTaskBarEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setTimeZone(String str) throws RemoteException {
            return false;
        }

        @Override
        public boolean setUsbDebuggingEnabled(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public boolean setWiFiState(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public void shoutdown() throws RemoteException {
        }

        @Override
        public boolean startGPS(boolean z) throws RemoteException {
            return false;
        }

        @Override
        public void startSoftUpdate(int i, int i2) throws RemoteException {
        }

        @Override
        public boolean startTcpdump(String str) throws RemoteException {
            return false;
        }

        @Override
        public boolean stopApp(String str) throws RemoteException {
            return false;
        }

        @Override
        public boolean stopTcpdump() throws RemoteException {
            return false;
        }

        @Override
        public boolean uninstallApplication(String str) throws RemoteException {
            return false;
        }

        @Override
        public void unlockScreen() throws RemoteException {
        }

        @Override
        public boolean verifyDynamicCode(String str, String str2, int i) throws RemoteException {
            return false;
        }

        @Override
        public boolean verifyDynamicToken(String str, int i) throws RemoteException {
            return false;
        }

        @Override
        public boolean wipeApplicationData(String str) throws RemoteException {
            return false;
        }

        @Override
        public boolean wipeRecentTasks() throws RemoteException {
            return false;
        }
    }

    public static abstract class Stub extends Binder implements MdmAidlInterface {
        private static final String DESCRIPTOR = "com.xuehai.system.mdm.MdmAidlInterface";
        static final int TRANSACTION_activateFreeLicense = 7;
        static final int TRANSACTION_activatePayLicense = 9;
        static final int TRANSACTION_addAppAlwaysRunningList = 29;
        static final int TRANSACTION_addAppAutoRunningList = 32;
        static final int TRANSACTION_addAppDomainsRules = 40;
        static final int TRANSACTION_addDomainsRules = 39;
        static final int TRANSACTION_addFirewallAllowApps = 42;
        static final int TRANSACTION_addFirewallDenyApps = 43;
        static final int TRANSACTION_addIgnoreFrequentRelaunchAppList = 136;
        static final int TRANSACTION_addPackageToBatteryOptimizationWhiteList = 79;
        static final int TRANSACTION_addPackagesToNotificationWhiteList = 24;
        static final int TRANSACTION_addRuntimePermissionFixAppList = 115;
        static final int TRANSACTION_adjustStreamVolume = 128;
        static final int TRANSACTION_allowFactoryReset = 58;
        static final int TRANSACTION_allowFirmwareRecovery = 60;
        static final int TRANSACTION_allowHardwareKey = 63;
        static final int TRANSACTION_allowLocationService = 143;
        static final int TRANSACTION_allowMultiWindowMode = 50;
        static final int TRANSACTION_allowMultipleUsers = 49;
        static final int TRANSACTION_allowOTAUpgrade = 59;
        static final int TRANSACTION_allowSafeMode = 61;
        static final int TRANSACTION_applyRuntimePermissions = 89;
        static final int TRANSACTION_cleanAppDomainRules = 41;
        static final int TRANSACTION_cleanFirewallApps = 44;
        static final int TRANSACTION_clearBootingAnimation = 91;
        static final int TRANSACTION_clearIgnoreFrequentRelaunchAppList = 139;
        static final int TRANSACTION_clearPackagesFromNotificationList = 26;
        static final int TRANSACTION_clearRuntimePermissionFixAppList = 118;
        static final int TRANSACTION_clearShuttingDownAnimation = 93;
        static final int TRANSACTION_controlAirplaneMode = 109;
        static final int TRANSACTION_controlChangeHome = 106;
        static final int TRANSACTION_controlEyeMode = 110;
        static final int TRANSACTION_controlRingerMode = 112;
        static final int TRANSACTION_controlSettingsTopLevelMenu = 104;
        static final int TRANSACTION_controlShowDeprecatedTarget = 142;
        static final int TRANSACTION_deactivateDevice = 1;
        static final int TRANSACTION_dumpAppUsageEventsSnapshot = 98;
        static final int TRANSACTION_getAppAlwaysRunningList = 31;
        static final int TRANSACTION_getAppAutoRunningList = 34;
        static final int TRANSACTION_getAppUsage = 94;
        static final int TRANSACTION_getAppUsageV2 = 99;
        static final int TRANSACTION_getApplicationNetworkStats = 69;
        static final int TRANSACTION_getAutomaticTime = 36;
        static final int TRANSACTION_getDeviceMacAddress = 114;
        static final int TRANSACTION_getIgnoreFrequentRelaunchAppList = 138;
        static final int TRANSACTION_getImei = 97;
        static final int TRANSACTION_getIntSettingsSystemValue = 124;
        static final int TRANSACTION_getLongSettingsSystemValue = 125;
        static final int TRANSACTION_getRuntimePermissionFixAppList = 117;
        static final int TRANSACTION_getSerialNumber = 10;
        static final int TRANSACTION_getSimSerialNumber = 103;
        static final int TRANSACTION_getStringSettingsSystemValue = 123;
        static final int TRANSACTION_getTopAppPackageName = 87;
        static final int TRANSACTION_gettFloatSettingsSystemValue = 126;
        static final int TRANSACTION_installApplication = 17;
        static final int TRANSACTION_isBluetoothEnabled = 86;
        static final int TRANSACTION_isClipboardAllowed = 52;
        static final int TRANSACTION_isDeviceAdminActivated = 2;
        static final int TRANSACTION_isExternalStorageEnable = 84;
        static final int TRANSACTION_isEyeComfortTurnedOn = 144;
        static final int TRANSACTION_isFactoryResetAllowed = 81;
        static final int TRANSACTION_isFirmwareRecoveryAllowed = 80;
        static final int TRANSACTION_isFreeLicenseActivated = 6;
        static final int TRANSACTION_isHardwareKeyAllow = 62;
        static final int TRANSACTION_isMTPAvailable = 83;
        static final int TRANSACTION_isMdmSupport = 3;
        static final int TRANSACTION_isOsVersionRemoteValid = 113;
        static final int TRANSACTION_isPayLicenseActivated = 8;
        static final int TRANSACTION_isRandomMacDisabled = 145;
        static final int TRANSACTION_isUsbDebuggingEnabled = 82;
        static final int TRANSACTION_lockScreen = 95;
        static final int TRANSACTION_multipleUsersAllowed = 85;
        static final int TRANSACTION_openSoftUpdateView = 76;
        static final int TRANSACTION_putFloatSettingsSystemValue = 122;
        static final int TRANSACTION_putIntSettingsSystemValue = 120;
        static final int TRANSACTION_putLongSettingsSystemValue = 121;
        static final int TRANSACTION_putStringSettingsSystemValue = 119;
        static final int TRANSACTION_queryTrafficDetailBySummary = 102;
        static final int TRANSACTION_queryTrafficDetailByUid = 100;
        static final int TRANSACTION_readFileFromData = 4;
        static final int TRANSACTION_reboot = 72;
        static final int TRANSACTION_removeAppAlwaysRunningList = 30;
        static final int TRANSACTION_removeAppAutoRunningList = 33;
        static final int TRANSACTION_removeDefaultLauncher = 28;
        static final int TRANSACTION_removeDevicePassword = 73;
        static final int TRANSACTION_removeIgnoreFrequentRelaunchAppList = 137;
        static final int TRANSACTION_removeLockScreen = 12;
        static final int TRANSACTION_removePackagesFromNotificationWhiteList = 25;
        static final int TRANSACTION_removeRuntimePermissionFixAppList = 116;
        static final int TRANSACTION_resetFactory = 74;
        static final int TRANSACTION_resetLocalActivateState = 135;
        static final int TRANSACTION_resetNetworkSetting = 68;
        static final int TRANSACTION_runBugReport = 75;
        static final int TRANSACTION_setApplicationComponentState = 23;
        static final int TRANSACTION_setApplicationState = 19;
        static final int TRANSACTION_setApplicationUninstalltionState = 22;
        static final int TRANSACTION_setAutomaticTime = 37;
        static final int TRANSACTION_setBixbyShortcutEnabled = 132;
        static final int TRANSACTION_setBluetoothEnable = 111;
        static final int TRANSACTION_setBootingAnimation = 90;
        static final int TRANSACTION_setCameraShortcutEnabled = 131;
        static final int TRANSACTION_setCameraState = 53;
        static final int TRANSACTION_setClipboardEnabled = 51;
        static final int TRANSACTION_setDateTimeChangeEnabled = 107;
        static final int TRANSACTION_setDefaultHome = 105;
        static final int TRANSACTION_setDefaultLauncher = 27;
        static final int TRANSACTION_setDevelopHiddenState = 66;
        static final int TRANSACTION_setDropMenuHiddenState = 13;
        static final int TRANSACTION_setEmergencyCallOnly = 11;
        static final int TRANSACTION_setFileShareDisabled = 140;
        static final int TRANSACTION_setInputMethod = 48;
        static final int TRANSACTION_setIptablesOption = 45;
        static final int TRANSACTION_setKanbanEnabled = 130;
        static final int TRANSACTION_setLockScreenItems = 129;
        static final int TRANSACTION_setMTPEnabled = 57;
        static final int TRANSACTION_setMobileDataState = 101;
        static final int TRANSACTION_setNetworkSpeedState = 70;
        static final int TRANSACTION_setPowerSavingMode = 108;
        static final int TRANSACTION_setRandomMacDisabled = 141;
        static final int TRANSACTION_setScreenCapture = 54;
        static final int TRANSACTION_setSdCardState = 55;
        static final int TRANSACTION_setSettingEnabled = 64;
        static final int TRANSACTION_setSettingsHiddenState = 65;
        static final int TRANSACTION_setShuttingDownAnimation = 92;
        static final int TRANSACTION_setSpenPointImageState = 14;
        static final int TRANSACTION_setStatusBarButtonState = 78;
        static final int TRANSACTION_setStreamVolume = 127;
        static final int TRANSACTION_setSwitchInputMethodEnabled = 133;
        static final int TRANSACTION_setSystemTime = 38;
        static final int TRANSACTION_setTaskBarEnabled = 134;
        static final int TRANSACTION_setTimeZone = 35;
        static final int TRANSACTION_setUsbDebuggingEnabled = 56;
        static final int TRANSACTION_setWiFiState = 67;
        static final int TRANSACTION_shoutdown = 71;
        static final int TRANSACTION_startGPS = 46;
        static final int TRANSACTION_startSoftUpdate = 77;
        static final int TRANSACTION_startTcpdump = 15;
        static final int TRANSACTION_stopApp = 21;
        static final int TRANSACTION_stopTcpdump = 16;
        static final int TRANSACTION_uninstallApplication = 18;
        static final int TRANSACTION_unlockScreen = 96;
        static final int TRANSACTION_verifyDynamicCode = 88;
        static final int TRANSACTION_verifyDynamicToken = 5;
        static final int TRANSACTION_wipeApplicationData = 20;
        static final int TRANSACTION_wipeRecentTasks = 47;

        private static class Proxy implements MdmAidlInterface {
            public static MdmAidlInterface sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override
            public void activateFreeLicense() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().activateFreeLicense();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void activatePayLicense(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().activatePayLicense(str);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean addAppAlwaysRunningList(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addAppAlwaysRunningList(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean addAppAutoRunningList(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addAppAutoRunningList(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean addAppDomainsRules(List<String> list, List<String> list2, List<String> list3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeStringList(list2);
                    parcelObtain.writeStringList(list3);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addAppDomainsRules(list, list2, list3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean addDomainsRules(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addDomainsRules(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean addFirewallAllowApps(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addFirewallAllowApps(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean addFirewallDenyApps(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addFirewallDenyApps(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean addIgnoreFrequentRelaunchAppList(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(136, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addIgnoreFrequentRelaunchAppList(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean addPackageToBatteryOptimizationWhiteList(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(79, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPackageToBatteryOptimizationWhiteList(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean addPackagesToNotificationWhiteList(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPackagesToNotificationWhiteList(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean addRuntimePermissionFixAppList(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(115, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addRuntimePermissionFixAppList(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean adjustStreamVolume(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(128, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().adjustStreamVolume(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean allowFactoryReset(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allowFactoryReset(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean allowFirmwareRecovery(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allowFirmwareRecovery(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean allowHardwareKey(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(63, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allowHardwareKey(i, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean allowLocationService(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(143, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allowLocationService(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean allowMultiWindowMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allowMultiWindowMode(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean allowMultipleUsers(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allowMultipleUsers(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean allowOTAUpgrade(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allowOTAUpgrade(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean allowSafeMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allowSafeMode(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean applyRuntimePermissions(String str, List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(89, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().applyRuntimePermissions(str, list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override
            public boolean cleanAppDomainRules() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cleanAppDomainRules();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean cleanFirewallApps() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cleanFirewallApps();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void clearBootingAnimation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (this.mRemote.transact(91, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().clearBootingAnimation();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean clearIgnoreFrequentRelaunchAppList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(139, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearIgnoreFrequentRelaunchAppList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean clearPackagesFromNotificationList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearPackagesFromNotificationList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean clearRuntimePermissionFixAppList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(118, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearRuntimePermissionFixAppList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void clearShuttingDownAnimation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (this.mRemote.transact(93, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().clearShuttingDownAnimation();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void controlAirplaneMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(109, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().controlAirplaneMode(z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void controlChangeHome(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(106, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().controlChangeHome(z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void controlEyeMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(110, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().controlEyeMode(z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void controlRingerMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(112, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().controlRingerMode(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void controlSettingsTopLevelMenu(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(104, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().controlSettingsTopLevelMenu(str);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean controlShowDeprecatedTarget(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(142, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().controlShowDeprecatedTarget(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void deactivateDevice() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().deactivateDevice();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void dumpAppUsageEventsSnapshot(long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (this.mRemote.transact(98, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().dumpAppUsageEventsSnapshot(j, j2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public List<String> getAppAlwaysRunningList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppAlwaysRunningList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public List<String> getAppAutoRunningList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppAutoRunningList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public List<AppUsage> getAppUsage(long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(94, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppUsage(j, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(AppUsage.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public List<AppUsage2> getAppUsageV2(long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(99, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppUsageV2(j, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(AppUsage2.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public List<NetworkStatsBean> getApplicationNetworkStats() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(69, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApplicationNetworkStats();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(NetworkStatsBean.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean getAutomaticTime() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutomaticTime();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public String getDeviceMacAddress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(114, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceMacAddress();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public List<String> getIgnoreFrequentRelaunchAppList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(138, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIgnoreFrequentRelaunchAppList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public String getImei() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(97, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImei();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public int getIntSettingsSystemValue(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(124, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIntSettingsSystemValue(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "com.xuehai.system.mdm.MdmAidlInterface";
            }

            @Override
            public long getLongSettingsSystemValue(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(125, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLongSettingsSystemValue(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public List<String> getRuntimePermissionFixAppList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(117, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRuntimePermissionFixAppList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public String getSerialNumber() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSerialNumber();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public String getSimSerialNumber() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(103, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSimSerialNumber();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public String getStringSettingsSystemValue(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(123, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStringSettingsSystemValue(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public String getTopAppPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(87, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTopAppPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public float gettFloatSettingsSystemValue(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(126, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().gettFloatSettingsSystemValue(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean installApplication(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().installApplication(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isBluetoothEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(86, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBluetoothEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isClipboardAllowed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isClipboardAllowed();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isDeviceAdminActivated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDeviceAdminActivated();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isExternalStorageEnable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(84, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isExternalStorageEnable();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isEyeComfortTurnedOn() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(144, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEyeComfortTurnedOn();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isFactoryResetAllowed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(81, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFactoryResetAllowed();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isFirmwareRecoveryAllowed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(80, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFirmwareRecoveryAllowed();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isFreeLicenseActivated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFreeLicenseActivated();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isHardwareKeyAllow(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHardwareKeyAllow(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isMTPAvailable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(83, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMTPAvailable();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isMdmSupport() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMdmSupport();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isOsVersionRemoteValid() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(113, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOsVersionRemoteValid();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isPayLicenseActivated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPayLicenseActivated();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isRandomMacDisabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(145, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRandomMacDisabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isUsbDebuggingEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(82, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUsbDebuggingEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void lockScreen() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (this.mRemote.transact(95, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().lockScreen();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean multipleUsersAllowed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(85, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().multipleUsersAllowed();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void openSoftUpdateView() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (this.mRemote.transact(76, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().openSoftUpdateView();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean putFloatSettingsSystemValue(int i, String str, float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeFloat(f);
                    if (!this.mRemote.transact(122, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putFloatSettingsSystemValue(i, str, f);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean putIntSettingsSystemValue(int i, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(120, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putIntSettingsSystemValue(i, str, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean putLongSettingsSystemValue(int i, String str, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(121, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putLongSettingsSystemValue(i, str, j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean putStringSettingsSystemValue(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(119, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putStringSettingsSystemValue(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public TrafficInfo queryTrafficDetailBySummary(int i, String str, long j, long j2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    try {
                        if (!this.mRemote.transact(102, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            TrafficInfo trafficInfoQueryTrafficDetailBySummary = Stub.getDefaultImpl().queryTrafficDetailBySummary(i, str, j, j2);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                            return trafficInfoQueryTrafficDetailBySummary;
                        }
                        parcelObtain2.readException();
                        TrafficInfo trafficInfoCreateFromParcel = parcelObtain2.readInt() != 0 ? TrafficInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return trafficInfoCreateFromParcel;
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override
            public TrafficInfo queryTrafficDetailByUid(int i, String str, long j, long j2, int i2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeInt(i2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(100, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        TrafficInfo trafficInfoQueryTrafficDetailByUid = Stub.getDefaultImpl().queryTrafficDetailByUid(i, str, j, j2, i2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return trafficInfoQueryTrafficDetailByUid;
                    }
                    parcelObtain2.readException();
                    TrafficInfo trafficInfoCreateFromParcel = parcelObtain2.readInt() != 0 ? TrafficInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return trafficInfoCreateFromParcel;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override
            public ParcelFileDescriptor readFileFromData(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().readFileFromData(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void reboot() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (this.mRemote.transact(72, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().reboot();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean removeAppAlwaysRunningList(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAppAlwaysRunningList(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean removeAppAutoRunningList(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAppAutoRunningList(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean removeDefaultLauncher(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeDefaultLauncher(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void removeDevicePassword() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (this.mRemote.transact(73, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().removeDevicePassword();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean removeIgnoreFrequentRelaunchAppList(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(137, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeIgnoreFrequentRelaunchAppList(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean removeLockScreen() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeLockScreen();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean removePackagesFromNotificationWhiteList(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removePackagesFromNotificationWhiteList(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean removeRuntimePermissionFixAppList(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(116, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeRuntimePermissionFixAppList(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean resetFactory(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(74, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resetFactory(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean resetLocalActivateState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(135, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resetLocalActivateState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean resetNetworkSetting() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(68, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resetNetworkSetting();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public String runBugReport(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(75, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().runBugReport(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setApplicationComponentState(ComponentName componentName, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setApplicationComponentState(componentName, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void setApplicationState(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setApplicationState(str, z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void setApplicationUninstalltionState(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setApplicationUninstalltionState(str, z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setAutomaticTime(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAutomaticTime(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setBixbyShortcutEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(132, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBixbyShortcutEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void setBluetoothEnable(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(111, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setBluetoothEnable(z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void setBootingAnimation(String str, String str2, String str3, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(90, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setBootingAnimation(str, str2, str3, j);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setCameraShortcutEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(131, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCameraShortcutEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setCameraState(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCameraState(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setClipboardEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setClipboardEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setDateTimeChangeEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(107, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDateTimeChangeEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void setDefaultHome(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(105, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setDefaultHome(str, str2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setDefaultLauncher(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDefaultLauncher(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setDevelopHiddenState(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(66, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDevelopHiddenState(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setDropMenuHiddenState(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDropMenuHiddenState(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setEmergencyCallOnly(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEmergencyCallOnly(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setFileShareDisabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(140, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setFileShareDisabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setInputMethod(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setInputMethod(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setIptablesOption(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setIptablesOption(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setKanbanEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(130, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setKanbanEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setLockScreenItems(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(129, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLockScreenItems(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setMTPEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMTPEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setMobileDataState(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(101, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMobileDataState(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setNetworkSpeedState(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(70, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNetworkSpeedState(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void setPowerSavingMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(108, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setPowerSavingMode(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setRandomMacDisabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(141, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRandomMacDisabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setScreenCapture(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setScreenCapture(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setSdCardState(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSdCardState(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setSettingEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(64, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSettingEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setSettingsHiddenState(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(65, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSettingsHiddenState(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void setShuttingDownAnimation(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(92, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setShuttingDownAnimation(str, str2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setSpenPointImageState(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSpenPointImageState(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void setStatusBarButtonState(Map map) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeMap(map);
                    if (this.mRemote.transact(78, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setStatusBarButtonState(map);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setStreamVolume(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(127, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setStreamVolume(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setSwitchInputMethodEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(133, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSwitchInputMethodEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setSystemTime(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSystemTime(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setTaskBarEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(134, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setTaskBarEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setTimeZone(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setTimeZone(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setUsbDebuggingEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUsbDebuggingEnabled(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean setWiFiState(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(67, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setWiFiState(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void shoutdown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (this.mRemote.transact(71, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().shoutdown();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean startGPS(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startGPS(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void startSoftUpdate(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(77, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().startSoftUpdate(i, i2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean startTcpdump(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startTcpdump(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean stopApp(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopApp(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean stopTcpdump() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopTcpdump();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean uninstallApplication(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().uninstallApplication(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void unlockScreen() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (this.mRemote.transact(96, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unlockScreen();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean verifyDynamicCode(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(88, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyDynamicCode(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean verifyDynamicToken(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyDynamicToken(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean wipeApplicationData(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().wipeApplicationData(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean wipeRecentTasks() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xuehai.system.mdm.MdmAidlInterface");
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().wipeRecentTasks();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.xuehai.system.mdm.MdmAidlInterface");
        }

        public static MdmAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.xuehai.system.mdm.MdmAidlInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof MdmAidlInterface)) ? new Proxy(iBinder) : (MdmAidlInterface) iInterfaceQueryLocalInterface;
        }

        public static MdmAidlInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(MdmAidlInterface mdmAidlInterface) {
            if (Proxy.sDefaultImpl != null || mdmAidlInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = mdmAidlInterface;
            return true;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.xuehai.system.mdm.MdmAidlInterface");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    deactivateDevice();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsDeviceAdminActivated = isDeviceAdminActivated();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDeviceAdminActivated ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsMdmSupport = isMdmSupport();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsMdmSupport ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    ParcelFileDescriptor fileFromData = readFileFromData(parcel.readString());
                    parcel2.writeNoException();
                    if (fileFromData != null) {
                        parcel2.writeInt(1);
                        fileFromData.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zVerifyDynamicToken = verifyDynamicToken(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zVerifyDynamicToken ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsFreeLicenseActivated = isFreeLicenseActivated();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFreeLicenseActivated ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    activateFreeLicense();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsPayLicenseActivated = isPayLicenseActivated();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPayLicenseActivated ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    activatePayLicense(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    String serialNumber = getSerialNumber();
                    parcel2.writeNoException();
                    parcel2.writeString(serialNumber);
                    return true;
                case 11:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean emergencyCallOnly = setEmergencyCallOnly(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(emergencyCallOnly ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zRemoveLockScreen = removeLockScreen();
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveLockScreen ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean dropMenuHiddenState = setDropMenuHiddenState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(dropMenuHiddenState ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean spenPointImageState = setSpenPointImageState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(spenPointImageState ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zStartTcpdump = startTcpdump(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartTcpdump ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zStopTcpdump = stopTcpdump();
                    parcel2.writeNoException();
                    parcel2.writeInt(zStopTcpdump ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zInstallApplication = installApplication(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zInstallApplication ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zUninstallApplication = uninstallApplication(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zUninstallApplication ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    setApplicationState(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zWipeApplicationData = wipeApplicationData(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zWipeApplicationData ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zStopApp = stopApp(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zStopApp ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    setApplicationUninstalltionState(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean applicationComponentState = setApplicationComponentState(parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(applicationComponentState ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAddPackagesToNotificationWhiteList = addPackagesToNotificationWhiteList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddPackagesToNotificationWhiteList ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zRemovePackagesFromNotificationWhiteList = removePackagesFromNotificationWhiteList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemovePackagesFromNotificationWhiteList ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zClearPackagesFromNotificationList = clearPackagesFromNotificationList();
                    parcel2.writeNoException();
                    parcel2.writeInt(zClearPackagesFromNotificationList ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean defaultLauncher = setDefaultLauncher(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(defaultLauncher ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zRemoveDefaultLauncher = removeDefaultLauncher(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveDefaultLauncher ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAddAppAlwaysRunningList = addAppAlwaysRunningList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddAppAlwaysRunningList ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zRemoveAppAlwaysRunningList = removeAppAlwaysRunningList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveAppAlwaysRunningList ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    List<String> appAlwaysRunningList = getAppAlwaysRunningList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(appAlwaysRunningList);
                    return true;
                case 32:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAddAppAutoRunningList = addAppAutoRunningList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddAppAutoRunningList ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zRemoveAppAutoRunningList = removeAppAutoRunningList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveAppAutoRunningList ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    List<String> appAutoRunningList = getAppAutoRunningList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(appAutoRunningList);
                    return true;
                case 35:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean timeZone = setTimeZone(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(timeZone ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean automaticTime = getAutomaticTime();
                    parcel2.writeNoException();
                    parcel2.writeInt(automaticTime ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean automaticTime2 = setAutomaticTime(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(automaticTime2 ? 1 : 0);
                    return true;
                case 38:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean systemTime = setSystemTime(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(systemTime ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAddDomainsRules = addDomainsRules(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddDomainsRules ? 1 : 0);
                    return true;
                case 40:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAddAppDomainsRules = addAppDomainsRules(parcel.createStringArrayList(), parcel.createStringArrayList(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddAppDomainsRules ? 1 : 0);
                    return true;
                case 41:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zCleanAppDomainRules = cleanAppDomainRules();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCleanAppDomainRules ? 1 : 0);
                    return true;
                case 42:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAddFirewallAllowApps = addFirewallAllowApps(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddFirewallAllowApps ? 1 : 0);
                    return true;
                case 43:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAddFirewallDenyApps = addFirewallDenyApps(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddFirewallDenyApps ? 1 : 0);
                    return true;
                case 44:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zCleanFirewallApps = cleanFirewallApps();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCleanFirewallApps ? 1 : 0);
                    return true;
                case 45:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean iptablesOption = setIptablesOption(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iptablesOption ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zStartGPS = startGPS(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartGPS ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zWipeRecentTasks = wipeRecentTasks();
                    parcel2.writeNoException();
                    parcel2.writeInt(zWipeRecentTasks ? 1 : 0);
                    return true;
                case 48:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean inputMethod = setInputMethod(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(inputMethod ? 1 : 0);
                    return true;
                case 49:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAllowMultipleUsers = allowMultipleUsers(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAllowMultipleUsers ? 1 : 0);
                    return true;
                case 50:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAllowMultiWindowMode = allowMultiWindowMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAllowMultiWindowMode ? 1 : 0);
                    return true;
                case 51:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean clipboardEnabled = setClipboardEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(clipboardEnabled ? 1 : 0);
                    return true;
                case 52:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsClipboardAllowed = isClipboardAllowed();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsClipboardAllowed ? 1 : 0);
                    return true;
                case 53:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean cameraState = setCameraState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(cameraState ? 1 : 0);
                    return true;
                case 54:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean screenCapture = setScreenCapture(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(screenCapture ? 1 : 0);
                    return true;
                case 55:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean sdCardState = setSdCardState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(sdCardState ? 1 : 0);
                    return true;
                case 56:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean usbDebuggingEnabled = setUsbDebuggingEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(usbDebuggingEnabled ? 1 : 0);
                    return true;
                case 57:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean mTPEnabled = setMTPEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(mTPEnabled ? 1 : 0);
                    return true;
                case 58:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAllowFactoryReset = allowFactoryReset(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAllowFactoryReset ? 1 : 0);
                    return true;
                case 59:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAllowOTAUpgrade = allowOTAUpgrade(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAllowOTAUpgrade ? 1 : 0);
                    return true;
                case 60:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAllowFirmwareRecovery = allowFirmwareRecovery(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAllowFirmwareRecovery ? 1 : 0);
                    return true;
                case 61:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAllowSafeMode = allowSafeMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAllowSafeMode ? 1 : 0);
                    return true;
                case 62:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsHardwareKeyAllow = isHardwareKeyAllow(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHardwareKeyAllow ? 1 : 0);
                    return true;
                case 63:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAllowHardwareKey = allowHardwareKey(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAllowHardwareKey ? 1 : 0);
                    return true;
                case 64:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean settingEnabled = setSettingEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(settingEnabled ? 1 : 0);
                    return true;
                case 65:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean settingsHiddenState = setSettingsHiddenState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(settingsHiddenState ? 1 : 0);
                    return true;
                case 66:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean developHiddenState = setDevelopHiddenState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(developHiddenState ? 1 : 0);
                    return true;
                case 67:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean wiFiState = setWiFiState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(wiFiState ? 1 : 0);
                    return true;
                case 68:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zResetNetworkSetting = resetNetworkSetting();
                    parcel2.writeNoException();
                    parcel2.writeInt(zResetNetworkSetting ? 1 : 0);
                    return true;
                case 69:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    List<NetworkStatsBean> applicationNetworkStats = getApplicationNetworkStats();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(applicationNetworkStats);
                    return true;
                case 70:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean networkSpeedState = setNetworkSpeedState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(networkSpeedState ? 1 : 0);
                    return true;
                case 71:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    shoutdown();
                    parcel2.writeNoException();
                    return true;
                case 72:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    reboot();
                    parcel2.writeNoException();
                    return true;
                case 73:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    removeDevicePassword();
                    parcel2.writeNoException();
                    return true;
                case 74:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zResetFactory = resetFactory(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zResetFactory ? 1 : 0);
                    return true;
                case 75:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    String strRunBugReport = runBugReport(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(strRunBugReport);
                    return true;
                case 76:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    openSoftUpdateView();
                    parcel2.writeNoException();
                    return true;
                case 77:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    startSoftUpdate(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 78:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    setStatusBarButtonState(parcel.readHashMap(getClass().getClassLoader()));
                    parcel2.writeNoException();
                    return true;
                case 79:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAddPackageToBatteryOptimizationWhiteList = addPackageToBatteryOptimizationWhiteList(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddPackageToBatteryOptimizationWhiteList ? 1 : 0);
                    return true;
                case 80:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsFirmwareRecoveryAllowed = isFirmwareRecoveryAllowed();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFirmwareRecoveryAllowed ? 1 : 0);
                    return true;
                case 81:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsFactoryResetAllowed = isFactoryResetAllowed();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFactoryResetAllowed ? 1 : 0);
                    return true;
                case 82:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsUsbDebuggingEnabled = isUsbDebuggingEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUsbDebuggingEnabled ? 1 : 0);
                    return true;
                case 83:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsMTPAvailable = isMTPAvailable();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsMTPAvailable ? 1 : 0);
                    return true;
                case 84:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsExternalStorageEnable = isExternalStorageEnable();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsExternalStorageEnable ? 1 : 0);
                    return true;
                case 85:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zMultipleUsersAllowed = multipleUsersAllowed();
                    parcel2.writeNoException();
                    parcel2.writeInt(zMultipleUsersAllowed ? 1 : 0);
                    return true;
                case 86:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsBluetoothEnabled = isBluetoothEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBluetoothEnabled ? 1 : 0);
                    return true;
                case 87:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    String topAppPackageName = getTopAppPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(topAppPackageName);
                    return true;
                case 88:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zVerifyDynamicCode = verifyDynamicCode(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zVerifyDynamicCode ? 1 : 0);
                    return true;
                case 89:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zApplyRuntimePermissions = applyRuntimePermissions(parcel.readString(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zApplyRuntimePermissions ? 1 : 0);
                    return true;
                case 90:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    setBootingAnimation(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 91:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    clearBootingAnimation();
                    parcel2.writeNoException();
                    return true;
                case 92:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    setShuttingDownAnimation(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 93:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    clearShuttingDownAnimation();
                    parcel2.writeNoException();
                    return true;
                case 94:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    List<AppUsage> appUsage = getAppUsage(parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(appUsage);
                    return true;
                case 95:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    lockScreen();
                    parcel2.writeNoException();
                    return true;
                case 96:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    unlockScreen();
                    parcel2.writeNoException();
                    return true;
                case 97:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    String imei = getImei();
                    parcel2.writeNoException();
                    parcel2.writeString(imei);
                    return true;
                case 98:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    dumpAppUsageEventsSnapshot(parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 99:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    List<AppUsage2> appUsageV2 = getAppUsageV2(parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(appUsageV2);
                    return true;
                case 100:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    TrafficInfo trafficInfoQueryTrafficDetailByUid = queryTrafficDetailByUid(parcel.readInt(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    if (trafficInfoQueryTrafficDetailByUid != null) {
                        parcel2.writeInt(1);
                        trafficInfoQueryTrafficDetailByUid.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 101:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean mobileDataState = setMobileDataState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(mobileDataState ? 1 : 0);
                    return true;
                case 102:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    TrafficInfo trafficInfoQueryTrafficDetailBySummary = queryTrafficDetailBySummary(parcel.readInt(), parcel.readString(), parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    if (trafficInfoQueryTrafficDetailBySummary != null) {
                        parcel2.writeInt(1);
                        trafficInfoQueryTrafficDetailBySummary.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 103:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    String simSerialNumber = getSimSerialNumber();
                    parcel2.writeNoException();
                    parcel2.writeString(simSerialNumber);
                    return true;
                case 104:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    controlSettingsTopLevelMenu(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 105:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    setDefaultHome(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 106:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    controlChangeHome(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 107:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean dateTimeChangeEnabled = setDateTimeChangeEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(dateTimeChangeEnabled ? 1 : 0);
                    return true;
                case 108:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    setPowerSavingMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 109:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    controlAirplaneMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 110:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    controlEyeMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 111:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    setBluetoothEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 112:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    controlRingerMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 113:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsOsVersionRemoteValid = isOsVersionRemoteValid();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsOsVersionRemoteValid ? 1 : 0);
                    return true;
                case 114:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    String deviceMacAddress = getDeviceMacAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(deviceMacAddress);
                    return true;
                case 115:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAddRuntimePermissionFixAppList = addRuntimePermissionFixAppList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddRuntimePermissionFixAppList ? 1 : 0);
                    return true;
                case 116:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zRemoveRuntimePermissionFixAppList = removeRuntimePermissionFixAppList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveRuntimePermissionFixAppList ? 1 : 0);
                    return true;
                case 117:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    List<String> runtimePermissionFixAppList = getRuntimePermissionFixAppList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(runtimePermissionFixAppList);
                    return true;
                case 118:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zClearRuntimePermissionFixAppList = clearRuntimePermissionFixAppList();
                    parcel2.writeNoException();
                    parcel2.writeInt(zClearRuntimePermissionFixAppList ? 1 : 0);
                    return true;
                case 119:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zPutStringSettingsSystemValue = putStringSettingsSystemValue(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPutStringSettingsSystemValue ? 1 : 0);
                    return true;
                case 120:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zPutIntSettingsSystemValue = putIntSettingsSystemValue(parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPutIntSettingsSystemValue ? 1 : 0);
                    return true;
                case 121:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zPutLongSettingsSystemValue = putLongSettingsSystemValue(parcel.readInt(), parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPutLongSettingsSystemValue ? 1 : 0);
                    return true;
                case 122:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zPutFloatSettingsSystemValue = putFloatSettingsSystemValue(parcel.readInt(), parcel.readString(), parcel.readFloat());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPutFloatSettingsSystemValue ? 1 : 0);
                    return true;
                case 123:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    String stringSettingsSystemValue = getStringSettingsSystemValue(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(stringSettingsSystemValue);
                    return true;
                case 124:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    int intSettingsSystemValue = getIntSettingsSystemValue(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(intSettingsSystemValue);
                    return true;
                case 125:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    long longSettingsSystemValue = getLongSettingsSystemValue(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(longSettingsSystemValue);
                    return true;
                case 126:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    float f = gettFloatSettingsSystemValue(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeFloat(f);
                    return true;
                case 127:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean streamVolume = setStreamVolume(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(streamVolume ? 1 : 0);
                    return true;
                case 128:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAdjustStreamVolume = adjustStreamVolume(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAdjustStreamVolume ? 1 : 0);
                    return true;
                case 129:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean lockScreenItems = setLockScreenItems(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(lockScreenItems ? 1 : 0);
                    return true;
                case 130:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean kanbanEnabled = setKanbanEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(kanbanEnabled ? 1 : 0);
                    return true;
                case 131:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean cameraShortcutEnabled = setCameraShortcutEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(cameraShortcutEnabled ? 1 : 0);
                    return true;
                case 132:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean bixbyShortcutEnabled = setBixbyShortcutEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(bixbyShortcutEnabled ? 1 : 0);
                    return true;
                case 133:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean switchInputMethodEnabled = setSwitchInputMethodEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(switchInputMethodEnabled ? 1 : 0);
                    return true;
                case 134:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean taskBarEnabled = setTaskBarEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(taskBarEnabled ? 1 : 0);
                    return true;
                case 135:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zResetLocalActivateState = resetLocalActivateState();
                    parcel2.writeNoException();
                    parcel2.writeInt(zResetLocalActivateState ? 1 : 0);
                    return true;
                case 136:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAddIgnoreFrequentRelaunchAppList = addIgnoreFrequentRelaunchAppList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddIgnoreFrequentRelaunchAppList ? 1 : 0);
                    return true;
                case 137:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zRemoveIgnoreFrequentRelaunchAppList = removeIgnoreFrequentRelaunchAppList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveIgnoreFrequentRelaunchAppList ? 1 : 0);
                    return true;
                case 138:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    List<String> ignoreFrequentRelaunchAppList = getIgnoreFrequentRelaunchAppList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(ignoreFrequentRelaunchAppList);
                    return true;
                case 139:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zClearIgnoreFrequentRelaunchAppList = clearIgnoreFrequentRelaunchAppList();
                    parcel2.writeNoException();
                    parcel2.writeInt(zClearIgnoreFrequentRelaunchAppList ? 1 : 0);
                    return true;
                case 140:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean fileShareDisabled = setFileShareDisabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(fileShareDisabled ? 1 : 0);
                    return true;
                case 141:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean randomMacDisabled = setRandomMacDisabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(randomMacDisabled ? 1 : 0);
                    return true;
                case 142:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zControlShowDeprecatedTarget = controlShowDeprecatedTarget(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zControlShowDeprecatedTarget ? 1 : 0);
                    return true;
                case 143:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zAllowLocationService = allowLocationService(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAllowLocationService ? 1 : 0);
                    return true;
                case 144:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsEyeComfortTurnedOn = isEyeComfortTurnedOn();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsEyeComfortTurnedOn ? 1 : 0);
                    return true;
                case 145:
                    parcel.enforceInterface("com.xuehai.system.mdm.MdmAidlInterface");
                    boolean zIsRandomMacDisabled = isRandomMacDisabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsRandomMacDisabled ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void activateFreeLicense() throws RemoteException;

    void activatePayLicense(String str) throws RemoteException;

    boolean addAppAlwaysRunningList(List<String> list) throws RemoteException;

    boolean addAppAutoRunningList(List<String> list) throws RemoteException;

    boolean addAppDomainsRules(List<String> list, List<String> list2, List<String> list3) throws RemoteException;

    boolean addDomainsRules(List<String> list) throws RemoteException;

    boolean addFirewallAllowApps(List<String> list) throws RemoteException;

    boolean addFirewallDenyApps(List<String> list) throws RemoteException;

    boolean addIgnoreFrequentRelaunchAppList(List<String> list) throws RemoteException;

    boolean addPackageToBatteryOptimizationWhiteList(String str) throws RemoteException;

    boolean addPackagesToNotificationWhiteList(List<String> list) throws RemoteException;

    boolean addRuntimePermissionFixAppList(List<String> list) throws RemoteException;

    boolean adjustStreamVolume(int i, int i2, int i3) throws RemoteException;

    boolean allowFactoryReset(boolean z) throws RemoteException;

    boolean allowFirmwareRecovery(boolean z) throws RemoteException;

    boolean allowHardwareKey(int i, boolean z) throws RemoteException;

    boolean allowLocationService(boolean z) throws RemoteException;

    boolean allowMultiWindowMode(boolean z) throws RemoteException;

    boolean allowMultipleUsers(boolean z) throws RemoteException;

    boolean allowOTAUpgrade(boolean z) throws RemoteException;

    boolean allowSafeMode(boolean z) throws RemoteException;

    boolean applyRuntimePermissions(String str, List<String> list) throws RemoteException;

    boolean cleanAppDomainRules() throws RemoteException;

    boolean cleanFirewallApps() throws RemoteException;

    void clearBootingAnimation() throws RemoteException;

    boolean clearIgnoreFrequentRelaunchAppList() throws RemoteException;

    boolean clearPackagesFromNotificationList() throws RemoteException;

    boolean clearRuntimePermissionFixAppList() throws RemoteException;

    void clearShuttingDownAnimation() throws RemoteException;

    void controlAirplaneMode(boolean z) throws RemoteException;

    void controlChangeHome(boolean z) throws RemoteException;

    void controlEyeMode(boolean z) throws RemoteException;

    void controlRingerMode(int i) throws RemoteException;

    void controlSettingsTopLevelMenu(String str) throws RemoteException;

    boolean controlShowDeprecatedTarget(boolean z) throws RemoteException;

    void deactivateDevice() throws RemoteException;

    void dumpAppUsageEventsSnapshot(long j, long j2) throws RemoteException;

    List<String> getAppAlwaysRunningList() throws RemoteException;

    List<String> getAppAutoRunningList() throws RemoteException;

    List<AppUsage> getAppUsage(long j, long j2) throws RemoteException;

    List<AppUsage2> getAppUsageV2(long j, long j2) throws RemoteException;

    List<NetworkStatsBean> getApplicationNetworkStats() throws RemoteException;

    boolean getAutomaticTime() throws RemoteException;

    String getDeviceMacAddress() throws RemoteException;

    List<String> getIgnoreFrequentRelaunchAppList() throws RemoteException;

    String getImei() throws RemoteException;

    int getIntSettingsSystemValue(int i, String str) throws RemoteException;

    long getLongSettingsSystemValue(int i, String str) throws RemoteException;

    List<String> getRuntimePermissionFixAppList() throws RemoteException;

    String getSerialNumber() throws RemoteException;

    String getSimSerialNumber() throws RemoteException;

    String getStringSettingsSystemValue(int i, String str) throws RemoteException;

    String getTopAppPackageName() throws RemoteException;

    float gettFloatSettingsSystemValue(int i, String str) throws RemoteException;

    boolean installApplication(String str) throws RemoteException;

    boolean isBluetoothEnabled() throws RemoteException;

    boolean isClipboardAllowed() throws RemoteException;

    boolean isDeviceAdminActivated() throws RemoteException;

    boolean isExternalStorageEnable() throws RemoteException;

    boolean isEyeComfortTurnedOn() throws RemoteException;

    boolean isFactoryResetAllowed() throws RemoteException;

    boolean isFirmwareRecoveryAllowed() throws RemoteException;

    boolean isFreeLicenseActivated() throws RemoteException;

    boolean isHardwareKeyAllow(int i) throws RemoteException;

    boolean isMTPAvailable() throws RemoteException;

    boolean isMdmSupport() throws RemoteException;

    boolean isOsVersionRemoteValid() throws RemoteException;

    boolean isPayLicenseActivated() throws RemoteException;

    boolean isRandomMacDisabled() throws RemoteException;

    boolean isUsbDebuggingEnabled() throws RemoteException;

    void lockScreen() throws RemoteException;

    boolean multipleUsersAllowed() throws RemoteException;

    void openSoftUpdateView() throws RemoteException;

    boolean putFloatSettingsSystemValue(int i, String str, float f) throws RemoteException;

    boolean putIntSettingsSystemValue(int i, String str, int i2) throws RemoteException;

    boolean putLongSettingsSystemValue(int i, String str, long j) throws RemoteException;

    boolean putStringSettingsSystemValue(int i, String str, String str2) throws RemoteException;

    TrafficInfo queryTrafficDetailBySummary(int i, String str, long j, long j2) throws RemoteException;

    TrafficInfo queryTrafficDetailByUid(int i, String str, long j, long j2, int i2) throws RemoteException;

    ParcelFileDescriptor readFileFromData(String str) throws RemoteException;

    void reboot() throws RemoteException;

    boolean removeAppAlwaysRunningList(List<String> list) throws RemoteException;

    boolean removeAppAutoRunningList(List<String> list) throws RemoteException;

    boolean removeDefaultLauncher(String str, String str2) throws RemoteException;

    void removeDevicePassword() throws RemoteException;

    boolean removeIgnoreFrequentRelaunchAppList(List<String> list) throws RemoteException;

    boolean removeLockScreen() throws RemoteException;

    boolean removePackagesFromNotificationWhiteList(List<String> list) throws RemoteException;

    boolean removeRuntimePermissionFixAppList(List<String> list) throws RemoteException;

    boolean resetFactory(String str) throws RemoteException;

    boolean resetLocalActivateState() throws RemoteException;

    boolean resetNetworkSetting() throws RemoteException;

    String runBugReport(String str) throws RemoteException;

    boolean setApplicationComponentState(ComponentName componentName, boolean z) throws RemoteException;

    void setApplicationState(String str, boolean z) throws RemoteException;

    void setApplicationUninstalltionState(String str, boolean z) throws RemoteException;

    boolean setAutomaticTime(boolean z) throws RemoteException;

    boolean setBixbyShortcutEnabled(boolean z) throws RemoteException;

    void setBluetoothEnable(boolean z) throws RemoteException;

    void setBootingAnimation(String str, String str2, String str3, long j) throws RemoteException;

    boolean setCameraShortcutEnabled(boolean z) throws RemoteException;

    boolean setCameraState(boolean z) throws RemoteException;

    boolean setClipboardEnabled(boolean z) throws RemoteException;

    boolean setDateTimeChangeEnabled(boolean z) throws RemoteException;

    void setDefaultHome(String str, String str2) throws RemoteException;

    boolean setDefaultLauncher(String str, String str2) throws RemoteException;

    boolean setDevelopHiddenState(boolean z) throws RemoteException;

    boolean setDropMenuHiddenState(boolean z) throws RemoteException;

    boolean setEmergencyCallOnly(boolean z) throws RemoteException;

    boolean setFileShareDisabled(boolean z) throws RemoteException;

    boolean setInputMethod(String str) throws RemoteException;

    boolean setIptablesOption(boolean z) throws RemoteException;

    boolean setKanbanEnabled(boolean z) throws RemoteException;

    boolean setLockScreenItems(boolean z) throws RemoteException;

    boolean setMTPEnabled(boolean z) throws RemoteException;

    boolean setMobileDataState(boolean z) throws RemoteException;

    boolean setNetworkSpeedState(boolean z) throws RemoteException;

    void setPowerSavingMode(int i) throws RemoteException;

    boolean setRandomMacDisabled(boolean z) throws RemoteException;

    boolean setScreenCapture(boolean z) throws RemoteException;

    boolean setSdCardState(boolean z) throws RemoteException;

    boolean setSettingEnabled(boolean z) throws RemoteException;

    boolean setSettingsHiddenState(boolean z) throws RemoteException;

    void setShuttingDownAnimation(String str, String str2) throws RemoteException;

    boolean setSpenPointImageState(boolean z) throws RemoteException;

    void setStatusBarButtonState(Map map) throws RemoteException;

    boolean setStreamVolume(int i, int i2, int i3) throws RemoteException;

    boolean setSwitchInputMethodEnabled(boolean z) throws RemoteException;

    boolean setSystemTime(long j) throws RemoteException;

    boolean setTaskBarEnabled(boolean z) throws RemoteException;

    boolean setTimeZone(String str) throws RemoteException;

    boolean setUsbDebuggingEnabled(boolean z) throws RemoteException;

    boolean setWiFiState(boolean z) throws RemoteException;

    void shoutdown() throws RemoteException;

    boolean startGPS(boolean z) throws RemoteException;

    void startSoftUpdate(int i, int i2) throws RemoteException;

    boolean startTcpdump(String str) throws RemoteException;

    boolean stopApp(String str) throws RemoteException;

    boolean stopTcpdump() throws RemoteException;

    boolean uninstallApplication(String str) throws RemoteException;

    void unlockScreen() throws RemoteException;

    boolean verifyDynamicCode(String str, String str2, int i) throws RemoteException;

    boolean verifyDynamicToken(String str, int i) throws RemoteException;

    boolean wipeApplicationData(String str) throws RemoteException;

    boolean wipeRecentTasks() throws RemoteException;
}
