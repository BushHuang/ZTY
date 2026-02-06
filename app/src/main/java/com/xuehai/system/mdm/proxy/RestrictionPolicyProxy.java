package com.xuehai.system.mdm.proxy;

import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.policies.IRestrictionPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u0000 22\u00020\u0001:\u00012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0005H\u0016J\b\u0010\u0017\u001a\u00020\u0005H\u0016J\b\u0010\u0018\u001a\u00020\u0005H\u0016J\u0010\u0010\u0019\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u001a\u001a\u00020\u0005H\u0016J\b\u0010\u001b\u001a\u00020\u0005H\u0016J\b\u0010\u001c\u001a\u00020\u0005H\u0016J\b\u0010\u001d\u001a\u00020\u0005H\u0016J\b\u0010\u001e\u001a\u00020\u0005H\u0016J\b\u0010\u001f\u001a\u00020\u0005H\u0016J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010\"\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010#\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010$\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010%\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010*\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010,\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010-\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010.\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010/\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u00100\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\b\u00101\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/xuehai/system/mdm/proxy/RestrictionPolicyProxy;", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "policy", "(Lcom/xuehai/system/common/policies/IRestrictionPolicy;)V", "allowFactoryReset", "", "allow", "allowFirmwareRecovery", "allowHardwareKey", "keyCode", "", "allowMultiWindowMode", "allowMultipleUsers", "allowOTAUpgrade", "allowOTG", "enable", "allowSafeMode", "allowStatusBarExpansion", "allowSystemAccountLogin", "allowUsbHostStorage", "disableHomeLongFunc", "", "isClipboardAllowed", "isFactoryResetAllowed", "isFirmwareRecoveryAllowed", "isHardwareKeyAllow", "isMTPAvailable", "isSafeModeAllow", "isSdCardEnabled", "isUsbDebuggingEnabled", "isUsbHostStorageAllowed", "multipleUsersAllowed", "setBixbyShortcutEnabled", "setCameraShortcutEnabled", "setCameraState", "setClipboardEnabled", "setEmergencyCallOnly", "setFileShareDisabled", "setInputMethod", "inputMethodClassName", "", "setKanbanEnabled", "setLockScreenItems", "setMTPEnabled", "setScreenCapture", "setSdCardState", "setSwitchInputMethodEnabled", "setTaskBarEnabled", "setUsbDebuggingEnabled", "wipeRecentTasks", "Companion", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RestrictionPolicyProxy implements IRestrictionPolicy {
    public static final String TAG = "MDM:RestrictionPolicy";
    private final IRestrictionPolicy policy;

    public RestrictionPolicyProxy(IRestrictionPolicy iRestrictionPolicy) {
        Intrinsics.checkNotNullParameter(iRestrictionPolicy, "policy");
        this.policy = iRestrictionPolicy;
    }

    @Override
    public boolean allowFactoryReset(boolean allow) {
        String str = allow ? "开启恢复出厂设置" : "禁用恢复出厂设置";
        try {
            boolean zAllowFactoryReset = this.policy.allowFactoryReset(allow);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(zAllowFactoryReset));
            return zAllowFactoryReset;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean allowFirmwareRecovery(boolean allow) {
        String str = allow ? "开启固件升级" : "禁用固件升级";
        try {
            boolean zAllowFirmwareRecovery = this.policy.allowFirmwareRecovery(allow);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(zAllowFirmwareRecovery));
            return zAllowFirmwareRecovery;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean allowHardwareKey(int keyCode, boolean allow) {
        String str = allow ? "开启按键" : "禁用按键";
        try {
            boolean zAllowHardwareKey = this.policy.allowHardwareKey(keyCode, allow);
            MdmLog.log("MDM:RestrictionPolicy", str + ':' + keyCode, Boolean.valueOf(zAllowHardwareKey));
            return zAllowHardwareKey;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + ':' + keyCode + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean allowMultiWindowMode(boolean allow) {
        String str = allow ? "开启多窗口" : "禁用多窗口";
        try {
            boolean zAllowMultiWindowMode = this.policy.allowMultiWindowMode(allow);
            MdmLog.log("MDM:RestrictionPolicy", str + ':', Boolean.valueOf(zAllowMultiWindowMode));
            return zAllowMultiWindowMode;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + ": 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean allowMultipleUsers(boolean allow) {
        String str = allow ? "启用多用户" : "禁用多用户";
        try {
            boolean zAllowMultipleUsers = this.policy.allowMultipleUsers(allow);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(zAllowMultipleUsers));
            return zAllowMultipleUsers;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean allowOTAUpgrade(boolean allow) {
        String str = allow ? "开启OTA升级" : "禁用OTA升级";
        try {
            boolean zAllowOTAUpgrade = this.policy.allowOTAUpgrade(allow);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(zAllowOTAUpgrade));
            return zAllowOTAUpgrade;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean allowOTG(boolean enable) {
        String str = enable ? "启用 usb OTG" : "禁用 usb OTG";
        try {
            boolean zAllowOTG = this.policy.allowOTG(enable);
            MdmLog.i("MDM:RestrictionPolicy", str + ": " + zAllowOTG);
            return zAllowOTG;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean allowSafeMode(boolean allow) {
        String str = allow ? "开启安全模式" : "禁用安全模式";
        try {
            boolean zAllowSafeMode = this.policy.allowSafeMode(allow);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(zAllowSafeMode));
            return zAllowSafeMode;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean allowStatusBarExpansion(boolean allow) {
        String str = allow ? "允许下拉菜单展开" : "禁止下拉菜单展开";
        try {
            boolean zAllowStatusBarExpansion = this.policy.allowStatusBarExpansion(allow);
            MdmLog.log("MDM:RestrictionPolicy", str + ": " + allow, Boolean.valueOf(zAllowStatusBarExpansion));
            return zAllowStatusBarExpansion;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean allowSystemAccountLogin(boolean allow) {
        String str = allow ? "启用 系统登录账号" : "禁用 系统登录账号";
        try {
            boolean zAllowSystemAccountLogin = this.policy.allowSystemAccountLogin(allow);
            MdmLog.i("MDM:RestrictionPolicy", str + ": " + zAllowSystemAccountLogin);
            return zAllowSystemAccountLogin;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean allowUsbHostStorage(boolean allow) {
        String str = allow ? "允许UsbHostStorage" : "禁止UsbHostStorage";
        try {
            boolean zAllowUsbHostStorage = this.policy.allowUsbHostStorage(allow);
            MdmLog.log("MDM:RestrictionPolicy", str + ": " + allow, Boolean.valueOf(zAllowUsbHostStorage));
            return zAllowUsbHostStorage;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public void disableHomeLongFunc() {
        try {
            this.policy.disableHomeLongFunc();
            MdmLog.log$default("MDM:RestrictionPolicy", "禁用home键长按", null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "禁用home键长按 发生异常！", th);
        }
    }

    @Override
    public boolean isClipboardAllowed() {
        try {
            boolean zIsClipboardAllowed = this.policy.isClipboardAllowed();
            MdmLog.log$default("MDM:RestrictionPolicy", "剪贴板是否可用 : " + zIsClipboardAllowed, null, 4, null);
            return zIsClipboardAllowed;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "剪贴板是否可用 发生异常!", th);
            return true;
        }
    }

    @Override
    public boolean isFactoryResetAllowed() {
        try {
            boolean zIsFactoryResetAllowed = this.policy.isFactoryResetAllowed();
            MdmLog.i("MDM:RestrictionPolicy", "获取恢复出厂设置状态: " + zIsFactoryResetAllowed);
            return zIsFactoryResetAllowed;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "获取恢复出厂设置状态 发生异常!", th);
            return true;
        }
    }

    @Override
    public boolean isFirmwareRecoveryAllowed() {
        try {
            boolean zIsFirmwareRecoveryAllowed = this.policy.isFirmwareRecoveryAllowed();
            MdmLog.i("MDM:RestrictionPolicy", "获取固件升级状态: " + zIsFirmwareRecoveryAllowed);
            return zIsFirmwareRecoveryAllowed;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "获取固件升级状态 发生异常!", th);
            return true;
        }
    }

    @Override
    public boolean isHardwareKeyAllow(int keyCode) {
        try {
            boolean zIsHardwareKeyAllow = this.policy.isHardwareKeyAllow(keyCode);
            MdmLog.log$default("MDM:RestrictionPolicy", "按键状态:" + keyCode + ' ' + zIsHardwareKeyAllow, null, 4, null);
            return zIsHardwareKeyAllow;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "按键状态:" + keyCode + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean isMTPAvailable() {
        try {
            boolean zIsMTPAvailable = this.policy.isMTPAvailable();
            MdmLog.i("MDM:RestrictionPolicy", "获取usb mtp 状态: " + zIsMTPAvailable);
            return zIsMTPAvailable;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "获取usb mtp 状态 发生异常!", th);
            return true;
        }
    }

    @Override
    public boolean isSafeModeAllow() {
        try {
            boolean zIsSafeModeAllow = this.policy.isSafeModeAllow();
            MdmLog.log$default("MDM:RestrictionPolicy", "查询安全模式是否被启用 " + zIsSafeModeAllow, null, 4, null);
            return zIsSafeModeAllow;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "查询安全模式是否被启用 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean isSdCardEnabled() {
        try {
            boolean zIsSdCardEnabled = this.policy.isSdCardEnabled();
            MdmLog.i("MDM:RestrictionPolicy", "获取外置sdcard启用状态: " + zIsSdCardEnabled);
            return zIsSdCardEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "获取外置sdcard启用状态 发生异常!", th);
            return true;
        }
    }

    @Override
    public boolean isUsbDebuggingEnabled() {
        try {
            boolean zIsUsbDebuggingEnabled = this.policy.isUsbDebuggingEnabled();
            MdmLog.i("MDM:RestrictionPolicy", "获取usb debug状态: " + zIsUsbDebuggingEnabled);
            return zIsUsbDebuggingEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "获取usb debug状态 发生异常!", th);
            return true;
        }
    }

    @Override
    public boolean isUsbHostStorageAllowed() {
        try {
            boolean zIsUsbHostStorageAllowed = this.policy.isUsbHostStorageAllowed();
            MdmLog.i("MDM:RestrictionPolicy", "获取USB HOST状态: " + zIsUsbHostStorageAllowed);
            return zIsUsbHostStorageAllowed;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "获取USB HOST状态 发生异常!", th);
            return true;
        }
    }

    @Override
    public boolean multipleUsersAllowed() {
        try {
            boolean zMultipleUsersAllowed = this.policy.multipleUsersAllowed();
            MdmLog.i("MDM:RestrictionPolicy", "获取设备多用户启用状态: " + zMultipleUsersAllowed);
            return zMultipleUsersAllowed;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "获取设备多用户启用状态 发生异常!", th);
            return true;
        }
    }

    @Override
    public boolean setBixbyShortcutEnabled(boolean enable) {
        String str = enable ? "长按关机键默认为bixby" : "长按关机键默认为关机";
        try {
            boolean bixbyShortcutEnabled = this.policy.setBixbyShortcutEnabled(enable);
            MdmLog.i("MDM:RestrictionPolicy", str + ": " + bixbyShortcutEnabled);
            return bixbyShortcutEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + ": 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setCameraShortcutEnabled(boolean enable) {
        String str = enable ? "启用连按两次关机键打开相机" : "禁用连按两次关机键打开相机";
        try {
            boolean cameraShortcutEnabled = this.policy.setCameraShortcutEnabled(enable);
            MdmLog.i("MDM:RestrictionPolicy", str + ": " + cameraShortcutEnabled);
            return cameraShortcutEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + ": 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setCameraState(boolean enable) {
        String str = enable ? "启用相机" : "禁用相机";
        try {
            boolean cameraState = this.policy.setCameraState(enable);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(cameraState));
            return cameraState;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean setClipboardEnabled(boolean enable) {
        String str = enable ? "启用剪贴板" : "禁用剪贴板";
        try {
            boolean clipboardEnabled = this.policy.setClipboardEnabled(enable);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(clipboardEnabled));
            return clipboardEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean setEmergencyCallOnly(boolean enable) {
        String str = enable ? "开启仅允许拨打紧急电话" : "关闭仅允许拨打紧急电话";
        try {
            boolean emergencyCallOnly = this.policy.setEmergencyCallOnly(enable);
            MdmLog.log("MDM:RestrictionPolicy", str + ": " + enable, Boolean.valueOf(emergencyCallOnly));
            return emergencyCallOnly;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setFileShareDisabled(boolean enable) {
        return this.policy.setFileShareDisabled(enable);
    }

    @Override
    public boolean setInputMethod(String inputMethodClassName) {
        Intrinsics.checkNotNullParameter(inputMethodClassName, "inputMethodClassName");
        try {
            boolean inputMethod = this.policy.setInputMethod(inputMethodClassName);
            MdmLog.log("MDM:RestrictionPolicy", "设置默认输入法:" + inputMethodClassName, Boolean.valueOf(inputMethod));
            return inputMethod;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "设置默认输入法:" + inputMethodClassName + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean setKanbanEnabled(boolean enable) {
        String str = enable ? "启用每日看板功能" : "禁用每日看板功能";
        try {
            boolean kanbanEnabled = this.policy.setKanbanEnabled(enable);
            MdmLog.i("MDM:RestrictionPolicy", str + ": " + kanbanEnabled);
            return kanbanEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + ": 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setLockScreenItems(boolean enable) {
        String str = enable ? "启用显示锁屏界面功能" : "禁用显示锁屏界面功能";
        try {
            boolean lockScreenItems = this.policy.setLockScreenItems(enable);
            MdmLog.i("MDM:RestrictionPolicy", str + ": " + lockScreenItems);
            return lockScreenItems;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + ": 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setMTPEnabled(boolean enable) {
        String str = enable ? "开启usb mtp" : "禁用usb mtp";
        try {
            boolean mTPEnabled = this.policy.setMTPEnabled(enable);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(mTPEnabled));
            return mTPEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean setScreenCapture(boolean enable) {
        String str = enable ? "启用截屏" : "禁用截屏";
        try {
            boolean screenCapture = this.policy.setScreenCapture(enable);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(screenCapture));
            return screenCapture;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean setSdCardState(boolean enable) {
        String str = enable ? "开启外置SDCard" : "禁用外置SDCard";
        try {
            boolean sdCardState = this.policy.setSdCardState(enable);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(sdCardState));
            return sdCardState;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean setSwitchInputMethodEnabled(boolean enable) {
        String str = enable ? "启用底部导航栏输入法切换按钮" : "禁用底部导航栏输入法切换按钮";
        try {
            boolean switchInputMethodEnabled = this.policy.setSwitchInputMethodEnabled(enable);
            MdmLog.i("MDM:RestrictionPolicy", str + ": " + switchInputMethodEnabled);
            return switchInputMethodEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + ": 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setTaskBarEnabled(boolean enable) {
        String str = enable ? "启用导航栏上的推荐应用/窗口" : "禁用导航栏上的推荐应用/窗口";
        try {
            boolean taskBarEnabled = this.policy.setTaskBarEnabled(enable);
            MdmLog.i("MDM:RestrictionPolicy", str + ": " + taskBarEnabled);
            return taskBarEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + ": 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setUsbDebuggingEnabled(boolean enable) {
        String str = enable ? "usb debug enable" : "usb debug disable";
        try {
            boolean usbDebuggingEnabled = this.policy.setUsbDebuggingEnabled(enable);
            MdmLog.log("MDM:RestrictionPolicy", str, Boolean.valueOf(usbDebuggingEnabled));
            return usbDebuggingEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean wipeRecentTasks() {
        try {
            boolean zWipeRecentTasks = this.policy.wipeRecentTasks();
            MdmLog.log("MDM:RestrictionPolicy", "清空任务栈", Boolean.valueOf(zWipeRecentTasks));
            return zWipeRecentTasks;
        } catch (Throwable th) {
            MdmLog.e("MDM:RestrictionPolicy", "清空任务栈 发生异常!", th);
            return false;
        }
    }
}
