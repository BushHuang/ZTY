package kshark.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kshark.internal.aosp.ByteArrayComparator;
import kshark.internal.aosp.ByteArrayTimSort;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001!B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0012\u0010\u0012\u001a\u00060\u0010R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0002J\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0003H\u0002J\u0018\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0003H\u0002J\u0015\u0010\u001e\u001a\u00020\u0003*\u00020\u001f2\u0006\u0010 \u001a\u00020\u0003H\u0082\fJ\u0015\u0010\u001e\u001a\u00020\u0014*\u00020\u001f2\u0006\u0010 \u001a\u00020\u0014H\u0082\fR\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lkshark/internal/UnsortedByteEntries;", "", "bytesPerValue", "", "longIdentifiers", "", "initialCapacity", "growthFactor", "", "(IZID)V", "assigned", "bytesPerEntry", "currentCapacity", "entries", "", "subArray", "Lkshark/internal/UnsortedByteEntries$MutableByteSubArray;", "subArrayIndex", "append", "key", "", "growEntries", "", "newCapacity", "moveToSortedMap", "Lkshark/internal/SortedBytesMap;", "readInt", "array", "index", "readLong", "and", "", "other", "MutableByteSubArray", "shark"}, k = 1, mv = {1, 1, 15})
public final class UnsortedByteEntries {
    private int assigned;
    private final int bytesPerEntry;
    private final int bytesPerValue;
    private int currentCapacity;
    private byte[] entries;
    private final double growthFactor;
    private final int initialCapacity;
    private final boolean longIdentifiers;
    private final MutableByteSubArray subArray;
    private int subArrayIndex;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bJ\u0016\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n¨\u0006\u000e"}, d2 = {"Lkshark/internal/UnsortedByteEntries$MutableByteSubArray;", "", "(Lkshark/internal/UnsortedByteEntries;)V", "writeByte", "", "value", "", "writeId", "", "writeInt", "", "writeLong", "writeTruncatedLong", "byteCount", "shark"}, k = 1, mv = {1, 1, 15})
    public final class MutableByteSubArray {
        public MutableByteSubArray() {
        }

        public final void writeByte(byte value) {
            int i = UnsortedByteEntries.this.subArrayIndex;
            UnsortedByteEntries.this.subArrayIndex++;
            if (i >= 0 && UnsortedByteEntries.this.bytesPerEntry >= i) {
                int i2 = ((UnsortedByteEntries.this.assigned - 1) * UnsortedByteEntries.this.bytesPerEntry) + i;
                byte[] bArr = UnsortedByteEntries.this.entries;
                if (bArr == null) {
                    Intrinsics.throwNpe();
                }
                bArr[i2] = value;
                return;
            }
            throw new IllegalArgumentException(("Index " + i + " should be between 0 and " + UnsortedByteEntries.this.bytesPerEntry).toString());
        }

        public final void writeId(long value) {
            if (UnsortedByteEntries.this.longIdentifiers) {
                writeLong(value);
            } else {
                writeInt((int) value);
            }
        }

        public final void writeInt(int value) {
            int i = UnsortedByteEntries.this.subArrayIndex;
            UnsortedByteEntries.this.subArrayIndex += 4;
            if (!(i >= 0 && i <= UnsortedByteEntries.this.bytesPerEntry + (-4))) {
                StringBuilder sb = new StringBuilder();
                sb.append("Index ");
                sb.append(i);
                sb.append(" should be between 0 and ");
                sb.append(UnsortedByteEntries.this.bytesPerEntry - 4);
                throw new IllegalArgumentException(sb.toString().toString());
            }
            int i2 = ((UnsortedByteEntries.this.assigned - 1) * UnsortedByteEntries.this.bytesPerEntry) + i;
            byte[] bArr = UnsortedByteEntries.this.entries;
            if (bArr == null) {
                Intrinsics.throwNpe();
            }
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((value >>> 24) & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((value >>> 16) & 255);
            bArr[i4] = (byte) ((value >>> 8) & 255);
            bArr[i4 + 1] = (byte) (value & 255);
        }

        public final void writeLong(long value) {
            int i = UnsortedByteEntries.this.subArrayIndex;
            UnsortedByteEntries.this.subArrayIndex += 8;
            if (!(i >= 0 && i <= UnsortedByteEntries.this.bytesPerEntry - 8)) {
                throw new IllegalArgumentException(("Index " + i + " should be between 0 and " + (UnsortedByteEntries.this.bytesPerEntry - 8)).toString());
            }
            int i2 = ((UnsortedByteEntries.this.assigned - 1) * UnsortedByteEntries.this.bytesPerEntry) + i;
            byte[] bArr = UnsortedByteEntries.this.entries;
            if (bArr == null) {
                Intrinsics.throwNpe();
            }
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((value >>> 56) & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((value >>> 48) & 255);
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((value >>> 40) & 255);
            int i6 = i5 + 1;
            bArr[i5] = (byte) ((value >>> 32) & 255);
            int i7 = i6 + 1;
            bArr[i6] = (byte) ((value >>> 24) & 255);
            int i8 = i7 + 1;
            bArr[i7] = (byte) ((value >>> 16) & 255);
            bArr[i8] = (byte) ((value >>> 8) & 255);
            bArr[i8 + 1] = (byte) (value & 255);
        }

        public final void writeTruncatedLong(long value, int byteCount) {
            int i = UnsortedByteEntries.this.subArrayIndex;
            UnsortedByteEntries.this.subArrayIndex += byteCount;
            if (!(i >= 0 && i <= UnsortedByteEntries.this.bytesPerEntry - byteCount)) {
                throw new IllegalArgumentException(("Index " + i + " should be between 0 and " + (UnsortedByteEntries.this.bytesPerEntry - byteCount)).toString());
            }
            int i2 = ((UnsortedByteEntries.this.assigned - 1) * UnsortedByteEntries.this.bytesPerEntry) + i;
            byte[] bArr = UnsortedByteEntries.this.entries;
            if (bArr == null) {
                Intrinsics.throwNpe();
            }
            int i3 = (byteCount - 1) * 8;
            while (i3 >= 8) {
                bArr[i2] = (byte) (255 & (value >>> i3));
                i3 -= 8;
                i2++;
            }
            bArr[i2] = (byte) (value & 255);
        }
    }

    public UnsortedByteEntries(int i, boolean z, int i2, double d) {
        this.bytesPerValue = i;
        this.longIdentifiers = z;
        this.initialCapacity = i2;
        this.growthFactor = d;
        this.bytesPerEntry = i + (z ? 8 : 4);
        this.subArray = new MutableByteSubArray();
    }

    public UnsortedByteEntries(int i, boolean z, int i2, double d, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, z, (i3 & 4) != 0 ? 4 : i2, (i3 & 8) != 0 ? 2.0d : d);
    }

    private final int and(byte b, int i) {
        return b & i;
    }

    private final long and(byte b, long j) {
        return j & b;
    }

    private final void growEntries(int newCapacity) {
        int i = this.bytesPerEntry;
        byte[] bArr = new byte[newCapacity * i];
        System.arraycopy(this.entries, 0, bArr, 0, this.assigned * i);
        this.entries = bArr;
    }

    private final int readInt(byte[] array, int index) {
        int i = index + 1;
        int i2 = i + 1;
        int i3 = ((array[index] & 255) << 24) | ((array[i] & 255) << 16);
        int i4 = i2 + 1;
        return (array[i4] & 255) | i3 | ((array[i2] & 255) << 8);
    }

    private final long readLong(byte[] array, int index) {
        long j = (array[index] & 255) << 56;
        long j2 = j | ((array[r0] & 255) << 48);
        long j3 = j2 | ((array[r9] & 255) << 40);
        long j4 = j3 | ((array[r0] & 255) << 32);
        long j5 = j4 | ((array[r9] & 255) << 24);
        long j6 = j5 | ((array[r0] & 255) << 16);
        int i = index + 1 + 1 + 1 + 1 + 1 + 1 + 1;
        return (array[i] & 255) | j6 | ((array[r9] & 255) << 8);
    }

    public final MutableByteSubArray append(long key) {
        if (this.entries == null) {
            int i = this.initialCapacity;
            this.currentCapacity = i;
            this.entries = new byte[i * this.bytesPerEntry];
        } else {
            int i2 = this.currentCapacity;
            if (i2 == this.assigned) {
                double d = i2;
                double d2 = this.growthFactor;
                Double.isNaN(d);
                int i3 = (int) (d * d2);
                growEntries(i3);
                this.currentCapacity = i3;
            }
        }
        this.assigned++;
        this.subArrayIndex = 0;
        this.subArray.writeId(key);
        return this.subArray;
    }

    public final SortedBytesMap moveToSortedMap() {
        if (this.assigned == 0) {
            return new SortedBytesMap(this.longIdentifiers, this.bytesPerValue, new byte[0]);
        }
        byte[] bArrCopyOf = this.entries;
        if (bArrCopyOf == null) {
            Intrinsics.throwNpe();
        }
        ByteArrayTimSort.INSTANCE.sort(bArrCopyOf, 0, this.assigned, this.bytesPerEntry, new ByteArrayComparator() {
            @Override
            public int compare(int entrySize, byte[] o1Array, int o1Index, byte[] o2Array, int o2Index) {
                Intrinsics.checkParameterIsNotNull(o1Array, "o1Array");
                Intrinsics.checkParameterIsNotNull(o2Array, "o2Array");
                return UnsortedByteEntries.this.longIdentifiers ? (UnsortedByteEntries.this.readLong(o1Array, o1Index * entrySize) > UnsortedByteEntries.this.readLong(o2Array, o2Index * entrySize) ? 1 : (UnsortedByteEntries.this.readLong(o1Array, o1Index * entrySize) == UnsortedByteEntries.this.readLong(o2Array, o2Index * entrySize) ? 0 : -1)) : Intrinsics.compare(UnsortedByteEntries.this.readInt(o1Array, o1Index * entrySize), UnsortedByteEntries.this.readInt(o2Array, o2Index * entrySize));
            }
        });
        int length = bArrCopyOf.length;
        int i = this.assigned;
        int i2 = this.bytesPerEntry;
        if (length > i * i2) {
            bArrCopyOf = Arrays.copyOf(bArrCopyOf, i * i2);
            Intrinsics.checkExpressionValueIsNotNull(bArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        }
        this.entries = (byte[]) null;
        this.assigned = 0;
        return new SortedBytesMap(this.longIdentifiers, this.bytesPerValue, bArrCopyOf);
    }
}
