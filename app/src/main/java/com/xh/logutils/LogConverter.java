package com.xh.logutils;

import android.text.TextUtils;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.HashMap;

public class LogConverter {
    static HashMap<String, Object> convertLogInfoToMap(final String str, final String str2) {
        String strSubstring;
        StringBuilder sb;
        try {
            HashMap<String, Object> map = new HashMap<>();
            if (TextUtils.isEmpty(str)) {
                return map;
            }
            String[] strArrSplit = str.split("\n");
            if (strArrSplit.length == 0) {
                return map;
            }
            String str3 = null;
            StringBuilder sb2 = new StringBuilder();
            int length = strArrSplit.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = true;
                    break;
                }
                String str4 = strArrSplit[i];
                if (!TextUtils.isEmpty(str4)) {
                    if (!str4.contains("=")) {
                        if (map.size() <= 0) {
                            break;
                        }
                        sb2.append("\n");
                        sb2.append(str4);
                        map.put(str3, sb2.toString());
                    } else {
                        if (str4.startsWith("=")) {
                            break;
                        }
                        if (str4.endsWith("=")) {
                            int iIndexOf = str4.indexOf("=");
                            strSubstring = str4.substring(0, iIndexOf);
                            String strSubstring2 = str4.substring(iIndexOf + 1);
                            map.put(strSubstring, strSubstring2);
                            sb = new StringBuilder(strSubstring2);
                        } else {
                            String[] strArrSplit2 = str4.split("=");
                            if (strArrSplit2.length != 2) {
                                int iIndexOf2 = str4.indexOf("=");
                                strSubstring = str4.substring(0, iIndexOf2);
                                String strSubstring3 = str4.substring(iIndexOf2 + 1);
                                map.put(strSubstring, strSubstring3);
                                sb = new StringBuilder(strSubstring3);
                            } else if (!TextUtils.isEmpty(strArrSplit2[0]) && !TextUtils.isEmpty(strArrSplit2[1])) {
                                map.put(strArrSplit2[0], strArrSplit2[1]);
                                str3 = strArrSplit2[0];
                                sb2 = new StringBuilder(strArrSplit2[1]);
                            }
                        }
                        str3 = strSubstring;
                        sb2 = sb;
                    }
                }
                i++;
            }
            return z ? map : new HashMap<String, Object>() {
                {
                    put(str2, str);
                }
            };
        } catch (Throwable th) {
            LogUtils.e(LogUtils.getStackTraceString(th));
            return new HashMap<String, Object>() {
                {
                    put(str2, str);
                }
            };
        }
    }
}
