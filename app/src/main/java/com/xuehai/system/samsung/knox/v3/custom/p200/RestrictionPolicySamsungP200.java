package com.xuehai.system.samsung.knox.v3.custom.p200;

import android.content.Context;
import com.b2b.rom.ISamsungDevice;
import com.xuehai.system.samsung.knox.v3.RestrictionPolicySamsungV3;
import com.xuehai.system.samsung.knox.v3.custom.SamsungCustomSDK;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p200/RestrictionPolicySamsungP200;", "Lcom/xuehai/system/samsung/knox/v3/RestrictionPolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "disableHomeLongFunc", "", "samsungDevice", "Lcom/b2b/rom/ISamsungDevice;", "setInputMethod", "", "inputMethodClassName", "", "setMTPEnabled", "enable", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RestrictionPolicySamsungP200 extends RestrictionPolicySamsungV3 {
    private final Context context;

    public RestrictionPolicySamsungP200(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override
    public void disableHomeLongFunc() {
        samsungDevice().disableHomeLongFunc();
    }

    public final ISamsungDevice samsungDevice() {
        return SamsungCustomSDK.INSTANCE.samsungDevice(this.context);
    }

    @Override
    public boolean setInputMethod(String inputMethodClassName) {
        Intrinsics.checkNotNullParameter(inputMethodClassName, "inputMethodClassName");
        return samsungDevice().setInputMethod(inputMethodClassName);
    }

    @Override
    public boolean setMTPEnabled(boolean enable) {
        samsungDevice().setMTPEnabled(enable);
        return super.setMTPEnabled(enable);
    }
}
