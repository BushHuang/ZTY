package com.xuehai.system.samsung.knox.v3.custom.p620;

import android.content.Context;
import com.b2b.rom.SamsungDevice;
import com.xuehai.system.common.entities.RingerMode;
import com.xuehai.system.samsung.knox.v3.SettingPolicySamsungV3;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\b\u0010\u0015\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p620/SettingPolicySamsungP620;", "Lcom/xuehai/system/samsung/knox/v3/SettingPolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "samsungDevice", "Lcom/b2b/rom/SamsungDevice;", "controlEyeMode", "", "isOn", "", "controlRingerMode", "mode", "Lcom/xuehai/system/common/entities/RingerMode;", "controlRotation", "isRotation", "openSoftUpdateView", "setSettingDisabled", "setSettingEnabled", "setSettingsHiddenState", "state", "startSmartView", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SettingPolicySamsungP620 extends SettingPolicySamsungV3 {
    private final SamsungDevice samsungDevice;

    public SettingPolicySamsungP620(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        SamsungDevice samsungDevice = SamsungDevice.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(samsungDevice, "getInstance(context)");
        this.samsungDevice = samsungDevice;
    }

    @Override
    public void controlEyeMode(boolean isOn) {
        this.samsungDevice.controlEyeMode(isOn);
    }

    @Override
    public void controlRingerMode(RingerMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.samsungDevice.controlRingerMode(mode.getCode());
    }

    @Override
    public void controlRotation(boolean isRotation) {
        this.samsungDevice.controlRotation(isRotation);
    }

    @Override
    public void openSoftUpdateView() {
        this.samsungDevice.openSoftUpdateView();
    }

    @Override
    public boolean setSettingDisabled() {
        this.samsungDevice.setSettingsEnableStatus(false);
        return true;
    }

    @Override
    public boolean setSettingEnabled() {
        this.samsungDevice.setSettingsEnableStatus(true);
        return true;
    }

    @Override
    public boolean setSettingsHiddenState(boolean state) {
        return super.setSettingsHiddenState(false);
    }

    @Override
    public void startSmartView() {
        this.samsungDevice.startSmartView();
    }
}
