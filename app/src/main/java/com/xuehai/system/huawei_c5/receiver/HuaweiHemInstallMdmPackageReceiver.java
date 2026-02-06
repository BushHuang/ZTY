package com.xuehai.system.huawei_c5.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/xuehai/system/huawei_c5/receiver/HuaweiHemInstallMdmPackageReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HuaweiHemInstallMdmPackageReceiver extends BroadcastReceiver {
    private static final String TAG = "HuaweiHemInstallMdmPackageReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        MdmLog.i("HuaweiHemInstallMdmPackageReceiver", "静默安装广播接收器");
        if (intent == null || (extras = intent.getExtras()) == null) {
            return;
        }
        String string = extras.getString("packageName");
        int i = extras.getInt("returnCode");
        if (i == 0) {
            MdmLog.i("HuaweiHemInstallMdmPackageReceiver", "静默安装" + string + "成功");
            return;
        }
        MdmLog.i("HuaweiHemInstallMdmPackageReceiver", "静默安装" + string + "失败，installStatus = " + i);
    }
}
