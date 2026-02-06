package com.xh.xhcore.common.http.strategy.okhttp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J6\u0010\u000b\u001a\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\u0010\r\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u000eH\u0096\u0002¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0010\u001a\u00028\u0000¢\u0006\u0002\u0010\u0011R\u0010\u0010\u0004\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/DynamicProxy;", "T", "", "Ljava/lang/reflect/InvocationHandler;", "realObject", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "getMethodParamsSize", "", "method", "Ljava/lang/reflect/Method;", "invoke", "proxy", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "newProxyInstance", "()Ljava/lang/Object;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DynamicProxy<T> implements InvocationHandler {
    private final T realObject;

    public DynamicProxy(T t) {
        Intrinsics.checkNotNullParameter(t, "realObject");
        this.realObject = t;
    }

    private final int getMethodParamsSize(Method method) {
        Class<?>[] parameterTypes;
        if (method == null || (parameterTypes = method.getParameterTypes()) == null) {
            return -1;
        }
        return parameterTypes.length;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (args == null) {
            if (method == null) {
                return null;
            }
            return method.invoke(this.realObject, new Object[0]);
        }
        switch (getMethodParamsSize(method)) {
            case 1:
                if (method == null) {
                    return null;
                }
                return method.invoke(this.realObject, args[0]);
            case 2:
                if (method == null) {
                    return null;
                }
                return method.invoke(this.realObject, args[0], args[1]);
            case 3:
                if (method == null) {
                    return null;
                }
                return method.invoke(this.realObject, args[0], args[1], args[2]);
            case 4:
                if (method == null) {
                    return null;
                }
                return method.invoke(this.realObject, args[0], args[1], args[2], args[3]);
            case 5:
                if (method == null) {
                    return null;
                }
                return method.invoke(this.realObject, args[0], args[1], args[2], args[3], args[4]);
            case 6:
                if (method == null) {
                    return null;
                }
                return method.invoke(this.realObject, args[0], args[1], args[2], args[3], args[5]);
            case 7:
                if (method == null) {
                    return null;
                }
                return method.invoke(this.realObject, args[0], args[1], args[2], args[3], args[5], args[6]);
            case 8:
                if (method == null) {
                    return null;
                }
                return method.invoke(this.realObject, args[0], args[1], args[2], args[3], args[5], args[6], args[7]);
            case 9:
                if (method == null) {
                    return null;
                }
                return method.invoke(this.realObject, args[0], args[1], args[2], args[3], args[5], args[6], args[7], args[8]);
            case 10:
                if (method == null) {
                    return null;
                }
                return method.invoke(this.realObject, args[0], args[1], args[2], args[3], args[5], args[6], args[7], args[8], args[9]);
            default:
                return null;
        }
    }

    public final T newProxyInstance() {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(), this.realObject.getClass().getInterfaces(), this);
    }
}
