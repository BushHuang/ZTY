package com.xuehai.system.lenovo;

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
import com.xuehai.system.lenovo.csdk.tb223fc.ApplicationPolicyLenovo223FC;
import com.xuehai.system.lenovo.csdk.tb223fc.DateTimePolicyLenovo223FC;
import com.xuehai.system.lenovo.csdk.tb223fc.DevicePolicyLenovo223FC;
import com.xuehai.system.lenovo.csdk.tb223fc.FirewallPolicyEmptyLenovo223FC;
import com.xuehai.system.lenovo.csdk.tb223fc.LocationPolicyLenovo223FC;
import com.xuehai.system.lenovo.csdk.tb223fc.RestrictionPolicyLenovo223FC;
import com.xuehai.system.lenovo.csdk.tb223fc.SettingPolicyLenovo223FC;
import com.xuehai.system.lenovo.csdk.tb223fc.WifiPolicyLenovo223FC;
import com.xuehai.system.lenovo.csdk.tb_x6c6f.ApplicationPolicyLenovoX6C6F;
import com.xuehai.system.lenovo.csdk.tb_x6c6f.DateTimePolicyLenovoX6C6F;
import com.xuehai.system.lenovo.csdk.tb_x6c6f.DevicePolicyLenovoX6C6F;
import com.xuehai.system.lenovo.csdk.tb_x6c6f.FirewallPolicyLenovoX6C6F;
import com.xuehai.system.lenovo.csdk.tb_x6c6f.LocationPolicyLenovoX6C6F;
import com.xuehai.system.lenovo.csdk.tb_x6c6f.RestrictionPolicyLenovoX6C6F;
import com.xuehai.system.lenovo.csdk.tb_x6c6f.SettingPolicyLenovoX6C6F;
import com.xuehai.system.lenovo.csdk.tb_x6c6f.WifiPolicyLenovoX6C6F;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/lenovo/LenovoDevices;", "Lcom/xuehai/system/common/AndroidDevice;", "()V", "OTHER", "TB223FC", "TB_8604F", "TB_X6C6F", "Lcom/xuehai/system/lenovo/LenovoDevices$OTHER;", "Lcom/xuehai/system/lenovo/LenovoDevices$TB_8604F;", "Lcom/xuehai/system/lenovo/LenovoDevices$TB_X6C6F;", "Lcom/xuehai/system/lenovo/LenovoDevices$TB223FC;", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class LenovoDevices implements AndroidDevice {

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/lenovo/LenovoDevices$OTHER;", "Lcom/xuehai/system/lenovo/LenovoDevices;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OTHER extends LenovoDevices {
        private final Context context;

        public OTHER(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicyLenovo(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicyLenovo(this.context);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicyLenovo(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicyLenovo(this.context);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicyLenovo();
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicyLenovo();
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicyLenovo(this.context);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicyLenovo(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicyLenovo();
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/lenovo/LenovoDevices$TB223FC;", "Lcom/xuehai/system/lenovo/LenovoDevices;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class TB223FC extends LenovoDevices {
        private final Context context;

        public TB223FC(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicyLenovo223FC(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicyLenovo223FC(this.context);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicyLenovo223FC(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicyEmptyLenovo223FC(this.context);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicyLenovo();
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicyLenovo223FC(this.context);
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicyLenovo223FC(this.context);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicyLenovo223FC(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicyLenovo223FC(this.context);
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/lenovo/LenovoDevices$TB_8604F;", "Lcom/xuehai/system/lenovo/LenovoDevices;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class TB_8604F extends LenovoDevices {
        private final Context context;

        public TB_8604F(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicyLenovo(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicyLenovo(this.context);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicyLenovo(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicyLenovo(this.context);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicyLenovo();
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicyLenovo();
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicyLenovo(this.context);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicyLenovo(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicyLenovo();
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/lenovo/LenovoDevices$TB_X6C6F;", "Lcom/xuehai/system/lenovo/LenovoDevices;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class TB_X6C6F extends LenovoDevices {
        private final Context context;

        public TB_X6C6F(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicyLenovoX6C6F(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicyLenovoX6C6F(this.context);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicyLenovoX6C6F(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicyLenovoX6C6F(this.context);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicyLenovo();
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicyLenovoX6C6F(this.context);
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicyLenovoX6C6F(this.context);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicyLenovoX6C6F(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicyLenovoX6C6F(this.context);
        }
    }

    private LenovoDevices() {
    }

    public LenovoDevices(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
