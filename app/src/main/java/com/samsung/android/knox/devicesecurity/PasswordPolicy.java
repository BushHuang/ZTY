package com.samsung.android.knox.devicesecurity;

import android.content.ComponentName;
import com.samsung.android.knox.SupportLibUtils;
import java.util.List;
import java.util.Map;

public class PasswordPolicy {
    public static final int BIOMETRIC_AUTHENTICATION_FINGERPRINT = 1;
    public static final int BIOMETRIC_AUTHENTICATION_IRIS = 2;
    private android.app.enterprise.PasswordPolicy mPasswordPolicy;

    public PasswordPolicy(android.app.enterprise.PasswordPolicy passwordPolicy) {
        this.mPasswordPolicy = passwordPolicy;
    }

    public boolean deleteAllRestrictions() {
        return this.mPasswordPolicy.deleteAllRestrictions();
    }

    public boolean enforcePwdChange() {
        return this.mPasswordPolicy.enforcePwdChange();
    }

    public boolean excludeExternalStorageForFailedPasswordsWipe(boolean z) {
        return this.mPasswordPolicy.excludeExternalStorageForFailedPasswordsWipe(z);
    }

    public List<String> getForbiddenStrings(boolean z) {
        return this.mPasswordPolicy.getForbiddenStrings(z);
    }

    public int getMaximumCharacterOccurences() {
        return this.mPasswordPolicy.getMaximumCharacterOccurences();
    }

    public int getMaximumCharacterSequenceLength() {
        return this.mPasswordPolicy.getMaximumCharacterSequenceLength();
    }

    public int getMaximumFailedPasswordsForDeviceDisable() {
        return this.mPasswordPolicy.getMaximumFailedPasswordsForDeviceDisable();
    }

    public int getMaximumNumericSequenceLength() {
        return this.mPasswordPolicy.getMaximumNumericSequenceLength();
    }

    public int getMinPasswordComplexChars(ComponentName componentName) {
        return this.mPasswordPolicy.getMinPasswordComplexChars(componentName);
    }

    public int getMinimumCharacterChangeLength() {
        return this.mPasswordPolicy.getMinimumCharacterChangeLength();
    }

    public int getPasswordChangeTimeout() {
        return this.mPasswordPolicy.getPasswordChangeTimeout();
    }

    public int getPasswordExpires(ComponentName componentName) {
        return this.mPasswordPolicy.getPasswordExpires(componentName);
    }

    public int getPasswordHistory(ComponentName componentName) {
        return this.mPasswordPolicy.getPasswordHistory(componentName);
    }

    public int getPasswordLockDelay() {
        return this.mPasswordPolicy.getPasswordLockDelay();
    }

    public String getRequiredPwdPatternRestrictions(boolean z) {
        return this.mPasswordPolicy.getRequiredPwdPatternRestrictions(z);
    }

    public Map<Integer, String> getSupportedBiometricAuthentications() {
        try {
            return this.mPasswordPolicy.getSupportedBiometricAuthentications();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(PasswordPolicy.class, "getSupportedBiometricAuthentications", null, 13));
        }
    }

    public boolean isBiometricAuthenticationEnabled(int i) {
        try {
            return this.mPasswordPolicy.isBiometricAuthenticationEnabled(i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(PasswordPolicy.class, "isBiometricAuthenticationEnabled", new Class[]{Integer.TYPE}, 12));
        }
    }

    public boolean isExternalStorageForFailedPasswordsWipeExcluded() {
        return this.mPasswordPolicy.isExternalStorageForFailedPasswordsWipeExcluded();
    }

    public boolean isPasswordVisibilityEnabled() {
        return this.mPasswordPolicy.isPasswordVisibilityEnabled();
    }

    public boolean isScreenLockPatternVisibilityEnabled() {
        return this.mPasswordPolicy.isScreenLockPatternVisibilityEnabled();
    }

    public void reboot(String str) {
        this.mPasswordPolicy.reboot(str);
    }

    public boolean setBiometricAuthenticationEnabled(int i, boolean z) {
        try {
            return this.mPasswordPolicy.setBiometricAuthenticationEnabled(i, z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(PasswordPolicy.class, "setBiometricAuthenticationEnabled", new Class[]{Integer.TYPE, Boolean.TYPE}, 12));
        }
    }

    public boolean setForbiddenStrings(List<String> list) {
        return this.mPasswordPolicy.setForbiddenStrings(list);
    }

    public boolean setMaximumCharacterOccurrences(int i) {
        return this.mPasswordPolicy.setMaximumCharacterOccurrences(i);
    }

    public boolean setMaximumCharacterSequenceLength(int i) {
        return this.mPasswordPolicy.setMaximumCharacterSequenceLength(i);
    }

    public boolean setMaximumFailedPasswordsForDeviceDisable(int i) {
        return this.mPasswordPolicy.setMaximumFailedPasswordsForDeviceDisable(i);
    }

    public boolean setMaximumNumericSequenceLength(int i) {
        return this.mPasswordPolicy.setMaximumNumericSequenceLength(i);
    }

    public void setMinPasswordComplexChars(ComponentName componentName, int i) {
        this.mPasswordPolicy.setMinPasswordComplexChars(componentName, i);
    }

    public boolean setMinimumCharacterChangeLength(int i) {
        return this.mPasswordPolicy.setMinimumCharacterChangeLength(i);
    }

    public boolean setPasswordChangeTimeout(int i) {
        return this.mPasswordPolicy.setPasswordChangeTimeout(i);
    }

    public void setPasswordExpires(ComponentName componentName, int i) {
        this.mPasswordPolicy.setPasswordExpires(componentName, i);
    }

    public void setPasswordHistory(ComponentName componentName, int i) {
        this.mPasswordPolicy.setPasswordHistory(componentName, i);
    }

    public boolean setPasswordLockDelay(int i) {
        return this.mPasswordPolicy.setPasswordLockDelay(i);
    }

    public boolean setPasswordVisibilityEnabled(boolean z) {
        return this.mPasswordPolicy.setPasswordVisibilityEnabled(z);
    }

    public boolean setRequiredPasswordPattern(String str) {
        return this.mPasswordPolicy.setRequiredPasswordPattern(str);
    }

    public boolean setScreenLockPatternVisibilityEnabled(boolean z) {
        return this.mPasswordPolicy.setScreenLockPatternVisibilityEnabled(z);
    }
}
