package com.analysys.visual;

import android.text.TextUtils;
import com.analysys.utils.AnsReflectUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ak {

    private final String f103a;
    private Class<?>[] b;
    private Object[] c;

    public ak(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("reflect_name", null);
        this.f103a = strOptString;
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("params_type");
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("params");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray2 == null) {
            return;
        }
        Class<?>[] clsArr = new Class[jSONArrayOptJSONArray.length()];
        this.b = clsArr;
        this.c = new Object[clsArr.length];
        int i = 0;
        while (true) {
            Class<?>[] clsArr2 = this.b;
            if (i >= clsArr2.length) {
                return;
            }
            clsArr2[i] = Class.forName(jSONArrayOptJSONArray.getString(i));
            this.c[i] = jSONArrayOptJSONArray2.get(i);
            i++;
        }
    }

    public Object a(Object obj) {
        Class<?>[] clsArr = this.b;
        return clsArr == null ? AnsReflectUtils.getField(obj, this.f103a) : AnsReflectUtils.invokeMethod(obj, this.f103a, clsArr, this.c);
    }
}
