package com.xuehai.system.huawei_c5;

import android.content.Context;
import android.os.Build;
import com.huawei.hem.license.HemLicenseManager;
import com.huawei.hmf.tasks.Task;
import com.xuehai.system.common.compat.LicensePolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0010\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\b\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/xuehai/system/huawei_c5/LicensePolicyHuaWeiHEM;", "Lcom/xuehai/system/common/compat/LicensePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "hemInstance", "Lcom/huawei/hem/license/HemLicenseManager;", "kotlin.jvm.PlatformType", "getHemInstance", "()Lcom/huawei/hem/license/HemLicenseManager;", "hemInstance$delegate", "Lkotlin/Lazy;", "isLicenseSuccess", "", "mActivateTask", "Lcom/huawei/hmf/tasks/Task;", "", "activateFreeLicense", "", "activatePayLicense", "payLicenseKey", "", "isFreeLicenseActivated", "isPayLicenseActivated", "resetLocalActivateState", "unActivateFreeLicense", "unActivatePayLicense", "Companion", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LicensePolicyHuaWeiHEM extends LicensePolicyDefault {
    public static final String TAG = "LicensePolicyHuaWeiHEM";
    private final Context context;

    private final Lazy hemInstance;
    private volatile boolean isLicenseSuccess;
    private volatile Task<Integer> mActivateTask;

    public LicensePolicyHuaWeiHEM(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.hemInstance = LazyKt.lazy(new LicensePolicyHuaWeiHEM$hemInstance$2(this));
    }

    private final HemLicenseManager getHemInstance() {
        return (HemLicenseManager) this.hemInstance.getValue();
    }

    @Override
    public void activateFreeLicense() {
        if (this.mActivateTask != null) {
            MdmLog.w("LicensePolicyHuaWeiHEM", "HEM SDK正在激活中...");
        } else {
            this.mActivateTask = getHemInstance().activeLicense();
        }
    }

    @Override
    public void activatePayLicense(String payLicenseKey) {
        Intrinsics.checkNotNullParameter(payLicenseKey, "payLicenseKey");
    }

    @Override
    public boolean isFreeLicenseActivated() {
        if (this.mActivateTask != null) {
            MdmLog.w("LicensePolicyHuaWeiHEM", "当前存在正在激活Licence的任务！");
            return false;
        }
        if (!this.isLicenseSuccess) {
            Context contextCreateDeviceProtectedStorageContext = Build.VERSION.SDK_INT >= 24 ? this.context.createDeviceProtectedStorageContext() : this.context;
            Intrinsics.checkNotNullExpressionValue(contextCreateDeviceProtectedStorageContext, "ctx");
            this.isLicenseSuccess = new HuaweiHemLicenseState(contextCreateDeviceProtectedStorageContext).getLicenseState();
        }
        return this.isLicenseSuccess;
    }

    @Override
    public boolean isPayLicenseActivated() {
        return true;
    }

    @Override
    public boolean resetLocalActivateState() {
        this.isLicenseSuccess = false;
        new HuaweiHemLicenseState(this.context).setLicenseState(false);
        return true;
    }

    @Override
    public void unActivateFreeLicense() {
        getHemInstance().deActiveLicense();
        this.isLicenseSuccess = false;
        new HuaweiHemLicenseState(this.context).setLicenseState(false);
    }

    @Override
    public void unActivatePayLicense() {
    }
}
