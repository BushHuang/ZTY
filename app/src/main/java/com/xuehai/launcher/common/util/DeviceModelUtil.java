package com.xuehai.launcher.common.util;

import android.content.Context;
import android.os.Build;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xuehai.mdm.config.DeviceSupport;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fH\u0007J\u0006\u0010\u000e\u001a\u00020\nJ\b\u0010\u000f\u001a\u00020\nH\u0007J\b\u0010\u0010\u001a\u00020\nH\u0007J\b\u0010\u0011\u001a\u00020\nH\u0007J\b\u0010\u0012\u001a\u00020\nH\u0007J\b\u0010\u0013\u001a\u00020\nH\u0007J\b\u0010\u0014\u001a\u00020\nH\u0007J\b\u0010\u0015\u001a\u00020\nH\u0007J\b\u0010\u0016\u001a\u00020\nH\u0007J\b\u0010\u0017\u001a\u00020\nH\u0007J\b\u0010\u0018\u001a\u00020\nH\u0007J\b\u0010\u0019\u001a\u00020\nH\u0007J\b\u0010\u001a\u001a\u00020\nH\u0007J\b\u0010\u001b\u001a\u00020\nH\u0007J\u0006\u0010\u001c\u001a\u00020\nJ\u0006\u0010\u001d\u001a\u00020\nR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001e"}, d2 = {"Lcom/xuehai/launcher/common/util/DeviceModelUtil;", "", "()V", "deviceSupport", "Lcom/xuehai/mdm/config/DeviceSupport;", "getDeviceSupport", "()Lcom/xuehai/mdm/config/DeviceSupport;", "deviceSupport$delegate", "Lkotlin/Lazy;", "compareDeviceModel", "", "device", "", "deviceMode", "isCustomSettingDevice", "isHuaweiBBG7W00", "isHuaweiBTKZW00", "isHuaweiBZF5W00", "isHuaweiHEMDevice", "isLenovoDevice", "isLenovoTB223FC", "isLenovoTB8604F", "isLenovoTBX6C6F", "isSMP200", "isSMP615C", "isSMP620", "isSamsungSupport", "isSamsungV3Support", "isUseXhNotificationDevice", "isUseXhSettingsDevice", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceModelUtil {
    public static final DeviceModelUtil INSTANCE = new DeviceModelUtil();

    private static final Lazy deviceSupport = LazyKt.lazy(new Function0<DeviceSupport>() {
        @Override
        public final DeviceSupport invoke() {
            Context context = XhBaseApplication.mContext;
            Intrinsics.checkNotNullExpressionValue(context, "mContext");
            return new DeviceSupport(context);
        }
    });

    private DeviceModelUtil() {
    }

    @JvmStatic
    public static final boolean compareDeviceModel(String device, String deviceMode) {
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(deviceMode, "deviceMode");
        return Intrinsics.areEqual(deviceMode, device);
    }

    public static boolean compareDeviceModel$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(str2, "MODEL");
        }
        return compareDeviceModel(str, str2);
    }

    private final DeviceSupport getDeviceSupport() {
        return (DeviceSupport) deviceSupport.getValue();
    }

    @JvmStatic
    public static final boolean isHuaweiBBG7W00() {
        return compareDeviceModel$default("BBG7-W00", null, 2, null);
    }

    @JvmStatic
    public static final boolean isHuaweiBTKZW00() {
        return compareDeviceModel$default("BTKZ-W00", null, 2, null);
    }

    @JvmStatic
    public static final boolean isHuaweiBZF5W00() {
        return compareDeviceModel$default("BZF5-W00", null, 2, null) || compareDeviceModel$default("AY6-W00", null, 2, null);
    }

    @JvmStatic
    public static final boolean isHuaweiHEMDevice() {
        return INSTANCE.getDeviceSupport().isHuaWeiHEMSupport();
    }

    @JvmStatic
    public static final boolean isLenovoDevice() {
        return INSTANCE.getDeviceSupport().isLenovoSupport();
    }

    @JvmStatic
    public static final boolean isLenovoTB223FC() {
        return compareDeviceModel$default("Lenovo TB223FC", null, 2, null);
    }

    @JvmStatic
    public static final boolean isLenovoTB8604F() {
        return compareDeviceModel$default("Lenovo TB-8604F", null, 2, null);
    }

    @JvmStatic
    public static final boolean isLenovoTBX6C6F() {
        return compareDeviceModel$default("Lenovo TB-X6C6F", null, 2, null);
    }

    @JvmStatic
    public static final boolean isSMP200() {
        return compareDeviceModel$default("SM-P200", null, 2, null);
    }

    @JvmStatic
    public static final boolean isSMP615C() {
        return compareDeviceModel$default("SM-P615C", null, 2, null);
    }

    @JvmStatic
    public static final boolean isSMP620() {
        return compareDeviceModel$default("SM-P620", null, 2, null);
    }

    @JvmStatic
    public static final boolean isSamsungSupport() {
        return INSTANCE.getDeviceSupport().isSamsungSupport();
    }

    @JvmStatic
    public static final boolean isSamsungV3Support() {
        return INSTANCE.getDeviceSupport().isSamsungV3Support();
    }

    public final boolean isCustomSettingDevice() {
        return getDeviceSupport().isCustomSettingDevice();
    }

    public final boolean isUseXhNotificationDevice() {
        return getDeviceSupport().isUseXhNotificationDevice();
    }

    public final boolean isUseXhSettingsDevice() {
        return getDeviceSupport().isUseXhSettingsDevice();
    }
}
