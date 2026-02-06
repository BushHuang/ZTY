package com.xh.xhcore.common.http.strategy.old;

import org.json.JSONObject;

public interface IHttpHandler {
    int download(int i, String str, String str2, JSONObject jSONObject);

    int request(int i, String str, JSONObject jSONObject);

    int upload(int i, String str, String str2, JSONObject jSONObject);
}
