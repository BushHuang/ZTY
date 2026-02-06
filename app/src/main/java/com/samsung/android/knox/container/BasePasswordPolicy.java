package com.samsung.android.knox.container;

import android.content.ComponentName;
import com.samsung.android.knox.SupportLibUtils;

public class BasePasswordPolicy {
    private android.app.enterprise.BasePasswordPolicy mBasePasswordPolicy;

    public BasePasswordPolicy(android.app.enterprise.BasePasswordPolicy basePasswordPolicy) {
        this.mBasePasswordPolicy = basePasswordPolicy;
    }

    public int getCurrentFailedPasswordAttempts() {
        return this.mBasePasswordPolicy.getCurrentFailedPasswordAttempts();
    }

    public int getKeyguardDisabledFeatures(ComponentName componentName) {
        try {
            return this.mBasePasswordPolicy.getKeyguardDisabledFeatures(componentName);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(BasePasswordPolicy.class, "getKeyguardDisabledFeatures", new Class[]{ComponentName.class}, 15));
        }
    }

    public int getMaximumFailedPasswordsForWipe(ComponentName componentName) {
        return this.mBasePasswordPolicy.getMaximumFailedPasswordsForWipe(componentName);
    }

    public long getMaximumTimeToLock(ComponentName componentName) {
        return this.mBasePasswordPolicy.getMaximumTimeToLock(componentName);
    }

    public long getPasswordExpiration(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordExpiration(componentName);
    }

    public long getPasswordExpirationTimeout(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordExpirationTimeout(componentName);
    }

    public int getPasswordHistoryLength(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordHistoryLength(componentName);
    }

    public long getPasswordMaximumLength(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordMaximumLength(componentName);
    }

    public int getPasswordMinimumLength(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordMinimumLength(componentName);
    }

    public int getPasswordMinimumLetters(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordMinimumLetters(componentName);
    }

    public int getPasswordMinimumLowerCase(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordMinimumLowerCase(componentName);
    }

    public int getPasswordMinimumNonLetter(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordMinimumNonLetter(componentName);
    }

    public int getPasswordMinimumNumeric(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordMinimumNumeric(componentName);
    }

    public int getPasswordMinimumSymbols(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordMinimumSymbols(componentName);
    }

    public int getPasswordMinimumUpperCase(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordMinimumUpperCase(componentName);
    }

    public int getPasswordQuality(ComponentName componentName) {
        return this.mBasePasswordPolicy.getPasswordQuality(componentName);
    }

    public boolean isActivePasswordSufficient() {
        return this.mBasePasswordPolicy.isActivePasswordSufficient();
    }

    public boolean resetPassword(String str, int i) {
        return this.mBasePasswordPolicy.resetPassword(str, i);
    }

    public void setKeyguardDisabledFeatures(ComponentName componentName, int i) {
        try {
            this.mBasePasswordPolicy.setKeyguardDisabledFeatures(componentName, i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(BasePasswordPolicy.class, "setKeyguardDisabledFeatures", new Class[]{ComponentName.class, Integer.TYPE}, 15));
        }
    }

    public void setMaximumFailedPasswordsForWipe(ComponentName componentName, int i) {
        this.mBasePasswordPolicy.setMaximumFailedPasswordsForWipe(componentName, i);
    }

    public void setMaximumTimeToLock(ComponentName componentName, long j) {
        this.mBasePasswordPolicy.setMaximumTimeToLock(componentName, j);
    }

    public void setPasswordExpirationTimeout(ComponentName componentName, long j) {
        this.mBasePasswordPolicy.setPasswordExpirationTimeout(componentName, j);
    }

    public void setPasswordHistoryLength(ComponentName componentName, int i) {
        this.mBasePasswordPolicy.setPasswordHistoryLength(componentName, i);
    }

    public void setPasswordMinimumLength(ComponentName componentName, int i) {
        this.mBasePasswordPolicy.setPasswordMinimumLength(componentName, i);
    }

    public void setPasswordMinimumLetters(ComponentName componentName, int i) {
        this.mBasePasswordPolicy.setPasswordMinimumLetters(componentName, i);
    }

    public void setPasswordMinimumLowerCase(ComponentName componentName, int i) {
        this.mBasePasswordPolicy.setPasswordMinimumLowerCase(componentName, i);
    }

    public void setPasswordMinimumNonLetter(ComponentName componentName, int i) {
        this.mBasePasswordPolicy.setPasswordMinimumNonLetter(componentName, i);
    }

    public void setPasswordMinimumNumeric(ComponentName componentName, int i) {
        this.mBasePasswordPolicy.setPasswordMinimumNumeric(componentName, i);
    }

    public void setPasswordMinimumSymbols(ComponentName componentName, int i) {
        this.mBasePasswordPolicy.setPasswordMinimumSymbols(componentName, i);
    }

    public void setPasswordMinimumUpperCase(ComponentName componentName, int i) {
        this.mBasePasswordPolicy.setPasswordMinimumUpperCase(componentName, i);
    }

    public void setPasswordQuality(ComponentName componentName, int i) {
        this.mBasePasswordPolicy.setPasswordQuality(componentName, i);
    }
}
