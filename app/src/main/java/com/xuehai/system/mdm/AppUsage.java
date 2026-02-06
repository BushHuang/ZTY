package com.xuehai.system.mdm;

import android.os.Parcel;
import android.os.Parcelable;

public class AppUsage implements Parcelable {
    public static final Parcelable.Creator<AppUsage> CREATOR = new Parcelable.Creator<AppUsage>() {
        @Override
        public AppUsage createFromParcel(Parcel parcel) {
            return new AppUsage(parcel);
        }

        @Override
        public AppUsage[] newArray(int i) {
            return new AppUsage[i];
        }
    };
    private String packageName;
    private long usedTime;

    public AppUsage() {
    }

    protected AppUsage(Parcel parcel) {
        this.packageName = parcel.readString();
        this.usedTime = parcel.readLong();
    }

    public AppUsage(String str, long j) {
        this.packageName = str;
        this.usedTime = j;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public long getUsedTime() {
        return this.usedTime;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setUsedTime(long j) {
        this.usedTime = j;
    }

    public String toString() {
        return "AppUsage{packageName='" + this.packageName + "', usedTime=" + this.usedTime + '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
        parcel.writeLong(this.usedTime);
    }
}
