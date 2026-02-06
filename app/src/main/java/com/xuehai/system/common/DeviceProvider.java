package com.xuehai.system.common;

import android.content.Context;
import com.xuehai.system.common.MdmDevice;
import com.xuehai.system.common.util.Command;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/xuehai/system/common/DeviceProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getDevice", "Lcom/xuehai/system/common/AndroidDevice;", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceProvider {
    private final Context context;

    public DeviceProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final AndroidDevice getDevice() {
        return Command.isRoot() ? new MdmDevice.Root(this.context) : new MdmDevice.Standard(this.context);
    }
}
