package com.samsung.android.knox.accounts;

public class EmailPolicy {
    private android.app.enterprise.EmailPolicy mEmailPolicy;

    public EmailPolicy(android.app.enterprise.EmailPolicy emailPolicy) {
        this.mEmailPolicy = emailPolicy;
    }

    public boolean allowAccountAddition(boolean z) {
        return this.mEmailPolicy.allowAccountAddition(z);
    }

    public boolean allowEmailSettingsChange(boolean z, long j) {
        return this.mEmailPolicy.allowEmailSettingsChange(z, j);
    }

    public boolean getAllowEmailForwarding(String str) {
        return this.mEmailPolicy.getAllowEmailForwarding(str);
    }

    public boolean getAllowHtmlEmail(String str) {
        return this.mEmailPolicy.getAllowHtmlEmail(str);
    }

    public boolean isAccountAdditionAllowed() {
        return this.mEmailPolicy.isAccountAdditionAllowed();
    }

    public boolean isEmailNotificationsEnabled(long j) {
        return this.mEmailPolicy.isEmailNotificationsEnabled(j);
    }

    public boolean isEmailSettingsChangeAllowed(long j) {
        return this.mEmailPolicy.isEmailSettingsChangeAllowed(j);
    }

    public boolean setAllowEmailForwarding(String str, boolean z) {
        return this.mEmailPolicy.setAllowEmailForwarding(str, z);
    }

    public boolean setAllowHtmlEmail(String str, boolean z) {
        return this.mEmailPolicy.setAllowHtmlEmail(str, z);
    }

    public boolean setEmailNotificationsState(boolean z, long j) {
        return this.mEmailPolicy.setEmailNotificationsState(z, j);
    }
}
