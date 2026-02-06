package com.xh.xhcore.common.http.strategy.okhttp;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J6\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\u0010\n\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0006\u0018\u00010\u000bH\u0096\u0002¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/BufferedSinkDynamicProxy;", "Lcom/xh/xhcore/common/http/strategy/okhttp/DynamicProxy;", "Lokio/BufferedSink;", "realBufferedSink", "(Lokio/BufferedSink;)V", "invoke", "", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BufferedSinkDynamicProxy extends DynamicProxy<BufferedSink> {
    public BufferedSinkDynamicProxy(BufferedSink bufferedSink) {
        super(bufferedSink);
        Intrinsics.checkNotNullParameter(bufferedSink, "realBufferedSink");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        return super.invoke(proxy, method, args);
    }
}
