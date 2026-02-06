package com.analysys;

import android.content.Context;
import android.text.TextUtils;
import com.analysys.process.AgentProcess;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class q {

    Context f73a;
    private final String b = "outer";
    private final String c = "value";
    private final String d = "valueType";

    static class a {

        public static q f74a = new q();
    }

    public static q a(Context context) {
        if (a.f74a.f73a == null && context != null) {
            a.f74a.f73a = context;
        }
        return a.f74a;
    }

    private Object a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        int iOptInt = jSONObject.optInt("valueType");
        String strOptString = jSONObject.optString("value");
        if (iOptInt == 0) {
            return b(strOptString);
        }
        if (iOptInt == 1) {
            return strOptString;
        }
        if (iOptInt == 2) {
            return str;
        }
        return null;
    }

    private Map<String, Object> a(Object obj) {
        if (obj != null) {
            return (Map) obj;
        }
        return null;
    }

    private JSONObject a(String str) {
        JSONObject jSONObjectB = w.b(AnalysysUtil.getContext());
        if (jSONObjectB == null) {
            return null;
        }
        return str.startsWith("$profile") ? jSONObjectB.optJSONObject("$profile") : jSONObjectB.optJSONObject(str);
    }

    private JSONObject a(String str, JSONObject jSONObject, Map<String, Object> map, long j) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("outer");
        HashMap map2 = new HashMap();
        if (jSONArrayOptJSONArray == null) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            String strOptString = jSONArrayOptJSONArray.optString(i);
            JSONObject jSONObjectOptJSONObject = w.b.optJSONObject(strOptString);
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(strOptString);
            if (jSONArrayOptJSONArray2 != null) {
                int length = jSONArrayOptJSONArray2.length();
                for (int i2 = 0; i2 < length; i2++) {
                    String strOptString2 = jSONArrayOptJSONArray2.optString(i2);
                    map2.put(strOptString2, a(jSONObjectOptJSONObject.optJSONObject(strOptString2), (String) null));
                }
                if (!CommonUtils.isEmpty(map2)) {
                    CommonUtils.clearEmptyValue(map2);
                    map.putAll(map2);
                    map2.clear();
                }
                jSONObject2.put(strOptString, new JSONObject(map));
            } else {
                CommonUtils.pushToJSON(jSONObject2, strOptString, a(jSONObjectOptJSONObject, str));
                jSONObject2.put("xwhen", j);
            }
        }
        return jSONObject2;
    }

    private void a(String str, Map<String, Object> map) {
        Map<String, Object> superProperty;
        if ("$alias".equals(str) || str.startsWith("$profile") || (superProperty = AgentProcess.getInstance().getSuperProperty()) == null || superProperty.size() <= 0) {
            return;
        }
        for (String str2 : superProperty.keySet()) {
            if (str2 != null && !map.containsKey(str2)) {
                map.put(str2, superProperty.get(str2));
            }
        }
    }

    private void a(Map<String, Object> map, Object obj) {
        Map<String, Object> mapA = a(obj);
        CommonUtils.clearEmptyValue(mapA);
        if (mapA != null) {
            map.putAll(mapA);
            mapA.clear();
        }
    }

    private Object b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return CommonUtils.reflexUtils(CommonUtils.getClassPath(str), CommonUtils.getMethod(str), new Class[]{Context.class}, this.f73a);
    }

    public JSONObject a(Object... objArr) {
        String strValueOf;
        if (objArr == null || this.f73a == null) {
            return null;
        }
        long jLongValue = ((Long) objArr[0]).longValue();
        String strValueOf2 = String.valueOf(objArr[1]);
        String strValueOf3 = String.valueOf(objArr[2]);
        Map<String, Object> mapA = a(objArr[3]);
        JSONObject jSONObjectA = a(strValueOf3);
        if (jSONObjectA == null) {
            return null;
        }
        if ("$track".equals(strValueOf3)) {
            strValueOf = String.valueOf(objArr[5]);
            if (!ad.a(strValueOf3, strValueOf)) {
                ah.a(strValueOf2, u.c());
            }
        } else {
            strValueOf = strValueOf3;
        }
        ad.a(strValueOf2, mapA);
        Map<String, Object> map = mapA == null ? new HashMap() : mapA;
        a(map, objArr[4]);
        a(strValueOf, map);
        return a(strValueOf, jSONObjectA, map, jLongValue);
    }
}
