package org.sevenzip4j.stream;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomFileOutputStream extends SeekableOutputStream {
    protected RandomAccessFile randomFile;
    protected boolean sync;

    public RandomFileOutputStream(File file) throws IOException {
        this(file, false);
    }

    public RandomFileOutputStream(File file, boolean z) throws IOException {
        File absoluteFile = file.getAbsoluteFile();
        File parentFile = absoluteFile.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        this.randomFile = new RandomAccessFile(absoluteFile, "rw");
        this.sync = z;
    }

    public RandomFileOutputStream(String str) throws IOException {
        this(str, false);
    }

    public RandomFileOutputStream(String str, boolean z) throws IOException {
        this(new File(str), z);
    }

    @Override
    public void close() throws IOException {
        this.randomFile.close();
    }

    @Override
    public void flush() throws IOException {
        if (this.sync) {
            this.randomFile.getFD().sync();
        }
    }

    public FileDescriptor getFD() throws IOException {
        return this.randomFile.getFD();
    }

    public long getFileSize() throws IOException {
        return this.randomFile.length();
    }

    @Override
    public long getPosition() throws IOException {
        return this.randomFile.getFilePointer();
    }

    public void setFileSize(long j) throws IOException {
        this.randomFile.setLength(j);
    }

    @Override
    public void setPosition(long j) throws IOException {
        this.randomFile.seek(j);
    }

    @Override
    public void write(int i) throws IOException {
        this.randomFile.write(i);
        if (this.sync) {
            this.randomFile.getFD().sync();
        }
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        this.randomFile.write(bArr);
        if (this.sync) {
            this.randomFile.getFD().sync();
        }
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.randomFile.write(bArr, i, i2);
        if (this.sync) {
            this.randomFile.getFD().sync();
        }
    }
}
