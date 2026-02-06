package com.samsung.android.knox.ucm.core;

import android.os.Bundle;
import com.samsung.android.knox.EnterpriseDeviceManager;
import com.samsung.android.knox.SupportLibUtils;
import com.samsung.android.knox.ucm.configurator.UniversalCredentialManager;
import java.security.Provider;

public class UniversalCredentialUtil {
    public static final String AGENT_IS_GENERATE_PASSWORD_AVAILABLE = "isGeneratePasswordAvailable";
    public static final String AGENT_IS_PUK_SUPPORTED = "isPUKSupported";
    private final com.sec.enterprise.knox.ucm.core.UniversalCredentialUtil mUniversalCredentialUtil;

    private UniversalCredentialUtil(com.sec.enterprise.knox.ucm.core.UniversalCredentialUtil universalCredentialUtil) {
        this.mUniversalCredentialUtil = universalCredentialUtil;
    }

    public static UniversalCredentialUtil getInstance() {
        try {
            com.sec.enterprise.knox.ucm.core.UniversalCredentialUtil universalCredentialUtil = com.sec.enterprise.knox.ucm.core.UniversalCredentialUtil.getInstance();
            if (universalCredentialUtil == null) {
                return null;
            }
            return new UniversalCredentialUtil(universalCredentialUtil);
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(UniversalCredentialUtil.class, 19));
        }
    }

    public static String getKeychainUri(String str, String str2) {
        try {
            return com.sec.enterprise.knox.ucm.core.UniversalCredentialUtil.getKeychainUri(str, str2);
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(UniversalCredentialUtil.class, 19));
        }
    }

    public static String getKeychainUri(String str, String str2, int i) {
        try {
            return com.sec.enterprise.knox.ucm.core.UniversalCredentialUtil.getKeychainUri(str, str2, i);
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(UniversalCredentialUtil.class, 19));
        }
    }

    public static String getRawAlias(String str) {
        try {
            return com.sec.enterprise.knox.ucm.core.UniversalCredentialUtil.getRawAlias(str);
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(UniversalCredentialUtil.class, 19));
        }
    }

    public static String getSource(String str) {
        try {
            return com.sec.enterprise.knox.ucm.core.UniversalCredentialUtil.getSource(str);
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(UniversalCredentialUtil.class, 19));
        }
    }

    public static boolean isValidUri(String str) {
        try {
            return com.sec.enterprise.knox.ucm.core.UniversalCredentialUtil.isValidUri(str);
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(UniversalCredentialUtil.class, 19));
        }
    }

    private void verifyBundle(Bundle bundle) {
        if (bundle != null && EnterpriseDeviceManager.getAPILevel() < 20) {
            if (bundle.get("isGeneratePasswordAvailable") != null) {
                throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(UniversalCredentialManager.class, "isGeneratePasswordAvailable", 20));
            }
            if (bundle.get("isPUKSupported") != null) {
                throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(UniversalCredentialManager.class, "isPUKSupported", 20));
            }
        }
    }

    public Bundle APDUCommand(String str, byte[] bArr, Bundle bundle) {
        try {
            verifyBundle(bundle);
            return this.mUniversalCredentialUtil.APDUCommand(str, bArr, bundle);
        } catch (NoSuchFieldError e) {
            throw new NoSuchFieldError(e.getMessage());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(UniversalCredentialUtil.class, "APDUCommand", new Class[]{String.class, byte[].class, Bundle.class}, 19));
        }
    }

    public Bundle changePin(String str, String str2, String str3) {
        try {
            return this.mUniversalCredentialUtil.changePin(str, str2, str3);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(UniversalCredentialUtil.class, "changePin", new Class[]{String.class, String.class, String.class}, 19));
        }
    }

    public Bundle getInfo(String str) {
        try {
            return this.mUniversalCredentialUtil.getInfo(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(UniversalCredentialUtil.class, "getInfo", new Class[]{String.class}, 19));
        }
    }

    public Provider[] getProviders() {
        try {
            return this.mUniversalCredentialUtil.getProviders();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(UniversalCredentialUtil.class, "getProviders", null, 19));
        }
    }
}
