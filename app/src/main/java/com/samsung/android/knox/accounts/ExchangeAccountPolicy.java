package com.samsung.android.knox.accounts;

import com.samsung.android.knox.SupportLibUtils;

public class ExchangeAccountPolicy {
    public static final String ACTION_CBA_INSTALL_STATUS = "com.samsung.android.knox.intent.action.CBA_INSTALL_STATUS";
    public static final String ACTION_ENFORCE_SMIME_ALIAS_RESULT = "com.samsung.android.knox.intent.action.ENFORCE_SMIME_ALIAS_RESULT";
    public static final String ACTION_EXCHANGE_ACCOUNT_ADD_RESULT = "com.samsung.android.knox.intent.action.EXCHANGE_ACCOUNT_ADD_RESULT";
    public static final String ACTION_EXCHANGE_ACCOUNT_DELETE_RESULT = "com.samsung.android.knox.intent.action.EXCHANGE_ACCOUNT_DELETE_RESULT";
    public static final String EXTRA_ACCOUNT_ID = "com.samsung.android.knox.intent.extra.ACCOUNT_ID";
    public static final String EXTRA_EMAIL_ADDRESS = "com.samsung.android.knox.intent.extra.EMAIL_ADDRESS";
    public static final String EXTRA_ENFORCE_SMIME_ALIAS_TYPE = "com.samsung.android.knox.intent.extra.ENFORCE_SMIME_ALIAS_TYPE";
    public static final String EXTRA_RESULT = "com.samsung.android.knox.intent.extra.RESULT";
    public static final String EXTRA_SERVER_ADDRESS = "com.samsung.android.knox.intent.extra.SERVER_ADDRESS";
    public static final String EXTRA_SMIME_RESULT = "com.samsung.android.knox.intent.extra.SMIME_RESULT";
    public static final String EXTRA_STATUS = "com.samsung.android.knox.intent.extra.STATUS";
    private android.app.enterprise.ExchangeAccountPolicy mExchangeAccountPolicy;

    public ExchangeAccountPolicy(android.app.enterprise.ExchangeAccountPolicy exchangeAccountPolicy) {
        this.mExchangeAccountPolicy = exchangeAccountPolicy;
    }

    public long addNewAccount(ExchangeAccount exchangeAccount) {
        try {
            return this.mExchangeAccountPolicy.addNewAccount(ExchangeAccount.convertToOld(exchangeAccount));
        } catch (NoSuchFieldError e) {
            throw new NoSuchFieldError(e.getMessage());
        }
    }

    public boolean allowInComingAttachments(boolean z, long j) {
        return this.mExchangeAccountPolicy.allowInComingAttachments(z, j);
    }

    public boolean deleteAccount(long j) {
        return this.mExchangeAccountPolicy.deleteAccount(j);
    }

    public Account getAccountDetails(long j) {
        return Account.convertToNew(this.mExchangeAccountPolicy.getAccountDetails(j));
    }

    public long getAccountId(String str, String str2, String str3) {
        return this.mExchangeAccountPolicy.getAccountId(str, str2, str3);
    }

    public Account[] getAllEASAccounts() {
        return Account.convertToNewArray(this.mExchangeAccountPolicy.getAllEASAccounts());
    }

    public String getDeviceId() {
        return this.mExchangeAccountPolicy.getDeviceId();
    }

    public int getIncomingAttachmentsSize(long j) {
        return this.mExchangeAccountPolicy.getIncomingAttachmentsSize(j);
    }

    public int getMaxCalendarAgeFilter(long j) {
        return this.mExchangeAccountPolicy.getMaxCalendarAgeFilter(j);
    }

    public int getMaxEmailAgeFilter(long j) {
        return this.mExchangeAccountPolicy.getMaxEmailAgeFilter(j);
    }

    public int getMaxEmailBodyTruncationSize(long j) {
        return this.mExchangeAccountPolicy.getMaxEmailBodyTruncationSize(j);
    }

    public int getMaxEmailHTMLBodyTruncationSize(long j) {
        return this.mExchangeAccountPolicy.getMaxEmailHTMLBodyTruncationSize(j);
    }

    public boolean getRequireEncryptedSMIMEMessages(long j) {
        return this.mExchangeAccountPolicy.getRequireEncryptedSMIMEMessages(j);
    }

    public boolean getRequireSignedSMIMEMessages(long j) {
        return this.mExchangeAccountPolicy.getRequireSignedSMIMEMessages(j);
    }

    public String getSMIMECertificateAlias(long j, int i) {
        try {
            return this.mExchangeAccountPolicy.getSMIMECertificateAlias(j, i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ExchangeAccountPolicy.class, "getSMIMECertificateAlias", new Class[]{Long.TYPE, Integer.TYPE}, 15));
        }
    }

    public boolean isIncomingAttachmentsAllowed(long j) {
        return this.mExchangeAccountPolicy.isIncomingAttachmentsAllowed(j);
    }

    public void removePendingAccount(String str, String str2, String str3, String str4) {
        this.mExchangeAccountPolicy.removePendingAccount(str, str2, str3, str4);
    }

    public void sendAccountsChangedBroadcast() {
        this.mExchangeAccountPolicy.sendAccountsChangedBroadcast();
    }

    public boolean setAcceptAllCertificates(boolean z, long j) {
        return this.mExchangeAccountPolicy.setAcceptAllCertificates(z, j);
    }

    public long setAccountBaseParameters(String str, String str2, String str3, String str4, long j) {
        return this.mExchangeAccountPolicy.setAccountBaseParameters(str, str2, str3, str4, j);
    }

    public boolean setAccountName(String str, long j) {
        return this.mExchangeAccountPolicy.setAccountName(str, j);
    }

    public boolean setAlwaysVibrateOnEmailNotification(boolean z, long j) {
        return this.mExchangeAccountPolicy.setAlwaysVibrateOnEmailNotification(z, j);
    }

    public boolean setAsDefaultAccount(long j) {
        return this.mExchangeAccountPolicy.setAsDefaultAccount(j);
    }

    public void setClientAuthCert(byte[] bArr, String str, long j) {
        this.mExchangeAccountPolicy.setClientAuthCert(bArr, str, j);
    }

    public boolean setDataSyncs(boolean z, boolean z2, boolean z3, boolean z4, long j) {
        return this.mExchangeAccountPolicy.setDataSyncs(z, z2, z3, z4, j);
    }

    public boolean setForceSMIMECertificateAlias(long j, String str, int i) {
        try {
            return this.mExchangeAccountPolicy.setForceSMIMECertificateAlias(j, str, i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ExchangeAccountPolicy.class, "setForceSMIMECertificateAlias", new Class[]{Long.TYPE, String.class, Integer.TYPE}, 15));
        }
    }

    public boolean setForceSMIMECertificateAlias(long j, String str, String str2, int i) {
        try {
            return this.mExchangeAccountPolicy.setForceSMIMECertificateAlias(j, str, str2, i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ExchangeAccountPolicy.class, "setForceSMIMECertificateAlias", new Class[]{Long.TYPE, String.class, String.class, Integer.TYPE}, 19));
        }
    }

    public boolean setIncomingAttachmentsSize(int i, long j) {
        return this.mExchangeAccountPolicy.setIncomingAttachmentsSize(i, j);
    }

    public boolean setMaxCalendarAgeFilter(int i, long j) {
        return this.mExchangeAccountPolicy.setMaxCalendarAgeFilter(i, j);
    }

    public boolean setMaxEmailAgeFilter(int i, long j) {
        return this.mExchangeAccountPolicy.setMaxEmailAgeFilter(i, j);
    }

    public boolean setMaxEmailBodyTruncationSize(int i, long j) {
        return this.mExchangeAccountPolicy.setMaxEmailBodyTruncationSize(i, j);
    }

    public boolean setMaxEmailHTMLBodyTruncationSize(int i, long j) {
        return this.mExchangeAccountPolicy.setMaxEmailHTMLBodyTruncationSize(i, j);
    }

    public boolean setPassword(String str, long j) {
        return this.mExchangeAccountPolicy.setPassword(str, j);
    }

    public boolean setPastDaysToSync(int i, long j) {
        return this.mExchangeAccountPolicy.setPastDaysToSync(i, j);
    }

    public boolean setRequireEncryptedSMIMEMessages(long j, boolean z) {
        return this.mExchangeAccountPolicy.setRequireEncryptedSMIMEMessages(j, z);
    }

    public boolean setRequireSignedSMIMEMessages(long j, boolean z) {
        return this.mExchangeAccountPolicy.setRequireSignedSMIMEMessages(j, z);
    }

    public boolean setSSL(boolean z, long j) {
        return this.mExchangeAccountPolicy.setSSL(z, j);
    }

    public boolean setSignature(String str, long j) {
        return this.mExchangeAccountPolicy.setSignature(str, j);
    }

    public boolean setSyncPeakTimings(int i, int i2, int i3, long j) {
        return this.mExchangeAccountPolicy.setSyncPeakTimings(i, i2, i3, j);
    }

    public boolean setSyncSchedules(int i, int i2, int i3, long j) {
        return this.mExchangeAccountPolicy.setSyncSchedules(i, i2, i3, j);
    }
}
