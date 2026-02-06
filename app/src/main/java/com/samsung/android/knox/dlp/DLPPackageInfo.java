package com.samsung.android.knox.dlp;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.SupportLibUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DLPPackageInfo implements Parcelable {
    public static final Parcelable.Creator<DLPPackageInfo> CREATOR = new Parcelable.Creator<DLPPackageInfo>() {
        @Override
        public DLPPackageInfo createFromParcel(Parcel parcel) {
            return new DLPPackageInfo(parcel);
        }

        @Override
        public DLPPackageInfo[] newArray(int i) {
            return new DLPPackageInfo[i];
        }
    };
    public AppIdentity appIdentity;
    public Bundle extras;

    public DLPPackageInfo() {
    }

    private DLPPackageInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public DLPPackageInfo(AppIdentity appIdentity, Bundle bundle) {
        this.appIdentity = appIdentity;
        this.extras = bundle;
    }

    static DLPPackageInfo convertToNew(com.sec.enterprise.knox.dlp.DLPPackageInfo dLPPackageInfo) {
        if (dLPPackageInfo == null) {
            return null;
        }
        return new DLPPackageInfo(AppIdentity.convertToNew(dLPPackageInfo.appIdentity), dLPPackageInfo.extras);
    }

    static List<DLPPackageInfo> convertToNewList(List<com.sec.enterprise.knox.dlp.DLPPackageInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return null;
        }
        Iterator<com.sec.enterprise.knox.dlp.DLPPackageInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    static com.sec.enterprise.knox.dlp.DLPPackageInfo convertToOld(DLPPackageInfo dLPPackageInfo) throws NoClassDefFoundError {
        if (dLPPackageInfo == null) {
            return null;
        }
        try {
            try {
                return new com.sec.enterprise.knox.dlp.DLPPackageInfo(AppIdentity.convertToOld(dLPPackageInfo.appIdentity), dLPPackageInfo.extras);
            } catch (NoClassDefFoundError unused) {
                throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(DLPPackageInfo.class, 19));
            }
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static List<com.sec.enterprise.knox.dlp.DLPPackageInfo> convertToOldList(List<DLPPackageInfo> list) throws NoClassDefFoundError {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return null;
        }
        Iterator<DLPPackageInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToOld(it.next()));
        }
        return arrayList;
    }

    private void readFromParcel(Parcel parcel) {
        this.appIdentity = (AppIdentity) parcel.readParcelable(AppIdentity.class.getClassLoader());
        this.extras = parcel.readBundle();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.appIdentity, i);
        parcel.writeBundle(this.extras);
    }
}
