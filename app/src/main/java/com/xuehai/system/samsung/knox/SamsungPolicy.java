package com.xuehai.system.samsung.knox;

import android.content.Context;
import com.samsung.android.knox.EnterpriseDeviceManager;
import com.samsung.android.knox.application.ApplicationPolicy;
import com.samsung.android.knox.datetime.DateTimePolicy;
import com.samsung.android.knox.deviceinfo.DeviceInventory;
import com.samsung.android.knox.kiosk.KioskMode;
import com.samsung.android.knox.location.LocationPolicy;
import com.samsung.android.knox.multiuser.MultiUserManager;
import com.samsung.android.knox.net.firewall.Firewall;
import com.samsung.android.knox.net.wifi.WifiPolicy;
import com.samsung.android.knox.restriction.PhoneRestrictionPolicy;
import com.samsung.android.knox.restriction.RestrictionPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006J\u000e\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tJ\u000e\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000bJ\u000e\u0010\f\u001a\n \u0007*\u0004\u0018\u00010\r0\rJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\n \u0007*\u0004\u0018\u00010\u00110\u0011J\u000e\u0010\u0012\u001a\n \u0007*\u0004\u0018\u00010\u00130\u0013J\u000e\u0010\u0014\u001a\n \u0007*\u0004\u0018\u00010\u00150\u0015J\u000e\u0010\u0016\u001a\n \u0007*\u0004\u0018\u00010\u00170\u0017J\u000e\u0010\u0018\u001a\n \u0007*\u0004\u0018\u00010\u00190\u0019J\u000e\u0010\u001a\u001a\n \u0007*\u0004\u0018\u00010\u001b0\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/xuehai/system/samsung/knox/SamsungPolicy;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/samsung/android/knox/application/ApplicationPolicy;", "kotlin.jvm.PlatformType", "dateTimePolicy", "Lcom/samsung/android/knox/datetime/DateTimePolicy;", "deviceInventory", "Lcom/samsung/android/knox/deviceinfo/DeviceInventory;", "firewall", "Lcom/samsung/android/knox/net/firewall/Firewall;", "getEnterpriseDeviceManager", "Lcom/samsung/android/knox/EnterpriseDeviceManager;", "kioskMode", "Lcom/samsung/android/knox/kiosk/KioskMode;", "locationPolicy", "Lcom/samsung/android/knox/location/LocationPolicy;", "multiUserManager", "Lcom/samsung/android/knox/multiuser/MultiUserManager;", "phoneRestrictionPolicy", "Lcom/samsung/android/knox/restriction/PhoneRestrictionPolicy;", "restrictionPolicy", "Lcom/samsung/android/knox/restriction/RestrictionPolicy;", "wifiPolicy", "Lcom/samsung/android/knox/net/wifi/WifiPolicy;", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SamsungPolicy {
    private final Context context;

    public SamsungPolicy(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final ApplicationPolicy applicationPolicy() {
        return getEnterpriseDeviceManager().getApplicationPolicy();
    }

    public final DateTimePolicy dateTimePolicy() {
        return getEnterpriseDeviceManager().getDateTimePolicy();
    }

    public final DeviceInventory deviceInventory() {
        return getEnterpriseDeviceManager().getDeviceInventory();
    }

    public final Firewall firewall() {
        return getEnterpriseDeviceManager().getFirewall();
    }

    public final EnterpriseDeviceManager getEnterpriseDeviceManager() {
        EnterpriseDeviceManager enterpriseDeviceManager = EnterpriseDeviceManager.getInstance(this.context);
        Intrinsics.checkNotNullExpressionValue(enterpriseDeviceManager, "getInstance(context)");
        return enterpriseDeviceManager;
    }

    public final KioskMode kioskMode() {
        return getEnterpriseDeviceManager().getKioskMode();
    }

    public final LocationPolicy locationPolicy() {
        return getEnterpriseDeviceManager().getLocationPolicy();
    }

    public final MultiUserManager multiUserManager() {
        return getEnterpriseDeviceManager().getMultiUserManager();
    }

    public final PhoneRestrictionPolicy phoneRestrictionPolicy() {
        return getEnterpriseDeviceManager().getPhoneRestrictionPolicy();
    }

    public final RestrictionPolicy restrictionPolicy() {
        return getEnterpriseDeviceManager().getRestrictionPolicy();
    }

    public final WifiPolicy wifiPolicy() {
        return getEnterpriseDeviceManager().getWifiPolicy();
    }
}
