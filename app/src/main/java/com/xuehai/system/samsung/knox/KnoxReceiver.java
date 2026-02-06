package com.xuehai.system.samsung.knox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\r"}, d2 = {"Lcom/xuehai/system/samsung/knox/KnoxReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "showToast", "msg", "", "Companion", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class KnoxReceiver extends BroadcastReceiver {
    public static final String ACTION_FREE = "com.xuehai.mdm.knox.free";
    public static final String ACTION_PAY = "com.xuehai.mdm.knox.pay";
    private static final String FAIL = "fail";
    public static final String LICENSE_STATE = "license_state";
    public static final String LICENSE_STATE_DESC = "license_state_desc";

    private final void showToast(Context context, String msg) {
        MdmLog.d("KnoxReceiver", msg);
        Toast.makeText(context, msg, 1).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        StringBuilder sb = new StringBuilder();
        sb.append("KnoxReceiver : ");
        String str2 = null;
        sb.append(intent != null ? intent.getAction() : null);
        MdmLog.log$default("MdmLog", sb.toString(), null, 4, null);
        if (intent != null) {
            String action = intent.getAction();
            LicenseState licenseState = new LicenseState(context);
            boolean z = true;
            boolean z2 = false;
            if (Intrinsics.areEqual("edm.intent.action.license.status", action)) {
                if (Intrinsics.areEqual("fail", intent.getStringExtra("edm.intent.extra.license.status"))) {
                    str2 = "激活失败(ELM)，请确保网络正常并且设备时间准确!";
                    z = false;
                } else {
                    str2 = "激活成功(ELM).";
                }
                licenseState.setELMLicenseState(z);
                str = "com.xuehai.mdm.knox.free";
            } else {
                if (!Intrinsics.areEqual("edm.intent.action.knox_license.status", action)) {
                    str = "";
                    if (str2 != null) {
                        showToast(context, str2);
                    }
                    LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
                    Intent intent2 = new Intent(str);
                    intent2.putExtra("license_state", z2);
                    intent2.putExtra("license_state_desc", str2 != null ? str2 : "");
                    localBroadcastManager.sendBroadcast(intent2);
                }
                if (Intrinsics.areEqual("fail", intent.getStringExtra("edm.intent.extra.knox_license.status"))) {
                    str2 = "激活失败(KLM)，请确保网络正常并且设备时间准确!";
                    z = false;
                } else {
                    str2 = "激活成功(KLM).";
                }
                licenseState.setKLMLicenseState(z);
                str = "com.xuehai.mdm.knox.pay";
            }
            z2 = z;
            if (str2 != null) {
            }
            LocalBroadcastManager localBroadcastManager2 = LocalBroadcastManager.getInstance(context);
            Intent intent22 = new Intent(str);
            intent22.putExtra("license_state", z2);
            intent22.putExtra("license_state_desc", str2 != null ? str2 : "");
            localBroadcastManager2.sendBroadcast(intent22);
        }
    }
}
