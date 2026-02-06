package com.xh.xhcore.common.http.strategy.old;

import org.json.JSONObject;

public interface ISyncHttpHandler<T> extends IHttpHandler {
    T requestSync(int i, String str, JSONObject jSONObject);
}
