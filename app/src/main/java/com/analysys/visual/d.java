package com.analysys.visual;

import android.text.TextUtils;
import com.analysys.ui.WindowUIHelper;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;
import com.analysys.visual.i;
import com.analysys.visual.i.a;
import com.analysys.visual.i.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {

    private static String f144a;

    public static i.c a(JSONObject jSONObject) {
        i.c cVar = new i.c();
        cVar.b = new e(jSONObject.getJSONObject("target"), false);
        if (cVar.b.g == null) {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("properties");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() != 0) {
                cVar.c = new ArrayList();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    u uVarA = t.a(jSONArrayOptJSONArray.getJSONObject(i));
                    if (uVarA != null) {
                        cVar.c.add(uVarA);
                    }
                }
            }
        } else {
            cVar.d = jSONObject.toString();
        }
        return cVar;
    }

    private static i a(String str, e eVar, boolean z) {
        if (z) {
            return new o(str);
        }
        if (eVar.f145a != null && eVar.f != null) {
            return new m(str, 1);
        }
        char c = 65535;
        if (str.hashCode() == 94750088 && str.equals("click")) {
            c = 0;
        }
        if (c != 0) {
            return null;
        }
        return new n(str, 1);
    }

    public static i a(String str, JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("event_type");
        boolean z = jSONObject.optInt("is_hybrid", 0) == 1;
        e eVar = new e(jSONObject, z);
        i iVarA = a(string, eVar, z);
        if (iVarA == null) {
            return null;
        }
        iVarA.g = eVar;
        iVarA.m = jSONObject.toString();
        iVarA.getClass();
        iVarA.f = iVarA.new a();
        if (jSONObject.has("binding_range")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("binding_range");
            if (!b(jSONObject2)) {
                return null;
            }
            JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("pages");
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
                iVarA.f.f147a = true;
            } else {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    iVarA.f.b.add(jSONArrayOptJSONArray.getString(i));
                }
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("dialogs");
            if (jSONArrayOptJSONArray2 == null || jSONArrayOptJSONArray2.length() == 0) {
                iVarA.f.c = iVarA.f.f147a;
                for (String str2 : iVarA.f.b) {
                    if (str2 == null || !str2.contains("(dialog)")) {
                        iVarA.f.d.add(WindowUIHelper.getDialogName(str2));
                    } else {
                        iVarA.f.d.add(str2);
                    }
                }
            } else {
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    iVarA.f.d.add(jSONArrayOptJSONArray2.getString(i2));
                }
            }
            JSONArray jSONArrayOptJSONArray3 = jSONObject2.optJSONArray("floatwins");
            if (jSONArrayOptJSONArray3 == null || jSONArrayOptJSONArray3.length() == 0) {
                iVarA.f.e = iVarA.f.f147a;
                for (String str3 : iVarA.f.b) {
                    if (str3 == null || !str3.contains("(float)")) {
                        iVarA.f.f.add(WindowUIHelper.getFloatWindowName(str3));
                    } else {
                        iVarA.f.f.add(str3);
                    }
                }
            } else {
                for (int i3 = 0; i3 < jSONArrayOptJSONArray3.length(); i3++) {
                    iVarA.f.f.add(jSONArrayOptJSONArray3.getString(i3));
                }
            }
            JSONArray jSONArrayOptJSONArray4 = jSONObject2.optJSONArray("popwins");
            if (jSONArrayOptJSONArray4 == null || jSONArrayOptJSONArray4.length() == 0) {
                iVarA.f.g = iVarA.f.f147a;
                for (String str4 : iVarA.f.b) {
                    if (str4 == null || !str4.contains("(popup)")) {
                        iVarA.f.h.add(WindowUIHelper.getPopupWindowName(str4));
                    } else {
                        iVarA.f.h.add(str4);
                    }
                }
            } else {
                for (int i4 = 0; i4 < jSONArrayOptJSONArray4.length(); i4++) {
                    iVarA.f.h.add(jSONArrayOptJSONArray4.getString(i4));
                }
            }
        } else {
            String strOptString = jSONObject.optString("target_page");
            if (TextUtils.isEmpty(strOptString)) {
                iVarA.f.f147a = true;
            } else {
                int iIndexOf = strOptString.indexOf(WindowUIHelper.getDialogName(null));
                if (iIndexOf == 0) {
                    iVarA.f.c = true;
                } else if (iIndexOf > 0) {
                    iVarA.f.d.add(strOptString);
                } else {
                    int iIndexOf2 = strOptString.indexOf(WindowUIHelper.getFloatWindowName(null));
                    if (iIndexOf2 == 0) {
                        iVarA.f.e = true;
                    } else if (iIndexOf2 > 0) {
                        iVarA.f.f.add(strOptString);
                    } else {
                        int iIndexOf3 = strOptString.indexOf(WindowUIHelper.getPopupWindowName(null));
                        if (iIndexOf3 == 0) {
                            iVarA.f.g = true;
                        } else if (iIndexOf3 > 0) {
                            iVarA.f.h.add(strOptString);
                        } else {
                            iVarA.f.b.add(strOptString);
                        }
                    }
                }
            }
            String strOptString2 = jSONObject.optString("app_version");
            if (!TextUtils.isEmpty(strOptString2) && !a(strOptString2)) {
                return null;
            }
        }
        if (jSONObject.has("delete")) {
            str = "delete";
        }
        iVarA.c = str;
        iVarA.f146a = jSONObject.optInt("id", -1);
        iVarA.b = jSONObject.getString("event_id");
        if (z) {
            return iVarA;
        }
        if (iVarA.g.f != null) {
            iVarA.h = new e(iVarA.g.f145a, iVarA.g.f);
            if (iVarA.g.e != -1) {
                a(iVarA, iVarA.g, new x("sib_position", null, "number", null, Integer.valueOf(iVarA.g.e), null));
            }
        }
        iVarA.i = new ArrayList();
        iVarA.i.add(new l());
        JSONArray jSONArrayOptJSONArray5 = jSONObject.optJSONArray("properties");
        if (jSONArrayOptJSONArray5 != null && jSONArrayOptJSONArray5.length() != 0) {
            iVarA.j = new ArrayList();
            for (int i5 = 0; i5 < jSONArrayOptJSONArray5.length(); i5++) {
                u uVarA = t.a(jSONArrayOptJSONArray5.getJSONObject(i5));
                if (uVarA != null) {
                    iVarA.j.add(uVarA);
                }
            }
        }
        JSONArray jSONArrayOptJSONArray6 = jSONObject.optJSONArray("related");
        if (jSONArrayOptJSONArray6 != null && jSONArrayOptJSONArray6.length() != 0) {
            iVarA.k = new ArrayList();
            for (int i6 = 0; i6 < jSONArrayOptJSONArray6.length(); i6++) {
                iVarA.k.add(a(jSONArrayOptJSONArray6.getJSONObject(i6)));
            }
        }
        String strOptString3 = jSONObject.optString("match_text", null);
        if (!TextUtils.isEmpty(strOptString3)) {
            a(iVarA, iVarA.g, new y("text", null, "string", null, strOptString3, null));
        }
        if (iVarA.g.d != null) {
            List<s> list = iVarA.g.d;
            int size = list.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                s sVar = list.get(size);
                if (TextUtils.equals(sVar.a(), "sib_position")) {
                    a(iVarA, iVarA.g, sVar);
                    list.remove(size);
                    break;
                }
                size--;
            }
        }
        return iVarA;
    }

    private static void a(i iVar, e eVar, s sVar) {
        if (iVar.l == null) {
            iVar.l = new ArrayList();
        }
        iVar.getClass();
        i.b bVar = iVar.new b();
        bVar.b = eVar;
        bVar.c = new ArrayList();
        bVar.c.add(sVar);
        iVar.l.add(bVar);
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(f144a)) {
            f144a = CommonUtils.getVersionName(AnalysysUtil.getContext());
        }
        return TextUtils.equals(str, f144a);
    }

    private static boolean b(JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("versions");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
            return true;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            if (a(jSONArrayOptJSONArray.getString(i))) {
                return true;
            }
        }
        return false;
    }
}
