package com.samsung.android.knox.keystore;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;

public class CMCProfile extends EnrollmentProfile implements Parcelable {
    public String estServerUrl;
    public String oneTimePassword;
    public String password;
    public boolean serverSideKeyGeneration;
    public String subjectAlterNativeName;
    public String subjectName;
    public String userName;

    public CMCProfile() {
    }

    public CMCProfile(Parcel parcel) {
        readFromParcel(parcel);
    }

    static com.sec.enterprise.knox.certenroll.CMCProfile convertToOld(CMCProfile cMCProfile) throws NoSuchFieldError, NoClassDefFoundError {
        if (cMCProfile == null) {
            return null;
        }
        try {
            com.sec.enterprise.knox.certenroll.CMCProfile cMCProfile2 = new com.sec.enterprise.knox.certenroll.CMCProfile();
            EnrollmentProfile.convertToOld(cMCProfile, cMCProfile2);
            cMCProfile2.estServerUrl = cMCProfile.estServerUrl;
            cMCProfile2.subjectName = cMCProfile.subjectName;
            cMCProfile2.subjectAlterNativeName = cMCProfile.subjectAlterNativeName;
            cMCProfile2.userName = cMCProfile.userName;
            cMCProfile2.password = cMCProfile.password;
            cMCProfile2.oneTimePassword = cMCProfile.oneTimePassword;
            cMCProfile2.serverSideKeyGeneration = cMCProfile.serverSideKeyGeneration;
            return cMCProfile2;
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(CMCProfile.class, 12));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String getProfileType() {
        return this.profileType;
    }

    public void readFromParcel(Parcel parcel) {
        try {
            this.profileType = parcel.readString();
            this.estServerUrl = parcel.readString();
            this.subjectName = parcel.readString();
            this.subjectAlterNativeName = parcel.readString();
            this.keySize = parcel.readInt();
            this.keyPairAlgorithm = parcel.readString();
            this.certificateAlias = parcel.readString();
            this.keystoreType = parcel.readString();
            this.userName = parcel.readString();
            this.password = parcel.readString();
            this.oneTimePassword = parcel.readString();
            this.serverSideKeyGeneration = parcel.readInt() != 0;
            this.credentialStorageBundle = parcel.readBundle();
            this.hashAlgorithmType = parcel.readString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(getClass().getName());
            parcel.writeString("CMC");
            parcel.writeString(this.estServerUrl);
            parcel.writeString(this.subjectName);
            parcel.writeString(this.subjectAlterNativeName);
            parcel.writeInt(this.keySize);
            parcel.writeString(this.keyPairAlgorithm);
            parcel.writeString(this.certificateAlias);
            parcel.writeString(this.keystoreType);
            parcel.writeString(this.userName);
            parcel.writeString(this.password);
            parcel.writeString(this.oneTimePassword);
            parcel.writeInt(this.serverSideKeyGeneration ? 1 : 0);
            parcel.writeBundle(this.credentialStorageBundle);
            parcel.writeString(this.hashAlgorithmType);
        }
    }
}
