package com.xuehai.system.common.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u001c\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b¨\u0006\u0010"}, d2 = {"Lcom/xuehai/system/common/receiver/MonitoringReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onApplicationFocusChange", "", "componentName", "Landroid/content/ComponentName;", "gained", "", "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "register", "Companion", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class MonitoringReceiver extends BroadcastReceiver {
    private static final String ACTION_APPLICATION_FOCUS_CHANGE = "com.xuehai.launcher.intent.action.ACTION_APPLICATION_FOCUS_CHANGE";

    public static final Companion INSTANCE = new Companion(null);
    public static final String EXTRA_APPLICATION_FOCUS_COMPONENT_NAME = "com.xuehai.launcher.intent.action.extra.EXTRA_APPLICATION_FOCUS_COMPONENT_NAME";
    public static final String EXTRA_APPLICATION_FOCUS_STATUS = "com.xuehai.launcher.intent.action.extra.EXTRA_APPLICATION_FOCUS_STATUS";

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/xuehai/system/common/receiver/MonitoringReceiver$Companion;", "", "()V", "ACTION_APPLICATION_FOCUS_CHANGE", "", "EXTRA_APPLICATION_FOCUS_COMPONENT_NAME", "EXTRA_APPLICATION_FOCUS_STATUS", "createIntentFilter", "Landroid/content/IntentFilter;", "onApplicationFocusChange", "", "context", "Landroid/content/Context;", "componentName", "gained", "", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final IntentFilter createIntentFilter() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.xuehai.launcher.intent.action.ACTION_APPLICATION_FOCUS_CHANGE");
            return intentFilter;
        }

        public final void onApplicationFocusChange(Context context, String componentName, boolean gained) {
            Intrinsics.checkNotNullParameter(context, "context");
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
            Intent intent = new Intent("com.xuehai.launcher.intent.action.ACTION_APPLICATION_FOCUS_CHANGE");
            intent.putExtra("com.xuehai.launcher.intent.action.extra.EXTRA_APPLICATION_FOCUS_COMPONENT_NAME", componentName);
            intent.putExtra("com.xuehai.launcher.intent.action.extra.EXTRA_APPLICATION_FOCUS_STATUS", gained);
            localBroadcastManager.sendBroadcast(intent);
        }
    }

    public abstract void onApplicationFocusChange(ComponentName componentName, boolean gained);

    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName componentNameUnflattenFromString;
        String action = intent != null ? intent.getAction() : null;
        if (action != null && action.hashCode() == -1967774668 && action.equals("com.xuehai.launcher.intent.action.ACTION_APPLICATION_FOCUS_CHANGE")) {
            try {
                String stringExtra = intent.getStringExtra("com.xuehai.launcher.intent.action.extra.EXTRA_APPLICATION_FOCUS_COMPONENT_NAME");
                boolean booleanExtra = intent.getBooleanExtra("com.xuehai.launcher.intent.action.extra.EXTRA_APPLICATION_FOCUS_STATUS", false);
                if (stringExtra == null || (componentNameUnflattenFromString = ComponentName.unflattenFromString(stringExtra)) == null) {
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(componentNameUnflattenFromString, "it");
                onApplicationFocusChange(componentNameUnflattenFromString, booleanExtra);
            } catch (Throwable unused) {
            }
        }
    }

    public final void register(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        LocalBroadcastManager.getInstance(context).registerReceiver(this, INSTANCE.createIntentFilter());
    }
}
