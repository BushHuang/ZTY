package com.xh.xhcore.common.statistic.connect.traffic.download;

import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/traffic/download/DownloadTrafficModel;", "", "networkTraffic", "", "networkType", "", "(JI)V", "getNetworkTraffic", "()J", "getNetworkType", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadTrafficModel {
    private final long networkTraffic;
    private final int networkType;

    public DownloadTrafficModel(long j, int i) {
        this.networkTraffic = j;
        this.networkType = i;
    }

    public static DownloadTrafficModel copy$default(DownloadTrafficModel downloadTrafficModel, long j, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = downloadTrafficModel.networkTraffic;
        }
        if ((i2 & 2) != 0) {
            i = downloadTrafficModel.networkType;
        }
        return downloadTrafficModel.copy(j, i);
    }

    public final long getNetworkTraffic() {
        return this.networkTraffic;
    }

    public final int getNetworkType() {
        return this.networkType;
    }

    public final DownloadTrafficModel copy(long networkTraffic, int networkType) {
        return new DownloadTrafficModel(networkTraffic, networkType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadTrafficModel)) {
            return false;
        }
        DownloadTrafficModel downloadTrafficModel = (DownloadTrafficModel) other;
        return this.networkTraffic == downloadTrafficModel.networkTraffic && this.networkType == downloadTrafficModel.networkType;
    }

    public final long getNetworkTraffic() {
        return this.networkTraffic;
    }

    public final int getNetworkType() {
        return this.networkType;
    }

    public int hashCode() {
        return (C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.networkTraffic) * 31) + this.networkType;
    }

    public String toString() {
        return "DownloadTrafficModel(networkTraffic=" + this.networkTraffic + ", networkType=" + this.networkType + ')';
    }
}
