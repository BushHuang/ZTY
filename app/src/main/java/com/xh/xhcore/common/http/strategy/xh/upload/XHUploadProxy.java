package com.xh.xhcore.common.http.strategy.xh.upload;

import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.strategy.BaseRequest;
import com.xh.xhcore.common.http.strategy.okhttp.upload.UploadOkHttp;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import com.xh.xhcore.common.http.strategy.xh.upload.XHUploadProxy;

public abstract class XHUploadProxy<T extends XHUploadProxy, R extends BaseRequest> extends XHBaseRequestProxy<T, R, XHRequestCallBack.XHUploadCallBack> {
    public static final String TAG = "XHUploadProxy";

    XHUploadProxy(R r) {
        super(r);
    }

    public static XHUploadOkHttpProxy createOkHttp() {
        return new XHUploadOkHttpProxy(new UploadOkHttp());
    }

    public static XHUploadOkHttpProxy createOkHttp(UploadOkHttp uploadOkHttp) {
        return new XHUploadOkHttpProxy(uploadOkHttp);
    }
}
