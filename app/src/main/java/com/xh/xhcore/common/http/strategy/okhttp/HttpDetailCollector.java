package com.xh.xhcore.common.http.strategy.okhttp;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0012\u0010\u0014\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0015\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/HttpDetailCollector;", "", "()V", "UTF8", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "level", "Lokhttp3/logging/HttpLoggingInterceptor$Level;", "bodyHasUnknownEncoding", "", "headers", "Lokhttp3/Headers;", "getHttpDetail", "Lorg/json/JSONObject;", "request", "Lokhttp3/Request;", "response", "Lokhttp3/Response;", "getHttpRequestDetail", "", "getHttpResponseDetail", "getResponseBodyStringBuilder", "Ljava/lang/StringBuilder;", "responseBody", "Lokhttp3/ResponseBody;", "isPlaintext", "buffer", "Lokio/Buffer;", "isPlaintext$xhcore_release", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HttpDetailCollector {
    public static final HttpDetailCollector INSTANCE = new HttpDetailCollector();
    private static volatile HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
    private static final Charset UTF8 = Charset.forName("UTF-8");

    private HttpDetailCollector() {
    }

    private final boolean bodyHasUnknownEncoding(Headers headers) {
        String str = headers.get("Content-Encoding");
        return (str == null || StringsKt.equals(str, "identity", true) || StringsKt.equals(str, "gzip", true)) ? false : true;
    }

    @JvmStatic
    public static final JSONObject getHttpDetail(Request request, Response response) throws JSONException {
        Intrinsics.checkNotNullParameter(request, "request");
        JSONObject jSONObjectPut = new JSONObject().put("request", getHttpRequestDetail(request)).put("response", getHttpResponseDetail(response));
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "JSONObject().put(\"reques…ResponseDetail(response))");
        return jSONObjectPut;
    }

    @JvmStatic
    public static final String getHttpRequestDetail(Request request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        StringBuilder sb = new StringBuilder();
        HttpLoggingInterceptor.Level level2 = level;
        boolean z = level2 == HttpLoggingInterceptor.Level.BODY;
        boolean z2 = z || level2 == HttpLoggingInterceptor.Level.HEADERS;
        RequestBody requestBodyBody = request.body();
        String str = "--> " + ((Object) request.method()) + ' ' + request.url();
        if (!z2 && requestBodyBody != null) {
            str = str + " (" + requestBodyBody.contentLength() + "-byte body)";
        }
        sb.append(str);
        sb.append("\r\n");
        if (z2) {
            if (requestBodyBody != null) {
                if (requestBodyBody.contentType() != null) {
                    sb.append(Intrinsics.stringPlus("Content-Type: ", requestBodyBody.contentType()));
                    sb.append("\r\n");
                }
                if (requestBodyBody.contentLength() != -1) {
                    sb.append(Intrinsics.stringPlus("Content-Length: ", Long.valueOf(requestBodyBody.contentLength())));
                    sb.append("\r\n");
                }
            }
            Headers headers = request.headers();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String strName = headers.name(i);
                if (!StringsKt.equals("Content-Type", strName, true) && !StringsKt.equals("Content-Length", strName, true)) {
                    sb.append(strName + ": " + ((Object) headers.value(i)));
                    sb.append("\r\n");
                }
            }
            if (!z || requestBodyBody == null) {
                sb.append(Intrinsics.stringPlus("--> END ", request.method()));
                sb.append("\r\n");
            } else {
                HttpDetailCollector httpDetailCollector = INSTANCE;
                Headers headers2 = request.headers();
                Intrinsics.checkNotNullExpressionValue(headers2, "request.headers()");
                if (httpDetailCollector.bodyHasUnknownEncoding(headers2)) {
                    sb.append("--> END " + ((Object) request.method()) + " (encoded body omitted)");
                    sb.append("\r\n");
                } else {
                    Buffer buffer = new Buffer();
                    requestBodyBody.writeTo(buffer);
                    Charset charset = UTF8;
                    MediaType mediaTypeContentType = requestBodyBody.contentType();
                    if (mediaTypeContentType != null) {
                        charset = mediaTypeContentType.charset(UTF8);
                    }
                    sb.append("");
                    sb.append("\r\n");
                    if (!INSTANCE.isPlaintext$xhcore_release(buffer) || charset == null) {
                        sb.append("--> END " + ((Object) request.method()) + " (binary " + requestBodyBody.contentLength() + "-byte body omitted)");
                        sb.append("\r\n");
                    } else {
                        sb.append(buffer.readString(charset));
                        sb.append("\r\n");
                        sb.append("--> END " + ((Object) request.method()) + " (" + requestBodyBody.contentLength() + "-byte body)");
                        sb.append("\r\n");
                    }
                }
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "requestDetailSb.toString()");
        return string;
    }

    @JvmStatic
    public static final String getHttpResponseDetail(Response response) {
        String str;
        if (response == null) {
            return "response is null";
        }
        StringBuilder sb = new StringBuilder();
        HttpLoggingInterceptor.Level level2 = level;
        boolean z = (level2 == HttpLoggingInterceptor.Level.BODY) || level2 == HttpLoggingInterceptor.Level.HEADERS;
        if (z) {
            Headers headers = response.headers();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                sb.append(headers.name(i) + ": " + ((Object) headers.value(i)));
                sb.append("\r\n");
            }
        }
        ResponseBody responseBodyBody = response.body();
        if (responseBodyBody != null) {
            long jContentLength = responseBodyBody.contentLength();
            String str2 = jContentLength != -1 ? jContentLength + "-byte" : "unknown-length";
            StringBuilder sb2 = new StringBuilder();
            sb2.append("<-- ");
            sb2.append(response.code());
            String strMessage = response.message();
            Intrinsics.checkNotNullExpressionValue(strMessage, "response.message()");
            if (strMessage.length() == 0) {
                str = "";
            } else {
                String strMessage2 = response.message();
                Intrinsics.checkNotNullExpressionValue(strMessage2, "response.message()");
                str = ' ' + strMessage2;
            }
            sb2.append(str);
            sb2.append(response.request().url());
            sb2.append(" (");
            sb2.append(z ? "" : ", " + str2 + " body");
            sb2.append(')');
            sb.append(sb2.toString());
            sb.append("\r\n");
            Headers headers2 = response.headers();
            Intrinsics.checkNotNullExpressionValue(headers2, "response.headers()");
            sb.append((CharSequence) getResponseBodyStringBuilder(headers2, responseBodyBody));
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "responseDetailSb.toString()");
        return string;
    }

    @JvmStatic
    public static final StringBuilder getResponseBodyStringBuilder(Headers headers, ResponseBody responseBody) throws Throwable {
        GzipSource gzipSource;
        Throwable th;
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        BufferedSource bufferedSourceSource = responseBody.source();
        bufferedSourceSource.request(Long.MAX_VALUE);
        Buffer buffer = bufferedSourceSource.buffer();
        String str = headers.get("Content-Encoding");
        if (str != null && StringsKt.equals("gzip", str, true)) {
            try {
                gzipSource = new GzipSource(buffer.clone());
            } catch (Throwable th2) {
                gzipSource = null;
                th = th2;
            }
            try {
                buffer = new Buffer();
                buffer.writeAll(gzipSource);
                gzipSource.close();
            } catch (Throwable th3) {
                th = th3;
                if (gzipSource != null) {
                    gzipSource.close();
                }
                throw th;
            }
        }
        Charset charset = UTF8;
        MediaType mediaTypeContentType = responseBody.contentType();
        if (mediaTypeContentType != null) {
            charset = mediaTypeContentType.charset(UTF8);
        }
        StringBuilder sb = new StringBuilder();
        if (responseBody.contentLength() != 0 && charset != null) {
            sb.append(buffer.clone().readString(charset));
        }
        return sb;
    }

    public final boolean isPlaintext$xhcore_release(Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            int i = 0;
            while (i < 16) {
                i++;
                if (buffer2.exhausted()) {
                    return true;
                }
                int utf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(utf8CodePoint) && !Character.isWhitespace(utf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }
}
