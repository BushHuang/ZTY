package org.sevenzip4j.stream;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.output.CountingOutputStream;
import org.sevenzip4j.archive.CRC;

public class CRCOutputStream extends CountingOutputStream {
    private CRC crc;

    public CRCOutputStream(OutputStream outputStream) {
        super(outputStream);
        CRC crc = new CRC();
        this.crc = crc;
        crc.Init();
    }

    public int getDigest() {
        return this.crc.GetDigest();
    }

    @Override
    public synchronized long resetByteCount() {
        this.crc.Init();
        return super.resetByteCount();
    }

    @Override
    public synchronized int resetCount() {
        this.crc.Init();
        return super.resetCount();
    }

    @Override
    public void write(int i) throws IOException {
        super.write(i);
        this.crc.UpdateByte(i);
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        super.write(bArr);
        this.crc.Update(bArr);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        super.write(bArr, i, i2);
        this.crc.Update(bArr, i, i2);
    }
}
