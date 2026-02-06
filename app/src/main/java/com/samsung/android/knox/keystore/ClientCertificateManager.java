package com.samsung.android.knox.keystore;

import com.samsung.android.knox.SupportLibUtils;

public class ClientCertificateManager {
    private com.sec.enterprise.knox.ccm.ClientCertificateManager mClientCertificateManager;

    public ClientCertificateManager(com.sec.enterprise.knox.ccm.ClientCertificateManager clientCertificateManager) {
        this.mClientCertificateManager = clientCertificateManager;
    }

    public boolean addPackageToExemptList(String str) {
        try {
            return this.mClientCertificateManager.addPackageToExemptList(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ClientCertificateManager.class, "addPackageToExemptList", new Class[]{String.class}, 12));
        }
    }

    public boolean deleteCCMProfile() {
        try {
            return this.mClientCertificateManager.deleteCCMProfile();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ClientCertificateManager.class, "deleteCCMProfile", null, 12));
        }
    }

    public boolean deleteCSRProfile(String str) {
        try {
            return this.mClientCertificateManager.deleteCSRProfile(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ClientCertificateManager.class, "deleteCSRProfile", new Class[]{String.class}, 12));
        }
    }

    public boolean deleteCertificate(String str) {
        try {
            return this.mClientCertificateManager.deleteCertificate(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ClientCertificateManager.class, "deleteCertificate", new Class[]{String.class}, 12));
        }
    }

    public byte[] generateCSRUsingTemplate(String str, String str2, String str3) {
        try {
            return this.mClientCertificateManager.generateCSRUsingTemplate(str, str2, str3);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ClientCertificateManager.class, "generateCSRUsingTemplate", new Class[]{String.class, String.class, String.class}, 12));
        }
    }

    public CCMProfile getCCMProfile() {
        try {
            return CCMProfile.convertToNew(this.mClientCertificateManager.getCCMProfile());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ClientCertificateManager.class, "getCCMProfile", null, 12));
        }
    }

    public String getCCMVersion() {
        try {
            return this.mClientCertificateManager.getCCMVersion();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ClientCertificateManager.class, "getCCMVersion", null, 12));
        }
    }

    public String getDefaultCertificateAlias() {
        try {
            return this.mClientCertificateManager.getDefaultCertificateAlias();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ClientCertificateManager.class, "getDefaultCertificateAlias", null, 12));
        }
    }

    public boolean installCertificate(CertificateProfile certificateProfile, byte[] bArr, String str) {
        try {
            return this.mClientCertificateManager.installCertificate(CertificateProfile.convertToOld(certificateProfile), bArr, str);
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public boolean isCCMPolicyEnabledForPackage(String str) {
        try {
            return this.mClientCertificateManager.isCCMPolicyEnabledForPackage(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ClientCertificateManager.class, "isCCMPolicyEnabledForPackage", new Class[]{String.class}, 12));
        }
    }

    public boolean removePackageFromExemptList(String str) {
        try {
            return this.mClientCertificateManager.removePackageFromExemptList(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ClientCertificateManager.class, "removePackageFromExemptList", new Class[]{String.class}, 12));
        }
    }

    public boolean setCCMProfile(CCMProfile cCMProfile) {
        try {
            return this.mClientCertificateManager.setCCMProfile(CCMProfile.convertToOld(cCMProfile));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public boolean setCSRProfile(CSRProfile cSRProfile) {
        try {
            return this.mClientCertificateManager.setCSRProfile(CSRProfile.convertToOld(cSRProfile));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }
}
