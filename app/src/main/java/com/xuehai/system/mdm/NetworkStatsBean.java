package com.xuehai.system.mdm;

import android.os.Parcel;
import android.os.Parcelable;

public class NetworkStatsBean implements Parcelable {
    public static final Parcelable.Creator<NetworkStatsBean> CREATOR = new Parcelable.Creator<NetworkStatsBean>() {
        @Override
        public NetworkStatsBean createFromParcel(Parcel parcel) {
            return new NetworkStatsBean(parcel);
        }

        @Override
        public NetworkStatsBean[] newArray(int i) {
            return new NetworkStatsBean[i];
        }
    };
    public long mobileRxBytes;
    public long mobileTxBytes;
    public int uid;
    public long wifiRxBytes;
    public long wifiTxBytes;

    public NetworkStatsBean() {
    }

    protected NetworkStatsBean(Parcel parcel) {
        this.uid = parcel.readInt();
        this.wifiRxBytes = parcel.readLong();
        this.wifiTxBytes = parcel.readLong();
        this.mobileRxBytes = parcel.readLong();
        this.mobileTxBytes = parcel.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getMobileRxBytes() {
        return this.mobileRxBytes;
    }

    public long getMobileTxBytes() {
        return this.mobileTxBytes;
    }

    public int getUid() {
        return this.uid;
    }

    public long getWifiRxBytes() {
        return this.wifiRxBytes;
    }

    public long getWifiTxBytes() {
        return this.wifiTxBytes;
    }

    public void setMobileRxBytes(long j) {
        this.mobileRxBytes = j;
    }

    public void setMobileTxBytes(long j) {
        this.mobileTxBytes = j;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    public void setWifiRxBytes(long j) {
        this.wifiRxBytes = j;
    }

    public void setWifiTxBytes(long j) {
        this.wifiTxBytes = j;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.uid);
        parcel.writeLong(this.wifiRxBytes);
        parcel.writeLong(this.wifiTxBytes);
        parcel.writeLong(this.mobileRxBytes);
        parcel.writeLong(this.mobileTxBytes);
    }
}
