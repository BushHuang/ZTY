package com.xuehai.system.common.compat;

import com.xuehai.system.common.UnSupportException;
import com.xuehai.system.common.policies.IRestrictionPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0004H\u0016J\b\u0010\u0016\u001a\u00020\u0004H\u0016J\b\u0010\u0017\u001a\u00020\u0004H\u0016J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\b\u0010\u001b\u001a\u00020\u0004H\u0016J\b\u0010\u001c\u001a\u00020\u0004H\u0016J\b\u0010\u001d\u001a\u00020\u0004H\u0016J\b\u0010\u001e\u001a\u00020\u0004H\u0016J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010!\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010)\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010*\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010,\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010-\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010.\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010/\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u00100\u001a\u00020\u0004H\u0016¨\u00061"}, d2 = {"Lcom/xuehai/system/common/compat/RestrictionPolicyDefault;", "Lcom/xuehai/system/common/policies/IRestrictionPolicy;", "()V", "allowFactoryReset", "", "allow", "allowFirmwareRecovery", "allowHardwareKey", "keyCode", "", "allowMultiWindowMode", "allowMultipleUsers", "allowOTAUpgrade", "allowOTG", "enable", "allowSafeMode", "allowStatusBarExpansion", "allowSystemAccountLogin", "allowUsbHostStorage", "disableHomeLongFunc", "", "isClipboardAllowed", "isFactoryResetAllowed", "isFirmwareRecoveryAllowed", "isHardwareKeyAllow", "isMTPAvailable", "isSafeModeAllow", "isSdCardEnabled", "isUsbDebuggingEnabled", "isUsbHostStorageAllowed", "multipleUsersAllowed", "setBixbyShortcutEnabled", "setCameraShortcutEnabled", "setCameraState", "setClipboardEnabled", "setEmergencyCallOnly", "setFileShareDisabled", "setInputMethod", "inputMethodClassName", "", "setKanbanEnabled", "setLockScreenItems", "setMTPEnabled", "setScreenCapture", "setSdCardState", "setSwitchInputMethodEnabled", "setTaskBarEnabled", "setUsbDebuggingEnabled", "wipeRecentTasks", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class RestrictionPolicyDefault implements IRestrictionPolicy {
    @Override
    public boolean allowFactoryReset(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowFirmwareRecovery(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowHardwareKey(int keyCode, boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowMultiWindowMode(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowMultipleUsers(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowOTAUpgrade(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowOTG(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowSafeMode(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowStatusBarExpansion(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowSystemAccountLogin(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowUsbHostStorage(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public void disableHomeLongFunc() {
    }

    @Override
    public boolean isClipboardAllowed() {
        return true;
    }

    @Override
    public boolean isFactoryResetAllowed() {
        throw new UnSupportException();
    }

    @Override
    public boolean isFirmwareRecoveryAllowed() {
        throw new UnSupportException();
    }

    @Override
    public boolean isHardwareKeyAllow(int keyCode) {
        throw new UnSupportException();
    }

    @Override
    public boolean isMTPAvailable() {
        throw new UnSupportException();
    }

    @Override
    public boolean isSafeModeAllow() {
        return false;
    }

    @Override
    public boolean isSdCardEnabled() {
        throw new UnSupportException();
    }

    @Override
    public boolean isUsbDebuggingEnabled() {
        throw new UnSupportException();
    }

    @Override
    public boolean isUsbHostStorageAllowed() {
        throw new UnSupportException();
    }

    @Override
    public boolean multipleUsersAllowed() {
        throw new UnSupportException();
    }

    @Override
    public boolean setBixbyShortcutEnabled(boolean enable) {
        return false;
    }

    @Override
    public boolean setCameraShortcutEnabled(boolean enable) {
        return false;
    }

    @Override
    public boolean setCameraState(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean setClipboardEnabled(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean setEmergencyCallOnly(boolean enable) {
        return true;
    }

    @Override
    public boolean setFileShareDisabled(boolean enable) {
        return true;
    }

    @Override
    public boolean setInputMethod(String inputMethodClassName) {
        Intrinsics.checkNotNullParameter(inputMethodClassName, "inputMethodClassName");
        throw new UnSupportException();
    }

    @Override
    public boolean setKanbanEnabled(boolean enable) {
        return false;
    }

    @Override
    public boolean setLockScreenItems(boolean enable) {
        return false;
    }

    @Override
    public boolean setMTPEnabled(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean setScreenCapture(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean setSdCardState(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean setSwitchInputMethodEnabled(boolean enable) {
        return false;
    }

    @Override
    public boolean setTaskBarEnabled(boolean enable) {
        return false;
    }

    @Override
    public boolean setUsbDebuggingEnabled(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean wipeRecentTasks() {
        throw new UnSupportException();
    }
}
