package com.samsung.android.knox.keystore;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;
import com.sec.enterprise.knox.ccm.CSRProfile;

public class CSRProfile implements Parcelable {
    public static final Parcelable.Creator<CSRProfile> CREATOR = new Parcelable.Creator<CSRProfile>() {
        @Override
        public CSRProfile createFromParcel(Parcel parcel) {
            return new CSRProfile(parcel);
        }

        @Override
        public CSRProfile[] newArray(int i) {
            return new CSRProfile[i];
        }
    };
    public String commonName;
    public String country;
    public CSRFormat csrFormat;
    public String domainComponent;
    public String emailAddress;
    public KeyAlgorithm keyAlgType;
    public int keyLength;
    public String locality;
    public String organization;
    public ProfileType profileType;
    public String state;
    public String templateName;

    public enum CSRFormat {
        PKCS10
    }

    public enum KeyAlgorithm {
        RSA
    }

    public enum ProfileType {
        SCEP,
        CMP,
        CMC,
        PROPRIETARY
    }

    public CSRProfile() {
        this.profileType = ProfileType.SCEP;
        this.csrFormat = CSRFormat.PKCS10;
        this.keyAlgType = KeyAlgorithm.RSA;
        this.templateName = null;
        this.keyLength = 1024;
        this.commonName = null;
        this.organization = null;
        this.domainComponent = null;
        this.emailAddress = null;
        this.country = null;
        this.state = null;
        this.locality = null;
        this.profileType = ProfileType.SCEP;
        this.csrFormat = CSRFormat.PKCS10;
        this.keyAlgType = KeyAlgorithm.RSA;
    }

    private CSRProfile(Parcel parcel) {
        this.profileType = ProfileType.SCEP;
        this.csrFormat = CSRFormat.PKCS10;
        this.keyAlgType = KeyAlgorithm.RSA;
        this.templateName = null;
        this.keyLength = 1024;
        this.commonName = null;
        this.organization = null;
        this.domainComponent = null;
        this.emailAddress = null;
        this.country = null;
        this.state = null;
        this.locality = null;
        try {
            this.profileType = ProfileType.valueOf(parcel.readString());
        } catch (IllegalArgumentException e) {
            this.profileType = null;
            e.printStackTrace();
        }
        if (this.profileType == null) {
            this.profileType = ProfileType.SCEP;
        }
        try {
            this.csrFormat = CSRFormat.valueOf(parcel.readString());
        } catch (IllegalArgumentException e2) {
            this.csrFormat = null;
            e2.printStackTrace();
        }
        if (this.csrFormat == null) {
            this.csrFormat = CSRFormat.PKCS10;
        }
        try {
            this.keyAlgType = KeyAlgorithm.valueOf(parcel.readString());
        } catch (IllegalArgumentException e3) {
            this.keyAlgType = null;
            e3.printStackTrace();
        }
        if (this.keyAlgType == null) {
            this.keyAlgType = KeyAlgorithm.RSA;
        }
        this.templateName = parcel.readString();
        this.keyLength = parcel.readInt();
        this.commonName = parcel.readString();
        this.organization = parcel.readString();
        this.domainComponent = parcel.readString();
        this.emailAddress = parcel.readString();
        this.country = parcel.readString();
        this.state = parcel.readString();
        this.locality = parcel.readString();
    }

    static CSRProfile convertToNew(com.sec.enterprise.knox.ccm.CSRProfile cSRProfile) {
        if (cSRProfile == null) {
            return null;
        }
        CSRProfile cSRProfile2 = new CSRProfile();
        if (cSRProfile.profileType.equals(CSRProfile.ProfileType.SCEP)) {
            cSRProfile2.profileType = ProfileType.SCEP;
        } else if (cSRProfile.profileType.equals(CSRProfile.ProfileType.CMC)) {
            cSRProfile2.profileType = ProfileType.CMC;
        } else if (cSRProfile.profileType.equals(CSRProfile.ProfileType.CMP)) {
            cSRProfile2.profileType = ProfileType.CMP;
        } else if (cSRProfile.profileType.equals(CSRProfile.ProfileType.PROPRIETARY)) {
            cSRProfile2.profileType = ProfileType.PROPRIETARY;
        }
        if (cSRProfile.csrFormat.equals(CSRProfile.CSRFormat.PKCS10)) {
            cSRProfile2.csrFormat = CSRFormat.PKCS10;
        }
        if (cSRProfile.keyAlgType.equals(CSRProfile.KeyAlgorithm.RSA)) {
            cSRProfile2.keyAlgType = KeyAlgorithm.RSA;
        }
        cSRProfile2.templateName = cSRProfile.templateName;
        cSRProfile2.keyLength = cSRProfile.keyLength;
        cSRProfile2.commonName = cSRProfile.commonName;
        cSRProfile2.organization = cSRProfile.organization;
        cSRProfile2.domainComponent = cSRProfile.domainComponent;
        cSRProfile2.emailAddress = cSRProfile.emailAddress;
        cSRProfile2.country = cSRProfile.country;
        cSRProfile2.state = cSRProfile.state;
        cSRProfile2.locality = cSRProfile.locality;
        return cSRProfile2;
    }

    static com.sec.enterprise.knox.ccm.CSRProfile convertToOld(CSRProfile cSRProfile) throws NoClassDefFoundError {
        if (cSRProfile == null) {
            return null;
        }
        try {
            com.sec.enterprise.knox.ccm.CSRProfile cSRProfile2 = new com.sec.enterprise.knox.ccm.CSRProfile();
            if (cSRProfile.profileType.equals(ProfileType.SCEP)) {
                cSRProfile2.profileType = CSRProfile.ProfileType.SCEP;
            } else if (cSRProfile.profileType.equals(ProfileType.CMC)) {
                cSRProfile2.profileType = CSRProfile.ProfileType.CMC;
            } else if (cSRProfile.profileType.equals(ProfileType.CMP)) {
                cSRProfile2.profileType = CSRProfile.ProfileType.CMP;
            } else if (cSRProfile.profileType.equals(ProfileType.PROPRIETARY)) {
                cSRProfile2.profileType = CSRProfile.ProfileType.PROPRIETARY;
            }
            if (cSRProfile.csrFormat.equals(CSRFormat.PKCS10)) {
                cSRProfile2.csrFormat = CSRProfile.CSRFormat.PKCS10;
            }
            if (cSRProfile.keyAlgType.equals(KeyAlgorithm.RSA)) {
                cSRProfile2.keyAlgType = CSRProfile.KeyAlgorithm.RSA;
            }
            cSRProfile2.templateName = cSRProfile.templateName;
            cSRProfile2.keyLength = cSRProfile.keyLength;
            cSRProfile2.commonName = cSRProfile.commonName;
            cSRProfile2.organization = cSRProfile.organization;
            cSRProfile2.domainComponent = cSRProfile.domainComponent;
            cSRProfile2.emailAddress = cSRProfile.emailAddress;
            cSRProfile2.country = cSRProfile.country;
            cSRProfile2.state = cSRProfile.state;
            cSRProfile2.locality = cSRProfile.locality;
            return cSRProfile2;
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(CSRProfile.class, 12));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        ProfileType profileType = this.profileType;
        if (profileType == null) {
            parcel.writeString(ProfileType.SCEP.name());
        } else {
            parcel.writeString(profileType.name());
        }
        CSRFormat cSRFormat = this.csrFormat;
        if (cSRFormat == null) {
            parcel.writeString(CSRFormat.PKCS10.name());
        } else {
            parcel.writeString(cSRFormat.name());
        }
        KeyAlgorithm keyAlgorithm = this.keyAlgType;
        if (keyAlgorithm == null) {
            parcel.writeString(KeyAlgorithm.RSA.name());
        } else {
            parcel.writeString(keyAlgorithm.name());
        }
        parcel.writeString(this.templateName);
        parcel.writeInt(this.keyLength);
        parcel.writeString(this.commonName);
        parcel.writeString(this.organization);
        parcel.writeString(this.domainComponent);
        parcel.writeString(this.emailAddress);
        parcel.writeString(this.country);
        parcel.writeString(this.state);
        parcel.writeString(this.locality);
    }
}
