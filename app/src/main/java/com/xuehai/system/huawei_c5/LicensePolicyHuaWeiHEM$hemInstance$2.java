package com.xuehai.system.huawei_c5;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hem.license.HemLicenseManager;
import com.huawei.hem.license.HemLicenseStatusListener;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/huawei/hem/license/HemLicenseManager;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
final class LicensePolicyHuaWeiHEM$hemInstance$2 extends Lambda implements Function0<HemLicenseManager> {
    final LicensePolicyHuaWeiHEM this$0;

    LicensePolicyHuaWeiHEM$hemInstance$2(LicensePolicyHuaWeiHEM licensePolicyHuaWeiHEM) {
        super(0);
        this.this$0 = licensePolicyHuaWeiHEM;
    }

    private static final void m211invoke$lambda2$lambda1(LicensePolicyHuaWeiHEM licensePolicyHuaWeiHEM, HemLicenseManager hemLicenseManager, int i, String str) {
        Intrinsics.checkNotNullParameter(licensePolicyHuaWeiHEM, "this$0");
        licensePolicyHuaWeiHEM.isLicenseSuccess = i == 2000;
        Context context = hemLicenseManager.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        new HuaweiHemLicenseState(context).setLicenseState(licensePolicyHuaWeiHEM.isLicenseSuccess);
        licensePolicyHuaWeiHEM.mActivateTask = null;
        Intent intent = new Intent("com.xuehai.mdm.huawei.free");
        StringBuilder sb = new StringBuilder();
        sb.append("DPC激活 code：");
        sb.append(i);
        sb.append(", msg：");
        sb.append(str == null ? "" : str);
        String string = sb.toString();
        intent.putExtra("license_state", licensePolicyHuaWeiHEM.isLicenseSuccess);
        intent.putExtra("license_state_desc", string);
        LocalBroadcastManager.getInstance(hemLicenseManager.getContext()).sendBroadcast(intent);
        MdmLog.i("LicensePolicyHuaWeiHEM", "HEM SDK激活结果：[currentThread：" + Thread.currentThread().getName() + "]，code：" + i + "，isLicenseSuccess：" + licensePolicyHuaWeiHEM.isLicenseSuccess + "，msg：" + str);
    }

    @Override
    public final HemLicenseManager invoke() {
        final HemLicenseManager hemLicenseManager = HemLicenseManager.getInstance(this.this$0.context);
        final LicensePolicyHuaWeiHEM licensePolicyHuaWeiHEM = this.this$0;
        hemLicenseManager.setStatusListener(new HemLicenseStatusListener() {
            @Override
            public final void onStatus(int i, String str) {
                LicensePolicyHuaWeiHEM$hemInstance$2.m211invoke$lambda2$lambda1(licensePolicyHuaWeiHEM, hemLicenseManager, i, str);
            }
        });
        return hemLicenseManager;
    }
}
