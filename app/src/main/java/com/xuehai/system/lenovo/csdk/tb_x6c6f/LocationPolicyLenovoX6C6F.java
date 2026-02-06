package com.xuehai.system.lenovo.csdk.tb_x6c6f;

import android.app.csdk.CSDKManager;
import android.content.Context;
import com.xuehai.system.common.compat.LocationPolicyDefault;
import com.xuehai.system.lenovo.csdk.LenovoCSDK;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/xuehai/system/lenovo/csdk/tb_x6c6f/LocationPolicyLenovoX6C6F;", "Lcom/xuehai/system/common/compat/LocationPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "csdkManager", "Landroid/app/csdk/CSDKManager;", "startGPS", "", "start", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LocationPolicyLenovoX6C6F extends LocationPolicyDefault {
    private final CSDKManager csdkManager;

    public LocationPolicyLenovoX6C6F(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.csdkManager = LenovoCSDK.INSTANCE.getCSDKManager(context);
    }

    @Override
    public boolean startGPS(boolean start) {
        return this.csdkManager.enableLocationV3(start);
    }
}
