package com.xuehai.system.samsung.knox;

import android.content.Context;
import com.xuehai.system.common.compat.DevicePolicyDefault;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/xuehai/system/samsung/knox/DevicePolicySamsung;", "Lcom/xuehai/system/common/compat/DevicePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/SamsungPolicy;", "getSerialNumber", "", "getSimSerialNumber", "setAdminRemovable", "", "removable", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DevicePolicySamsung extends DevicePolicyDefault {
    private final SamsungPolicy policy;

    public DevicePolicySamsung(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = new SamsungPolicy(context);
    }

    @Override
    public String getSerialNumber() {
        String serialNumber = this.policy.deviceInventory().getSerialNumber();
        return serialNumber == null ? super.getSerialNumber() : serialNumber;
    }

    @Override
    public String getSimSerialNumber() {
        try {
            String str = this.policy.deviceInventory().getLastSimChangeInfo().currentSimInfo.serialNumber;
            Intrinsics.checkNotNullExpressionValue(str, "policy.deviceInventory()…rrentSimInfo.serialNumber");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return super.getSimSerialNumber();
        }
    }

    @Override
    public boolean setAdminRemovable(boolean removable) {
        return this.policy.getEnterpriseDeviceManager().setAdminRemovable(removable);
    }
}
