package com.tencent.tinker.ziputils.ziputil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.ZipException;

public class TinkerZipEntry implements ZipConstants, Cloneable {
    public static final int DEFLATED = 8;
    public static final int STORED = 0;
    String comment;
    long compressedSize;
    int compressionMethod;
    long crc;
    long dataOffset;
    byte[] extra;
    long localHeaderRelOffset;
    int modDate;
    String name;
    long size;
    int time;

    public TinkerZipEntry(TinkerZipEntry tinkerZipEntry) {
        this.crc = -1L;
        this.compressedSize = -1L;
        this.size = -1L;
        this.compressionMethod = -1;
        this.time = -1;
        this.modDate = -1;
        this.localHeaderRelOffset = -1L;
        this.dataOffset = -1L;
        this.name = tinkerZipEntry.name;
        this.comment = tinkerZipEntry.comment;
        this.time = tinkerZipEntry.time;
        this.size = tinkerZipEntry.size;
        this.compressedSize = tinkerZipEntry.compressedSize;
        this.crc = tinkerZipEntry.crc;
        this.compressionMethod = tinkerZipEntry.compressionMethod;
        this.modDate = tinkerZipEntry.modDate;
        this.extra = tinkerZipEntry.extra;
        this.localHeaderRelOffset = tinkerZipEntry.localHeaderRelOffset;
        this.dataOffset = tinkerZipEntry.dataOffset;
    }

    public TinkerZipEntry(TinkerZipEntry tinkerZipEntry, String str) {
        this.crc = -1L;
        this.compressedSize = -1L;
        this.size = -1L;
        this.compressionMethod = -1;
        this.time = -1;
        this.modDate = -1;
        this.localHeaderRelOffset = -1L;
        this.dataOffset = -1L;
        this.name = str;
        this.comment = tinkerZipEntry.comment;
        this.time = tinkerZipEntry.time;
        this.size = tinkerZipEntry.size;
        this.compressedSize = tinkerZipEntry.compressedSize;
        this.crc = tinkerZipEntry.crc;
        this.compressionMethod = tinkerZipEntry.compressionMethod;
        this.modDate = tinkerZipEntry.modDate;
        this.extra = tinkerZipEntry.extra;
        this.localHeaderRelOffset = tinkerZipEntry.localHeaderRelOffset;
        this.dataOffset = tinkerZipEntry.dataOffset;
    }

    public TinkerZipEntry(String str) {
        this.crc = -1L;
        this.compressedSize = -1L;
        this.size = -1L;
        this.compressionMethod = -1;
        this.time = -1;
        this.modDate = -1;
        this.localHeaderRelOffset = -1L;
        this.dataOffset = -1L;
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        validateStringLength("Name", str);
        this.name = str;
    }

    public TinkerZipEntry(String str, String str2, long j, long j2, long j3, int i, int i2, int i3, byte[] bArr, long j4, long j5) {
        this.crc = -1L;
        this.compressedSize = -1L;
        this.size = -1L;
        this.compressionMethod = -1;
        this.time = -1;
        this.modDate = -1;
        this.localHeaderRelOffset = -1L;
        this.dataOffset = -1L;
        this.name = str;
        this.comment = str2;
        this.crc = j;
        this.compressedSize = j2;
        this.size = j3;
        this.compressionMethod = i;
        this.time = i2;
        this.modDate = i3;
        this.extra = bArr;
        this.localHeaderRelOffset = j4;
        this.dataOffset = j5;
    }

    TinkerZipEntry(byte[] bArr, InputStream inputStream, Charset charset, boolean z) throws IOException {
        this.crc = -1L;
        this.compressedSize = -1L;
        this.size = -1L;
        this.compressionMethod = -1;
        this.time = -1;
        this.modDate = -1;
        this.localHeaderRelOffset = -1L;
        this.dataOffset = -1L;
        Streams.readFully(inputStream, bArr, 0, bArr.length);
        BufferIterator it = HeapBufferIterator.iterator(bArr, 0, bArr.length, ByteOrder.LITTLE_ENDIAN);
        int i = it.readInt();
        if (i != 33639248) {
            TinkerZipFile.throwZipException("unknown", inputStream.available(), "unknown", 0L, "Central Directory Entry", i);
        }
        it.seek(8);
        int i2 = it.readShort() & 65535;
        if ((i2 & 1) != 0) {
            throw new ZipException("Invalid General Purpose Bit Flag: " + i2);
        }
        charset = (i2 & 2048) != 0 ? Charset.forName("UTF-8") : charset;
        this.compressionMethod = it.readShort() & 65535;
        this.time = it.readShort() & 65535;
        this.modDate = it.readShort() & 65535;
        this.crc = it.readInt() & 4294967295L;
        this.compressedSize = it.readInt() & 4294967295L;
        this.size = it.readInt() & 4294967295L;
        int i3 = it.readShort() & 65535;
        int i4 = it.readShort() & 65535;
        int i5 = 65535 & it.readShort();
        it.seek(42);
        this.localHeaderRelOffset = 4294967295L & it.readInt();
        byte[] bArr2 = new byte[i3];
        Streams.readFully(inputStream, bArr2, 0, i3);
        if (containsNulByte(bArr2)) {
            throw new ZipException("Filename contains NUL byte: " + java.util.Arrays.toString(bArr2));
        }
        this.name = new String(bArr2, 0, i3, charset);
        if (i4 > 0) {
            byte[] bArr3 = new byte[i4];
            this.extra = bArr3;
            Streams.readFully(inputStream, bArr3, 0, i4);
        }
        if (i5 > 0) {
            byte[] bArr4 = new byte[i5];
            Streams.readFully(inputStream, bArr4, 0, i5);
            this.comment = new String(bArr4, 0, i5, charset);
        }
    }

    private static boolean containsNulByte(byte[] bArr) {
        for (byte b : bArr) {
            if (b == 0) {
                return true;
            }
        }
        return false;
    }

    private static void validateStringLength(String str, String str2) {
        byte[] bytes = str2.getBytes(Charset.forName("UTF-8"));
        if (bytes.length <= 65535) {
            return;
        }
        throw new IllegalArgumentException(str + " too long: " + bytes.length);
    }

    public Object clone() {
        try {
            TinkerZipEntry tinkerZipEntry = (TinkerZipEntry) super.clone();
            tinkerZipEntry.extra = this.extra != null ? (byte[]) this.extra.clone() : null;
            return tinkerZipEntry;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof TinkerZipEntry) {
            return this.name.equals(((TinkerZipEntry) obj).name);
        }
        return false;
    }

    public String getComment() {
        return this.comment;
    }

    public long getCompressedSize() {
        return this.compressedSize;
    }

    public long getCrc() {
        return this.crc;
    }

    public long getDataOffset() {
        return this.dataOffset;
    }

    public byte[] getExtra() {
        return this.extra;
    }

    public int getMethod() {
        return this.compressionMethod;
    }

    public String getName() {
        return this.name;
    }

    public long getSize() {
        return this.size;
    }

    public long getTime() {
        if (this.time == -1) {
            return -1L;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(14, 0);
        int i = this.modDate;
        int i2 = ((i >> 9) & 127) + 1980;
        int i3 = ((i >> 5) & 15) - 1;
        int i4 = i & 31;
        int i5 = this.time;
        gregorianCalendar.set(i2, i3, i4, (i5 >> 11) & 31, (i5 >> 5) & 63, (i5 & 31) << 1);
        return gregorianCalendar.getTime().getTime();
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean isDirectory() {
        String str = this.name;
        return str.charAt(str.length() - 1) == '/';
    }

    public void setComment(String str) {
        if (str == null) {
            this.comment = null;
        } else {
            validateStringLength("Comment", str);
            this.comment = str;
        }
    }

    public void setCompressedSize(long j) {
        this.compressedSize = j;
    }

    public void setCrc(long j) {
        if (j >= 0 && j <= 4294967295L) {
            this.crc = j;
            return;
        }
        throw new IllegalArgumentException("Bad CRC32: " + j);
    }

    public void setDataOffset(long j) {
        this.dataOffset = j;
    }

    public void setExtra(byte[] bArr) {
        if (bArr == null || bArr.length <= 65535) {
            this.extra = bArr;
            return;
        }
        throw new IllegalArgumentException("Extra data too long: " + bArr.length);
    }

    public void setMethod(int i) {
        if (i == 0 || i == 8) {
            this.compressionMethod = i;
            return;
        }
        throw new IllegalArgumentException("Bad method: " + i);
    }

    public void setSize(long j) {
        if (j >= 0) {
            this.size = j;
            return;
        }
        throw new IllegalArgumentException("Bad size: " + j);
    }

    public void setTime(long j) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date(j));
        if (gregorianCalendar.get(1) < 1980) {
            this.modDate = 33;
            this.time = 0;
            return;
        }
        this.modDate = gregorianCalendar.get(5);
        this.modDate = ((gregorianCalendar.get(2) + 1) << 5) | this.modDate;
        this.modDate = ((gregorianCalendar.get(1) - 1980) << 9) | this.modDate;
        this.time = gregorianCalendar.get(13) >> 1;
        this.time = (gregorianCalendar.get(12) << 5) | this.time;
        this.time = (gregorianCalendar.get(11) << 11) | this.time;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("name:" + this.name);
        stringBuffer.append("\ncomment:" + this.comment);
        stringBuffer.append("\ntime:" + this.time);
        stringBuffer.append("\nsize:" + this.size);
        stringBuffer.append("\ncompressedSize:" + this.compressedSize);
        stringBuffer.append("\ncrc:" + this.crc);
        stringBuffer.append("\ncompressionMethod:" + this.compressionMethod);
        stringBuffer.append("\nmodDate:" + this.modDate);
        stringBuffer.append("\nextra length:" + this.extra.length);
        stringBuffer.append("\nlocalHeaderRelOffset:" + this.localHeaderRelOffset);
        stringBuffer.append("\ndataOffset:" + this.dataOffset);
        return stringBuffer.toString();
    }
}
