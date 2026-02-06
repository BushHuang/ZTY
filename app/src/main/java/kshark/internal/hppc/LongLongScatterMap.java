package kshark.internal.hppc;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J \u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0019J\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0004J\u0018\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00190!0 J \u0010\"\u001a\u00020\u00142\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00140$J\u0011\u0010%\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019H\u0086\u0002J\u000e\u0010&\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0019J\u000e\u0010'\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u0004J\u0010\u0010(\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0019H\u0002J\u0018\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020\nH\u0002J\u0006\u0010,\u001a\u00020\u0014J\u000e\u0010-\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019J\u0019\u0010.\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u0019H\u0086\u0002J\u0010\u00100\u001a\u00020\u00142\u0006\u00101\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lkshark/internal/hppc/LongLongScatterMap;", "", "()V", "assigned", "", "hasEmptyKey", "", "isEmpty", "()Z", "keys", "", "loadFactor", "", "mask", "resizeAt", "size", "getSize", "()I", "values", "allocateBuffers", "", "arraySize", "allocateThenInsertThenRehash", "slot", "pendingKey", "", "pendingValue", "containsKey", "key", "ensureCapacity", "expectedElements", "entrySequence", "Lkotlin/sequences/Sequence;", "Lkotlin/Pair;", "forEach", "block", "Lkotlin/Function2;", "get", "getSlot", "getSlotValue", "hashKey", "rehash", "fromKeys", "fromValues", "release", "remove", "set", "value", "shiftConflictingKeys", "gapSlotArg", "shark"}, k = 1, mv = {1, 1, 15})
public final class LongLongScatterMap {
    private int assigned;
    private boolean hasEmptyKey;
    private int mask;
    private int resizeAt;
    private long[] keys = new long[0];
    private long[] values = new long[0];
    private double loadFactor = 0.75d;

    public LongLongScatterMap() {
        ensureCapacity(4);
    }

    private final void allocateBuffers(int arraySize) {
        long[] jArr = this.keys;
        long[] jArr2 = this.values;
        int i = arraySize + 1;
        try {
            this.keys = new long[i];
            this.values = new long[i];
            this.resizeAt = HHPC.INSTANCE.expandAtCount(arraySize, this.loadFactor);
            this.mask = arraySize - 1;
        } catch (OutOfMemoryError e) {
            this.keys = jArr;
            this.values = jArr2;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ROOT;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
            String str = String.format(locale, "Not enough memory to allocate buffers for rehashing: %,d -> %,d", Arrays.copyOf(new Object[]{Integer.valueOf(this.mask + 1), Integer.valueOf(arraySize)}, 2));
            Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(locale, format, *args)");
            throw new RuntimeException(str, e);
        }
    }

    private final void allocateThenInsertThenRehash(int slot, long pendingKey, long pendingValue) {
        long[] jArr = this.keys;
        long[] jArr2 = this.values;
        allocateBuffers(HHPC.INSTANCE.nextBufferSize(this.mask + 1, getSize(), this.loadFactor));
        jArr[slot] = pendingKey;
        jArr2[slot] = pendingValue;
        rehash(jArr, jArr2);
    }

    private final int hashKey(long key) {
        return HHPC.INSTANCE.mixPhi(key);
    }

    private final void rehash(long[] fromKeys, long[] fromValues) {
        int i;
        long[] jArr = this.keys;
        long[] jArr2 = this.values;
        int i2 = this.mask;
        int length = fromKeys.length - 1;
        jArr[jArr.length - 1] = fromKeys[length];
        jArr2[jArr2.length - 1] = fromValues[length];
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
                jArr2[i] = fromValues[length];
            }
        }
    }

    private final void shiftConflictingKeys(int gapSlotArg) {
        int i;
        long j;
        long[] jArr = this.keys;
        long[] jArr2 = this.values;
        int i2 = this.mask;
        while (true) {
            int i3 = 0;
            do {
                i3++;
                i = (gapSlotArg + i3) & i2;
                j = jArr[i];
                if (j == 0) {
                    jArr[gapSlotArg] = 0;
                    jArr2[gapSlotArg] = 0;
                    this.assigned--;
                    return;
                }
            } while (((i - hashKey(j)) & i2) < i3);
            jArr[gapSlotArg] = j;
            jArr2[gapSlotArg] = jArr2[i];
            gapSlotArg = i;
        }
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
            long[] jArr2 = this.values;
            allocateBuffers(HHPC.INSTANCE.minBufferSize(expectedElements, this.loadFactor));
            if (isEmpty()) {
                return;
            }
            rehash(jArr, jArr2);
        }
    }

    public final Sequence<Pair<Long, Long>> entrySequence() {
        final int i = this.mask + 1;
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        return SequencesKt.generateSequence(new Function0<Pair<? extends Long, ? extends Long>>() {
            {
                super(0);
            }

            @Override
            public final Pair<? extends Long, ? extends Long> invoke() {
                if (intRef.element < i) {
                    intRef.element++;
                    while (intRef.element < i) {
                        long j = LongLongScatterMap.this.keys[intRef.element];
                        if (j != 0) {
                            return TuplesKt.to(Long.valueOf(j), Long.valueOf(LongLongScatterMap.this.values[intRef.element]));
                        }
                        intRef.element++;
                    }
                }
                if (intRef.element != i || !LongLongScatterMap.this.hasEmptyKey) {
                    return null;
                }
                intRef.element++;
                return TuplesKt.to(0L, Long.valueOf(LongLongScatterMap.this.values[i]));
            }
        });
    }

    public final void forEach(Function2<? super Long, ? super Long, Unit> block) {
        long j;
        Intrinsics.checkParameterIsNotNull(block, "block");
        int i = this.mask + 1;
        int i2 = -1;
        while (true) {
            if (i2 < i) {
                do {
                    i2++;
                    if (i2 < i) {
                        j = this.keys[i2];
                    }
                } while (j == 0);
                block.invoke(Long.valueOf(j), Long.valueOf(this.values[i2]));
            }
            if (i2 != i || !this.hasEmptyKey) {
                return;
            }
            i2++;
            block.invoke(0L, Long.valueOf(this.values[i]));
        }
    }

    public final long get(long key) {
        int slot = getSlot(key);
        if (slot != -1) {
            return getSlotValue(slot);
        }
        throw new IllegalArgumentException(("Unknown key " + key).toString());
    }

    public final int getSize() {
        return this.assigned + (this.hasEmptyKey ? 1 : 0);
    }

    public final int getSlot(long key) {
        if (key == 0) {
            if (this.hasEmptyKey) {
                return this.mask + 1;
            }
            return -1;
        }
        long[] jArr = this.keys;
        int i = this.mask;
        int iHashKey = hashKey(key) & i;
        long j = jArr[iHashKey];
        while (j != 0) {
            if (j == key) {
                return iHashKey;
            }
            iHashKey = (iHashKey + 1) & i;
            j = jArr[iHashKey];
        }
        return -1;
    }

    public final long getSlotValue(int slot) {
        return this.values[slot];
    }

    public final boolean isEmpty() {
        return getSize() == 0;
    }

    public final void release() {
        this.assigned = 0;
        this.hasEmptyKey = false;
        allocateBuffers(HHPC.INSTANCE.minBufferSize(4, this.loadFactor));
    }

    public final long remove(long key) {
        int i = this.mask;
        if (key == 0) {
            this.hasEmptyKey = false;
            long[] jArr = this.values;
            int i2 = i + 1;
            long j = jArr[i2];
            jArr[i2] = 0;
            return j;
        }
        long[] jArr2 = this.keys;
        int iHashKey = hashKey(key) & i;
        long j2 = jArr2[iHashKey];
        while (j2 != 0) {
            if (j2 == key) {
                long j3 = this.values[iHashKey];
                shiftConflictingKeys(iHashKey);
                return j3;
            }
            iHashKey = (iHashKey + 1) & i;
            j2 = jArr2[iHashKey];
        }
        return 0L;
    }

    public final long set(long key, long value) {
        int i = this.mask;
        if (key == 0) {
            this.hasEmptyKey = true;
            long[] jArr = this.values;
            int i2 = i + 1;
            long j = jArr[i2];
            jArr[i2] = value;
            return j;
        }
        long[] jArr2 = this.keys;
        int iHashKey = hashKey(key) & i;
        long j2 = jArr2[iHashKey];
        while (j2 != 0) {
            if (j2 == key) {
                long[] jArr3 = this.values;
                long j3 = jArr3[iHashKey];
                jArr3[iHashKey] = value;
                return j3;
            }
            iHashKey = (iHashKey + 1) & i;
            j2 = jArr2[iHashKey];
        }
        if (this.assigned == this.resizeAt) {
            allocateThenInsertThenRehash(iHashKey, key, value);
        } else {
            jArr2[iHashKey] = key;
            this.values[iHashKey] = value;
        }
        this.assigned++;
        return 0L;
    }
}
