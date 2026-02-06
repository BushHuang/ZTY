package com.xh.xhcore.common.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class XhRequestObject {
    private Object mObject;

    public XhRequestObject(Object obj) {
        this.mObject = obj;
    }

    public String toDefaultString() throws UnsupportedEncodingException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("postData", "K=" + URLEncoder.encode(new Gson().toJson(this.mObject), "utf-8"));
        jsonObject.addProperty("contentType", "application/x-www-form-urlencoded; charset=UTF-8");
        return jsonObject.toString();
    }

    public String toKString() {
        return new Gson().toJson(this.mObject);
    }

    @Deprecated
    public String toString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("postData", "K=" + new Gson().toJson(this.mObject));
        jsonObject.addProperty("contentType", "application/x-www-form-urlencoded; charset=UTF-8");
        return jsonObject.toString();
    }
}
