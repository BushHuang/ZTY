package com.xuehai.system.samsung.knox;

import android.content.Context;
import com.samsung.android.knox.custom.CustomDeviceManager;
import com.xuehai.system.common.compat.RestrictionPolicyDefault;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u0013\u001a\u00020\bH\u0016J\b\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0015\u001a\u00020\bH\u0016J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\bH\u0016J\b\u0010\u0018\u001a\u00020\bH\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016J\b\u0010\u001b\u001a\u00020\bH\u0016J\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0016J\u0010\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0016J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0016J\u0010\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0016J\u0010\u0010$\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0016J\u0010\u0010%\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0016J\u0010\u0010&\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0016J\b\u0010'\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/xuehai/system/samsung/knox/RestrictionPolicySamsung;", "Lcom/xuehai/system/common/compat/RestrictionPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/SamsungPolicy;", "allowFactoryReset", "", "allow", "allowFirmwareRecovery", "allowHardwareKey", "keyCode", "", "allowMultiWindowMode", "allowMultipleUsers", "allowOTAUpgrade", "allowSafeMode", "allowUsbHostStorage", "isClipboardAllowed", "isFactoryResetAllowed", "isFirmwareRecoveryAllowed", "isHardwareKeyAllow", "isMTPAvailable", "isSdCardEnabled", "isUsbDebuggingEnabled", "isUsbHostStorageAllowed", "multipleUsersAllowed", "setCameraState", "enable", "setClipboardEnabled", "setEmergencyCallOnly", "setInputMethod", "inputMethodClassName", "", "setMTPEnabled", "setScreenCapture", "setSdCardState", "setUsbDebuggingEnabled", "wipeRecentTasks", "mdm_samsungknox_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RestrictionPolicySamsung extends RestrictionPolicyDefault {
    private final SamsungPolicy policy;

    public RestrictionPolicySamsung(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = new SamsungPolicy(context);
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
        return CustomDeviceManager.getInstance().getSettingsManager().setInputMethod(inputMethodClassName, true) >= 0;
    }

    @Override
    public boolean setMTPEnabled(boolean enable) {
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
        return this.policy.restrictionPolicy().setUsbDebuggingEnabled(enable);
    }

    @Override
    public boolean wipeRecentTasks() {
        return this.policy.kioskMode().wipeRecentTasks();
    }
}
