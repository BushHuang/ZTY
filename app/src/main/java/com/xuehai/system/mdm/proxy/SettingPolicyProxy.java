package com.xuehai.system.mdm.proxy;

import com.xuehai.system.common.entities.PowerMode;
import com.xuehai.system.common.entities.RingerMode;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.policies.ISettingPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 B2\u00020\u0001:\u0001BB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0005H\u0016J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aH\u0016J+\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u0002H\u001cH\u0016¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u0005H\u0016J\b\u0010!\u001a\u00020\u0005H\u0016J\b\u0010\"\u001a\u00020\u0005H\u0016J\b\u0010#\u001a\u00020\u000eH\u0016J \u0010$\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010%\u001a\u00020&H\u0016J\u0016\u0010'\u001a\u00020\u00052\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aH\u0016J\b\u0010)\u001a\u00020\u0005H\u0016J\u0016\u0010*\u001a\u00020\u00052\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aH\u0016J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0005H\u0016J\u0010\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0016J\u0010\u0010/\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0016J\u0010\u00100\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0016J\u0010\u00101\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u000202H\u0016J\b\u00103\u001a\u00020\u0005H\u0016J\b\u00104\u001a\u00020\u0005H\u0016J\u0010\u00105\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0016J,\u00106\u001a\u00020\u00052\"\u00107\u001a\u001e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000508j\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0005`9H\u0016J \u0010:\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\b\u0010<\u001a\u00020\u000eH\u0016J\u0018\u0010=\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020\u00072\u0006\u0010?\u001a\u00020\u0007H\u0016J\u0010\u0010@\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010A\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/xuehai/system/mdm/proxy/SettingPolicyProxy;", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "policy", "(Lcom/xuehai/system/common/policies/ISettingPolicy;)V", "adjustStreamVolume", "", "streamType", "", "direction", "flags", "allowLockScreen", "allow", "allowSearchSettingsIndex", "controlAirplaneMode", "", "isOn", "controlEyeMode", "controlRingerMode", "mode", "Lcom/xuehai/system/common/entities/RingerMode;", "controlRotation", "isRotation", "controlSettingsTopLevelMenu", "keyList", "", "getAllowAccessibilityServicesList", "", "getSettingsSystemValue", "T", "key", "defaultObject", "(ILjava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "isBluetoothEnabled", "isEyeComfortTurnedOn", "isSearchSettingsEnable", "openSoftUpdateView", "putSettingsSystemValue", "value", "", "removeAllowAccessibilityServicesList", "pkgList", "removeLockScreen", "setAllowAccessibilityServicesList", "setBluetoothEnable", "enable", "setDevelopHiddenState", "state", "setDropMenuHiddenState", "setMobileDataState", "setPowerSavingMode", "Lcom/xuehai/system/common/entities/PowerMode;", "setSettingDisabled", "setSettingEnabled", "setSettingsHiddenState", "setStatusBarButtonState", "buttonList", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "setStreamVolume", "volume", "startSmartView", "startSoftUpdate", "hour", "minute", "turnOnConnectionAlwaysOn", "turnOnThreeKeyNavigation", "Companion", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SettingPolicyProxy implements ISettingPolicy {
    public static final String TAG = "MDM:SettingPolicy";
    private final ISettingPolicy policy;

    public SettingPolicyProxy(ISettingPolicy iSettingPolicy) {
        Intrinsics.checkNotNullParameter(iSettingPolicy, "policy");
        this.policy = iSettingPolicy;
    }

    @Override
    public boolean adjustStreamVolume(int streamType, int direction, int flags) {
        String str = "强制增大/降低多媒体音量：streamType = " + streamType + "，direction = " + direction;
        try {
            boolean zAdjustStreamVolume = this.policy.adjustStreamVolume(streamType, direction, flags);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(zAdjustStreamVolume));
            return zAdjustStreamVolume;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean allowLockScreen(boolean allow) {
        String str = allow ? "允许锁屏" : "移除锁屏";
        try {
            boolean zAllowLockScreen = this.policy.allowLockScreen(allow);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(zAllowLockScreen));
            return zAllowLockScreen;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean allowSearchSettingsIndex(boolean allow) {
        String str = allow ? "允许设置搜索" : "禁用设置搜索";
        try {
            boolean zAllowSearchSettingsIndex = this.policy.allowSearchSettingsIndex(allow);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(zAllowSearchSettingsIndex));
            return zAllowSearchSettingsIndex;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public void controlAirplaneMode(boolean isOn) {
        try {
            MdmLog.i("MDM:SettingPolicy", "设置飞行模式: " + isOn);
            this.policy.controlAirplaneMode(isOn);
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "设置飞行模式: " + isOn + " 发生异常!", th);
        }
    }

    @Override
    public void controlEyeMode(boolean isOn) {
        try {
            MdmLog.i("MDM:SettingPolicy", "设置护眼模式: " + isOn);
            this.policy.controlEyeMode(isOn);
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "设置护眼模式: " + isOn + " 发生异常!", th);
        }
    }

    @Override
    public void controlRingerMode(RingerMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        try {
            MdmLog.i("MDM:SettingPolicy", "设置声音模式: " + mode);
            this.policy.controlRingerMode(mode);
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "设置声音模式: " + mode + " 发生异常!", th);
        }
    }

    @Override
    public void controlRotation(boolean isRotation) {
        try {
            MdmLog.i("MDM:SettingPolicy", "设置旋转模式: " + isRotation);
            this.policy.controlRotation(isRotation);
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "设置旋转模式: " + isRotation + " 发生异常!", th);
        }
    }

    @Override
    public void controlSettingsTopLevelMenu(String keyList) {
        Intrinsics.checkNotNullParameter(keyList, "keyList");
        try {
            MdmLog.i("MDM:SettingPolicy", "隐藏设置中部分条目");
            this.policy.controlSettingsTopLevelMenu(keyList);
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "隐藏设置中部分条目发生异常!", th);
        }
    }

    @Override
    public List<String> getAllowAccessibilityServicesList() {
        try {
            List<String> allowAccessibilityServicesList = this.policy.getAllowAccessibilityServicesList();
            MdmLog.i("MDM:SettingPolicy", "移除可强制开启辅助（无障碍）服务组件名 ，result = " + allowAccessibilityServicesList);
            return allowAccessibilityServicesList;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "移除可强制开启辅助（无障碍）服务组件名 发生异常！", th);
            return new ArrayList();
        }
    }

    @Override
    public <T> T getSettingsSystemValue(int mode, String key, T defaultObject) {
        Intrinsics.checkNotNullParameter(key, "key");
        String str = "获取系统属性：key = " + key + "，默认值 = " + defaultObject;
        try {
            T t = (T) this.policy.getSettingsSystemValue(mode, key, defaultObject);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(' ');
            sb.append(t != null ? t.toString() : null);
            MdmLog.i("MDM:SettingPolicy", sb.toString());
            return t;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常！", th);
            return defaultObject;
        }
    }

    @Override
    public boolean isBluetoothEnabled() {
        try {
            boolean zIsBluetoothEnabled = this.policy.isBluetoothEnabled();
            MdmLog.i("MDM:SettingPolicy", "获取蓝牙状态: " + zIsBluetoothEnabled);
            return zIsBluetoothEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "获取蓝牙状态 发生异常!", th);
            return true;
        }
    }

    @Override
    public boolean isEyeComfortTurnedOn() {
        try {
            boolean zIsEyeComfortTurnedOn = this.policy.isEyeComfortTurnedOn();
            MdmLog.log$default("MDM:SettingPolicy", "查询护眼模式是否开启" + zIsEyeComfortTurnedOn, null, 4, null);
            return zIsEyeComfortTurnedOn;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "查询护眼模式是否开启异常!", th);
            return false;
        }
    }

    @Override
    public boolean isSearchSettingsEnable() {
        try {
            boolean zIsSearchSettingsEnable = this.policy.isSearchSettingsEnable();
            MdmLog.log("MDM:SettingPolicy", "获取设置搜索是否可用", Boolean.valueOf(zIsSearchSettingsEnable));
            return zIsSearchSettingsEnable;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "获取设置搜索是否可用 发生异常！", th);
            return false;
        }
    }

    @Override
    public void openSoftUpdateView() {
        try {
            MdmLog.log$default("MDM:SettingPolicy", "打开软件更新界面", null, 4, null);
            this.policy.openSoftUpdateView();
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "打开软件更新界面 发生异常！", th);
        }
    }

    @Override
    public boolean putSettingsSystemValue(int mode, String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        String str = "设置系统属性：key = " + key + "，value = " + value;
        try {
            boolean zPutSettingsSystemValue = this.policy.putSettingsSystemValue(mode, key, value);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(zPutSettingsSystemValue));
            return zPutSettingsSystemValue;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean removeAllowAccessibilityServicesList(List<String> pkgList) {
        Intrinsics.checkNotNullParameter(pkgList, "pkgList");
        try {
            boolean zRemoveAllowAccessibilityServicesList = this.policy.removeAllowAccessibilityServicesList(pkgList);
            MdmLog.log("MDM:SettingPolicy", "移除可强制开启辅助（无障碍）服务组件名", Boolean.valueOf(zRemoveAllowAccessibilityServicesList));
            return zRemoveAllowAccessibilityServicesList;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "移除可强制开启辅助（无障碍）服务组件名 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean removeLockScreen() {
        try {
            boolean zRemoveLockScreen = this.policy.removeLockScreen();
            MdmLog.log("MDM:SettingPolicy", "移除锁屏", Boolean.valueOf(zRemoveLockScreen));
            return zRemoveLockScreen;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "移除锁屏 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setAllowAccessibilityServicesList(List<String> pkgList) {
        Intrinsics.checkNotNullParameter(pkgList, "pkgList");
        try {
            boolean allowAccessibilityServicesList = this.policy.setAllowAccessibilityServicesList(pkgList);
            MdmLog.log("MDM:SettingPolicy", "设置可强制开启辅助（无障碍）服务组件名", Boolean.valueOf(allowAccessibilityServicesList));
            return allowAccessibilityServicesList;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "设置可强制开启辅助（无障碍）服务组件名 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setBluetoothEnable(boolean enable) {
        String str = enable ? "允许使用蓝牙" : "禁用蓝牙";
        try {
            boolean bluetoothEnable = this.policy.setBluetoothEnable(enable);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(bluetoothEnable));
            return bluetoothEnable;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setDevelopHiddenState(boolean state) {
        String str = state ? "隐藏开发者模式" : "打开开发者模式";
        try {
            boolean developHiddenState = this.policy.setDevelopHiddenState(state);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(developHiddenState));
            return developHiddenState;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean setDropMenuHiddenState(boolean state) {
        String str = state ? "隐藏下拉菜单" : "显示下拉菜单";
        try {
            boolean dropMenuHiddenState = this.policy.setDropMenuHiddenState(state);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(dropMenuHiddenState));
            return dropMenuHiddenState;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setMobileDataState(boolean state) {
        try {
            MdmLog.i("MDM:SettingPolicy", "设置移动数据:state=" + state);
            return this.policy.setMobileDataState(state);
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "设置移动数据异常!", th);
            return false;
        }
    }

    @Override
    public void setPowerSavingMode(PowerMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        try {
            MdmLog.i("MDM:SettingPolicy", "设置省电模式: " + mode);
            this.policy.setPowerSavingMode(mode);
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "设置省电模式: " + mode + " 发生异常!", th);
        }
    }

    @Override
    public boolean setSettingDisabled() {
        try {
            boolean settingDisabled = this.policy.setSettingDisabled();
            MdmLog.log("MDM:SettingPolicy", "setSettingDisabled", Boolean.valueOf(settingDisabled));
            return settingDisabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "setSettingDisabled 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean setSettingEnabled() {
        try {
            boolean settingEnabled = this.policy.setSettingEnabled();
            MdmLog.log("MDM:SettingPolicy", "setSettingEnabled", Boolean.valueOf(settingEnabled));
            return settingEnabled;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "setSettingEnabled 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean setSettingsHiddenState(boolean state) {
        String str = state ? "隐藏设置部分选项" : "显示设置部分选项";
        try {
            boolean settingsHiddenState = this.policy.setSettingsHiddenState(state);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(settingsHiddenState));
            return settingsHiddenState;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean setStatusBarButtonState(HashMap<String, Boolean> buttonList) {
        Intrinsics.checkNotNullParameter(buttonList, "buttonList");
        try {
            boolean statusBarButtonState = this.policy.setStatusBarButtonState(buttonList);
            MdmLog.log("MDM:SettingPolicy", "设置下拉菜单的按键状态", Boolean.valueOf(statusBarButtonState));
            return statusBarButtonState;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "设置下拉菜单的按键状态 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean setStreamVolume(int volume, int streamType, int flags) {
        String str = "设置声音音量：volume = " + volume + "，value = " + streamType;
        try {
            boolean streamVolume = this.policy.setStreamVolume(volume, streamType, flags);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(streamVolume));
            return streamVolume;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public void startSmartView() {
        try {
            MdmLog.i("MDM:SettingPolicy", "打开同屏");
            this.policy.startSmartView();
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "打开同屏发生异常!", th);
        }
    }

    @Override
    public void startSoftUpdate(int hour, int minute) {
        try {
            MdmLog.log$default("MDM:SettingPolicy", "定时启动三星自带系统更新程序" + hour + ':' + minute, null, 4, null);
            this.policy.startSoftUpdate(hour, minute);
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", "定时启动三星自带系统更新程序" + hour + ':' + minute + " 发生异常！", th);
        }
    }

    @Override
    public boolean turnOnConnectionAlwaysOn(boolean isOn) {
        String str = isOn ? "开启始终连接数据业务功能" : "关闭始终连接数据业务功能";
        try {
            boolean zTurnOnConnectionAlwaysOn = this.policy.turnOnConnectionAlwaysOn(isOn);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(zTurnOnConnectionAlwaysOn));
            return zTurnOnConnectionAlwaysOn;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean turnOnThreeKeyNavigation(boolean isOn) {
        String str = isOn ? "打开屏幕内三键导航" : "关闭屏幕内三键导航";
        try {
            boolean zTurnOnThreeKeyNavigation = this.policy.turnOnThreeKeyNavigation(isOn);
            MdmLog.log("MDM:SettingPolicy", str, Boolean.valueOf(zTurnOnThreeKeyNavigation));
            return zTurnOnThreeKeyNavigation;
        } catch (Throwable th) {
            MdmLog.e("MDM:SettingPolicy", str + " 发生异常！", th);
            return false;
        }
    }
}
