package com.xh.xhcore.common.http.strategy;

import java.util.Map;

public interface IRequest<T> {
    T addHeader(String str, Object obj);

    T cancel();

    String getUrl();

    boolean isCanceled();

    T setHeaders(Map<String, Object> map);

    T setIsAsync(boolean z);

    T setMethod(String str);

    T setUrl(String str);

    <TagType> TagType tag(Class<? extends TagType> cls);

    <TagType> T tag(Class<? super TagType> cls, TagType tagtype);
}
