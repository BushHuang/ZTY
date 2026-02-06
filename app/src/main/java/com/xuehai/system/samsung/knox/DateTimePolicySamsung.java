package com.xuehai.system.samsung.knox;

import android.content.Context;
import com.xuehai.system.common.compat.DateTimePolicyDefault;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/xuehai/system/samsung/knox/DateTimePolicySamsung;", "Lcom/xuehai/system/common/compat/DateTimePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/SamsungPolicy;", "getAutomaticTime", "", "setAutomaticTime", "enable", "setTimeZone", "timeZoneId", "", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DateTimePolicySamsung extends DateTimePolicyDefault {
    private final SamsungPolicy policy;

    public DateTimePolicySamsung(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = new SamsungPolicy(context);
    }

    @Override
    public boolean getAutomaticTime() {
        return this.policy.dateTimePolicy().getAutomaticTime();
    }

    @Override
    public boolean setAutomaticTime(boolean enable) {
        return this.policy.dateTimePolicy().setAutomaticTime(enable);
    }

    @Override
    public boolean setTimeZone(String timeZoneId) {
        Intrinsics.checkNotNullParameter(timeZoneId, "timeZoneId");
        return this.policy.dateTimePolicy().setTimeZone(timeZoneId);
    }
}
