package com.xuehai.system.huawei_c5;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.provider.Settings;
import com.huawei.android.app.admin.DeviceControlManager;
import com.huawei.android.app.admin.DeviceHwSystemManager;
import com.huawei.android.app.admin.DevicePasswordManager;
import com.huawei.android.app.admin.DeviceRestrictionManager;
import com.huawei.android.app.admin.DeviceSettingsManager;
import com.xuehai.system.common.compat.SettingPolicyDefault;
import com.xuehai.system.common.entities.PowerMode;
import com.xuehai.system.common.entities.RingerMode;
import com.xuehai.system.common.log.MdmLog;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\bH\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0016J\b\u0010\u0017\u001a\u00020\bH\u0016J\b\u0010\u0018\u001a\u00020\bH\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016J\b\u0010\u001a\u001a\u00020\fH\u0016J\u0016\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0016J\b\u0010\u001d\u001a\u00020\bH\u0016J\u0016\u0010\u001e\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0016J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\bH\u0016J\u0010\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bH\u0016J\u0010\u0010#\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\bH\u0016J\b\u0010&\u001a\u00020\bH\u0016J\u0010\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\bH\u0002J\u0010\u0010)\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bH\u0016J\u0010\u0010*\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010+\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/xuehai/system/huawei_c5/SettingPolicyHWHEM;", "Lcom/xuehai/system/common/compat/SettingPolicyDefault;", "context", "Landroid/content/Context;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/Context;Landroid/content/ComponentName;)V", "allowLockScreen", "", "allow", "allowSearchSettingsIndex", "controlAirplaneMode", "", "isOn", "controlEyeMode", "controlRingerMode", "mode", "Lcom/xuehai/system/common/entities/RingerMode;", "controlRotation", "isRotation", "getAllowAccessibilityServicesList", "", "", "isBluetoothEnabled", "isEyeComfortTurnedOn", "isSearchSettingsEnable", "openSoftUpdateView", "removeAllowAccessibilityServicesList", "pkgList", "removeLockScreen", "setAllowAccessibilityServicesList", "setBluetoothEnable", "enable", "setDevelopHiddenState", "state", "setPowerSavingMode", "Lcom/xuehai/system/common/entities/PowerMode;", "setSettingDisabled", "setSettingEnabled", "setSettingsApplicationDisabled", "disable", "setSettingsHiddenState", "turnOnConnectionAlwaysOn", "turnOnThreeKeyNavigation", "Companion", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SettingPolicyHWHEM extends SettingPolicyDefault {
    private static final String TAG = "SettingPolicyHWHEM";
    private final ComponentName componentName;
    private final Context context;

    public SettingPolicyHWHEM(Context context, ComponentName componentName) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.context = context;
        this.componentName = componentName;
    }

    private final boolean setSettingsApplicationDisabled(boolean disable) {
        return new DeviceRestrictionManager().setSettingsApplicationDisabled(this.componentName, disable);
    }

    @Override
    public boolean allowLockScreen(boolean allow) {
        try {
            PackageManager packageManager = this.context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
            packageManager.setComponentEnabledSetting(new ComponentName("com.android.settings", "com.android.settings.password.ChooseLockPassword"), allow ? 1 : 2, 0);
        } catch (Throwable th) {
            MdmLog.e("SettingPolicyHWHEM", "设置华为C5移除锁屏调用组件设置失败", th);
        }
        return new DevicePasswordManager().setKeyguardDisabled(this.componentName, 0, !allow);
    }

    @Override
    public boolean allowSearchSettingsIndex(boolean allow) {
        return new DeviceSettingsManager().setSearchIndexDisabled(this.componentName, !allow);
    }

    @Override
    public void controlAirplaneMode(boolean isOn) {
        new WifiPolicyHWHEM(this.componentName).setWiFiState(!isOn);
        Settings.Global.putInt(this.context.getContentResolver(), "airplane_mode_on", isOn ? 1 : 0);
    }

    @Override
    public void controlEyeMode(boolean isOn) {
        new DeviceControlManager().turnOnEyeComfort(this.componentName, isOn);
    }

    @Override
    public void controlRingerMode(RingerMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Object systemService = this.context.getSystemService("audio");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.media.AudioManager");
        }
        AudioManager audioManager = (AudioManager) systemService;
        audioManager.setRingerMode(mode.getCode() == RingerMode.Normal.INSTANCE.getCode() ? 2 : 0);
        audioManager.getStreamVolume(2);
    }

    @Override
    public void controlRotation(boolean isRotation) {
        new DeviceControlManager().turnOnAutoRotation(this.componentName, isRotation);
    }

    @Override
    public List<String> getAllowAccessibilityServicesList() {
        ArrayList allowAccessibilityServicesList = new DeviceSettingsManager().getAllowAccessibilityServicesList(this.componentName);
        Intrinsics.checkNotNullExpressionValue(allowAccessibilityServicesList, "DeviceSettingsManager().…rvicesList(componentName)");
        return allowAccessibilityServicesList;
    }

    @Override
    public boolean isBluetoothEnabled() {
        return !new DeviceRestrictionManager().isBluetoothDisabled(this.componentName);
    }

    @Override
    public boolean isEyeComfortTurnedOn() {
        return new DeviceControlManager().isEyeComfortTurnedOn(this.componentName);
    }

    @Override
    public boolean isSearchSettingsEnable() {
        return !new DeviceSettingsManager().isSearchIndexDisabled(this.componentName);
    }

    @Override
    public void openSoftUpdateView() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.huawei.android.hwouc", "com.huawei.android.hwouc.ui.activities.MainEntranceActivity"));
        intent.setFlags(270532608);
        this.context.startActivity(intent);
    }

    @Override
    public boolean removeAllowAccessibilityServicesList(List<String> pkgList) {
        Intrinsics.checkNotNullParameter(pkgList, "pkgList");
        return new DeviceSettingsManager().removeAllowAccessibilityServicesList(this.componentName, new ArrayList(pkgList));
    }

    @Override
    public boolean removeLockScreen() {
        return allowLockScreen(false);
    }

    @Override
    public boolean setAllowAccessibilityServicesList(List<String> pkgList) {
        Intrinsics.checkNotNullParameter(pkgList, "pkgList");
        return new DeviceSettingsManager().setAllowAccessibilityServicesList(this.componentName, new ArrayList(pkgList));
    }

    @Override
    public boolean setBluetoothEnable(boolean enable) {
        new DeviceRestrictionManager().setBluetoothDisabled(this.componentName, !enable);
        return isBluetoothEnabled() == enable;
    }

    @Override
    public boolean setDevelopHiddenState(boolean state) {
        return new DeviceSettingsManager().setDevelopmentOptionDisabled(this.componentName, state);
    }

    @Override
    public void setPowerSavingMode(PowerMode mode) {
        boolean z;
        Intrinsics.checkNotNullParameter(mode, "mode");
        if (mode instanceof PowerMode.SavingOn) {
            z = false;
        } else {
            if (!(mode instanceof PowerMode.SavingOff)) {
                throw new NoWhenBranchMatchedException();
            }
            z = true;
        }
        new DeviceHwSystemManager().setPowerSaveModeDisabled(this.componentName, Boolean.valueOf(z));
    }

    @Override
    public boolean setSettingDisabled() {
        return setSettingsApplicationDisabled(true);
    }

    @Override
    public boolean setSettingEnabled() {
        return setSettingsApplicationDisabled(false);
    }

    @Override
    public boolean setSettingsHiddenState(boolean state) throws Resources.NotFoundException {
        new DeviceRestrictionManager().setStatusBarExpandPanelDisabled(this.componentName, state);
        ArrayList arrayList = new ArrayList();
        if (state) {
            String[] stringArray = this.context.getResources().getStringArray(R.array.hw_system_settings_item);
            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr….hw_system_settings_item)");
            CollectionsKt.addAll(arrayList, stringArray);
        }
        new DeviceControlManager().setCustomSettingsMenu(this.componentName, arrayList);
        return true;
    }

    @Override
    public boolean turnOnConnectionAlwaysOn(boolean isOn) {
        new DeviceControlManager().turnOnConnectionAlwaysOn(this.componentName, isOn);
        return true;
    }

    @Override
    public boolean turnOnThreeKeyNavigation(boolean isOn) {
        return new DeviceSettingsManager().turnOnThreeKeyNavigation(this.componentName, isOn);
    }
}
