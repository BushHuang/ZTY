package com.samsung.android.knox.keystore;

import android.app.enterprise.SecurityPolicy;
import java.util.List;

public class CertificateProvisioning {
    public static final String CA_CERTIFICATE = "CACERT_";
    public static final int ERROR_KEYSTORE_KEY_NOT_FOUND = 7;
    public static final int ERROR_KEYSTORE_LOCKED = 2;
    public static final int ERROR_KEYSTORE_NONE = 1;
    public static final int ERROR_KEYSTORE_PERMISSION_DENIED = 6;
    public static final int ERROR_KEYSTORE_SYSTEM = 5;
    public static final int ERROR_KEYSTORE_UNDEFINED_ACTION = 9;
    public static final int ERROR_KEYSTORE_UNINITIALIZED = 3;
    public static final int ERROR_KEYSTORE_VALUE_CORRUPTED = 8;
    public static final int ERROR_KEYSTORE_WRONG_PASSWORD = 10;
    public static final int GLOBAL_KEYSTORE_PARAMS = 2;
    public static final int KEYSTORE_DEFAULT = 1;
    public static final int KEYSTORE_FOR_VPN_AND_APPS = 4;
    public static final int KEYSTORE_FOR_WIFI = 2;
    public static final String TYPE_CERTIFICATE = "CERT";
    public static final String TYPE_PKCS12 = "PKCS12";
    public static final String USER_CERTIFICATE = "USRCERT_";
    public static final int USER_KEYSTORE_PARAMS = 5;
    private SecurityPolicy mSecurityPolicy;

    public CertificateProvisioning(SecurityPolicy securityPolicy) {
        this.mSecurityPolicy = securityPolicy;
    }

    public boolean deleteCertificateFromKeystore(CertificateInfo certificateInfo, int i) {
        return this.mSecurityPolicy.deleteCertificateFromKeystore(CertificateInfo.convertToOld(certificateInfo), i);
    }

    public List<CertificateInfo> getCertificatesFromKeystore(int i) {
        return CertificateInfo.convertToNewList(this.mSecurityPolicy.getCertificatesFromKeystore(i));
    }

    public int getCredentialStorageStatus() {
        return this.mSecurityPolicy.getCredentialStorageStatus();
    }

    public List<CertificateInfo> getSystemCertificates() {
        return CertificateInfo.convertToNewList(this.mSecurityPolicy.getSystemCertificates());
    }

    public boolean installCertificateToKeystore(String str, byte[] bArr, String str2, String str3, int i) {
        return this.mSecurityPolicy.installCertificateToKeystore(str, bArr, str2, str3, i);
    }

    public void installCertificateWithType(String str, byte[] bArr) {
        this.mSecurityPolicy.installCertificateWithType(str, bArr);
    }

    public void installCertificatesFromSdCard() {
        this.mSecurityPolicy.installCertificatesFromSdCard();
    }

    public boolean resetCredentialStorage() {
        return this.mSecurityPolicy.resetCredentialStorage();
    }

    public boolean unlockCredentialStorage(String str) {
        return this.mSecurityPolicy.unlockCredentialStorage(str);
    }
}
