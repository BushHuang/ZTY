package com.xuehai.system.samsung.knox.v3.custom.p615;

import android.content.Context;
import com.xuehai.system.common.util.MdmUtil;
import com.xuehai.system.samsung.knox.v3.custom.p200.DevicePolicySamsungP200;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016¨\u0006\u000e"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p615/DevicePolicySamsungP615;", "Lcom/xuehai/system/samsung/knox/v3/custom/p200/DevicePolicySamsungP200;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "controlShowDeprecatedTarget", "", "show", "debug", "", "boolean", "getImei", "", "getSerialNumber", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DevicePolicySamsungP615 extends DevicePolicySamsungP200 {
    public DevicePolicySamsungP615(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public boolean controlShowDeprecatedTarget(boolean show) {
        samsungDevice().controlShowDeprecatedTarget(show);
        return true;
    }

    @Override
    public void debug(boolean z) {
    }

    @Override
    public String getImei() {
        String imei = samsungDevice().getIMEI();
        return imei == null ? super.getImei() : imei;
    }

    @Override
    public String getSerialNumber() {
        String serialNumber = super.getSerialNumber();
        if (!(serialNumber.length() == 0) && !MdmUtil.INSTANCE.isAllCharsSame(serialNumber)) {
            return serialNumber;
        }
        try {
            String sn = samsungDevice().getSN();
            Intrinsics.checkNotNullExpressionValue(sn, "{\n                    sa…ce().sn\n                }");
            return sn;
        } catch (Exception unused) {
            return MdmUtil.INSTANCE.getUUID(getContext());
        }
    }
}
