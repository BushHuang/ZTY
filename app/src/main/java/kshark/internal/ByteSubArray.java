package kshark.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0005J\u0006\u0010\u0011\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkshark/internal/ByteSubArray;", "", "array", "", "rangeStart", "", "size", "longIdentifiers", "", "([BIIZ)V", "currentIndex", "endInclusive", "readByte", "", "readId", "", "readInt", "readLong", "readTruncatedLong", "byteCount", "shark"}, k = 1, mv = {1, 1, 15})
public final class ByteSubArray {
    private final byte[] array;
    private int currentIndex;
    private final int endInclusive;
    private final boolean longIdentifiers;
    private final int rangeStart;

    public ByteSubArray(byte[] bArr, int i, int i2, boolean z) {
        Intrinsics.checkParameterIsNotNull(bArr, "array");
        this.array = bArr;
        this.rangeStart = i;
        this.longIdentifiers = z;
        this.endInclusive = i2 - 1;
    }

    public final byte readByte() {
        int i = this.currentIndex;
        this.currentIndex = i + 1;
        if (i >= 0 && this.endInclusive >= i) {
            return this.array[this.rangeStart + i];
        }
        throw new IllegalArgumentException(("Index " + i + " should be between 0 and " + this.endInclusive).toString());
    }

    public final long readId() {
        return this.longIdentifiers ? readLong() : readInt();
    }

    public final int readInt() {
        int i = this.currentIndex;
        this.currentIndex = i + 4;
        if (i >= 0 && i <= this.endInclusive + (-3)) {
            return ByteSubArrayKt.readInt(this.array, this.rangeStart + i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Index ");
        sb.append(i);
        sb.append(" should be between 0 and ");
        sb.append(this.endInclusive - 3);
        throw new IllegalArgumentException(sb.toString().toString());
    }

    public final long readLong() {
        int i = this.currentIndex;
        this.currentIndex = i + 8;
        if (i >= 0 && i <= this.endInclusive + (-7)) {
            return ByteSubArrayKt.readLong(this.array, this.rangeStart + i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Index ");
        sb.append(i);
        sb.append(" should be between 0 and ");
        sb.append(this.endInclusive - 7);
        throw new IllegalArgumentException(sb.toString().toString());
    }

    public final long readTruncatedLong(int byteCount) {
        int i = this.currentIndex;
        this.currentIndex = i + byteCount;
        if (!(i >= 0 && i <= this.endInclusive - (byteCount + (-1)))) {
            throw new IllegalArgumentException(("Index " + i + " should be between 0 and " + (this.endInclusive - (byteCount - 1))).toString());
        }
        int i2 = this.rangeStart + i;
        byte[] bArr = this.array;
        long j = 0;
        int i3 = (byteCount - 1) * 8;
        while (i3 >= 8) {
            j |= (255 & bArr[i2]) << i3;
            i3 -= 8;
            i2++;
        }
        return (bArr[i2] & 255) | j;
    }
}
