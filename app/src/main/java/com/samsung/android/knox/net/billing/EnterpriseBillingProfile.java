package com.samsung.android.knox.net.billing;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;
import java.util.ArrayList;
import java.util.List;

public class EnterpriseBillingProfile implements Parcelable {
    public static Parcelable.Creator<EnterpriseBillingProfile> CREATOR = new Parcelable.Creator<EnterpriseBillingProfile>() {
        @Override
        public EnterpriseBillingProfile createFromParcel(Parcel parcel) {
            EnterpriseBillingProfile enterpriseBillingProfile = new EnterpriseBillingProfile(parcel.readString());
            enterpriseBillingProfile.addApnsToProfile(parcel.readArrayList(enterpriseBillingProfile.getClass().getClassLoader()));
            return enterpriseBillingProfile;
        }

        @Override
        public EnterpriseBillingProfile[] newArray(int i) {
            return new EnterpriseBillingProfile[i];
        }
    };
    private final List<EnterpriseApn> apns;
    private final String profileName;

    public EnterpriseBillingProfile(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException();
        }
        this.profileName = str;
        this.apns = new ArrayList();
    }

    static EnterpriseBillingProfile convertToNew(com.sec.enterprise.knox.billing.EnterpriseBillingProfile enterpriseBillingProfile) {
        if (enterpriseBillingProfile == null) {
            return null;
        }
        EnterpriseBillingProfile enterpriseBillingProfile2 = new EnterpriseBillingProfile(enterpriseBillingProfile.getProfileName());
        enterpriseBillingProfile2.addApnsToProfile(EnterpriseApn.convertToNewList(enterpriseBillingProfile.getApnsFromProfile()));
        return enterpriseBillingProfile2;
    }

    static com.sec.enterprise.knox.billing.EnterpriseBillingProfile convertToOld(EnterpriseBillingProfile enterpriseBillingProfile) throws NoClassDefFoundError {
        if (enterpriseBillingProfile == null) {
            return null;
        }
        try {
            com.sec.enterprise.knox.billing.EnterpriseBillingProfile enterpriseBillingProfile2 = new com.sec.enterprise.knox.billing.EnterpriseBillingProfile(enterpriseBillingProfile.getProfileName());
            enterpriseBillingProfile2.addApnsToProfile(EnterpriseApn.convertToOldList(enterpriseBillingProfile.getApnsFromProfile()));
            return enterpriseBillingProfile2;
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(EnterpriseBillingProfile.class, 13));
        }
    }

    public void addApnToProfile(EnterpriseApn enterpriseApn) {
        if (enterpriseApn != null) {
            this.apns.add(enterpriseApn);
        }
    }

    public void addApnsToProfile(List<EnterpriseApn> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.apns.addAll(list);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<EnterpriseApn> getApnsFromProfile() {
        return this.apns;
    }

    public String getProfileName() {
        return this.profileName;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.profileName);
        parcel.writeList(this.apns);
    }
}
