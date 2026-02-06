package com.samsung.android.knox.keystore;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;

public abstract class EnrollmentProfile implements Parcelable {
    public static final Parcelable.Creator<EnrollmentProfile> CREATOR = new Parcelable.Creator<EnrollmentProfile>() {
        @Override
        public EnrollmentProfile createFromParcel(Parcel parcel) {
            String string = parcel.readString();
            if (string.equals(SCEPProfile.class.getName())) {
                return new SCEPProfile(parcel);
            }
            if (string.equals(CMCProfile.class.getName())) {
                return new CMCProfile(parcel);
            }
            if (string.equals(CMPProfile.class.getName())) {
                return new CMPProfile(parcel);
            }
            return null;
        }

        @Override
        public EnrollmentProfile[] newArray(int i) {
            return new EnrollmentProfile[i];
        }
    };
    public String certificateAlias;
    public Bundle credentialStorageBundle = null;
    public String hashAlgorithmType;
    public String keyPairAlgorithm;
    public int keySize;
    public String keystoreType;
    public String profileType;

    static com.sec.enterprise.knox.certenroll.EnrollmentProfile convertToOld(EnrollmentProfile enrollmentProfile) throws NoSuchFieldError, NoClassDefFoundError {
        if (enrollmentProfile == null) {
            return null;
        }
        if (enrollmentProfile instanceof CMCProfile) {
            return CMCProfile.convertToOld((CMCProfile) enrollmentProfile);
        }
        if (enrollmentProfile instanceof CMPProfile) {
            return CMPProfile.convertToOld((CMPProfile) enrollmentProfile);
        }
        if (enrollmentProfile instanceof SCEPProfile) {
            return SCEPProfile.convertToOld((SCEPProfile) enrollmentProfile);
        }
        return null;
    }

    static void convertToOld(EnrollmentProfile enrollmentProfile, com.sec.enterprise.knox.certenroll.EnrollmentProfile enrollmentProfile2) throws NoSuchFieldError, NoClassDefFoundError {
        enrollmentProfile2.profileType = enrollmentProfile.profileType;
        enrollmentProfile2.setKeySize(enrollmentProfile.keySize);
        enrollmentProfile2.setKeyPairAlgorithm(enrollmentProfile.keyPairAlgorithm);
        enrollmentProfile2.setCertificateAlias(enrollmentProfile.certificateAlias);
        enrollmentProfile2.setKeystoreType(enrollmentProfile.keystoreType);
        try {
            enrollmentProfile2.credentialStorageBundle = enrollmentProfile.credentialStorageBundle;
        } catch (NoSuchFieldError unused) {
            if (enrollmentProfile.credentialStorageBundle != null) {
                throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(EnrollmentProfile.class, "credentialStorageBundle", 20));
            }
        }
        try {
            enrollmentProfile2.hashAlgorithmType = enrollmentProfile.hashAlgorithmType;
        } catch (NoSuchFieldError unused2) {
            if (enrollmentProfile.hashAlgorithmType != null) {
                throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(EnrollmentProfile.class, "hashAlgorithmType", 20));
            }
        }
    }

    public String getCertificateAlias() {
        return this.certificateAlias;
    }

    public String getKeyPairAlgorithm() {
        return this.keyPairAlgorithm;
    }

    public int getKeySize() {
        return this.keySize;
    }

    public String getKeystoreType() {
        return this.keystoreType;
    }

    public abstract String getProfileType();

    public void setCertificateAlias(String str) {
        this.certificateAlias = str;
    }

    public void setKeyPairAlgorithm(String str) {
        this.keyPairAlgorithm = str;
    }

    public void setKeySize(int i) {
        this.keySize = i;
    }

    public void setKeystoreType(String str) {
        this.keystoreType = str;
    }
}
