package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import com.xuehai.system.common.compat.LocationPolicyDefault;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/LocationPolicySamsungV3;", "Lcom/xuehai/system/common/compat/LocationPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "startGPS", "", "start", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LocationPolicySamsungV3 extends LocationPolicyDefault {
    private final SamsungKnoxV3 policy;

    public LocationPolicySamsungV3(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = SamsungKnoxV3.INSTANCE.getInstance(context);
    }

    @Override
    public boolean startGPS(boolean start) {
        return this.policy.locationPolicy().startGPS(start);
    }
}
