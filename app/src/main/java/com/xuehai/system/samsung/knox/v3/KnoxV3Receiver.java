package com.xuehai.system.samsung.knox.v3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\r"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/KnoxV3Receiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "showToast", "msg", "", "Companion", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class KnoxV3Receiver extends BroadcastReceiver {
    public static final String ACTION = "com.xuehai.mdm.Knox.v3";
    private static final int DEFAULT_ERROR_CODE = -1;
    public static final String LICENSE_STATE = "license_state";
    public static final String LICENSE_STATE_DESC = "license_state_desc";

    private final void showToast(Context context, String msg) {
        MdmLog.d("KnoxV3Receiver", msg);
        Toast.makeText(context, msg, 1).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) throws Resources.NotFoundException {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        StringBuilder sb = new StringBuilder();
        sb.append("KnoxV3Receiver : ");
        sb.append(intent != null ? intent.getAction() : null);
        MdmLog.log$default("MdmLog", sb.toString(), null, 4, null);
        if (Intrinsics.areEqual("com.samsung.android.knox.intent.action.KNOX_LICENSE_STATUS", intent != null ? intent.getAction() : null)) {
            int intExtra = intent.getIntExtra("com.samsung.android.knox.intent.extra.KNOX_LICENSE_ERROR_CODE", -1);
            if (intExtra == 0) {
                new LicenseV3State(context).setLicenseState(true);
                i = R.string.klm_activated_succesfully;
            } else if (intExtra == 201) {
                i = R.string.err_klm_licence_invalid_license;
            } else if (intExtra == 301) {
                i = R.string.err_klm_internal;
            } else if (intExtra == 401) {
                i = R.string.err_klm_internal_server;
            } else if (intExtra == 601) {
                i = R.string.err_klm_user_disagrees_license_agreement;
            } else if (intExtra == 101) {
                i = R.string.err_klm_null_params;
            } else if (intExtra == 102) {
                i = R.string.err_klm_unknown;
            } else if (intExtra == 501) {
                i = R.string.err_klm_network_disconnected;
            } else if (intExtra != 502) {
                switch (intExtra) {
                    case 203:
                        i = R.string.err_klm_licence_terminated;
                        break;
                    case 204:
                        i = R.string.err_klm_invalid_package_name;
                        break;
                    case 205:
                        i = R.string.err_klm_not_current_date;
                        break;
                    default:
                        i = R.string.err_klm_code_unknown;
                        break;
                }
            } else {
                i = R.string.err_klm_network_general;
            }
            String string = context.getResources().getString(i, String.valueOf(intExtra), intent.getStringExtra("com.samsung.android.knox.intent.extra.KNOX_LICENSE_STATUS"));
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…NSE_STATUS)\n            )");
            showToast(context, string);
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
            Intent intent2 = new Intent("com.xuehai.mdm.Knox.v3");
            intent2.putExtra("license_state", intExtra == 0);
            intent2.putExtra("license_state_desc", string);
            localBroadcastManager.sendBroadcast(intent2);
        }
    }
}
