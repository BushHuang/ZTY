package com.xuehai.system.lenovo.csdk;

import android.app.csdk.CSDKManager;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/xuehai/system/lenovo/csdk/LenovoCSDK;", "", "()V", "csdkManager", "Landroid/app/csdk/CSDKManager;", "getCSDKManager", "context", "Landroid/content/Context;", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LenovoCSDK {
    public static final LenovoCSDK INSTANCE = new LenovoCSDK();
    private static CSDKManager csdkManager;

    private LenovoCSDK() {
    }

    public final CSDKManager getCSDKManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CSDKManager cSDKManager = csdkManager;
        if (cSDKManager != null) {
            return cSDKManager;
        }
        CSDKManager cSDKManager2 = new CSDKManager(context);
        csdkManager = cSDKManager2;
        cSDKManager2.enableSmartAutoRotation(false);
        cSDKManager2.enablePermissionDialogButton(true);
        return cSDKManager2;
    }
}
