package com.xh.networkclient;

import com.xh.networkclient.common.CommonUtils;

public interface NetworkCallback {
    public static final int NETWORK_SUCCESS = CommonUtils.baseCode;

    void onEvent(int i, int i2, String str, String str2);

    void onProgress(int i, double d, double d2, String str);

    void onRecvDate(int i, byte[] bArr, int i2, String str);
}
