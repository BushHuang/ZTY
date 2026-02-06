package com.samsung.android.knox.net.vpn;

import java.util.List;

public class VpnPolicy {
    private android.app.enterprise.VpnPolicy mVpnPolicy;

    public VpnPolicy(android.app.enterprise.VpnPolicy vpnPolicy) {
        this.mVpnPolicy = vpnPolicy;
    }

    public boolean allowOnlySecureConnections(boolean z) {
        return this.mVpnPolicy.allowOnlySecureConnections(z);
    }

    public boolean allowUserAddProfiles(boolean z) {
        return this.mVpnPolicy.allowUserAddProfiles(z);
    }

    public boolean allowUserChangeProfiles(boolean z) {
        return this.mVpnPolicy.allowUserChangeProfiles(z);
    }

    public boolean allowUserSetAlwaysOn(boolean z) {
        return this.mVpnPolicy.allowUserSetAlwaysOn(z);
    }

    public boolean createProfile(VpnAdminProfile vpnAdminProfile) {
        try {
            return this.mVpnPolicy.createProfile(VpnAdminProfile.convertToOld(vpnAdminProfile));
        } catch (NoSuchFieldError e) {
            throw new NoSuchFieldError(e.getMessage());
        }
    }

    public void deleteProfile(String str) {
        this.mVpnPolicy.deleteProfile(str);
    }

    public String getAlwaysOnProfile() {
        return this.mVpnPolicy.getAlwaysOnProfile();
    }

    public List<String> getDnsDomains(String str) {
        return this.mVpnPolicy.getDnsDomains(str);
    }

    public List<String> getDnsServers(String str) {
        return this.mVpnPolicy.getDnsServers(str);
    }

    public List<String> getForwardRoutes(String str) {
        return this.mVpnPolicy.getForwardRoutes(str);
    }

    public String getIPSecCaCertificate(String str) {
        return this.mVpnPolicy.getIPSecCaCertificate(str);
    }

    public String getIPSecPreSharedKey(String str) {
        return this.mVpnPolicy.getIPSecPreSharedKey(str);
    }

    public String getIPSecUserCertificate(String str) {
        return this.mVpnPolicy.getIPSecUserCertificate(str);
    }

    public String getId(String str) {
        return this.mVpnPolicy.getId(str);
    }

    public String getIpSecIdentifier(String str) {
        return this.mVpnPolicy.getIpSecIdentifier(str);
    }

    public String getL2TPSecret(String str) {
        return this.mVpnPolicy.getL2TPSecret(str);
    }

    public String getOcspServerUrl(String str) {
        return this.mVpnPolicy.getOcspServerUrl(str);
    }

    public String getServerName(String str) {
        return this.mVpnPolicy.getServerName(str);
    }

    public String getState(String str) {
        return this.mVpnPolicy.getState(str);
    }

    public List<String> getSupportedConnectionTypes() {
        return this.mVpnPolicy.getSupportedConnectionTypes();
    }

    public String getType(String str) {
        return this.mVpnPolicy.getType(str);
    }

    public String getUserName(String str) {
        return this.mVpnPolicy.getUserName(str);
    }

    public String getUserPassword(String str) {
        return this.mVpnPolicy.getUserPassword(str);
    }

    public String[] getVpnList() {
        return this.mVpnPolicy.getVpnList();
    }

    public boolean isAdminProfile(String str) {
        return this.mVpnPolicy.isAdminProfile(str);
    }

    public boolean isOnlySecureConnectionsAllowed() {
        return this.mVpnPolicy.isOnlySecureConnectionsAllowed();
    }

    public boolean isPPTPEncryptionEnabled(String str) {
        return this.mVpnPolicy.isPPTPEncryptionEnabled(str);
    }

    public boolean isUserAddProfilesAllowed() {
        return this.mVpnPolicy.isUserAddProfilesAllowed();
    }

    public boolean isUserChangeProfilesAllowed() {
        return this.mVpnPolicy.isUserChangeProfilesAllowed();
    }

    public boolean isUserSetAlwaysOnAllowed() {
        return this.mVpnPolicy.isUserSetAlwaysOnAllowed();
    }

    public boolean setAlwaysOnProfile(String str) {
        return this.mVpnPolicy.setAlwaysOnProfile(str);
    }

    public boolean setDnsDomains(String str, List<String> list) {
        return this.mVpnPolicy.setDnsDomains(str, list);
    }

    public boolean setDnsServers(String str, List<String> list) {
        return this.mVpnPolicy.setDnsServers(str, list);
    }

    public boolean setForwardRoutes(String str, List<String> list) {
        return this.mVpnPolicy.setForwardRoutes(str, list);
    }

    public boolean setIPSecCaCertificate(String str, String str2) {
        return this.mVpnPolicy.setIPSecCaCertificate(str, str2);
    }

    public boolean setIPSecPreSharedKey(String str, String str2) {
        return this.mVpnPolicy.setIPSecPreSharedKey(str, str2);
    }

    public boolean setIPSecUserCertificate(String str, String str2) {
        return this.mVpnPolicy.setIPSecUserCertificate(str, str2);
    }

    public void setId(String str, String str2) {
        this.mVpnPolicy.setId(str, str2);
    }

    public boolean setIpSecIdentifier(String str, String str2) {
        return this.mVpnPolicy.setIpSecIdentifier(str, str2);
    }

    public boolean setL2TPSecret(String str, boolean z, String str2) {
        return this.mVpnPolicy.setL2TPSecret(str, z, str2);
    }

    public boolean setOcspServerUrl(String str, String str2) {
        return this.mVpnPolicy.setOcspServerUrl(str, str2);
    }

    public boolean setPPTPEncryptionEnabled(String str, boolean z) {
        return this.mVpnPolicy.setPPTPEncryptionEnabled(str, z);
    }

    public void setProfileName(String str, String str2) {
        this.mVpnPolicy.setProfileName(str, str2);
    }

    public void setServerName(String str, String str2) {
        this.mVpnPolicy.setServerName(str, str2);
    }

    public boolean setUserName(String str, String str2) {
        return this.mVpnPolicy.setUserName(str, str2);
    }

    public boolean setUserPassword(String str, String str2) {
        return this.mVpnPolicy.setUserPassword(str, str2);
    }
}
