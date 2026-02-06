package com.tencent.tinker.bsdiff;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

public class BSDiff {
    private static final byte[] MAGIC_BYTES = {77, 105, 99, 114, 111, 77, 115, 103};

    private static class IntByRef {
        private int value;

        private IntByRef() {
        }
    }

    public static void bsdiff(File file, File file2, File file3) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file2));
        FileOutputStream fileOutputStream = new FileOutputStream(file3);
        try {
            fileOutputStream.write(bsdiff(bufferedInputStream, (int) file.length(), bufferedInputStream2, (int) file2.length()));
        } finally {
            fileOutputStream.close();
        }
    }

    public static byte[] bsdiff(InputStream inputStream, int i, InputStream inputStream2, int i2) throws IOException {
        byte[] bArr = new byte[i];
        BSUtil.readFromStream(inputStream, bArr, 0, i);
        inputStream.close();
        byte[] bArr2 = new byte[i2];
        BSUtil.readFromStream(inputStream2, bArr2, 0, i2);
        inputStream2.close();
        return bsdiff(bArr, i, bArr2, i2);
    }

    public static byte[] bsdiff(byte[] bArr, int i, byte[] bArr2, int i2) throws IOException {
        int i3;
        IntByRef intByRef;
        DataOutputStream dataOutputStream;
        GZIPOutputStream gZIPOutputStream;
        long j;
        DataOutputStream dataOutputStream2;
        int i4;
        int iSearch;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11 = i;
        int i12 = i11 + 1;
        int[] iArr = new int[i12];
        qsufsort(iArr, new int[i12], bArr, i11);
        byte[] bArr3 = new byte[i2];
        byte[] bArr4 = new byte[i2];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream3 = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream3.write(MAGIC_BYTES);
        dataOutputStream3.writeLong(-1L);
        dataOutputStream3.writeLong(-1L);
        long j2 = i2;
        dataOutputStream3.writeLong(j2);
        dataOutputStream3.flush();
        GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(dataOutputStream3);
        DataOutputStream dataOutputStream4 = new DataOutputStream(gZIPOutputStream2);
        IntByRef intByRef2 = new IntByRef();
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        while (i13 < i2) {
            int i20 = i13 + i16;
            int i21 = i16;
            int i22 = 0;
            int i23 = i20;
            while (true) {
                if (i20 >= i2) {
                    i3 = i14;
                    intByRef = intByRef2;
                    dataOutputStream = dataOutputStream4;
                    gZIPOutputStream = gZIPOutputStream2;
                    j = j2;
                    dataOutputStream2 = dataOutputStream3;
                    i4 = i20;
                    iSearch = i21;
                    i5 = i22;
                    break;
                }
                int i24 = i20;
                i3 = i14;
                intByRef = intByRef2;
                dataOutputStream = dataOutputStream4;
                gZIPOutputStream = gZIPOutputStream2;
                j = j2;
                dataOutputStream2 = dataOutputStream3;
                iSearch = search(iArr, bArr, i, bArr2, i2, i24, 0, i, intByRef);
                int i25 = i23;
                i5 = i22;
                i4 = i24;
                while (i25 < i4 + iSearch) {
                    int i26 = i25 + i17;
                    if (i26 < i11 && bArr[i26] == bArr2[i25]) {
                        i5++;
                    }
                    i25++;
                }
                if ((iSearch == i5 && iSearch != 0) || iSearch > i5 + 8) {
                    break;
                }
                int i27 = i4 + i17;
                if (i27 < i11 && bArr[i27] == bArr2[i4]) {
                    i5--;
                }
                i22 = i5;
                i21 = iSearch;
                i23 = i25;
                i20 = i4 + 1;
                i14 = i3;
                intByRef2 = intByRef;
                dataOutputStream4 = dataOutputStream;
                gZIPOutputStream2 = gZIPOutputStream;
                j2 = j;
                dataOutputStream3 = dataOutputStream2;
            }
            if (iSearch != i5 || i4 == i2) {
                int i28 = 0;
                int i29 = 0;
                int i30 = 0;
                int i31 = 0;
                while (true) {
                    int i32 = i18 + i29;
                    if (i32 >= i4 || (i10 = i19 + i29) >= i11) {
                        break;
                    }
                    if (bArr[i10] == bArr2[i32]) {
                        i28++;
                    }
                    i29++;
                    if ((i28 * 2) - i29 > (i30 * 2) - i31) {
                        i30 = i28;
                        i31 = i29;
                    }
                }
                if (i4 < i2) {
                    i6 = 0;
                    int i33 = 0;
                    int i34 = 0;
                    for (int i35 = 1; i4 >= i18 + i35 && intByRef.value >= i35; i35++) {
                        if (bArr[intByRef.value - i35] == bArr2[i4 - i35]) {
                            i33++;
                        }
                        if ((i33 * 2) - i35 > (i34 * 2) - i6) {
                            i6 = i35;
                            i34 = i33;
                        }
                    }
                } else {
                    i6 = 0;
                }
                int i36 = i18 + i31;
                int i37 = i4 - i6;
                if (i36 > i37) {
                    int i38 = i36 - i37;
                    i7 = iSearch;
                    int i39 = 0;
                    int i40 = 0;
                    int i41 = 0;
                    int i42 = 0;
                    while (i40 < i38) {
                        int i43 = i36;
                        if (bArr2[(i36 - i38) + i40] == bArr[((i19 + i31) - i38) + i40]) {
                            i42++;
                        }
                        if (bArr2[i37 + i40] == bArr[(intByRef.value - i6) + i40]) {
                            i42--;
                        }
                        int i44 = i42;
                        if (i44 > i39) {
                            i41 = i40 + 1;
                            i39 = i44;
                        }
                        i40++;
                        i42 = i44;
                        i36 = i43;
                    }
                    i31 += i41 - i38;
                    i6 -= i41;
                } else {
                    i7 = iSearch;
                }
                for (int i45 = 0; i45 < i31; i45++) {
                    bArr3[i3 + i45] = (byte) (bArr2[i18 + i45] - bArr[i19 + i45]);
                }
                int i46 = i3;
                int i47 = 0;
                while (true) {
                    i8 = i4 - i6;
                    int i48 = i18 + i31;
                    i9 = i8 - i48;
                    if (i47 >= i9) {
                        break;
                    }
                    int i49 = i15;
                    bArr4[i49 + i47] = bArr2[i48 + i47];
                    i47++;
                    i15 = i49;
                }
                i14 = i46 + i31;
                i15 += i9;
                DataOutputStream dataOutputStream5 = dataOutputStream;
                dataOutputStream5.writeInt(i31);
                dataOutputStream5.writeInt(i9);
                dataOutputStream5.writeInt((intByRef.value - i6) - (i19 + i31));
                i19 = intByRef.value - i6;
                i11 = i;
                i18 = i8;
                i16 = i7;
                gZIPOutputStream2 = gZIPOutputStream;
                j2 = j;
                dataOutputStream3 = dataOutputStream2;
                dataOutputStream4 = dataOutputStream5;
                i17 = intByRef.value - i4;
                i13 = i4;
                intByRef2 = intByRef;
            } else {
                i16 = iSearch;
                i13 = i4;
                i14 = i3;
                intByRef2 = intByRef;
                dataOutputStream4 = dataOutputStream;
                gZIPOutputStream2 = gZIPOutputStream;
                j2 = j;
                dataOutputStream3 = dataOutputStream2;
            }
        }
        DataOutputStream dataOutputStream6 = dataOutputStream3;
        dataOutputStream4.flush();
        gZIPOutputStream2.finish();
        int size = dataOutputStream6.size() - 32;
        GZIPOutputStream gZIPOutputStream3 = new GZIPOutputStream(dataOutputStream6);
        gZIPOutputStream3.write(bArr3, 0, i14);
        gZIPOutputStream3.finish();
        gZIPOutputStream3.flush();
        int size2 = (dataOutputStream6.size() - size) - 32;
        GZIPOutputStream gZIPOutputStream4 = new GZIPOutputStream(dataOutputStream6);
        gZIPOutputStream4.write(bArr4, 0, i15);
        gZIPOutputStream4.finish();
        gZIPOutputStream4.flush();
        dataOutputStream6.close();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(32);
        DataOutputStream dataOutputStream7 = new DataOutputStream(byteArrayOutputStream2);
        dataOutputStream7.write(MAGIC_BYTES);
        dataOutputStream7.writeLong(size);
        dataOutputStream7.writeLong(size2);
        dataOutputStream7.writeLong(j2);
        dataOutputStream7.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
        System.arraycopy(byteArray2, 0, byteArray, 0, byteArray2.length);
        return byteArray;
    }

    private static int matchlen(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int iMin = Math.min(i - i2, i3 - i4);
        for (int i5 = 0; i5 < iMin; i5++) {
            if (bArr[i2 + i5] != bArr2[i4 + i5]) {
                return i5;
            }
        }
        return iMin;
    }

    private static int memcmp(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5 = i - i2;
        int i6 = i3 - i4;
        if (i5 > i6) {
            i5 = i6;
        }
        for (int i7 = 0; i7 < i5; i7++) {
            int i8 = i7 + i2;
            int i9 = i7 + i4;
            if (bArr[i8] != bArr2[i9]) {
                return bArr[i8] < bArr2[i9] ? -1 : 1;
            }
        }
        return 0;
    }

    private static void qsufsort(int[] iArr, int[] iArr2, byte[] bArr, int i) {
        int i2;
        int i3;
        int[] iArr3 = new int[256];
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = 255 & bArr[i4];
            iArr3[i5] = iArr3[i5] + 1;
        }
        for (int i6 = 1; i6 < 256; i6++) {
            iArr3[i6] = iArr3[i6] + iArr3[i6 - 1];
        }
        for (int i7 = 255; i7 > 0; i7--) {
            iArr3[i7] = iArr3[i7 - 1];
        }
        iArr3[0] = 0;
        for (int i8 = 0; i8 < i; i8++) {
            int i9 = bArr[i8] & 255;
            int i10 = iArr3[i9] + 1;
            iArr3[i9] = i10;
            iArr[i10] = i8;
        }
        iArr[0] = i;
        for (int i11 = 0; i11 < i; i11++) {
            iArr2[i11] = iArr3[bArr[i11] & 255];
        }
        iArr2[i] = 0;
        for (int i12 = 1; i12 < 256; i12++) {
            if (iArr3[i12] == iArr3[i12 - 1] + 1) {
                iArr[iArr3[i12]] = -1;
            }
        }
        iArr[0] = -1;
        int i13 = 1;
        while (true) {
            i2 = i + 1;
            if (iArr[0] == (-i2)) {
                break;
            }
            int i14 = 0;
            while (true) {
                i3 = 0;
                while (i14 < i2) {
                    if (iArr[i14] < 0) {
                        i3 -= iArr[i14];
                        i14 -= iArr[i14];
                    } else {
                        if (i3 != 0) {
                            iArr[i14 - i3] = -i3;
                        }
                        int i15 = (iArr2[iArr[i14]] + 1) - i14;
                        split(iArr, iArr2, i14, i15, i13);
                        i14 += i15;
                    }
                }
                break;
            }
            if (i3 != 0) {
                iArr[i14 - i3] = -i3;
            }
            i13 += i13;
        }
        for (int i16 = 0; i16 < i2; i16++) {
            iArr[iArr2[i16]] = i16;
        }
    }

    private static int search(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2, int i3, int i4, int i5, IntByRef intByRef) {
        int i6 = i5 - i4;
        if (i6 >= 2) {
            int i7 = i4 + (i6 / 2);
            return memcmp(bArr, i, iArr[i7], bArr2, i2, i3) < 0 ? search(iArr, bArr, i, bArr2, i2, i3, i7, i5, intByRef) : search(iArr, bArr, i, bArr2, i2, i3, i4, i7, intByRef);
        }
        int iMatchlen = matchlen(bArr, i, iArr[i4], bArr2, i2, i3);
        int iMatchlen2 = matchlen(bArr, i, iArr[i5], bArr2, i2, i3);
        if (iMatchlen > iMatchlen2) {
            intByRef.value = iArr[i4];
            return iMatchlen;
        }
        intByRef.value = iArr[i5];
        return iMatchlen2;
    }

    private static void split(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int i4;
        if (i2 >= 16) {
            int i5 = iArr2[iArr[(i2 / 2) + i] + i3];
            int i6 = i;
            int i7 = 0;
            int i8 = 0;
            while (true) {
                i4 = i + i2;
                if (i6 >= i4) {
                    break;
                }
                if (iArr2[iArr[i6] + i3] < i5) {
                    i7++;
                }
                if (iArr2[iArr[i6] + i3] == i5) {
                    i8++;
                }
                i6++;
            }
            int i9 = i7 + i;
            int i10 = i8 + i9;
            int i11 = i;
            int i12 = 0;
            int i13 = 0;
            while (i11 < i9) {
                if (iArr2[iArr[i11] + i3] < i5) {
                    i11++;
                } else if (iArr2[iArr[i11] + i3] == i5) {
                    int i14 = iArr[i11];
                    int i15 = i9 + i12;
                    iArr[i11] = iArr[i15];
                    iArr[i15] = i14;
                    i12++;
                } else {
                    int i16 = iArr[i11];
                    int i17 = i10 + i13;
                    iArr[i11] = iArr[i17];
                    iArr[i17] = i16;
                    i13++;
                }
            }
            while (true) {
                int i18 = i9 + i12;
                if (i18 >= i10) {
                    break;
                }
                if (iArr2[iArr[i18] + i3] == i5) {
                    i12++;
                } else {
                    int i19 = iArr[i18];
                    int i20 = i10 + i13;
                    iArr[i18] = iArr[i20];
                    iArr[i20] = i19;
                    i13++;
                }
            }
            if (i9 > i) {
                split(iArr, iArr2, i, i9 - i, i3);
            }
            for (int i21 = 0; i21 < i10 - i9; i21++) {
                iArr2[iArr[i9 + i21]] = i10 - 1;
            }
            if (i9 == i10 - 1) {
                iArr[i9] = -1;
            }
            if (i4 > i10) {
                split(iArr, iArr2, i10, i4 - i10, i3);
                return;
            }
            return;
        }
        int i22 = i;
        while (true) {
            int i23 = i + i2;
            if (i22 >= i23) {
                return;
            }
            int i24 = iArr2[iArr[i22] + i3];
            int i25 = 1;
            int i26 = 1;
            while (true) {
                int i27 = i22 + i25;
                if (i27 >= i23) {
                    break;
                }
                if (iArr2[iArr[i27] + i3] < i24) {
                    i24 = iArr2[iArr[i27] + i3];
                    i26 = 0;
                }
                if (iArr2[iArr[i27] + i3] == i24) {
                    int i28 = i22 + i26;
                    int i29 = iArr[i28];
                    iArr[i28] = iArr[i27];
                    iArr[i27] = i29;
                    i26++;
                }
                i25++;
            }
            for (int i30 = 0; i30 < i26; i30++) {
                iArr2[iArr[i22 + i30]] = (i22 + i26) - 1;
            }
            if (i26 == 1) {
                iArr[i22] = -1;
            }
            i22 += i26;
        }
    }
}
