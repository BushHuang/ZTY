package kshark.internal;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kshark.HeapField;
import kshark.HeapObject;
import kshark.HeapValue;
import kshark.ValueHolder;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u001a"}, d2 = {"Lkshark/internal/KeyedWeakReferenceMirror;", "", "referent", "Lkshark/ValueHolder$ReferenceHolder;", "key", "", "description", "watchDurationMillis", "", "retainedDurationMillis", "(Lkshark/ValueHolder$ReferenceHolder;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)V", "getDescription", "()Ljava/lang/String;", "hasReferent", "", "getHasReferent", "()Z", "isRetained", "getKey", "getReferent", "()Lkshark/ValueHolder$ReferenceHolder;", "getRetainedDurationMillis", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getWatchDurationMillis", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class KeyedWeakReferenceMirror {

    public static final Companion INSTANCE = new Companion(null);
    private static final String UNKNOWN_LEGACY = "Unknown (legacy)";
    private final String description;
    private final boolean hasReferent;
    private final boolean isRetained;
    private final String key;
    private final ValueHolder.ReferenceHolder referent;
    private final Long retainedDurationMillis;
    private final Long watchDurationMillis;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkshark/internal/KeyedWeakReferenceMirror$Companion;", "", "()V", "UNKNOWN_LEGACY", "", "fromInstance", "Lkshark/internal/KeyedWeakReferenceMirror;", "weakRef", "Lkshark/HeapObject$HeapInstance;", "heapDumpUptimeMillis", "", "(Lkshark/HeapObject$HeapInstance;Ljava/lang/Long;)Lkshark/internal/KeyedWeakReferenceMirror;", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KeyedWeakReferenceMirror fromInstance(HeapObject.HeapInstance weakRef, Long heapDumpUptimeMillis) {
            Long lValueOf;
            String asJavaString;
            HeapValue value;
            Intrinsics.checkParameterIsNotNull(weakRef, "weakRef");
            String instanceClassName = weakRef.getInstanceClassName();
            Long lValueOf2 = null;
            if (heapDumpUptimeMillis != null) {
                long jLongValue = heapDumpUptimeMillis.longValue();
                HeapField heapField = weakRef.get(instanceClassName, "watchUptimeMillis");
                if (heapField == null) {
                    Intrinsics.throwNpe();
                }
                Long asLong = heapField.getValue().getAsLong();
                if (asLong == null) {
                    Intrinsics.throwNpe();
                }
                lValueOf = Long.valueOf(jLongValue - asLong.longValue());
            } else {
                lValueOf = null;
            }
            if (heapDumpUptimeMillis != null) {
                HeapField heapField2 = weakRef.get(instanceClassName, "retainedUptimeMillis");
                if (heapField2 == null) {
                    Intrinsics.throwNpe();
                }
                Long asLong2 = heapField2.getValue().getAsLong();
                if (asLong2 == null) {
                    Intrinsics.throwNpe();
                }
                long jLongValue2 = asLong2.longValue();
                lValueOf2 = Long.valueOf(jLongValue2 != -1 ? heapDumpUptimeMillis.longValue() - jLongValue2 : -1L);
            }
            Long l = lValueOf2;
            HeapField heapField3 = weakRef.get(instanceClassName, "key");
            if (heapField3 == null) {
                Intrinsics.throwNpe();
            }
            String asJavaString2 = heapField3.getValue().readAsJavaString();
            if (asJavaString2 == null) {
                Intrinsics.throwNpe();
            }
            HeapField heapField4 = weakRef.get(instanceClassName, "description");
            if (heapField4 == null) {
                heapField4 = weakRef.get(instanceClassName, "name");
            }
            if (heapField4 == null || (value = heapField4.getValue()) == null || (asJavaString = value.readAsJavaString()) == null) {
                asJavaString = "Unknown (legacy)";
            }
            String str = asJavaString;
            HeapField heapField5 = weakRef.get("java.lang.ref.Reference", "referent");
            if (heapField5 == null) {
                Intrinsics.throwNpe();
            }
            ValueHolder holder = heapField5.getValue().getHolder();
            if (holder != null) {
                return new KeyedWeakReferenceMirror((ValueHolder.ReferenceHolder) holder, asJavaString2, str, lValueOf, l);
            }
            throw new TypeCastException("null cannot be cast to non-null type kshark.ValueHolder.ReferenceHolder");
        }
    }

    public KeyedWeakReferenceMirror(ValueHolder.ReferenceHolder referenceHolder, String str, String str2, Long l, Long l2) {
        Intrinsics.checkParameterIsNotNull(referenceHolder, "referent");
        Intrinsics.checkParameterIsNotNull(str, "key");
        Intrinsics.checkParameterIsNotNull(str2, "description");
        this.referent = referenceHolder;
        this.key = str;
        this.description = str2;
        this.watchDurationMillis = l;
        this.retainedDurationMillis = l2;
        boolean z = true;
        this.hasReferent = referenceHolder.getValue() != 0;
        Long l3 = this.retainedDurationMillis;
        if (l3 != null && l3 != null && l3.longValue() == -1) {
            z = false;
        }
        this.isRetained = z;
    }

    public final String getDescription() {
        return this.description;
    }

    public final boolean getHasReferent() {
        return this.hasReferent;
    }

    public final String getKey() {
        return this.key;
    }

    public final ValueHolder.ReferenceHolder getReferent() {
        return this.referent;
    }

    public final Long getRetainedDurationMillis() {
        return this.retainedDurationMillis;
    }

    public final Long getWatchDurationMillis() {
        return this.watchDurationMillis;
    }

    public final boolean getIsRetained() {
        return this.isRetained;
    }
}
