package com.xuehai.system.samsung.knox;

import android.content.Context;
import com.samsung.android.knox.custom.CustomDeviceManager;
import com.xuehai.system.common.compat.SettingPolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016J\b\u0010\u000f\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/xuehai/system/samsung/knox/SettingPolicySamsung;", "Lcom/xuehai/system/common/compat/SettingPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/SamsungPolicy;", "isBluetoothEnabled", "", "removeLockScreen", "setBluetoothEnable", "enable", "setDevelopHiddenState", "state", "setMobileDataState", "setSettingDisabled", "setSettingEnabled", "Companion", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SettingPolicySamsung extends SettingPolicyDefault {
    private static final String APP_SETTINGS = "com.android.settings";
    private final SamsungPolicy policy;

    public SettingPolicySamsung(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = new SamsungPolicy(context);
    }

    @Override
    public boolean isBluetoothEnabled() {
        return this.policy.restrictionPolicy().isBluetoothTetheringEnabled();
    }

    @Override
    public boolean removeLockScreen() {
        return CustomDeviceManager.getInstance().getSystemManager().removeLockScreen() >= 0;
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
    public boolean setMobileDataState(boolean state) {
        try {
            this.policy.restrictionPolicy().allowUserMobileDataLimit(state);
            return true;
        } catch (Exception e) {
            MdmLog.e("setMobileDataState-exception", String.valueOf(e.getMessage()));
            return false;
        }
    }

    @Override
    public boolean setSettingDisabled() {
        return this.policy.applicationPolicy().setDisableApplication("com.android.settings");
    }

    @Override
    public boolean setSettingEnabled() {
        return this.policy.applicationPolicy().setEnableApplication("com.android.settings");
    }
}
