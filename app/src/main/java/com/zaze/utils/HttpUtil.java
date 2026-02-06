package com.zaze.utils;

import android.text.TextUtils;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    public static final String URL = "url";

    public static String buildGetRequest(String str, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : map.keySet()) {
            if (sb.length() != 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(str2));
            sb.append("=");
            sb.append(URLEncoder.encode(ZStringUtil.parseString(map.get(str2))));
        }
        return str.contains("?") ? ZStringUtil.format("%s&%s", str, sb.toString()) : ZStringUtil.format("%s?%s", str, sb.toString());
    }

    public static Map<String, String> processGetRequest(String str) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            String[] strArrSplit = str.split("\\?");
            if (strArrSplit.length == 2) {
                map.put("url", strArrSplit[0]);
                for (String str2 : strArrSplit[1].split("&")) {
                    String[] strArrSplit2 = str2.split("=");
                    if (strArrSplit2.length == 2) {
                        map.put(strArrSplit2[0], strArrSplit2[1]);
                    }
                }
            }
        }
        return map;
    }
}
