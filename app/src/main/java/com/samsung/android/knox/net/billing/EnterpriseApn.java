package com.samsung.android.knox.net.billing;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnterpriseApn implements Parcelable {
    public static Parcelable.Creator<EnterpriseApn> CREATOR = new Parcelable.Creator<EnterpriseApn>() {
        @Override
        public EnterpriseApn createFromParcel(Parcel parcel) {
            return new EnterpriseApn(parcel.readString(), parcel.readString(), parcel.readString());
        }

        @Override
        public EnterpriseApn[] newArray(int i) {
            return new EnterpriseApn[i];
        }
    };
    public final String apn;
    public final String mcc;
    public final String mnc;

    public EnterpriseApn(String str, String str2, String str3) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0) {
            throw new IllegalArgumentException();
        }
        this.apn = str;
        this.mcc = str2;
        this.mnc = str3;
    }

    static EnterpriseApn convertToNew(com.sec.enterprise.knox.billing.EnterpriseApn enterpriseApn) {
        if (enterpriseApn != null) {
            return new EnterpriseApn(enterpriseApn.apn, enterpriseApn.mcc, enterpriseApn.mnc);
        }
        return null;
    }

    static List<EnterpriseApn> convertToNewList(List<com.sec.enterprise.knox.billing.EnterpriseApn> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return null;
        }
        Iterator<com.sec.enterprise.knox.billing.EnterpriseApn> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    static com.sec.enterprise.knox.billing.EnterpriseApn convertToOld(EnterpriseApn enterpriseApn) throws NoClassDefFoundError {
        if (enterpriseApn == null) {
            return null;
        }
        try {
            return new com.sec.enterprise.knox.billing.EnterpriseApn(enterpriseApn.apn, enterpriseApn.mcc, enterpriseApn.mnc);
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(EnterpriseApn.class, 13));
        }
    }

    static List<com.sec.enterprise.knox.billing.EnterpriseApn> convertToOldList(List<EnterpriseApn> list) throws NoClassDefFoundError {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return null;
        }
        Iterator<EnterpriseApn> it = list.iterator();
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
        if (obj != null) {
            return ((EnterpriseApn) obj).toString().equalsIgnoreCase(toString());
        }
        return false;
    }

    public int hashCode() {
        String str = this.apn;
        if (str == null || this.mcc == null || this.mnc == null) {
            return 0;
        }
        int i = 0;
        for (char c : str.toCharArray()) {
            i += c;
        }
        for (char c2 : this.mcc.toCharArray()) {
            i += c2;
        }
        for (char c3 : this.mnc.toCharArray()) {
            i += c3;
        }
        return i;
    }

    public String toString() {
        return this.apn + ":" + this.mcc + ":" + this.mnc;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.apn);
        parcel.writeString(this.mcc);
        parcel.writeString(this.mnc);
    }
}
