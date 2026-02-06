package com.samsung.android.knox.license;

import android.content.Context;

public class KnoxEnterpriseLicenseManager {
    public static final String ACTION_LICENSE_STATUS = "com.samsung.android.knox.intent.action.KNOX_LICENSE_STATUS";
    public static final String EXTRA_LICENSE_ACTIVATION_INITIATOR = "com.samsung.android.knox.intent.extra.KNOX_LICENSE_ACTIVATION_INITIATOR";
    public static final String EXTRA_LICENSE_ERROR_CODE = "com.samsung.android.knox.intent.extra.KNOX_LICENSE_ERROR_CODE";
    public static final String EXTRA_LICENSE_RESULT_TYPE = "com.samsung.android.knox.intent.extra.KNOX_LICENSE_RESULT_TYPE";
    public static final String EXTRA_LICENSE_STATUS = "com.samsung.android.knox.intent.extra.KNOX_LICENSE_STATUS";
    private static KnoxEnterpriseLicenseManager mKLM;
    private static com.sec.enterprise.knox.license.KnoxEnterpriseLicenseManager mKnoxEnterpriseLicenseManager;

    private KnoxEnterpriseLicenseManager(com.sec.enterprise.knox.license.KnoxEnterpriseLicenseManager knoxEnterpriseLicenseManager) {
        mKnoxEnterpriseLicenseManager = knoxEnterpriseLicenseManager;
    }

    public static KnoxEnterpriseLicenseManager getInstance(Context context) {
        com.sec.enterprise.knox.license.KnoxEnterpriseLicenseManager knoxEnterpriseLicenseManager;
        if (context == null) {
            return null;
        }
        KnoxEnterpriseLicenseManager knoxEnterpriseLicenseManager2 = mKLM;
        if (knoxEnterpriseLicenseManager2 == null) {
            synchronized (KnoxEnterpriseLicenseManager.class) {
                knoxEnterpriseLicenseManager2 = mKLM;
                if (knoxEnterpriseLicenseManager2 == null && (knoxEnterpriseLicenseManager = com.sec.enterprise.knox.license.KnoxEnterpriseLicenseManager.getInstance(context)) != null) {
                    knoxEnterpriseLicenseManager2 = new KnoxEnterpriseLicenseManager(knoxEnterpriseLicenseManager);
                    mKLM = knoxEnterpriseLicenseManager2;
                }
            }
        }
        return knoxEnterpriseLicenseManager2;
    }

    public void activateLicense(String str) {
        mKnoxEnterpriseLicenseManager.activateLicense(str);
    }

    public void activateLicense(String str, String str2) {
        mKnoxEnterpriseLicenseManager.activateLicense(str, str2);
    }

    public void deActivateLicense(String str) {
        mKnoxEnterpriseLicenseManager.deActivateLicense(str);
    }

    public void deActivateLicense(String str, String str2) {
        mKnoxEnterpriseLicenseManager.deActivateLicense(str, str2);
    }
}
