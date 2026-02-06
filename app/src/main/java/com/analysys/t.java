package com.analysys;

import android.content.Context;
import android.text.TextUtils;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import org.json.JSONObject;

public class t {

    public static JSONObject f79a;
    public static JSONObject b;
    public static JSONObject c;
    public static JSONObject d;
    public static JSONObject e;
    public static JSONObject f;
    public static JSONObject g;
    public static JSONObject h;
    static JSONObject i;

    public static void a(Context context) {
        try {
            if (i == null) {
                i = new JSONObject(CommonUtils.getMould(context, "LifeCycleConfig.json"));
            }
            if (i != null) {
                f79a = i.optJSONObject("Upload");
                b = i.optJSONObject("Encrypt");
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public static void b(Context context) {
        if (i == null) {
            String mould = CommonUtils.getMould(context, "LifeCycleConfig.json");
            if (!TextUtils.isEmpty(mould)) {
                i = new JSONObject(mould);
            }
        }
        JSONObject jSONObject = i;
        if (jSONObject != null) {
            c = jSONObject.optJSONObject("VisualBase");
            d = i.optJSONObject("Visual");
            e = i.optJSONObject("VisualConfig");
        }
    }

    public static void c(Context context) {
        if (i == null) {
            i = new JSONObject(CommonUtils.getMould(context, "LifeCycleConfig.json"));
        }
        JSONObject jSONObject = i;
        if (jSONObject != null) {
            f = jSONObject.optJSONObject("PushParse");
            g = i.optJSONObject("PushClick");
        }
    }

    public static void d(Context context) {
        if (i == null) {
            i = new JSONObject(CommonUtils.getMould(context, "LifeCycleConfig.json"));
        }
        JSONObject jSONObject = i;
        if (jSONObject != null) {
            h = jSONObject.optJSONObject("Probe");
        }
    }
}
