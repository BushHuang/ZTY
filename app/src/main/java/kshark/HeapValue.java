package kshark;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kshark.HeapObject;
import kshark.ValueHolder;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\n\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u00106\u001a\u0004\u0018\u000107R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u001f\u001a\u0004\u0018\u00010 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0013\u0010#\u001a\u0004\u0018\u00010 8F¢\u0006\u0006\u001a\u0004\b$\u0010\"R\u0013\u0010%\u001a\u0004\u0018\u00010&8F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0013\u0010)\u001a\u0004\u0018\u00010 8F¢\u0006\u0006\u001a\u0004\b*\u0010\"R\u0013\u0010+\u001a\u0004\u0018\u00010,8F¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0011\u00103\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b3\u00104R\u0011\u00105\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b5\u00104¨\u00068"}, d2 = {"Lkshark/HeapValue;", "", "graph", "Lkshark/HeapGraph;", "holder", "Lkshark/ValueHolder;", "(Lkshark/HeapGraph;Lkshark/ValueHolder;)V", "asBoolean", "", "getAsBoolean", "()Ljava/lang/Boolean;", "asByte", "", "getAsByte", "()Ljava/lang/Byte;", "asChar", "", "getAsChar", "()Ljava/lang/Character;", "asDouble", "", "getAsDouble", "()Ljava/lang/Double;", "asFloat", "", "getAsFloat", "()Ljava/lang/Float;", "asInt", "", "getAsInt", "()Ljava/lang/Integer;", "asLong", "", "getAsLong", "()Ljava/lang/Long;", "asNonNullObjectId", "getAsNonNullObjectId", "asObject", "Lkshark/HeapObject;", "getAsObject", "()Lkshark/HeapObject;", "asObjectId", "getAsObjectId", "asShort", "", "getAsShort", "()Ljava/lang/Short;", "getGraph", "()Lkshark/HeapGraph;", "getHolder", "()Lkshark/ValueHolder;", "isNonNullReference", "()Z", "isNullReference", "readAsJavaString", "", "shark"}, k = 1, mv = {1, 1, 15})
public final class HeapValue {
    private final HeapGraph graph;
    private final ValueHolder holder;

    public HeapValue(HeapGraph heapGraph, ValueHolder valueHolder) {
        Intrinsics.checkParameterIsNotNull(heapGraph, "graph");
        this.graph = heapGraph;
        this.holder = valueHolder;
    }

    public final Boolean getAsBoolean() {
        ValueHolder valueHolder = this.holder;
        if (valueHolder instanceof ValueHolder.BooleanHolder) {
            return Boolean.valueOf(((ValueHolder.BooleanHolder) valueHolder).getValue());
        }
        return null;
    }

    public final Byte getAsByte() {
        ValueHolder valueHolder = this.holder;
        if (valueHolder instanceof ValueHolder.ByteHolder) {
            return Byte.valueOf(((ValueHolder.ByteHolder) valueHolder).getValue());
        }
        return null;
    }

    public final Character getAsChar() {
        ValueHolder valueHolder = this.holder;
        if (valueHolder instanceof ValueHolder.CharHolder) {
            return Character.valueOf(((ValueHolder.CharHolder) valueHolder).getValue());
        }
        return null;
    }

    public final Double getAsDouble() {
        ValueHolder valueHolder = this.holder;
        if (valueHolder instanceof ValueHolder.DoubleHolder) {
            return Double.valueOf(((ValueHolder.DoubleHolder) valueHolder).getValue());
        }
        return null;
    }

    public final Float getAsFloat() {
        ValueHolder valueHolder = this.holder;
        if (valueHolder instanceof ValueHolder.FloatHolder) {
            return Float.valueOf(((ValueHolder.FloatHolder) valueHolder).getValue());
        }
        return null;
    }

    public final Integer getAsInt() {
        ValueHolder valueHolder = this.holder;
        if (valueHolder instanceof ValueHolder.IntHolder) {
            return Integer.valueOf(((ValueHolder.IntHolder) valueHolder).getValue());
        }
        return null;
    }

    public final Long getAsLong() {
        ValueHolder valueHolder = this.holder;
        if (valueHolder instanceof ValueHolder.LongHolder) {
            return Long.valueOf(((ValueHolder.LongHolder) valueHolder).getValue());
        }
        return null;
    }

    public final Long getAsNonNullObjectId() {
        ValueHolder valueHolder = this.holder;
        if (!(valueHolder instanceof ValueHolder.ReferenceHolder) || ((ValueHolder.ReferenceHolder) valueHolder).isNull()) {
            return null;
        }
        return Long.valueOf(((ValueHolder.ReferenceHolder) this.holder).getValue());
    }

    public final HeapObject getAsObject() {
        ValueHolder valueHolder = this.holder;
        if (!(valueHolder instanceof ValueHolder.ReferenceHolder) || ((ValueHolder.ReferenceHolder) valueHolder).isNull()) {
            return null;
        }
        return this.graph.findObjectById(((ValueHolder.ReferenceHolder) this.holder).getValue());
    }

    public final Long getAsObjectId() {
        ValueHolder valueHolder = this.holder;
        if (valueHolder instanceof ValueHolder.ReferenceHolder) {
            return Long.valueOf(((ValueHolder.ReferenceHolder) valueHolder).getValue());
        }
        return null;
    }

    public final Short getAsShort() {
        ValueHolder valueHolder = this.holder;
        if (valueHolder instanceof ValueHolder.ShortHolder) {
            return Short.valueOf(((ValueHolder.ShortHolder) valueHolder).getValue());
        }
        return null;
    }

    public final HeapGraph getGraph() {
        return this.graph;
    }

    public final ValueHolder getHolder() {
        return this.holder;
    }

    public final boolean isNonNullReference() {
        ValueHolder valueHolder = this.holder;
        return (valueHolder instanceof ValueHolder.ReferenceHolder) && !((ValueHolder.ReferenceHolder) valueHolder).isNull();
    }

    public final boolean isNullReference() {
        ValueHolder valueHolder = this.holder;
        return (valueHolder instanceof ValueHolder.ReferenceHolder) && ((ValueHolder.ReferenceHolder) valueHolder).isNull();
    }

    public final String readAsJavaString() {
        HeapObject heapObjectFindObjectByIdOrNull;
        HeapObject.HeapInstance asInstance;
        ValueHolder valueHolder = this.holder;
        if (!(valueHolder instanceof ValueHolder.ReferenceHolder) || ((ValueHolder.ReferenceHolder) valueHolder).isNull() || (heapObjectFindObjectByIdOrNull = this.graph.findObjectByIdOrNull(((ValueHolder.ReferenceHolder) this.holder).getValue())) == null || (asInstance = heapObjectFindObjectByIdOrNull.getAsInstance()) == null) {
            return null;
        }
        return asInstance.readAsJavaString();
    }
}
