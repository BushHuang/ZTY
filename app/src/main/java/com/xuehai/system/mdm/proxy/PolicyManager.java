package com.xuehai.system.mdm.proxy;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.config.Permission;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.util.DeviceModelUtil;
import com.xuehai.launcher.common.util.app.ApplicationUtil;
import com.xuehai.mdm.config.DeviceConfiguration;
import com.xuehai.mdm.config.SettingsTopLevelMenu;
import com.xuehai.mdm.config.StatusBarButton;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.mdm.ZTYLoginStatus;
import com.zaze.utils.ZCommand;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u00108\u001a\u00020\u00182\u0006\u00109\u001a\u00020:J\u0014\u0010;\u001a\u00020<2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020?0>J\u0006\u0010@\u001a\u00020<J\u0006\u0010A\u001a\u00020<J\u0006\u0010B\u001a\u00020<J\u0010\u0010C\u001a\u00020<2\b\b\u0002\u0010D\u001a\u00020\u0018J\u0010\u0010E\u001a\u00020<2\b\b\u0002\u0010F\u001a\u00020\u0018J\b\u0010G\u001a\u00020<H\u0002J\u0006\u0010H\u001a\u00020<R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0002\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0002\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u00020!8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0002\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001c\u0010)\u001a\u00020*8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u0002\u001a\u0004\b,\u0010-R\u001c\u0010.\u001a\u00020/8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b0\u0010\u0002\u001a\u0004\b1\u00102R\u001c\u00103\u001a\u0002048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b5\u0010\u0002\u001a\u0004\b6\u00107¨\u0006I"}, d2 = {"Lcom/xuehai/system/mdm/proxy/PolicyManager;", "", "()V", "applicationPolicyProxy", "Lcom/xuehai/system/mdm/proxy/ApplicationPolicyProxy;", "getApplicationPolicyProxy$annotations", "getApplicationPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/ApplicationPolicyProxy;", "dateTimePolicyProxy", "Lcom/xuehai/system/mdm/proxy/DateTimePolicyProxy;", "getDateTimePolicyProxy$annotations", "getDateTimePolicyProxy", "()Lcom/xuehai/system/mdm/proxy/DateTimePolicyProxy;", "devicePolicyProxy", "Lcom/xuehai/system/mdm/proxy/DevicePolicyProxy;", "getDevicePolicyProxy$annotations", "getDevicePolicyProxy", "()Lcom/xuehai/system/mdm/proxy/DevicePolicyProxy;", "firewallPolicyProxy", "Lcom/xuehai/system/mdm/proxy/FirewallPolicyProxy;", "getFirewallPolicyProxy$annotations", "getFirewallPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/FirewallPolicyProxy;", "<set-?>", "", "isLocked", "()Z", "licensePolicyProxy", "Lcom/xuehai/system/mdm/proxy/LicensePolicyProxy;", "getLicensePolicyProxy$annotations", "getLicensePolicyProxy", "()Lcom/xuehai/system/mdm/proxy/LicensePolicyProxy;", "locationPolicyProxy", "Lcom/xuehai/system/mdm/proxy/LocationPolicyProxy;", "getLocationPolicyProxy$annotations", "getLocationPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/LocationPolicyProxy;", "plugins", "Lcom/xuehai/system/mdm/proxy/PolicyPlugins;", "getPlugins", "()Lcom/xuehai/system/mdm/proxy/PolicyPlugins;", "restrictionPolicyProxy", "Lcom/xuehai/system/mdm/proxy/RestrictionPolicyProxy;", "getRestrictionPolicyProxy$annotations", "getRestrictionPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/RestrictionPolicyProxy;", "settingPolicyProxy", "Lcom/xuehai/system/mdm/proxy/SettingPolicyProxy;", "getSettingPolicyProxy$annotations", "getSettingPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/SettingPolicyProxy;", "wifiPolicyProxy", "Lcom/xuehai/system/mdm/proxy/WifiPolicyProxy;", "getWifiPolicyProxy$annotations", "getWifiPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/WifiPolicyProxy;", "checkMdmPermission", "context", "Landroid/content/Context;", "disableApps", "", "installedApps", "", "", "disableSetting", "doFixedRules", "enableSettingForUsagePermission", "initComponents", "enable", "lock", "ignoreBusiness", "setDefaultApplication", "unLock", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PolicyManager {
    public static final PolicyManager INSTANCE = new PolicyManager();
    private static final ApplicationPolicyProxy applicationPolicyProxy;
    private static final DateTimePolicyProxy dateTimePolicyProxy;
    private static final DevicePolicyProxy devicePolicyProxy;
    private static final FirewallPolicyProxy firewallPolicyProxy;
    private static boolean isLocked;
    private static final LicensePolicyProxy licensePolicyProxy;
    private static final LocationPolicyProxy locationPolicyProxy;
    private static final PolicyPlugins plugins;
    private static final RestrictionPolicyProxy restrictionPolicyProxy;
    private static final SettingPolicyProxy settingPolicyProxy;
    private static final WifiPolicyProxy wifiPolicyProxy;

    static {
        PolicyPlugins policyPlugins = new PolicyPlugins(BaseApplication.INSTANCE.getInstance().getDeviceProtectedStorageContext());
        plugins = policyPlugins;
        applicationPolicyProxy = policyPlugins.getApplicationPolicyProxy();
        dateTimePolicyProxy = plugins.getDateTimePolicyProxy();
        devicePolicyProxy = plugins.getDevicePolicyProxy();
        firewallPolicyProxy = plugins.getFirewallPolicyProxy();
        licensePolicyProxy = plugins.getLicensePolicyProxy();
        locationPolicyProxy = plugins.getLocationPolicyProxy();
        restrictionPolicyProxy = plugins.getRestrictionPolicyProxy();
        settingPolicyProxy = plugins.getSettingPolicyProxy();
        wifiPolicyProxy = plugins.getWifiPolicyProxy();
    }

    private PolicyManager() {
    }

    public static final ApplicationPolicyProxy getApplicationPolicyProxy() {
        return applicationPolicyProxy;
    }

    @JvmStatic
    public static void getApplicationPolicyProxy$annotations() {
    }

    public static final DateTimePolicyProxy getDateTimePolicyProxy() {
        return dateTimePolicyProxy;
    }

    @JvmStatic
    public static void getDateTimePolicyProxy$annotations() {
    }

    public static final DevicePolicyProxy getDevicePolicyProxy() {
        return devicePolicyProxy;
    }

    @JvmStatic
    public static void getDevicePolicyProxy$annotations() {
    }

    public static final FirewallPolicyProxy getFirewallPolicyProxy() {
        return firewallPolicyProxy;
    }

    @JvmStatic
    public static void getFirewallPolicyProxy$annotations() {
    }

    public static final LicensePolicyProxy getLicensePolicyProxy() {
        return licensePolicyProxy;
    }

    @JvmStatic
    public static void getLicensePolicyProxy$annotations() {
    }

    public static final LocationPolicyProxy getLocationPolicyProxy() {
        return locationPolicyProxy;
    }

    @JvmStatic
    public static void getLocationPolicyProxy$annotations() {
    }

    public static final RestrictionPolicyProxy getRestrictionPolicyProxy() {
        return restrictionPolicyProxy;
    }

    @JvmStatic
    public static void getRestrictionPolicyProxy$annotations() {
    }

    public static final SettingPolicyProxy getSettingPolicyProxy() {
        return settingPolicyProxy;
    }

    @JvmStatic
    public static void getSettingPolicyProxy$annotations() {
    }

    public static final WifiPolicyProxy getWifiPolicyProxy() {
        return wifiPolicyProxy;
    }

    @JvmStatic
    public static void getWifiPolicyProxy$annotations() {
    }

    public static void initComponents$default(PolicyManager policyManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        policyManager.initComponents(z);
    }

    public static void lock$default(PolicyManager policyManager, boolean z, int i, Object obj) throws Resources.NotFoundException {
        if ((i & 1) != 0) {
            z = false;
        }
        policyManager.lock(z);
    }

    private final void setDefaultApplication() {
        if (ClientConfig.INSTANCE.isStudentClient()) {
            applicationPolicyProxy.setDefaultLauncher(ClientConfig.INSTANCE.getPackageName(), ClientConfig.INSTANCE.getLauncherActivity());
            applicationPolicyProxy.setDefaultDeviceAssistance(ClientConfig.INSTANCE.getPackageName(), ClientConfig.INSTANCE.getLauncherActivity());
            restrictionPolicyProxy.disableHomeLongFunc();
        }
    }

    public final boolean checkMdmPermission(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!plugins.getDeviceSupportCompat().isSamsungV3Support()) {
            return true;
        }
        boolean zHasRuntimePermissions = plugins.getApplicationPolicyProxy().hasRuntimePermissions("com.samsung.android.knox.permission.KNOX_APP_MGMT");
        MyLog.i("[MDM]", "checkUserPermission KNOX_APP_MGMT: " + zHasRuntimePermissions);
        return zHasRuntimePermissions;
    }

    public final void disableApps(Set<String> installedApps) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(installedApps, "installedApps");
        DeviceConfiguration deviceConfiguration = plugins.getDeviceSupportCompat().getDeviceConfiguration();
        HashSet<String> appWhiteList = deviceConfiguration.getAppWhiteList();
        HashSet<String> appBlackList = deviceConfiguration.getAppBlackList();
        ArrayList arrayList = new ArrayList();
        for (Object obj : installedApps) {
            String str = (String) obj;
            if (appBlackList.contains(str) && !appWhiteList.contains(str)) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            applicationPolicyProxy.setDisableApplication((String) it.next());
        }
    }

    public final void disableSetting() {
        MyLog.i("SDK[MDM]", "设置管控，禁用设置");
        restrictionPolicyProxy.allowSystemAccountLogin(false);
        settingPolicyProxy.allowSearchSettingsIndex(false);
        settingPolicyProxy.setSettingsHiddenState(true);
        if (!DeviceModelUtil.isHuaweiHEMDevice()) {
            settingPolicyProxy.setSettingDisabled();
        }
        settingPolicyProxy.controlSettingsTopLevelMenu(SettingsTopLevelMenu.INSTANCE.getTopLevelKeyList());
        restrictionPolicyProxy.setLockScreenItems(false);
        restrictionPolicyProxy.setKanbanEnabled(false);
        restrictionPolicyProxy.setCameraShortcutEnabled(false);
        restrictionPolicyProxy.setBixbyShortcutEnabled(false);
        restrictionPolicyProxy.setSwitchInputMethodEnabled(false);
        restrictionPolicyProxy.setTaskBarEnabled(false);
    }

    public final void doFixedRules() {
        setDefaultApplication();
        if (ClientConfig.INSTANCE.isStudentClient()) {
            devicePolicyProxy.setDexDisable(true);
            restrictionPolicyProxy.allowUsbHostStorage(false);
            if (DeviceModelUtil.isHuaweiHEMDevice()) {
                applicationPolicyProxy.addRuntimePermissionFixAppList(CollectionsKt.arrayListOf("com.xh.zhitongyunstu"));
                applicationPolicyProxy.addRuntimePermissionFixAppList(CollectionsKt.arrayListOf("com.xuehai.settings"));
                applicationPolicyProxy.addRuntimePermissionFixAppList(CollectionsKt.arrayListOf("com.zhitongyun.wifimanager"));
            }
        } else if (ClientConfig.INSTANCE.isTeacherClient() && !restrictionPolicyProxy.isUsbHostStorageAllowed()) {
            restrictionPolicyProxy.allowUsbHostStorage(true);
        }
        devicePolicyProxy.controlShowDeprecatedTarget(false);
    }

    public final void enableSettingForUsagePermission() {
        MyLog.i("SDK[MDM]", "设置管控，启用设置");
        restrictionPolicyProxy.allowSystemAccountLogin(true);
        settingPolicyProxy.allowSearchSettingsIndex(true);
        settingPolicyProxy.setSettingsHiddenState(true);
        settingPolicyProxy.controlSettingsTopLevelMenu(SettingsTopLevelMenu.INSTANCE.getTopLevelKeyList());
        settingPolicyProxy.setSettingEnabled();
    }

    public final PolicyPlugins getPlugins() {
        return plugins;
    }

    public final void initComponents(boolean enable) {
        StringBuilder sb = new StringBuilder();
        sb.append("批量");
        sb.append(enable ? "启用" : "禁用");
        sb.append("组件");
        MyLog.i("SDK[MDM]", sb.toString());
        Iterator<T> it = plugins.getDeviceSupportCompat().getDeviceConfiguration().getForbiddenComponentNames().iterator();
        while (it.hasNext()) {
            applicationPolicyProxy.setApplicationComponentState((ComponentName) it.next(), enable);
        }
    }

    public final boolean isLocked() {
        return isLocked;
    }

    public final void lock(boolean ignoreBusiness) throws Resources.NotFoundException {
        MyLog.i("SDK[MDM]", "lock(" + ignoreBusiness + ") >>> isLocked = " + isLocked);
        applicationPolicyProxy.addPackageToBatteryOptimizationWhiteList(ClientConfig.INSTANCE.getPackageName());
        if (ClientConfig.INSTANCE.isTeacherClient()) {
            isLocked = true;
            return;
        }
        if (DeviceModelUtil.isHuaweiHEMDevice()) {
            locationPolicyProxy.allowLocationService(true);
        }
        locationPolicyProxy.startGPS(true);
        devicePolicyProxy.setAdminRemovable(false);
        boolean zIsDebugMode = Permission.isDebugMode();
        MyLog.i("SDK[MDM]", "执行管控策略 isDebug = " + zIsDebugMode);
        restrictionPolicyProxy.allowFirmwareRecovery(zIsDebugMode);
        restrictionPolicyProxy.allowFactoryReset(zIsDebugMode);
        restrictionPolicyProxy.setUsbDebuggingEnabled(zIsDebugMode);
        restrictionPolicyProxy.setMTPEnabled(zIsDebugMode);
        restrictionPolicyProxy.setSdCardState(zIsDebugMode);
        restrictionPolicyProxy.allowMultiWindowMode(false);
        restrictionPolicyProxy.allowOTG(zIsDebugMode);
        settingPolicyProxy.setDevelopHiddenState(!zIsDebugMode);
        restrictionPolicyProxy.setClipboardEnabled(false);
        restrictionPolicyProxy.allowMultiWindowMode(false);
        doFixedRules();
        restrictionPolicyProxy.allowMultipleUsers(false);
        restrictionPolicyProxy.allowSafeMode(false);
        disableSetting();
        MdmLog.i("SDK[MDM]", "隐藏下拉菜单隐藏项: " + settingPolicyProxy.setDropMenuHiddenState(true));
        MdmLog.i("SDK[MDM]", "隐藏状态栏按钮: " + settingPolicyProxy.setStatusBarButtonState(StatusBarButton.INSTANCE.getStatusBarButton()));
        MdmLog.i("SDK[MDM]", "添加第三方桌面豁免名单: " + applicationPolicyProxy.addIgnoreFrequentRelaunchAppList(CollectionsKt.arrayListOf("com.xuehai.launcher")));
        dateTimePolicyProxy.setAutomaticTime(true);
        restrictionPolicyProxy.setEmergencyCallOnly(true);
        devicePolicyProxy.setDexDisable(true);
        restrictionPolicyProxy.allowUsbHostStorage(false);
        if (DeviceModelUtil.isHuaweiHEMDevice()) {
            settingPolicyProxy.turnOnConnectionAlwaysOn(true);
        } else {
            initComponents$default(this, false, 1, null);
        }
        if (!ignoreBusiness) {
            restrictionPolicyProxy.setScreenCapture(false);
            restrictionPolicyProxy.allowOTAUpgrade(false);
            if (!ZTYLoginStatus.INSTANCE.isZTYLogin()) {
                List installedApplications$default = ApplicationUtil.getInstalledApplications$default(ApplicationUtil.INSTANCE, 0, 1, null);
                ArrayList arrayList = new ArrayList();
                for (Object obj : installedApplications$default) {
                    if (((ApplicationInfo) obj).enabled) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList3.add(((ApplicationInfo) it.next()).packageName);
                }
                disableApps(CollectionsKt.toSet(arrayList3));
            }
            ZCommand.execRootCmd("iptables -F");
            firewallPolicyProxy.cleanAppRules();
            firewallPolicyProxy.setIptablesOption(false);
        }
        isLocked = true;
    }

    public final void unLock() {
        isLocked = false;
        MdmLog.i("SDK[MDM]", "开启刷机: " + restrictionPolicyProxy.allowFirmwareRecovery(true));
        MdmLog.i("SDK[MDM]", "开启双清: " + restrictionPolicyProxy.allowFactoryReset(true));
        MdmLog.i("SDK[MDM]", "允许使用sd卡: " + restrictionPolicyProxy.setSdCardState(true));
        MdmLog.i("SDK[MDM]", "usb MTP可用: " + restrictionPolicyProxy.setMTPEnabled(true));
        MdmLog.i("SDK[MDM]", "usb OTG可用: " + restrictionPolicyProxy.allowOTG(true));
        MdmLog.i("SDK[MDM]", "usb 调试可用: " + restrictionPolicyProxy.setUsbDebuggingEnabled(true));
        MdmLog.i("SDK[MDM]", "usb host可用: " + restrictionPolicyProxy.allowUsbHostStorage(true));
        MdmLog.i("SDK[MDM]", "启用设置: " + settingPolicyProxy.setSettingEnabled());
        MdmLog.i("SDK[MDM]", "显示设置隐藏项: " + settingPolicyProxy.setSettingsHiddenState(false));
        StringBuilder sb = new StringBuilder();
        sb.append("显示设设置中部分条目: ");
        settingPolicyProxy.controlSettingsTopLevelMenu(SettingsTopLevelMenu.INSTANCE.getEnableAllKeys());
        sb.append(Unit.INSTANCE);
        MdmLog.i("SDK[MDM]", sb.toString());
        MdmLog.i("SDK[MDM]", "显示开发者模式: " + settingPolicyProxy.setDevelopHiddenState(false));
        HashMap<String, Boolean> map = new HashMap<>();
        Iterator<Map.Entry<String, Boolean>> it = StatusBarButton.INSTANCE.getStatusBarButton().entrySet().iterator();
        while (it.hasNext()) {
            map.put(it.next().getKey(), true);
        }
        MdmLog.i("SDK[MDM]", "显示状态栏按钮: " + settingPolicyProxy.setStatusBarButtonState(map));
        MdmLog.i("SDK[MDM]", "显示下拉菜单隐藏项: " + settingPolicyProxy.setDropMenuHiddenState(false));
        MdmLog.i("SDK[MDM]", "移除默认桌面: " + applicationPolicyProxy.removeDefaultLauncher(ClientConfig.INSTANCE.getPackageName(), ClientConfig.INSTANCE.getLauncherActivity()));
        MdmLog.i("SDK[MDM]", "清除Root防火墙: " + ZCommand.execRootCmd("iptables -F"));
        MdmLog.i("SDK[MDM]", "清除所有防火墙规则: " + firewallPolicyProxy.cleanAppRules());
        MdmLog.i("SDK[MDM]", "关闭防火墙: " + firewallPolicyProxy.setIptablesOption(false));
        MdmLog.i("SDK[MDM]", "开启安全模式: " + restrictionPolicyProxy.allowSafeMode(true));
        MdmLog.i("SDK[MDM]", "开启OTA推送升级: " + restrictionPolicyProxy.allowOTAUpgrade(true));
        MdmLog.i("SDK[MDM]", "清除锁屏: " + settingPolicyProxy.removeLockScreen());
        MdmLog.i("SDK[MDM]", "开启截屏: " + restrictionPolicyProxy.setScreenCapture(true));
        MdmLog.i("SDK[MDM]", "启用剪贴板: " + restrictionPolicyProxy.setClipboardEnabled(true));
        StringBuilder sb2 = new StringBuilder();
        sb2.append("清空应用卸载规则: ");
        applicationPolicyProxy.clearApplicationUninstallRule();
        sb2.append(Unit.INSTANCE);
        MdmLog.i("SDK[MDM]", sb2.toString());
        MdmLog.i("SDK[MDM]", "清空应用禁止关闭权限规则: " + applicationPolicyProxy.clearRuntimePermissionFixAppList());
        MdmLog.i("SDK[MDM]", "清空第三方桌面豁免名单: " + applicationPolicyProxy.clearIgnoreFrequentRelaunchAppList());
        MdmLog.i("SDK[MDM]", "清空可信任列表: " + applicationPolicyProxy.clearTrustListForSystemManger());
        MdmLog.i("SDK[MDM]", "启用显示锁屏界面功能: " + restrictionPolicyProxy.setLockScreenItems(true));
        MdmLog.i("SDK[MDM]", "启用每日看板功能: " + restrictionPolicyProxy.setKanbanEnabled(true));
        MdmLog.i("SDK[MDM]", "启用连按两次关机键打开相机: " + restrictionPolicyProxy.setCameraShortcutEnabled(true));
        MdmLog.i("SDK[MDM]", "长按关机键默认为bixby: " + restrictionPolicyProxy.setBixbyShortcutEnabled(true));
        MdmLog.i("SDK[MDM]", "启用底部导航栏输入法切换按钮: " + restrictionPolicyProxy.setSwitchInputMethodEnabled(true));
        MdmLog.i("SDK[MDM]", "启用导航栏上的推荐应用/窗口: " + restrictionPolicyProxy.setTaskBarEnabled(true));
        MdmLog.i("SDK[MDM]", "重置本地保存的激活状态: " + licensePolicyProxy.resetLocalActivateState());
        MdmLog.i("SDK[MDM]", "允许设置搜索: " + settingPolicyProxy.allowSearchSettingsIndex(true));
        MdmLog.i("SDK[MDM]", "开启多窗口: " + restrictionPolicyProxy.allowMultiWindowMode(true));
        MdmLog.i("SDK[MDM]", "允许系统账号登录: " + restrictionPolicyProxy.allowSystemAccountLogin(true));
        initComponents(true);
        List installedApplications$default = ApplicationUtil.getInstalledApplications$default(ApplicationUtil.INSTANCE, 0, 1, null);
        ArrayList<ApplicationInfo> arrayList = new ArrayList();
        for (Object obj : installedApplications$default) {
            if (!((ApplicationInfo) obj).enabled) {
                arrayList.add(obj);
            }
        }
        for (ApplicationInfo applicationInfo : arrayList) {
            ApplicationPolicyProxy applicationPolicyProxy2 = applicationPolicyProxy;
            String str = applicationInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "it.packageName");
            applicationPolicyProxy2.setEnableApplication(str);
        }
    }
}
