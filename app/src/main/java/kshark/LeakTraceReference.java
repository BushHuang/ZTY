package kshark;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0002!\"B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0007HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006#"}, d2 = {"Lkshark/LeakTraceReference;", "Ljava/io/Serializable;", "originObject", "Lkshark/LeakTraceObject;", "referenceType", "Lkshark/LeakTraceReference$ReferenceType;", "referenceName", "", "declaredClassName", "(Lkshark/LeakTraceObject;Lkshark/LeakTraceReference$ReferenceType;Ljava/lang/String;Ljava/lang/String;)V", "getDeclaredClassName", "()Ljava/lang/String;", "getOriginObject", "()Lkshark/LeakTraceObject;", "referenceDisplayName", "getReferenceDisplayName", "referenceGenericName", "getReferenceGenericName", "getReferenceName", "getReferenceType", "()Lkshark/LeakTraceReference$ReferenceType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "ReferenceType", "shark"}, k = 1, mv = {1, 1, 15})
public final class LeakTraceReference implements Serializable {
    private static final long serialVersionUID = 1;
    private final String declaredClassName;
    private final LeakTraceObject originObject;
    private final String referenceName;
    private final ReferenceType referenceType;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkshark/LeakTraceReference$ReferenceType;", "", "(Ljava/lang/String;I)V", "INSTANCE_FIELD", "STATIC_FIELD", "LOCAL", "ARRAY_ENTRY", "shark"}, k = 1, mv = {1, 1, 15})
    public enum ReferenceType {
        INSTANCE_FIELD,
        STATIC_FIELD,
        LOCAL,
        ARRAY_ENTRY
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ReferenceType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ReferenceType.ARRAY_ENTRY.ordinal()] = 1;
            $EnumSwitchMapping$0[ReferenceType.STATIC_FIELD.ordinal()] = 2;
            $EnumSwitchMapping$0[ReferenceType.INSTANCE_FIELD.ordinal()] = 3;
            $EnumSwitchMapping$0[ReferenceType.LOCAL.ordinal()] = 4;
            int[] iArr2 = new int[ReferenceType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[ReferenceType.ARRAY_ENTRY.ordinal()] = 1;
            $EnumSwitchMapping$1[ReferenceType.STATIC_FIELD.ordinal()] = 2;
            $EnumSwitchMapping$1[ReferenceType.INSTANCE_FIELD.ordinal()] = 3;
            $EnumSwitchMapping$1[ReferenceType.LOCAL.ordinal()] = 4;
        }
    }

    public LeakTraceReference(LeakTraceObject leakTraceObject, ReferenceType referenceType, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(leakTraceObject, "originObject");
        Intrinsics.checkParameterIsNotNull(referenceType, "referenceType");
        Intrinsics.checkParameterIsNotNull(str, "referenceName");
        Intrinsics.checkParameterIsNotNull(str2, "declaredClassName");
        this.originObject = leakTraceObject;
        this.referenceType = referenceType;
        this.referenceName = str;
        this.declaredClassName = str2;
    }

    public static LeakTraceReference copy$default(LeakTraceReference leakTraceReference, LeakTraceObject leakTraceObject, ReferenceType referenceType, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            leakTraceObject = leakTraceReference.originObject;
        }
        if ((i & 2) != 0) {
            referenceType = leakTraceReference.referenceType;
        }
        if ((i & 4) != 0) {
            str = leakTraceReference.referenceName;
        }
        if ((i & 8) != 0) {
            str2 = leakTraceReference.declaredClassName;
        }
        return leakTraceReference.copy(leakTraceObject, referenceType, str, str2);
    }

    public final LeakTraceObject getOriginObject() {
        return this.originObject;
    }

    public final ReferenceType getReferenceType() {
        return this.referenceType;
    }

    public final String getReferenceName() {
        return this.referenceName;
    }

    public final String getDeclaredClassName() {
        return this.declaredClassName;
    }

    public final LeakTraceReference copy(LeakTraceObject originObject, ReferenceType referenceType, String referenceName, String declaredClassName) {
        Intrinsics.checkParameterIsNotNull(originObject, "originObject");
        Intrinsics.checkParameterIsNotNull(referenceType, "referenceType");
        Intrinsics.checkParameterIsNotNull(referenceName, "referenceName");
        Intrinsics.checkParameterIsNotNull(declaredClassName, "declaredClassName");
        return new LeakTraceReference(originObject, referenceType, referenceName, declaredClassName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LeakTraceReference)) {
            return false;
        }
        LeakTraceReference leakTraceReference = (LeakTraceReference) other;
        return Intrinsics.areEqual(this.originObject, leakTraceReference.originObject) && Intrinsics.areEqual(this.referenceType, leakTraceReference.referenceType) && Intrinsics.areEqual(this.referenceName, leakTraceReference.referenceName) && Intrinsics.areEqual(this.declaredClassName, leakTraceReference.declaredClassName);
    }

    public final String getDeclaredClassName() {
        return this.declaredClassName;
    }

    public final LeakTraceObject getOriginObject() {
        return this.originObject;
    }

    public final String getReferenceDisplayName() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.referenceType.ordinal()];
        if (i == 1) {
            return '[' + this.referenceName + ']';
        }
        if (i == 2 || i == 3) {
            return this.referenceName;
        }
        if (i == 4) {
            return "<Java Local>";
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String getReferenceGenericName() {
        int i = WhenMappings.$EnumSwitchMapping$1[this.referenceType.ordinal()];
        if (i == 1) {
            return "[x]";
        }
        if (i == 2 || i == 3) {
            return this.referenceName;
        }
        if (i == 4) {
            return "<Java Local>";
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String getReferenceName() {
        return this.referenceName;
    }

    public final ReferenceType getReferenceType() {
        return this.referenceType;
    }

    public int hashCode() {
        LeakTraceObject leakTraceObject = this.originObject;
        int iHashCode = (leakTraceObject != null ? leakTraceObject.hashCode() : 0) * 31;
        ReferenceType referenceType = this.referenceType;
        int iHashCode2 = (iHashCode + (referenceType != null ? referenceType.hashCode() : 0)) * 31;
        String str = this.referenceName;
        int iHashCode3 = (iHashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.declaredClassName;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "LeakTraceReference(originObject=" + this.originObject + ", referenceType=" + this.referenceType + ", referenceName=" + this.referenceName + ", declaredClassName=" + this.declaredClassName + ")";
    }
}
