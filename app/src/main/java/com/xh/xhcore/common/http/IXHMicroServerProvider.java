package com.xh.xhcore.common.http;

import com.xh.xhcore.common.http.XHRequestCallBack;

@Deprecated
public interface IXHMicroServerProvider {

    public enum MicroServerDataSource {
        DATA_SOURCE_REQUEST,
        DATA_SOURCE_PLATFORM
    }

    String getDescription();

    XHRequestCallBack.HttpCallBack getHttpCallback();

    MicroServerDataSource getMicroServerDataSource();

    boolean getMicroServerForceRestful();

    String getServiceType();
}
