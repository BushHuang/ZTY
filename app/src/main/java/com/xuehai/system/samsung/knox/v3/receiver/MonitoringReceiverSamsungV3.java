package com.xuehai.system.samsung.knox.v3.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xuehai.system.common.receiver.MonitoringReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/receiver/MonitoringReceiverSamsungV3;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MonitoringReceiverSamsungV3 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        String action = intent != null ? intent.getAction() : null;
        if (action != null && action.hashCode() == -319562002 && action.equals("com.samsung.android.knox.intent.action.APPLICATION_FOCUS_CHANGE")) {
            MonitoringReceiver.INSTANCE.onApplicationFocusChange(context, intent.getStringExtra("com.samsung.android.knox.intent.extra.APPLICATION_FOCUS_COMPONENT_NAME"), Intrinsics.areEqual(intent.getStringExtra("com.samsung.android.knox.intent.extra.APPLICATION_FOCUS_STATUS"), "gained"));
        }
    }
}
