package android.app.csdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.telephony.data.ApnSetting;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class CSDKManager {
    public static final String GESTURAL_BUTTON_MODE = "com.android.internal.systemui.navbar.gestural";
    public static final int LOCKSCREEN_MODE_NONE = 0;
    public static final int LOCKSCREEN_MODE_PASSWORD = 2;
    public static final int LOCKSCREEN_MODE_PIN = 3;
    public static final int LOCKSCREEN_MODE_SWIPE = 1;
    public static final int LOCKSCREEN_MODE_UNLOCK = 4;
    public static final int LOCK_TASK_FEATURE_BLOCK_ACTIVITY_START_IN_TASK = 64;
    public static final int LOCK_TASK_FEATURE_GLOBAL_ACTIONS = 16;
    public static final int LOCK_TASK_FEATURE_HOME = 4;
    public static final int LOCK_TASK_FEATURE_KEYGUARD = 32;
    public static final int LOCK_TASK_FEATURE_NONE = 0;
    public static final int LOCK_TASK_FEATURE_NOTIFICATIONS = 2;
    public static final int LOCK_TASK_FEATURE_OVERVIEW = 8;
    public static final int LOCK_TASK_FEATURE_SYSTEM_INFO = 1;
    public static final String THREE_BUTTON_MODE = "com.android.internal.systemui.navbar.threebutton";
    public static final String TWO_BUTTON_MODE = "com.android.internal.systemui.navbar.twobutton";
    public static final int WHEN_TO_DREAM_EITHER = 2;
    public static final int WHEN_TO_DREAM_NEVER = 3;
    public static final int WHEN_TO_DREAM_WHILE_CHARGING = 0;
    public static final int WHEN_TO_DREAM_WHILE_DOCKED = 1;

    public CSDKManager(Context context) {
        throw new RuntimeException("Stub!");
    }

    public static String getLauncherPackageName(Context context, int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean aCLK(String str) {
        throw new RuntimeException("Stub!");
    }

    public void activateLicense(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean activeSsid(String str) {
        throw new RuntimeException("Stub!");
    }

    public void addAppManageBlackListV3(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addAppManageWhiteListV3(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addAppOpsPermissionWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addAutostartPackageBlackList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addDozeSettingWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addInstallPackageBlackList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addInstallPackageWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addNotificationWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addPersistentAppList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addPersistentPreferredActivity(IntentFilter intentFilter, ComponentName componentName) {
        throw new RuntimeException("Stub!");
    }

    public void addRuntimePermissionWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public boolean addSsid(String str, String str2, int i, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void addUninstallPackageBlackList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addUninstallPackageWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addWifiBlackList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void addWifiWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void allowCast(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void allowClipboard(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public Bitmap captureScreen() {
        throw new RuntimeException("Stub!");
    }

    public void changeDeviceName(String str) {
        throw new RuntimeException("Stub!");
    }

    public void clearApplicationCacheFiles(String str) {
        throw new RuntimeException("Stub!");
    }

    public void clearApplicationUserData(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean clearCustomBootAnim() {
        throw new RuntimeException("Stub!");
    }

    public boolean clearCustomShutAnim() {
        throw new RuntimeException("Stub!");
    }

    public void clearDefault(String str) {
        throw new RuntimeException("Stub!");
    }

    public void clearDefaultAssistant() {
        throw new RuntimeException("Stub!");
    }

    public void clearDefaultBrowser() {
        throw new RuntimeException("Stub!");
    }

    public void clearDefaultDialer() {
        throw new RuntimeException("Stub!");
    }

    public void clearDefaultIntent(Intent intent) {
        throw new RuntimeException("Stub!");
    }

    public void clearDefaultSms() {
        throw new RuntimeException("Stub!");
    }

    public void clearPackagePersistentPreferredActivities(String str) {
        throw new RuntimeException("Stub!");
    }

    public void clipSystemSettings(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void clipSystemWifiDetail(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean copyFileToSdcard(File file, File file2) {
        throw new RuntimeException("Stub!");
    }

    public boolean copyFileToSdcard(InputStream inputStream, String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public boolean copySdcardFile(File file, File file2) {
        throw new RuntimeException("Stub!");
    }

    public boolean copySdcardFile(String str, OutputStream outputStream) {
        throw new RuntimeException("Stub!");
    }

    public boolean createApn(ApnSetting apnSetting) {
        throw new RuntimeException("Stub!");
    }

    public boolean createVpn(String str, String str2, String str3, String str4) {
        throw new RuntimeException("Stub!");
    }

    public boolean deactivateLicense(String str) {
        throw new RuntimeException("Stub!");
    }

    public int deleteApn(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public boolean deleteSdcardFile(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean deleteVpn(String str) {
        throw new RuntimeException("Stub!");
    }

    public void disableApplicationManageV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableAutostart(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableBatteryProtectMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disableCamera(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disableGestureMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disableHiddenGame(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableInstallation(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disableLockScreenNotification(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableLockScreenStatusBarPanel(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableOemUnLock(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disablePowerSaveMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableRandomMac(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableStatusBarNotification(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disableStatusBarPanel(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableTabletMasterAutostart(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableUnInstallation(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableVibrator(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disableWifiDisplay(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowAirplaneModeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowAllDefault(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowApnV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowApplicationPermissionsPage(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowAssistantReset(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowAutoDateAndTimeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowBatteryProtectMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowBluetoothShareV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowBluetoothTetheringV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowBluetoothV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowBrowserReset(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowChangeWorkMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowChargingSound(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowDataV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowDevModeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowDialerReset(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowDozeSetting(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowFactoryResetV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowLocationV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowLockScreenModeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowLockScreenNotificationV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowMultiUserV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowMultiWindow(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowOemUnLock(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowOtaDownloadEnabled(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowOtaOverNightEnabled(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowPowerSaveMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowScreenLockSound(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetAlarmVolumeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetAutoRotation(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetBootTimeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetBrightnessV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetFontSize(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetInputMethodV3(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetLiftToWake(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetMediaVolumeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetNotificationVolumV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetShutDownTimeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetSleepTimeoutV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetSmartRotation(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetSysDateV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetSysTimeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetSysTimeZoneV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetTapToWake(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSetWhenToDream(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowSimcardV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSmsReset(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSpecialAccessPermissionsPage(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowStatusBarNotification(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowStayAwake(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowSwitchLauncherV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowSwitchLockScreenMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowSystemNavigationMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowTouchSound(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void disallowTouchVibration(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowUsbDebuggingV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowUsbModeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowUsbTetheringV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowWifiAccessPointV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowWifiAdvanceSettingsV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowWifiDirectV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowWifiHotspotV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean disallowWifiV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableAccessibility(String str, String str2, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableAirplaneMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableAllUnkownsourcesV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableAutoBrightness(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableAutoRotation(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableBluetoothTetheringV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableBluetoothV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableCaptureScreenV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableChargingSound(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableDataV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableDevMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableDevModeV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableDeviceAdmin(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableLiftToWake(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableLocationV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableMassStorage(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableNotDisturb(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableNotification(String str, String str2, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableOtaCheckStatus(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableOtaDownloadLte(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableOtaDownloadStatus(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableOtaOverNightStatus(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableOverlayWindow(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enablePermissionDialogButton(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enablePictureInPicture(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableScreenLockSound(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableScreenSaver(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableSearchV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableSim(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableSmartAutoRotation(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableStayAwake(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableSystemAutoUpdate(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableTapToWake(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableTouchSound(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableTouchVibration(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableUnkownsourcesV3(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableUsageStats(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableUsbDebugging(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableUsbHostPermission(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableUsbTetheringV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableWifiBlackList(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableWifiCaptiveV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableWifiHotspotV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableWifiState(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean enableWifiV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableWifiWhiteList(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableWorkMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void enableWriteSettings(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void fullScreenForever(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public List<String> getAppManageBlackListV3() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getAppManageWhiteListV3() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getAppOpsPermissionWhiteList() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getAutostartPackageBlackList() {
        throw new RuntimeException("Stub!");
    }

    public long getCurrentUsbMode() {
        throw new RuntimeException("Stub!");
    }

    public boolean getCustomBootLogo() {
        throw new RuntimeException("Stub!");
    }

    public boolean getCustomSplashPath() {
        throw new RuntimeException("Stub!");
    }

    public long getDefaultUsbMode() {
        throw new RuntimeException("Stub!");
    }

    public String getDeviceInfo(int i) {
        throw new RuntimeException("Stub!");
    }

    public List<String> getDisplayBlacklistV3() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getDozeSettingWhiteList() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getInstallPackageBlackList() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getInstallPackageWhiteList() {
        throw new RuntimeException("Stub!");
    }

    public int getLockTaskFeatures() {
        throw new RuntimeException("Stub!");
    }

    public String[] getLockTaskPackages() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getNotificationWhiteList() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getPersistentAppList() {
        throw new RuntimeException("Stub!");
    }

    public String getRecentTasks(int i) {
        throw new RuntimeException("Stub!");
    }

    public List<String> getRuntimePermissionWhiteList() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getSdcardDirList(String str) {
        throw new RuntimeException("Stub!");
    }

    public Map<String, String> getSimContacts() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getUninstallPackageBlackList() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getUninstallPackageWhiteList() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getWifiBlackList() {
        throw new RuntimeException("Stub!");
    }

    public List<String> getWifiWhiteList() {
        throw new RuntimeException("Stub!");
    }

    public boolean hasWorkMode() {
        throw new RuntimeException("Stub!");
    }

    public boolean hideBackSoftKey(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean hideHomeSoftKey(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean hideMenuSoftKey(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void hideResolverAlwaysButton(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean hideStatusBar(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean hideUsbMenu(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void installPackage(String str) {
        throw new RuntimeException("Stub!");
    }

    public void installPackage(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean isBluetoothDisable() {
        throw new RuntimeException("Stub!");
    }

    public boolean isCustomCharge() {
        throw new RuntimeException("Stub!");
    }

    public boolean isCustomFastBoot() {
        throw new RuntimeException("Stub!");
    }

    public boolean isCustomHardRst() {
        throw new RuntimeException("Stub!");
    }

    public boolean isCustomOtg() {
        throw new RuntimeException("Stub!");
    }

    public boolean isCustomRecoveryV3() {
        throw new RuntimeException("Stub!");
    }

    public boolean isCustomSdUpdate() {
        throw new RuntimeException("Stub!");
    }

    public boolean isCustomized(int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean isEnabledEyeMode() {
        throw new RuntimeException("Stub!");
    }

    public boolean isFactoryResetDisable() {
        throw new RuntimeException("Stub!");
    }

    public boolean isFlashLocked() {
        throw new RuntimeException("Stub!");
    }

    public boolean isLicenseKeyEnabled(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean isMassStorageEnabled() {
        throw new RuntimeException("Stub!");
    }

    public boolean isUsbDebuggingEnabled() {
        throw new RuntimeException("Stub!");
    }

    public boolean isWorkModeDisallowed() {
        throw new RuntimeException("Stub!");
    }

    public boolean isWorkModeEnabled() {
        throw new RuntimeException("Stub!");
    }

    public void killApplicationProcess(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean launchFactoryReset() {
        throw new RuntimeException("Stub!");
    }

    public boolean launchNetworkReset() {
        throw new RuntimeException("Stub!");
    }

    public boolean makeDirectoryInSdcard(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean modifyApConfig(String str, String str2, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void putSettingsValue(int i, int i2, String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public List<ApnSetting> queryApn(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean rebootDevice() {
        throw new RuntimeException("Stub!");
    }

    public boolean releaseKeyControl() {
        throw new RuntimeException("Stub!");
    }

    public void removeAllRecentTasks() {
        throw new RuntimeException("Stub!");
    }

    public void removeAppManageBlackListV3(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeAppManageWhiteListV3(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeAppOpsPermissionWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeAutostartPackageBlackList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeDeviceOwner(String str) {
        throw new RuntimeException("Stub!");
    }

    public void removeDisplayBlacklistV3(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeDozeSettingWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeInstallPackageBlackList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeInstallPackageWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeNotificationWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removePersistentAppList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeRuntimePermissionWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public boolean removeSsid(String str) {
        throw new RuntimeException("Stub!");
    }

    public void removeTask(int i) {
        throw new RuntimeException("Stub!");
    }

    public void removeUninstallPackageBlackList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeUninstallPackageWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeWifiBlackList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void removeWifiWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public boolean requestKeyControl(int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean requestKeyControl_V3(int i, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void restoreApn() {
        throw new RuntimeException("Stub!");
    }

    public void setAlarmVolumeValueV3(int i) {
        throw new RuntimeException("Stub!");
    }

    public void setAppOpsPermissions(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setAutoState(int i) {
        throw new RuntimeException("Stub!");
    }

    public void setBootTime(boolean z, int i, int i2, int i3, long j) {
        throw new RuntimeException("Stub!");
    }

    public boolean setBrightness(int i) {
        throw new RuntimeException("Stub!");
    }

    public void setComponentEnabled(ComponentName componentName, int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public void setCurrentUsbMode(int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean setCustomBootAnim(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomBootLogo(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomCharge(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomFastBoot(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomHardRst(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomLauncher(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public boolean setCustomLogo(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomOtg(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomRecoveryV3(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomSdUpdate(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean setCustomShutAnim(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomSplashPath(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean setDefaultApnV3(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setDefaultAssistant(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setDefaultBrowser(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setDefaultDialer(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean setDefaultInputMethod(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setDefaultIntent(Intent intent, String str) {
        throw new RuntimeException("Stub!");
    }

    public void setDefaultSms(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setDefaultUsbMode(int i) {
        throw new RuntimeException("Stub!");
    }

    public void setDefaultVideoPlayer(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean setDeviceOwner(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setDisplayBlacklistV3(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void setEnabledEyeMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setFlashLocked(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setFontSize(int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean setHttpProxy(String str, int i, List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void setLanguage(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public boolean setLauncher(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean setLocationMode(int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean setLockPassword(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean setLockPassword(boolean z, String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean setLockPin(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean setLockPin(boolean z, String str) {
        throw new RuntimeException("Stub!");
    }

    public void setLockScreenMode(int i) {
        throw new RuntimeException("Stub!");
    }

    public void setLockTaskFeatures(int i) {
        throw new RuntimeException("Stub!");
    }

    public void setLockTaskPackages(String[] strArr) {
        throw new RuntimeException("Stub!");
    }

    public void setMediaVolumeValueV3(int i) {
        throw new RuntimeException("Stub!");
    }

    public void setNotificationVolumeValueV3(int i) {
        throw new RuntimeException("Stub!");
    }

    public void setNotificationWhiteList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public void setPackageEnabled(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setPersistValue(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public void setPersistentAppList(List<String> list) {
        throw new RuntimeException("Stub!");
    }

    public boolean setPreferApn(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public void setRuntimePermissions(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setSafeModeDisabled(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setShutDownTime(boolean z, int i, int i2, int i3, long j) {
        throw new RuntimeException("Stub!");
    }

    public void setSleepTimeoutV3(int i) {
        throw new RuntimeException("Stub!");
    }

    public void setSysDate(int i, int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public void setSysTime(long j) {
        throw new RuntimeException("Stub!");
    }

    public boolean setSystemNavigationMode(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setTimeZoneV3(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setUserRestriction(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void setWhenToDream(int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean shutdownDevice() {
        throw new RuntimeException("Stub!");
    }

    public boolean sleepDevice() {
        throw new RuntimeException("Stub!");
    }

    public void uninstallPackage(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean unlockScreen() {
        throw new RuntimeException("Stub!");
    }

    public boolean wakeupDevice() {
        throw new RuntimeException("Stub!");
    }
}
