package com.analysys.visual;

import org.json.JSONObject;

public class t {
    public static u a(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("prop_name");
        String strOptString2 = jSONObject.optString("key", null);
        String strOptString3 = jSONObject.optString("prop_type", null);
        ak akVar = jSONObject.has("reflect_name") ? new ak(jSONObject) : null;
        Object objOpt = jSONObject.opt("value");
        String strOptString4 = jSONObject.optString("regex", null);
        char c = 65535;
        switch (strOptString.hashCode()) {
            case 3556653:
                if (strOptString.equals("text")) {
                    c = 0;
                    break;
                }
                break;
            case 94742904:
                if (strOptString.equals("class")) {
                    c = 1;
                    break;
                }
                break;
            case 1018147644:
                if (strOptString.equals("sib_position")) {
                    c = 2;
                    break;
                }
                break;
            case 1944512819:
                if (strOptString.equals("sib_item_type")) {
                    c = 3;
                    break;
                }
                break;
        }
        if (c == 0) {
            return new y(strOptString, strOptString2, strOptString3, akVar, objOpt, strOptString4);
        }
        if (c == 1) {
            return new v(strOptString, strOptString2, strOptString3, akVar, objOpt, strOptString4);
        }
        if (c == 2) {
            return new x(strOptString, strOptString2, strOptString3, akVar, objOpt, strOptString4);
        }
        if (c == 3) {
            return new w(strOptString, strOptString2, strOptString3, akVar, objOpt, strOptString4);
        }
        if (akVar != null) {
            return new u(strOptString, strOptString2, strOptString3, akVar, objOpt, strOptString4);
        }
        return null;
    }
}
