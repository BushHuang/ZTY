package kshark.internal;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0011\u0010\u000f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0002J\u0018\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00130\u00120\u0011J\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0002J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0005H\u0002R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkshark/internal/SortedBytesMap;", "", "longIdentifiers", "", "bytesPerValue", "", "sortedEntries", "", "(ZI[B)V", "bytesPerEntry", "bytesPerKey", "size", "binarySearch", "key", "", "contains", "entrySequence", "Lkotlin/sequences/Sequence;", "Lkotlin/Pair;", "Lkshark/internal/ByteSubArray;", "get", "keyAt", "index", "shark"}, k = 1, mv = {1, 1, 15})
public final class SortedBytesMap {
    private final int bytesPerEntry;
    private final int bytesPerKey;
    private final int bytesPerValue;
    private final boolean longIdentifiers;
    private final int size;
    private final byte[] sortedEntries;

    public SortedBytesMap(boolean z, int i, byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "sortedEntries");
        this.longIdentifiers = z;
        this.bytesPerValue = i;
        this.sortedEntries = bArr;
        int i2 = z ? 8 : 4;
        this.bytesPerKey = i2;
        int i3 = i2 + this.bytesPerValue;
        this.bytesPerEntry = i3;
        this.size = this.sortedEntries.length / i3;
    }

    private final int binarySearch(long key) {
        int i = this.size - 1;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            long jKeyAt = keyAt(i3);
            if (jKeyAt < key) {
                i2 = i3 + 1;
            } else {
                if (jKeyAt <= key) {
                    return i3;
                }
                i = i3 - 1;
            }
        }
        return i2 ^ (-1);
    }

    private final long keyAt(int index) {
        return this.longIdentifiers ? ByteSubArrayKt.readLong(this.sortedEntries, index * this.bytesPerEntry) : ByteSubArrayKt.readInt(this.sortedEntries, r3);
    }

    public final boolean contains(long key) {
        return binarySearch(key) >= 0;
    }

    public final Sequence<Pair<Long, ByteSubArray>> entrySequence() {
        return SequencesKt.map(CollectionsKt.asSequence(RangesKt.until(0, this.size)), new Function1<Integer, Pair<? extends Long, ? extends ByteSubArray>>() {
            {
                super(1);
            }

            @Override
            public Pair<? extends Long, ? extends ByteSubArray> invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Pair<Long, ByteSubArray> invoke(int i) {
                return TuplesKt.to(Long.valueOf(SortedBytesMap.this.keyAt(i)), new ByteSubArray(SortedBytesMap.this.sortedEntries, (SortedBytesMap.this.bytesPerEntry * i) + SortedBytesMap.this.bytesPerKey, SortedBytesMap.this.bytesPerValue, SortedBytesMap.this.longIdentifiers));
            }
        });
    }

    public final ByteSubArray get(long key) {
        int iBinarySearch = binarySearch(key);
        if (iBinarySearch < 0) {
            return null;
        }
        return new ByteSubArray(this.sortedEntries, (iBinarySearch * this.bytesPerEntry) + this.bytesPerKey, this.bytesPerValue, this.longIdentifiers);
    }
}
