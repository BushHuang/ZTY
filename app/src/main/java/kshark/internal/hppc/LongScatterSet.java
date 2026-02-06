package kshark.internal.hppc;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004H\u0002J\u0018\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u000fH\u0002J\u0011\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0086\u0002J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0004J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0011\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0086\u0002J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\bH\u0002J\u0006\u0010\u001d\u001a\u00020\u0011J\u0006\u0010\u001e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lkshark/internal/hppc/LongScatterSet;", "", "()V", "assigned", "", "hasEmptyKey", "", "keys", "", "loadFactor", "", "mask", "resizeAt", "add", "key", "", "allocateBuffers", "", "arraySize", "allocateThenInsertThenRehash", "slot", "pendingKey", "contains", "ensureCapacity", "expectedElements", "hashKey", "plusAssign", "rehash", "fromKeys", "release", "size", "shark"}, k = 1, mv = {1, 1, 15})
public final class LongScatterSet {
    private int assigned;
    private boolean hasEmptyKey;
    private long[] keys = new long[0];
    private final double loadFactor = 0.75d;
    private int mask;
    private int resizeAt;

    public LongScatterSet() {
        ensureCapacity(4);
    }

    private final void allocateBuffers(int arraySize) {
        long[] jArr = this.keys;
        try {
            this.keys = new long[arraySize + 1];
            this.resizeAt = HHPC.INSTANCE.expandAtCount(arraySize, this.loadFactor);
            this.mask = arraySize - 1;
        } catch (OutOfMemoryError e) {
            this.keys = jArr;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ROOT;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
            String str = String.format(locale, "Not enough memory to allocate buffers for rehashing: %,d -> %,d", Arrays.copyOf(new Object[]{Integer.valueOf(size()), Integer.valueOf(arraySize)}, 2));
            Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(locale, format, *args)");
            throw new RuntimeException(str, e);
        }
    }

    private final void allocateThenInsertThenRehash(int slot, long pendingKey) {
        long[] jArr = this.keys;
        allocateBuffers(HHPC.INSTANCE.nextBufferSize(this.mask + 1, size(), this.loadFactor));
        jArr[slot] = pendingKey;
        rehash(jArr);
    }

    private final int hashKey(long key) {
        return HHPC.INSTANCE.mixPhi(key);
    }

    private final void rehash(long[] fromKeys) {
        int i;
        long[] jArr = this.keys;
        int i2 = this.mask;
        int length = fromKeys.length - 1;
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
            }
        }
    }

    public final boolean add(long key) {
        if (key == 0) {
            boolean z = !this.hasEmptyKey;
            this.hasEmptyKey = true;
            return z;
        }
        long[] jArr = this.keys;
        int i = this.mask;
        int iHashKey = hashKey(key) & i;
        long j = jArr[iHashKey];
        while (j != 0) {
            if (j == key) {
                return false;
            }
            iHashKey = (iHashKey + 1) & i;
            j = jArr[iHashKey];
        }
        if (this.assigned == this.resizeAt) {
            allocateThenInsertThenRehash(iHashKey, key);
        } else {
            jArr[iHashKey] = key;
        }
        this.assigned++;
        return true;
    }

    public final boolean contains(long key) {
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
            allocateBuffers(HHPC.INSTANCE.minBufferSize(expectedElements, this.loadFactor));
            if (size() != 0) {
                rehash(jArr);
            }
        }
    }

    public final void plusAssign(long key) {
        add(key);
    }

    public final void release() {
        this.assigned = 0;
        this.hasEmptyKey = false;
        allocateBuffers(HHPC.INSTANCE.minBufferSize(4, this.loadFactor));
    }

    public final int size() {
        return this.assigned + (this.hasEmptyKey ? 1 : 0);
    }
}
