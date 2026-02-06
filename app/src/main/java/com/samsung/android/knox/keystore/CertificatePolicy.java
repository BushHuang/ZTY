package com.samsung.android.knox.keystore;

import com.samsung.android.knox.SupportLibUtils;
import java.security.cert.X509Certificate;
import java.util.List;

public class CertificatePolicy {
    public static final String ACTION_CERTIFICATE_FAILURE = "com.samsung.android.knox.intent.action.CERTIFICATE_FAILURE";
    public static final String ACTION_CERTIFICATE_REMOVED = "com.samsung.android.knox.intent.action.CERTIFICATE_REMOVED";
    public static final String ACTION_REFRESH_CREDENTIALS_UI_INTERNAL = "com.samsung.android.knox.intent.action.REFRESH_CREDENTIALS_UI_INTERNAL";
    public static final String APP_INFO_PKGNAME = "appInfoPkgName";
    public static final String BROWSER_MODULE = "browser_module";
    public static final int CERTIFICATE_FAIL_ALG_NON_FIPS_APPROVED = 9;
    public static final int CERTIFICATE_FAIL_ALTSUBJECT_MISMATCH = 6;
    public static final int CERTIFICATE_FAIL_BAD_CERTIFICATE = 7;
    public static final int CERTIFICATE_FAIL_EXPIRED = 4;
    public static final int CERTIFICATE_FAIL_INSTALL_PARSE_CERTIFICATE_ENCODING = 11;
    public static final int CERTIFICATE_FAIL_INSTALL_PARSE_INCONSISTENT_CERTIFICATES = 12;
    public static final int CERTIFICATE_FAIL_INSTALL_PARSE_NO_CERTIFICATES = 10;
    public static final int CERTIFICATE_FAIL_NOT_YET_VALID = 3;
    public static final int CERTIFICATE_FAIL_REVOKED = 2;
    public static final int CERTIFICATE_FAIL_SERVER_CHAIN_PROBE = 8;
    public static final int CERTIFICATE_FAIL_SUBJECT_MISMATCH = 5;
    public static final int CERTIFICATE_FAIL_UNABLE_TO_CHECK_REVOCATION_STATUS = 13;
    public static final int CERTIFICATE_FAIL_UNSPECIFIED = 0;
    public static final int CERTIFICATE_FAIL_UNTRUSTED = 1;
    public static final int CERTIFICATE_VALIDATED_SUCCESSFULLY = -1;
    public static final String EXTRA_CERTIFICATE_FAILURE_MESSAGE = "com.samsung.android.knox.intent.extra.CERTIFICATE_FAILURE_MESSAGE";
    public static final String EXTRA_CERTIFICATE_FAILURE_MODULE = "com.samsung.android.knox.intent.extra.CERTIFICATE_FAILURE_MODULE";
    public static final String EXTRA_CERTIFICATE_REMOVED_SUBJECT = "com.samsung.android.knox.intent.extra.CERTIFICATE_REMOVED_SUBJECT";
    public static final String EXTRA_USER_ID = "com.samsung.android.knox.intent.extra.USER_ID";
    public static final String INSTALLER_MODULE = "installer_module";
    public static final String IS_MARKET_INSTALLATION = "isMarketInstallation";
    public static final String PACKAGE_MODULE = "package_manager_module";
    public static final String WIFI_MODULE = "wifi_module";
    private com.sec.enterprise.knox.certificate.CertificatePolicy mCertificatePolicy;

    public CertificatePolicy(com.sec.enterprise.knox.certificate.CertificatePolicy certificatePolicy) {
        this.mCertificatePolicy = certificatePolicy;
    }

    public boolean addPermissionApplicationPrivateKey(PermissionApplicationPrivateKey permissionApplicationPrivateKey) {
        try {
            return this.mCertificatePolicy.addPermissionApplicationPrivateKey(PermissionApplicationPrivateKey.convertToOld(permissionApplicationPrivateKey));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public boolean addTrustedCaCertificateList(List<X509Certificate> list) {
        return this.mCertificatePolicy.addTrustedCaCertificateList(list);
    }

    public boolean addUntrustedCertificateList(List<X509Certificate> list) {
        return this.mCertificatePolicy.addUntrustedCertificateList(list);
    }

    public boolean allowUserRemoveCertificates(boolean z) {
        return this.mCertificatePolicy.allowUserRemoveCertificates(z);
    }

    public boolean clearPermissionApplicationPrivateKey() {
        return this.mCertificatePolicy.clearPermissionApplicationPrivateKey();
    }

    public boolean clearTrustedCaCertificateList() {
        return this.mCertificatePolicy.clearTrustedCaCertificateList();
    }

    public boolean clearUntrustedCertificateList() {
        return this.mCertificatePolicy.clearUntrustedCertificateList();
    }

    public boolean enableCertificateFailureNotification(boolean z) {
        return this.mCertificatePolicy.enableCertificateFailureNotification(z);
    }

    public boolean enableCertificateValidationAtInstall(boolean z) {
        return this.mCertificatePolicy.enableCertificateValidationAtInstall(z);
    }

    public boolean enableOcspCheck(String str, boolean z) {
        return this.mCertificatePolicy.enableOcspCheck(str, z);
    }

    public boolean enableRevocationCheck(String str, boolean z) {
        return this.mCertificatePolicy.enableRevocationCheck(str, z);
    }

    public List<PermissionApplicationPrivateKey> getListPermissionApplicationPrivateKey() {
        return PermissionApplicationPrivateKey.convertToNewList(this.mCertificatePolicy.getListPermissionApplicationPrivateKey());
    }

    public List<CertificateControlInfo> getTrustedCaCertificateList() {
        return CertificateControlInfo.convertToNewList(this.mCertificatePolicy.getTrustedCaCertificateList());
    }

    public List<CertificateControlInfo> getUntrustedCertificateList() {
        return CertificateControlInfo.convertToNewList(this.mCertificatePolicy.getUntrustedCertificateList());
    }

    public boolean isCertificateFailureNotificationEnabled() {
        return this.mCertificatePolicy.isCertificateFailureNotificationEnabled();
    }

    public boolean isCertificateValidationAtInstallEnabled() {
        return this.mCertificatePolicy.isCertificateValidationAtInstallEnabled();
    }

    public boolean isNonTrustedAppInstallBlocked() {
        try {
            return this.mCertificatePolicy.isNonTrustedAppInstallBlocked();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(CertificatePolicy.class, "isNonTrustedAppInstallBlocked", null, 13));
        }
    }

    public boolean isOcspCheckEnabled(String str) {
        return this.mCertificatePolicy.isOcspCheckEnabled(str);
    }

    public boolean isRevocationCheckEnabled(String str) {
        return this.mCertificatePolicy.isRevocationCheckEnabled(str);
    }

    public boolean isUserRemoveCertificatesAllowed() {
        return this.mCertificatePolicy.isUserRemoveCertificatesAllowed();
    }

    public boolean removePermissionApplicationPrivateKey(PermissionApplicationPrivateKey permissionApplicationPrivateKey) {
        try {
            return this.mCertificatePolicy.removePermissionApplicationPrivateKey(PermissionApplicationPrivateKey.convertToOld(permissionApplicationPrivateKey));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public boolean removeTrustedCaCertificateList(List<X509Certificate> list) {
        return this.mCertificatePolicy.removeTrustedCaCertificateList(list);
    }

    public boolean removeUntrustedCertificateList(List<X509Certificate> list) {
        return this.mCertificatePolicy.removeUntrustedCertificateList(list);
    }

    public boolean setNonTrustedAppInstallBlock(boolean z) {
        try {
            return this.mCertificatePolicy.setNonTrustedAppInstallBlock(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(CertificatePolicy.class, "setNonTrustedAppInstallBlock", new Class[]{Boolean.TYPE}, 13));
        }
    }
}
