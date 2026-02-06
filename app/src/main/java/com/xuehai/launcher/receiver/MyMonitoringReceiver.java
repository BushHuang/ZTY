package com.xuehai.launcher.receiver;

import android.content.ComponentName;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.receiver.MonitoringReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/xuehai/launcher/receiver/MyMonitoringReceiver;", "Lcom/xuehai/system/common/receiver/MonitoringReceiver;", "()V", "onApplicationFocusChange", "", "componentName", "Landroid/content/ComponentName;", "gained", "", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyMonitoringReceiver extends MonitoringReceiver {
    @Override
    public void onApplicationFocusChange(ComponentName componentName, boolean gained) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        MdmLog.i("MdmLog", "onApplicationFocusChange: " + componentName + ", " + gained);
    }
}
