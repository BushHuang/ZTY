package com.xh.xhcore.common.http.strategy.okhttp.download;

import com.xh.xhcore.common.http.strategy.okhttp.OkHttpClientManager;
import com.xh.xhcore.common.http.strategy.okhttp.download.BaseDownloadOkHttp;
import com.xh.xhcore.common.http.strategy.okhttp.request.BaseRequestOkHttp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class BaseDownloadOkHttp<T extends BaseDownloadOkHttp> extends BaseRequestOkHttp<T> {
    private String localPath;

    public BaseDownloadOkHttp() {
        setMethod("GET");
    }

    private static FileOutputStream openOutputStream(File file, boolean z) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        } else {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        }
        return new FileOutputStream(file, z);
    }

    public static File saveDownloadFile(Response response, String str) throws IOException {
        byte[] bArr = new byte[2048];
        File file = new File(str);
        FileOutputStream fileOutputStreamOpenOutputStream = openOutputStream(file, false);
        InputStream inputStreamByteStream = response.body().byteStream();
        while (true) {
            int i = inputStreamByteStream.read(bArr);
            if (i == -1) {
                break;
            }
            fileOutputStreamOpenOutputStream.write(bArr, 0, i);
        }
        fileOutputStreamOpenOutputStream.flush();
        if (inputStreamByteStream != null) {
            inputStreamByteStream.close();
        }
        if (fileOutputStreamOpenOutputStream != null) {
            fileOutputStreamOpenOutputStream.close();
        }
        return file;
    }

    @Override
    protected OkHttpClient getDefaultOkHttpClient() {
        return OkHttpClientManager.getInstance().getDefaultUploadDownloadClient();
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public T setLocalPath(String str) {
        this.localPath = str;
        return this;
    }
}
