package com.tencent.tinker.ziputils.ziputil;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.zip.ZipException;

public class TinkerZipFile implements Closeable, ZipConstants {
    static final int GPBF_DATA_DESCRIPTOR_FLAG = 8;
    static final int GPBF_ENCRYPTED_FLAG = 1;
    static final int GPBF_UNSUPPORTED_MASK = 1;
    static final int GPBF_UTF8_FLAG = 2048;
    public static final int OPEN_DELETE = 4;
    public static final int OPEN_READ = 1;
    private String comment;
    private final LinkedHashMap<String, TinkerZipEntry> entries;
    private File fileToDeleteOnClose;
    private final String filename;
    private RandomAccessFile raf;

    static class EocdRecord {
        final long centralDirOffset;
        final int commentLength;
        final long numEntries;

        EocdRecord(long j, long j2, int i) {
            this.numEntries = j;
            this.centralDirOffset = j2;
            this.commentLength = i;
        }
    }

    public static class RAFStream extends InputStream {
        private long endOffset;
        private long offset;
        private final RandomAccessFile sharedRaf;

        public RAFStream(RandomAccessFile randomAccessFile, long j) throws IOException {
            this(randomAccessFile, j, randomAccessFile.length());
        }

        public RAFStream(RandomAccessFile randomAccessFile, long j, long j2) {
            this.sharedRaf = randomAccessFile;
            this.offset = j;
            this.endOffset = j2;
        }

        @Override
        public int available() throws IOException {
            return this.offset < this.endOffset ? 1 : 0;
        }

        @Override
        public int read() throws IOException {
            return Streams.readSingleByte(this);
        }

        @Override
        public int read(byte[] bArr, int i, int i2) throws IOException {
            synchronized (this.sharedRaf) {
                long j = this.endOffset - this.offset;
                if (i2 > j) {
                    i2 = (int) j;
                }
                this.sharedRaf.seek(this.offset);
                int i3 = this.sharedRaf.read(bArr, i, i2);
                if (i3 <= 0) {
                    return -1;
                }
                this.offset += i3;
                return i3;
            }
        }

        @Override
        public long skip(long j) throws IOException {
            long j2 = this.endOffset;
            long j3 = this.offset;
            if (j > j2 - j3) {
                j = j2 - j3;
            }
            this.offset += j;
            return j;
        }
    }

    public TinkerZipFile(File file) throws IOException {
        this(file, 1);
    }

    public TinkerZipFile(File file, int i) throws IOException {
        this.entries = new LinkedHashMap<>();
        this.filename = file.getPath();
        if (i != 1 && i != 5) {
            throw new IllegalArgumentException("Bad mode: " + i);
        }
        if ((i & 4) != 0) {
            this.fileToDeleteOnClose = file;
            file.deleteOnExit();
        } else {
            this.fileToDeleteOnClose = null;
        }
        this.raf = new RandomAccessFile(this.filename, "r");
        readCentralDir();
    }

    public TinkerZipFile(String str) throws IOException {
        this(new File(str), 1);
    }

    private void checkNotClosed() {
        if (this.raf == null) {
            throw new IllegalStateException("Zip file closed");
        }
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        int length;
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || (length = charSequence.length()) != charSequence2.length()) {
            return false;
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return charSequence.equals(charSequence2);
        }
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    private static EocdRecord parseEocdRecord(RandomAccessFile randomAccessFile, long j, boolean z) throws IOException {
        long j2;
        long j3;
        randomAccessFile.seek(j);
        byte[] bArr = new byte[18];
        randomAccessFile.readFully(bArr);
        BufferIterator it = HeapBufferIterator.iterator(bArr, 0, 18, ByteOrder.LITTLE_ENDIAN);
        if (z) {
            it.skip(16);
            j2 = -1;
            j3 = -1;
        } else {
            int i = it.readShort() & 65535;
            int i2 = it.readShort() & 65535;
            long j4 = it.readShort() & 65535;
            int i3 = it.readShort() & 65535;
            it.skip(4);
            long j5 = it.readInt() & 4294967295L;
            if (j4 != i3 || i != 0 || i2 != 0) {
                throw new ZipException("Spanned archives not supported");
            }
            j2 = j4;
            j3 = j5;
        }
        return new EocdRecord(j2, j3, it.readShort() & 65535);
    }

    private void readCentralDir() throws IOException {
        long length = this.raf.length() - 22;
        if (length < 0) {
            throw new ZipException("File too short to be a zip file: " + this.raf.length());
        }
        this.raf.seek(0L);
        if (Integer.reverseBytes(this.raf.readInt()) != 67324752) {
            throw new ZipException("Not a zip archive");
        }
        long j = length - 65536;
        long j2 = j >= 0 ? j : 0L;
        do {
            this.raf.seek(length);
            if (Integer.reverseBytes(this.raf.readInt()) == 101010256) {
                byte[] bArr = new byte[18];
                this.raf.readFully(bArr);
                BufferIterator it = HeapBufferIterator.iterator(bArr, 0, 18, ByteOrder.LITTLE_ENDIAN);
                int i = it.readShort() & 65535;
                int i2 = it.readShort() & 65535;
                int i3 = it.readShort() & 65535;
                int i4 = it.readShort() & 65535;
                it.skip(4);
                long j3 = it.readInt() & 4294967295L;
                int i5 = it.readShort() & 65535;
                if (i3 != i4 || i != 0 || i2 != 0) {
                    throw new ZipException("Spanned archives not supported");
                }
                if (i5 > 0) {
                    byte[] bArr2 = new byte[i5];
                    this.raf.readFully(bArr2);
                    this.comment = new String(bArr2, 0, i5, StandardCharsets.UTF_8);
                }
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new RAFStream(this.raf, j3), 4096);
                byte[] bArr3 = new byte[46];
                for (int i6 = 0; i6 < i3; i6++) {
                    TinkerZipEntry tinkerZipEntry = new TinkerZipEntry(bArr3, bufferedInputStream, StandardCharsets.UTF_8, false);
                    if (tinkerZipEntry.localHeaderRelOffset >= j3) {
                        throw new ZipException("Local file header offset is after central directory");
                    }
                    String name = tinkerZipEntry.getName();
                    if (this.entries.put(name, tinkerZipEntry) != null) {
                        throw new ZipException("Duplicate entry name: " + name);
                    }
                }
                return;
            }
            length--;
        } while (length >= j2);
        throw new ZipException("End Of Central Directory signature not found");
    }

    static void throwZipException(String str, long j, String str2, long j2, String str3, int i) throws ZipException {
        throw new ZipException("file name:" + str + ", file size" + j + ", entry name:" + str2 + ", entry localHeaderRelOffset:" + j2 + ", " + str3 + " signature not found; was " + Integer.toHexString(i));
    }

    @Override
    public void close() throws IOException {
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            synchronized (randomAccessFile) {
                this.raf = null;
                randomAccessFile.close();
            }
            File file = this.fileToDeleteOnClose;
            if (file != null) {
                file.delete();
                this.fileToDeleteOnClose = null;
            }
        }
    }

    public Enumeration<? extends TinkerZipEntry> entries() {
        checkNotClosed();
        final Iterator<TinkerZipEntry> it = this.entries.values().iterator();
        return new Enumeration<TinkerZipEntry>() {
            @Override
            public boolean hasMoreElements() {
                TinkerZipFile.this.checkNotClosed();
                return it.hasNext();
            }

            @Override
            public TinkerZipEntry nextElement() {
                TinkerZipFile.this.checkNotClosed();
                return (TinkerZipEntry) it.next();
            }
        };
    }

    public String getComment() {
        checkNotClosed();
        return this.comment;
    }

    public TinkerZipEntry getEntry(String str) {
        checkNotClosed();
        if (str == null) {
            throw new NullPointerException("entryName == null");
        }
        TinkerZipEntry tinkerZipEntry = this.entries.get(str);
        if (tinkerZipEntry != null) {
            return tinkerZipEntry;
        }
        return this.entries.get(str + "/");
    }

    public InputStream getInputStream(TinkerZipEntry tinkerZipEntry) throws IOException {
        RAFStream rAFStream;
        TinkerZipEntry entry = getEntry(tinkerZipEntry.getName());
        if (entry == null) {
            return null;
        }
        RandomAccessFile randomAccessFile = this.raf;
        synchronized (randomAccessFile) {
            rAFStream = new RAFStream(randomAccessFile, entry.localHeaderRelOffset);
            DataInputStream dataInputStream = new DataInputStream(rAFStream);
            int iReverseBytes = Integer.reverseBytes(dataInputStream.readInt());
            if (iReverseBytes != 67324752) {
                throwZipException(this.filename, randomAccessFile.length(), entry.getName(), entry.localHeaderRelOffset, "Local File Header", iReverseBytes);
            }
            dataInputStream.skipBytes(2);
            int iReverseBytes2 = Short.reverseBytes(dataInputStream.readShort()) & 65535;
            if ((iReverseBytes2 & 1) != 0) {
                throw new ZipException("Invalid General Purpose Bit Flag: " + iReverseBytes2);
            }
            dataInputStream.skipBytes(18);
            int iReverseBytes3 = Short.reverseBytes(dataInputStream.readShort()) & 65535;
            int iReverseBytes4 = 65535 & Short.reverseBytes(dataInputStream.readShort());
            dataInputStream.close();
            rAFStream.skip(iReverseBytes3 + iReverseBytes4);
            if (entry.compressionMethod == 0) {
                rAFStream.endOffset = rAFStream.offset + entry.size;
            } else {
                rAFStream.endOffset = rAFStream.offset + entry.compressedSize;
            }
        }
        return rAFStream;
    }

    public String getName() {
        return this.filename;
    }

    public int size() {
        checkNotClosed();
        return this.entries.size();
    }
}
