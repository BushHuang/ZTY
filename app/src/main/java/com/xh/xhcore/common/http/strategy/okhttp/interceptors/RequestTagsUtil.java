package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import android.text.TextUtils;
import com.xh.xhcore.common.extension.OkHttpExtensionKt;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import com.xh.xhcore.common.http.strategy.xh.config.AllowUploadNetworkError;
import com.xh.xhcore.common.http.strategy.xh.config.UserCancelNetwork;
import com.xh.xhcore.common.http.strategy.xh.config.msg.INetworkErrorMessage;
import com.xh.xhcore.common.http.strategy.xh.config.msg.ProxyErrorMessage;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.ReflectUtil;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.connection.StreamAllocation;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0007J\u001e\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u0018\u0010\f\u001a\n \r*\u0004\u0018\u00010\u000b0\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J+\u0010\u0010\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0013H\u0007¢\u0006\u0002\u0010\u0014J-\u0010\u0010\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0013H\u0007¢\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0007J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0007J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007J\u001c\u0010\u0017\u001a\u00020\u00182\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007J1\u0010\u001d\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00132\u0006\u0010\u001e\u001a\u0002H\u0011H\u0007¢\u0006\u0002\u0010\u001fJ1\u0010\u001d\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u00112\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00132\u0006\u0010\u001e\u001a\u0002H\u0011H\u0007¢\u0006\u0002\u0010 ¨\u0006!"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/interceptors/RequestTagsUtil;", "", "()V", "allowUploadNetworkErrorLogFromConfig", "", "xhBaseRequestProxy", "Lcom/xh/xhcore/common/http/strategy/xh/XHBaseRequestProxy;", "getExtraNetworkErrorMessageFromTag", "", "", "request", "Lokhttp3/Request;", "getOriginalRequest", "kotlin.jvm.PlatformType", "chain", "Lokhttp3/Interceptor$Chain;", "getTag", "TagType", "clazz", "Ljava/lang/Class;", "(Lokhttp3/Interceptor$Chain;Ljava/lang/Class;)Ljava/lang/Object;", "(Lokhttp3/Request;Ljava/lang/Class;)Ljava/lang/Object;", "isUserCancelNetwork", "putProxyMessageTag", "", "call", "Lokhttp3/Call;", "proxy", "Ljava/net/Proxy;", "putTag", "content", "(Lokhttp3/Interceptor$Chain;Ljava/lang/Class;Ljava/lang/Object;)V", "(Lokhttp3/Request;Ljava/lang/Class;Ljava/lang/Object;)V", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RequestTagsUtil {
    public static final RequestTagsUtil INSTANCE = new RequestTagsUtil();

    private RequestTagsUtil() {
    }

    @JvmStatic
    public static final boolean allowUploadNetworkErrorLogFromConfig(XHBaseRequestProxy<?, ?, ?> xhBaseRequestProxy) {
        Intrinsics.checkNotNullParameter(xhBaseRequestProxy, "xhBaseRequestProxy");
        AllowUploadNetworkError allowUploadNetworkError = (AllowUploadNetworkError) xhBaseRequestProxy.tag(AllowUploadNetworkError.class);
        if (allowUploadNetworkError == null) {
            return true;
        }
        return allowUploadNetworkError.getAllow();
    }

    @JvmStatic
    public static final Map<String, Object> getExtraNetworkErrorMessageFromTag(Request request) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (request != null) {
            try {
                Object field = ReflectUtil.getField(request, "tags");
                if (field == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<java.lang.Class<*>, kotlin.Any>");
                }
                for (Map.Entry entry : ((Map) field).entrySet()) {
                    if (entry.getValue() instanceof INetworkErrorMessage) {
                        String jsonStringFormat = JsonUtil.toJsonStringFormat(entry.getValue());
                        String str = jsonStringFormat;
                        if (!(str == null || str.length() == 0)) {
                            String simpleName = ((Class) entry.getKey()).getSimpleName();
                            Intrinsics.checkNotNullExpressionValue(simpleName, "entry.key.simpleName");
                            Intrinsics.checkNotNullExpressionValue(jsonStringFormat, "toJsonStringFormat");
                            linkedHashMap.put(simpleName, jsonStringFormat);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return linkedHashMap;
    }

    private final Request getOriginalRequest(Interceptor.Chain chain) {
        return chain.call().request();
    }

    @JvmStatic
    public static final <TagType> TagType getTag(Interceptor.Chain chain, Class<TagType> clazz) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (TagType) getTag(INSTANCE.getOriginalRequest(chain), clazz);
    }

    @JvmStatic
    public static final <TagType> TagType getTag(Request request, Class<TagType> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        if (request != null) {
            return (TagType) request.tag(clazz);
        }
        return null;
    }

    @JvmStatic
    public static final boolean isUserCancelNetwork(XHBaseRequestProxy<?, ?, ?> xhBaseRequestProxy) {
        Intrinsics.checkNotNullParameter(xhBaseRequestProxy, "xhBaseRequestProxy");
        UserCancelNetwork userCancelNetwork = (UserCancelNetwork) xhBaseRequestProxy.tag(UserCancelNetwork.class);
        if (userCancelNetwork == null) {
            return false;
        }
        return userCancelNetwork.getUserCancel();
    }

    @JvmStatic
    public static final void putProxyMessageTag(Call call) {
        if (call == null) {
            return;
        }
        try {
            StreamAllocation streamAllocation = OkHttpExtensionKt.getStreamAllocation(call);
            if (streamAllocation == null) {
                return;
            }
            Object field = ReflectUtil.getField(streamAllocation, "routeSelector");
            if (field == null) {
                throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.connection.RouteSelector");
            }
            Object field2 = ReflectUtil.getField((RouteSelector) field, "proxies");
            if (field2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<java.net.Proxy>");
            }
            List list = (List) field2;
            if (list.size() > 0) {
                putProxyMessageTag(call.request(), (Proxy) list.get(list.size() - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JvmStatic
    public static final void putProxyMessageTag(Interceptor.Chain chain, Proxy proxy) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        putProxyMessageTag(INSTANCE.getOriginalRequest(chain), proxy);
    }

    @JvmStatic
    public static final void putProxyMessageTag(Request request, Proxy proxy) {
        SocketAddress socketAddressAddress;
        String string = null;
        if ((proxy == null ? null : proxy.type()) != Proxy.Type.DIRECT) {
            if (proxy != null && (socketAddressAddress = proxy.address()) != null) {
                string = socketAddressAddress.toString();
            }
            if (TextUtils.isEmpty(string)) {
                return;
            }
            StringBuilder sb = new StringBuilder("代理地址: ");
            sb.append(string);
            Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder(\"代理地址: \").append(proxyAddress)");
            if (request != null) {
                String string2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "proxyMsgSb.toString()");
                putTag(request, (Class<ProxyErrorMessage>) ProxyErrorMessage.class, new ProxyErrorMessage(string2));
            }
        }
    }

    @JvmStatic
    public static final <TagType> void putTag(Interceptor.Chain chain, Class<TagType> clazz, TagType content) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Request originalRequest = INSTANCE.getOriginalRequest(chain);
        Intrinsics.checkNotNullExpressionValue(originalRequest, "getOriginalRequest(chain)");
        putTag(originalRequest, clazz, content);
    }

    @JvmStatic
    public static final <TagType> void putTag(Request request, Class<TagType> clazz, TagType content) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        try {
            Object field = ReflectUtil.getField(request, "tags");
            if (field == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<java.lang.Class<TagType of com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestTagsUtil.putTag>, TagType of com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestTagsUtil.putTag>");
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.putAll((Map) field);
            linkedHashMap.put(clazz, content);
            ReflectUtil.setField(request, "tags", linkedHashMap);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
