package com.samsung.android.knox.restriction;

import android.os.Bundle;
import com.samsung.android.knox.SupportLibUtils;

public class RestrictionPolicy {
    public static final String ACTION_UPDATE_FOTA_VERSION_RESULT = "com.samsung.android.knox.intent.action.UPDATE_FOTA_VERSION_RESULT";
    public static final int ERROR_UPDATE_FOTA_ENABLED_BY_OTHER_ADMIN = 2;
    public static final int ERROR_UPDATE_FOTA_INVALID_VERSION_FORMAT = 3;
    public static final int ERROR_UPDATE_FOTA_NONE = 0;
    public static final int ERROR_UPDATE_FOTA_UNKNOWN = 4;
    public static final int ERROR_UPDATE_FOTA_UNKNOWN_SERVER = 1;
    public static final String EXTRA_UPDATE_FOTA_VERSION_STATUS = "com.samsung.android.knox.intent.extra.UPDATE_FOTA_VERSION_STATUS";
    public static final int LOCKSCREEN_MULTIPLE_WIDGET_VIEW = 1;
    public static final int LOCKSCREEN_SHORTCUTS_VIEW = 2;
    public static final int WEARABLE_GEAR_DEVICE = 1;
    private android.app.enterprise.RestrictionPolicy mRestrictionPolicy;

    public RestrictionPolicy(android.app.enterprise.RestrictionPolicy restrictionPolicy) {
        this.mRestrictionPolicy = restrictionPolicy;
    }

    public boolean allowAirplaneMode(boolean z) {
        return this.mRestrictionPolicy.allowAirplaneMode(z);
    }

    public boolean allowAndroidBeam(boolean z) {
        return this.mRestrictionPolicy.allowAndroidBeam(z);
    }

    public boolean allowAudioRecord(boolean z) {
        return this.mRestrictionPolicy.allowAudioRecord(z);
    }

    public boolean allowBackgroundProcessLimit(boolean z) {
        return this.mRestrictionPolicy.allowBackgroundProcessLimit(z);
    }

    public boolean allowBluetooth(boolean z) {
        return this.mRestrictionPolicy.allowBluetooth(z);
    }

    public boolean allowClipboardShare(boolean z) {
        return this.mRestrictionPolicy.allowClipboardShare(z);
    }

    public boolean allowDeveloperMode(boolean z) {
        return this.mRestrictionPolicy.allowDeveloperMode(z);
    }

    public boolean allowFactoryReset(boolean z) {
        return this.mRestrictionPolicy.allowFactoryReset(z);
    }

    public boolean allowFastEncryption(boolean z) {
        return this.mRestrictionPolicy.allowFastEncryption(z);
    }

    public boolean allowFirmwareRecovery(boolean z) {
        return this.mRestrictionPolicy.allowFirmwareRecovery(z);
    }

    public boolean allowGoogleAccountsAutoSync(boolean z) {
        return this.mRestrictionPolicy.allowGoogleAccountsAutoSync(z);
    }

    public boolean allowGoogleCrashReport(boolean z) {
        return this.mRestrictionPolicy.allowGoogleCrashReport(z);
    }

    public boolean allowKillingActivitiesOnLeave(boolean z) {
        return this.mRestrictionPolicy.allowKillingActivitiesOnLeave(z);
    }

    public boolean allowLockScreenView(int i, boolean z) {
        return this.mRestrictionPolicy.allowLockScreenView(i, z);
    }

    public boolean allowOTAUpgrade(boolean z) {
        return this.mRestrictionPolicy.allowOTAUpgrade(z);
    }

    public boolean allowPowerOff(boolean z) {
        return this.mRestrictionPolicy.allowPowerOff(z);
    }

    public boolean allowSBeam(boolean z) {
        return this.mRestrictionPolicy.allowSBeam(z);
    }

    public boolean allowSDCardMove(boolean z) {
        return this.mRestrictionPolicy.allowSDCardMove(z);
    }

    public boolean allowSDCardWrite(boolean z) {
        return this.mRestrictionPolicy.allowSDCardWrite(z);
    }

    public boolean allowSVoice(boolean z) {
        return this.mRestrictionPolicy.allowSVoice(z);
    }

    public boolean allowSafeMode(boolean z) {
        return this.mRestrictionPolicy.allowSafeMode(z);
    }

    public boolean allowScreenPinning(boolean z) {
        try {
            return this.mRestrictionPolicy.allowScreenPinning(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(RestrictionPolicy.class, "allowScreenPinning", new Class[]{Boolean.TYPE}, 17));
        }
    }

    public boolean allowSettingsChanges(boolean z) {
        return this.mRestrictionPolicy.allowSettingsChanges(z);
    }

    public boolean allowShareList(boolean z) {
        return this.mRestrictionPolicy.allowShareList(z);
    }

    public boolean allowSmartClipMode(boolean z) {
        try {
            return this.mRestrictionPolicy.allowSmartClipMode(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(RestrictionPolicy.class, "allowSmartClipMode", new Class[]{Boolean.TYPE}, 13));
        }
    }

    public boolean allowStatusBarExpansion(boolean z) {
        return this.mRestrictionPolicy.allowStatusBarExpansion(z);
    }

    public boolean allowStopSystemApp(boolean z) {
        return this.mRestrictionPolicy.allowStopSystemApp(z);
    }

    public boolean allowUsbHostStorage(boolean z) {
        return this.mRestrictionPolicy.allowUsbHostStorage(z);
    }

    public boolean allowUserMobileDataLimit(boolean z) {
        return this.mRestrictionPolicy.allowUserMobileDataLimit(z);
    }

    public boolean allowVideoRecord(boolean z) {
        return this.mRestrictionPolicy.allowVideoRecord(z);
    }

    public boolean allowVpn(boolean z) {
        return this.mRestrictionPolicy.allowVpn(z);
    }

    public boolean allowWallpaperChange(boolean z) {
        return this.mRestrictionPolicy.allowWallpaperChange(z);
    }

    public boolean allowWiFi(boolean z) {
        return this.mRestrictionPolicy.allowWiFi(z);
    }

    public boolean allowWifiDirect(boolean z) {
        return this.mRestrictionPolicy.allowWifiDirect(z);
    }

    public boolean enableWearablePolicy(int i, boolean z) {
        try {
            return this.mRestrictionPolicy.enableWearablePolicy(i, z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(RestrictionPolicy.class, "enableWearablePolicy", new Class[]{Integer.TYPE, Boolean.TYPE}, 19));
        }
    }

    public String getAllowedFOTAVersion() {
        try {
            return this.mRestrictionPolicy.getAllowedFOTAVersion();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(RestrictionPolicy.class, "getAllowedFOTAVersion", null, 20));
        }
    }

    public boolean isAirplaneModeAllowed() {
        return this.mRestrictionPolicy.isAirplaneModeAllowed();
    }

    public boolean isAndroidBeamAllowed() {
        return this.mRestrictionPolicy.isAndroidBeamAllowed();
    }

    public boolean isAudioRecordAllowed() {
        return this.mRestrictionPolicy.isAudioRecordAllowed();
    }

    public boolean isBackgroundDataEnabled() {
        return this.mRestrictionPolicy.isBackgroundDataEnabled();
    }

    public boolean isBackgroundProcessLimitAllowed() {
        return this.mRestrictionPolicy.isBackgroundProcessLimitAllowed();
    }

    public boolean isBackupAllowed(boolean z) {
        return this.mRestrictionPolicy.isBackupAllowed(z);
    }

    public boolean isBluetoothEnabled(boolean z) {
        return this.mRestrictionPolicy.isBluetoothEnabled(z);
    }

    public boolean isBluetoothTetheringEnabled() {
        return this.mRestrictionPolicy.isBluetoothTetheringEnabled();
    }

    public boolean isCameraEnabled(boolean z) {
        return this.mRestrictionPolicy.isCameraEnabled(z);
    }

    public boolean isCellularDataAllowed() {
        return this.mRestrictionPolicy.isCellularDataAllowed();
    }

    public boolean isClipboardAllowed(boolean z) {
        return this.mRestrictionPolicy.isClipboardAllowed(z);
    }

    public boolean isClipboardShareAllowed() {
        return this.mRestrictionPolicy.isClipboardShareAllowed();
    }

    public boolean isDeveloperModeAllowed() {
        return this.mRestrictionPolicy.isDeveloperModeAllowed();
    }

    public boolean isFactoryResetAllowed() {
        return this.mRestrictionPolicy.isFactoryResetAllowed();
    }

    public boolean isFastEncryptionAllowed(boolean z) {
        return this.mRestrictionPolicy.isFastEncryptionAllowed(z);
    }

    public boolean isFirmwareRecoveryAllowed(boolean z) {
        return this.mRestrictionPolicy.isFirmwareRecoveryAllowed(z);
    }

    public boolean isGoogleAccountsAutoSyncAllowed() {
        return this.mRestrictionPolicy.isGoogleAccountsAutoSyncAllowed();
    }

    public boolean isGoogleCrashReportAllowed() {
        return this.mRestrictionPolicy.isGoogleCrashReportAllowed();
    }

    public boolean isHeadphoneEnabled(boolean z) {
        return this.mRestrictionPolicy.isHeadphoneEnabled(z);
    }

    public boolean isHomeKeyEnabled() {
        return this.mRestrictionPolicy.isHomeKeyEnabled();
    }

    public boolean isKillingActivitiesOnLeaveAllowed() {
        return this.mRestrictionPolicy.isKillingActivitiesOnLeaveAllowed();
    }

    public boolean isLockScreenEnabled(boolean z) {
        return this.mRestrictionPolicy.isLockScreenEnabled(z);
    }

    public boolean isLockScreenViewAllowed(int i) {
        return this.mRestrictionPolicy.isLockScreenViewAllowed(i);
    }

    public boolean isMicrophoneEnabled(boolean z) {
        return this.mRestrictionPolicy.isMicrophoneEnabled(z);
    }

    public boolean isMockLocationEnabled() {
        return this.mRestrictionPolicy.isMockLocationEnabled();
    }

    public boolean isNonMarketAppAllowed() {
        return this.mRestrictionPolicy.isNonMarketAppAllowed();
    }

    public boolean isOTAUpgradeAllowed() {
        return this.mRestrictionPolicy.isOTAUpgradeAllowed();
    }

    public boolean isPowerOffAllowed() {
        return this.mRestrictionPolicy.isPowerOffAllowed();
    }

    public boolean isSBeamAllowed() {
        return this.mRestrictionPolicy.isSBeamAllowed();
    }

    public boolean isSDCardMoveAllowed(boolean z) {
        return this.mRestrictionPolicy.isSDCardMoveAllowed(z);
    }

    public boolean isSDCardWriteAllowed() {
        return this.mRestrictionPolicy.isSDCardWriteAllowed();
    }

    public boolean isSVoiceAllowed() {
        return this.mRestrictionPolicy.isSVoiceAllowed();
    }

    public boolean isSafeModeAllowed() {
        return this.mRestrictionPolicy.isSafeModeAllowed();
    }

    public boolean isScreenCaptureEnabled(boolean z) {
        return this.mRestrictionPolicy.isScreenCaptureEnabled(z);
    }

    public boolean isScreenPinningAllowed() {
        try {
            return this.mRestrictionPolicy.isScreenPinningAllowed();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(RestrictionPolicy.class, "isScreenPinningAllowed", null, 17));
        }
    }

    public boolean isSdCardEnabled() {
        return this.mRestrictionPolicy.isSdCardEnabled();
    }

    public boolean isSettingsChangesAllowed(boolean z) {
        return this.mRestrictionPolicy.isSettingsChangesAllowed(z);
    }

    public boolean isShareListAllowed() {
        return this.mRestrictionPolicy.isShareListAllowed();
    }

    public boolean isSmartClipModeAllowed() {
        try {
            return this.mRestrictionPolicy.isSmartClipModeAllowed();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(RestrictionPolicy.class, "isSmartClipModeAllowed", null, 13));
        }
    }

    public boolean isStatusBarExpansionAllowed() {
        return this.mRestrictionPolicy.isStatusBarExpansionAllowed();
    }

    public boolean isStopSystemAppAllowed() {
        return this.mRestrictionPolicy.isStopSystemAppAllowed();
    }

    public boolean isTetheringEnabled() {
        return this.mRestrictionPolicy.isTetheringEnabled();
    }

    public boolean isUsbDebuggingEnabled() {
        return this.mRestrictionPolicy.isUsbDebuggingEnabled();
    }

    public boolean isUsbHostStorageAllowed() {
        return this.mRestrictionPolicy.isUsbHostStorageAllowed();
    }

    public boolean isUsbMediaPlayerAvailable(boolean z) {
        return this.mRestrictionPolicy.isUsbMediaPlayerAvailable(z);
    }

    public boolean isUsbTetheringEnabled() {
        return this.mRestrictionPolicy.isUsbTetheringEnabled();
    }

    public boolean isUserMobileDataLimitAllowed() {
        return this.mRestrictionPolicy.isUserMobileDataLimitAllowed();
    }

    public boolean isVideoRecordAllowed() {
        return this.mRestrictionPolicy.isVideoRecordAllowed();
    }

    public boolean isVpnAllowed() {
        return this.mRestrictionPolicy.isVpnAllowed();
    }

    public boolean isWallpaperChangeAllowed() {
        return this.mRestrictionPolicy.isWallpaperChangeAllowed();
    }

    public boolean isWearablePolicyEnabled(int i) {
        try {
            return this.mRestrictionPolicy.isWearablePolicyEnabled(i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(RestrictionPolicy.class, "isWearablePolicyEnabled", new Class[]{Integer.TYPE}, 19));
        }
    }

    public boolean isWiFiEnabled(boolean z) {
        return this.mRestrictionPolicy.isWiFiEnabled(z);
    }

    public boolean isWifiDirectAllowed() {
        return this.mRestrictionPolicy.isWifiDirectAllowed();
    }

    public boolean isWifiTetheringEnabled() {
        return this.mRestrictionPolicy.isWifiTetheringEnabled();
    }

    public boolean setAllowNonMarketApps(boolean z) {
        return this.mRestrictionPolicy.setAllowNonMarketApps(z);
    }

    public boolean setAllowedFOTAVersion(String str, Bundle bundle) {
        try {
            return this.mRestrictionPolicy.setAllowedFOTAVersion(str, bundle);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(RestrictionPolicy.class, "setAllowedFOTAVersion", new Class[]{String.class, Bundle.class}, 20));
        }
    }

    public boolean setBackgroundData(boolean z) {
        return this.mRestrictionPolicy.setBackgroundData(z);
    }

    public boolean setBackup(boolean z) {
        return this.mRestrictionPolicy.setBackup(z);
    }

    public boolean setBluetoothTethering(boolean z) {
        return this.mRestrictionPolicy.setBluetoothTethering(z);
    }

    public boolean setCameraState(boolean z) {
        return this.mRestrictionPolicy.setCameraState(z);
    }

    public boolean setCellularData(boolean z) {
        return this.mRestrictionPolicy.setCellularData(z);
    }

    public boolean setClipboardEnabled(boolean z) {
        return this.mRestrictionPolicy.setClipboardEnabled(z);
    }

    public boolean setHeadphoneState(boolean z) {
        return this.mRestrictionPolicy.setHeadphoneState(z);
    }

    public boolean setHomeKeyState(boolean z) {
        return this.mRestrictionPolicy.setHomeKeyState(z);
    }

    public boolean setLockScreenState(boolean z) {
        return this.mRestrictionPolicy.setLockScreenState(z);
    }

    public boolean setMicrophoneState(boolean z) {
        return this.mRestrictionPolicy.setMicrophoneState(z);
    }

    public boolean setMockLocation(boolean z) {
        return this.mRestrictionPolicy.setMockLocation(z);
    }

    public boolean setScreenCapture(boolean z) {
        return this.mRestrictionPolicy.setScreenCapture(z);
    }

    public boolean setSdCardState(boolean z) {
        return this.mRestrictionPolicy.setSdCardState(z);
    }

    public boolean setTethering(boolean z) {
        return this.mRestrictionPolicy.setTethering(z);
    }

    public boolean setUsbDebuggingEnabled(boolean z) {
        return this.mRestrictionPolicy.setUsbDebuggingEnabled(z);
    }

    public boolean setUsbMediaPlayerAvailability(boolean z) {
        return this.mRestrictionPolicy.setUsbMediaPlayerAvailability(z);
    }

    public boolean setUsbTethering(boolean z) {
        return this.mRestrictionPolicy.setUsbTethering(z);
    }

    public boolean setWifiTethering(boolean z) {
        return this.mRestrictionPolicy.setWifiTethering(z);
    }
}
