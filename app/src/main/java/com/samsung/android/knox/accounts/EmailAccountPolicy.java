package com.samsung.android.knox.accounts;

public class EmailAccountPolicy {
    public static final String ACCOUNT_TYPE_IMAP = "imap";
    public static final String ACCOUNT_TYPE_POP3 = "pop3";
    public static final String ACTION_EMAIL_ACCOUNT_ADD_RESULT = "com.samsung.android.knox.intent.action.EMAIL_ACCOUNT_ADD_RESULT";
    public static final String ACTION_EMAIL_ACCOUNT_DELETE_RESULT = "com.samsung.android.knox.intent.action.EMAIL_ACCOUNT_DELETE_RESULT";
    public static final String EXTRA_ACCOUNT_ID = "com.samsung.android.knox.intent.extra.ACCOUNT_ID";
    public static final String EXTRA_EMAIL_ADDRESS = "com.samsung.android.knox.intent.extra.EMAIL_ADDRESS";
    public static final String EXTRA_INCOMING_PROTOCOL = "com.samsung.android.knox.intent.extra.INCOMING_PROTOCOL";
    public static final String EXTRA_INCOMING_SERVER_ADDRESS = "com.samsung.android.knox.intent.extra.INCOMING_SERVER_ADDRESS";
    public static final String EXTRA_RESULT = "com.samsung.android.knox.intent.extra.RESULT";
    private android.app.enterprise.EmailAccountPolicy mEmailAccountPolicy;

    public EmailAccountPolicy(android.app.enterprise.EmailAccountPolicy emailAccountPolicy) {
        this.mEmailAccountPolicy = emailAccountPolicy;
    }

    public long addNewAccount(EmailAccount emailAccount) {
        try {
            return this.mEmailAccountPolicy.addNewAccount(EmailAccount.convertToOld(emailAccount));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public boolean deleteAccount(long j) {
        return this.mEmailAccountPolicy.deleteAccount(j);
    }

    public Account getAccountDetails(long j) {
        return Account.convertToNew(this.mEmailAccountPolicy.getAccountDetails(j));
    }

    public long getAccountId(String str, String str2, String str3) {
        return this.mEmailAccountPolicy.getAccountId(str, str2, str3);
    }

    public Account[] getAllEmailAccounts() {
        return Account.convertToNewArray(this.mEmailAccountPolicy.getAllEmailAccounts());
    }

    public void removePendingAccount(String str, String str2, String str3) {
        this.mEmailAccountPolicy.removePendingAccount(str, str2, str3);
    }

    public void sendAccountsChangedBroadcast() {
        this.mEmailAccountPolicy.sendAccountsChangedBroadcast();
    }

    public boolean setAccountName(String str, long j) {
        return this.mEmailAccountPolicy.setAccountName(str, j);
    }

    public boolean setAlwaysVibrateOnEmailNotification(boolean z, long j) {
        return this.mEmailAccountPolicy.setAlwaysVibrateOnEmailNotification(z, j);
    }

    public boolean setAsDefaultAccount(long j) {
        return this.mEmailAccountPolicy.setAsDefaultAccount(j);
    }

    public boolean setInComingProtocol(String str, long j) {
        return this.mEmailAccountPolicy.setInComingProtocol(str, j);
    }

    public boolean setInComingServerAcceptAllCertificates(boolean z, long j) {
        return this.mEmailAccountPolicy.setInComingServerAcceptAllCertificates(z, j);
    }

    public long setInComingServerAddress(String str, long j) {
        return this.mEmailAccountPolicy.setInComingServerAddress(str, j);
    }

    public boolean setInComingServerPassword(String str, long j) {
        return this.mEmailAccountPolicy.setInComingServerPassword(str, j);
    }

    public boolean setInComingServerPort(int i, long j) {
        return this.mEmailAccountPolicy.setInComingServerPort(i, j);
    }

    public boolean setInComingServerSSL(boolean z, long j) {
        return this.mEmailAccountPolicy.setInComingServerSSL(z, j);
    }

    public boolean setOutGoingServerAcceptAllCertificates(boolean z, long j) {
        return this.mEmailAccountPolicy.setOutGoingServerAcceptAllCertificates(z, j);
    }

    public long setOutGoingServerAddress(String str, long j) {
        return this.mEmailAccountPolicy.setOutGoingServerAddress(str, j);
    }

    public boolean setOutGoingServerPassword(String str, long j) {
        return this.mEmailAccountPolicy.setOutGoingServerPassword(str, j);
    }

    public boolean setOutGoingServerPort(int i, long j) {
        return this.mEmailAccountPolicy.setOutGoingServerPort(i, j);
    }

    public boolean setOutGoingServerSSL(boolean z, long j) {
        return this.mEmailAccountPolicy.setOutGoingServerSSL(z, j);
    }

    public boolean setSenderName(String str, long j) {
        return this.mEmailAccountPolicy.setSenderName(str, j);
    }

    public boolean setSignature(String str, long j) {
        return this.mEmailAccountPolicy.setSignature(str, j);
    }
}
