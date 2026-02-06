package com.xuehai.system.mdm;

import android.os.Parcel;
import android.os.Parcelable;

public class AppUsage2 implements Parcelable {
    public static final Parcelable.Creator<AppUsage2> CREATOR = new Parcelable.Creator<AppUsage2>() {
        @Override
        public AppUsage2 createFromParcel(Parcel parcel) {
            return new AppUsage2(parcel);
        }

        @Override
        public AppUsage2[] newArray(int i) {
            return new AppUsage2[i];
        }
    };
    private long lastTimeUsed;
    private String packageName;
    private long usedTime;

    public AppUsage2() {
    }

    protected AppUsage2(Parcel parcel) {
        this.packageName = parcel.readString();
        this.usedTime = parcel.readLong();
        this.lastTimeUsed = parcel.readLong();
    }

    public AppUsage2(String str, long j, long j2) {
        this.packageName = str;
        this.usedTime = j;
        this.lastTimeUsed = j2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getLastTimeUsed() {
        return this.lastTimeUsed;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public long getUsedTime() {
        return this.usedTime;
    }

    public void setLastTimeUsed(long j) {
        this.lastTimeUsed = j;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setUsedTime(long j) {
        this.usedTime = j;
    }

    public String toString() {
        return "AppUsage{packageName='" + this.packageName + "', usedTime=" + this.usedTime + ", lastTimeUsed=" + this.lastTimeUsed + '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
        parcel.writeLong(this.usedTime);
        parcel.writeLong(this.lastTimeUsed);
    }
}
