package com.obs.services.internal;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import com.obs.services.internal.io.MayRepeatableInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class RepeatableRequestEntity extends RequestBody implements Closeable {
    private static final ILogger interfaceLog = LoggerBuilder.getLogger("com.obs.services.internal.RestStorageService");
    private long contentLength;
    private String contentType;
    private InputStream inputStream;
    private volatile long bytesWritten = 0;
    private final int writeBufferSize = 4096;

    public RepeatableRequestEntity(InputStream inputStream, String str, long j, ObsProperties obsProperties) {
        this.contentLength = -1L;
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        this.inputStream = inputStream;
        this.contentLength = j;
        this.contentType = str;
        if (!(inputStream instanceof MayRepeatableInputStream)) {
            this.inputStream = new MayRepeatableInputStream(inputStream, obsProperties.getIntProperty("httpclient.write-buffer-size", 8192));
        }
        this.inputStream.mark(0);
    }

    @Override
    public void close() throws IOException {
        InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @Override
    public long contentLength() throws IOException {
        return this.contentLength;
    }

    @Override
    public MediaType contentType() {
        String str = this.contentType;
        if (str == null) {
            str = "application/octet-stream";
        }
        return MediaType.parse(str);
    }

    public boolean isRepeatable() {
        InputStream inputStream = this.inputStream;
        return inputStream == null || inputStream.markSupported();
    }

    @Override
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.bytesWritten > 0) {
            this.inputStream.reset();
            this.bytesWritten = 0L;
        }
        writeToBIO(bufferedSink);
        if (interfaceLog.isInfoEnabled()) {
            interfaceLog.info((CharSequence) ("write data end, cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms"));
        }
    }

    protected void writeToBIO(BufferedSink bufferedSink) throws IOException {
        int i;
        byte[] bArr = new byte[4096];
        long j = this.contentLength;
        if (j < 0) {
            int i2 = this.inputStream.read(bArr);
            while (i2 != -1) {
                this.bytesWritten += i2;
                bufferedSink.write(bArr, 0, i2);
                i2 = this.inputStream.read(bArr);
            }
            return;
        }
        while (j > 0 && (i = this.inputStream.read(bArr, 0, (int) Math.min(4096L, j))) != -1) {
            bufferedSink.write(bArr, 0, i);
            long j2 = i;
            this.bytesWritten += j2;
            j -= j2;
        }
    }

    protected void writeToNIO(BufferedSink bufferedSink) throws IOException {
        int i;
        ReadableByteChannel readableByteChannelNewChannel = Channels.newChannel(this.inputStream);
        WritableByteChannel writableByteChannelNewChannel = Channels.newChannel(bufferedSink.outputStream());
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4096);
        long j = this.contentLength;
        if (j < 0) {
            for (int i2 = readableByteChannelNewChannel.read(byteBufferAllocate); i2 > 0; i2 = readableByteChannelNewChannel.read(byteBufferAllocate)) {
                byteBufferAllocate.flip();
                while (byteBufferAllocate.hasRemaining()) {
                    writableByteChannelNewChannel.write(byteBufferAllocate);
                }
                byteBufferAllocate.clear();
                this.bytesWritten += i2;
            }
            return;
        }
        while (j > 0 && (i = readableByteChannelNewChannel.read(byteBufferAllocate)) > 0) {
            byteBufferAllocate.position((int) Math.min(4096L, j));
            byteBufferAllocate.flip();
            while (byteBufferAllocate.hasRemaining()) {
                writableByteChannelNewChannel.write(byteBufferAllocate);
            }
            byteBufferAllocate.clear();
            long j2 = i;
            this.bytesWritten += j2;
            j -= j2;
        }
    }
}
