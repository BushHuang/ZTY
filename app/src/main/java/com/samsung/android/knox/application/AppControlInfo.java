package com.samsung.android.knox.application;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppControlInfo implements Parcelable {
    public static final Parcelable.Creator<AppControlInfo> CREATOR = new Parcelable.Creator<AppControlInfo>() {
        @Override
        public AppControlInfo createFromParcel(Parcel parcel) {
            return new AppControlInfo(parcel);
        }

        @Override
        public AppControlInfo[] newArray(int i) {
            return new AppControlInfo[i];
        }
    };
    public String adminPackageName;
    public List<String> entries;
    final Object mQueueLock;

    public AppControlInfo() {
        this.adminPackageName = null;
        this.entries = null;
        this.mQueueLock = new Object();
    }

    private AppControlInfo(Parcel parcel) {
        this.adminPackageName = null;
        this.entries = null;
        this.mQueueLock = new Object();
        readFromParcel(parcel);
    }

    static AppControlInfo convertToNew(android.app.enterprise.AppControlInfo appControlInfo) {
        if (appControlInfo == null) {
            return null;
        }
        AppControlInfo appControlInfo2 = new AppControlInfo();
        appControlInfo2.adminPackageName = appControlInfo.adminPackageName;
        appControlInfo2.entries = appControlInfo.entries;
        return appControlInfo2;
    }

    static List<AppControlInfo> convertToNewList(List<android.app.enterprise.AppControlInfo> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<android.app.enterprise.AppControlInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        synchronized (this.mQueueLock) {
            this.adminPackageName = parcel.readString();
            this.entries = parcel.createStringArrayList();
        }
    }

    public String toString() {
        return "adminPackageName: " + this.adminPackageName + " ,appControlType: " + this.entries;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this.mQueueLock) {
            parcel.writeString(this.adminPackageName);
            parcel.writeStringList(this.entries);
        }
    }
}
