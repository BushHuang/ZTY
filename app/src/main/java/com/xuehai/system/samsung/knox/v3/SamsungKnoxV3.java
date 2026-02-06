package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import com.samsung.android.knox.EnterpriseDeviceManager;
import com.samsung.android.knox.application.ApplicationPolicy;
import com.samsung.android.knox.custom.CustomDeviceManager;
import com.samsung.android.knox.custom.SettingsManager;
import com.samsung.android.knox.custom.SystemManager;
import com.samsung.android.knox.datetime.DateTimePolicy;
import com.samsung.android.knox.deviceinfo.DeviceInventory;
import com.samsung.android.knox.devicesecurity.PasswordPolicy;
import com.samsung.android.knox.kiosk.KioskMode;
import com.samsung.android.knox.location.LocationPolicy;
import com.samsung.android.knox.multiuser.MultiUserManager;
import com.samsung.android.knox.net.firewall.Firewall;
import com.samsung.android.knox.net.wifi.WifiPolicy;
import com.samsung.android.knox.restriction.PhoneRestrictionPolicy;
import com.samsung.android.knox.restriction.RestrictionPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000bJ\u000e\u0010\f\u001a\n \u0007*\u0004\u0018\u00010\r0\rJ\u000e\u0010\u000e\u001a\n \u0007*\u0004\u0018\u00010\u000f0\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\n \u0007*\u0004\u0018\u00010\u00150\u0015J\u000e\u0010\u0016\u001a\n \u0007*\u0004\u0018\u00010\u00170\u0017J\u000e\u0010\u0018\u001a\n \u0007*\u0004\u0018\u00010\u00190\u0019J\u000e\u0010\u001a\u001a\n \u0007*\u0004\u0018\u00010\u001b0\u001bJ\u000e\u0010\u001c\u001a\n \u0007*\u0004\u0018\u00010\u001d0\u001dJ\u000e\u0010\u001e\u001a\n \u0007*\u0004\u0018\u00010\u001f0\u001fJ\u000e\u0010 \u001a\n \u0007*\u0004\u0018\u00010!0!J\u000e\u0010\"\u001a\n \u0007*\u0004\u0018\u00010#0#J\u000e\u0010$\u001a\n \u0007*\u0004\u0018\u00010%0%R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/samsung/android/knox/application/ApplicationPolicy;", "kotlin.jvm.PlatformType", "customDeviceManager", "Lcom/samsung/android/knox/custom/CustomDeviceManager;", "dateTimePolicy", "Lcom/samsung/android/knox/datetime/DateTimePolicy;", "deviceInventory", "Lcom/samsung/android/knox/deviceinfo/DeviceInventory;", "firewall", "Lcom/samsung/android/knox/net/firewall/Firewall;", "hasPermission", "", "permission", "", "kioskMode", "Lcom/samsung/android/knox/kiosk/KioskMode;", "locationPolicy", "Lcom/samsung/android/knox/location/LocationPolicy;", "multiUserManager", "Lcom/samsung/android/knox/multiuser/MultiUserManager;", "passwordPolicy", "Lcom/samsung/android/knox/devicesecurity/PasswordPolicy;", "phoneRestrictionPolicy", "Lcom/samsung/android/knox/restriction/PhoneRestrictionPolicy;", "restrictionPolicy", "Lcom/samsung/android/knox/restriction/RestrictionPolicy;", "settingsManager", "Lcom/samsung/android/knox/custom/SettingsManager;", "systemManager", "Lcom/samsung/android/knox/custom/SystemManager;", "wifiPolicy", "Lcom/samsung/android/knox/net/wifi/WifiPolicy;", "Companion", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SamsungKnoxV3 {

    public static final Companion INSTANCE = new Companion(null);
    private static SamsungKnoxV3 INSTANCE;
    private final Context context;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3$Companion;", "", "()V", "INSTANCE", "Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "getInstance", "context", "Landroid/content/Context;", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SamsungKnoxV3 getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SamsungKnoxV3 samsungKnoxV3 = SamsungKnoxV3.INSTANCE;
            if (samsungKnoxV3 != null) {
                return samsungKnoxV3;
            }
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
            SamsungKnoxV3 samsungKnoxV32 = new SamsungKnoxV3(applicationContext, null);
            Companion companion = SamsungKnoxV3.INSTANCE;
            SamsungKnoxV3.INSTANCE = samsungKnoxV32;
            return samsungKnoxV32;
        }
    }

    private SamsungKnoxV3(Context context) {
        this.context = context;
    }

    public SamsungKnoxV3(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final ApplicationPolicy applicationPolicy() {
        return EnterpriseDeviceManager.getInstance(this.context).getApplicationPolicy();
    }

    public final CustomDeviceManager customDeviceManager() {
        CustomDeviceManager customDeviceManager = CustomDeviceManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(customDeviceManager, "getInstance()");
        return customDeviceManager;
    }

    public final DateTimePolicy dateTimePolicy() {
        return EnterpriseDeviceManager.getInstance(this.context).getDateTimePolicy();
    }

    public final DeviceInventory deviceInventory() {
        return EnterpriseDeviceManager.getInstance(this.context).getDeviceInventory();
    }

    public final Firewall firewall() {
        return EnterpriseDeviceManager.getInstance(this.context).getFirewall();
    }

    public final boolean hasPermission(String permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        return customDeviceManager().checkEnterprisePermission(permission);
    }

    public final KioskMode kioskMode() {
        return EnterpriseDeviceManager.getInstance(this.context).getKioskMode();
    }

    public final LocationPolicy locationPolicy() {
        return EnterpriseDeviceManager.getInstance(this.context).getLocationPolicy();
    }

    public final MultiUserManager multiUserManager() {
        return EnterpriseDeviceManager.getInstance(this.context).getMultiUserManager();
    }

    public final PasswordPolicy passwordPolicy() {
        return EnterpriseDeviceManager.getInstance(this.context).getPasswordPolicy();
    }

    public final PhoneRestrictionPolicy phoneRestrictionPolicy() {
        return EnterpriseDeviceManager.getInstance(this.context).getPhoneRestrictionPolicy();
    }

    public final RestrictionPolicy restrictionPolicy() {
        return EnterpriseDeviceManager.getInstance(this.context).getRestrictionPolicy();
    }

    public final SettingsManager settingsManager() {
        return customDeviceManager().getSettingsManager();
    }

    public final SystemManager systemManager() {
        return customDeviceManager().getSystemManager();
    }

    public final WifiPolicy wifiPolicy() {
        return EnterpriseDeviceManager.getInstance(this.context).getWifiPolicy();
    }
}
