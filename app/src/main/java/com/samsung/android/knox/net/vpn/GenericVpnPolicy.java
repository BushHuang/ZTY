package com.samsung.android.knox.net.vpn;

import com.samsung.android.knox.keystore.CertificateInfo;
import java.util.List;

public class GenericVpnPolicy {
    public static final String ACTION_BIND_RESULT = "com.samsung.android.knox.intent.action.VPN_BIND_RESULT";
    public static final String ACTION_BIND_RESULT_OLD = "com.samsung.android.mdm.VPN_BIND_RESULT";
    public static final String EXTRA_BIND_CID = "com.samsung.android.knox.intent.extra.VPN_BIND_CID";
    public static final String EXTRA_BIND_CID_OLD = "vpn_bind_cid";
    public static final String EXTRA_BIND_STATUS = "com.samsung.android.knox.intent.extra.VPN_BIND_STATUS";
    public static final String EXTRA_BIND_STATUS_OLD = "vpn_bind_status";
    public static final String EXTRA_BIND_VENDOR = "com.samsung.android.knox.intent.extra.VPN_BIND_VENDOR";
    public static final String EXTRA_BIND_VENDOR_OLD = "vpn_bind_vendor";
    private com.sec.enterprise.knox.GenericVpnPolicy mGenericVpnPolicy;

    public GenericVpnPolicy(com.sec.enterprise.knox.GenericVpnPolicy genericVpnPolicy) {
        this.mGenericVpnPolicy = genericVpnPolicy;
    }

    public int activateVpnProfile(String str, boolean z) {
        return this.mGenericVpnPolicy.activateVpnProfile(str, z);
    }

    public int addAllContainerPackagesToVpn(int i, String str) {
        return this.mGenericVpnPolicy.addAllContainerPackagesToVpn(i, str);
    }

    public int addAllPackagesToVpn(String str) {
        return this.mGenericVpnPolicy.addAllPackagesToVpn(str);
    }

    public int addContainerPackagesToVpn(int i, String[] strArr, String str) {
        return this.mGenericVpnPolicy.addContainerPackagesToVpn(i, strArr, str);
    }

    public int addPackagesToVpn(String[] strArr, String str) {
        return this.mGenericVpnPolicy.addPackagesToVpn(strArr, str);
    }

    public int createVpnProfile(String str) {
        return this.mGenericVpnPolicy.createVpnProfile(str);
    }

    public String[] getAllContainerPackagesInVpnProfile(int i, String str) {
        return this.mGenericVpnPolicy.getAllContainerPackagesInVpnProfile(i, str);
    }

    public String[] getAllPackagesInVpnProfile(String str) {
        return this.mGenericVpnPolicy.getAllPackagesInVpnProfile(str);
    }

    public List<String> getAllVpnProfiles() {
        return this.mGenericVpnPolicy.getAllVpnProfiles();
    }

    public CertificateInfo getCACertificate(String str) {
        return CertificateInfo.convertToNew(this.mGenericVpnPolicy.getCACertificate(str));
    }

    public String getErrorString(String str) {
        return this.mGenericVpnPolicy.getErrorString(str);
    }

    public int getState(String str) {
        return this.mGenericVpnPolicy.getState(str);
    }

    public CertificateInfo getUserCertificate(String str) {
        return CertificateInfo.convertToNew(this.mGenericVpnPolicy.getUserCertificate(str));
    }

    public int getVpnModeOfOperation(String str) {
        return this.mGenericVpnPolicy.getVpnModeOfOperation(str);
    }

    public String getVpnProfile(String str) {
        return this.mGenericVpnPolicy.getVpnProfile(str);
    }

    public int removeAllContainerPackagesFromVpn(int i, String str) {
        return this.mGenericVpnPolicy.removeAllContainerPackagesFromVpn(i, str);
    }

    public int removeAllPackagesFromVpn(String str) {
        return this.mGenericVpnPolicy.removeAllPackagesFromVpn(str);
    }

    public int removeContainerPackagesFromVpn(int i, String[] strArr, String str) {
        return this.mGenericVpnPolicy.removeContainerPackagesFromVpn(i, strArr, str);
    }

    public int removePackagesFromVpn(String[] strArr, String str) {
        return this.mGenericVpnPolicy.removePackagesFromVpn(strArr, str);
    }

    public int removeVpnProfile(String str) {
        return this.mGenericVpnPolicy.removeVpnProfile(str);
    }

    public boolean setAutoRetryOnConnectionError(String str, boolean z) {
        return this.mGenericVpnPolicy.setAutoRetryOnConnectionError(str, z);
    }

    public boolean setCACertificate(String str, byte[] bArr) {
        return this.mGenericVpnPolicy.setCACertificate(str, bArr);
    }

    public boolean setServerCertValidationUserAcceptanceCriteria(String str, boolean z, List<Integer> list, int i) {
        return this.mGenericVpnPolicy.setServerCertValidationUserAcceptanceCriteria(str, z, list, i);
    }

    public boolean setUserCertificate(String str, byte[] bArr, String str2) {
        return this.mGenericVpnPolicy.setUserCertificate(str, bArr, str2);
    }

    public int setVpnModeOfOperation(String str, int i) {
        return this.mGenericVpnPolicy.setVpnModeOfOperation(str, i);
    }
}
