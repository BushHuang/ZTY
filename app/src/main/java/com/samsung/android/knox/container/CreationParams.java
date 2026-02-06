package com.samsung.android.knox.container;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;

public class CreationParams implements Parcelable {
    public static final Parcelable.Creator<CreationParams> CREATOR = new Parcelable.Creator<CreationParams>() {
        @Override
        public CreationParams createFromParcel(Parcel parcel) {
            return new CreationParams(parcel);
        }

        @Override
        public CreationParams[] newArray(int i) {
            return new CreationParams[i];
        }
    };
    private String mAdminPkgName;
    private String mConfigName;
    private String mPwdResetToken;

    public CreationParams() {
        this.mConfigName = null;
        this.mAdminPkgName = null;
        this.mPwdResetToken = null;
    }

    public CreationParams(Parcel parcel) {
        this.mConfigName = null;
        this.mAdminPkgName = null;
        this.mPwdResetToken = null;
        String string = parcel.readString();
        this.mConfigName = string;
        if (string != null && string.isEmpty()) {
            this.mConfigName = null;
        }
        String string2 = parcel.readString();
        this.mAdminPkgName = string2;
        if (string2 != null && string2.isEmpty()) {
            this.mAdminPkgName = null;
        }
        String string3 = parcel.readString();
        this.mPwdResetToken = string3;
        if (string3 == null || !string3.isEmpty()) {
            return;
        }
        this.mPwdResetToken = null;
    }

    static com.sec.enterprise.knox.container.CreationParams convertToOld(CreationParams creationParams) throws NoClassDefFoundError {
        if (creationParams == null) {
            return null;
        }
        try {
            com.sec.enterprise.knox.container.CreationParams creationParams2 = new com.sec.enterprise.knox.container.CreationParams();
            creationParams2.setAdminPackageName(creationParams.getAdminPackageName());
            creationParams2.setConfigurationName(creationParams.getConfigurationName());
            creationParams2.setPasswordResetToken(creationParams.getPasswordResetToken());
            return creationParams2;
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(CreationParams.class, 13));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAdminPackageName() {
        return this.mAdminPkgName;
    }

    public String getConfigurationName() {
        return this.mConfigName;
    }

    public String getPasswordResetToken() {
        return this.mPwdResetToken;
    }

    public void setAdminPackageName(String str) {
        this.mAdminPkgName = str;
    }

    public void setConfigurationName(String str) {
        this.mConfigName = str;
    }

    public void setPasswordResetToken(String str) {
        this.mPwdResetToken = str;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        String str = this.mConfigName;
        if (str != null) {
            parcel.writeString(str);
        } else {
            parcel.writeString("");
        }
        String str2 = this.mAdminPkgName;
        if (str2 != null) {
            parcel.writeString(str2);
        } else {
            parcel.writeString("");
        }
        String str3 = this.mPwdResetToken;
        if (str3 != null) {
            parcel.writeString(str3);
        } else {
            parcel.writeString("");
        }
    }
}
