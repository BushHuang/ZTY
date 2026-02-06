package com.samsung.android.knox.keystore;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;

public class SCEPProfile extends EnrollmentProfile implements Parcelable {
    public int challengeLength;
    public byte[] challengePassword;
    public String scepProfileName;
    public String scepUrl;
    public String subjectAlternativeName;
    public String subjectName;
    public long validitytimeForChallenge;

    public SCEPProfile() {
    }

    public SCEPProfile(Parcel parcel) {
        this.profileType = parcel.readString();
        try {
            this.scepUrl = parcel.readString();
            this.scepProfileName = parcel.readString();
            int i = parcel.readInt();
            this.challengeLength = i;
            byte[] bArr = new byte[i];
            this.challengePassword = bArr;
            parcel.readByteArray(bArr);
            this.subjectName = parcel.readString();
            this.validitytimeForChallenge = parcel.readLong();
            this.keySize = parcel.readInt();
            this.keyPairAlgorithm = parcel.readString();
            this.subjectAlternativeName = parcel.readString();
            this.certificateAlias = parcel.readString();
            this.keystoreType = parcel.readString();
            this.credentialStorageBundle = parcel.readBundle();
            this.hashAlgorithmType = parcel.readString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static com.sec.enterprise.knox.certenroll.SCEPProfile convertToOld(SCEPProfile sCEPProfile) throws NoSuchFieldError, NoClassDefFoundError {
        if (sCEPProfile == null) {
            return null;
        }
        try {
            com.sec.enterprise.knox.certenroll.SCEPProfile sCEPProfile2 = new com.sec.enterprise.knox.certenroll.SCEPProfile();
            EnrollmentProfile.convertToOld(sCEPProfile, sCEPProfile2);
            sCEPProfile2.scepUrl = sCEPProfile.scepUrl;
            sCEPProfile2.scepProfileName = sCEPProfile.scepProfileName;
            sCEPProfile2.challengeLength = sCEPProfile.challengeLength;
            sCEPProfile2.challengePassword = sCEPProfile.challengePassword;
            sCEPProfile2.subjectName = sCEPProfile.subjectName;
            sCEPProfile2.validitytimeForChallenge = sCEPProfile.validitytimeForChallenge;
            sCEPProfile2.subjectAlternativeName = sCEPProfile.subjectAlternativeName;
            return sCEPProfile2;
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(SCEPProfile.class, 12));
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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getClass().getName());
        parcel.writeString("SCEP");
        parcel.writeString(this.scepUrl);
        parcel.writeString(this.scepProfileName);
        parcel.writeInt(this.challengeLength);
        parcel.writeByteArray(this.challengePassword);
        parcel.writeString(this.subjectName);
        parcel.writeLong(this.validitytimeForChallenge);
        parcel.writeInt(this.keySize);
        parcel.writeString(this.keyPairAlgorithm);
        parcel.writeString(this.subjectAlternativeName);
        parcel.writeString(this.certificateAlias);
        parcel.writeString(this.keystoreType);
        parcel.writeBundle(this.credentialStorageBundle);
        parcel.writeString(this.hashAlgorithmType);
    }
}
