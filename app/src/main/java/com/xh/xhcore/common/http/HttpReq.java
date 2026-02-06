package com.xh.xhcore.common.http;

import com.xh.networkclient.HttpRequest;
import com.xh.networkclient.NetworkCallback;
import com.xh.xhcore.common.http.archi.xh.TransformCodeArchCallback;
import com.xh.xhcore.common.http.archi.xh.TransformDescriptionArchCallback;
import com.xh.xhcore.common.upload.XHTask;

@Deprecated
public class HttpReq {

    public interface IRequestCallBack extends NetworkCallback {
        @Override
        void onEvent(int i, int i2, String str, String str2);

        @Override
        void onProgress(int i, double d, double d2, String str);

        @Override
        void onRecvDate(int i, byte[] bArr, int i2, String str);
    }

    public static int download(String str, String str2, String str3, IRequestCallBack iRequestCallBack) {
        return HttpRequest.getInstance().download(str, str2, str3, new TransformDescriptionArchCallback(iRequestCallBack));
    }

    public static int download(String str, String[] strArr, String str2, String str3, IRequestCallBack iRequestCallBack) {
        return HttpRequest.getInstance().download(str, strArr, str2, str3, new TransformDescriptionArchCallback(iRequestCallBack));
    }

    public static int reqWithKZ(String str, String str2, int i, IRequestCallBack iRequestCallBack) {
        return HttpRequest.getInstance().requestWithKZ(str, str2, i, new TransformCodeArchCallback(str, iRequestCallBack));
    }

    public static int reqWithKZ(String str, String str2, String str3, int i, String str4, IRequestCallBack iRequestCallBack) {
        return HttpRequest.getInstance().requestWithKZ(str, str2, str3, i, str4, new TransformCodeArchCallback(str, iRequestCallBack));
    }

    @Deprecated
    public static int request(String str, String str2, IRequestCallBack iRequestCallBack) {
        return HttpRequest.getInstance().request(str, str2, new TransformDescriptionArchCallback(iRequestCallBack));
    }

    public static int request(String str, String str2, String str3, IRequestCallBack iRequestCallBack) {
        return HttpRequest.getInstance().request(str, str2, str3, new TransformDescriptionArchCallback(iRequestCallBack));
    }

    public static int requestWithRESTFul(String str, HttpRequest.RequestMethod requestMethod, String str2, String str3, IRequestCallBack iRequestCallBack) {
        return HttpRequest.getInstance().requestWithRESTful(str, requestMethod, str2, str3, new TransformCodeArchCallback(str, iRequestCallBack));
    }

    public static int requestWithRESTFul(String str, HttpRequest.RequestMethod requestMethod, String str2, String str3, String str4, IRequestCallBack iRequestCallBack) {
        return HttpRequest.getInstance().requestWithRESTful(str, requestMethod, str2, str3, str4, new TransformCodeArchCallback(str, iRequestCallBack));
    }

    protected static int stop(XHTask xHTask) {
        return HttpRequest.getInstance().stop(xHTask.getXhTaskId());
    }

    public static int upload(String str, String str2, String str3, IRequestCallBack iRequestCallBack) {
        return HttpRequest.getInstance().upload(str, str2, str3, new TransformDescriptionArchCallback(iRequestCallBack));
    }

    public static int upload(String str, String[] strArr, String str2, IRequestCallBack iRequestCallBack) {
        return HttpRequest.getInstance().upload(str, strArr, str2, new TransformDescriptionArchCallback(iRequestCallBack));
    }
}
