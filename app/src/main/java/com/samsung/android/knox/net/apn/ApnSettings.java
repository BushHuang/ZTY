package com.samsung.android.knox.net.apn;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.EnterpriseDeviceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApnSettings implements Parcelable {
    public static final Parcelable.Creator<ApnSettings> CREATOR = new Parcelable.Creator<ApnSettings>() {
        @Override
        public ApnSettings createFromParcel(Parcel parcel) {
            return new ApnSettings(parcel);
        }

        @Override
        public ApnSettings[] newArray(int i) {
            return new ApnSettings[i];
        }
    };
    public static final String PROTOCOL_IPV4 = "IP";
    public static final String PROTOCOL_IPV4_IPV6 = "IPV4V6";
    public static final String PROTOCOL_IPV6 = "IPV6";
    public String apn;
    public int authType;
    public long id;
    public String mcc;
    public String mmsPort;
    public String mmsProxy;
    public String mmsc;
    public String mnc;
    public String name;
    public String password;
    public int port;
    public String protocol;
    public String proxy;
    public String roamingProtocol;
    public String server;
    public String type;
    public String user;

    public ApnSettings() {
        this.id = -1L;
        this.name = "";
        this.apn = "";
        this.mcc = "";
        this.mnc = "";
        this.user = "";
        this.server = "";
        this.password = "";
        this.proxy = "";
        this.port = -1;
        this.mmsProxy = "";
        this.mmsPort = "";
        this.mmsc = "";
        this.type = "";
        this.authType = -1;
        this.protocol = "IP";
        this.roamingProtocol = "IP";
    }

    private ApnSettings(Parcel parcel) {
        this.id = -1L;
        this.name = "";
        this.apn = "";
        this.mcc = "";
        this.mnc = "";
        this.user = "";
        this.server = "";
        this.password = "";
        this.proxy = "";
        this.port = -1;
        this.mmsProxy = "";
        this.mmsPort = "";
        this.mmsc = "";
        this.type = "";
        this.authType = -1;
        this.protocol = "IP";
        this.roamingProtocol = "IP";
        readFromParcel(parcel);
    }

    static ApnSettings convertToNew(android.app.enterprise.ApnSettings apnSettings) {
        if (apnSettings == null) {
            return null;
        }
        ApnSettings apnSettings2 = new ApnSettings();
        apnSettings2.id = apnSettings.id;
        apnSettings2.name = apnSettings.name;
        apnSettings2.apn = apnSettings.apn;
        apnSettings2.mcc = apnSettings.mcc;
        apnSettings2.mnc = apnSettings.mnc;
        apnSettings2.user = apnSettings.user;
        apnSettings2.server = apnSettings.server;
        apnSettings2.password = apnSettings.password;
        apnSettings2.port = apnSettings.port;
        apnSettings2.proxy = apnSettings.proxy;
        apnSettings2.mmsProxy = apnSettings.mmsProxy;
        apnSettings2.mmsPort = apnSettings.mmsPort;
        apnSettings2.mmsc = apnSettings.mmsc;
        apnSettings2.type = apnSettings.type;
        apnSettings2.authType = apnSettings.authType;
        if (EnterpriseDeviceManager.getAPILevel() >= 20) {
            apnSettings2.protocol = apnSettings.protocol;
            apnSettings2.roamingProtocol = apnSettings.roamingProtocol;
        }
        return apnSettings2;
    }

    static List<ApnSettings> convertToNewList(List<android.app.enterprise.ApnSettings> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<android.app.enterprise.ApnSettings> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    static android.app.enterprise.ApnSettings convertToOld(ApnSettings apnSettings) throws NoSuchFieldError {
        if (apnSettings == null) {
            return null;
        }
        android.app.enterprise.ApnSettings apnSettings2 = new android.app.enterprise.ApnSettings();
        apnSettings2.id = apnSettings.id;
        apnSettings2.name = apnSettings.name;
        apnSettings2.apn = apnSettings.apn;
        apnSettings2.mcc = apnSettings.mcc;
        apnSettings2.mnc = apnSettings.mnc;
        apnSettings2.user = apnSettings.user;
        apnSettings2.server = apnSettings.server;
        apnSettings2.password = apnSettings.password;
        apnSettings2.port = apnSettings.port;
        apnSettings2.proxy = apnSettings.proxy;
        apnSettings2.mmsProxy = apnSettings.mmsProxy;
        apnSettings2.mmsPort = apnSettings.mmsPort;
        apnSettings2.mmsc = apnSettings.mmsc;
        apnSettings2.type = apnSettings.type;
        apnSettings2.authType = apnSettings.authType;
        if (EnterpriseDeviceManager.getAPILevel() >= 20) {
            apnSettings2.protocol = apnSettings.protocol;
            apnSettings2.roamingProtocol = apnSettings.roamingProtocol;
        }
        return apnSettings2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getApn() {
        return this.apn;
    }

    public int getAuthType() {
        return this.authType;
    }

    public long getId() {
        return this.id;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getMmsPort() {
        return this.mmsPort;
    }

    public String getMmsProxy() {
        return this.mmsProxy;
    }

    public String getMmsc() {
        return this.mmsc;
    }

    public String getMnc() {
        return this.mnc;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public int getPort() {
        return this.port;
    }

    public String getProxy() {
        return this.proxy;
    }

    public String getServer() {
        return this.server;
    }

    public String getType() {
        return this.type;
    }

    public String getUser() {
        return this.user;
    }

    public void readFromParcel(Parcel parcel) {
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.apn = parcel.readString();
        this.mcc = parcel.readString();
        this.mnc = parcel.readString();
        this.user = parcel.readString();
        this.server = parcel.readString();
        this.password = parcel.readString();
        this.proxy = parcel.readString();
        this.port = parcel.readInt();
        this.mmsProxy = parcel.readString();
        this.mmsPort = parcel.readString();
        this.mmsc = parcel.readString();
        this.type = parcel.readString();
        this.authType = parcel.readInt();
        this.protocol = parcel.readString();
        this.roamingProtocol = parcel.readString();
    }

    public void setApn(String str) {
        this.apn = str;
    }

    public void setAuthType(int i) {
        this.authType = i;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setMcc(String str) {
        this.mcc = str;
    }

    public void setMmsPort(String str) {
        this.mmsPort = str;
    }

    public void setMmsProxy(String str) {
        this.mmsProxy = str;
    }

    public void setMmsc(String str) {
        this.mmsc = str;
    }

    public void setMnc(String str) {
        this.mnc = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public void setProxy(String str) {
        this.proxy = str;
    }

    public void setServer(String str) {
        this.server = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setUser(String str) {
        this.user = str;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.apn);
        parcel.writeString(this.mcc);
        parcel.writeString(this.mnc);
        parcel.writeString(this.user);
        parcel.writeString(this.server);
        parcel.writeString(this.password);
        parcel.writeString(this.proxy);
        parcel.writeInt(this.port);
        parcel.writeString(this.mmsProxy);
        parcel.writeString(this.mmsPort);
        parcel.writeString(this.mmsc);
        parcel.writeString(this.type);
        parcel.writeInt(this.authType);
        parcel.writeString(this.protocol);
        parcel.writeString(this.roamingProtocol);
    }
}
