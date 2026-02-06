package com.xuehai.mdm.config;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016J\u0018\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016¨\u0006\u0011"}, d2 = {"Lcom/xuehai/mdm/config/SamsungV3Configuration;", "Lcom/xuehai/mdm/config/SamsungConfiguration;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getAppBlackList", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "getAppWhiteList", "getForbiddenComponentNames", "", "Landroid/content/ComponentName;", "isOsVersionValid", "", "isSDKSupport", "Companion", "mdm_config_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SamsungV3Configuration extends SamsungConfiguration {
    private static final Set<String> osVersions = SetsKt.setOf((Object[]) new String[]{"PPR1.180610.011.P200ZCU1ASE2", "PPR1.180610.011.P200ZCU1ASG1", "PPR1.180610.011.P200ZCU2ASH2", "PPR1.180610.011.P200ZCU2ASJ3_B2BF"});

    public SamsungV3Configuration(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i("DeviceConfiguration", "加载Samsung knox v3配置");
    }

    @Override
    public HashSet<String> getAppBlackList() throws Resources.NotFoundException {
        HashSet<String> appBlackList = super.getAppBlackList();
        HashSet<String> hashSet = appBlackList;
        String[] stringArray = getContext().getResources().getStringArray(R.array.samsung_un_keep_app_p615);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…samsung_un_keep_app_p615)");
        CollectionsKt.addAll(hashSet, stringArray);
        if (Intrinsics.areEqual(Build.MODEL, "SM-P620")) {
            String[] stringArray2 = getContext().getResources().getStringArray(R.array.samsung_un_keep_app_p620);
            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…samsung_un_keep_app_p620)");
            CollectionsKt.addAll(hashSet, stringArray2);
        }
        return appBlackList;
    }

    @Override
    public HashSet<String> getAppWhiteList() throws Resources.NotFoundException {
        HashSet<String> appWhiteList = super.getAppWhiteList();
        HashSet<String> hashSet = appWhiteList;
        String[] stringArray = getContext().getResources().getStringArray(R.array.samsung_keep_app_p615);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ay.samsung_keep_app_p615)");
        CollectionsKt.addAll(hashSet, stringArray);
        if (Intrinsics.areEqual(Build.MODEL, "SM-P620")) {
            String[] stringArray2 = getContext().getResources().getStringArray(R.array.samsung_keep_app_p620);
            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ay.samsung_keep_app_p620)");
            CollectionsKt.addAll(hashSet, stringArray2);
        }
        return appWhiteList;
    }

    @Override
    public List<ComponentName> getForbiddenComponentNames() {
        List<ComponentName> forbiddenComponentNames = super.getForbiddenComponentNames();
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.Settings$AppNotificationSettingsActivity"));
        forbiddenComponentNames.add(new ComponentName("android", "com.android.internal.app.PlatLogoActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.Settings$ManageApplicationsActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.Settings$UsbDetailsActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.Settings$AppDrawOverlaySettingsActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.applications.InstalledAppDetails"));
        forbiddenComponentNames.add(new ComponentName("com.google.android.gms", "com.google.android.gms.feedback.FeedbackActivity"));
        forbiddenComponentNames.add(new ComponentName("com.sec.android.inputmethod", "com.sec.android.inputmethod.SamsungKeypadSettings"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.Settings$WifiAdvancedActivity"));
        forbiddenComponentNames.add(new ComponentName("com.samsung.accessibility", "com.samsung.accessibility.Activities$AccessibilitySettings"));
        if (Intrinsics.areEqual(Build.MODEL, "SM-P200")) {
            forbiddenComponentNames.add(new ComponentName("com.google.android.gms", "com.google.android.gms.backup.component.BackupSettingsActivity"));
            forbiddenComponentNames.add(new ComponentName("com.samsung.android.packageinstaller", "com.android.packageinstaller.CMApplicationListActivity"));
            forbiddenComponentNames.add(new ComponentName("com.samsung.android.mobileservice", "com.samsung.android.samsungaccount.authentication.ui.base.WebContentView"));
            forbiddenComponentNames.add(new ComponentName("com.samsung.android.mobileservice", "com.samsung.android.samsungaccount.setting.ui.SettingWebView"));
            forbiddenComponentNames.add(new ComponentName("com.samsung.android.mobileservice", "com.samsung.android.samsungaccount.setting.ui.main.SettingMainPreference"));
        } else {
            forbiddenComponentNames.add(new ComponentName("com.google.android.captiveportallogin", "com.android.captiveportallogin.CaptivePortalLoginActivity"));
            forbiddenComponentNames.add(new ComponentName("com.sec.android.app.launcher", "com.sec.android.app.launcher.activities.LauncherActivity"));
            forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.Settings$FaceWidgetSettingsActivity"));
            forbiddenComponentNames.add(new ComponentName("com.samsung.android.setting.multisound", "com.samsung.android.setting.multisound.MultiSoundSettingsActivity"));
            forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.Settings$UsefulFeatureMainActivity"));
            forbiddenComponentNames.add(new ComponentName("com.android.settings.intelligence", "com.android.settings.intelligence.search.SearchActivity"));
            forbiddenComponentNames.add(new ComponentName("com.samsung.android.permissioncontroller", "com.android.permissioncontroller.role.ui.DefaultAppActivity"));
            forbiddenComponentNames.add(new ComponentName("com.samsung.android.permissioncontroller", "com.android.permissioncontroller.role.ui.DefaultAppListActivity"));
            forbiddenComponentNames.add(new ComponentName("com.samsung.android.honeyboard", "com.samsung.android.honeyboard.settings.common.HoneyBoardSettingsActivity"));
            forbiddenComponentNames.add(new ComponentName("com.osp.app.signin", "com.samsung.android.samsungaccount.setting.ui.main.SettingMainPreference"));
            forbiddenComponentNames.add(new ComponentName("com.osp.app.signin", "com.samsung.android.samsungaccount.setting.ui.SettingWebView"));
            forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.homepage.SettingsHomepageActivity"));
            forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.Settings"));
        }
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.DeviceAdminSettings"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.ActivityPicker"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.LanguageSettings"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.applications.StorageUse"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.applications.ManageApplications"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.RunningServices"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.notification.zen.ZenSuggestionActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.notification.zen.ZenOnboardingActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.SettingsLicenseActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.notification.history.NotificationHistoryActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.MonitoringCertInfoActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.accessibility.AccessibilitySettingsForSetupWizardActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.password.SetupChooseLockGeneric$InternalActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.FontSizeSettingsForSetupWizardActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.password.ScreenLockSuggestionActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.UsageStatsActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.wifi.WifiDialogActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.samsung.android.settings.usefulfeature.ContinuityAddAccountActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.samsung.android.settings.security.SimPinLockSettings"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.password.SetupChooseLockGeneric"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.password.SetupChooseLockGenericForNoneGoogle"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.accounts.AddAccountSettings"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.samsung.android.settings.biometrics.face.FaceEntry"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.Settings$ResetSettingsActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.samsung.android.settings.display.SecScreenSizePreferenceFragment"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.samsung.android.settings.display.SecFontSizePreferenceFragment"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.samsung.android.settings.display.SecFontStylePreferenceFragment"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.ConfirmDeviceCredentialActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.wifi.mobileap.WifiApSettings"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.wifi.mobileap.autohotspot.WifiApAutoHotspotSettings"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.backup.UserBackupSettingsActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.password.SetupChooseLockGeneric$InternalActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.biometrics.BiometricEnrollActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.inputmethod.InputMethodAndSubtypeEnablerActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.samsung.android.settings.wifi.p2p.WifiP2pDummyPickerActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.bluetooth.DevicePickerActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.samsung.android.settings.bluetooth.CheckBluetoothStateActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.android.settings.TetherSettings"));
        forbiddenComponentNames.add(new ComponentName("com.samsung.accessibility", "com.samsung.accessibility.homepage.AccessibilityHomepageActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.systemui", "com.android.systemui.edgelighting.settings.EdgeLightingAppListActivity"));
        forbiddenComponentNames.add(new ComponentName("com.osp.app.signin", "com.osp.app.signin.AccountView"));
        forbiddenComponentNames.add(new ComponentName("com.android.htmlviewer", "com.android.htmlviewer.HTMLViewerActivity"));
        forbiddenComponentNames.add(new ComponentName("com.samsung.safetyinformation", "com.samsung.safetyinformation.SettingsSafetyInformationActivity"));
        forbiddenComponentNames.add(new ComponentName("com.android.settings", "com.samsung.android.settings.deviceinfo.legal.SamsungKnoxPrivacyNotice"));
        forbiddenComponentNames.add(new ComponentName("com.osp.app.signin", "com.samsung.android.samsungaccount.authentication.ui.base.WebContentView"));
        forbiddenComponentNames.add(new ComponentName("com.android.printspooler", "com.android.printspooler.ui.PrintActivity"));
        if (Intrinsics.areEqual(Build.MODEL, "SM-P620")) {
            forbiddenComponentNames.add(new ComponentName("com.samsung.android.app.sharelive", "com.samsung.android.app.sharelive.presentation.main.MainActivity"));
            forbiddenComponentNames.add(new ComponentName("com.samsung.android.bixby.agent", "com.samsung.android.bixby.onboarding.provision.ProvisioningActivity"));
        }
        return forbiddenComponentNames;
    }

    @Override
    public boolean isOsVersionValid() {
        return true;
    }

    @Override
    public boolean isSDKSupport() {
        return true;
    }
}
