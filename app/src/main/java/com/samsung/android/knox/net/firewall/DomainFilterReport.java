package com.samsung.android.knox.net.firewall;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DomainFilterReport implements Parcelable {
    public static final Parcelable.Creator<DomainFilterReport> CREATOR = new Parcelable.Creator<DomainFilterReport>() {
        @Override
        public DomainFilterReport createFromParcel(Parcel parcel) {
            return new DomainFilterReport(parcel);
        }

        @Override
        public DomainFilterReport[] newArray(int i) {
            return new DomainFilterReport[i];
        }
    };
    private String mDomainUrl;
    private String mPackageName;
    private long mTimeStamp;

    private DomainFilterReport() {
    }

    private DomainFilterReport(Parcel parcel) {
        this();
        this.mPackageName = parcel.readString();
        this.mTimeStamp = parcel.readLong();
        this.mDomainUrl = parcel.readString();
    }

    public DomainFilterReport(String str, long j, String str2) {
        this.mPackageName = str;
        this.mTimeStamp = j;
        this.mDomainUrl = str2;
    }

    private static DomainFilterReport convertToNew(com.sec.enterprise.firewall.DomainFilterReport domainFilterReport) {
        if (domainFilterReport == null) {
            return null;
        }
        return new DomainFilterReport(domainFilterReport.getPackageName(), domainFilterReport.getTimeStamp(), domainFilterReport.getDomainUrl());
    }

    static List<DomainFilterReport> convertToNewList(List<com.sec.enterprise.firewall.DomainFilterReport> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<com.sec.enterprise.firewall.DomainFilterReport> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getDomainUrl() {
        return this.mDomainUrl;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPackageName);
        parcel.writeLong(this.mTimeStamp);
        parcel.writeString(this.mDomainUrl);
    }
}
