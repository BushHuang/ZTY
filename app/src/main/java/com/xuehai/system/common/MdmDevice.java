package com.xuehai.system.common;

import android.content.Context;
import com.xuehai.system.common.compat.ApplicationPolicyDefault;
import com.xuehai.system.common.compat.DateTimePolicyDefault;
import com.xuehai.system.common.compat.DevicePolicyDefault;
import com.xuehai.system.common.compat.FirewallPolicyDefault;
import com.xuehai.system.common.compat.LicensePolicyDefault;
import com.xuehai.system.common.compat.LocationPolicyDefault;
import com.xuehai.system.common.compat.RestrictionPolicyDefault;
import com.xuehai.system.common.compat.SettingPolicyDefault;
import com.xuehai.system.common.compat.WifiPolicyDefault;
import com.xuehai.system.common.compat.root.ApplicationPolicyRoot;
import com.xuehai.system.common.compat.root.FirewallPolicyRoot;
import com.xuehai.system.common.policies.IApplicationPolicy;
import com.xuehai.system.common.policies.IDateTimePolicy;
import com.xuehai.system.common.policies.IDevicePolicy;
import com.xuehai.system.common.policies.IFirewallPolicy;
import com.xuehai.system.common.policies.ILicensePolicy;
import com.xuehai.system.common.policies.ILocationPolicy;
import com.xuehai.system.common.policies.IRestrictionPolicy;
import com.xuehai.system.common.policies.ISettingPolicy;
import com.xuehai.system.common.policies.IWifiPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/xuehai/system/common/MdmDevice;", "Lcom/xuehai/system/common/AndroidDevice;", "()V", "Root", "Standard", "Lcom/xuehai/system/common/MdmDevice$Standard;", "Lcom/xuehai/system/common/MdmDevice$Root;", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class MdmDevice implements AndroidDevice {

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/common/MdmDevice$Root;", "Lcom/xuehai/system/common/MdmDevice;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Root extends MdmDevice {
        private final Context context;

        public Root(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicyRoot(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicyDefault();
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicyDefault(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicyRoot(this.context);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicyDefault();
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicyDefault();
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicyDefault();
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicyDefault(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicyDefault();
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/common/MdmDevice$Standard;", "Lcom/xuehai/system/common/MdmDevice;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Standard extends MdmDevice {
        private final Context context;

        public Standard(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicyDefault(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicyDefault();
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicyDefault(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicyDefault();
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicyDefault();
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicyDefault();
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicyDefault();
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicyDefault(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicyDefault();
        }
    }

    private MdmDevice() {
    }

    public MdmDevice(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
