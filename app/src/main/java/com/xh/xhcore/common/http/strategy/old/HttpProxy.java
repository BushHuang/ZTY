package com.xh.xhcore.common.http.strategy.old;

import org.json.JSONObject;

public class HttpProxy implements IHttpHandler {
    private static IHttpHandler httpHandler;
    private static HttpProxy instance;

    private HttpProxy() {
    }

    public static HttpProxy getInstance() {
        if (instance == null) {
            synchronized (HttpProxy.class) {
                if (instance == null) {
                    instance = new HttpProxy();
                }
            }
        }
        return instance;
    }

    public static void init(IHttpHandler iHttpHandler) {
        httpHandler = iHttpHandler;
    }

    @Override
    public int download(int i, String str, String str2, JSONObject jSONObject) {
        return httpHandler.download(i, str, str2, jSONObject);
    }

    @Override
    public int request(int i, String str, JSONObject jSONObject) {
        return httpHandler.request(i, str, jSONObject);
    }

    @Override
    public int upload(int i, String str, String str2, JSONObject jSONObject) {
        return httpHandler.upload(i, str, str2, jSONObject);
    }
}
