package org.apache.commons.io.output;

import java.io.Writer;

public class NullWriter extends Writer {
    public static final NullWriter NULL_WRITER = new NullWriter();

    @Override
    public Writer append(char c) {
        return this;
    }

    @Override
    public Writer append(CharSequence charSequence) {
        return this;
    }

    @Override
    public Writer append(CharSequence charSequence, int i, int i2) {
        return this;
    }

    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    @Override
    public void write(int i) {
    }

    @Override
    public void write(String str) {
    }

    @Override
    public void write(String str, int i, int i2) {
    }

    @Override
    public void write(char[] cArr) {
    }

    @Override
    public void write(char[] cArr, int i, int i2) {
    }
}
