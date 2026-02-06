package com.analysys.visual;

import android.text.TextUtils;
import android.widget.AdapterView;
import com.analysys.process.SystemIds;
import com.analysys.ui.UniqueViewHelper;
import com.analysys.utils.ANSLog;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.visual.p;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    private static final List<p.b> i = Collections.emptyList();

    public List<p.b> f145a;
    public String b;
    public boolean c;
    public List<s> d;
    public int e;
    public String f;
    public String g;
    public int h;

    public e(List<p.b> list, String str) {
        this.e = -1;
        this.h = -1;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (TextUtils.equals(list.get(size).b, str)) {
                this.f145a = list.subList(0, size + 1);
                return;
            }
        }
    }

    e(JSONObject jSONObject, boolean z) {
        this.e = -1;
        this.h = -1;
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("new_path");
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("props_binding");
        this.g = jSONObject.optString("h5_path", null);
        this.b = jSONObject.optString("path", null);
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() != 0 && !z) {
            this.f145a = a(jSONArrayOptJSONArray);
        }
        if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() != 0) {
            this.d = new ArrayList();
            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                u uVarA = t.a(jSONArrayOptJSONArray2.getJSONObject(i2));
                if (uVarA != null) {
                    this.d.add(uVarA);
                }
            }
        }
        if (this.f145a == null && this.d == null && !z && !TextUtils.isEmpty(this.b)) {
            this.f145a = a(new JSONArray(this.b));
            this.c = true;
        }
        this.h = jSONObject.optInt("step", -1);
    }

    private Integer a(int i2, String str) {
        int iIdFromName;
        if (str != null) {
            iIdFromName = SystemIds.getInstance().idFromName(null, str);
            if (iIdFromName == -1) {
                ANSLog.w("Path element contains an id name not known to the system. No views will be matched.\nMake sure that you're not stripping your packages R class out with proguard.\nid name was \"" + str + "\"");
                return null;
            }
        } else {
            iIdFromName = -1;
        }
        if (-1 == iIdFromName || -1 == i2 || iIdFromName == i2) {
            return -1 != iIdFromName ? Integer.valueOf(iIdFromName) : Integer.valueOf(i2);
        }
        ANSLog.w("Path contains both a named and an explicit id, and they don't match. No views will be matched.");
        return null;
    }

    private List<p.b> a(JSONArray jSONArray) throws JSONException {
        int i2;
        int i3;
        ArrayList arrayList = new ArrayList();
        for (int length = jSONArray.length() - 1; length >= 0; length--) {
            JSONObject jSONObject = jSONArray.getJSONObject(length);
            String strOptString = jSONObject.optString("prefix", null);
            String strOptString2 = jSONObject.optString("view_class", null);
            int iOptInt = jSONObject.optInt("index", -1);
            String strOptString3 = jSONObject.optString("contentDescription", null);
            int iOptInt2 = jSONObject.optInt("id", -1);
            String strOptString4 = jSONObject.optString("mp_id_name", null);
            String strOptString5 = jSONObject.optString("tag", null);
            int iOptInt3 = jSONObject.optInt("row", -1);
            if ("shortest".equals(strOptString)) {
                i2 = 1;
            } else {
                if (strOptString != null) {
                    return i;
                }
                i2 = 0;
            }
            Integer numA = a(iOptInt2, strOptString4);
            if (numA == null) {
                return i;
            }
            arrayList.add(0, new p.b(i2, strOptString2, iOptInt, numA.intValue(), strOptString4, strOptString3, strOptString5, iOptInt3));
            if (this.e == -1 && iOptInt3 != -1) {
                this.e = iOptInt3;
            }
            boolean zIsExtendsFromUniqueClass = UniqueViewHelper.isExtendsFromUniqueClass(strOptString2, "unique.ViewPager");
            if (this.f == null && strOptString2 != null && (AnsReflectUtils.isSubClass((Class<?>) AdapterView.class, strOptString2) || UniqueViewHelper.isExtendsFromUniqueClass(strOptString2, "unique.RecyclerView") || zIsExtendsFromUniqueClass)) {
                this.f = strOptString2;
                i3 = 1;
                if (arrayList.size() > 1) {
                    ((p.b) arrayList.get(1)).c = 0;
                }
            } else {
                i3 = 1;
            }
            if (zIsExtendsFromUniqueClass && arrayList.size() > i3) {
                ((p.b) arrayList.get(0)).f153a = 2;
                ((p.b) arrayList.get(i3)).c = 0;
            }
        }
        return arrayList;
    }
}
