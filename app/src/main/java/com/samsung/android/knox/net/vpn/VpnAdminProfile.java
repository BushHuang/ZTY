package com.samsung.android.knox.net.vpn;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class VpnAdminProfile implements Parcelable {
    public static final Parcelable.Creator<VpnAdminProfile> CREATOR = new Parcelable.Creator<VpnAdminProfile>() {
        @Override
        public VpnAdminProfile createFromParcel(Parcel parcel) {
            return new VpnAdminProfile(parcel);
        }

        @Override
        public VpnAdminProfile[] newArray(int i) {
            return new VpnAdminProfile[i];
        }
    };
    public static final String VPN_TYPE_IPSEC_HYBRID_RSA = "IPSEC_HYBRID_RSA";
    public static final String VPN_TYPE_IPSEC_IKEV2_PSK = "IPSEC_IKEV2_PSK";
    public static final String VPN_TYPE_IPSEC_IKEV2_RSA = "IPSEC_IKEV2_RSA";
    public static final String VPN_TYPE_IPSEC_XAUTH_PSK = "IPSEC_XAUTH_PSK";
    public static final String VPN_TYPE_IPSEC_XAUTH_RSA = "IPSEC_XAUTH_RSA";
    public static final String VPN_TYPE_L2TP_IPSEC_CRT = "L2TP_IPSEC";
    public static final String VPN_TYPE_L2TP_IPSEC_PSK = "L2TP_IPSEC_PSK";
    public static final String VPN_TYPE_PPTP = "PPTP";
    public List<String> dnsServers;
    public boolean enablePPTPEncryption;
    public List<String> forwardRoutes;
    public String ipsecCaCertificate;
    public String ipsecIdentifier;
    public String ipsecPreSharedKey;
    public String ipsecUserCertificate;
    public String l2tpSecret;
    public String ocspServerUrl;
    public String profileName;
    public List<String> searchDomains;
    public String serverName;
    public String userName;
    public String userPassword;
    public String vpnType;

    public VpnAdminProfile() {
        this.profileName = null;
        this.serverName = null;
        this.vpnType = null;
        this.userName = "";
        this.userPassword = "";
        this.enablePPTPEncryption = false;
        this.l2tpSecret = "";
        this.ipsecPreSharedKey = "";
        this.ipsecCaCertificate = "";
        this.ipsecUserCertificate = "";
        this.dnsServers = new ArrayList();
        this.searchDomains = new ArrayList();
        this.forwardRoutes = new ArrayList();
        this.ipsecIdentifier = "";
        this.ocspServerUrl = "";
    }

    private VpnAdminProfile(Parcel parcel) {
        this.profileName = null;
        this.serverName = null;
        this.vpnType = null;
        this.userName = "";
        this.userPassword = "";
        this.enablePPTPEncryption = false;
        this.l2tpSecret = "";
        this.ipsecPreSharedKey = "";
        this.ipsecCaCertificate = "";
        this.ipsecUserCertificate = "";
        this.dnsServers = new ArrayList();
        this.searchDomains = new ArrayList();
        this.forwardRoutes = new ArrayList();
        this.ipsecIdentifier = "";
        this.ocspServerUrl = "";
        this.profileName = parcel.readString();
        this.serverName = parcel.readString();
        this.vpnType = parcel.readString();
        this.userName = parcel.readString();
        this.userPassword = parcel.readString();
        this.enablePPTPEncryption = parcel.readByte() == 1;
        parcel.readByte();
        this.l2tpSecret = parcel.readString();
        this.ipsecPreSharedKey = parcel.readString();
        this.ipsecCaCertificate = parcel.readString();
        this.ipsecUserCertificate = parcel.readString();
        parcel.readStringList(this.dnsServers);
        parcel.readStringList(this.searchDomains);
        parcel.readStringList(this.forwardRoutes);
        this.ipsecIdentifier = parcel.readString();
        this.ocspServerUrl = parcel.readString();
    }

    static android.app.enterprise.VpnAdminProfile convertToOld(VpnAdminProfile vpnAdminProfile) {
        if (vpnAdminProfile == null) {
            return null;
        }
        android.app.enterprise.VpnAdminProfile vpnAdminProfile2 = new android.app.enterprise.VpnAdminProfile();
        vpnAdminProfile2.profileName = vpnAdminProfile.profileName;
        vpnAdminProfile2.serverName = vpnAdminProfile.serverName;
        vpnAdminProfile2.vpnType = vpnAdminProfile.vpnType;
        vpnAdminProfile2.userName = vpnAdminProfile.userName;
        vpnAdminProfile2.userPassword = vpnAdminProfile.userPassword;
        vpnAdminProfile2.PPTPEncryptionEnable = vpnAdminProfile.enablePPTPEncryption;
        vpnAdminProfile2.L2TPSecret = vpnAdminProfile.l2tpSecret;
        vpnAdminProfile2.IPSecPreSharedKey = vpnAdminProfile.ipsecPreSharedKey;
        vpnAdminProfile2.IPSecCaCertificate = vpnAdminProfile.ipsecCaCertificate;
        vpnAdminProfile2.IPSecUserCertificate = vpnAdminProfile.ipsecUserCertificate;
        vpnAdminProfile2.dnsServers = vpnAdminProfile.dnsServers;
        vpnAdminProfile2.searchDomains = vpnAdminProfile.searchDomains;
        vpnAdminProfile2.forwardRoutes = vpnAdminProfile.forwardRoutes;
        vpnAdminProfile2.ipsecIdentifier = vpnAdminProfile.ipsecIdentifier;
        vpnAdminProfile2.ocspServerUrl = vpnAdminProfile.ocspServerUrl;
        return vpnAdminProfile2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.profileName);
        parcel.writeString(this.serverName);
        parcel.writeString(this.vpnType);
        parcel.writeString(this.userName);
        parcel.writeString(this.userPassword);
        if (this.enablePPTPEncryption) {
            parcel.writeByte((byte) 1);
        } else {
            parcel.writeByte((byte) 0);
        }
        parcel.writeString(this.l2tpSecret);
        parcel.writeString(this.ipsecPreSharedKey);
        parcel.writeString(this.ipsecCaCertificate);
        parcel.writeString(this.ipsecUserCertificate);
        parcel.writeStringList(this.dnsServers);
        parcel.writeStringList(this.searchDomains);
        parcel.writeStringList(this.forwardRoutes);
        parcel.writeString(this.ipsecIdentifier);
        parcel.writeString(this.ocspServerUrl);
    }
}
