package org.apache.commons.io.input;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.EndianUtils;

public class SwappedDataInputStream extends ProxyInputStream implements DataInput {
    public SwappedDataInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public boolean readBoolean() throws IOException {
        return readByte() != 0;
    }

    @Override
    public byte readByte() throws IOException {
        return (byte) this.in.read();
    }

    @Override
    public char readChar() throws IOException {
        return (char) readShort();
    }

    @Override
    public double readDouble() throws IOException {
        return EndianUtils.readSwappedDouble(this.in);
    }

    @Override
    public float readFloat() throws IOException {
        return EndianUtils.readSwappedFloat(this.in);
    }

    @Override
    public void readFully(byte[] bArr) throws IOException {
        readFully(bArr, 0, bArr.length);
    }

    @Override
    public void readFully(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i2;
        while (i3 > 0) {
            int i4 = read(bArr, (i + i2) - i3, i3);
            if (-1 == i4) {
                throw new EOFException();
            }
            i3 -= i4;
        }
    }

    @Override
    public int readInt() throws IOException {
        return EndianUtils.readSwappedInteger(this.in);
    }

    @Override
    public String readLine() throws IOException {
        throw new UnsupportedOperationException("Operation not supported: readLine()");
    }

    @Override
    public long readLong() throws IOException {
        return EndianUtils.readSwappedLong(this.in);
    }

    @Override
    public short readShort() throws IOException {
        return EndianUtils.readSwappedShort(this.in);
    }

    @Override
    public String readUTF() throws IOException {
        throw new UnsupportedOperationException("Operation not supported: readUTF()");
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return this.in.read();
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return EndianUtils.readSwappedUnsignedShort(this.in);
    }

    @Override
    public int skipBytes(int i) throws IOException {
        return (int) this.in.skip(i);
    }
}
