package org.sevenzip4j.archive.coder;

import SevenZip.Compression.LZMA.Encoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LZMACoder implements Coder {
    private Encoder encoder = new Encoder();

    public Encoder getEncoder() {
        return this.encoder;
    }

    @Override
    public OutputStream getEncoderOutputStream(OutputStream outputStream) throws IOException {
        return new LZMAOutputStream(this.encoder, outputStream);
    }

    @Override
    public MethodID getMethodID() {
        return MethodID.k_LZMA;
    }

    @Override
    public byte[] getProperties() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.encoder.WriteCoderProperties(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
