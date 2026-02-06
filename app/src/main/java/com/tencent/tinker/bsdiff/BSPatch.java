package com.tencent.tinker.bsdiff;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.zip.GZIPInputStream;

public class BSPatch {
    public static final int RETURN_DIFF_FILE_ERR = 2;
    public static final int RETURN_NEW_FILE_ERR = 4;
    public static final int RETURN_OLD_FILE_ERR = 3;
    public static final int RETURN_SUCCESS = 1;

    public static int patchFast(File file, File file2, File file3, int i) throws IOException {
        if (file == null || file.length() <= 0) {
            return 3;
        }
        if (file2 == null) {
            return 4;
        }
        if (file3 == null || file3.length() <= 0) {
            return 2;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        int length = (int) file3.length();
        byte[] bArr = new byte[length];
        FileInputStream fileInputStream = new FileInputStream(file3);
        try {
            BSUtil.readFromStream(fileInputStream, bArr, 0, length);
            fileInputStream.close();
            byte[] bArrPatchFast = patchFast(bufferedInputStream, (int) file.length(), bArr, i);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                fileOutputStream.write(bArrPatchFast);
                fileOutputStream.close();
                return 1;
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        } catch (Throwable th2) {
            fileInputStream.close();
            throw th2;
        }
    }

    public static int patchFast(InputStream inputStream, InputStream inputStream2, File file) throws IOException {
        if (inputStream == null) {
            return 3;
        }
        if (file == null) {
            return 4;
        }
        if (inputStream2 == null) {
            return 2;
        }
        byte[] bArrInputStreamToByte = BSUtil.inputStreamToByte(inputStream);
        byte[] bArrInputStreamToByte2 = BSUtil.inputStreamToByte(inputStream2);
        byte[] bArrPatchFast = patchFast(bArrInputStreamToByte, bArrInputStreamToByte.length, bArrInputStreamToByte2, bArrInputStreamToByte2.length, 0);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(bArrPatchFast);
            fileOutputStream.close();
            return 1;
        } catch (Throwable th) {
            fileOutputStream.close();
            throw th;
        }
    }

    public static byte[] patchFast(InputStream inputStream, int i, byte[] bArr, int i2) throws IOException {
        byte[] bArr2 = new byte[i];
        BSUtil.readFromStream(inputStream, bArr2, 0, i);
        inputStream.close();
        return patchFast(bArr2, i, bArr, bArr.length, i2);
    }

    public static byte[] patchFast(InputStream inputStream, InputStream inputStream2) throws IOException {
        if (inputStream == null || inputStream2 == null) {
            return null;
        }
        byte[] bArrInputStreamToByte = BSUtil.inputStreamToByte(inputStream);
        byte[] bArrInputStreamToByte2 = BSUtil.inputStreamToByte(inputStream2);
        return patchFast(bArrInputStreamToByte, bArrInputStreamToByte.length, bArrInputStreamToByte2, bArrInputStreamToByte2.length, 0);
    }

    public static byte[] patchFast(byte[] bArr, int i, byte[] bArr2, int i2, int i3) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr2, 0, i2));
        dataInputStream.skip(8L);
        long j = dataInputStream.readLong();
        long j2 = dataInputStream.readLong();
        int i4 = (int) dataInputStream.readLong();
        dataInputStream.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr2, 0, i2);
        byteArrayInputStream.skip(32L);
        DataInputStream dataInputStream2 = new DataInputStream(new GZIPInputStream(byteArrayInputStream));
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr2, 0, i2);
        byteArrayInputStream2.skip(j + 32);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream2);
        ByteArrayInputStream byteArrayInputStream3 = new ByteArrayInputStream(bArr2, 0, i2);
        byteArrayInputStream3.skip(j2 + j + 32);
        GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream3);
        byte[] bArr3 = new byte[i4];
        int[] iArr = new int[3];
        int i5 = 0;
        int i6 = 0;
        while (i5 < i4) {
            for (int i7 = 0; i7 <= 2; i7++) {
                iArr[i7] = dataInputStream2.readInt();
            }
            if (iArr[0] + i5 > i4) {
                throw new IOException("Corrupt by wrong patch file.");
            }
            if (!BSUtil.readFromStream(gZIPInputStream, bArr3, i5, iArr[0])) {
                throw new IOException("Corrupt by wrong patch file.");
            }
            for (int i8 = 0; i8 < iArr[0]; i8++) {
                int i9 = i6 + i8;
                if (i9 >= 0 && i9 < i) {
                    int i10 = i5 + i8;
                    bArr3[i10] = (byte) (bArr3[i10] + bArr[i9]);
                }
            }
            int i11 = i5 + iArr[0];
            int i12 = i6 + iArr[0];
            if (iArr[1] + i11 > i4) {
                throw new IOException("Corrupt by wrong patch file.");
            }
            if (!BSUtil.readFromStream(gZIPInputStream2, bArr3, i11, iArr[1])) {
                throw new IOException("Corrupt by wrong patch file.");
            }
            i5 = i11 + iArr[1];
            i6 = i12 + iArr[2];
        }
        dataInputStream2.close();
        gZIPInputStream.close();
        gZIPInputStream2.close();
        return bArr3;
    }

    public static int patchLessMemory(RandomAccessFile randomAccessFile, int i, byte[] bArr, int i2, File file, int i3) throws Throwable {
        if (randomAccessFile == null || i <= 0) {
            return 3;
        }
        if (file == null) {
            return 4;
        }
        if (bArr == null || i2 <= 0) {
            return 2;
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr, 0, i2));
        dataInputStream.skip(8L);
        long j = dataInputStream.readLong();
        long j2 = dataInputStream.readLong();
        int i4 = (int) dataInputStream.readLong();
        dataInputStream.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, 0, i2);
        byteArrayInputStream.skip(32L);
        DataInputStream dataInputStream2 = new DataInputStream(new GZIPInputStream(byteArrayInputStream));
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr, 0, i2);
        byteArrayInputStream2.skip(j + 32);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream2);
        ByteArrayInputStream byteArrayInputStream3 = new ByteArrayInputStream(bArr, 0, i2);
        byteArrayInputStream3.skip(j2 + j + 32);
        GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream3);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            int[] iArr = new int[3];
            int i5 = 0;
            int i6 = 0;
            while (i5 < i4) {
                for (int i7 = 0; i7 <= 2; i7++) {
                    iArr[i7] = dataInputStream2.readInt();
                }
                if (iArr[0] + i5 > i4) {
                    fileOutputStream.close();
                } else {
                    byte[] bArr2 = new byte[iArr[0]];
                    if (BSUtil.readFromStream(gZIPInputStream, bArr2, 0, iArr[0])) {
                        byte[] bArr3 = new byte[iArr[0]];
                        try {
                            if (randomAccessFile.read(bArr3, 0, iArr[0]) < iArr[0]) {
                                fileOutputStream.close();
                            } else {
                                for (int i8 = 0; i8 < iArr[0]; i8++) {
                                    int i9 = i6 + i8;
                                    if (i9 >= 0 && i9 < i) {
                                        bArr2[i8] = (byte) (bArr2[i8] + bArr3[i8]);
                                    }
                                }
                                fileOutputStream.write(bArr2);
                                int i10 = i5 + iArr[0];
                                int i11 = i6 + iArr[0];
                                if (iArr[1] + i10 > i4) {
                                    fileOutputStream.close();
                                } else {
                                    byte[] bArr4 = new byte[iArr[1]];
                                    if (!BSUtil.readFromStream(gZIPInputStream2, bArr4, 0, iArr[1])) {
                                        fileOutputStream.close();
                                        randomAccessFile.close();
                                        fileOutputStream.close();
                                        return 2;
                                    }
                                    fileOutputStream.write(bArr4);
                                    fileOutputStream.flush();
                                    i5 = i10 + iArr[1];
                                    i6 = i11 + iArr[2];
                                    randomAccessFile.seek(i6);
                                }
                            }
                            randomAccessFile.close();
                            fileOutputStream.close();
                            return 2;
                        } catch (Throwable th) {
                            th = th;
                            randomAccessFile.close();
                            fileOutputStream.close();
                            throw th;
                        }
                    }
                    fileOutputStream.close();
                }
                randomAccessFile.close();
                fileOutputStream.close();
                return 2;
            }
            dataInputStream2.close();
            gZIPInputStream.close();
            gZIPInputStream2.close();
            randomAccessFile.close();
            fileOutputStream.close();
            return 1;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static int patchLessMemory(RandomAccessFile randomAccessFile, File file, File file2, int i) throws IOException {
        if (randomAccessFile == null || randomAccessFile.length() <= 0) {
            return 3;
        }
        if (file == null) {
            return 4;
        }
        if (file2 == null || file2.length() <= 0) {
            return 2;
        }
        int length = (int) file2.length();
        byte[] bArr = new byte[length];
        FileInputStream fileInputStream = new FileInputStream(file2);
        try {
            BSUtil.readFromStream(fileInputStream, bArr, 0, length);
            fileInputStream.close();
            return patchLessMemory(randomAccessFile, (int) randomAccessFile.length(), bArr, length, file, i);
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }
}
