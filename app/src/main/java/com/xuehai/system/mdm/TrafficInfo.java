package com.xuehai.system.mdm;

import android.os.Parcel;
import android.os.Parcelable;

public class TrafficInfo implements Parcelable {
    public static final Parcelable.Creator<TrafficInfo> CREATOR = new Parcelable.Creator<TrafficInfo>() {
        @Override
        public TrafficInfo createFromParcel(Parcel parcel) {
            return new TrafficInfo(parcel);
        }

        @Override
        public TrafficInfo[] newArray(int i) {
            return new TrafficInfo[i];
        }
    };
    private long rxBytes;
    private long totalData;
    private long txBytes;
    private int uid;

    public TrafficInfo() {
    }

    protected TrafficInfo(Parcel parcel) {
        this.totalData = parcel.readLong();
        this.rxBytes = parcel.readLong();
        this.txBytes = parcel.readLong();
        this.uid = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getRxBytes() {
        return this.rxBytes;
    }

    public long getTotalData() {
        return this.totalData;
    }

    public long getTxBytes() {
        return this.txBytes;
    }

    public int getUid() {
        return this.uid;
    }

    public void readFromParcel(Parcel parcel) {
        this.totalData = parcel.readLong();
        this.rxBytes = parcel.readLong();
        this.txBytes = parcel.readLong();
        this.uid = parcel.readInt();
    }

    public void setRxBytes(long j) {
        this.rxBytes = j;
    }

    public void setTotalData(long j) {
        this.totalData = j;
    }

    public void setTxBytes(long j) {
        this.txBytes = j;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.totalData);
        parcel.writeLong(this.rxBytes);
        parcel.writeLong(this.txBytes);
        parcel.writeInt(this.uid);
    }
}
