package org.sevenzip4j.archive.coder;

import java.io.IOException;
import java.io.OutputStream;

public interface Coder {
    OutputStream getEncoderOutputStream(OutputStream outputStream) throws IOException;

    MethodID getMethodID();

    byte[] getProperties() throws IOException;
}
