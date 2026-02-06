package com.xh.xhcore.common.util;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Deprecated(message = "不再需要")
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/xh/xhcore/common/util/DensityHelper;", "", "density", "", "scaleDensity", "(FF)V", "getDensity", "()F", "setDensity", "(F)V", "getScaleDensity", "setScaleDensity", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DensityHelper {
    private float density;
    private float scaleDensity;

    public DensityHelper() {
        float f = 0.0f;
        this(f, f, 3, null);
    }

    public DensityHelper(float f, float f2) {
        this.density = f;
        this.scaleDensity = f2;
    }

    public DensityHelper(float f, float f2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 1.0f : f, (i & 2) != 0 ? 1.0f : f2);
    }

    public static DensityHelper copy$default(DensityHelper densityHelper, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = densityHelper.density;
        }
        if ((i & 2) != 0) {
            f2 = densityHelper.scaleDensity;
        }
        return densityHelper.copy(f, f2);
    }

    public final float getDensity() {
        return this.density;
    }

    public final float getScaleDensity() {
        return this.scaleDensity;
    }

    public final DensityHelper copy(float density, float scaleDensity) {
        return new DensityHelper(density, scaleDensity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DensityHelper)) {
            return false;
        }
        DensityHelper densityHelper = (DensityHelper) other;
        return Intrinsics.areEqual((Object) Float.valueOf(this.density), (Object) Float.valueOf(densityHelper.density)) && Intrinsics.areEqual((Object) Float.valueOf(this.scaleDensity), (Object) Float.valueOf(densityHelper.scaleDensity));
    }

    public final float getDensity() {
        return this.density;
    }

    public final float getScaleDensity() {
        return this.scaleDensity;
    }

    public int hashCode() {
        return (Float.floatToIntBits(this.density) * 31) + Float.floatToIntBits(this.scaleDensity);
    }

    public final void setDensity(float f) {
        this.density = f;
    }

    public final void setScaleDensity(float f) {
        this.scaleDensity = f;
    }

    public String toString() {
        return "DensityHelper(density=" + this.density + ", scaleDensity=" + this.scaleDensity + ')';
    }
}
