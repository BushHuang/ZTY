package com.analysys;

import android.content.Context;
import android.text.TextUtils;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class w {

    public static JSONObject f157a;
    public static JSONObject b;
    public static JSONArray c;
    public static JSONArray d;
    public static JSONArray e;
    public static JSONObject f;
    public static int g;

    public static void a(Context context) throws JSONException {
        if (b == null) {
            f157a = c(context);
            JSONObject jSONObjectD = d(context);
            b = jSONObjectD;
            if (f157a == null || jSONObjectD == null) {
                ah.a("SDK template loading exception.");
                return;
            }
            JSONObject jSONObjectOptJSONObject = jSONObjectD.optJSONObject("xcontext");
            c = jSONObjectOptJSONObject.optJSONObject("contextKey").getJSONArray("checkFuncList");
            d = jSONObjectOptJSONObject.optJSONObject("contextValue").getJSONArray("checkFuncList");
            e = b.optJSONArray("ReservedKeywords");
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("additional");
            f = jSONObjectOptJSONObject2;
            if (jSONObjectOptJSONObject2 != null) {
                g = jSONObjectOptJSONObject2.optInt("userKeysLimit");
            }
        }
    }

    private static void a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null || jSONArray2 == null) {
            return;
        }
        for (int i = 0; i < jSONArray2.length(); i++) {
            jSONArray.put(jSONArray2.optString(i));
        }
    }

    private static void a(JSONObject jSONObject) throws JSONException {
        if (CommonUtils.isEmpty(jSONObject)) {
            return;
        }
        Iterator<String> itKeys = jSONObject.keys();
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("base");
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (!"base".equals(next)) {
                b(jSONObject.optJSONObject(next), jSONObjectOptJSONObject);
            }
        }
        jSONObject.remove("base");
    }

    private static void a(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject2 != null) {
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (!TextUtils.isEmpty(next)) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(next);
                    if (jSONObjectOptJSONObject != null) {
                        b(jSONObjectOptJSONObject, jSONObject2.optJSONObject(next));
                    } else {
                        jSONObject.put(next, jSONObject2.optJSONObject(next));
                    }
                }
            }
        }
    }

    public static JSONObject b(Context context) {
        if (f157a == null) {
            try {
                a(context);
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return f157a;
    }

    private static void b(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("outer");
        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("outer");
        if (jSONArrayOptJSONArray == null) {
            jSONObject.put("outer", jSONArrayOptJSONArray2);
        } else {
            a(jSONArrayOptJSONArray, jSONArrayOptJSONArray2);
        }
        JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("xcontext");
        JSONArray jSONArrayOptJSONArray4 = jSONObject2.optJSONArray("xcontext");
        if (jSONArrayOptJSONArray3 == null) {
            jSONObject.put("xcontext", jSONArrayOptJSONArray4);
        } else {
            a(jSONArrayOptJSONArray3, jSONArrayOptJSONArray4);
        }
    }

    private static JSONObject c(Context context) throws JSONException {
        String mould = CommonUtils.getMould(context, "AnsFieldsTemplate.json");
        String mould2 = CommonUtils.getMould(context, "CustomerFieldsTemplate.json");
        JSONObject jSONObject = !CommonUtils.isEmpty(mould) ? new JSONObject(mould) : null;
        if (!TextUtils.isEmpty(mould2)) {
            a(jSONObject, new JSONObject(mould2));
        }
        a(jSONObject);
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    private static void c(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject2 == null || jSONObject == null) {
            return;
        }
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            jSONObject.put(next, jSONObject2.optJSONObject(next));
        }
    }

    private static JSONObject d(Context context) throws JSONException {
        String mould = CommonUtils.getMould(context, "AnsRuleTemplate.json");
        String mould2 = CommonUtils.getMould(context, "CustomerRuleTemplate.json");
        if (CommonUtils.isEmpty(mould)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(mould);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("ReservedKeywords");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("xcontext");
        if (CommonUtils.isEmpty(mould2)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(mould2);
        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("ReservedKeywords");
        JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("xcontext");
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray2 != null) {
            a(jSONArrayOptJSONArray, jSONArrayOptJSONArray2);
            jSONObject2.remove("ReservedKeywords");
        }
        if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject2 != null) {
            c(jSONObjectOptJSONObject, jSONObjectOptJSONObject2);
            jSONObject2.remove("xcontext");
        }
        c(jSONObject, jSONObject2);
        return jSONObject;
    }
}
