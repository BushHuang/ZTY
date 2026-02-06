package com.xh.xhcore.common.http.strategy.xh.callback;

import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.strategy.LogUtils;

public class BaseXHHttpCallback<T> extends XHRequestCallBack.HttpCallBack<T> {
    @Override
    public void failed(int i, String str) {
        LogUtils.d("请求失败 code = " + i + " msg = " + str);
    }

    @Override
    public void success(T t) {
        LogUtils.d("请求成功 t = " + t);
    }
}
