package com.xuehai.mdm.config;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\u001a\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\tJ\u0006\u0010\u0011\u001a\u00020\tJ\u0006\u0010\u0012\u001a\u00020\tJ\u0006\u0010\u0013\u001a\u00020\tJ\u0006\u0010\u0014\u001a\u00020\tJ\u0006\u0010\u0015\u001a\u00020\tJ\u0006\u0010\u0016\u001a\u00020\tJ\u0006\u0010\u0017\u001a\u00020\tR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/xuehai/mdm/config/DeviceSupport;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "configuration", "Lcom/xuehai/mdm/config/DeviceConfiguration;", "getDeviceConfiguration", "isCustomSettingDevice", "", "isDevicePolicyServiceSupport", "isDeviceSupport", "model", "", "res", "", "isHuaWeiHEMSupport", "isHuaWeiSupport", "isLenovoSupport", "isSDKSupport", "isSamsungSupport", "isSamsungV3Support", "isUseXhNotificationDevice", "isUseXhSettingsDevice", "mdm_config_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceSupport {
    private DeviceConfiguration configuration;
    private final Context context;

    public DeviceSupport(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public static boolean isDeviceSupport$default(DeviceSupport deviceSupport, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(str, "MODEL");
        }
        return deviceSupport.isDeviceSupport(str, i);
    }

    public final DeviceConfiguration getDeviceConfiguration() {
        LenovoConfiguration samsungConfiguration = this.configuration;
        if (samsungConfiguration == null) {
            samsungConfiguration = isSamsungSupport() ? new SamsungConfiguration(this.context) : isSamsungV3Support() ? new SamsungV3Configuration(this.context) : isHuaWeiSupport() ? new HuaWeiConfiguration(this.context) : isHuaWeiHEMSupport() ? new HuaWeiHEMConfiguration(this.context) : isLenovoSupport() ? new LenovoConfiguration(this.context) : new DeviceConfiguration(this.context);
            this.configuration = samsungConfiguration;
        }
        return samsungConfiguration;
    }

    public final boolean isCustomSettingDevice() {
        return isDeviceSupport$default(this, null, R.array.setting_custom_device, 1, null);
    }

    public final boolean isDevicePolicyServiceSupport() {
        return getDeviceConfiguration().isDevicePolicyServiceSupport();
    }

    public final boolean isDeviceSupport(String model, int res) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(model, "model");
        String[] stringArray = this.context.getResources().getStringArray(res);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStringArray(res)");
        return ArraysKt.contains(stringArray, model);
    }

    public final boolean isHuaWeiHEMSupport() {
        if (isDeviceSupport$default(this, null, R.array.device_huawei_c5, 1, null)) {
            return true;
        }
        return !isHuaWeiSupport() && StringsKt.equals("HUAWEI", Build.MANUFACTURER, true);
    }

    public final boolean isHuaWeiSupport() {
        return isDeviceSupport$default(this, null, R.array.device_huawei, 1, null);
    }

    public final boolean isLenovoSupport() {
        return isDeviceSupport$default(this, null, R.array.device_lenovo, 1, null);
    }

    public final boolean isSDKSupport() {
        return getDeviceConfiguration().isSDKSupport();
    }

    public final boolean isSamsungSupport() {
        return isDeviceSupport$default(this, null, R.array.device_samsung, 1, null);
    }

    public final boolean isSamsungV3Support() {
        return isDeviceSupport$default(this, null, R.array.device_samsung_v3, 1, null);
    }

    public final boolean isUseXhNotificationDevice() {
        return isDeviceSupport$default(this, null, R.array.use_xh_notification_device, 1, null);
    }

    public final boolean isUseXhSettingsDevice() {
        return isDeviceSupport$default(this, null, R.array.use_xh_settings_device, 1, null);
    }
}
