package com.xh.xhcore.common.http;

import com.xh.xhcore.common.http.strategy.LogUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class XHHttpUtil {
    public static boolean isNeedPolling(int i) {
        return i == 107000006 || i == 107001012 || i == 107001016 || i == 107004001;
    }

    public static boolean pingAddress(String str, int i, int i2) throws InterruptedException, IOException {
        Process processExec = Runtime.getRuntime().exec("ping -c " + i + " -w " + i2 + " " + str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
        }
        LogUtils.d("result content : " + sb.toString());
        int iWaitFor = processExec.waitFor();
        LogUtils.d("result status : " + iWaitFor + " domainOrIp = " + str);
        return iWaitFor == 0;
    }

    public static boolean pingBaidu(int i, int i2) throws InterruptedException, IOException {
        return pingAddress("www.baidu.com", i, i2);
    }

    public static String toByteUnit(long j) {
        long j2 = j >> 10;
        long j3 = j >> 20;
        return (j >> 30) > 0 ? String.format(Locale.getDefault(), "%.2fGb/s", Float.valueOf((j3 * 1.0f) / 1024.0f)) : j3 > 0 ? String.format(Locale.getDefault(), "%.2fMb/s", Float.valueOf((j2 * 1.0f) / 1024.0f)) : j2 > 0 ? String.format(Locale.getDefault(), "%dKb/s", Long.valueOf(j2)) : String.format(Locale.getDefault(), "%db/s", Long.valueOf(j));
    }

    public static String toSpeedJson(long j) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("speed", toByteUnit(j));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
