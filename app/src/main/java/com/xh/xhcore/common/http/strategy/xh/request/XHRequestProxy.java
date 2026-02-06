package com.xh.xhcore.common.http.strategy.xh.request;

import android.text.TextUtils;
import com.xh.networkclient.HttpRequest;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.archi.CommonUtil;
import com.xh.xhcore.common.http.strategy.BaseRequest;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestSignUtil;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.ServerTypeTag;
import com.xh.xhcore.common.http.strategy.okhttp.request.RequestOkHttp;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestProxy;

public abstract class XHRequestProxy<T extends XHRequestProxy, R extends BaseRequest> extends XHBaseRequestProxy<T, R, XHRequestCallBack.HttpCallBack> {
    public static final int REQUEST_TYPE_KZ = 1;
    public static final int REQUEST_TYPE_RESTFUL = 0;
    private String bodyData;
    private String businessUrl;
    private int requestType;
    private String serverType;

    XHRequestProxy(R r) {
        super(r);
        this.requestType = 0;
    }

    public static XHRequestOkHttpProxy createOkHttp() {
        return new XHRequestOkHttpProxy(new RequestOkHttp());
    }

    public static XHRequestOkHttpProxy createOkHttp(RequestOkHttp requestOkHttp) {
        return new XHRequestOkHttpProxy(requestOkHttp);
    }

    public static boolean needCombineBodyDataIntoUrl(String str) {
        return RequestSignUtil.needCombineBodyDataIntoUrl(str);
    }

    @Deprecated
    public String getBodyData() {
        return this.bodyData;
    }

    public String getBusinessUrl() {
        return this.businessUrl;
    }

    public HttpRequest.RequestMethod getMethodToEnum() {
        String method = this.baseRequest.getMethod();
        if (TextUtils.isEmpty(method)) {
            return null;
        }
        return HttpRequest.RequestMethod.valueOf(method);
    }

    public String getServerType() {
        return this.serverType;
    }

    protected final boolean isRestful() {
        return this.requestType == 0;
    }

    @Deprecated
    public final T setBodyData(Object obj) {
        this.bodyData = CommonUtil.objectToString(obj);
        return this;
    }

    public T setBusinessUrl(String str) {
        this.businessUrl = str;
        return this;
    }

    public T setRequestType(int i) {
        this.requestType = i;
        return this;
    }

    public T setServerType(String str) {
        this.serverType = str;
        tag((Class<? super Class>) ServerTypeTag.class, (Class) new ServerTypeTag(str));
        return this;
    }

    @Override
    public T setUrl(String str) {
        this.businessUrl = str;
        return this;
    }
}
