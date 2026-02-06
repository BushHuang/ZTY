package com.xh.xhcore.common.http.strategy.okhttp.upload;

import com.xh.xhcore.common.http.strategy.okhttp.OkHttpClientManager;
import com.xh.xhcore.common.http.strategy.okhttp.ProgressRequestBody;
import com.xh.xhcore.common.http.strategy.okhttp.request.BaseRequestOkHttp;
import com.xh.xhcore.common.http.strategy.okhttp.upload.BaseUploadOkHttp;
import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class BaseUploadOkHttp<T extends BaseUploadOkHttp> extends BaseRequestOkHttp<T> {
    private OkHttpClientManager.RequestBodyWrapperFactory requestBodyWrapperFactory;

    public BaseUploadOkHttp() {
        setMethod("POST");
    }

    private String guessMimeType(String str) {
        String contentTypeFor;
        try {
            contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str);
        } catch (Exception unused) {
            contentTypeFor = null;
        }
        return contentTypeFor == null ? "application/octet-stream" : contentTypeFor;
    }

    public static File[] stringFilesToList(String[] strArr) {
        List listAsList = Arrays.asList(strArr);
        ArrayList arrayList = new ArrayList();
        Iterator it = listAsList.iterator();
        while (it.hasNext()) {
            arrayList.add(new File((String) it.next()));
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    private void wrapRequestBody() {
        this.requestBody = this.request.body();
        if (getRequestBodyWrapperFactory() != null) {
            this.requestBody = getRequestBodyWrapperFactory().createRequestBodyWrapper(this.requestBody);
            this.request = this.request.newBuilder().post(this.requestBody).build();
        }
    }

    @Override
    protected void callRequestAsync(OkHttpClient okHttpClient, Callback callback) {
        wrapRequestBody();
        super.callRequestAsync(okHttpClient, callback);
    }

    @Override
    protected void callRequestSync(OkHttpClient okHttpClient, Callback callback) {
        wrapRequestBody();
        super.callRequestSync(okHttpClient, callback);
    }

    @Override
    protected OkHttpClient getDefaultOkHttpClient() {
        return OkHttpClientManager.getInstance().getDefaultUploadDownloadClient();
    }

    @Override
    public String getRequestBodyString() {
        return "upload request body!";
    }

    public OkHttpClientManager.RequestBodyWrapperFactory getRequestBodyWrapperFactory() {
        return this.requestBodyWrapperFactory;
    }

    public T setFile(String str) {
        setFiles(new File(str));
        return this;
    }

    public T setFiles(File file) {
        setFiles(new File[]{file});
        return this;
    }

    public T setFiles(File[] fileArr) {
        clearFormDataPart();
        for (File file : fileArr) {
            String name = file.getName();
            addFormDataPart("files", name, RequestBody.create(MediaType.parse(guessMimeType(name)), file));
        }
        return this;
    }

    public T setFiles(String[] strArr) {
        setFiles(stringFilesToList(strArr));
        return this;
    }

    public T setProgressRequestListener(final ProgressRequestBody.ProgressRequestListener progressRequestListener) {
        setRequestBodyWrapperFactory(new OkHttpClientManager.RequestBodyWrapperFactory() {
            @Override
            public RequestBody createRequestBodyWrapper(RequestBody requestBody) {
                return new ProgressRequestBody(requestBody, progressRequestListener);
            }
        });
        return this;
    }

    public T setRequestBodyWrapperFactory(OkHttpClientManager.RequestBodyWrapperFactory requestBodyWrapperFactory) {
        this.requestBodyWrapperFactory = requestBodyWrapperFactory;
        return this;
    }
}
