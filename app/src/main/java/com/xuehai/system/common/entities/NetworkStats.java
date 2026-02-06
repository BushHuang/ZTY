package com.xuehai.system.common.entities;

public class NetworkStats {
    public long mobileRxBytes;
    public long mobileTxBytes;
    public int uid;
    public long wifiRxBytes;
    public long wifiTxBytes;

    public String toString() {
        return "{\"uid\":" + this.uid + ",\"wifiRxBytes\":" + this.wifiRxBytes + ",\"wifiTxBytes\":" + this.wifiTxBytes + ",\"mobileRxBytes\":" + this.mobileRxBytes + ",\"mobileTxBytes\":" + this.mobileTxBytes + "}";
    }
}
