package com.samsung.android.knox.deviceinfo;

import android.os.Parcel;
import android.os.Parcelable;

public class SimChangeInfo implements Parcelable {
    public static final Parcelable.Creator<SimChangeInfo> CREATOR = new Parcelable.Creator<SimChangeInfo>() {
        @Override
        public SimChangeInfo createFromParcel(Parcel parcel) {
            return new SimChangeInfo(parcel);
        }

        @Override
        public SimChangeInfo[] newArray(int i) {
            return new SimChangeInfo[i];
        }
    };
    public static final int SIM_CHANGED = 2;
    public static final int SIM_INSERTED = 3;
    public static final int SIM_REMOVED = 1;
    public int changeOperation;
    public long changeTime;
    public SimInfo currentSimInfo;
    public SimInfo previousSimInfo;

    public SimChangeInfo() {
    }

    public SimChangeInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public static SimChangeInfo convertToNew(android.app.enterprise.SimChangeInfo simChangeInfo) {
        if (simChangeInfo == null) {
            return null;
        }
        SimChangeInfo simChangeInfo2 = new SimChangeInfo();
        simChangeInfo2.changeOperation = simChangeInfo.changeOperation;
        simChangeInfo2.changeTime = simChangeInfo.changeTime;
        simChangeInfo2.currentSimInfo = SimInfo.convertToNew(simChangeInfo.currentSimInfo);
        simChangeInfo2.previousSimInfo = SimInfo.convertToNew(simChangeInfo.previousSimInfo);
        return simChangeInfo2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.changeTime = parcel.readLong();
        this.changeOperation = parcel.readInt();
        this.previousSimInfo = new SimInfo(parcel);
        this.currentSimInfo = new SimInfo(parcel);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.changeTime);
        parcel.writeInt(this.changeOperation);
        this.previousSimInfo.writeToParcel(parcel, i);
        this.currentSimInfo.writeToParcel(parcel, i);
    }
}
