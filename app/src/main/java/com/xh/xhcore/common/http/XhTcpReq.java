package com.xh.xhcore.common.http;

import com.xh.networkclient.NetworkCallback;
import com.xh.networkclient.TcpRequest;
import com.xh.xhcore.common.http.HttpReq;

public class XhTcpReq {
    public static int connect(String str, int i, String str2, final HttpReq.IRequestCallBack iRequestCallBack) {
        return TcpRequest.getInstance().connect(str, i, str2, new NetworkCallback() {
            @Override
            public void onEvent(int i2, int i3, String str3, String str4) {
                HttpReq.IRequestCallBack iRequestCallBack2 = iRequestCallBack;
                if (iRequestCallBack2 != null) {
                    iRequestCallBack2.onEvent(i2, i3, XHErrorCodeUtil.getErrorMsgInfo(i3, str3), str4);
                }
            }

            @Override
            public void onProgress(int i2, double d, double d2, String str3) {
                HttpReq.IRequestCallBack iRequestCallBack2 = iRequestCallBack;
                if (iRequestCallBack2 != null) {
                    iRequestCallBack2.onProgress(i2, d, d2, str3);
                }
            }

            @Override
            public void onRecvDate(int i2, byte[] bArr, int i3, String str3) {
                HttpReq.IRequestCallBack iRequestCallBack2 = iRequestCallBack;
                if (iRequestCallBack2 != null) {
                    iRequestCallBack2.onRecvDate(i2, bArr, i3, str3);
                }
            }
        });
    }

    public static int disConnect(int i) {
        return TcpRequest.getInstance().disConnect(i);
    }

    public static int send(int i, byte[] bArr, int i2, String str) {
        return TcpRequest.getInstance().send(i, bArr, i2, str);
    }
}
