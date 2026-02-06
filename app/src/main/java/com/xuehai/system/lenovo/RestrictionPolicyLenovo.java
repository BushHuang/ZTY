package com.xuehai.system.lenovo;

import android.app.mia.MiaMdmPolicyManager;
import android.content.Context;
import com.xuehai.system.common.compat.RestrictionPolicyDefault;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xuehai/system/lenovo/RestrictionPolicyLenovo;", "Lcom/xuehai/system/common/compat/RestrictionPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Landroid/app/mia/MiaMdmPolicyManager;", "isSdCardEnabled", "", "isUsbDebuggingEnabled", "multipleUsersAllowed", "setCameraState", "enable", "setInputMethod", "inputMethodClassName", "", "setScreenCapture", "setSdCardState", "setUsbDebuggingEnabled", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RestrictionPolicyLenovo extends RestrictionPolicyDefault {
    private final MiaMdmPolicyManager policy;

    public RestrictionPolicyLenovo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = new MiaMdmPolicyManager(context);
    }

    @Override
    public boolean isSdCardEnabled() {
        return !this.policy.isExternalStorageDisabled();
    }

    @Override
    public boolean isUsbDebuggingEnabled() {
        return !this.policy.isUsbDisabled();
    }

    @Override
    public boolean multipleUsersAllowed() {
        return false;
    }

    @Override
    public boolean setCameraState(boolean enable) {
        this.policy.setCamera(enable);
        return this.policy.isForbidCamera() == (enable ^ true);
    }

    @Override
    public boolean setInputMethod(String inputMethodClassName) {
        Intrinsics.checkNotNullParameter(inputMethodClassName, "inputMethodClassName");
        this.policy.setDefaultInput(inputMethodClassName);
        return true;
    }

    @Override
    public boolean setScreenCapture(boolean enable) {
        this.policy.setScreenshotDisabled(!enable);
        return this.policy.isScreenshotDisabled() == (enable ^ true);
    }

    @Override
    public boolean setSdCardState(boolean enable) {
        this.policy.setExternalStorageDisabled(!enable);
        return this.policy.isExternalStorageDisabled() == (enable ^ true);
    }

    @Override
    public boolean setUsbDebuggingEnabled(boolean enable) {
        this.policy.setUsbDebuggingEnabled(enable);
        return isUsbDebuggingEnabled() == enable;
    }
}
