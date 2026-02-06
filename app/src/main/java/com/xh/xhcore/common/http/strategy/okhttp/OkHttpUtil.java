package com.xh.xhcore.common.http.strategy.okhttp;

import com.xh.xhcore.common.util.JsonUtil;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.Request;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001a\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0004H\u0007J\u001e\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\f2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0012\u0010\r\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001c\u0010\u000f\u001a\u00020\u00042\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\fH\u0007¨\u0006\u0011"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/OkHttpUtil;", "", "()V", "getHeadersString", "", "headers", "Lokhttp3/Headers;", "getRequestHeader", "request", "Lokhttp3/Request;", "requestHeaderName", "getRequestHeadersMap", "", "getRequestHeadersString", "headersToMap", "mapToJsonString", "map", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OkHttpUtil {
    public static final OkHttpUtil INSTANCE = new OkHttpUtil();

    private OkHttpUtil() {
    }

    @JvmStatic
    public static final String getHeadersString(Headers headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        return mapToJsonString(headersToMap(headers));
    }

    @JvmStatic
    public static final String getRequestHeader(Request request, String requestHeaderName) {
        String str;
        Intrinsics.checkNotNullParameter(requestHeaderName, "requestHeaderName");
        return (request == null || (str = request.headers().get(requestHeaderName)) == null) ? "" : str;
    }

    @JvmStatic
    public static final Map<String, Object> getRequestHeadersMap(Request request) {
        if (request == null) {
            return new HashMap();
        }
        Headers headers = request.headers();
        Intrinsics.checkNotNullExpressionValue(headers, "request.headers()");
        return headersToMap(headers);
    }

    @JvmStatic
    public static final String getRequestHeadersString(Request request) {
        return mapToJsonString(getRequestHeadersMap(request));
    }

    @JvmStatic
    public static final Map<String, Object> headersToMap(Headers headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        int size = headers.size();
        HashMap map = new HashMap();
        for (int i = 0; i < size; i++) {
            map.put(headers.name(i), headers.value(i));
        }
        return map;
    }

    @JvmStatic
    public static final String mapToJsonString(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        String strJSONObjectToString = JsonUtil.JSONObjectToString(new JSONObject(map));
        Intrinsics.checkNotNullExpressionValue(strJSONObjectToString, "JSONObjectToString(JSONObject(map))");
        return strJSONObjectToString;
    }
}
