package com.analysys;

import android.text.TextUtils;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ad {
    private static int a(String str, Object obj, JSONArray jSONArray) {
        if (jSONArray == null) {
            return 202;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            String strOptString = jSONArray.optString(i);
            CommonUtils.reflexUtils(CommonUtils.getClassPath(strOptString), CommonUtils.getMethod(strOptString), new Class[]{Object.class}, obj);
        }
        return u.a();
    }

    public static JSONObject a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return jSONObject;
        }
        String strOptString = jSONObject.optString("appid");
        long jOptLong = jSONObject.optLong("xwhen");
        String strOptString2 = jSONObject.optString("xwho");
        String strOptString3 = jSONObject.optString("xwhat");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("xcontext");
        if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString.trim()) || jOptLong == 0 || TextUtils.isEmpty(strOptString2) || TextUtils.isEmpty(strOptString2.trim()) || TextUtils.isEmpty(strOptString3) || TextUtils.isEmpty(strOptString3.trim()) || CommonUtils.isEmpty(jSONObjectOptJSONObject)) {
            return null;
        }
        return jSONObject;
    }

    public static void a(Map<String, Object> map, String str, String str2) {
        try {
            if (CommonUtils.isEmpty(str) || CommonUtils.isEmpty(str2)) {
                return;
            }
            map.put(str, str2);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public static boolean a(String str) {
        if (!CommonUtils.isEmpty(str) && str.length() <= 255) {
            return true;
        }
        u.a(201, " The length of the id needs to be 1-255!");
        return false;
    }

    public static boolean a(String str, String str2) {
        if (w.b == null) {
            return false;
        }
        JSONObject jSONObjectOptJSONObject = w.b.optJSONObject(str);
        if (CommonUtils.isEmpty(jSONObjectOptJSONObject) || jSONObjectOptJSONObject == null) {
            return true;
        }
        JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("checkFuncList");
        if (CommonUtils.isEmpty(jSONArrayOptJSONArray)) {
            return true;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            String strOptString = jSONArrayOptJSONArray.optString(i);
            CommonUtils.reflexUtils(CommonUtils.getClassPath(strOptString), CommonUtils.getMethod(strOptString), new Class[]{Object.class}, str2);
            if (u.a() != 200) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(String str, Map<String, Object> map) {
        if (map == null) {
            return true;
        }
        u.d();
        if (w.g != 0 && w.g < map.size()) {
            u.a(201, " The length of the property key-value pair needs to be 1-100!");
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            Object obj = map.get(next);
            if (next == null) {
                it.remove();
                u.a(201, " Key can not be empty!");
            } else if (201 != a(str, next, w.c) && 201 != a(str, obj, w.d) && u.a() == 202 && !TextUtils.isEmpty(u.b())) {
                map.put(next, u.b());
                u.a((String) null);
            }
        }
        if ("profileUnset".equals(str)) {
            u.a(200, null);
        }
        if (u.a() == 200) {
            return true;
        }
        ah.a(str, u.c());
        return true;
    }

    public static boolean b(String str) {
        if (str.length() <= 255) {
            return true;
        }
        u.a(201, " The length of the id needs to be 1-255!");
        return false;
    }
}
