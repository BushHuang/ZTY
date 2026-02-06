package kshark;

import java.io.Serializable;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kshark.internal.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0002+,B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0007¢\u0006\u0002\u0010\rJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\tHÆ\u0003J\t\u0010!\u001a\u00020\u000bHÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003JK\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0007HÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000f¨\u0006-"}, d2 = {"Lkshark/LeakTraceObject;", "Ljava/io/Serializable;", "objectId", "", "type", "Lkshark/LeakTraceObject$ObjectType;", "className", "", "labels", "", "leakingStatus", "Lkshark/LeakTraceObject$LeakingStatus;", "leakingStatusReason", "(JLkshark/LeakTraceObject$ObjectType;Ljava/lang/String;Ljava/util/Set;Lkshark/LeakTraceObject$LeakingStatus;Ljava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "classSimpleName", "getClassSimpleName", "getLabels", "()Ljava/util/Set;", "getLeakingStatus", "()Lkshark/LeakTraceObject$LeakingStatus;", "getLeakingStatusReason", "getObjectId", "()J", "getType", "()Lkshark/LeakTraceObject$ObjectType;", "typeName", "getTypeName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "LeakingStatus", "ObjectType", "shark"}, k = 1, mv = {1, 1, 15})
public final class LeakTraceObject implements Serializable {
    private final String className;
    private final Set<String> labels;
    private final LeakingStatus leakingStatus;
    private final String leakingStatusReason;
    private final long objectId;
    private final ObjectType type;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkshark/LeakTraceObject$LeakingStatus;", "", "(Ljava/lang/String;I)V", "NOT_LEAKING", "LEAKING", "UNKNOWN", "shark"}, k = 1, mv = {1, 1, 15})
    public enum LeakingStatus {
        NOT_LEAKING,
        LEAKING,
        UNKNOWN
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkshark/LeakTraceObject$ObjectType;", "", "(Ljava/lang/String;I)V", "CLASS", "ARRAY", "INSTANCE", "shark"}, k = 1, mv = {1, 1, 15})
    public enum ObjectType {
        CLASS,
        ARRAY,
        INSTANCE
    }

    public LeakTraceObject(long j, ObjectType objectType, String str, Set<String> set, LeakingStatus leakingStatus, String str2) {
        Intrinsics.checkParameterIsNotNull(objectType, "type");
        Intrinsics.checkParameterIsNotNull(str, "className");
        Intrinsics.checkParameterIsNotNull(set, "labels");
        Intrinsics.checkParameterIsNotNull(leakingStatus, "leakingStatus");
        Intrinsics.checkParameterIsNotNull(str2, "leakingStatusReason");
        this.objectId = j;
        this.type = objectType;
        this.className = str;
        this.labels = set;
        this.leakingStatus = leakingStatus;
        this.leakingStatusReason = str2;
    }

    public final long getObjectId() {
        return this.objectId;
    }

    public final ObjectType getType() {
        return this.type;
    }

    public final String getClassName() {
        return this.className;
    }

    public final Set<String> component4() {
        return this.labels;
    }

    public final LeakingStatus getLeakingStatus() {
        return this.leakingStatus;
    }

    public final String getLeakingStatusReason() {
        return this.leakingStatusReason;
    }

    public final LeakTraceObject copy(long objectId, ObjectType type, String className, Set<String> labels, LeakingStatus leakingStatus, String leakingStatusReason) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(className, "className");
        Intrinsics.checkParameterIsNotNull(labels, "labels");
        Intrinsics.checkParameterIsNotNull(leakingStatus, "leakingStatus");
        Intrinsics.checkParameterIsNotNull(leakingStatusReason, "leakingStatusReason");
        return new LeakTraceObject(objectId, type, className, labels, leakingStatus, leakingStatusReason);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof LeakTraceObject) {
                LeakTraceObject leakTraceObject = (LeakTraceObject) other;
                if (!(this.objectId == leakTraceObject.objectId) || !Intrinsics.areEqual(this.type, leakTraceObject.type) || !Intrinsics.areEqual(this.className, leakTraceObject.className) || !Intrinsics.areEqual(this.labels, leakTraceObject.labels) || !Intrinsics.areEqual(this.leakingStatus, leakTraceObject.leakingStatus) || !Intrinsics.areEqual(this.leakingStatusReason, leakTraceObject.leakingStatusReason)) {
                }
            }
            return false;
        }
        return true;
    }

    public final String getClassName() {
        return this.className;
    }

    public final String getClassSimpleName() {
        return StringsKt.lastSegment(this.className, '.');
    }

    public final Set<String> getLabels() {
        return this.labels;
    }

    public final LeakingStatus getLeakingStatus() {
        return this.leakingStatus;
    }

    public final String getLeakingStatusReason() {
        return this.leakingStatusReason;
    }

    public final long getObjectId() {
        return this.objectId;
    }

    public final ObjectType getType() {
        return this.type;
    }

    public final String getTypeName() {
        String strName = this.type.name();
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        if (strName == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = strName.toLowerCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }

    public int hashCode() {
        long j = this.objectId;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        ObjectType objectType = this.type;
        int iHashCode = (i + (objectType != null ? objectType.hashCode() : 0)) * 31;
        String str = this.className;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        Set<String> set = this.labels;
        int iHashCode3 = (iHashCode2 + (set != null ? set.hashCode() : 0)) * 31;
        LeakingStatus leakingStatus = this.leakingStatus;
        int iHashCode4 = (iHashCode3 + (leakingStatus != null ? leakingStatus.hashCode() : 0)) * 31;
        String str2 = this.leakingStatusReason;
        return iHashCode4 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "LeakTraceObject(objectId=" + this.objectId + ", type=" + this.type + ", className=" + this.className + ", labels=" + this.labels + ", leakingStatus=" + this.leakingStatus + ", leakingStatusReason=" + this.leakingStatusReason + ")";
    }
}
