package com.xh.xhcore.common.http.archi;

import com.google.gson.Gson;
import java.util.regex.Pattern;

public class CommonUtil {
    private static Gson gson = new Gson();

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean isIP(String str) {
        if (str.length() < 7 || str.length() > 15 || "".equals(str)) {
            return false;
        }
        return Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}").matcher(str).find();
    }

    public static <E> String objectToString(E e) {
        if (e != 0) {
            return e instanceof String ? (String) e : gson.toJson(e);
        }
        return null;
    }
}
