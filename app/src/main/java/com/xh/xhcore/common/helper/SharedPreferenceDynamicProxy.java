package com.xh.xhcore.common.helper;

import android.content.SharedPreferences;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.okhttp.DynamicProxy;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J6\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\u0010\u000b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\fH\u0096\u0002¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/xh/xhcore/common/helper/SharedPreferenceDynamicProxy;", "Lcom/xh/xhcore/common/http/strategy/okhttp/DynamicProxy;", "Landroid/content/SharedPreferences;", "spFileName", "", "(Ljava/lang/String;)V", "invoke", "", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SharedPreferenceDynamicProxy extends DynamicProxy<SharedPreferences> {
    public SharedPreferenceDynamicProxy(String str) {
        Intrinsics.checkNotNullParameter(str, "spFileName");
        SharedPreferences sharedPreferences = XhBaseApplication.getXhBaseApplication().getSharedPreferences(str, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getXhBaseApplication().g…me, Context.MODE_PRIVATE)");
        super(sharedPreferences);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object objInvoke = super.invoke(proxy, method, args);
        LogUtils.INSTANCE.d("proxy = " + proxy + ", method = " + method + ", args = " + args + ", result = " + objInvoke);
        return objInvoke;
    }
}
