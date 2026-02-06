package com.samsung.android.knox.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.samsung.android.knox.EnterpriseDeviceManager;
import com.samsung.android.knox.SupportLibUtils;
import com.samsung.android.knox.net.AuthConfig;
import java.util.ArrayList;
import java.util.List;

public class WifiAdminProfile implements Parcelable {
    public static final Parcelable.Creator<WifiAdminProfile> CREATOR = new Parcelable.Creator<WifiAdminProfile>() {
        @Override
        public WifiAdminProfile createFromParcel(Parcel parcel) {
            return new WifiAdminProfile(parcel);
        }

        @Override
        public WifiAdminProfile[] newArray(int i) {
            return new WifiAdminProfile[i];
        }
    };
    private static final int ENGINE_ANDROID_KEYSTORE = 0;
    private static final int ENGINE_UCM = 2;
    private static final int PROXY_STATE_NONE = 0;
    public String anonymousIdentity;
    public String caCertificate;
    public String clientCertification;
    private int mEngineId;
    private String mStorageName;
    public String password;
    public String phase1;
    public String phase2;
    public List<AuthConfig> proxyAuthConfigList;
    public List<String> proxyBypassList;
    public String proxyHostname;
    public String proxyPacUrl;
    public int proxyPort;
    public int proxyState;
    public String psk;
    public String security;
    public String ssid;
    public String staticGateway;
    public String staticIp;
    public boolean staticIpEnabled;
    public String staticPrimaryDns;
    public String staticSecondaryDns;
    public String staticSubnetMask;
    public String userIdentity;
    public String wepKey1;
    public String wepKey2;
    public String wepKey3;
    public String wepKey4;
    public int wepKeyId;

    public WifiAdminProfile() {
        this.ssid = null;
        this.psk = null;
        this.password = null;
        this.security = null;
        this.wepKeyId = -1;
        this.wepKey1 = null;
        this.wepKey2 = null;
        this.wepKey3 = null;
        this.wepKey4 = null;
        this.userIdentity = null;
        this.anonymousIdentity = null;
        this.phase2 = null;
        this.clientCertification = null;
        this.caCertificate = null;
        this.staticIp = null;
        this.staticGateway = null;
        this.staticPrimaryDns = null;
        this.staticSecondaryDns = null;
        this.staticSubnetMask = null;
        this.proxyHostname = null;
        this.proxyBypassList = null;
        this.proxyAuthConfigList = new ArrayList();
        this.phase1 = null;
        this.proxyState = 0;
        this.proxyPacUrl = null;
        this.mEngineId = 0;
        this.mStorageName = null;
    }

    public WifiAdminProfile(int i) {
        this.ssid = null;
        this.psk = null;
        this.password = null;
        this.security = null;
        this.wepKeyId = -1;
        this.wepKey1 = null;
        this.wepKey2 = null;
        this.wepKey3 = null;
        this.wepKey4 = null;
        this.userIdentity = null;
        this.anonymousIdentity = null;
        this.phase2 = null;
        this.clientCertification = null;
        this.caCertificate = null;
        this.staticIp = null;
        this.staticGateway = null;
        this.staticPrimaryDns = null;
        this.staticSecondaryDns = null;
        this.staticSubnetMask = null;
        this.proxyHostname = null;
        this.proxyBypassList = null;
        this.proxyAuthConfigList = new ArrayList();
        this.phase1 = null;
        this.proxyState = 0;
        this.proxyPacUrl = null;
        this.mEngineId = 0;
        this.mStorageName = null;
        if (i != 2) {
            this.mEngineId = i;
        }
    }

    private WifiAdminProfile(Parcel parcel) {
        this.ssid = null;
        this.psk = null;
        this.password = null;
        this.security = null;
        this.wepKeyId = -1;
        this.wepKey1 = null;
        this.wepKey2 = null;
        this.wepKey3 = null;
        this.wepKey4 = null;
        this.userIdentity = null;
        this.anonymousIdentity = null;
        this.phase2 = null;
        this.clientCertification = null;
        this.caCertificate = null;
        this.staticIp = null;
        this.staticGateway = null;
        this.staticPrimaryDns = null;
        this.staticSecondaryDns = null;
        this.staticSubnetMask = null;
        this.proxyHostname = null;
        this.proxyBypassList = null;
        this.proxyAuthConfigList = new ArrayList();
        this.phase1 = null;
        this.proxyState = 0;
        this.proxyPacUrl = null;
        this.mEngineId = 0;
        this.mStorageName = null;
        this.ssid = parcel.readString();
        this.psk = parcel.readString();
        this.password = parcel.readString();
        this.security = parcel.readString();
        this.wepKeyId = parcel.readInt();
        this.wepKey1 = parcel.readString();
        this.wepKey2 = parcel.readString();
        this.wepKey3 = parcel.readString();
        this.wepKey4 = parcel.readString();
        this.userIdentity = parcel.readString();
        this.anonymousIdentity = parcel.readString();
        this.phase2 = parcel.readString();
        this.clientCertification = parcel.readString();
        this.caCertificate = parcel.readString();
        this.staticIpEnabled = parcel.readInt() != 0;
        this.staticIp = parcel.readString();
        this.staticGateway = parcel.readString();
        this.staticPrimaryDns = parcel.readString();
        this.staticSecondaryDns = parcel.readString();
        this.staticSubnetMask = parcel.readString();
        this.mEngineId = parcel.readInt();
        this.proxyHostname = parcel.readString();
        this.proxyPort = parcel.readInt();
        this.proxyBypassList = parcel.createStringArrayList();
        parcel.readList(this.proxyAuthConfigList, AuthConfig.class.getClassLoader());
        this.phase1 = parcel.readString();
        this.proxyState = parcel.readInt();
        this.proxyPacUrl = parcel.readString();
        this.mStorageName = parcel.readString();
    }

    public WifiAdminProfile(String str) {
        this.ssid = null;
        this.psk = null;
        this.password = null;
        this.security = null;
        this.wepKeyId = -1;
        this.wepKey1 = null;
        this.wepKey2 = null;
        this.wepKey3 = null;
        this.wepKey4 = null;
        this.userIdentity = null;
        this.anonymousIdentity = null;
        this.phase2 = null;
        this.clientCertification = null;
        this.caCertificate = null;
        this.staticIp = null;
        this.staticGateway = null;
        this.staticPrimaryDns = null;
        this.staticSecondaryDns = null;
        this.staticSubnetMask = null;
        this.proxyHostname = null;
        this.proxyBypassList = null;
        this.proxyAuthConfigList = new ArrayList();
        this.phase1 = null;
        this.proxyState = 0;
        this.proxyPacUrl = null;
        this.mEngineId = 0;
        this.mStorageName = null;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mEngineId = 2;
        this.mStorageName = str;
    }

    static WifiAdminProfile convertToNew(android.app.enterprise.WifiAdminProfile wifiAdminProfile) {
        WifiAdminProfile wifiAdminProfile2 = null;
        if (wifiAdminProfile == null) {
            return null;
        }
        try {
            if (wifiAdminProfile.getStorageName() != null) {
                wifiAdminProfile2 = new WifiAdminProfile(wifiAdminProfile.getStorageName());
            }
        } catch (NoSuchMethodError unused) {
        }
        try {
            if (wifiAdminProfile.getEngineId() != 0) {
                wifiAdminProfile2 = new WifiAdminProfile(wifiAdminProfile.getEngineId());
            }
        } catch (NoSuchMethodError unused2) {
        }
        if (wifiAdminProfile2 == null) {
            wifiAdminProfile2 = new WifiAdminProfile();
        }
        wifiAdminProfile2.anonymousIdentity = wifiAdminProfile.anonymousIdentity;
        wifiAdminProfile2.caCertificate = wifiAdminProfile.caCertificate;
        wifiAdminProfile2.clientCertification = wifiAdminProfile.clientCertification;
        wifiAdminProfile2.password = wifiAdminProfile.password;
        wifiAdminProfile2.phase2 = wifiAdminProfile.phase2;
        wifiAdminProfile2.psk = wifiAdminProfile.psk;
        wifiAdminProfile2.security = wifiAdminProfile.security;
        wifiAdminProfile2.ssid = wifiAdminProfile.ssid;
        wifiAdminProfile2.userIdentity = wifiAdminProfile.userIdentity;
        wifiAdminProfile2.wepKey1 = wifiAdminProfile.wepKey1;
        wifiAdminProfile2.wepKey2 = wifiAdminProfile.wepKey2;
        wifiAdminProfile2.wepKey3 = wifiAdminProfile.wepKey3;
        wifiAdminProfile2.wepKey4 = wifiAdminProfile.wepKey4;
        wifiAdminProfile2.wepKeyId = wifiAdminProfile.wepKeyId;
        if (EnterpriseDeviceManager.getAPILevel() >= 17) {
            wifiAdminProfile2.phase1 = wifiAdminProfile.phase1;
            wifiAdminProfile2.proxyBypassList = wifiAdminProfile.proxyBypassList;
            wifiAdminProfile2.proxyHostname = wifiAdminProfile.proxyHostname;
            wifiAdminProfile2.proxyPacUrl = wifiAdminProfile.proxyPacUrl;
            wifiAdminProfile2.proxyPort = wifiAdminProfile.proxyPort;
            wifiAdminProfile2.proxyState = wifiAdminProfile.proxyState;
            wifiAdminProfile2.staticGateway = wifiAdminProfile.staticGateway;
            wifiAdminProfile2.staticIp = wifiAdminProfile.staticIp;
            wifiAdminProfile2.staticIpEnabled = wifiAdminProfile.staticIpEnabled;
            wifiAdminProfile2.staticPrimaryDns = wifiAdminProfile.staticPrimaryDns;
            wifiAdminProfile2.staticSecondaryDns = wifiAdminProfile.staticSecondaryDns;
            wifiAdminProfile2.staticSubnetMask = wifiAdminProfile.staticSubnetMask;
        }
        if (EnterpriseDeviceManager.getAPILevel() >= 20) {
            wifiAdminProfile2.proxyAuthConfigList = AuthConfig.convertToNewList(wifiAdminProfile.proxyAuthConfigList);
        }
        return wifiAdminProfile2;
    }

    static android.app.enterprise.WifiAdminProfile convertToOld(WifiAdminProfile wifiAdminProfile) {
        if (wifiAdminProfile == null) {
            return null;
        }
        try {
            android.app.enterprise.WifiAdminProfile wifiAdminProfile2 = wifiAdminProfile.getStorageName() != null ? new android.app.enterprise.WifiAdminProfile(wifiAdminProfile.getStorageName()) : null;
            try {
                if (wifiAdminProfile.getEngineId() != 0) {
                    wifiAdminProfile2 = new android.app.enterprise.WifiAdminProfile(wifiAdminProfile.getEngineId());
                }
                if (wifiAdminProfile2 == null) {
                    wifiAdminProfile2 = new android.app.enterprise.WifiAdminProfile();
                }
                wifiAdminProfile2.anonymousIdentity = wifiAdminProfile.anonymousIdentity;
                wifiAdminProfile2.caCertificate = wifiAdminProfile.caCertificate;
                wifiAdminProfile2.clientCertification = wifiAdminProfile.clientCertification;
                wifiAdminProfile2.password = wifiAdminProfile.password;
                wifiAdminProfile2.phase2 = wifiAdminProfile.phase2;
                wifiAdminProfile2.psk = wifiAdminProfile.psk;
                wifiAdminProfile2.security = wifiAdminProfile.security;
                wifiAdminProfile2.ssid = wifiAdminProfile.ssid;
                wifiAdminProfile2.userIdentity = wifiAdminProfile.userIdentity;
                wifiAdminProfile2.wepKey1 = wifiAdminProfile.wepKey1;
                wifiAdminProfile2.wepKey2 = wifiAdminProfile.wepKey2;
                wifiAdminProfile2.wepKey3 = wifiAdminProfile.wepKey3;
                wifiAdminProfile2.wepKey4 = wifiAdminProfile.wepKey4;
                wifiAdminProfile2.wepKeyId = wifiAdminProfile.wepKeyId;
                if (EnterpriseDeviceManager.getAPILevel() >= 17) {
                    wifiAdminProfile2.phase1 = wifiAdminProfile.phase1;
                    wifiAdminProfile2.proxyBypassList = wifiAdminProfile.proxyBypassList;
                    wifiAdminProfile2.proxyHostname = wifiAdminProfile.proxyHostname;
                    wifiAdminProfile2.proxyPacUrl = wifiAdminProfile.proxyPacUrl;
                    wifiAdminProfile2.proxyPort = wifiAdminProfile.proxyPort;
                    wifiAdminProfile2.proxyState = wifiAdminProfile.proxyState;
                    wifiAdminProfile2.staticGateway = wifiAdminProfile.staticGateway;
                    wifiAdminProfile2.staticIp = wifiAdminProfile.staticIp;
                    wifiAdminProfile2.staticIpEnabled = wifiAdminProfile.staticIpEnabled;
                    wifiAdminProfile2.staticPrimaryDns = wifiAdminProfile.staticPrimaryDns;
                    wifiAdminProfile2.staticSecondaryDns = wifiAdminProfile.staticSecondaryDns;
                    wifiAdminProfile2.staticSubnetMask = wifiAdminProfile.staticSubnetMask;
                }
                if (EnterpriseDeviceManager.getAPILevel() >= 20) {
                    wifiAdminProfile2.proxyAuthConfigList = AuthConfig.convertToOldList(wifiAdminProfile.proxyAuthConfigList);
                }
                return wifiAdminProfile2;
            } catch (NoClassDefFoundError unused) {
                throw new NoClassDefFoundError(SupportLibUtils.buildMethodErrorMsg(WifiAdminProfile.class, "WifiAdminProfile", new Class[]{Integer.TYPE}, 12));
            }
        } catch (NoClassDefFoundError unused2) {
            throw new NoClassDefFoundError(SupportLibUtils.buildMethodErrorMsg(WifiAdminProfile.class, "WifiAdminProfile", new Class[]{String.class}, 19));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getEngineId() {
        return this.mEngineId;
    }

    public String getStorageName() {
        return this.mStorageName;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ssid);
        parcel.writeString(this.psk);
        parcel.writeString(this.password);
        parcel.writeString(this.security);
        parcel.writeInt(this.wepKeyId);
        parcel.writeString(this.wepKey1);
        parcel.writeString(this.wepKey2);
        parcel.writeString(this.wepKey3);
        parcel.writeString(this.wepKey4);
        parcel.writeString(this.userIdentity);
        parcel.writeString(this.anonymousIdentity);
        parcel.writeString(this.phase2);
        parcel.writeString(this.clientCertification);
        parcel.writeString(this.caCertificate);
        parcel.writeInt(this.staticIpEnabled ? 1 : 0);
        parcel.writeString(this.staticIp);
        parcel.writeString(this.staticGateway);
        parcel.writeString(this.staticPrimaryDns);
        parcel.writeString(this.staticSecondaryDns);
        parcel.writeString(this.staticSubnetMask);
        parcel.writeInt(this.mEngineId);
        parcel.writeString(this.proxyHostname);
        parcel.writeInt(this.proxyPort);
        parcel.writeStringList(this.proxyBypassList);
        parcel.writeList(this.proxyAuthConfigList);
        parcel.writeString(this.phase1);
        parcel.writeInt(this.proxyState);
        parcel.writeString(this.proxyPacUrl);
        parcel.writeString(this.mStorageName);
    }
}
