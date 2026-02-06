package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import com.xuehai.system.common.compat.DateTimePolicyDefault;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/DateTimePolicySamsungV3;", "Lcom/xuehai/system/common/compat/DateTimePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "getAutomaticTime", "", "setAutomaticTime", "enable", "setDateTimeChangeEnabled", "setTimeZone", "timeZoneId", "", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DateTimePolicySamsungV3 extends DateTimePolicyDefault {
    private final SamsungKnoxV3 policy;

    public DateTimePolicySamsungV3(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = SamsungKnoxV3.INSTANCE.getInstance(context);
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
    public boolean setDateTimeChangeEnabled(boolean enable) {
        return this.policy.dateTimePolicy().setDateTimeChangeEnabled(enable);
    }

    @Override
    public boolean setTimeZone(String timeZoneId) {
        Intrinsics.checkNotNullParameter(timeZoneId, "timeZoneId");
        return this.policy.dateTimePolicy().setTimeZone(timeZoneId);
    }
}
