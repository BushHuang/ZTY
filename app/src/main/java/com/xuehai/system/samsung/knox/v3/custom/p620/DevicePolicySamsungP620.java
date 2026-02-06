package com.xuehai.system.samsung.knox.v3.custom.p620;

import android.content.Context;
import com.b2b.rom.SamsungDevice;
import com.xuehai.system.common.util.MdmUtil;
import com.xuehai.system.samsung.knox.v3.DevicePolicySamsungV3;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p620/DevicePolicySamsungP620;", "Lcom/xuehai/system/samsung/knox/v3/DevicePolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "samsungDevice", "Lcom/b2b/rom/SamsungDevice;", "controlShowDeprecatedTarget", "", "show", "getDeviceMacAddress", "", "getImei", "getSerialNumber", "reboot", "", "setSpenPointImageState", "state", "shoutdown", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DevicePolicySamsungP620 extends DevicePolicySamsungV3 {
    private final SamsungDevice samsungDevice;

    public DevicePolicySamsungP620(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        SamsungDevice samsungDevice = SamsungDevice.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(samsungDevice, "getInstance(context)");
        this.samsungDevice = samsungDevice;
    }

    @Override
    public boolean controlShowDeprecatedTarget(boolean show) {
        this.samsungDevice.controlShowDeprecatedTarget(!show);
        return true;
    }

    @Override
    public String getDeviceMacAddress() {
        String macAddress = this.samsungDevice.getMacAddress();
        Intrinsics.checkNotNullExpressionValue(macAddress, "samsungDevice.macAddress");
        return macAddress;
    }

    @Override
    public String getImei() {
        String imei = this.samsungDevice.getIMEI();
        return imei == null ? super.getImei() : imei;
    }

    @Override
    public String getSerialNumber() {
        String serialNumber = super.getSerialNumber();
        if (!(serialNumber.length() == 0) && !MdmUtil.INSTANCE.isAllCharsSame(serialNumber)) {
            return serialNumber;
        }
        try {
            String sn = this.samsungDevice.getSN();
            Intrinsics.checkNotNullExpressionValue(sn, "{\n                    sa…vice.sn\n                }");
            return sn;
        } catch (Exception unused) {
            return MdmUtil.INSTANCE.getUUID(getContext());
        }
    }

    @Override
    public void reboot() {
        super.reboot();
    }

    @Override
    public boolean setSpenPointImageState(boolean state) {
        return super.setSpenPointImageState(state);
    }

    @Override
    public void shoutdown() {
        super.shoutdown();
    }
}
