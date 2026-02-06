package com.fasterxml.jackson.core.io;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class DataOutputAsStream extends OutputStream {
    protected final DataOutput _output;

    public DataOutputAsStream(DataOutput dataOutput) {
        this._output = dataOutput;
    }

    @Override
    public void write(int i) throws IOException {
        this._output.write(i);
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        this._output.write(bArr, 0, bArr.length);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this._output.write(bArr, i, i2);
    }
}
