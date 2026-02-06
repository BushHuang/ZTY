package com.samsung.android.knox.application;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.SupportLibUtils;
import com.sec.enterprise.knox.AdvancedRestrictionPolicy;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationPolicy {
    public static final String ACTION_APPLICATION_FOCUS_CHANGE = "com.samsung.android.knox.intent.action.APPLICATION_FOCUS_CHANGE";
    public static final String ACTION_PREVENT_APPLICATION_START = "com.samsung.android.knox.intent.action.PREVENT_APPLICATION_START";
    public static final String ACTION_PREVENT_APPLICATION_STOP = "com.samsung.android.knox.intent.action.PREVENT_APPLICATION_STOP";
    public static final String DEFAULT_TYPE_AUDIO = "audio/*";
    public static final String DEFAULT_TYPE_PDF = "application/pdf";
    public static final String EXTRA_APPLICATION_FOCUS_COMPONENT_NAME = "com.samsung.android.knox.intent.extra.APPLICATION_FOCUS_COMPONENT_NAME";
    public static final String EXTRA_APPLICATION_FOCUS_STATUS = "com.samsung.android.knox.intent.extra.APPLICATION_FOCUS_STATUS";
    public static final String EXTRA_APPLICATION_PACKAGE_NAME = "com.samsung.android.knox.intent.extra.APPLICATION_PACKAGE_NAME";
    public static final String EXTRA_ERROR_CLASS = "com.samsung.android.knox.intent.extra.ERROR_CLASS";
    public static final String EXTRA_ERROR_REASON = "com.samsung.android.knox.intent.extra.ERROR_REASON";
    public static final String EXTRA_ERROR_TYPE = "com.samsung.android.knox.intent.extra.ERROR_TYPE";
    public static final String EXTRA_USER_ID = "com.samsung.android.knox.intent.extra.USER_ID";
    private static final String PROXY_FLAGS = "proxy-flags";
    private AdvancedRestrictionPolicy mAdvancedRestrictionPolicy;
    private android.app.enterprise.ApplicationPolicy mApplicationPolicy;
    public static final Intent SMS_MMS_TASK = createSmsMmsTask();
    public static final Intent LAUNCHER_TASK = createLauncherTask();
    public static final Intent OPEN_URL_TASK = createOpenURLTask();
    public static final Intent OPEN_PDF_TASK = createOpenPDFTask();
    public static final Intent OPEN_DIALER_TASK = createOpenDialerTask();
    public static final Intent PLAY_AUDIO_TASK = createAudioTask();
    public static final Intent PLAY_VIDEO_TASK = createVideoTask();
    public static final Intent DEVICE_ASSISTANCE_ACTIVITY_TASK = createAssistActivityTask();
    public static final Intent DEVICE_ASSISTANCE_SERVICE_TASK = createAssistServiceTask();

    public ApplicationPolicy(android.app.enterprise.ApplicationPolicy applicationPolicy, AdvancedRestrictionPolicy advancedRestrictionPolicy) {
        this.mApplicationPolicy = applicationPolicy;
        this.mAdvancedRestrictionPolicy = advancedRestrictionPolicy;
    }

    private Bundle convertToOldBundle(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("proxyFlags", bundle.getInt("proxy-flags", 0));
        return bundle2;
    }

    private static Intent createAssistActivityTask() {
        return new Intent("android.intent.action.ASSIST");
    }

    private static Intent createAssistServiceTask() {
        return new Intent("android.service.voice.VoiceInteractionService");
    }

    private static Intent createAudioTask() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File("audio.mp3")), "audio/*");
        return intent;
    }

    private static Intent createLauncherTask() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        return intent;
    }

    private static Intent createOpenDialerTask() {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return intent;
    }

    private static Intent createOpenPDFTask() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File("file.pdf")), "application/pdf");
        return intent;
    }

    private static Intent createOpenURLTask() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("http://"));
        return intent;
    }

    private static Intent createSmsMmsTask() {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("smsto:"));
        return intent;
    }

    private static Intent createVideoTask() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setType("video/*");
        return intent;
    }

    public boolean addAppPackageNameToBlackList(String str) {
        return this.mApplicationPolicy.addAppPackageNameToBlackList(str);
    }

    public boolean addAppPackageNameToWhiteList(String str) {
        return this.mApplicationPolicy.addAppPackageNameToWhiteList(str);
    }

    public boolean addAppPackageNameToWhiteList(String str, boolean z) {
        return this.mApplicationPolicy.addAppPackageNameToWhiteList(str, z);
    }

    public boolean addAppPermissionToBlackList(String str) {
        return this.mApplicationPolicy.addAppPermissionToBlackList(str);
    }

    public boolean addAppSignatureToBlackList(String str) {
        return this.mApplicationPolicy.addAppSignatureToBlackList(str);
    }

    public boolean addAppSignatureToWhiteList(String str) {
        return this.mApplicationPolicy.addAppSignatureToWhiteList(str);
    }

    public boolean addAppSignatureToWhiteList(String str, boolean z) {
        return this.mApplicationPolicy.addAppSignatureToWhiteList(str, z);
    }

    public boolean addHomeShortcut(String str, String str2) {
        return this.mApplicationPolicy.addHomeShortcut(str, str2);
    }

    public boolean addNewAdminActivationAppWhiteList(List<String> list) {
        try {
            return this.mAdvancedRestrictionPolicy.addNewAdminActivationAppWhiteList(list);
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "addNewAdminActivationAppWhiteList", new Class[]{List.class}, 11));
        }
    }

    public int addPackageToBatteryOptimizationWhiteList(AppIdentity appIdentity) {
        try {
            return this.mApplicationPolicy.addPackageToBatteryOptimizationWhiteList(AppIdentity.convertToOld(appIdentity));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "addPackageToBatteryOptimizationWhiteList", new Class[]{AppIdentity.class}, 20));
        }
    }

    public boolean addPackagesToClearCacheBlackList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToClearCacheBlackList(list);
    }

    public boolean addPackagesToClearCacheWhiteList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToClearCacheWhiteList(list);
    }

    public boolean addPackagesToClearDataBlackList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToClearDataBlackList(list);
    }

    public boolean addPackagesToClearDataWhiteList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToClearDataWhiteList(list);
    }

    public boolean addPackagesToDisableClipboardBlackList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToDisableClipboardBlackList(list);
    }

    public boolean addPackagesToDisableClipboardWhiteList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToDisableClipboardWhiteList(list);
    }

    public boolean addPackagesToDisableClipboardWhiteList(List<String> list, boolean z) {
        return this.mApplicationPolicy.addPackagesToDisableClipboardWhiteList(list, z);
    }

    public boolean addPackagesToDisableUpdateBlackList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToDisableUpdateBlackList(list);
    }

    public boolean addPackagesToDisableUpdateWhiteList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToDisableUpdateWhiteList(list);
    }

    public boolean addPackagesToDisableUpdateWhiteList(List<String> list, boolean z) {
        return this.mApplicationPolicy.addPackagesToDisableUpdateWhiteList(list, z);
    }

    public boolean addPackagesToFocusMonitoringList(List<String> list) {
        try {
            return this.mApplicationPolicy.addPackagesToFocusMonitoringList(list);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "addPackagesToFocusMonitoringList", new Class[]{List.class}, 15));
        }
    }

    public boolean addPackagesToForceStopBlackList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToForceStopBlackList(list);
    }

    public boolean addPackagesToForceStopWhiteList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToForceStopWhiteList(list);
    }

    public boolean addPackagesToForceStopWhiteList(List<String> list, boolean z) {
        return this.mApplicationPolicy.addPackagesToForceStopWhiteList(list, z);
    }

    public boolean addPackagesToNotificationBlackList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToNotificationBlackList(list);
    }

    public boolean addPackagesToNotificationWhiteList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToNotificationWhiteList(list);
    }

    public boolean addPackagesToNotificationWhiteList(List<String> list, boolean z) {
        return this.mApplicationPolicy.addPackagesToNotificationWhiteList(list, z);
    }

    public List<String> addPackagesToPreventStartBlackList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToPreventStartBlackList(list);
    }

    public boolean addPackagesToWidgetBlackList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToWidgetBlackList(list);
    }

    public boolean addPackagesToWidgetWhiteList(List<String> list) {
        return this.mApplicationPolicy.addPackagesToWidgetWhiteList(list);
    }

    public boolean addPackagesToWidgetWhiteList(List<String> list, boolean z) {
        return this.mApplicationPolicy.addPackagesToWidgetWhiteList(list, z);
    }

    public boolean addUsbDevicesForDefaultAccess(String str, List<UsbDeviceConfig> list) {
        try {
            return this.mApplicationPolicy.addUsbDevicesForDefaultAccess(str, UsbDeviceConfig.convertToOldList(list));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "addUsbDevicesForDefaultAccess", new Class[]{String.class, List.class}, 12));
        }
    }

    public int applyRuntimePermissions(AppIdentity appIdentity, List<String> list, int i) {
        try {
            return this.mApplicationPolicy.applyRuntimePermissions(AppIdentity.convertToOld(appIdentity), list, i);
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "applyRuntimePermissions", new Class[]{AppIdentity.class, List.class, Integer.TYPE}, 19));
        }
    }

    public boolean changeApplicationIcon(String str, byte[] bArr) {
        return this.mApplicationPolicy.changeApplicationIcon(str, bArr);
    }

    public boolean changeApplicationName(String str, String str2) {
        return this.mApplicationPolicy.changeApplicationName(str, str2);
    }

    public boolean clearAppPackageNameFromList() {
        return this.mApplicationPolicy.clearAppPackageNameFromList();
    }

    public boolean clearAppSignatureFromList() {
        return this.mApplicationPolicy.clearAppSignatureFromList();
    }

    public boolean clearDisableClipboardBlackList() {
        return this.mApplicationPolicy.clearDisableClipboardBlackList();
    }

    public boolean clearDisableClipboardWhiteList() {
        return this.mApplicationPolicy.clearDisableClipboardWhiteList();
    }

    public boolean clearDisableUpdateBlackList() {
        return this.mApplicationPolicy.clearDisableUpdateBlackList();
    }

    public boolean clearDisableUpdateWhiteList() {
        return this.mApplicationPolicy.clearDisableUpdateWhiteList();
    }

    public boolean clearFocusMonitoringList() {
        try {
            return this.mApplicationPolicy.clearFocusMonitoringList();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "clearFocusMonitoringList", null, 15));
        }
    }

    public boolean clearNewAdminActivationAppWhiteList() {
        try {
            return this.mAdvancedRestrictionPolicy.clearNewAdminActivationAppWhiteList();
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "clearNewAdminActivationAppWhiteList", null, 11));
        }
    }

    public boolean clearPackagesFromDisableClipboardList() {
        return this.mApplicationPolicy.clearPackagesFromDisableClipboardList();
    }

    public boolean clearPackagesFromDisableUpdateList() {
        return this.mApplicationPolicy.clearPackagesFromDisableUpdateList();
    }

    public boolean clearPackagesFromForceStopList() {
        return this.mApplicationPolicy.clearPackagesFromForceStopList();
    }

    public boolean clearPackagesFromNotificationList() {
        return this.mApplicationPolicy.clearPackagesFromNotificationList();
    }

    public boolean clearPackagesFromWidgetList() {
        return this.mApplicationPolicy.clearPackagesFromWidgetList();
    }

    public boolean clearPreventStartBlackList() {
        return this.mApplicationPolicy.clearPreventStartBlackList();
    }

    public boolean clearUsbDevicesForDefaultAccess(String str) {
        try {
            return this.mApplicationPolicy.clearUsbDevicesForDefaultAccess(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "clearUsbDevicesForDefaultAccess", new Class[]{String.class}, 12));
        }
    }

    public boolean deleteHomeShortcut(String str, String str2) {
        return this.mApplicationPolicy.deleteHomeShortcut(str, str2);
    }

    public void disableAndroidBrowser() {
        this.mApplicationPolicy.disableAndroidBrowser();
    }

    public void disableAndroidMarket() {
        this.mApplicationPolicy.disableAndroidMarket();
    }

    public void disableVoiceDialer() {
        this.mApplicationPolicy.disableVoiceDialer();
    }

    public void disableYouTube() {
        this.mApplicationPolicy.disableYouTube();
    }

    public void enableAndroidBrowser() {
        this.mApplicationPolicy.enableAndroidBrowser();
    }

    public void enableAndroidMarket() {
        this.mApplicationPolicy.enableAndroidMarket();
    }

    public void enableVoiceDialer() {
        this.mApplicationPolicy.enableVoiceDialer();
    }

    public void enableYouTube() {
        this.mApplicationPolicy.enableYouTube();
    }

    public AppInfoLastUsage[] getAllAppLastUsage() {
        return AppInfoLastUsage.convertToNewArray(this.mApplicationPolicy.getAllAppLastUsage());
    }

    public List<DefaultAppConfiguration> getAllDefaultApplications() {
        try {
            return DefaultAppConfiguration.convertToNewList(this.mApplicationPolicy.getAllDefaultApplications());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getAllDefaultApplications", null, 15));
        }
    }

    public Map<AppWidgetProviderInfo, ArrayList<Integer>> getAllWidgets(String str) {
        return this.mApplicationPolicy.getAllWidgets(str);
    }

    public List<AppControlInfo> getAppPackageNamesAllBlackLists() {
        return AppControlInfo.convertToNewList(this.mApplicationPolicy.getAppPackageNamesAllBlackLists());
    }

    public List<AppControlInfo> getAppPackageNamesAllWhiteLists() {
        return AppControlInfo.convertToNewList(this.mApplicationPolicy.getAppPackageNamesAllWhiteLists());
    }

    public List<AppControlInfo> getAppPermissionsAllBlackLists() {
        return AppControlInfo.convertToNewList(this.mApplicationPolicy.getAppPermissionsAllBlackLists());
    }

    public String[] getAppPermissionsBlackList() {
        return this.mApplicationPolicy.getAppPermissionsBlackList();
    }

    public List<AppControlInfo> getAppSignaturesAllBlackLists() {
        return AppControlInfo.convertToNewList(this.mApplicationPolicy.getAppSignaturesAllBlackLists());
    }

    public List<AppControlInfo> getAppSignaturesAllWhiteLists() {
        return AppControlInfo.convertToNewList(this.mApplicationPolicy.getAppSignaturesAllWhiteLists());
    }

    public String[] getAppSignaturesBlackList() {
        return this.mApplicationPolicy.getAppSignaturesBlackList();
    }

    public String[] getAppSignaturesWhiteList() {
        return this.mApplicationPolicy.getAppSignaturesWhiteList();
    }

    public long getApplicationCacheSize(String str) {
        return this.mApplicationPolicy.getApplicationCacheSize(str);
    }

    public long getApplicationCodeSize(String str) {
        return this.mApplicationPolicy.getApplicationCodeSize(str);
    }

    public boolean getApplicationComponentState(ComponentName componentName) {
        return this.mApplicationPolicy.getApplicationComponentState(componentName);
    }

    public long getApplicationCpuUsage(String str) {
        return this.mApplicationPolicy.getApplicationCpuUsage(str);
    }

    public long getApplicationDataSize(String str) {
        return this.mApplicationPolicy.getApplicationDataSize(str);
    }

    public boolean getApplicationInstallationEnabled(String str) {
        return this.mApplicationPolicy.getApplicationInstallationEnabled(str);
    }

    public int getApplicationInstallationMode() {
        return this.mApplicationPolicy.getApplicationInstallationMode();
    }

    public long getApplicationMemoryUsage(String str) {
        return this.mApplicationPolicy.getApplicationMemoryUsage(str);
    }

    public String getApplicationName(String str) {
        return this.mApplicationPolicy.getApplicationName(str);
    }

    public List<NetworkStats> getApplicationNetworkStats() {
        return NetworkStats.convertToNewList(this.mApplicationPolicy.getApplicationNetworkStats());
    }

    public int getApplicationNotificationMode() {
        return this.mApplicationPolicy.getApplicationNotificationMode();
    }

    public Bundle getApplicationRestrictions(ComponentName componentName, String str) {
        try {
            return this.mApplicationPolicy.getApplicationRestrictions(componentName, str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getApplicationRestrictions", new Class[]{ComponentName.class, String.class}, 20));
        }
    }

    public boolean getApplicationStateEnabled(String str) {
        return this.mApplicationPolicy.getApplicationStateEnabled(str);
    }

    public String[] getApplicationStateList(boolean z) {
        return this.mApplicationPolicy.getApplicationStateList(z);
    }

    public long getApplicationTotalSize(String str) {
        return this.mApplicationPolicy.getApplicationTotalSize(str);
    }

    public int getApplicationUid(String str) {
        try {
            return this.mApplicationPolicy.getApplicationUid(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getApplicationUid", new Class[]{String.class}, 12));
        }
    }

    public boolean getApplicationUninstallationEnabled(String str) {
        return this.mApplicationPolicy.getApplicationUninstallationEnabled(str);
    }

    public int getApplicationUninstallationMode() {
        return this.mApplicationPolicy.getApplicationUninstallationMode();
    }

    public String getApplicationVersion(String str) {
        return this.mApplicationPolicy.getApplicationVersion(str);
    }

    public int getApplicationVersionCode(String str) {
        return this.mApplicationPolicy.getApplicationVersionCode(str);
    }

    public AppInfoLastUsage[] getAvgNoAppUsagePerMonth() {
        return AppInfoLastUsage.convertToNewArray(this.mApplicationPolicy.getAvgNoAppUsagePerMonth());
    }

    public ComponentName getDefaultApplication(Intent intent) {
        try {
            return this.mApplicationPolicy.getDefaultApplication(intent);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getDefaultApplication", new Class[]{Intent.class}, 15));
        }
    }

    public List<ComponentName> getHomeShortcuts(String str, boolean z) {
        return this.mApplicationPolicy.getHomeShortcuts(str, z);
    }

    public String[] getInstalledApplicationsIDList() {
        return this.mApplicationPolicy.getInstalledApplicationsIDList();
    }

    public List<AppInfo> getMostCpuUsageApps(int i, boolean z) {
        return AppInfo.convertToNewList(this.mApplicationPolicy.getMostCpuUsageApps(i, z));
    }

    public List<AppInfo> getMostDataUsageApps(int i) {
        return AppInfo.convertToNewList(this.mApplicationPolicy.getMostDataUsageApps(i));
    }

    public List<AppInfo> getMostMemoryUsageApps(int i, boolean z) {
        return AppInfo.convertToNewList(this.mApplicationPolicy.getMostMemoryUsageApps(i, z));
    }

    public List<String> getNewAdminActivationAppWhiteList() {
        try {
            return this.mAdvancedRestrictionPolicy.getNewAdminActivationAppWhiteList();
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getNewAdminActivationAppWhiteList", null, 11));
        }
    }

    public List<String> getPackagesFromBatteryOptimizationWhiteList() {
        try {
            return this.mApplicationPolicy.getPackagesFromBatteryOptimizationWhiteList();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getPackagesFromBatteryOptimizationWhiteList", null, 20));
        }
    }

    public List<String> getPackagesFromClearCacheBlackList() {
        return this.mApplicationPolicy.getPackagesFromClearCacheBlackList();
    }

    public List<String> getPackagesFromClearCacheWhiteList() {
        return this.mApplicationPolicy.getPackagesFromClearCacheWhiteList();
    }

    public List<String> getPackagesFromClearDataBlackList() {
        return this.mApplicationPolicy.getPackagesFromClearDataBlackList();
    }

    public List<String> getPackagesFromClearDataWhiteList() {
        return this.mApplicationPolicy.getPackagesFromClearDataWhiteList();
    }

    public List<String> getPackagesFromDisableClipboardBlackList() {
        return this.mApplicationPolicy.getPackagesFromDisableClipboardBlackList();
    }

    public List<String> getPackagesFromDisableClipboardWhiteList() {
        return this.mApplicationPolicy.getPackagesFromDisableClipboardWhiteList();
    }

    public List<String> getPackagesFromDisableUpdateBlackList() {
        return this.mApplicationPolicy.getPackagesFromDisableUpdateBlackList();
    }

    public List<String> getPackagesFromDisableUpdateWhiteList() {
        return this.mApplicationPolicy.getPackagesFromDisableUpdateWhiteList();
    }

    public List<String> getPackagesFromFocusMonitoringList() {
        try {
            return this.mApplicationPolicy.getPackagesFromFocusMonitoringList();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getPackagesFromFocusMonitoringList", null, 15));
        }
    }

    public List<String> getPackagesFromForceStopBlackList() {
        return this.mApplicationPolicy.getPackagesFromForceStopBlackList();
    }

    public List<String> getPackagesFromForceStopWhiteList() {
        return this.mApplicationPolicy.getPackagesFromForceStopWhiteList();
    }

    public List<String> getPackagesFromNotificationBlackList() {
        return this.mApplicationPolicy.getPackagesFromNotificationBlackList();
    }

    public List<String> getPackagesFromNotificationWhiteList() {
        return this.mApplicationPolicy.getPackagesFromNotificationWhiteList();
    }

    public List<String> getPackagesFromPreventStartBlackList() {
        return this.mApplicationPolicy.getPackagesFromPreventStartBlackList();
    }

    public List<String> getPackagesFromWidgetBlackList() {
        return this.mApplicationPolicy.getPackagesFromWidgetBlackList();
    }

    public List<String> getPackagesFromWidgetWhiteList() {
        return this.mApplicationPolicy.getPackagesFromWidgetWhiteList();
    }

    public List<String> getRuntimePermissions(String str, int i) {
        try {
            return this.mApplicationPolicy.getRuntimePermissions(str, i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getRuntimePermissions", new Class[]{String.class, Integer.TYPE}, 19));
        }
    }

    public List<UsbDeviceConfig> getUsbDevicesForDefaultAccess(String str) {
        try {
            return UsbDeviceConfig.convertToNewList(this.mApplicationPolicy.getUsbDevicesForDefaultAccess(str));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getUsbDevicesForDefaultAccess", new Class[]{String.class}, 12));
        }
    }

    public boolean installApplication(String str) {
        try {
            return this.mApplicationPolicy.installApplication(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "installApplication", new Class[]{String.class}, 12));
        }
    }

    public boolean installApplication(String str, boolean z) {
        return this.mApplicationPolicy.installApplication(str, z);
    }

    public boolean isApplicationInstalled(String str) {
        return this.mApplicationPolicy.isApplicationInstalled(str);
    }

    public boolean isApplicationRunning(String str) {
        return this.mApplicationPolicy.isApplicationRunning(str);
    }

    public boolean isNewAdminActivationEnabled(boolean z) {
        try {
            return this.mAdvancedRestrictionPolicy.isNewAdminActivationEnabled(z);
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "isNewAdminActivationEnabled", new Class[]{Boolean.TYPE}, 11));
        }
    }

    public boolean isNewAdminInstallationEnabled(boolean z) {
        try {
            return this.mAdvancedRestrictionPolicy.isNewAdminInstallationEnabled(z);
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "isNewAdminInstallationEnabled", new Class[]{Boolean.TYPE}, 11));
        }
    }

    public boolean preventNewAdminActivation(boolean z) {
        try {
            return this.mAdvancedRestrictionPolicy.preventNewAdminActivation(z);
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "preventNewAdminActivation", new Class[]{Boolean.TYPE}, 11));
        }
    }

    public boolean preventNewAdminInstallation(boolean z) {
        try {
            return this.mAdvancedRestrictionPolicy.preventNewAdminInstallation(z);
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "preventNewAdminInstallation", new Class[]{Boolean.TYPE}, 11));
        }
    }

    public boolean removeAppPackageNameFromBlackList(String str) {
        return this.mApplicationPolicy.removeAppPackageNameFromBlackList(str);
    }

    public boolean removeAppPackageNameFromWhiteList(String str) {
        return this.mApplicationPolicy.removeAppPackageNameFromWhiteList(str);
    }

    public boolean removeAppPermissionFromBlackList(String str) {
        return this.mApplicationPolicy.removeAppPermissionFromBlackList(str);
    }

    public boolean removeAppSignatureFromBlackList(String str) {
        return this.mApplicationPolicy.removeAppSignatureFromBlackList(str);
    }

    public boolean removeAppSignatureFromWhiteList(String str) {
        return this.mApplicationPolicy.removeAppSignatureFromWhiteList(str);
    }

    public boolean removeDefaultApplication(Intent intent, ComponentName componentName) {
        try {
            return this.mApplicationPolicy.removeDefaultApplication(intent, componentName);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "removeDefaultApplication", new Class[]{Intent.class, ComponentName.class}, 15));
        }
    }

    public int removePackageFromBatteryOptimizationWhiteList(AppIdentity appIdentity) {
        try {
            return this.mApplicationPolicy.removePackageFromBatteryOptimizationWhiteList(AppIdentity.convertToOld(appIdentity));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "removePackageFromBatteryOptimizationWhiteList", new Class[]{AppIdentity.class}, 20));
        }
    }

    public boolean removePackagesFromClearCacheBlackList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromClearCacheBlackList(list);
    }

    public boolean removePackagesFromClearCacheWhiteList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromClearCacheWhiteList(list);
    }

    public boolean removePackagesFromClearDataBlackList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromClearDataBlackList(list);
    }

    public boolean removePackagesFromClearDataWhiteList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromClearDataWhiteList(list);
    }

    public boolean removePackagesFromDisableClipboardBlackList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromDisableClipboardBlackList(list);
    }

    public boolean removePackagesFromDisableClipboardWhiteList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromDisableClipboardWhiteList(list);
    }

    public boolean removePackagesFromDisableUpdateBlackList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromDisableUpdateBlackList(list);
    }

    public boolean removePackagesFromDisableUpdateWhiteList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromDisableUpdateWhiteList(list);
    }

    public boolean removePackagesFromFocusMonitoringList(List<String> list) {
        try {
            return this.mApplicationPolicy.removePackagesFromFocusMonitoringList(list);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "removePackagesFromFocusMonitoringList", new Class[]{List.class}, 15));
        }
    }

    public boolean removePackagesFromForceStopBlackList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromForceStopBlackList(list);
    }

    public boolean removePackagesFromForceStopWhiteList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromForceStopWhiteList(list);
    }

    public boolean removePackagesFromNotificationBlackList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromNotificationBlackList(list);
    }

    public boolean removePackagesFromNotificationWhiteList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromNotificationWhiteList(list);
    }

    public boolean removePackagesFromPreventStartBlackList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromPreventStartBlackList(list);
    }

    public boolean removePackagesFromWidgetBlackList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromWidgetBlackList(list);
    }

    public boolean removePackagesFromWidgetWhiteList(List<String> list) {
        return this.mApplicationPolicy.removePackagesFromWidgetWhiteList(list);
    }

    public int setAfWProxy(boolean z, AppIdentity appIdentity, Bundle bundle) {
        try {
            return this.mApplicationPolicy.setAfWProxy(z, AppIdentity.convertToOld(appIdentity), convertToOldBundle(bundle));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "setAfWProxy", new Class[]{Boolean.TYPE, AppIdentity.class, Bundle.class}, 19));
        }
    }

    public boolean setApplicationComponentState(ComponentName componentName, boolean z) {
        return this.mApplicationPolicy.setApplicationComponentState(componentName, z);
    }

    public void setApplicationInstallationDisabled(String str) {
        this.mApplicationPolicy.setApplicationInstallationDisabled(str);
    }

    public void setApplicationInstallationEnabled(String str) {
        this.mApplicationPolicy.setApplicationInstallationEnabled(str);
    }

    public boolean setApplicationInstallationMode(int i) {
        return this.mApplicationPolicy.setApplicationInstallationMode(i);
    }

    public boolean setApplicationNotificationMode(int i) {
        return this.mApplicationPolicy.setApplicationNotificationMode(i);
    }

    public void setApplicationRestrictions(ComponentName componentName, String str, Bundle bundle) {
        try {
            this.mApplicationPolicy.setApplicationRestrictions(componentName, str, bundle);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "setApplicationRestrictions", new Class[]{ComponentName.class, String.class, Bundle.class}, 20));
        }
    }

    public String[] setApplicationStateList(String[] strArr, boolean z) {
        return this.mApplicationPolicy.setApplicationStateList(strArr, z);
    }

    public void setApplicationUninstallationDisabled(String str) {
        this.mApplicationPolicy.setApplicationUninstallationDisabled(str);
    }

    public void setApplicationUninstallationEnabled(String str) {
        this.mApplicationPolicy.setApplicationUninstallationEnabled(str);
    }

    public boolean setApplicationUninstallationMode(int i) {
        return this.mApplicationPolicy.setApplicationUninstallationMode(i);
    }

    public boolean setDefaultApplication(Intent intent, ComponentName componentName) {
        try {
            return this.mApplicationPolicy.setDefaultApplication(intent, componentName);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "setDefaultApplication", new Class[]{Intent.class, ComponentName.class}, 15));
        }
    }

    public boolean setDisableApplication(String str) {
        return this.mApplicationPolicy.setDisableApplication(str);
    }

    public boolean setEnableApplication(String str) {
        return this.mApplicationPolicy.setEnableApplication(str);
    }

    public boolean startApp(String str, String str2) {
        return this.mApplicationPolicy.startApp(str, str2);
    }

    public boolean stopApp(String str) {
        return this.mApplicationPolicy.stopApp(str);
    }

    public boolean uninstallApplication(String str, boolean z) {
        return this.mApplicationPolicy.uninstallApplication(str, z);
    }

    public List<String> uninstallApplications(List<String> list) {
        return this.mApplicationPolicy.uninstallApplications(list);
    }

    public boolean updateApplication(String str) {
        return this.mApplicationPolicy.updateApplication(str);
    }

    public boolean wipeApplicationData(String str) {
        return this.mApplicationPolicy.wipeApplicationData(str);
    }
}
