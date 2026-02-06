package com.xh.xhcore.common.http;

import com.xh.xhcore.common.config.XmConfig;
import com.xh.xhcore.common.http.XHRequestCallBack;

public interface IMicroServer {
    String getActualIp(String str);

    void getMicroServiceIPListWithRESTFul(XHRequestCallBack.HttpCallBack httpCallBack);

    String getNextIp(String str);

    XmConfig getXmConfigInstance();

    boolean requestAgain(String str);
}
