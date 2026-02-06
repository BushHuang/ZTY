package com.xuehai.launcher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import com.xuehai.launcher.common.log.MyLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/receiver/InputMethodReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InputMethodReceiver extends BroadcastReceiver {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/xuehai/launcher/receiver/InputMethodReceiver$Companion;", "", "()V", "createIntentFilter", "Landroid/content/IntentFilter;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IntentFilter createIntentFilter() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.INPUT_METHOD_CHANGED");
            return intentFilter;
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null || (action = intent.getAction()) == null || !Intrinsics.areEqual("android.intent.action.INPUT_METHOD_CHANGED", action)) {
            return;
        }
        try {
            MyLog.i("[MDM]", "输入法发生变化");
            if (context != null) {
                String string = Settings.Secure.getString(context.getContentResolver(), "default_input_method");
                MyLog.i("[MDM]", "当前输入法为" + string);
                Intrinsics.checkNotNullExpressionValue(string, "defaultIme");
                if (StringsKt.contains$default((CharSequence) string, (CharSequence) "com.xh.ime", false, 2, (Object) null)) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
