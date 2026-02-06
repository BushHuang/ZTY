package com.samsung.android.knox.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.samsung.android.knox.SupportLibUtils;
import java.util.ArrayList;
import java.util.List;

public class ProxyProperties implements Parcelable {
    public static final Parcelable.Creator<ProxyProperties> CREATOR = new Parcelable.Creator<ProxyProperties>() {
        @Override
        public ProxyProperties createFromParcel(Parcel parcel) {
            return new ProxyProperties(parcel);
        }

        @Override
        public ProxyProperties[] newArray(int i) {
            return new ProxyProperties[i];
        }
    };
    private List<AuthConfig> mAuthConfigList;
    private List<String> mExclusionList;
    private String mHostname;
    private String mPacFileUrl;
    private int mPortNumber;

    public ProxyProperties() {
        this.mPortNumber = -1;
        this.mExclusionList = new ArrayList();
        this.mAuthConfigList = new ArrayList();
    }

    private ProxyProperties(Parcel parcel) {
        this.mPortNumber = -1;
        this.mExclusionList = new ArrayList();
        this.mAuthConfigList = new ArrayList();
        readFromParcel(parcel);
    }

    static ProxyProperties convertToNew(android.app.enterprise.devicesettings.ProxyProperties proxyProperties) {
        if (proxyProperties == null) {
            return null;
        }
        ProxyProperties proxyProperties2 = new ProxyProperties();
        proxyProperties2.setHostname(proxyProperties.getHostname());
        proxyProperties2.setPortNumber(proxyProperties.getPortNumber());
        proxyProperties2.setExclusionList(proxyProperties.getExclusionList());
        try {
            proxyProperties2.setPacFileUrl(proxyProperties.getPacFileUrl());
        } catch (NoSuchMethodError unused) {
        }
        try {
            proxyProperties2.setAuthConfigList(AuthConfig.convertToNewList(proxyProperties.getAuthConfigList()));
        } catch (NoSuchMethodError unused2) {
        }
        return proxyProperties2;
    }

    static android.app.enterprise.devicesettings.ProxyProperties convertToOld(ProxyProperties proxyProperties) throws NoSuchMethodError, NoClassDefFoundError {
        if (proxyProperties == null) {
            return null;
        }
        try {
            android.app.enterprise.devicesettings.ProxyProperties proxyProperties2 = new android.app.enterprise.devicesettings.ProxyProperties();
            proxyProperties2.setHostname(proxyProperties.getHostname());
            proxyProperties2.setPortNumber(proxyProperties.getPortNumber());
            proxyProperties2.setExclusionList(proxyProperties.getExclusionList());
            try {
                proxyProperties2.setPacFileUrl(proxyProperties.getPacFileUrl());
            } catch (NoSuchMethodError unused) {
                if (proxyProperties.getPacFileUrl() != null) {
                    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProxyProperties.class, "setPacFileUrl", new Class[]{String.class}, 20));
                }
            }
            List<com.sec.enterprise.network.AuthConfig> listConvertToOldList = AuthConfig.convertToOldList(proxyProperties.getAuthConfigList());
            try {
                proxyProperties2.setAuthConfigList(listConvertToOldList);
            } catch (NoSuchMethodError unused2) {
                if (listConvertToOldList == null || !listConvertToOldList.isEmpty()) {
                    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProxyProperties.class, "setAuthConfigList", new Class[]{List.class}, 20));
                }
            }
            return proxyProperties2;
        } catch (NoClassDefFoundError unused3) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(ProxyProperties.class, 17));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<AuthConfig> getAuthConfigList() {
        return this.mAuthConfigList;
    }

    public List<String> getExclusionList() {
        return this.mExclusionList;
    }

    public String getHostname() {
        return this.mHostname;
    }

    public String getPacFileUrl() {
        return this.mPacFileUrl;
    }

    public int getPortNumber() {
        return this.mPortNumber;
    }

    public boolean isAuthenticationConfigured() {
        List<AuthConfig> list = this.mAuthConfigList;
        return (list == null || list.isEmpty()) ? false : true;
    }

    public boolean isValid() {
        boolean z = !TextUtils.isEmpty(this.mPacFileUrl);
        boolean z2 = !TextUtils.isEmpty(this.mHostname);
        if (z && z2) {
            return false;
        }
        if (isAuthenticationConfigured()) {
            for (AuthConfig authConfig : this.mAuthConfigList) {
                if (authConfig == null || !authConfig.isValid()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void readFromParcel(Parcel parcel) {
        this.mHostname = parcel.readString();
        this.mPortNumber = parcel.readInt();
        parcel.readStringList(this.mExclusionList);
        this.mPacFileUrl = parcel.readString();
        parcel.readList(this.mAuthConfigList, AuthConfig.class.getClassLoader());
    }

    public void setAuthConfigList(List<AuthConfig> list) {
        this.mAuthConfigList = list;
    }

    public void setExclusionList(List<String> list) {
        this.mExclusionList = list;
    }

    public void setHostname(String str) {
        this.mHostname = str;
    }

    public void setPacFileUrl(String str) {
        this.mPacFileUrl = str;
    }

    public void setPortNumber(int i) {
        this.mPortNumber = i;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mHostname);
        parcel.writeInt(this.mPortNumber);
        parcel.writeStringList(this.mExclusionList);
        parcel.writeString(this.mPacFileUrl);
        parcel.writeList(this.mAuthConfigList);
    }
}
