package com.xh.xhcore.common.upload;

import com.xh.xhcore.common.http.XHRequestCallBack;

public interface IRequest {
    XHTask execute();

    XHTask execute(XHRequestCallBack.HttpCallBack httpCallBack);
}
