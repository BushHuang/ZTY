package com.xuehai.system.samsung.knox.v3.custom.p615;

import android.content.Context;
import com.samsung.android.knox.custom.SettingsManager;
import com.xuehai.system.common.entities.PowerMode;
import com.xuehai.system.common.entities.RingerMode;
import com.xuehai.system.samsung.knox.v3.custom.p200.SettingPolicySamsungP200;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0006H\u0016¨\u0006\u0019"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p615/SettingPolicySamsungP615;", "Lcom/xuehai/system/samsung/knox/v3/custom/p200/SettingPolicySamsungP200;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "controlAirplaneMode", "", "isOn", "", "controlEyeMode", "controlRingerMode", "mode", "Lcom/xuehai/system/common/entities/RingerMode;", "controlRotation", "isRotation", "controlSettingsTopLevelMenu", "keyList", "", "openSoftUpdateView", "setDropMenuHiddenState", "state", "setMobileDataState", "setPowerSavingMode", "Lcom/xuehai/system/common/entities/PowerMode;", "startSmartView", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SettingPolicySamsungP615 extends SettingPolicySamsungP200 {
    public SettingPolicySamsungP615(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public void controlAirplaneMode(boolean isOn) {
        samsungDevice().controlAirplaneMode(isOn);
    }

    @Override
    public void controlEyeMode(boolean isOn) {
        samsungDevice().controlEyeMode(isOn);
    }

    @Override
    public void controlRingerMode(RingerMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        samsungDevice().controlRingerMode(mode.getCode());
    }

    @Override
    public void controlRotation(boolean isRotation) {
        samsungDevice().controlRotation(isRotation);
    }

    @Override
    public void controlSettingsTopLevelMenu(String keyList) {
        Intrinsics.checkNotNullParameter(keyList, "keyList");
        samsungDevice().controlSettingsTopLevelMenu(keyList);
    }

    @Override
    public void openSoftUpdateView() {
        samsungDevice().openSoftUpdateView();
    }

    @Override
    public boolean setDropMenuHiddenState(boolean state) {
        return true;
    }

    @Override
    public boolean setMobileDataState(boolean state) {
        return getPolicy().settingsManager().setMobileDataState(state) == 0;
    }

    @Override
    public void setPowerSavingMode(PowerMode mode) {
        int i;
        Intrinsics.checkNotNullParameter(mode, "mode");
        SettingsManager settingsManager = getPolicy().settingsManager();
        if (mode instanceof PowerMode.SavingOn) {
            i = 1;
        } else {
            if (!(mode instanceof PowerMode.SavingOff)) {
                throw new NoWhenBranchMatchedException();
            }
            i = 0;
        }
        settingsManager.setPowerSavingMode(i);
    }

    @Override
    public void startSmartView() {
        samsungDevice().startSmartView();
    }
}
