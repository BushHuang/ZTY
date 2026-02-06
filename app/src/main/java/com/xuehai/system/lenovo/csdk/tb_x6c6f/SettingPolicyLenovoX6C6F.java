package com.xuehai.system.lenovo.csdk.tb_x6c6f;

import android.app.csdk.CSDKManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.xuehai.system.common.compat.SettingPolicyDefault;
import com.xuehai.system.common.entities.PowerMode;
import com.xuehai.system.common.entities.RingerMode;
import com.xuehai.system.lenovo.csdk.LenovoCSDK;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\nH\u0016J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH\u0016J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH\u0016J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016J\u0010\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH\u0016J\b\u0010\u001e\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/xuehai/system/lenovo/csdk/tb_x6c6f/SettingPolicyLenovoX6C6F;", "Lcom/xuehai/system/common/compat/SettingPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "csdkManager", "Landroid/app/csdk/CSDKManager;", "controlAirplaneMode", "", "isOn", "", "controlEyeMode", "controlRingerMode", "mode", "Lcom/xuehai/system/common/entities/RingerMode;", "controlRotation", "isRotation", "isBluetoothEnabled", "openSoftUpdateView", "removeLockScreen", "setBluetoothEnable", "enable", "setDevelopHiddenState", "state", "setDropMenuHiddenState", "setPowerSavingMode", "Lcom/xuehai/system/common/entities/PowerMode;", "setSettingDisabled", "setSettingEnabled", "setSettingsHiddenState", "startSmartView", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SettingPolicyLenovoX6C6F extends SettingPolicyDefault {
    private final Context context;
    private final CSDKManager csdkManager;

    public SettingPolicyLenovoX6C6F(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.csdkManager = LenovoCSDK.INSTANCE.getCSDKManager(this.context);
    }

    @Override
    public void controlAirplaneMode(boolean isOn) {
        this.csdkManager.enableAirplaneMode(isOn);
    }

    @Override
    public void controlEyeMode(boolean isOn) {
        this.csdkManager.setEnabledEyeMode(isOn);
    }

    @Override
    public void controlRingerMode(RingerMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        super.controlRingerMode(mode);
    }

    @Override
    public void controlRotation(boolean isRotation) {
        this.csdkManager.enableAutoRotation(isRotation);
    }

    @Override
    public boolean isBluetoothEnabled() {
        return !this.csdkManager.isBluetoothDisable();
    }

    @Override
    public void openSoftUpdateView() {
        Intent component = new Intent().setComponent(new ComponentName("com.lenovo.ota", "com.lenovo.row.ota.core.d.ui.MainActivity"));
        component.setFlags(268435456);
        this.context.startActivity(component);
    }

    @Override
    public boolean removeLockScreen() {
        return this.csdkManager.unlockScreen();
    }

    @Override
    public boolean setBluetoothEnable(boolean enable) {
        this.csdkManager.enableBluetoothV3(enable);
        return this.csdkManager.disallowBluetoothV3(!enable);
    }

    @Override
    public boolean setDevelopHiddenState(boolean state) {
        this.csdkManager.enableDevModeV3(!state);
        this.csdkManager.killApplicationProcess("com.android.settings");
        return true;
    }

    @Override
    public boolean setDropMenuHiddenState(boolean state) {
        this.csdkManager.disableStatusBarPanel(state);
        return true;
    }

    @Override
    public void setPowerSavingMode(PowerMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.csdkManager.disablePowerSaveMode(Intrinsics.areEqual(mode, PowerMode.SavingOn.INSTANCE));
    }

    @Override
    public boolean setSettingDisabled() {
        this.csdkManager.clipSystemSettings(true);
        return true;
    }

    @Override
    public boolean setSettingEnabled() {
        this.csdkManager.clipSystemSettings(false);
        return true;
    }

    @Override
    public boolean setSettingsHiddenState(boolean state) {
        this.csdkManager.killApplicationProcess("com.android.settings");
        return true;
    }

    @Override
    public void startSmartView() {
        this.csdkManager.allowCast(true);
    }
}
