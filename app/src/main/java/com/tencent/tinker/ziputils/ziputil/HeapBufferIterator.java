package com.tencent.tinker.ziputils.ziputil;

import java.nio.ByteOrder;

public final class HeapBufferIterator extends BufferIterator {
    private final byte[] buffer;
    private final int byteCount;
    private final int offset;
    private final ByteOrder order;
    private int position;

    HeapBufferIterator(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        this.buffer = bArr;
        this.offset = i;
        this.byteCount = i2;
        this.order = byteOrder;
    }

    public static BufferIterator iterator(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        return new HeapBufferIterator(bArr, i, i2, byteOrder);
    }

    public byte readByte() {
        byte[] bArr = this.buffer;
        int i = this.offset;
        int i2 = this.position;
        byte b = bArr[i + i2];
        this.position = i2 + 1;
        return b;
    }

    public void readByteArray(byte[] bArr, int i, int i2) {
        System.arraycopy(this.buffer, this.offset + this.position, bArr, i, i2);
        this.position += i2;
    }

    @Override
    public int readInt() {
        int iPeekInt = Memory.peekInt(this.buffer, this.offset + this.position, this.order);
        this.position += 4;
        return iPeekInt;
    }

    @Override
    public short readShort() {
        short sPeekShort = Memory.peekShort(this.buffer, this.offset + this.position, this.order);
        this.position += 2;
        return sPeekShort;
    }

    @Override
    public void seek(int i) {
        this.position = i;
    }

    @Override
    public void skip(int i) {
        this.position += i;
    }
}
