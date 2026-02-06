package com.xuehai.launcher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.util.ZtyFlagManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0004H\u0002J\b\u0010\n\u001a\u00020\u0004H\u0002¨\u0006\f"}, d2 = {"Lcom/xuehai/launcher/receiver/ScreenReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "screenOff", "userPresent", "Companion", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ScreenReceiver extends BroadcastReceiver {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/xuehai/launcher/receiver/ScreenReceiver$Companion;", "", "()V", "createIntentFilter", "Landroid/content/IntentFilter;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IntentFilter createIntentFilter() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            return intentFilter;
        }
    }

    private static final void m169onReceive$lambda1(Intent intent, ScreenReceiver screenReceiver) {
        String action;
        Intrinsics.checkNotNullParameter(screenReceiver, "this$0");
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        if (Intrinsics.areEqual("android.intent.action.SCREEN_OFF", action)) {
            screenReceiver.screenOff();
        } else if (Intrinsics.areEqual("android.intent.action.USER_PRESENT", action)) {
            screenReceiver.userPresent();
        }
    }

    private final void screenOff() {
        ZtyFlagManager.INSTANCE.stopMarkErrorTask();
    }

    private final void userPresent() {
    }

    @Override
    public void onReceive(Context context, final Intent intent) {
        ThreadPlugins.runInUIThread$default(new Runnable() {
            @Override
            public final void run() {
                ScreenReceiver.m169onReceive$lambda1(intent, this);
            }
        }, 0L, 2, null);
    }
}
