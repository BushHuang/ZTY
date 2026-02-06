package com.samsung.android.knox.application;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class AppInfoLastUsage extends AppInfo implements Parcelable {
    public static final Parcelable.Creator<AppInfoLastUsage> CREATOR = new Parcelable.Creator<AppInfoLastUsage>() {
        @Override
        public AppInfoLastUsage createFromParcel(Parcel parcel) {
            return new AppInfoLastUsage(parcel);
        }

        @Override
        public AppInfoLastUsage[] newArray(int i) {
            return new AppInfoLastUsage[i];
        }
    };
    public int launchCountPerMonth = -1;
    public long lastAppUsage = -1;
    public long lastLaunchTime = -1;

    public AppInfoLastUsage() {
    }

    public AppInfoLastUsage(Parcel parcel) {
        readFromParcel(parcel);
    }

    static AppInfoLastUsage convertToNew(android.app.enterprise.AppInfoLastUsage appInfoLastUsage) {
        if (appInfoLastUsage == null) {
            return null;
        }
        AppInfoLastUsage appInfoLastUsage2 = new AppInfoLastUsage();
        appInfoLastUsage2.packageName = appInfoLastUsage.mPackageName;
        appInfoLastUsage2.usage = appInfoLastUsage.mUsage;
        appInfoLastUsage2.lastAppUsage = appInfoLastUsage.mLastAppUsage;
        appInfoLastUsage2.lastLaunchTime = appInfoLastUsage.mLastLaunchTime;
        appInfoLastUsage2.launchCountPerMonth = appInfoLastUsage.mLaunchCountPerMonth;
        return appInfoLastUsage2;
    }

    static AppInfoLastUsage[] convertToNewArray(android.app.enterprise.AppInfoLastUsage[] appInfoLastUsageArr) {
        if (appInfoLastUsageArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (android.app.enterprise.AppInfoLastUsage appInfoLastUsage : appInfoLastUsageArr) {
            arrayList.add(convertToNew(appInfoLastUsage));
        }
        AppInfoLastUsage[] appInfoLastUsageArr2 = new AppInfoLastUsage[arrayList.size()];
        arrayList.toArray(appInfoLastUsageArr2);
        return appInfoLastUsageArr2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.launchCountPerMonth = parcel.readInt();
        this.lastAppUsage = parcel.readLong();
        this.lastLaunchTime = parcel.readLong();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.launchCountPerMonth);
        parcel.writeLong(this.lastAppUsage);
        parcel.writeLong(this.lastLaunchTime);
    }
}
