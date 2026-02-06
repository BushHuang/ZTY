package com.samsung.android.knox.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.samsung.android.knox.SupportLibUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AuthConfig implements Parcelable {
    public static String ANY_HOST = "*";
    public static int ANY_PORT = -1;
    public static final Parcelable.Creator<AuthConfig> CREATOR = new Parcelable.Creator<AuthConfig>() {
        @Override
        public AuthConfig createFromParcel(Parcel parcel) {
            return new AuthConfig(parcel);
        }

        @Override
        public AuthConfig[] newArray(int i) {
            return new AuthConfig[i];
        }
    };
    private String mHost;
    private String mPassword;
    private int mPort;
    private String mUsername;

    private AuthConfig(Parcel parcel) {
        readFromParcel(parcel);
    }

    public AuthConfig(String str, int i, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("Host, username and password cannot be null");
        }
        this.mUsername = str2;
        this.mPassword = str3;
        this.mHost = str;
        this.mPort = i;
    }

    public AuthConfig(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }
        this.mUsername = str;
        this.mPassword = str2;
        this.mHost = ANY_HOST;
        this.mPort = ANY_PORT;
    }

    static AuthConfig convertToNew(com.sec.enterprise.network.AuthConfig authConfig) {
        if (authConfig != null) {
            return new AuthConfig(authConfig.getHost(), authConfig.getPort(), authConfig.getUsername(), authConfig.getPassword());
        }
        return null;
    }

    public static List<AuthConfig> convertToNewList(List<com.sec.enterprise.network.AuthConfig> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return null;
        }
        Iterator<com.sec.enterprise.network.AuthConfig> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    static com.sec.enterprise.network.AuthConfig convertToOld(AuthConfig authConfig) {
        if (authConfig == null) {
            return null;
        }
        try {
            return new com.sec.enterprise.network.AuthConfig(authConfig.getHost(), authConfig.getPort(), authConfig.getUsername(), authConfig.getPassword());
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(AuthConfig.class, 20));
        }
    }

    public static List<com.sec.enterprise.network.AuthConfig> convertToOldList(List<AuthConfig> list) throws NoClassDefFoundError {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return null;
        }
        Iterator<AuthConfig> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToOld(it.next()));
        }
        return arrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthConfig authConfig = (AuthConfig) obj;
        String str = this.mHost;
        if (str == null) {
            if (authConfig.mHost != null) {
                return false;
            }
        } else if (!str.equals(authConfig.mHost)) {
            return false;
        }
        String str2 = this.mPassword;
        if (str2 == null) {
            if (authConfig.mPassword != null) {
                return false;
            }
        } else if (!str2.equals(authConfig.mPassword)) {
            return false;
        }
        if (this.mPort != authConfig.mPort) {
            return false;
        }
        String str3 = this.mUsername;
        if (str3 == null) {
            if (authConfig.mUsername != null) {
                return false;
            }
        } else if (!str3.equals(authConfig.mUsername)) {
            return false;
        }
        return true;
    }

    public String getHost() {
        return this.mHost;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public int getPort() {
        return this.mPort;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public int hashCode() {
        String str = this.mHost;
        int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.mPassword;
        int iHashCode2 = (((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + this.mPort) * 31;
        String str3 = this.mUsername;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public boolean isValid() {
        if (TextUtils.isEmpty(this.mHost) || TextUtils.isEmpty(this.mUsername) || TextUtils.isEmpty(this.mPassword)) {
            return false;
        }
        return (!ANY_HOST.equals(this.mHost) || ANY_PORT == this.mPort) && (ANY_HOST.equals(this.mHost) || ANY_PORT != this.mPort);
    }

    public void readFromParcel(Parcel parcel) {
        this.mUsername = parcel.readString();
        this.mPassword = parcel.readString();
        this.mHost = parcel.readString();
        this.mPort = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUsername);
        parcel.writeString(this.mPassword);
        parcel.writeString(this.mHost);
        parcel.writeInt(this.mPort);
    }
}
