package com.xh.xhcore.common.statistic.connect.speed.upload;

import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/speed/upload/UploadSpeedModel;", "", "maxUploadSpeed", "", "minUploadSpeed", "averageUploadSpeed", "networkType", "", "(JJJI)V", "getAverageUploadSpeed", "()J", "getMaxUploadSpeed", "getMinUploadSpeed", "getNetworkType", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UploadSpeedModel {
    private final long averageUploadSpeed;
    private final long maxUploadSpeed;
    private final long minUploadSpeed;
    private final int networkType;

    public UploadSpeedModel(long j, long j2, long j3, int i) {
        this.maxUploadSpeed = j;
        this.minUploadSpeed = j2;
        this.averageUploadSpeed = j3;
        this.networkType = i;
    }

    public final long getMaxUploadSpeed() {
        return this.maxUploadSpeed;
    }

    public final long getMinUploadSpeed() {
        return this.minUploadSpeed;
    }

    public final long getAverageUploadSpeed() {
        return this.averageUploadSpeed;
    }

    public final int getNetworkType() {
        return this.networkType;
    }

    public final UploadSpeedModel copy(long maxUploadSpeed, long minUploadSpeed, long averageUploadSpeed, int networkType) {
        return new UploadSpeedModel(maxUploadSpeed, minUploadSpeed, averageUploadSpeed, networkType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UploadSpeedModel)) {
            return false;
        }
        UploadSpeedModel uploadSpeedModel = (UploadSpeedModel) other;
        return this.maxUploadSpeed == uploadSpeedModel.maxUploadSpeed && this.minUploadSpeed == uploadSpeedModel.minUploadSpeed && this.averageUploadSpeed == uploadSpeedModel.averageUploadSpeed && this.networkType == uploadSpeedModel.networkType;
    }

    public final long getAverageUploadSpeed() {
        return this.averageUploadSpeed;
    }

    public final long getMaxUploadSpeed() {
        return this.maxUploadSpeed;
    }

    public final long getMinUploadSpeed() {
        return this.minUploadSpeed;
    }

    public final int getNetworkType() {
        return this.networkType;
    }

    public int hashCode() {
        return (((((C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.maxUploadSpeed) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.minUploadSpeed)) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.averageUploadSpeed)) * 31) + this.networkType;
    }

    public String toString() {
        return "UploadSpeedModel(maxUploadSpeed=" + this.maxUploadSpeed + ", minUploadSpeed=" + this.minUploadSpeed + ", averageUploadSpeed=" + this.averageUploadSpeed + ", networkType=" + this.networkType + ')';
    }
}
