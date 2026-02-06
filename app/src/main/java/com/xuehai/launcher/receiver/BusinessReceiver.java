package com.xuehai.launcher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.util.ZtyFlagManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/receiver/BusinessReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BusinessReceiver extends BroadcastReceiver {

    public static final Companion INSTANCE = new Companion(null);
    private static final String ztyErrorAction = "com.xuehai.launcher.mark_zty_error";
    private static final String ztyNormalAction = "com.xuehai.launcher.mark_zty_normally";

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/receiver/BusinessReceiver$Companion;", "", "()V", "ztyErrorAction", "", "ztyNormalAction", "createIntentFilter", "Landroid/content/IntentFilter;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IntentFilter createIntentFilter() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.xuehai.launcher.mark_zty_error");
            intentFilter.addAction("com.xuehai.launcher.mark_zty_normally");
            return intentFilter;
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        int iHashCode = action.hashCode();
        if (iHashCode == 1081462283) {
            if (action.equals("com.xuehai.launcher.mark_zty_error")) {
                MyLog.e("Error[MDM]", "智通平台发生异常");
                ZtyFlagManager.INSTANCE.markZtyError(true);
                return;
            }
            return;
        }
        if (iHashCode == 1428819409 && action.equals("com.xuehai.launcher.mark_zty_normally")) {
            MyLog.i("Error[MDM]", "智通平台当前正常");
            ZtyFlagManager.INSTANCE.markZtyError(false);
        }
    }
}
