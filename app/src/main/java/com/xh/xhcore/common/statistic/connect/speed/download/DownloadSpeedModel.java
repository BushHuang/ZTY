package com.xh.xhcore.common.statistic.connect.speed.download;

import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/speed/download/DownloadSpeedModel;", "", "maxDownloadSpeed", "", "minDownloadSpeed", "averageDownloadSpeed", "networkType", "", "(JJJI)V", "getAverageDownloadSpeed", "()J", "getMaxDownloadSpeed", "getMinDownloadSpeed", "getNetworkType", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadSpeedModel {
    private final long averageDownloadSpeed;
    private final long maxDownloadSpeed;
    private final long minDownloadSpeed;
    private final int networkType;

    public DownloadSpeedModel(long j, long j2, long j3, int i) {
        this.maxDownloadSpeed = j;
        this.minDownloadSpeed = j2;
        this.averageDownloadSpeed = j3;
        this.networkType = i;
    }

    public final long getMaxDownloadSpeed() {
        return this.maxDownloadSpeed;
    }

    public final long getMinDownloadSpeed() {
        return this.minDownloadSpeed;
    }

    public final long getAverageDownloadSpeed() {
        return this.averageDownloadSpeed;
    }

    public final int getNetworkType() {
        return this.networkType;
    }

    public final DownloadSpeedModel copy(long maxDownloadSpeed, long minDownloadSpeed, long averageDownloadSpeed, int networkType) {
        return new DownloadSpeedModel(maxDownloadSpeed, minDownloadSpeed, averageDownloadSpeed, networkType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadSpeedModel)) {
            return false;
        }
        DownloadSpeedModel downloadSpeedModel = (DownloadSpeedModel) other;
        return this.maxDownloadSpeed == downloadSpeedModel.maxDownloadSpeed && this.minDownloadSpeed == downloadSpeedModel.minDownloadSpeed && this.averageDownloadSpeed == downloadSpeedModel.averageDownloadSpeed && this.networkType == downloadSpeedModel.networkType;
    }

    public final long getAverageDownloadSpeed() {
        return this.averageDownloadSpeed;
    }

    public final long getMaxDownloadSpeed() {
        return this.maxDownloadSpeed;
    }

    public final long getMinDownloadSpeed() {
        return this.minDownloadSpeed;
    }

    public final int getNetworkType() {
        return this.networkType;
    }

    public int hashCode() {
        return (((((C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.maxDownloadSpeed) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.minDownloadSpeed)) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.averageDownloadSpeed)) * 31) + this.networkType;
    }

    public String toString() {
        return "DownloadSpeedModel(maxDownloadSpeed=" + this.maxDownloadSpeed + ", minDownloadSpeed=" + this.minDownloadSpeed + ", averageDownloadSpeed=" + this.averageDownloadSpeed + ", networkType=" + this.networkType + ')';
    }
}
