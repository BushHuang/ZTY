package com.xuehai.system.lenovo.csdk.tb223fc;

import android.app.csdk.CSDKManager;
import android.content.Context;
import com.xuehai.system.common.compat.RestrictionPolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.lenovo.csdk.LenovoCSDK;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0001,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\nH\u0016J\b\u0010\u001a\u001a\u00020\nH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0016J\u0010\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u001d\u001a\u00020\nH\u0016J\b\u0010\u001e\u001a\u00020\nH\u0016J\b\u0010\u001f\u001a\u00020\nH\u0016J\b\u0010 \u001a\u00020\nH\u0016J\u0010\u0010!\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\u0010\u0010\"\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\u0010\u0010#\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\u0010\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\u0010\u0010(\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\u0010\u0010)\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\u0010\u0010*\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\b\u0010+\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/xuehai/system/lenovo/csdk/tb223fc/RestrictionPolicyLenovo223FC;", "Lcom/xuehai/system/common/compat/RestrictionPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "csdkManager", "Landroid/app/csdk/CSDKManager;", "allowFactoryReset", "", "allow", "allowFirmwareRecovery", "allowHardwareKey", "keyCode", "", "allowMultiWindowMode", "allowMultipleUsers", "allowOTAUpgrade", "allowOTG", "enable", "allowSafeMode", "allowUsbHostStorage", "disableHomeLongFunc", "", "isClipboardAllowed", "isFactoryResetAllowed", "isFirmwareRecoveryAllowed", "isHardwareKeyAllow", "isMTPAvailable", "isSdCardEnabled", "isUsbDebuggingEnabled", "multipleUsersAllowed", "setCameraState", "setClipboardEnabled", "setEmergencyCallOnly", "setInputMethod", "inputMethodClassName", "", "setMTPEnabled", "setScreenCapture", "setSdCardState", "setUsbDebuggingEnabled", "wipeRecentTasks", "Companion", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RestrictionPolicyLenovo223FC extends RestrictionPolicyDefault {
    private static final String TAG = "RestrictionPolicyLenovo223FC";
    private final Context context;
    private final CSDKManager csdkManager;

    public RestrictionPolicyLenovo223FC(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.csdkManager = LenovoCSDK.INSTANCE.getCSDKManager(this.context);
    }

    @Override
    public boolean allowFactoryReset(boolean allow) {
        this.csdkManager.disallowFactoryResetV3(!allow);
        return true;
    }

    @Override
    public boolean allowFirmwareRecovery(boolean allow) {
        MdmLog.w("RestrictionPolicyLenovo223FC", "联想未实现！ [allowFirmwareRecovery(" + allow + ")]");
        return super.allowFirmwareRecovery(allow);
    }

    @Override
    public boolean allowHardwareKey(int keyCode, boolean allow) {
        if (keyCode == 3) {
            return this.csdkManager.hideHomeSoftKey(!allow);
        }
        if (keyCode == 4) {
            return this.csdkManager.hideBackSoftKey(!allow);
        }
        if (keyCode != 187) {
            return false;
        }
        return this.csdkManager.hideMenuSoftKey(!allow);
    }

    @Override
    public boolean allowMultiWindowMode(boolean allow) {
        MdmLog.w("RestrictionPolicyLenovo223FC", "联想未实现！ [allowMultiWindowMode(" + allow + ")]");
        return super.allowMultiWindowMode(allow);
    }

    @Override
    public boolean allowMultipleUsers(boolean allow) {
        this.csdkManager.disallowMultiUserV3(!allow);
        return true;
    }

    @Override
    public boolean allowOTAUpgrade(boolean allow) {
        this.csdkManager.setPackageEnabled("com.lenovo.ota", allow);
        return true;
    }

    @Override
    public boolean allowOTG(boolean enable) {
        this.csdkManager.setCustomOtg(enable);
        return true;
    }

    @Override
    public boolean allowSafeMode(boolean allow) {
        this.csdkManager.setSafeModeDisabled(!allow);
        return true;
    }

    @Override
    public boolean allowUsbHostStorage(boolean allow) {
        this.csdkManager.enableUsbHostPermission(allow);
        return true;
    }

    @Override
    public void disableHomeLongFunc() {
        MdmLog.w("RestrictionPolicyLenovo223FC", "联想未实现！ [disableHomeLongFunc()]");
    }

    public final Context getContext() {
        return this.context;
    }

    @Override
    public boolean isClipboardAllowed() {
        return super.isClipboardAllowed();
    }

    @Override
    public boolean isFactoryResetAllowed() {
        return !this.csdkManager.isFactoryResetDisable();
    }

    @Override
    public boolean isFirmwareRecoveryAllowed() {
        MdmLog.w("RestrictionPolicyLenovo223FC", "联想未实现！ [isFirmwareRecoveryAllowed()]");
        return super.isFirmwareRecoveryAllowed();
    }

    @Override
    public boolean isHardwareKeyAllow(int keyCode) {
        return super.isHardwareKeyAllow(keyCode);
    }

    @Override
    public boolean isMTPAvailable() {
        return this.csdkManager.getCurrentUsbMode() == 1;
    }

    @Override
    public boolean isSdCardEnabled() {
        return this.csdkManager.isMassStorageEnabled();
    }

    @Override
    public boolean isUsbDebuggingEnabled() {
        return this.csdkManager.isUsbDebuggingEnabled();
    }

    @Override
    public boolean multipleUsersAllowed() {
        MdmLog.w("RestrictionPolicyLenovo223FC", "联想未实现！ [multipleUsersAllowed()]");
        return super.multipleUsersAllowed();
    }

    @Override
    public boolean setCameraState(boolean enable) {
        return this.csdkManager.disableCamera(!enable);
    }

    @Override
    public boolean setClipboardEnabled(boolean enable) {
        this.csdkManager.allowClipboard(enable);
        return true;
    }

    @Override
    public boolean setEmergencyCallOnly(boolean enable) {
        MdmLog.w("RestrictionPolicyLenovo223FC", "联想未实现！ [setEmergencyCallOnly(" + enable + ")]");
        return super.setEmergencyCallOnly(enable);
    }

    @Override
    public boolean setInputMethod(String inputMethodClassName) {
        Intrinsics.checkNotNullParameter(inputMethodClassName, "inputMethodClassName");
        return this.csdkManager.setDefaultInputMethod(inputMethodClassName);
    }

    @Override
    public boolean setMTPEnabled(boolean enable) {
        if (enable) {
            this.csdkManager.setCurrentUsbMode(1);
            this.csdkManager.hideUsbMenu(false);
        } else {
            this.csdkManager.setCurrentUsbMode(0);
            this.csdkManager.hideUsbMenu(true);
        }
        return true;
    }

    @Override
    public boolean setScreenCapture(boolean enable) {
        this.csdkManager.enableCaptureScreenV3(enable);
        return true;
    }

    @Override
    public boolean setSdCardState(boolean enable) {
        return this.csdkManager.enableMassStorage(enable);
    }

    @Override
    public boolean setUsbDebuggingEnabled(boolean enable) {
        return this.csdkManager.enableUsbDebugging(enable);
    }

    @Override
    public boolean wipeRecentTasks() {
        this.csdkManager.addPersistentAppList(CollectionsKt.arrayListOf(this.context.getPackageName()));
        this.csdkManager.removeAllRecentTasks();
        this.csdkManager.removePersistentAppList(CollectionsKt.arrayListOf(this.context.getPackageName()));
        return true;
    }
}
