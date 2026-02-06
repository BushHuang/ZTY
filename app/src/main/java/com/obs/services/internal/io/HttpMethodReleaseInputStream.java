package com.obs.services.internal.io;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Response;

public class HttpMethodReleaseInputStream extends InputStream implements InputStreamWrapper {
    private static final ILogger interfaceLog = LoggerBuilder.getLogger("com.obs.services.internal.RestStorageService");
    private Response httpResponse;
    private InputStream inputStream;
    private boolean alreadyReleased = false;
    private boolean underlyingStreamConsumed = false;

    public HttpMethodReleaseInputStream(Response response) {
        this.inputStream = null;
        this.httpResponse = null;
        this.httpResponse = response;
        try {
            try {
                this.inputStream = new InterruptableInputStream(response.body().byteStream());
            } catch (Exception unused) {
                this.inputStream = new ByteArrayInputStream(new byte[0]);
            }
        } catch (Exception unused2) {
            response.close();
            this.inputStream = new ByteArrayInputStream(new byte[0]);
        }
    }

    @Override
    public int available() throws IOException {
        try {
            return this.inputStream.available();
        } catch (IOException e) {
            try {
                releaseConnection();
            } catch (IOException unused) {
            }
            throw e;
        }
    }

    @Override
    public void close() throws IOException {
        if (!this.alreadyReleased) {
            releaseConnection();
        }
        this.inputStream.close();
    }

    protected void finalize() throws Throwable {
        if (!this.alreadyReleased) {
            if (interfaceLog.isWarnEnabled()) {
                interfaceLog.warn((CharSequence) "Attempting to release HttpMethod in finalize() as its response data stream has gone out of scope. This attempt will not always succeed and cannot be relied upon! Please ensure response data streams are always fully consumed or closed to avoid HTTP connection starvation.");
            }
            releaseConnection();
            if (interfaceLog.isWarnEnabled()) {
                interfaceLog.warn((CharSequence) "Successfully released HttpMethod in finalize(). You were lucky this time... Please ensure response data streams are always fully consumed or closed.");
            }
        }
        super.finalize();
    }

    public Response getHttpResponse() {
        return this.httpResponse;
    }

    @Override
    public InputStream getWrappedInputStream() {
        return this.inputStream;
    }

    @Override
    public int read() throws IOException {
        try {
            int i = this.inputStream.read();
            if (i == -1) {
                this.underlyingStreamConsumed = true;
                if (!this.alreadyReleased) {
                    releaseConnection();
                }
            }
            return i;
        } catch (IOException e) {
            try {
                releaseConnection();
            } catch (IOException unused) {
            }
            throw e;
        }
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            int i3 = this.inputStream.read(bArr, i, i2);
            if (i3 == -1) {
                this.underlyingStreamConsumed = true;
                if (!this.alreadyReleased) {
                    releaseConnection();
                }
            }
            return i3;
        } catch (IOException e) {
            try {
                releaseConnection();
            } catch (IOException unused) {
            }
            throw e;
        }
    }

    protected void releaseConnection() throws IOException {
        Response response;
        if (this.alreadyReleased) {
            return;
        }
        if (!this.underlyingStreamConsumed && (response = this.httpResponse) != null) {
            response.close();
        }
        this.alreadyReleased = true;
    }
}
