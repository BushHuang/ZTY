package com.xuehai.system.huawei_c5;

import android.content.ComponentName;
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
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/xuehai/system/huawei_c5/HuaWeiHEMDevices;", "Lcom/xuehai/system/common/AndroidDevice;", "()V", "HuaWeiHEM", "OTHER", "Lcom/xuehai/system/huawei_c5/HuaWeiHEMDevices$OTHER;", "Lcom/xuehai/system/huawei_c5/HuaWeiHEMDevices$HuaWeiHEM;", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class HuaWeiHEMDevices implements AndroidDevice {

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/xuehai/system/huawei_c5/HuaWeiHEMDevices$HuaWeiHEM;", "Lcom/xuehai/system/huawei_c5/HuaWeiHEMDevices;", "context", "Landroid/content/Context;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/Context;Landroid/content/ComponentName;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class HuaWeiHEM extends HuaWeiHEMDevices {
        private final ComponentName componentName;
        private final Context context;

        public HuaWeiHEM(Context context, ComponentName componentName) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(componentName, "componentName");
            this.context = context;
            this.componentName = componentName;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicyHWHEM(this.context, this.componentName);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicyHWHEM(this.componentName);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicyHWHEM(this.context, this.componentName);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicyHWHEM(this.componentName);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicyHuaWeiHEM(this.context);
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicyHWHEM(this.context, this.componentName);
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicyHWHEM(this.context, this.componentName);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicyHWHEM(this.context, this.componentName);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicyHWHEM(this.componentName);
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/xuehai/system/huawei_c5/HuaWeiHEMDevices$OTHER;", "Lcom/xuehai/system/huawei_c5/HuaWeiHEMDevices;", "context", "Landroid/content/Context;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/Context;Landroid/content/ComponentName;)V", "applicationPolicy", "Lcom/xuehai/system/common/policies/IApplicationPolicy;", "dateTimePolicy", "Lcom/xuehai/system/common/policies/IDateTimePolicy;", "devicePolicy", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "firewallPolicy", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "licensePolicy", "Lcom/xuehai/system/common/policies/ILicensePolicy;", "locationPolicy", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "restrictionPolicy", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "settingPolicy", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "wifiPolicy", "Lcom/xuehai/system/common/policies/IWifiPolicy;", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OTHER extends HuaWeiHEMDevices {
        private final ComponentName componentName;
        private final Context context;

        public OTHER(Context context, ComponentName componentName) {
            super(null);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(componentName, "componentName");
            this.context = context;
            this.componentName = componentName;
        }

        @Override
        public IApplicationPolicy applicationPolicy() {
            return new ApplicationPolicyHWHEM(this.context, this.componentName);
        }

        @Override
        public IDateTimePolicy dateTimePolicy() {
            return new DateTimePolicyHWHEM(this.componentName);
        }

        @Override
        public IDevicePolicy devicePolicy() {
            return new DevicePolicyHWHEM(this.context, this.componentName);
        }

        @Override
        public IFirewallPolicy firewallPolicy() {
            return new FirewallPolicyHWHEM(this.componentName);
        }

        @Override
        public ILicensePolicy licensePolicy() {
            return new LicensePolicyHuaWeiHEM(this.context);
        }

        @Override
        public ILocationPolicy locationPolicy() {
            return new LocationPolicyHWHEM(this.context, this.componentName);
        }

        @Override
        public IRestrictionPolicy restrictionPolicy() {
            return new RestrictionPolicyHWHEM(this.context, this.componentName);
        }

        @Override
        public ISettingPolicy settingPolicy() {
            return new SettingPolicyHWHEM(this.context, this.componentName);
        }

        @Override
        public IWifiPolicy wifiPolicy() {
            return new WifiPolicyHWHEM(this.componentName);
        }
    }

    private HuaWeiHEMDevices() {
    }

    public HuaWeiHEMDevices(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
