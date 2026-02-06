package com.xuehai.system.mdm.proxy;

import android.content.Context;
import com.xuehai.mdm.config.DeviceSupport;
import com.xuehai.system.common.DeviceProvider;
import com.xuehai.system.huawei_c5.HuaWeiHEMDeviceProvider;
import com.xuehai.system.huawei_c5.HuaWeiHEMDevices;
import com.xuehai.system.lenovo.LenovoDeviceProvider;
import com.xuehai.system.mdm.device.DeviceManager;
import com.xuehai.system.samsung.knox.SamsungDeviceProvider;
import com.xuehai.system.samsung.knox.v3.SamsungDeviceProviderV3;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,¨\u0006-"}, d2 = {"Lcom/xuehai/system/mdm/proxy/PolicyPlugins;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicyProxy", "Lcom/xuehai/system/mdm/proxy/ApplicationPolicyProxy;", "getApplicationPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/ApplicationPolicyProxy;", "dateTimePolicyProxy", "Lcom/xuehai/system/mdm/proxy/DateTimePolicyProxy;", "getDateTimePolicyProxy", "()Lcom/xuehai/system/mdm/proxy/DateTimePolicyProxy;", "devicePolicyProxy", "Lcom/xuehai/system/mdm/proxy/DevicePolicyProxy;", "getDevicePolicyProxy", "()Lcom/xuehai/system/mdm/proxy/DevicePolicyProxy;", "deviceSupportCompat", "Lcom/xuehai/mdm/config/DeviceSupport;", "getDeviceSupportCompat", "()Lcom/xuehai/mdm/config/DeviceSupport;", "firewallPolicyProxy", "Lcom/xuehai/system/mdm/proxy/FirewallPolicyProxy;", "getFirewallPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/FirewallPolicyProxy;", "licensePolicyProxy", "Lcom/xuehai/system/mdm/proxy/LicensePolicyProxy;", "getLicensePolicyProxy", "()Lcom/xuehai/system/mdm/proxy/LicensePolicyProxy;", "locationPolicyProxy", "Lcom/xuehai/system/mdm/proxy/LocationPolicyProxy;", "getLocationPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/LocationPolicyProxy;", "restrictionPolicyProxy", "Lcom/xuehai/system/mdm/proxy/RestrictionPolicyProxy;", "getRestrictionPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/RestrictionPolicyProxy;", "settingPolicyProxy", "Lcom/xuehai/system/mdm/proxy/SettingPolicyProxy;", "getSettingPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/SettingPolicyProxy;", "wifiPolicyProxy", "Lcom/xuehai/system/mdm/proxy/WifiPolicyProxy;", "getWifiPolicyProxy", "()Lcom/xuehai/system/mdm/proxy/WifiPolicyProxy;", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PolicyPlugins {
    private final ApplicationPolicyProxy applicationPolicyProxy;
    private final DateTimePolicyProxy dateTimePolicyProxy;
    private final DevicePolicyProxy devicePolicyProxy;
    private final DeviceSupport deviceSupportCompat;
    private final FirewallPolicyProxy firewallPolicyProxy;
    private final LicensePolicyProxy licensePolicyProxy;
    private final LocationPolicyProxy locationPolicyProxy;
    private final RestrictionPolicyProxy restrictionPolicyProxy;
    private final SettingPolicyProxy settingPolicyProxy;
    private final WifiPolicyProxy wifiPolicyProxy;

    public PolicyPlugins(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceSupport deviceSupport = new DeviceSupport(context);
        this.deviceSupportCompat = deviceSupport;
        HuaWeiHEMDevices device$default = deviceSupport.isSamsungSupport() ? SamsungDeviceProvider.getDevice$default(new SamsungDeviceProvider(context), null, 1, null) : this.deviceSupportCompat.isSamsungV3Support() ? SamsungDeviceProviderV3.getDevice$default(new SamsungDeviceProviderV3(context), null, 1, null) : this.deviceSupportCompat.isLenovoSupport() ? LenovoDeviceProvider.getDevice$default(new LenovoDeviceProvider(context), null, 1, null) : this.deviceSupportCompat.isHuaWeiHEMSupport() ? HuaWeiHEMDeviceProvider.getDevice$default(new HuaWeiHEMDeviceProvider(context, DeviceManager.getAdminReceiver(context)), null, 1, null) : new DeviceProvider(context).getDevice();
        this.applicationPolicyProxy = new ApplicationPolicyProxy(device$default.applicationPolicy());
        this.dateTimePolicyProxy = new DateTimePolicyProxy(device$default.dateTimePolicy());
        this.devicePolicyProxy = new DevicePolicyProxy(device$default.devicePolicy());
        this.firewallPolicyProxy = new FirewallPolicyProxy(device$default.firewallPolicy());
        this.licensePolicyProxy = new LicensePolicyProxy(device$default.licensePolicy());
        this.locationPolicyProxy = new LocationPolicyProxy(device$default.locationPolicy());
        this.restrictionPolicyProxy = new RestrictionPolicyProxy(device$default.restrictionPolicy());
        this.settingPolicyProxy = new SettingPolicyProxy(device$default.settingPolicy());
        this.wifiPolicyProxy = new WifiPolicyProxy(device$default.wifiPolicy());
    }

    public final ApplicationPolicyProxy getApplicationPolicyProxy() {
        return this.applicationPolicyProxy;
    }

    public final DateTimePolicyProxy getDateTimePolicyProxy() {
        return this.dateTimePolicyProxy;
    }

    public final DevicePolicyProxy getDevicePolicyProxy() {
        return this.devicePolicyProxy;
    }

    public final DeviceSupport getDeviceSupportCompat() {
        return this.deviceSupportCompat;
    }

    public final FirewallPolicyProxy getFirewallPolicyProxy() {
        return this.firewallPolicyProxy;
    }

    public final LicensePolicyProxy getLicensePolicyProxy() {
        return this.licensePolicyProxy;
    }

    public final LocationPolicyProxy getLocationPolicyProxy() {
        return this.locationPolicyProxy;
    }

    public final RestrictionPolicyProxy getRestrictionPolicyProxy() {
        return this.restrictionPolicyProxy;
    }

    public final SettingPolicyProxy getSettingPolicyProxy() {
        return this.settingPolicyProxy;
    }

    public final WifiPolicyProxy getWifiPolicyProxy() {
        return this.wifiPolicyProxy;
    }
}
