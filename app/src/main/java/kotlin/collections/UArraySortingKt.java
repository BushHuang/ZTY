package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class UArraySortingKt {
    private static final int m675partitionnroSd4(long[] jArr, int i, int i2) {
        long jM459getsVKNKU = ULongArray.m459getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.ulongCompare(ULongArray.m459getsVKNKU(jArr, i), jM459getsVKNKU) < 0) {
                i++;
            }
            while (UnsignedKt.ulongCompare(ULongArray.m459getsVKNKU(jArr, i2), jM459getsVKNKU) > 0) {
                i2--;
            }
            if (i <= i2) {
                long jM459getsVKNKU2 = ULongArray.m459getsVKNKU(jArr, i);
                ULongArray.m464setk8EXiF4(jArr, i, ULongArray.m459getsVKNKU(jArr, i2));
                ULongArray.m464setk8EXiF4(jArr, i2, jM459getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    private static final int m676partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte bM303getw2LRezQ = UByteArray.m303getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = bM303getw2LRezQ & 255;
                if (Intrinsics.compare(UByteArray.m303getw2LRezQ(bArr, i) & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m303getw2LRezQ(bArr, i2) & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte bM303getw2LRezQ2 = UByteArray.m303getw2LRezQ(bArr, i);
                UByteArray.m308setVurrAj0(bArr, i, UByteArray.m303getw2LRezQ(bArr, i2));
                UByteArray.m308setVurrAj0(bArr, i2, bM303getw2LRezQ2);
                i++;
                i2--;
            }
        }
        return i;
    }

    private static final int m677partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short sM563getMh2AYeg = UShortArray.m563getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = sM563getMh2AYeg & 65535;
                if (Intrinsics.compare(UShortArray.m563getMh2AYeg(sArr, i) & 65535, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m563getMh2AYeg(sArr, i2) & 65535, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short sM563getMh2AYeg2 = UShortArray.m563getMh2AYeg(sArr, i);
                UShortArray.m568set01HTLdE(sArr, i, UShortArray.m563getMh2AYeg(sArr, i2));
                UShortArray.m568set01HTLdE(sArr, i2, sM563getMh2AYeg2);
                i++;
                i2--;
            }
        }
        return i;
    }

    private static final int m678partitionoBK06Vg(int[] iArr, int i, int i2) {
        int iM381getpVg5ArA = UIntArray.m381getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.uintCompare(UIntArray.m381getpVg5ArA(iArr, i), iM381getpVg5ArA) < 0) {
                i++;
            }
            while (UnsignedKt.uintCompare(UIntArray.m381getpVg5ArA(iArr, i2), iM381getpVg5ArA) > 0) {
                i2--;
            }
            if (i <= i2) {
                int iM381getpVg5ArA2 = UIntArray.m381getpVg5ArA(iArr, i);
                UIntArray.m386setVXSXFK8(iArr, i, UIntArray.m381getpVg5ArA(iArr, i2));
                UIntArray.m386setVXSXFK8(iArr, i2, iM381getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    private static final void m679quickSortnroSd4(long[] jArr, int i, int i2) {
        int iM675partitionnroSd4 = m675partitionnroSd4(jArr, i, i2);
        int i3 = iM675partitionnroSd4 - 1;
        if (i < i3) {
            m679quickSortnroSd4(jArr, i, i3);
        }
        if (iM675partitionnroSd4 < i2) {
            m679quickSortnroSd4(jArr, iM675partitionnroSd4, i2);
        }
    }

    private static final void m680quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int iM676partition4UcCI2c = m676partition4UcCI2c(bArr, i, i2);
        int i3 = iM676partition4UcCI2c - 1;
        if (i < i3) {
            m680quickSort4UcCI2c(bArr, i, i3);
        }
        if (iM676partition4UcCI2c < i2) {
            m680quickSort4UcCI2c(bArr, iM676partition4UcCI2c, i2);
        }
    }

    private static final void m681quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int iM677partitionAa5vz7o = m677partitionAa5vz7o(sArr, i, i2);
        int i3 = iM677partitionAa5vz7o - 1;
        if (i < i3) {
            m681quickSortAa5vz7o(sArr, i, i3);
        }
        if (iM677partitionAa5vz7o < i2) {
            m681quickSortAa5vz7o(sArr, iM677partitionAa5vz7o, i2);
        }
    }

    private static final void m682quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int iM678partitionoBK06Vg = m678partitionoBK06Vg(iArr, i, i2);
        int i3 = iM678partitionoBK06Vg - 1;
        if (i < i3) {
            m682quickSortoBK06Vg(iArr, i, i3);
        }
        if (iM678partitionoBK06Vg < i2) {
            m682quickSortoBK06Vg(iArr, iM678partitionoBK06Vg, i2);
        }
    }

    public static final void m683sortArraynroSd4(long[] jArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(jArr, "array");
        m679quickSortnroSd4(jArr, i, i2 - 1);
    }

    public static final void m684sortArray4UcCI2c(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "array");
        m680quickSort4UcCI2c(bArr, i, i2 - 1);
    }

    public static final void m685sortArrayAa5vz7o(short[] sArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(sArr, "array");
        m681quickSortAa5vz7o(sArr, i, i2 - 1);
    }

    public static final void m686sortArrayoBK06Vg(int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(iArr, "array");
        m682quickSortoBK06Vg(iArr, i, i2 - 1);
    }
}
