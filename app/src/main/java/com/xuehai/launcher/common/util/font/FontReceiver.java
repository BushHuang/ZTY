package com.xuehai.launcher.common.util.font;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.xuehai.launcher.common.log.MyLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/common/util/font/FontReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "compat_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FontReceiver extends BroadcastReceiver {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xuehai/launcher/common/util/font/FontReceiver$Companion;", "", "()V", "register", "", "context", "Landroid/content/Context;", "compat_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void register(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.registerReceiver(new FontReceiver(), new IntentFilter("com.xuehai.action.DOWNLOAD_FONT"));
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "com.xuehai.action.DOWNLOAD_FONT")) {
            MyLog.i("[MDM]", "收到字体下载请求, 进行转发");
            intent.setAction("com.xh.zhitongyun.action.download_font");
            intent.setPackage(null);
            if (context != null) {
                context.sendBroadcast(intent);
            }
        }
    }
}
