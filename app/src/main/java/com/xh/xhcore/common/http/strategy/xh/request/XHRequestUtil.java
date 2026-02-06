package com.xh.xhcore.common.http.strategy.xh.request;

import android.os.Build;
import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xh.networkclient.HttpRequest;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xh.xhcore.common.util.XHDeviceUtil;
import com.xh.xhcore.common.util.traceid.TraceContext;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class XHRequestUtil {
    private static void appendMapToJsonObject(Map<String, Object> map, JsonObject jsonObject) {
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                jsonObject.addProperty(entry.getKey().toString(), entry.getValue().toString());
            }
        }
    }

    private static String getClientSpanIdValue() {
        long jNextLong = new Random().nextLong();
        while (jNextLong == 0) {
            jNextLong = new Random().nextLong();
        }
        return Long.toHexString(jNextLong);
    }

    public static String getClientTraceIdValue() {
        return getClientTraceIdValue(CPVDUserData.getUser(XhBaseApplication.mContext));
    }

    public static String getClientTraceIdValue(CPVDUser cPVDUser) {
        return TraceContext.traceIdString(cPVDUser != null ? cPVDUser.getUserId() : Long.MAX_VALUE, System.currentTimeMillis());
    }

    private static JsonObject getDefaultHeaders() {
        String str;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Content-Type", "application/json; charset=UTF-8");
        try {
            CPVDUser user = CPVDUserData.getUser(XhBaseApplication.mContext);
            if (user != null) {
                str = user.getSchoolId() + "";
            } else {
                str = "null";
            }
            jsonObject.addProperty("SchoolId", str);
            jsonObject.addProperty("User-Agent", getDefaultUserAgent(user != null ? user.getDeviceNum() : null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String xHSessionId = XhBaseApplication.getXHSessionId();
        if (!TextUtils.isEmpty(xHSessionId)) {
            jsonObject.addProperty("Authorization", String.format(Locale.getDefault(), "Bearer %s", xHSessionId));
        }
        String xHUserId = XhBaseApplication.getXHUserId();
        if (!TextUtils.isEmpty(xHUserId)) {
            jsonObject.addProperty("UserId", xHUserId);
        }
        return jsonObject;
    }

    public static Map<String, String> getDefaultHeadersMap() {
        HashMap map = new HashMap();
        JsonObject defaultHeaders = getDefaultHeaders();
        if (defaultHeaders != null && defaultHeaders.size() != 0) {
            for (Map.Entry<String, JsonElement> entry : defaultHeaders.entrySet()) {
                map.put(entry.getKey(), entry.getValue().toString().replaceAll("\"", ""));
            }
            map.put("X-B3-TraceId", getClientTraceIdValue());
            map.put("X-B3-SpanId", getClientSpanIdValue());
            map.put("XHCore-Version", "4.1.34");
        }
        return map;
    }

    public static String getDefaultUserAgent(String str) {
        Locale locale = Locale.getDefault();
        Object[] objArr = new Object[5];
        objArr[0] = XHAppUtil.getPackageName();
        objArr[1] = XHAppUtil.getVersionName();
        objArr[2] = Build.MODEL;
        objArr[3] = Build.VERSION.RELEASE;
        if (TextUtils.isEmpty(str)) {
            str = XHDeviceUtil.getUUID(XhBaseApplication.mContext);
        }
        objArr[4] = str;
        return String.format(locale, "%s/%s (%s; android; %s; %s)", objArr);
    }

    public static String getErrorMsg(int i, String str) {
        if (i < 107000400 || i >= 107000504) {
            return str + ",网络错误:" + i;
        }
        return str + ",服务端错误:" + i;
    }

    public static JsonObject getHeadJson(Map<String, Object> map) {
        JsonObject defaultHeaders = getDefaultHeaders();
        appendMapToJsonObject(map, defaultHeaders);
        return defaultHeaders;
    }

    public static String getHttpDetailInfo(String str, HttpRequest.RequestMethod requestMethod, String str2, String str3, int i, String str4, String str5) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Url", str);
            jSONObject.put("RequestMethod", requestMethod == null ? null : requestMethod.toString());
            jSONObject.put("requestJsonParam", str2);
            jSONObject.put("BodyData", str3);
            jSONObject.put("ErrorCode", i);
            jSONObject.put("Description", str4);
            jSONObject.put("ResponseJsonParam", str5);
            jSONObject.put("session", XhBaseApplication.getXHSessionId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    @Deprecated
    public static String getRequestUrl(String str, String str2) {
        JsonObject jsonObjectStringToJsonObject = JsonUtil.stringToJsonObject(str2);
        if (jsonObjectStringToJsonObject == null || jsonObjectStringToJsonObject.size() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("?");
        for (Map.Entry<String, JsonElement> entry : jsonObjectStringToJsonObject.entrySet()) {
            sb.append(URLEncoder.encode(entry.getKey()));
            sb.append("=");
            sb.append(URLEncoder.encode(jsonElementToString(entry.getValue())));
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Deprecated
    private static String jsonElementToString(JsonElement jsonElement) {
        try {
            return jsonElement.getAsString();
        } catch (Exception e) {
            LogUtils.w("encode exception = " + e);
            String strReplaceAll = jsonElement.toString().replaceAll("\"", "");
            LogUtils.w("result = " + strReplaceAll);
            return strReplaceAll;
        }
    }
}
