package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import com.xuehai.system.common.compat.SettingPolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0010\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0016J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0015\u001a\u00020\fH\u0016J\b\u0010\u0016\u001a\u00020\fH\u0016J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/SettingPolicySamsungV3;", "Lcom/xuehai/system/common/compat/SettingPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "getPolicy", "()Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "controlAirplaneMode", "", "isOn", "", "isBluetoothEnabled", "removeLockScreen", "setBluetoothEnable", "enable", "setDevelopHiddenState", "state", "setDropMenuHiddenState", "setMobileDataState", "setSettingDisabled", "setSettingEnabled", "setSettingsHiddenState", "Companion", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SettingPolicySamsungV3 extends SettingPolicyDefault {
    private static final String APP_SECURE_FOLDER = "com.samsung.knox.securefolder";
    private static final String APP_SETTINGS = "com.android.settings";
    private static final String TAG = "SettingPolicySamsungV3";
    private final SamsungKnoxV3 policy;

    public SettingPolicySamsungV3(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = SamsungKnoxV3.INSTANCE.getInstance(context);
    }

    @Override
    public void controlAirplaneMode(boolean isOn) {
        int flightModeState = this.policy.settingsManager().setFlightModeState(isOn ? 1 : 0);
        if (flightModeState == -43) {
            MdmLog.i("SettingPolicySamsungV3", "飞行模式指定的状态无效");
            return;
        }
        if (flightModeState == 0) {
            MdmLog.i("SettingPolicySamsungV3", "飞行模式状态设置成功");
            return;
        }
        MdmLog.i("SettingPolicySamsungV3", "飞行模式状态设置失败，code：" + flightModeState);
    }

    public final SamsungKnoxV3 getPolicy() {
        return this.policy;
    }

    @Override
    public boolean isBluetoothEnabled() {
        return this.policy.restrictionPolicy().isBluetoothTetheringEnabled();
    }

    @Override
    public boolean removeLockScreen() {
        return this.policy.systemManager().removeLockScreen() == 0;
    }

    @Override
    public boolean setBluetoothEnable(boolean enable) {
        return this.policy.restrictionPolicy().allowBluetooth(enable);
    }

    @Override
    public boolean setDevelopHiddenState(boolean state) {
        return this.policy.restrictionPolicy().allowDeveloperMode(!state);
    }

    @Override
    public boolean setDropMenuHiddenState(boolean state) {
        return this.policy.restrictionPolicy().allowStatusBarExpansion(!state);
    }

    @Override
    public boolean setMobileDataState(boolean state) {
        return this.policy.settingsManager().setMobileDataState(state) == 0;
    }

    @Override
    public boolean setSettingDisabled() {
        return this.policy.applicationPolicy().setDisableApplication("com.samsung.knox.securefolder");
    }

    @Override
    public boolean setSettingEnabled() {
        return this.policy.applicationPolicy().setEnableApplication("com.android.settings") && this.policy.applicationPolicy().setEnableApplication("com.samsung.knox.securefolder");
    }

    @Override
    public boolean setSettingsHiddenState(boolean state) {
        return this.policy.settingsManager().setSettingsHiddenState(state, 8127) >= 0;
    }
}
