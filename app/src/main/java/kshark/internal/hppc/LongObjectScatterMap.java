package kshark.internal.hppc;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J%\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001fJ\u000e\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u001dJ\u000e\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0005J\u0018\u0010$\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00028\u00000&0%J\u0018\u0010'\u001a\u0004\u0018\u00018\u00002\u0006\u0010!\u001a\u00020\u001dH\u0086\u0002¢\u0006\u0002\u0010(J\u0010\u0010)\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u001dH\u0002J%\u0010*\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u000b2\u000e\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0014H\u0002¢\u0006\u0002\u0010-J\u0006\u0010.\u001a\u00020\u0018J\u0015\u0010/\u001a\u0004\u0018\u00018\u00002\u0006\u0010!\u001a\u00020\u001d¢\u0006\u0002\u0010(J \u00100\u001a\u0004\u0018\u00018\u00002\u0006\u0010!\u001a\u00020\u001d2\u0006\u00101\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u00102J\u0010\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0014X\u0082\u000e¢\u0006\n\n\u0002\u0010\u0016\u0012\u0004\b\u0015\u0010\u0003¨\u00065"}, d2 = {"Lkshark/internal/hppc/LongObjectScatterMap;", "T", "", "()V", "assigned", "", "hasEmptyKey", "", "isEmpty", "()Z", "keys", "", "loadFactor", "", "mask", "resizeAt", "size", "getSize", "()I", "values", "", "values$annotations", "[Ljava/lang/Object;", "allocateBuffers", "", "arraySize", "allocateThenInsertThenRehash", "slot", "pendingKey", "", "pendingValue", "(IJLjava/lang/Object;)V", "containsKey", "key", "ensureCapacity", "expectedElements", "entrySequence", "Lkotlin/sequences/Sequence;", "Lkotlin/Pair;", "get", "(J)Ljava/lang/Object;", "hashKey", "rehash", "fromKeys", "fromValues", "([J[Ljava/lang/Object;)V", "release", "remove", "set", "value", "(JLjava/lang/Object;)Ljava/lang/Object;", "shiftConflictingKeys", "gapSlotArg", "shark"}, k = 1, mv = {1, 1, 15})
public final class LongObjectScatterMap<T> {
    private int assigned;
    private boolean hasEmptyKey;
    private int mask;
    private int resizeAt;
    private long[] keys = new long[0];
    private T[] values = (T[]) new Object[0];
    private double loadFactor = 0.75d;

    public LongObjectScatterMap() {
        ensureCapacity(4);
    }

    private final void allocateBuffers(int arraySize) {
        long[] jArr = this.keys;
        T[] tArr = this.values;
        int i = arraySize + 1;
        try {
            this.keys = new long[i];
            this.values = (T[]) new Object[i];
            this.resizeAt = HHPC.INSTANCE.expandAtCount(arraySize, this.loadFactor);
            this.mask = arraySize - 1;
        } catch (OutOfMemoryError e) {
            this.keys = jArr;
            this.values = tArr;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ROOT;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
            String str = String.format(locale, "Not enough memory to allocate buffers for rehashing: %,d -> %,d", Arrays.copyOf(new Object[]{Integer.valueOf(this.mask + 1), Integer.valueOf(arraySize)}, 2));
            Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(locale, format, *args)");
            throw new RuntimeException(str, e);
        }
    }

    private final void allocateThenInsertThenRehash(int slot, long pendingKey, T pendingValue) {
        long[] jArr = this.keys;
        T[] tArr = this.values;
        allocateBuffers(HHPC.INSTANCE.nextBufferSize(this.mask + 1, getSize(), this.loadFactor));
        jArr[slot] = pendingKey;
        tArr[slot] = pendingValue;
        rehash(jArr, tArr);
    }

    private final int hashKey(long key) {
        return HHPC.INSTANCE.mixPhi(key);
    }

    private final void rehash(long[] fromKeys, T[] fromValues) {
        int i;
        long[] jArr = this.keys;
        T[] tArr = this.values;
        int i2 = this.mask;
        int length = fromKeys.length - 1;
        jArr[jArr.length - 1] = fromKeys[length];
        tArr[tArr.length - 1] = fromValues[length];
        while (true) {
            length--;
            if (length < 0) {
                return;
            }
            long j = fromKeys[length];
            if (j != 0) {
                int iHashKey = hashKey(j);
                while (true) {
                    i = iHashKey & i2;
                    if (jArr[i] == 0) {
                        break;
                    } else {
                        iHashKey = i + 1;
                    }
                }
                jArr[i] = j;
                tArr[i] = fromValues[length];
            }
        }
    }

    private final void shiftConflictingKeys(int gapSlotArg) {
        int i;
        long j;
        long[] jArr = this.keys;
        T[] tArr = this.values;
        int i2 = this.mask;
        while (true) {
            int i3 = 0;
            do {
                i3++;
                i = (gapSlotArg + i3) & i2;
                j = jArr[i];
                if (j == 0) {
                    jArr[gapSlotArg] = 0;
                    tArr[gapSlotArg] = null;
                    this.assigned--;
                    return;
                }
            } while (((i - hashKey(j)) & i2) < i3);
            jArr[gapSlotArg] = j;
            tArr[gapSlotArg] = tArr[i];
            gapSlotArg = i;
        }
    }

    private static void values$annotations() {
    }

    public final boolean containsKey(long key) {
        if (key == 0) {
            return this.hasEmptyKey;
        }
        long[] jArr = this.keys;
        int i = this.mask;
        int iHashKey = hashKey(key) & i;
        long j = jArr[iHashKey];
        while (j != 0) {
            if (j == key) {
                return true;
            }
            iHashKey = (iHashKey + 1) & i;
            j = jArr[iHashKey];
        }
        return false;
    }

    public final void ensureCapacity(int expectedElements) {
        if (expectedElements > this.resizeAt) {
            long[] jArr = this.keys;
            T[] tArr = this.values;
            allocateBuffers(HHPC.INSTANCE.minBufferSize(expectedElements, this.loadFactor));
            if (isEmpty()) {
                return;
            }
            rehash(jArr, tArr);
        }
    }

    public final Sequence<Pair<Long, T>> entrySequence() {
        final int i = this.mask + 1;
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        return SequencesKt.generateSequence(new Function0<Pair<? extends Long, ? extends T>>() {
            {
                super(0);
            }

            @Override
            public final Pair<Long, T> invoke() {
                if (intRef.element < i) {
                    intRef.element++;
                    while (intRef.element < i) {
                        long j = LongObjectScatterMap.this.keys[intRef.element];
                        if (j != 0) {
                            Long lValueOf = Long.valueOf(j);
                            Object obj = LongObjectScatterMap.this.values[intRef.element];
                            if (obj == null) {
                                Intrinsics.throwNpe();
                            }
                            return TuplesKt.to(lValueOf, obj);
                        }
                        intRef.element++;
                    }
                }
                if (intRef.element != i || !LongObjectScatterMap.this.hasEmptyKey) {
                    return null;
                }
                intRef.element++;
                Object obj2 = LongObjectScatterMap.this.values[i];
                if (obj2 == null) {
                    Intrinsics.throwNpe();
                }
                return TuplesKt.to(0L, obj2);
            }
        });
    }

    public final T get(long key) {
        if (key == 0) {
            if (this.hasEmptyKey) {
                return this.values[this.mask + 1];
            }
            return null;
        }
        long[] jArr = this.keys;
        int i = this.mask;
        int iHashKey = hashKey(key) & i;
        long j = jArr[iHashKey];
        while (j != 0) {
            if (j == key) {
                return this.values[iHashKey];
            }
            iHashKey = (iHashKey + 1) & i;
            j = jArr[iHashKey];
        }
        return null;
    }

    public final int getSize() {
        return this.assigned + (this.hasEmptyKey ? 1 : 0);
    }

    public final boolean isEmpty() {
        return getSize() == 0;
    }

    public final void release() {
        this.assigned = 0;
        this.hasEmptyKey = false;
        allocateBuffers(HHPC.INSTANCE.minBufferSize(4, this.loadFactor));
    }

    public final T remove(long key) {
        int i = this.mask;
        if (key == 0) {
            this.hasEmptyKey = false;
            T[] tArr = this.values;
            int i2 = i + 1;
            T t = tArr[i2];
            tArr[i2] = null;
            return t;
        }
        long[] jArr = this.keys;
        int iHashKey = hashKey(key) & i;
        long j = jArr[iHashKey];
        while (j != 0) {
            if (j == key) {
                T t2 = this.values[iHashKey];
                shiftConflictingKeys(iHashKey);
                return t2;
            }
            iHashKey = (iHashKey + 1) & i;
            j = jArr[iHashKey];
        }
        return null;
    }

    public final T set(long key, T value) {
        int i = this.mask;
        if (key == 0) {
            this.hasEmptyKey = true;
            T[] tArr = this.values;
            int i2 = i + 1;
            T t = tArr[i2];
            tArr[i2] = value;
            return t;
        }
        long[] jArr = this.keys;
        int iHashKey = hashKey(key) & i;
        long j = jArr[iHashKey];
        while (j != 0) {
            if (j == key) {
                T[] tArr2 = this.values;
                T t2 = tArr2[iHashKey];
                tArr2[iHashKey] = value;
                return t2;
            }
            iHashKey = (iHashKey + 1) & i;
            j = jArr[iHashKey];
        }
        if (this.assigned == this.resizeAt) {
            allocateThenInsertThenRehash(iHashKey, key, value);
        } else {
            jArr[iHashKey] = key;
            this.values[iHashKey] = value;
        }
        this.assigned++;
        return null;
    }
}
