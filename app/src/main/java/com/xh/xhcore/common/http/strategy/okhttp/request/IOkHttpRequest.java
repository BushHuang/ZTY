package com.xh.xhcore.common.http.strategy.okhttp.request;

import com.xh.xhcore.common.http.strategy.IRequest;
import java.util.List;
import java.util.Map;
import okhttp3.Interceptor;

public interface IOkHttpRequest<T> extends IRequest<T> {
    T addQueryParam(String str, String str2);

    T setConnectTimeout(long j);

    T setInterceptors(List<Interceptor> list);

    T setNetworkInterceptor(Interceptor interceptor);

    T setNetworkInterceptors(List<Interceptor> list);

    T setQueryParams(Map<String, String> map);

    T setReadTimeout(long j);

    T setRequestBody(Object obj);

    T setRequestBody(String str);

    T setRequestBody(String str, Object obj);

    T setRequestBody(String str, String str2);

    T setTimeout(long j);

    T setWriteTimeout(long j);
}
