package com.samsung.android.knox.accounts;

import java.util.List;

public class LDAPAccountPolicy {
    public static final String ACTION_LDAP_CREATE_ACCT_RESULT = "com.samsung.android.knox.intent.action.LDAP_CREATE_ACCT_RESULT";
    public static final String EXTRA_LDAP_ACCT_ID = "com.samsung.android.knox.intent.extra.LDAP_ACCT_ID";
    public static final String EXTRA_LDAP_RESULT = "com.samsung.android.knox.intent.extra.LDAP_RESULT";
    public static final String EXTRA_LDAP_USER_ID = "com.samsung.android.knox.intent.extra.LDAP_USER_ID";
    private android.app.enterprise.LDAPAccountPolicy mLDAPAccountPolicy;

    public LDAPAccountPolicy(android.app.enterprise.LDAPAccountPolicy lDAPAccountPolicy) {
        this.mLDAPAccountPolicy = lDAPAccountPolicy;
    }

    public void createLDAPAccount(LDAPAccount lDAPAccount) {
        this.mLDAPAccountPolicy.createLDAPAccount(LDAPAccount.convertToOld(lDAPAccount));
    }

    public boolean deleteLDAPAccount(long j) {
        return this.mLDAPAccountPolicy.deleteLDAPAccount(j);
    }

    public List<LDAPAccount> getAllLDAPAccounts() {
        return LDAPAccount.convertToNewList(this.mLDAPAccountPolicy.getAllLDAPAccounts());
    }

    public LDAPAccount getLDAPAccount(long j) {
        return LDAPAccount.convertToNew(this.mLDAPAccountPolicy.getLDAPAccount(j));
    }
}
