package com.xuehai.system.samsung.knox;

import android.content.Context;
import com.xuehai.system.common.AndroidDevice;
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
import com.xuehai.system.common.util.Command;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004¨\u0006\u0005"}, d2 = {"Lcom/xuehai/system/samsung/knox/SamsungDevices;", "Lcom/xuehai/system/common/AndroidDevice;", "()V", "ALL", "Lcom/xuehai/system/samsung/knox/SamsungDevices$ALL;", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class SamsungDevices implements AndroidDevice {

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/samsung/knox/SamsungDevices$ALL;", "Lcom/xuehai/system/samsung/knox/SamsungDevices;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ALL extends SamsungDevices {
        private final Context context;

        public ALL(Context context) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicySamsung(this.context);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicySamsung(this.context);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicySamsung(this.context);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return Command.isRoot() ? new FirewallPolicyRoot(this.context) : new FirewallPolicySamsung(this.context);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicySamsung(this.context);
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicySamsung(this.context);
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicySamsung(this.context);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicySamsung(this.context);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicySamsung(this.context);
        }
    }

    private SamsungDevices() {
    }

    public SamsungDevices(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
