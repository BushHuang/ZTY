package com.xuehai.system.mdm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/mdm/MdmRouter;", "", "()V", "initGuideView", "", "context", "Landroid/content/Context;", "initSafeView", "startActivity", "intent", "Landroid/content/Intent;", "aidl_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MdmRouter {
    public final void initGuideView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        startActivity(context, new Intent("android.intent.action.VIEW", Uri.parse("mdm://com.xuehai.mdm:8080/init/guide")));
    }

    public final void initSafeView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        startActivity(context, new Intent("android.intent.action.VIEW", Uri.parse("mdm://com.xuehai.mdm:8080/safe")));
    }

    public final void startActivity(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
