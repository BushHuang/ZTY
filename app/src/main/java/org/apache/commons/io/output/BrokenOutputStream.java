package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class BrokenOutputStream extends OutputStream {
    private final IOException exception;

    public BrokenOutputStream() {
        this(new IOException("Broken output stream"));
    }

    public BrokenOutputStream(IOException iOException) {
        this.exception = iOException;
    }

    @Override
    public void close() throws IOException {
        throw this.exception;
    }

    @Override
    public void flush() throws IOException {
        throw this.exception;
    }

    @Override
    public void write(int i) throws IOException {
        throw this.exception;
    }
}
