package org.apache.commons.io.input;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class ReversedLinesFileReader implements Closeable {
    private final int avoidNewlineSplitBufferSize;
    private final int blockSize;
    private final int byteDecrement;
    private FilePart currentFilePart;
    private final String encoding;
    private final byte[][] newLineSequences;
    private final RandomAccessFile randomAccessFile;
    private final long totalBlockCount;
    private final long totalByteLength;
    private boolean trailingNewlineOfFileSkipped;

    private class FilePart {
        private int currentLastBytePos;
        private final byte[] data;
        private byte[] leftOver;
        private final long no;

        private FilePart(long j, int i, byte[] bArr) throws IOException {
            this.no = j;
            this.data = new byte[(bArr != null ? bArr.length : 0) + i];
            long j2 = (j - 1) * ReversedLinesFileReader.this.blockSize;
            if (j > 0) {
                ReversedLinesFileReader.this.randomAccessFile.seek(j2);
                if (ReversedLinesFileReader.this.randomAccessFile.read(this.data, 0, i) != i) {
                    throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
                }
            }
            if (bArr != null) {
                System.arraycopy(bArr, 0, this.data, i, bArr.length);
            }
            this.currentLastBytePos = this.data.length - 1;
            this.leftOver = null;
        }

        private void createLeftOver() {
            int i = this.currentLastBytePos + 1;
            if (i > 0) {
                byte[] bArr = new byte[i];
                this.leftOver = bArr;
                System.arraycopy(this.data, 0, bArr, 0, i);
            } else {
                this.leftOver = null;
            }
            this.currentLastBytePos = -1;
        }

        private int getNewLineMatchByteCount(byte[] bArr, int i) {
            for (byte[] bArr2 : ReversedLinesFileReader.this.newLineSequences) {
                boolean z = true;
                for (int length = bArr2.length - 1; length >= 0; length--) {
                    int length2 = (i + length) - (bArr2.length - 1);
                    z &= length2 >= 0 && bArr[length2] == bArr2[length];
                }
                if (z) {
                    return bArr2.length;
                }
            }
            return 0;
        }

        private String readLine() throws IOException {
            boolean z = this.no == 1;
            int i = this.currentLastBytePos;
            while (true) {
                if (i > -1) {
                    if (!z && i < ReversedLinesFileReader.this.avoidNewlineSplitBufferSize) {
                        createLeftOver();
                        break;
                    }
                    int newLineMatchByteCount = getNewLineMatchByteCount(this.data, i);
                    if (newLineMatchByteCount > 0) {
                        int i2 = i + 1;
                        int i3 = (this.currentLastBytePos - i2) + 1;
                        if (i3 < 0) {
                            throw new IllegalStateException("Unexpected negative line length=" + i3);
                        }
                        byte[] bArr = new byte[i3];
                        System.arraycopy(this.data, i2, bArr, 0, i3);
                        String str = new String(bArr, ReversedLinesFileReader.this.encoding);
                        this.currentLastBytePos = i - newLineMatchByteCount;
                    } else {
                        i -= ReversedLinesFileReader.this.byteDecrement;
                        if (i < 0) {
                            createLeftOver();
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }

        private FilePart rollOver() throws IOException {
            if (this.currentLastBytePos > -1) {
                throw new IllegalStateException("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=" + this.currentLastBytePos);
            }
            long j = this.no;
            if (j > 1) {
                ReversedLinesFileReader reversedLinesFileReader = ReversedLinesFileReader.this;
                return reversedLinesFileReader.new FilePart(j - 1, reversedLinesFileReader.blockSize, this.leftOver);
            }
            if (this.leftOver == null) {
                return null;
            }
            throw new IllegalStateException("Unexpected leftover of the last block: leftOverOfThisFilePart=" + new String(this.leftOver, ReversedLinesFileReader.this.encoding));
        }
    }

    public ReversedLinesFileReader(File file) throws IOException {
        this(file, 4096, Charset.defaultCharset().toString());
    }

    public ReversedLinesFileReader(File file, int i, String str) throws IOException {
        int i2;
        Charset charsetForName;
        this.trailingNewlineOfFileSkipped = false;
        this.blockSize = i;
        this.encoding = str;
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        this.randomAccessFile = randomAccessFile;
        long length = randomAccessFile.length();
        this.totalByteLength = length;
        long j = i;
        int i3 = (int) (length % j);
        if (i3 <= 0) {
            this.totalBlockCount = length / j;
            i2 = length > 0 ? i : i2;
            this.currentFilePart = new FilePart(this.totalBlockCount, i2, null);
            charsetForName = Charset.forName(str);
            if (charsetForName.newEncoder().maxBytesPerChar() != 1.0f || charsetForName == Charset.forName("UTF-8") || charsetForName == Charset.forName("Shift_JIS")) {
                this.byteDecrement = 1;
            } else {
                if (charsetForName == Charset.forName("UTF-16BE") && charsetForName != Charset.forName("UTF-16LE")) {
                    if (charsetForName == Charset.forName("UTF-16")) {
                        throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
                    }
                    throw new UnsupportedEncodingException("Encoding " + str + " is not supported yet (feel free to submit a patch)");
                }
                this.byteDecrement = 2;
            }
            byte[][] bArr = {"\r\n".getBytes(str), "\n".getBytes(str), "\r".getBytes(str)};
            this.newLineSequences = bArr;
            this.avoidNewlineSplitBufferSize = bArr[0].length;
        }
        this.totalBlockCount = (length / j) + 1;
        i2 = i3;
        this.currentFilePart = new FilePart(this.totalBlockCount, i2, null);
        charsetForName = Charset.forName(str);
        if (charsetForName.newEncoder().maxBytesPerChar() != 1.0f) {
            this.byteDecrement = 1;
        } else {
            if (charsetForName == Charset.forName("UTF-16BE")) {
            }
            this.byteDecrement = 2;
        }
        byte[][] bArr2 = {"\r\n".getBytes(str), "\n".getBytes(str), "\r".getBytes(str)};
        this.newLineSequences = bArr2;
        this.avoidNewlineSplitBufferSize = bArr2[0].length;
    }

    @Override
    public void close() throws IOException {
        this.randomAccessFile.close();
    }

    public String readLine() throws IOException {
        String line = this.currentFilePart.readLine();
        while (line == null) {
            FilePart filePartRollOver = this.currentFilePart.rollOver();
            this.currentFilePart = filePartRollOver;
            if (filePartRollOver == null) {
                break;
            }
            line = filePartRollOver.readLine();
        }
        if (!"".equals(line) || this.trailingNewlineOfFileSkipped) {
            return line;
        }
        this.trailingNewlineOfFileSkipped = true;
        return readLine();
    }
}
