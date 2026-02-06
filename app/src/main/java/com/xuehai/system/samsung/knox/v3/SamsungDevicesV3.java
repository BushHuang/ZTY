package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import com.xuehai.system.common.AndroidDevice;
import com.xuehai.system.common.policies.IApplicationPolicy;
import com.xuehai.system.common.policies.IDateTimePolicy;
import com.xuehai.system.common.policies.IDevicePolicy;
import com.xuehai.system.common.policies.IFirewallPolicy;
import com.xuehai.system.common.policies.ILicensePolicy;
import com.xuehai.system.common.policies.ILocationPolicy;
import com.xuehai.system.common.policies.IRestrictionPolicy;
import com.xuehai.system.common.policies.ISettingPolicy;
import com.xuehai.system.common.policies.IWifiPolicy;
import com.xuehai.system.samsung.knox.v3.custom.p200.ApplicationPolicySamsungP200;
import com.xuehai.system.samsung.knox.v3.custom.p200.DevicePolicySamsungP200;
import com.xuehai.system.samsung.knox.v3.custom.p200.LicensePolicySamsungP200;
import com.xuehai.system.samsung.knox.v3.custom.p200.RestrictionPolicySamsungP200;
import com.xuehai.system.samsung.knox.v3.custom.p200.SettingPolicySamsungP200;
import com.xuehai.system.samsung.knox.v3.custom.p200.WifiPolicySamsungP200;
import com.xuehai.system.samsung.knox.v3.custom.p615.ApplicationPolicySamsungP615;
import com.xuehai.system.samsung.knox.v3.custom.p615.DevicePolicySamsungP615;
import com.xuehai.system.samsung.knox.v3.custom.p615.SettingPolicySamsungP615;
import com.xuehai.system.samsung.knox.v3.custom.p620.ApplicationPolicySamsungP620;
import com.xuehai.system.samsung.knox.v3.custom.p620.DevicePolicySamsungP620;
import com.xuehai.system.samsung.knox.v3.custom.p620.FirewallPolicySamsungP620;
import com.xuehai.system.samsung.knox.v3.custom.p620.LocationPolicySamsungP620;
import com.xuehai.system.samsung.knox.v3.custom.p620.RestrictionPolicySamsungP620;
import com.xuehai.system.samsung.knox.v3.custom.p620.SettingPolicySamsungP620;
import com.xuehai.system.samsung.knox.v3.custom.p620.WifiPolicySamsungP620;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3;", "Lcom/xuehai/system/common/AndroidDevice;", "()V", "OTHER", "SM_P200", "SM_P615C", "SM_P620", "Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3$OTHER;", "Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3$SM_P200;", "Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3$SM_P615C;", "Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3$SM_P620;", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class SamsungDevicesV3 implements AndroidDevice {

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3$OTHER;", "Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OTHER extends SamsungDevicesV3 {
        private final Context context;

        public OTHER(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicySamsungV3(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicySamsungV3(this.context);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicySamsungV3(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicySamsungV3(this.context);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicySamsungV3(this.context);
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicySamsungV3(this.context);
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicySamsungV3(this.context);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicySamsungV3(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicySamsungV3(this.context);
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3$SM_P200;", "Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SM_P200 extends SamsungDevicesV3 {
        private final Context context;

        public SM_P200(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicySamsungP200(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicySamsungV3(this.context);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicySamsungP200(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicySamsungV3(this.context);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicySamsungP200(this.context);
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicySamsungV3(this.context);
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicySamsungP200(this.context);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicySamsungP200(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicySamsungP200(this.context);
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3$SM_P615C;", "Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SM_P615C extends SamsungDevicesV3 {
        private final Context context;

        public SM_P615C(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicySamsungP615(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicySamsungV3(this.context);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicySamsungP615(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicySamsungV3(this.context);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicySamsungP200(this.context);
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicySamsungV3(this.context);
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicySamsungP200(this.context);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicySamsungP615(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicySamsungP200(this.context);
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3$SM_P620;", "Lcom/xuehai/system/samsung/knox/v3/SamsungDevicesV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SM_P620 extends SamsungDevicesV3 {
        private final Context context;

        public SM_P620(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicySamsungP620(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicySamsungV3(this.context);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicySamsungP620(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicySamsungP620(this.context);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicySamsungP200(this.context);
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicySamsungP620(this.context);
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicySamsungP620(this.context);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicySamsungP620(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicySamsungP620(this.context);
        }
    }

    private SamsungDevicesV3() {
    }

    public SamsungDevicesV3(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
