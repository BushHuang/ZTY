package kshark.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\n\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0082\f\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\f\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\b\u001a\u00020\u0004*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\t\u001a\u00020\n*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u0000¨\u0006\u000b"}, d2 = {"and", "", "", "other", "", "readInt", "", "index", "readLong", "readShort", "", "shark"}, k = 2, mv = {1, 1, 15})
public final class ByteSubArrayKt {
    private static final int and(byte b, int i) {
        return b & i;
    }

    private static final long and(byte b, long j) {
        return j & b;
    }

    public static final int readInt(byte[] bArr, int i) {
        Intrinsics.checkParameterIsNotNull(bArr, "$this$readInt");
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = ((bArr[i] & 255) << 24) | ((bArr[i2] & 255) << 16);
        int i5 = i3 + 1;
        return (bArr[i5] & 255) | i4 | ((bArr[i3] & 255) << 8);
    }

    public static final long readLong(byte[] bArr, int i) {
        Intrinsics.checkParameterIsNotNull(bArr, "$this$readLong");
        long j = (bArr[i] & 255) << 56;
        long j2 = j | ((bArr[r0] & 255) << 48);
        long j3 = j2 | ((bArr[r8] & 255) << 40);
        long j4 = j3 | ((bArr[r0] & 255) << 32);
        long j5 = j4 | ((bArr[r8] & 255) << 24);
        long j6 = j5 | ((bArr[r0] & 255) << 16);
        int i2 = i + 1 + 1 + 1 + 1 + 1 + 1 + 1;
        return (bArr[i2] & 255) | j6 | ((bArr[r8] & 255) << 8);
    }

    public static final short readShort(byte[] bArr, int i) {
        Intrinsics.checkParameterIsNotNull(bArr, "$this$readShort");
        int i2 = i + 1;
        return (short) ((bArr[i2] & 255) | ((bArr[i] & 255) << 8));
    }
}
