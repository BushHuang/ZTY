package com.xh.xhcore.common.hotfix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.xh.xhcore.common.http.strategy.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/hotfix/PatchUpdateBroadcastUtil;", "", "()V", "ACTION_PATCH_UPDATE_SUFFIX", "", "getPatchUpdateAction", "registerPatchUpdateReceiver", "", "context", "Landroid/content/Context;", "send", "packageName", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PatchUpdateBroadcastUtil {
    public static final PatchUpdateBroadcastUtil INSTANCE = new PatchUpdateBroadcastUtil();
    private static final String ACTION_PATCH_UPDATE_SUFFIX = "ACTION_PATCH_UPDATE";

    private PatchUpdateBroadcastUtil() {
    }

    private final String getPatchUpdateAction() {
        return ACTION_PATCH_UPDATE_SUFFIX;
    }

    @JvmStatic
    public static final void registerPatchUpdateReceiver(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(INSTANCE.getPatchUpdateAction());
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context2, Intent intent) {
                Intrinsics.checkNotNullParameter(context2, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                LogUtils.INSTANCE.d("receive patch update broadcast");
                TinkerPatchUtil.getTinkerPatchTaskHandler().startNow();
            }
        }, intentFilter);
    }

    @JvmStatic
    public static final void send(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intent intent = new Intent(INSTANCE.getPatchUpdateAction());
        intent.setPackage(packageName);
        context.sendBroadcast(intent);
    }
}
