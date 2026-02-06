package com.samsung.android.knox;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppIdentity implements Parcelable {
    public static final Parcelable.Creator<AppIdentity> CREATOR = new Parcelable.Creator<AppIdentity>() {
        @Override
        public AppIdentity createFromParcel(Parcel parcel) {
            return new AppIdentity(parcel);
        }

        @Override
        public AppIdentity[] newArray(int i) {
            return new AppIdentity[i];
        }
    };
    private String packageName;
    private String signature;

    public AppIdentity() {
    }

    private AppIdentity(Parcel parcel) {
        readFromParcel(parcel);
    }

    public AppIdentity(String str, String str2) {
        this.packageName = str;
        this.signature = str2;
    }

    public static AppIdentity convertToNew(com.sec.enterprise.AppIdentity appIdentity) {
        if (appIdentity == null) {
            return null;
        }
        AppIdentity appIdentity2 = new AppIdentity();
        appIdentity2.setPackageName(appIdentity.getPackageName());
        appIdentity2.setSignature(appIdentity.getSignature());
        return appIdentity2;
    }

    public static com.sec.enterprise.AppIdentity convertToOld(AppIdentity appIdentity) throws NoClassDefFoundError {
        if (appIdentity == null) {
            return null;
        }
        try {
            com.sec.enterprise.AppIdentity appIdentity2 = new com.sec.enterprise.AppIdentity();
            appIdentity2.setPackageName(appIdentity.getPackageName());
            appIdentity2.setSignature(appIdentity.getSignature());
            return appIdentity2;
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(AppIdentity.class, 17));
        }
    }

    public static List<com.sec.enterprise.AppIdentity> convertToOldList(List<AppIdentity> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<AppIdentity> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToOld(it.next()));
        }
        return arrayList;
    }

    public static List<AppIdentity> convertoToNewList(List<com.sec.enterprise.AppIdentity> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<com.sec.enterprise.AppIdentity> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    private void readFromParcel(Parcel parcel) {
        this.packageName = parcel.readString();
        this.signature = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
        parcel.writeString(this.signature);
    }
}
