package com.huawei.hms.hem;

import java.util.HashMap;
import java.util.Map;

public final class e {

    private static final Map<Integer, String> f337a;

    static {
        HashMap map = new HashMap();
        f337a = map;
        map.put(1000, "internal error");
        f337a.put(1001, "network error");
        f337a.put(1002, "param error");
        f337a.put(1003, "licenseKey invalid");
        f337a.put(1004, "licenseKey expired");
        f337a.put(1005, "licenseKey unauthorized");
        f337a.put(1006, "license check failed");
        f337a.put(2000, "license activate success");
        f337a.put(2001, "license activate failed");
        f337a.put(3000, "license de-activate success");
        f337a.put(3001, "license de-activate failed");
    }

    public static String a(int i) {
        String str = f337a.get(Integer.valueOf(i));
        return str == null ? "" : str;
    }
}
