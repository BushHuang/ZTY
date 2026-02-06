package com.analysys;

import android.text.TextUtils;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;

public class ai {
    public static void a(Object obj) {
        try {
            String strValueOf = String.valueOf(obj);
            if (!b(strValueOf)) {
                u.a(201, "[ " + e(strValueOf) + "] does not conform to naming rules!");
                return;
            }
            if (99 >= strValueOf.length()) {
                u.a(200);
                return;
            }
            u.a(201, "The length of the property value string [" + e(strValueOf) + "] needs to be 1-99!");
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private static void a(List<Object> list) {
        if (list.size() > 100) {
            u.a(201, " The length of the property value array needs to be 1-100!");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof String) {
                String strValueOf = String.valueOf(obj);
                if (!d(strValueOf)) {
                    c(strValueOf);
                    list.set(i, f(strValueOf));
                }
            } else {
                u.a(203, "Property value invalid, support type: String/Number/boolean/String collection/String array!");
            }
        }
    }

    private static void a(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (jSONArray.opt(i) instanceof Number) {
                u.a(201, "Property value invalid, support type: String/Number/boolean/String collection/String array!");
                return;
            }
        }
    }

    private static boolean a(int i) {
        return 100 >= ((long) i);
    }

    private static boolean a(String str) {
        if (w.e == null) {
            return true;
        }
        JSONArray jSONArray = w.e;
        for (int i = 0; i < jSONArray.length(); i++) {
            if (str.equals(jSONArray.optString(i))) {
                return false;
            }
        }
        return true;
    }

    public static void b(Object obj) {
        try {
            if (CommonUtils.isEmpty(obj)) {
                u.a(201, " Key can not be empty!");
                return;
            }
            String strValueOf = String.valueOf(obj);
            if (99 < strValueOf.length()) {
                u.a(203, "The length of the property key string [" + e(strValueOf) + "] needs to be 1-99!");
                return;
            }
            if (!b(strValueOf)) {
                u.a(202, "[ " + e(strValueOf) + "] does not conform to naming rules!");
                return;
            }
            if (a(strValueOf)) {
                return;
            }
            u.a(203, "[ " + e(strValueOf) + " ] is a reserved field!");
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private static boolean b(String str) {
        if (str != null) {
            return Pattern.compile("^(^[$a-zA-Z][$a-zA-Z0-9_]{0,})$").matcher(str).matches();
        }
        return false;
    }

    public static void c(Object obj) {
        try {
            if (CommonUtils.isEmpty(obj)) {
                u.a(201, " Value can not be empty!");
                return;
            }
            if (!(obj instanceof Number) && !(obj instanceof Boolean)) {
                if (obj instanceof String) {
                    String strValueOf = String.valueOf(obj);
                    if (d(strValueOf)) {
                        return;
                    }
                    c(strValueOf);
                    u.a(f(strValueOf));
                    return;
                }
                if (obj.getClass().isArray()) {
                    d(obj);
                    return;
                }
                if (obj instanceof List) {
                    a((List<Object>) obj);
                } else if (obj instanceof JSONArray) {
                    a((JSONArray) obj);
                } else {
                    u.a(201, "Property value invalid, support type: String/Number/boolean/String collection/String array!");
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private static void c(String str) {
        u.a(202, "The length of the property value string [" + e(str) + "] needs to be 1-255!");
    }

    private static void d(Object obj) {
        if (!(obj instanceof String[])) {
            u.a(201, "Property value invalid, support type: String/Number/boolean/String collection/String array!");
            return;
        }
        String[] strArr = (String[]) obj;
        if (!a(strArr.length)) {
            u.a(201, " The length of the property value array needs to be 1-100!");
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (!d(str)) {
                c(str);
                strArr[i] = f(str);
            }
        }
    }

    private static boolean d(String str) {
        return TextUtils.isEmpty(str) || 255 >= str.length();
    }

    private static String e(String str) {
        if (CommonUtils.isEmpty(str) || 30 >= str.length()) {
            return str;
        }
        return str.substring(0, 30) + "....";
    }

    private static String f(String str) {
        if (str == null || 8192 >= str.length()) {
            return str;
        }
        return str.substring(0, 8191) + "$";
    }
}
