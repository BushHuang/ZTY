package com.zaze.utils;

import com.zaze.utils.log.ZLog;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalyzeUtil {
    private static boolean needLog = false;

    protected static String[] analyzeFileByLine(String str, String str2) {
        if (FileUtil.isCanRead(str)) {
            return FileUtil.readFromFile(str).toString().split(str2);
        }
        return null;
    }

    public static JSONArray analyzeFileFirstLineIsTag(String str, String str2, String str3) {
        return analyzeLineValueFirstLineIsTag(analyzeFileByLine(str, str2), str3);
    }

    public static JSONObject analyzeFileFirstValueIsTag(String str, String str2, String str3) {
        return analyzeLineValueFirstValueIsTag(analyzeFileByLine(str, str2), str3);
    }

    protected static JSONArray analyzeLineValueFirstLineIsTag(String[] strArr, String str) throws JSONException {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String[] strArrSplit = strArr[0].split(str);
        JSONArray jSONArray = new JSONArray();
        for (int i = 1; i < strArr.length; i++) {
            String[] strArrSplit2 = strArr[i].split(str);
            JSONObject jSONObject = new JSONObject();
            for (int i2 = 0; i2 < strArrSplit2.length; i2++) {
                if (i2 < strArrSplit.length) {
                    try {
                        jSONObject.put(strArrSplit[i2], strArrSplit2[i2]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            jSONArray.put(jSONObject);
        }
        if (needLog) {
            ZLog.i("Analyze[ZZ]", jSONArray.toString(), new Object[0]);
        }
        return jSONArray;
    }

    protected static JSONObject analyzeLineValueFirstValueIsTag(String[] strArr, String str) throws JSONException {
        int iEnd;
        int iStart;
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str2 : strArr) {
            Matcher matcher = Pattern.compile(str).matcher(str2);
            if (matcher.find()) {
                iStart = matcher.start();
                iEnd = matcher.end();
            } else {
                iEnd = 0;
                iStart = 0;
            }
            try {
                jSONObject.put(str2.substring(0, iStart), str2.substring(iEnd));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (needLog) {
            ZLog.i("Analyze[ZZ]", jSONObject.toString(), new Object[0]);
        }
        return jSONObject;
    }

    public static void setNeedLog(boolean z) {
        needLog = z;
    }
}
