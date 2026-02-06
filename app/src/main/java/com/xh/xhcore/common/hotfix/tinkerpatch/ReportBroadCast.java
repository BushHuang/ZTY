package com.xh.xhcore.common.hotfix.tinkerpatch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;

public class ReportBroadCast extends BroadcastReceiver {

    public static final String f434a = "tinkerpatch_intent_patch_version";
    public static final String b = "tinkerpatch_intent_patch_restart";
    public static final String c = "tinkerpatch_intent_patch_code";
    private static final String d = "Tinker.ReportBroadCast";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            TinkerLog.e("Tinker.ReportBroadCast", "onReceive intent == null", new Object[0]);
        } else if (ShareIntentUtil.getBooleanExtra(intent, "tinkerpatch_intent_patch_restart", false)) {
            TinkerLog.e("Tinker.ReportBroadCast", "restart main application with ReportBroadCast, just return", new Object[0]);
        }
    }
}
