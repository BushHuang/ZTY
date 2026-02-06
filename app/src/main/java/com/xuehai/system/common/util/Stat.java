package com.xuehai.system.common.util;

import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/xuehai/system/common/util/Stat;", "", "packageName", "", "totalTime", "", "(Ljava/lang/String;J)V", "getPackageName", "()Ljava/lang/String;", "getTotalTime", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Stat {
    private final String packageName;
    private final long totalTime;

    public Stat(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        this.packageName = str;
        this.totalTime = j;
    }

    public static Stat copy$default(Stat stat, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = stat.packageName;
        }
        if ((i & 2) != 0) {
            j = stat.totalTime;
        }
        return stat.copy(str, j);
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final long getTotalTime() {
        return this.totalTime;
    }

    public final Stat copy(String packageName, long totalTime) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new Stat(packageName, totalTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Stat)) {
            return false;
        }
        Stat stat = (Stat) other;
        return Intrinsics.areEqual(this.packageName, stat.packageName) && this.totalTime == stat.totalTime;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final long getTotalTime() {
        return this.totalTime;
    }

    public int hashCode() {
        return (this.packageName.hashCode() * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.totalTime);
    }

    public String toString() {
        return "Stat--" + this.packageName + ": " + this.totalTime + "ms;";
    }
}
