package org.sevenzip4j.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SeekableByteArrayOutputStream extends SeekableOutputStream {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private List<byte[]> buffers;
    private long count;
    private byte[] currentBuffer;
    private int currentBufferIndex;
    private long filledBufferSum;
    private long filledBufferSumTillPosition;
    private int filledBuffersCount;
    private long position;

    public SeekableByteArrayOutputStream() {
        this(1024);
    }

    public SeekableByteArrayOutputStream(int i) {
        this.buffers = new ArrayList();
        if (i >= 0) {
            needNewBuffer(i);
        } else {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
    }

    private byte[] getBuffer(int i) {
        return this.buffers.get(i);
    }

    private void needNewBuffer(long j) {
        int iMax;
        int i = this.currentBufferIndex;
        if (i < this.filledBuffersCount - 1) {
            long length = this.filledBufferSumTillPosition + this.currentBuffer.length;
            this.filledBufferSumTillPosition = length;
            if (length > this.filledBufferSum) {
                this.filledBufferSum = length;
            }
            int i2 = this.currentBufferIndex + 1;
            this.currentBufferIndex = i2;
            this.currentBuffer = getBuffer(i2);
            return;
        }
        if (i < this.buffers.size() - 1) {
            long length2 = this.filledBufferSumTillPosition + this.currentBuffer.length;
            this.filledBufferSumTillPosition = length2;
            if (length2 > this.filledBufferSum) {
                this.filledBufferSum = length2;
            }
            int i3 = this.currentBufferIndex + 1;
            this.currentBufferIndex = i3;
            this.filledBuffersCount++;
            this.currentBuffer = getBuffer(i3);
            return;
        }
        byte[] bArr = this.currentBuffer;
        if (bArr == null) {
            iMax = (int) j;
            this.filledBufferSum = 0L;
            this.filledBufferSumTillPosition = 0L;
            this.position = 0L;
        } else {
            iMax = Math.max(bArr.length << 1, (int) (j - this.filledBufferSum));
            long length3 = this.filledBufferSum + this.currentBuffer.length;
            this.filledBufferSum = length3;
            this.filledBufferSumTillPosition = length3;
        }
        this.currentBufferIndex++;
        this.filledBuffersCount++;
        byte[] bArr2 = new byte[iMax];
        this.currentBuffer = bArr2;
        this.buffers.add(bArr2);
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public long getPosition() {
        return this.position;
    }

    public synchronized void reset() {
        this.count = 0L;
        this.filledBufferSum = 0L;
        this.position = 0L;
        this.filledBuffersCount = 0;
        this.currentBufferIndex = 0;
        this.currentBuffer = getBuffer(0);
    }

    @Override
    public void setPosition(long j) {
        long length = 0;
        if (j < 0) {
            throw new IllegalArgumentException("negative position");
        }
        if (j >= this.count) {
            throw new IndexOutOfBoundsException("position >= count");
        }
        byte[] buffer = this.currentBuffer;
        int i = 0;
        for (int i2 = 0; i2 < this.filledBuffersCount; i2++) {
            buffer = getBuffer(i2);
            if (j <= buffer.length + length) {
                break;
            }
            length += buffer.length;
            i++;
        }
        this.currentBuffer = buffer;
        this.currentBufferIndex = i;
        this.filledBufferSumTillPosition = length;
        this.position = j;
    }

    public synchronized long size() {
        return this.count;
    }

    public synchronized byte[] toByteArray() {
        int i = (int) this.count;
        if (i == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 < this.buffers.size(); i3++) {
            byte[] buffer = getBuffer(i3);
            int iMin = Math.min(buffer.length, i);
            System.arraycopy(buffer, 0, bArr, i2, iMin);
            i2 += iMin;
            i -= iMin;
            if (i == 0) {
                break;
            }
        }
        return bArr;
    }

    public String toString() {
        return new String(toByteArray());
    }

    public String toString(String str) throws UnsupportedEncodingException {
        return new String(toByteArray(), str);
    }

    public synchronized int write(InputStream inputStream) throws IOException {
        int i;
        int i2 = (int) (this.position - this.filledBufferSumTillPosition);
        int i3 = inputStream.read(this.currentBuffer, i2, this.currentBuffer.length - i2);
        i = 0;
        while (i3 != -1) {
            i += i3;
            i2 += i3;
            if (this.position == this.count) {
                this.position += i3;
            }
            this.position += i3;
            if (i2 == this.currentBuffer.length) {
                needNewBuffer(this.currentBuffer.length);
                i2 = 0;
            }
            i3 = inputStream.read(this.currentBuffer, i2, this.currentBuffer.length - i2);
        }
        return i;
    }

    @Override
    public synchronized void write(int i) {
        int i2 = (int) (this.position - this.filledBufferSumTillPosition);
        if (i2 == this.currentBuffer.length) {
            needNewBuffer(this.position + 1);
            i2 = 0;
        }
        this.currentBuffer[i2] = (byte) i;
        long j = this.position + 1;
        this.position = j;
        if (j > this.count) {
            this.count = j;
        }
    }

    @Override
    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return;
        }
        synchronized (this) {
            long j = this.position + i2;
            int i4 = (int) (this.position - this.filledBufferSumTillPosition);
            while (i2 > 0) {
                int iMin = Math.min(i2, this.currentBuffer.length - i4);
                System.arraycopy(bArr, i3 - i2, this.currentBuffer, i4, iMin);
                i2 -= iMin;
                if (i2 > 0) {
                    needNewBuffer(j);
                    i4 = 0;
                }
            }
            this.position = j;
            if (j > this.count) {
                this.count = j;
            }
        }
    }

    public synchronized void writeTo(OutputStream outputStream) throws IOException {
        long j = this.count;
        for (int i = 0; i < this.buffers.size(); i++) {
            byte[] buffer = getBuffer(i);
            long jMin = Math.min(buffer.length, j);
            outputStream.write(buffer, 0, (int) jMin);
            j -= jMin;
            if (j == 0) {
                break;
            }
        }
    }
}
