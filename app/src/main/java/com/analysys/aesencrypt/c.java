package com.analysys.aesencrypt;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class c {

    static String f14a;
    static int b;

    static class a {

        public static final c f15a = new c();
    }

    public static c a() {
        return a.f15a;
    }

    private static String a(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        try {
            if (i % 2 != 0) {
                str = new StringBuilder(str).reverse().toString();
            }
            StringBuffer stringBuffer = new StringBuffer();
            if (i2 % 2 != 0) {
                for (int i3 = 0; i3 < str.length(); i3 = i3 + 1 + 1) {
                    stringBuffer.append(str.charAt(i3));
                }
            } else {
                int i4 = 0;
                while (i4 < str.length()) {
                    int i5 = i4 + 1;
                    stringBuffer.append(str.charAt(i5));
                    i4 = i5 + 1;
                }
            }
            if (stringBuffer.length() >= 16) {
                return stringBuffer.substring(0, 16);
            }
            for (int length = str.length() - 1; length > 0; length--) {
                stringBuffer.append(str.charAt(length));
                if (stringBuffer.length() == 16) {
                    return stringBuffer.toString();
                }
            }
            return String.valueOf(stringBuffer);
        } catch (Exception unused) {
            return null;
        }
    }

    private static String a(String str, String str2, String str3, String str4) {
        try {
            String strA = b.a(com.analysys.aesencrypt.a.a(MessageDigest.getInstance("MD5").digest((str + str2 + str3 + str4).getBytes("utf-8"))).getBytes());
            String[] strArrSplit = str3.split("\\.");
            int length = strArrSplit.length;
            if (length <= 2) {
                return "";
            }
            return a(strA, Integer.parseInt(strArrSplit[length - 2]), Integer.parseInt(strArrSplit[length - 1]));
        } catch (Throwable unused) {
            return null;
        }
    }

    private String c() {
        if (f14a == null) {
            f14a = String.valueOf(System.currentTimeMillis());
        }
        return f14a;
    }

    public String a(String str, String str2, String str3, int i) {
        try {
            String strA = a("Android", str, str2, c());
            if (strA == null) {
                return null;
            }
            b = i;
            return i == 2 ? com.analysys.aesencrypt.a.b(strA, str3) : i == 1 ? com.analysys.aesencrypt.a.a(str3, strA) : null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public Map<String, String> b() {
        HashMap map = new HashMap();
        map.put("reqt", c());
        map.put("reqv", String.valueOf(b));
        return map;
    }
}
