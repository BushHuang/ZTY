package com.xh.xhcore.common.http.strategy.okhttp.request;

import android.text.TextUtils;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.exception.HttpUrlNullPointerException;
import com.xh.xhcore.common.util.ReflectUtil;
import com.xh.xhcore.common.util.XHEncodeUtil;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

final class RequestBuilder {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";
    private RequestBody body;
    private FormBody.Builder formBuilder;
    private boolean hasBody;
    private String method;
    private MultipartBody.Builder multipartBuilder;
    private final Request.Builder requestBuilder = new Request.Builder();
    private String url;
    private HttpUrl.Builder urlBuilder;

    RequestBuilder() {
    }

    private String addHttpPrefixIfUrlHaveNoProtocol(String str) {
        if (haveProtocol(str)) {
            return str;
        }
        LogUtils.d("not start with protocol inputUrl = " + str);
        return "http://" + str;
    }

    private HttpUrl buildHttpUrl() throws HttpUrlNullPointerException {
        String str = this.url;
        if (str == null) {
            throw new HttpUrlNullPointerException("url is null");
        }
        try {
            String strAddHttpPrefixIfUrlHaveNoProtocol = addHttpPrefixIfUrlHaveNoProtocol(str);
            this.url = strAddHttpPrefixIfUrlHaveNoProtocol;
            return HttpUrl.get(strAddHttpPrefixIfUrlHaveNoProtocol);
        } catch (IllegalArgumentException e) {
            throw new HttpUrlNullPointerException("httpUrl is error, url = " + this.url, e);
        }
    }

    private static String canonicalizeForPath(String str, boolean z) {
        int length = str.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt < 32 || iCodePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, 0, iCharCount);
                canonicalizeForPath(buffer, str, iCharCount, length, z);
                return buffer.readUtf8();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str;
    }

    private static void canonicalizeForPath(Buffer buffer, String str, int i, int i2, boolean z) {
        Buffer buffer2 = null;
        while (i < i2) {
            int iCodePointAt = str.codePointAt(i);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt < 32 || iCodePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    buffer2.writeUtf8CodePoint(iCodePointAt);
                    while (!buffer2.exhausted()) {
                        int i3 = buffer2.readByte() & 255;
                        buffer.writeByte(37);
                        buffer.writeByte((int) HEX_DIGITS[(i3 >> 4) & 15]);
                        buffer.writeByte((int) HEX_DIGITS[i3 & 15]);
                    }
                } else {
                    buffer.writeUtf8CodePoint(iCodePointAt);
                }
            }
            i += Character.charCount(iCodePointAt);
        }
    }

    private void createFormBuilder() {
        if (this.formBuilder == null) {
            this.formBuilder = new FormBody.Builder();
        }
    }

    private void createMultipartBuilder() {
        if (this.multipartBuilder == null) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            this.multipartBuilder = builder;
            builder.setType(MultipartBody.FORM);
        }
    }

    private static boolean haveProtocol(String str) {
        return !TextUtils.isEmpty(str) && str.contains("://");
    }

    void addFormDataPart(String str, String str2) {
        createMultipartBuilder();
        this.multipartBuilder.addFormDataPart(str, str2);
    }

    void addFormDataPart(String str, String str2, RequestBody requestBody) {
        createMultipartBuilder();
        this.multipartBuilder.addFormDataPart(str, str2, requestBody);
    }

    void addFormField(String str, String str2, boolean z) {
        createFormBuilder();
        if (z) {
            this.formBuilder.addEncoded(str, str2);
        } else {
            this.formBuilder.add(str, str2);
        }
    }

    void addHeader(String str, String str2) {
        this.requestBuilder.removeHeader(str);
        try {
            this.requestBuilder.addHeader(str, str2);
        } catch (IllegalArgumentException unused) {
            this.requestBuilder.addHeader(XHEncodeUtil.urlEncode(str), XHEncodeUtil.urlEncode(str2));
        }
    }

    void addPart(Headers headers, RequestBody requestBody) {
        createMultipartBuilder();
        this.multipartBuilder.addPart(headers, requestBody);
    }

    void addPart(MultipartBody.Part part) {
        createMultipartBuilder();
        this.multipartBuilder.addPart(part);
    }

    void addQueryParam(String str, String str2, boolean z) {
        HttpUrl.Builder builder = this.urlBuilder;
        if (builder == null) {
            LogUtils.e("you must call setUrl to init urlBuilder before addQueryParam");
        } else if (z) {
            builder.addEncodedQueryParameter(str, str2);
        } else {
            builder.addQueryParameter(str, str2);
        }
    }

    Request build() throws HttpUrlNullPointerException {
        HttpUrl.Builder builder = this.urlBuilder;
        HttpUrl httpUrlBuild = builder != null ? builder.build() : buildHttpUrl();
        RequestBody requestBodyCreate = this.body;
        if (requestBodyCreate == null) {
            FormBody.Builder builder2 = this.formBuilder;
            if (builder2 != null) {
                requestBodyCreate = builder2.build();
            } else {
                MultipartBody.Builder builder3 = this.multipartBuilder;
                if (builder3 != null) {
                    requestBodyCreate = builder3.build();
                } else if (this.hasBody) {
                    requestBodyCreate = RequestBody.create((MediaType) null, new byte[0]);
                }
            }
        }
        return this.requestBuilder.url(httpUrlBuild).method(this.method, requestBodyCreate).build();
    }

    RequestBuilder clear() {
        this.formBuilder = null;
        this.multipartBuilder = null;
        this.requestBuilder.headers(new Headers.Builder().build());
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean parseHasBody(String str) {
        char c;
        switch (str.hashCode()) {
            case -531492226:
                if (!str.equals("OPTIONS")) {
                    c = 65535;
                    break;
                } else {
                    c = 6;
                    break;
                }
            case 70454:
                if (str.equals("GET")) {
                    c = 1;
                    break;
                }
                break;
            case 79599:
                if (str.equals("PUT")) {
                    c = 5;
                    break;
                }
                break;
            case 2213344:
                if (str.equals("HEAD")) {
                    c = 2;
                    break;
                }
                break;
            case 2461856:
                if (str.equals("POST")) {
                    c = 4;
                    break;
                }
                break;
            case 75900968:
                if (str.equals("PATCH")) {
                    c = 3;
                    break;
                }
                break;
            case 2012838315:
                if (str.equals("DELETE")) {
                    c = 0;
                    break;
                }
                break;
        }
        return c == 3 || c == 4 || c == 5;
    }

    void setBody(RequestBody requestBody) {
        this.body = requestBody;
    }

    public RequestBuilder setMethod(String str) {
        this.method = str;
        this.hasBody = parseHasBody(str);
        return this;
    }

    public RequestBuilder setUrl(String str) throws HttpUrlNullPointerException {
        this.url = str;
        this.urlBuilder = buildHttpUrl().newBuilder();
        return this;
    }

    public <TagType> TagType tag(Class<? extends TagType> cls) {
        try {
            return cls.cast(((Map) ReflectUtil.getField(this.requestBuilder, "tags")).get(cls));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public <TagType> void tag(Class<? super TagType> cls, TagType tagtype) {
        this.requestBuilder.tag(cls, tagtype);
    }
}
