package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import com.xuehai.system.common.compat.RestrictionPolicyDefault;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0015\u001a\u00020\bH\u0016J\b\u0010\u0016\u001a\u00020\bH\u0016J\u0010\u0010\u0017\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u0018\u001a\u00020\bH\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016J\b\u0010\u001b\u001a\u00020\bH\u0016J\b\u0010\u001c\u001a\u00020\bH\u0016J\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0016J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0016J\u0010\u0010 \u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0016J\u0010\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0016J\u0010\u0010%\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0016J\u0010\u0010&\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0016J\u0010\u0010'\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0016J\b\u0010(\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/RestrictionPolicySamsungV3;", "Lcom/xuehai/system/common/compat/RestrictionPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "allowFactoryReset", "", "allow", "allowFirmwareRecovery", "allowHardwareKey", "keyCode", "", "allowMultiWindowMode", "allowMultipleUsers", "allowOTAUpgrade", "allowSafeMode", "allowStatusBarExpansion", "allowUsbHostStorage", "isClipboardAllowed", "isFactoryResetAllowed", "isFirmwareRecoveryAllowed", "isHardwareKeyAllow", "isMTPAvailable", "isSdCardEnabled", "isUsbDebuggingEnabled", "isUsbHostStorageAllowed", "multipleUsersAllowed", "setCameraState", "enable", "setClipboardEnabled", "setEmergencyCallOnly", "setInputMethod", "inputMethodClassName", "", "setMTPEnabled", "setScreenCapture", "setSdCardState", "setUsbDebuggingEnabled", "wipeRecentTasks", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class RestrictionPolicySamsungV3 extends RestrictionPolicyDefault {
    private final Context context;
    private final SamsungKnoxV3 policy;

    public RestrictionPolicySamsungV3(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.policy = SamsungKnoxV3.INSTANCE.getInstance(this.context);
    }

    @Override
    public boolean allowFactoryReset(boolean allow) {
        return this.policy.restrictionPolicy().allowFactoryReset(allow);
    }

    @Override
    public boolean allowFirmwareRecovery(boolean allow) {
        return this.policy.restrictionPolicy().allowFirmwareRecovery(allow);
    }

    @Override
    public boolean allowHardwareKey(int keyCode, boolean allow) {
        return this.policy.kioskMode().allowHardwareKeys(Collections.singletonList(Integer.valueOf(keyCode)), allow) != null;
    }

    @Override
    public boolean allowMultiWindowMode(boolean allow) {
        try {
            Result.Companion companion = Result.INSTANCE;
            Result.m228constructorimpl(Boolean.valueOf(this.policy.kioskMode().allowEdgeScreen(31, allow)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m228constructorimpl(ResultKt.createFailure(th));
        }
        return this.policy.kioskMode().allowMultiWindowMode(allow);
    }

    @Override
    public boolean allowMultipleUsers(boolean allow) {
        return this.policy.multiUserManager().allowMultipleUsers(allow);
    }

    @Override
    public boolean allowOTAUpgrade(boolean allow) {
        return this.policy.restrictionPolicy().allowOTAUpgrade(allow);
    }

    @Override
    public boolean allowSafeMode(boolean allow) {
        return this.policy.restrictionPolicy().allowSafeMode(allow);
    }

    @Override
    public boolean allowStatusBarExpansion(boolean allow) {
        return this.policy.restrictionPolicy().allowStatusBarExpansion(allow);
    }

    @Override
    public boolean allowUsbHostStorage(boolean allow) {
        return this.policy.restrictionPolicy().allowUsbHostStorage(allow);
    }

    @Override
    public boolean isClipboardAllowed() {
        return this.policy.restrictionPolicy().isClipboardAllowed(false);
    }

    @Override
    public boolean isFactoryResetAllowed() {
        return this.policy.restrictionPolicy().isFactoryResetAllowed();
    }

    @Override
    public boolean isFirmwareRecoveryAllowed() {
        return this.policy.restrictionPolicy().isFirmwareRecoveryAllowed(false);
    }

    @Override
    public boolean isHardwareKeyAllow(int keyCode) {
        return this.policy.kioskMode().isHardwareKeyAllowed(keyCode);
    }

    @Override
    public boolean isMTPAvailable() {
        return this.policy.restrictionPolicy().isUsbMediaPlayerAvailable(false);
    }

    @Override
    public boolean isSdCardEnabled() {
        return this.policy.restrictionPolicy().isSdCardEnabled();
    }

    @Override
    public boolean isUsbDebuggingEnabled() {
        return this.policy.restrictionPolicy().isUsbDebuggingEnabled();
    }

    @Override
    public boolean isUsbHostStorageAllowed() {
        return this.policy.restrictionPolicy().isUsbHostStorageAllowed();
    }

    @Override
    public boolean multipleUsersAllowed() {
        return this.policy.multiUserManager().multipleUsersAllowed();
    }

    @Override
    public boolean setCameraState(boolean enable) {
        return this.policy.restrictionPolicy().setCameraState(enable);
    }

    @Override
    public boolean setClipboardEnabled(boolean enable) {
        return this.policy.restrictionPolicy().setClipboardEnabled(enable);
    }

    @Override
    public boolean setEmergencyCallOnly(boolean enable) {
        return this.policy.phoneRestrictionPolicy().setEmergencyCallOnly(enable);
    }

    @Override
    public boolean setInputMethod(String inputMethodClassName) {
        Intrinsics.checkNotNullParameter(inputMethodClassName, "inputMethodClassName");
        return this.policy.settingsManager().setInputMethod(inputMethodClassName, true) >= 0;
    }

    @Override
    public boolean setMTPEnabled(boolean enable) {
        if (!enable && this.policy.hasPermission("com.samsung.android.knox.permission.KNOX_CUSTOM_SYSTEM")) {
            this.policy.systemManager().setUsbConnectionType(4);
        }
        return this.policy.restrictionPolicy().setUsbMediaPlayerAvailability(enable);
    }

    @Override
    public boolean setScreenCapture(boolean enable) {
        return this.policy.restrictionPolicy().setScreenCapture(enable);
    }

    @Override
    public boolean setSdCardState(boolean enable) {
        return this.policy.restrictionPolicy().setSdCardState(enable);
    }

    @Override
    public boolean setUsbDebuggingEnabled(boolean enable) {
        if (this.policy.hasPermission("com.samsung.android.knox.permission.KNOX_CUSTOM_SETTING")) {
            this.policy.settingsManager().setAdbState(enable);
        }
        return this.policy.restrictionPolicy().setUsbDebuggingEnabled(enable);
    }

    @Override
    public boolean wipeRecentTasks() {
        return this.policy.kioskMode().wipeRecentTasks();
    }
}
