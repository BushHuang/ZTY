package com.xh.xhcore.common.statistic.connect.time;

import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/time/ConnectTimeConsumingModel;", "", "minConnectionTimeConsuming", "", "maxConnectionTimeConsuming", "averageConnectionTimeConsuming", "(JJJ)V", "getAverageConnectionTimeConsuming", "()J", "getMaxConnectionTimeConsuming", "getMinConnectionTimeConsuming", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ConnectTimeConsumingModel {
    private final long averageConnectionTimeConsuming;
    private final long maxConnectionTimeConsuming;
    private final long minConnectionTimeConsuming;

    public ConnectTimeConsumingModel(long j, long j2, long j3) {
        this.minConnectionTimeConsuming = j;
        this.maxConnectionTimeConsuming = j2;
        this.averageConnectionTimeConsuming = j3;
    }

    public static ConnectTimeConsumingModel copy$default(ConnectTimeConsumingModel connectTimeConsumingModel, long j, long j2, long j3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = connectTimeConsumingModel.minConnectionTimeConsuming;
        }
        long j4 = j;
        if ((i & 2) != 0) {
            j2 = connectTimeConsumingModel.maxConnectionTimeConsuming;
        }
        long j5 = j2;
        if ((i & 4) != 0) {
            j3 = connectTimeConsumingModel.averageConnectionTimeConsuming;
        }
        return connectTimeConsumingModel.copy(j4, j5, j3);
    }

    public final long getMinConnectionTimeConsuming() {
        return this.minConnectionTimeConsuming;
    }

    public final long getMaxConnectionTimeConsuming() {
        return this.maxConnectionTimeConsuming;
    }

    public final long getAverageConnectionTimeConsuming() {
        return this.averageConnectionTimeConsuming;
    }

    public final ConnectTimeConsumingModel copy(long minConnectionTimeConsuming, long maxConnectionTimeConsuming, long averageConnectionTimeConsuming) {
        return new ConnectTimeConsumingModel(minConnectionTimeConsuming, maxConnectionTimeConsuming, averageConnectionTimeConsuming);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConnectTimeConsumingModel)) {
            return false;
        }
        ConnectTimeConsumingModel connectTimeConsumingModel = (ConnectTimeConsumingModel) other;
        return this.minConnectionTimeConsuming == connectTimeConsumingModel.minConnectionTimeConsuming && this.maxConnectionTimeConsuming == connectTimeConsumingModel.maxConnectionTimeConsuming && this.averageConnectionTimeConsuming == connectTimeConsumingModel.averageConnectionTimeConsuming;
    }

    public final long getAverageConnectionTimeConsuming() {
        return this.averageConnectionTimeConsuming;
    }

    public final long getMaxConnectionTimeConsuming() {
        return this.maxConnectionTimeConsuming;
    }

    public final long getMinConnectionTimeConsuming() {
        return this.minConnectionTimeConsuming;
    }

    public int hashCode() {
        return (((C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.minConnectionTimeConsuming) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.maxConnectionTimeConsuming)) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.averageConnectionTimeConsuming);
    }

    public String toString() {
        return "ConnectTimeConsumingModel(minConnectionTimeConsuming=" + this.minConnectionTimeConsuming + ", maxConnectionTimeConsuming=" + this.maxConnectionTimeConsuming + ", averageConnectionTimeConsuming=" + this.averageConnectionTimeConsuming + ')';
    }
}
