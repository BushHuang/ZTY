package kshark.internal.aosp;

import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\f\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007H\u0002J\b\u0010\u0014\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0002J(\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H\u0002J(\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H\u0002J\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lkshark/internal/aosp/ByteArrayTimSort;", "", "a", "", "c", "Lkshark/internal/aosp/ByteArrayComparator;", "entrySize", "", "([BLkshark/internal/aosp/ByteArrayComparator;I)V", "minGallop", "runBase", "", "runLen", "stackSize", "tmp", "ensureCapacity", "minCapacity", "mergeAt", "", "i", "mergeCollapse", "mergeForceCollapse", "mergeHi", "base1", "len1", "base2", "len2", "mergeLo", "pushRun", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class ByteArrayTimSort {
    private static final boolean DEBUG = false;
    private final byte[] a;
    private final ByteArrayComparator c;
    private final int entrySize;
    private int minGallop;
    private final int[] runBase;
    private final int[] runLen;
    private int stackSize;
    private byte[] tmp;

    public static final Companion INSTANCE = new Companion(null);
    private static final int MIN_MERGE = 32;
    private static final int MIN_GALLOP = 7;
    private static final int INITIAL_TMP_STORAGE_LENGTH = 256;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006H\u0002J0\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0002JH\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0002JH\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J(\u0010\u001f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J.\u0010 \u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012J\u001e\u0010 \u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lkshark/internal/aosp/ByteArrayTimSort$Companion;", "", "()V", "DEBUG", "", "INITIAL_TMP_STORAGE_LENGTH", "", "MIN_GALLOP", "MIN_MERGE", "binarySort", "", "a", "", "lo", "hi", "start", "entrySize", "c", "Lkshark/internal/aosp/ByteArrayComparator;", "checkStartAndEnd", "len", "end", "countRunAndMakeAscending", "gallopLeft", "keyArray", "keyIndex", "base", "hint", "gallopRight", "minRunLength", "n", "reverseRange", "sort", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final void binarySort(byte[] a2, int lo, int hi, int start, int entrySize, ByteArrayComparator c) {
            int i = start;
            if (ByteArrayTimSort.DEBUG) {
                boolean z = lo <= i && i <= hi;
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Assertion failed");
                }
            }
            if (i == lo) {
                i++;
            }
            byte[] bArr = new byte[entrySize];
            int i2 = i;
            while (i2 < hi) {
                int i3 = i2 * entrySize;
                for (int i4 = 0; i4 < entrySize; i4++) {
                    bArr[i4] = a2[i3 + i4];
                }
                if (ByteArrayTimSort.DEBUG) {
                    boolean z2 = lo <= i2;
                    if (_Assertions.ENABLED && !z2) {
                        throw new AssertionError("Assertion failed");
                    }
                }
                int i5 = lo;
                int i6 = i2;
                while (i5 < i6) {
                    int i7 = (i5 + i6) >>> 1;
                    int i8 = i6;
                    if (c.compare(entrySize, bArr, 0, a2, i7) < 0) {
                        i6 = i7;
                    } else {
                        i5 = i7 + 1;
                        i6 = i8;
                    }
                }
                int i9 = i6;
                if (ByteArrayTimSort.DEBUG) {
                    boolean z3 = i5 == i9;
                    if (_Assertions.ENABLED && !z3) {
                        throw new AssertionError("Assertion failed");
                    }
                }
                int i10 = i2 - i5;
                if (i10 == 1) {
                    int i11 = i5 * entrySize;
                    int i12 = (i5 + 1) * entrySize;
                    for (int i13 = 0; i13 < entrySize; i13++) {
                        a2[i12 + i13] = a2[i11 + i13];
                    }
                } else if (i10 != 2) {
                    System.arraycopy(a2, i5 * entrySize, a2, (i5 + 1) * entrySize, i10 * entrySize);
                } else {
                    int i14 = i5 * entrySize;
                    int i15 = (i5 + 1) * entrySize;
                    int i16 = (i5 + 2) * entrySize;
                    for (int i17 = 0; i17 < entrySize; i17++) {
                        a2[i16 + i17] = a2[i15 + i17];
                    }
                    for (int i18 = 0; i18 < entrySize; i18++) {
                        a2[i15 + i18] = a2[i14 + i18];
                    }
                }
                int i19 = i5 * entrySize;
                for (int i20 = 0; i20 < entrySize; i20++) {
                    a2[i19 + i20] = bArr[i20];
                }
                i2++;
            }
        }

        private final void checkStartAndEnd(int len, int start, int end) {
            if (start < 0 || end > len) {
                throw new ArrayIndexOutOfBoundsException("start < 0 || end > len. start=" + start + ", end=" + end + ", len=" + len);
            }
            if (start <= end) {
                return;
            }
            throw new IllegalArgumentException("start > end: " + start + " > " + end);
        }

        private final int countRunAndMakeAscending(byte[] a2, int lo, int hi, int entrySize, ByteArrayComparator c) {
            if (ByteArrayTimSort.DEBUG) {
                boolean z = lo < hi;
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Assertion failed");
                }
            }
            int i = lo + 1;
            if (i == hi) {
                return 1;
            }
            int iCompare = c.compare(entrySize, a2, i, a2, lo);
            int i2 = i + 1;
            if (iCompare >= 0) {
                while (i2 < hi) {
                    if (c.compare(entrySize, a2, i2, a2, i2 - 1) < 0) {
                        break;
                    }
                    i2++;
                }
            } else {
                while (i2 < hi) {
                    if (c.compare(entrySize, a2, i2, a2, i2 - 1) >= 0) {
                        break;
                    }
                    i2++;
                }
                reverseRange(a2, lo, i2, entrySize);
            }
            return i2 - lo;
        }

        private final int gallopLeft(byte[] keyArray, int keyIndex, byte[] a2, int base, int len, int hint, int entrySize, ByteArrayComparator c) {
            int i;
            int i2;
            if (ByteArrayTimSort.DEBUG) {
                boolean z = len > 0 && hint >= 0 && hint < len;
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Assertion failed");
                }
            }
            int i3 = base + hint;
            if (c.compare(entrySize, keyArray, keyIndex, a2, i3) > 0) {
                int i4 = len - hint;
                int i5 = 1;
                int i6 = 0;
                while (i5 < i4 && c.compare(entrySize, keyArray, keyIndex, a2, i3 + i5) > 0) {
                    int i7 = (i5 * 2) + 1;
                    if (i7 <= 0) {
                        i7 = i4;
                    }
                    int i8 = i7;
                    i6 = i5;
                    i5 = i8;
                }
                if (i5 <= i4) {
                    i4 = i5;
                }
                i2 = i6 + hint;
                i = i4 + hint;
            } else {
                int i9 = hint + 1;
                int i10 = 1;
                int i11 = 0;
                while (i10 < i9 && c.compare(entrySize, keyArray, keyIndex, a2, i3 - i10) <= 0) {
                    int i12 = (i10 * 2) + 1;
                    if (i12 <= 0) {
                        i12 = i9;
                    }
                    int i13 = i12;
                    i11 = i10;
                    i10 = i13;
                }
                if (i10 <= i9) {
                    i9 = i10;
                }
                int i14 = hint - i9;
                i = hint - i11;
                i2 = i14;
            }
            if (ByteArrayTimSort.DEBUG) {
                boolean z2 = -1 <= i2 && i2 < i && i <= len;
                if (_Assertions.ENABLED && !z2) {
                    throw new AssertionError("Assertion failed");
                }
            }
            int i15 = i2 + 1;
            while (i15 < i) {
                int i16 = ((i - i15) >>> 1) + i15;
                if (c.compare(entrySize, keyArray, keyIndex, a2, base + i16) > 0) {
                    i15 = i16 + 1;
                } else {
                    i = i16;
                }
            }
            if (ByteArrayTimSort.DEBUG) {
                boolean z3 = i15 == i;
                if (_Assertions.ENABLED && !z3) {
                    throw new AssertionError("Assertion failed");
                }
            }
            return i;
        }

        private final int gallopRight(byte[] keyArray, int keyIndex, byte[] a2, int base, int len, int hint, int entrySize, ByteArrayComparator c) {
            int i;
            int i2;
            if (ByteArrayTimSort.DEBUG) {
                boolean z = len > 0 && hint >= 0 && hint < len;
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Assertion failed");
                }
            }
            int i3 = base + hint;
            if (c.compare(entrySize, keyArray, keyIndex, a2, i3) < 0) {
                int i4 = hint + 1;
                int i5 = 1;
                int i6 = 0;
                while (i5 < i4 && c.compare(entrySize, keyArray, keyIndex, a2, i3 - i5) < 0) {
                    int i7 = (i5 * 2) + 1;
                    if (i7 <= 0) {
                        i7 = i4;
                    }
                    int i8 = i7;
                    i6 = i5;
                    i5 = i8;
                }
                if (i5 <= i4) {
                    i4 = i5;
                }
                i = hint - i4;
                i2 = hint - i6;
            } else {
                int i9 = len - hint;
                int i10 = 1;
                int i11 = 0;
                while (i10 < i9 && c.compare(entrySize, keyArray, keyIndex, a2, i3 + i10) >= 0) {
                    int i12 = (i10 * 2) + 1;
                    if (i12 <= 0) {
                        i12 = i9;
                    }
                    int i13 = i12;
                    i11 = i10;
                    i10 = i13;
                }
                if (i10 <= i9) {
                    i9 = i10;
                }
                i = i11 + hint;
                i2 = hint + i9;
            }
            if (ByteArrayTimSort.DEBUG) {
                boolean z2 = -1 <= i && i < i2 && i2 <= len;
                if (_Assertions.ENABLED && !z2) {
                    throw new AssertionError("Assertion failed");
                }
            }
            int i14 = i + 1;
            while (i14 < i2) {
                int i15 = ((i2 - i14) >>> 1) + i14;
                if (c.compare(entrySize, keyArray, keyIndex, a2, base + i15) < 0) {
                    i2 = i15;
                } else {
                    i14 = i15 + 1;
                }
            }
            if (ByteArrayTimSort.DEBUG) {
                boolean z3 = i14 == i2;
                if (_Assertions.ENABLED && !z3) {
                    throw new AssertionError("Assertion failed");
                }
            }
            return i2;
        }

        private final int minRunLength(int n) {
            int i = 0;
            if (ByteArrayTimSort.DEBUG) {
                boolean z = n >= 0;
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Assertion failed");
                }
            }
            while (n >= ByteArrayTimSort.MIN_MERGE) {
                i |= n & 1;
                n >>= 1;
            }
            return n + i;
        }

        private final void reverseRange(byte[] a2, int lo, int hi, int entrySize) {
            while (true) {
                hi--;
                if (lo >= hi) {
                    return;
                }
                int i = lo * entrySize;
                int i2 = hi * entrySize;
                for (int i3 = 0; i3 < entrySize; i3++) {
                    int i4 = i + i3;
                    byte b = a2[i4];
                    int i5 = i2 + i3;
                    a2[i4] = a2[i5];
                    a2[i5] = b;
                }
                lo++;
            }
        }

        public final void sort(byte[] a2, int lo, int hi, int entrySize, ByteArrayComparator c) {
            Intrinsics.checkParameterIsNotNull(a2, "a");
            Intrinsics.checkParameterIsNotNull(c, "c");
            Companion companion = this;
            companion.checkStartAndEnd(a2.length / entrySize, lo, hi);
            int i = hi - lo;
            if (i < 2) {
                return;
            }
            if (i < ByteArrayTimSort.MIN_MERGE) {
                companion.binarySort(a2, lo, hi, lo + companion.countRunAndMakeAscending(a2, lo, hi, entrySize, c), entrySize, c);
                return;
            }
            ByteArrayTimSort byteArrayTimSort = new ByteArrayTimSort(a2, c, entrySize, null);
            int iMinRunLength = companion.minRunLength(i);
            int i2 = i;
            int i3 = lo;
            do {
                int iCountRunAndMakeAscending = companion.countRunAndMakeAscending(a2, i3, hi, entrySize, c);
                if (iCountRunAndMakeAscending < iMinRunLength) {
                    int i4 = i2 <= iMinRunLength ? i2 : iMinRunLength;
                    companion.binarySort(a2, i3, i3 + i4, i3 + iCountRunAndMakeAscending, entrySize, c);
                    iCountRunAndMakeAscending = i4;
                }
                byteArrayTimSort.pushRun(i3, iCountRunAndMakeAscending);
                byteArrayTimSort.mergeCollapse();
                i3 += iCountRunAndMakeAscending;
                i2 -= iCountRunAndMakeAscending;
            } while (i2 != 0);
            if (ByteArrayTimSort.DEBUG) {
                boolean z = i3 == hi;
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Assertion failed");
                }
            }
            byteArrayTimSort.mergeForceCollapse();
            if (ByteArrayTimSort.DEBUG) {
                boolean z2 = byteArrayTimSort.stackSize == 1;
                if (_Assertions.ENABLED && !z2) {
                    throw new AssertionError("Assertion failed");
                }
            }
        }

        public final void sort(byte[] a2, int entrySize, ByteArrayComparator c) {
            Intrinsics.checkParameterIsNotNull(a2, "a");
            Intrinsics.checkParameterIsNotNull(c, "c");
            sort(a2, 0, a2.length / entrySize, entrySize, c);
        }
    }

    private ByteArrayTimSort(byte[] bArr, ByteArrayComparator byteArrayComparator, int i) {
        this.a = bArr;
        this.c = byteArrayComparator;
        this.entrySize = i;
        this.minGallop = MIN_GALLOP;
        int length = bArr.length / i;
        int i2 = INITIAL_TMP_STORAGE_LENGTH;
        this.tmp = new byte[i * (length < i2 * 2 ? length >>> 1 : i2)];
        int i3 = length < 120 ? 5 : length < 1542 ? 10 : length < 119151 ? 19 : 40;
        this.runBase = new int[i3];
        this.runLen = new int[i3];
    }

    public ByteArrayTimSort(byte[] bArr, ByteArrayComparator byteArrayComparator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, byteArrayComparator, i);
    }

    private final byte[] ensureCapacity(int minCapacity) {
        byte[] bArr = this.tmp;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        int length = bArr.length;
        int i = this.entrySize;
        if (length < minCapacity * i) {
            int i2 = (minCapacity >> 1) | minCapacity;
            int i3 = i2 | (i2 >> 2);
            int i4 = i3 | (i3 >> 4);
            int i5 = i4 | (i4 >> 8);
            int i6 = (i5 | (i5 >> 16)) + 1;
            if (i6 >= 0) {
                minCapacity = Math.min(i6, (this.a.length / i) >>> 1);
            }
            this.tmp = new byte[minCapacity * this.entrySize];
        }
        byte[] bArr2 = this.tmp;
        if (bArr2 == null) {
            Intrinsics.throwNpe();
        }
        return bArr2;
    }

    private final void mergeAt(int i) {
        if (DEBUG) {
            boolean z = this.stackSize >= 2;
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Assertion failed");
            }
        }
        if (DEBUG) {
            boolean z2 = i >= 0;
            if (_Assertions.ENABLED && !z2) {
                throw new AssertionError("Assertion failed");
            }
        }
        if (DEBUG) {
            int i2 = this.stackSize;
            boolean z3 = i == i2 + (-2) || i == i2 + (-3);
            if (_Assertions.ENABLED && !z3) {
                throw new AssertionError("Assertion failed");
            }
        }
        int[] iArr = this.runBase;
        int i3 = iArr[i];
        int[] iArr2 = this.runLen;
        int i4 = iArr2[i];
        int i5 = i + 1;
        int i6 = iArr[i5];
        int i7 = iArr2[i5];
        if (DEBUG) {
            boolean z4 = i4 > 0 && i7 > 0;
            if (_Assertions.ENABLED && !z4) {
                throw new AssertionError("Assertion failed");
            }
        }
        if (DEBUG) {
            boolean z5 = i3 + i4 == i6;
            if (_Assertions.ENABLED && !z5) {
                throw new AssertionError("Assertion failed");
            }
        }
        int[] iArr3 = this.runLen;
        iArr3[i] = i4 + i7;
        if (i == this.stackSize - 3) {
            int[] iArr4 = this.runBase;
            int i8 = i + 2;
            iArr4[i5] = iArr4[i8];
            iArr3[i5] = iArr3[i8];
        }
        this.stackSize--;
        Companion companion = INSTANCE;
        byte[] bArr = this.a;
        int iGallopRight = companion.gallopRight(bArr, i6, bArr, i3, i4, 0, this.entrySize, this.c);
        if (DEBUG) {
            boolean z6 = iGallopRight >= 0;
            if (_Assertions.ENABLED && !z6) {
                throw new AssertionError("Assertion failed");
            }
        }
        int i9 = i3 + iGallopRight;
        int i10 = i4 - iGallopRight;
        if (i10 == 0) {
            return;
        }
        Companion companion2 = INSTANCE;
        byte[] bArr2 = this.a;
        int iGallopLeft = companion2.gallopLeft(bArr2, (i9 + i10) - 1, bArr2, i6, i7, i7 - 1, this.entrySize, this.c);
        if (DEBUG) {
            boolean z7 = iGallopLeft >= 0;
            if (_Assertions.ENABLED && !z7) {
                throw new AssertionError("Assertion failed");
            }
        }
        if (iGallopLeft == 0) {
            return;
        }
        if (i10 <= iGallopLeft) {
            mergeLo(i9, i10, i6, iGallopLeft);
        } else {
            mergeHi(i9, i10, i6, iGallopLeft);
        }
    }

    private final void mergeCollapse() {
        while (true) {
            int i = this.stackSize;
            if (i <= 1) {
                return;
            }
            int i2 = i - 2;
            if (i2 >= 1) {
                int[] iArr = this.runLen;
                if (iArr[i2 - 1] > iArr[i2] + iArr[i2 + 1]) {
                    if (i2 >= 2) {
                        int[] iArr2 = this.runLen;
                        if (iArr2[i2 - 2] <= iArr2[i2] + iArr2[i2 - 1]) {
                            int[] iArr3 = this.runLen;
                            if (iArr3[i2 - 1] < iArr3[i2 + 1]) {
                                i2--;
                            }
                        }
                    }
                    int[] iArr4 = this.runLen;
                    if (iArr4[i2] > iArr4[i2 + 1]) {
                        return;
                    }
                }
            }
            mergeAt(i2);
        }
    }

    private final void mergeForceCollapse() {
        while (true) {
            int i = this.stackSize;
            if (i <= 1) {
                return;
            }
            int i2 = i - 2;
            if (i2 > 0) {
                int[] iArr = this.runLen;
                if (iArr[i2 - 1] < iArr[i2 + 1]) {
                    i2--;
                }
            }
            mergeAt(i2);
        }
    }

    private final void mergeHi(int base1, int len1, int base2, int len2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        byte[] bArr;
        byte[] bArr2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        ByteArrayComparator byteArrayComparator;
        int i12 = len2;
        int i13 = 0;
        if (DEBUG) {
            boolean z = len1 > 0 && i12 > 0 && base1 + len1 == base2;
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Assertion failed");
            }
        }
        byte[] bArr3 = this.a;
        byte[] bArrEnsureCapacity = ensureCapacity(i12);
        int i14 = this.entrySize;
        int i15 = i12 * i14;
        System.arraycopy(bArr3, base2 * i14, bArrEnsureCapacity, 0, i15);
        int i16 = (base1 + len1) - 1;
        int i17 = i12 - 1;
        int i18 = (base2 + i12) - 1;
        int i19 = i18 * i14;
        int i20 = i16 * i14;
        for (int i21 = 0; i21 < i14; i21++) {
            bArr3[i19 + i21] = bArr3[i20 + i21];
        }
        int i22 = i18 - 1;
        int i23 = i16 - 1;
        int i24 = len1 - 1;
        if (i24 == 0) {
            System.arraycopy(bArrEnsureCapacity, 0, bArr3, (i22 - i17) * i14, i15);
            return;
        }
        if (i12 == 1) {
            int i25 = i22 - i24;
            System.arraycopy(bArr3, ((i23 - i24) + 1) * i14, bArr3, (i25 + 1) * i14, i24 * i14);
            int i26 = i25 * i14;
            int i27 = i17 * i14;
            while (i13 < i14) {
                bArr3[i26 + i13] = bArrEnsureCapacity[i27 + i13];
                i13++;
            }
            return;
        }
        ByteArrayComparator byteArrayComparator2 = this.c;
        int i28 = this.minGallop;
        loop2: while (true) {
            i = i12;
            i2 = i23;
            i3 = i17;
            i4 = i24;
            int i29 = 0;
            int i30 = 0;
            while (true) {
                if (DEBUG) {
                    boolean z2 = i4 > 0 && i > 1;
                    if (_Assertions.ENABLED && !z2) {
                        throw new AssertionError("Assertion failed");
                    }
                }
                int i31 = i;
                if (byteArrayComparator2.compare(i14, bArrEnsureCapacity, i3, bArr3, i2) < 0) {
                    int i32 = i22 * i14;
                    int i33 = i2 * i14;
                    for (int i34 = 0; i34 < i14; i34++) {
                        bArr3[i32 + i34] = bArr3[i33 + i34];
                    }
                    i22--;
                    i2--;
                    i29++;
                    i4--;
                    if (i4 == 0) {
                        i5 = i28;
                        i6 = i14;
                        bArr = bArrEnsureCapacity;
                        bArr2 = bArr3;
                        i = i31;
                        break loop2;
                    }
                    i = i31;
                    i30 = 0;
                    if ((i29 | i30) < i28) {
                        break;
                    }
                } else {
                    int i35 = i22 * i14;
                    int i36 = i3 * i14;
                    for (int i37 = 0; i37 < i14; i37++) {
                        bArr3[i35 + i37] = bArrEnsureCapacity[i36 + i37];
                    }
                    i22--;
                    i3--;
                    i30++;
                    i = i31 - 1;
                    if (i == 1) {
                        i5 = i28;
                        i6 = i14;
                        bArr = bArrEnsureCapacity;
                        bArr2 = bArr3;
                        break loop2;
                    }
                    i29 = 0;
                    if ((i29 | i30) < i28) {
                    }
                }
            }
            bArrEnsureCapacity = bArr;
            i12 = i;
            bArr3 = bArr2;
            i14 = i6;
            i23 = i10;
            i17 = i9;
            i24 = i8;
            byteArrayComparator2 = byteArrayComparator;
            i28 = i7 + 2;
            i22 = i11;
        }
        i5 = i7;
        if (i5 < 1) {
            i5 = 1;
        }
        this.minGallop = i5;
        if (i == 1) {
            if (DEBUG) {
                boolean z3 = i4 > 0;
                if (_Assertions.ENABLED && !z3) {
                    throw new AssertionError("Assertion failed");
                }
            }
            int i38 = i22 - i4;
            System.arraycopy(bArr2, ((i2 - i4) + 1) * i6, bArr2, (i38 + 1) * i6, i4 * i6);
            int i39 = i38 * i6;
            int i40 = i3 * i6;
            while (i13 < i6) {
                bArr2[i39 + i13] = bArr[i40 + i13];
                i13++;
            }
            return;
        }
        if (i == 0) {
            throw new IllegalArgumentException("Comparison method violates its general contract!");
        }
        if (DEBUG) {
            boolean z4 = i4 == 0;
            if (_Assertions.ENABLED && !z4) {
                throw new AssertionError("Assertion failed");
            }
        }
        if (DEBUG) {
            boolean z5 = i > 0;
            if (_Assertions.ENABLED && !z5) {
                throw new AssertionError("Assertion failed");
            }
        }
        System.arraycopy(bArr, 0, bArr2, (i22 - (i - 1)) * i6, i * i6);
    }

    private final void mergeLo(int base1, int len1, int base2, int len2) {
        int i;
        int i2;
        int i3;
        int i4;
        byte[] bArr;
        int i5;
        int i6;
        ByteArrayComparator byteArrayComparator;
        int i7;
        int i8 = len1;
        int i9 = 1;
        if (DEBUG) {
            boolean z = i8 > 0 && len2 > 0 && base1 + i8 == base2;
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Assertion failed");
            }
        }
        byte[] bArr2 = this.a;
        int i10 = this.entrySize;
        byte[] bArrEnsureCapacity = ensureCapacity(i8);
        int i11 = base1 * i10;
        int i12 = i8 * i10;
        System.arraycopy(bArr2, i11, bArrEnsureCapacity, 0, i12);
        int i13 = base2 * i10;
        for (int i14 = 0; i14 < i10; i14++) {
            bArr2[i11 + i14] = bArr2[i13 + i14];
        }
        int i15 = base1 + 1;
        int i16 = base2 + 1;
        int i17 = len2 - 1;
        if (i17 == 0) {
            System.arraycopy(bArrEnsureCapacity, i10 * 0, bArr2, i15 * i10, i12);
            return;
        }
        if (i8 == 1) {
            System.arraycopy(bArr2, i16 * i10, bArr2, i15 * i10, i17 * i10);
            int i18 = (i15 + i17) * i10;
            int i19 = i10 * 0;
            for (int i20 = 0; i20 < i10; i20++) {
                bArr2[i18 + i20] = bArrEnsureCapacity[i19 + i20];
            }
            return;
        }
        ByteArrayComparator byteArrayComparator2 = this.c;
        int i21 = this.minGallop;
        int i22 = 0;
        loop2: while (true) {
            i = i15;
            i2 = i22;
            i3 = i17;
            int i23 = 0;
            int i24 = 0;
            while (true) {
                if (DEBUG) {
                    boolean z2 = i8 > i9 && i3 > 0;
                    if (_Assertions.ENABLED && !z2) {
                        throw new AssertionError("Assertion failed");
                    }
                }
                int i25 = i21;
                if (byteArrayComparator2.compare(i10, bArr2, i16, bArrEnsureCapacity, i2) < 0) {
                    int i26 = i * i10;
                    int i27 = i16 * i10;
                    for (int i28 = 0; i28 < i10; i28++) {
                        bArr2[i26 + i28] = bArr2[i27 + i28];
                    }
                    i++;
                    i16++;
                    i24++;
                    i3--;
                    if (i3 == 0) {
                        break loop2;
                    }
                    i23 = 0;
                    if ((i23 | i24) < i25) {
                        break;
                    }
                    i21 = i25;
                    i9 = 1;
                } else {
                    int i29 = i * i10;
                    int i30 = i2 * i10;
                    for (int i31 = 0; i31 < i10; i31++) {
                        bArr2[i29 + i31] = bArrEnsureCapacity[i30 + i31];
                    }
                    i++;
                    i2++;
                    i23++;
                    i8--;
                    if (i8 == i9) {
                        break loop2;
                    }
                    i24 = 0;
                    if ((i23 | i24) < i25) {
                    }
                }
            }
            i21 = i7 + 2;
            i10 = i5;
            bArrEnsureCapacity = bArr;
            i15 = i;
            i22 = i2;
            i17 = i3;
            byteArrayComparator2 = byteArrayComparator;
            i9 = 1;
        }
        if (i4 < i6) {
            i4 = 1;
        }
        this.minGallop = i4;
        if (i8 == i6) {
            if (DEBUG) {
                boolean z3 = i3 > 0;
                if (_Assertions.ENABLED && !z3) {
                    throw new AssertionError("Assertion failed");
                }
            }
            System.arraycopy(bArr2, i16 * i5, bArr2, i * i5, i3 * i5);
            int i32 = (i + i3) * i5;
            int i33 = i2 * i5;
            for (int i34 = 0; i34 < i5; i34++) {
                bArr2[i32 + i34] = bArr[i33 + i34];
            }
            return;
        }
        if (i8 == 0) {
            throw new IllegalArgumentException("Comparison method violates its general contract!");
        }
        if (DEBUG) {
            boolean z4 = i3 == 0;
            if (_Assertions.ENABLED && !z4) {
                throw new AssertionError("Assertion failed");
            }
        }
        if (DEBUG) {
            boolean z5 = i8 > 1;
            if (_Assertions.ENABLED && !z5) {
                throw new AssertionError("Assertion failed");
            }
        }
        System.arraycopy(bArr, i2 * i5, bArr2, i * i5, i8 * i5);
    }

    private final void pushRun(int runBase, int runLen) {
        int[] iArr = this.runBase;
        int i = this.stackSize;
        iArr[i] = runBase;
        this.runLen[i] = runLen;
        this.stackSize = i + 1;
    }
}
