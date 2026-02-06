package com.xuehai.system.lenovo;

import android.app.mia.MiaMdmPolicyManager;
import android.content.Context;
import com.xuehai.system.common.compat.DateTimePolicyDefault;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/lenovo/DateTimePolicyLenovo;", "Lcom/xuehai/system/common/compat/DateTimePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Landroid/app/mia/MiaMdmPolicyManager;", "setSystemTime", "", "date", "Ljava/util/Date;", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DateTimePolicyLenovo extends DateTimePolicyDefault {
    private final MiaMdmPolicyManager policy;

    public DateTimePolicyLenovo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = new MiaMdmPolicyManager(context);
    }

    @Override
    public boolean setSystemTime(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.policy.updateSystemTime(new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss", Locale.getDefault()).format(date));
    }
}
