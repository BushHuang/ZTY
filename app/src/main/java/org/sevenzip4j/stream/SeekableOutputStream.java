package org.sevenzip4j.stream;

import java.io.IOException;
import java.io.OutputStream;

public abstract class SeekableOutputStream extends OutputStream {
    public abstract long getPosition() throws IOException;

    public abstract void setPosition(long j) throws IOException;

    @Override
    public abstract void write(int i) throws IOException;

    @Override
    public abstract void write(byte[] bArr, int i, int i2) throws IOException;
}
