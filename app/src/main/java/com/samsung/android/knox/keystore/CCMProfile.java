package com.samsung.android.knox.keystore;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;
import com.sec.enterprise.knox.ccm.CCMProfile;
import java.util.ArrayList;
import java.util.List;

public class CCMProfile implements Parcelable {
    public static final Parcelable.Creator<CCMProfile> CREATOR = new Parcelable.Creator<CCMProfile>() {
        @Override
        public CCMProfile createFromParcel(Parcel parcel) {
            return new CCMProfile(parcel);
        }

        @Override
        public CCMProfile[] newArray(int i) {
            return new CCMProfile[i];
        }
    };
    public AccessControlMethod accessControlMethod;
    public List<String> packageList;
    public boolean whiteListAllPackages;

    public enum AccessControlMethod {
        LOCK_STATE(0);

        private int value;

        AccessControlMethod(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public CCMProfile() {
        this.accessControlMethod = AccessControlMethod.LOCK_STATE;
        this.packageList = new ArrayList();
        this.whiteListAllPackages = false;
        this.accessControlMethod = AccessControlMethod.LOCK_STATE;
    }

    private CCMProfile(Parcel parcel) {
        this.accessControlMethod = AccessControlMethod.LOCK_STATE;
        this.packageList = new ArrayList();
        this.whiteListAllPackages = false;
        readFromParcel(parcel);
    }

    static CCMProfile convertToNew(com.sec.enterprise.knox.ccm.CCMProfile cCMProfile) {
        if (cCMProfile == null) {
            return null;
        }
        CCMProfile cCMProfile2 = new CCMProfile();
        if (cCMProfile.accessControlMethod.equals(CCMProfile.AccessControlMethod.LOCK_STATE)) {
            cCMProfile2.accessControlMethod = AccessControlMethod.LOCK_STATE;
        }
        cCMProfile2.packageList = cCMProfile.packageList;
        cCMProfile2.whiteListAllPackages = cCMProfile.whiteListAllPackages;
        return cCMProfile2;
    }

    static com.sec.enterprise.knox.ccm.CCMProfile convertToOld(CCMProfile cCMProfile) throws NoClassDefFoundError {
        if (cCMProfile == null) {
            return null;
        }
        try {
            com.sec.enterprise.knox.ccm.CCMProfile cCMProfile2 = new com.sec.enterprise.knox.ccm.CCMProfile();
            if (cCMProfile.accessControlMethod.equals(AccessControlMethod.LOCK_STATE)) {
                cCMProfile2.accessControlMethod = CCMProfile.AccessControlMethod.LOCK_STATE;
            }
            cCMProfile2.packageList = cCMProfile.packageList;
            cCMProfile2.whiteListAllPackages = cCMProfile.whiteListAllPackages;
            return cCMProfile2;
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(CCMProfile.class, 12));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        try {
            AccessControlMethod accessControlMethodValueOf = AccessControlMethod.valueOf(parcel.readString());
            this.accessControlMethod = accessControlMethodValueOf;
            if (accessControlMethodValueOf == null) {
                this.accessControlMethod = AccessControlMethod.LOCK_STATE;
            }
            this.whiteListAllPackages = parcel.readInt() != 0;
            parcel.readStringList(this.packageList);
        } catch (IllegalArgumentException e) {
            this.accessControlMethod = null;
            e.printStackTrace();
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        AccessControlMethod accessControlMethod = this.accessControlMethod;
        if (accessControlMethod == null) {
            parcel.writeString(AccessControlMethod.LOCK_STATE.name());
        } else {
            parcel.writeString(accessControlMethod.name());
        }
        parcel.writeInt(this.whiteListAllPackages ? 1 : 0);
        parcel.writeStringList(this.packageList);
    }
}
