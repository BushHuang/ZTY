package com.xuehai.system.common.policies;

import com.xuehai.system.common.entities.PowerMode;
import com.xuehai.system.common.entities.RingerMode;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H&J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H&J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0003H&J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H&J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018H&J+\u0010\u0019\u001a\u0002H\u001a\"\u0004\b\u0000\u0010\u001a2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u0002H\u001aH&¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u0003H&J\b\u0010\u001f\u001a\u00020\u0003H&J\b\u0010 \u001a\u00020\u0003H&J\b\u0010!\u001a\u00020\fH&J \u0010\"\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u0001H&J\u0016\u0010$\u001a\u00020\u00032\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018H&J\b\u0010&\u001a\u00020\u0003H&J\u0016\u0010'\u001a\u00020\u00032\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018H&J\u0010\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u0003H&J\u0010\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0003H&J\u0010\u0010,\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0003H&J\u0010\u0010-\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0003H&J\u0010\u0010.\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020/H&J\b\u00100\u001a\u00020\u0003H&J\b\u00101\u001a\u00020\u0003H&J\u0010\u00102\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0003H&J,\u00103\u001a\u00020\u00032\"\u00104\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000305j\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0003`6H&J \u00107\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\b\u00109\u001a\u00020\fH&J\u0018\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u00052\u0006\u0010<\u001a\u00020\u0005H&J\u0010\u0010=\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H&J\u0010\u0010>\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H&¨\u0006?"}, d2 = {"Lcom/xuehai/system/common/policies/ISettingPolicy;", "", "adjustStreamVolume", "", "streamType", "", "direction", "flags", "allowLockScreen", "allow", "allowSearchSettingsIndex", "controlAirplaneMode", "", "isOn", "controlEyeMode", "controlRingerMode", "mode", "Lcom/xuehai/system/common/entities/RingerMode;", "controlRotation", "isRotation", "controlSettingsTopLevelMenu", "keyList", "", "getAllowAccessibilityServicesList", "", "getSettingsSystemValue", "T", "key", "defaultObject", "(ILjava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "isBluetoothEnabled", "isEyeComfortTurnedOn", "isSearchSettingsEnable", "openSoftUpdateView", "putSettingsSystemValue", "value", "removeAllowAccessibilityServicesList", "pkgList", "removeLockScreen", "setAllowAccessibilityServicesList", "setBluetoothEnable", "enable", "setDevelopHiddenState", "state", "setDropMenuHiddenState", "setMobileDataState", "setPowerSavingMode", "Lcom/xuehai/system/common/entities/PowerMode;", "setSettingDisabled", "setSettingEnabled", "setSettingsHiddenState", "setStatusBarButtonState", "buttonList", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "setStreamVolume", "volume", "startSmartView", "startSoftUpdate", "hour", "minute", "turnOnConnectionAlwaysOn", "turnOnThreeKeyNavigation", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ISettingPolicy {
    boolean adjustStreamVolume(int streamType, int direction, int flags);

    boolean allowLockScreen(boolean allow);

    boolean allowSearchSettingsIndex(boolean allow);

    void controlAirplaneMode(boolean isOn);

    void controlEyeMode(boolean isOn);

    void controlRingerMode(RingerMode mode);

    void controlRotation(boolean isRotation);

    void controlSettingsTopLevelMenu(String keyList);

    List<String> getAllowAccessibilityServicesList();

    <T> T getSettingsSystemValue(int mode, String key, T defaultObject);

    boolean isBluetoothEnabled();

    boolean isEyeComfortTurnedOn();

    boolean isSearchSettingsEnable();

    void openSoftUpdateView();

    boolean putSettingsSystemValue(int mode, String key, Object value);

    boolean removeAllowAccessibilityServicesList(List<String> pkgList);

    boolean removeLockScreen();

    boolean setAllowAccessibilityServicesList(List<String> pkgList);

    boolean setBluetoothEnable(boolean enable);

    boolean setDevelopHiddenState(boolean state);

    boolean setDropMenuHiddenState(boolean state);

    boolean setMobileDataState(boolean state);

    void setPowerSavingMode(PowerMode mode);

    boolean setSettingDisabled();

    boolean setSettingEnabled();

    boolean setSettingsHiddenState(boolean state);

    boolean setStatusBarButtonState(HashMap<String, Boolean> buttonList);

    boolean setStreamVolume(int volume, int streamType, int flags);

    void startSmartView();

    void startSoftUpdate(int hour, int minute);

    boolean turnOnConnectionAlwaysOn(boolean isOn);

    boolean turnOnThreeKeyNavigation(boolean isOn);
}
