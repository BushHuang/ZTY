package com.xh.xhcore.common.http;

import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xh.networkclient.HttpRequest;
import com.xh.networkclient.common.CommonUtils;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.HttpReq;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.strategy.HttpConst;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestProxy;
import com.xh.xhcore.common.util.XHDeviceUtil;
import com.xh.xhcore.common.util.XHLog;
import com.xh.xhcore.common.util.XHNetworkUtil;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class XHHttpBaseReq {
    private static final String TAG = "XHHttpBaseReq";
    private static Gson mGson = new Gson();

    private static void executeCallBack(int i, XHRequestCallBack.HttpCallBack httpCallBack, String str, String str2, String str3, HttpRequest.RequestMethod requestMethod, String str4, String str5, boolean z, Map<String, Object> map, String str6, String str7) {
        if (i == CommonUtils.baseCode) {
            httpCallBack.successParse(str);
        } else {
            failedCallBack(i, str2, str3, requestMethod, str4, httpCallBack, str, 0, null, str5, z, map, str6, str7);
        }
    }

    private static void executeKZCallBack(int i, String str, XHRequestCallBack.HttpCallBack httpCallBack, String str2, String str3, String str4, boolean z, Map<String, Object> map, String str5, String str6, Object obj) {
        if (i != CommonUtils.baseCode) {
            failedCallBack(i, str2, str3, null, null, httpCallBack, str, 1, null, str4, z, map, str5, str6);
            return;
        }
        XhJsonParse xhJsonParse = new XhJsonParse();
        xhJsonParse.parseDataWithKZ(str);
        if (200 == xhJsonParse.getR()) {
            httpCallBack.successParse(xhJsonParse.getD());
        } else {
            httpCallBack.failed(xhJsonParse.getR(), getErrorMsg(xhJsonParse.getR(), xhJsonParse.getI()), str, getHttpDetailInfo(str6, null, str5, obj instanceof String ? (String) obj : mGson.toJson(obj), i, str4, str));
        }
    }

    private static <E> void failedCallBack(int i, String str, String str2, HttpRequest.RequestMethod requestMethod, String str3, XHRequestCallBack.HttpCallBack httpCallBack, String str4, int i2, E e, String str5, boolean z, Map<String, Object> map, String str6, String str7) {
        if (XHErrorCodeUtil.CODE_DESCRIPTION_MAP.containsKey(Integer.valueOf(i))) {
            if (!XHHttpUtil.isNeedPolling(i)) {
                httpCallBack.failed(i, getErrorMsg(i, XHErrorCodeUtil.CODE_DESCRIPTION_MAP.get(Integer.valueOf(i))), str4, getHttpDetailInfo(str7, requestMethod, str6, str3, i, str5, str4));
                return;
            }
            if (!XHMicroServer.getInstance().requestAgain(str)) {
                httpCallBack.failed(i, getErrorMsg(i, XHErrorCodeUtil.CODE_DESCRIPTION_MAP.get(Integer.valueOf(i))), str4, getHttpDetailInfo(str7, requestMethod, str6, str3, i, str5, str4));
                return;
            } else if (i2 == 0) {
                requestWithRESTFull(str, str2, requestMethod, str3, z, map, str6, httpCallBack);
                return;
            } else {
                requestWithKZ(str, str2, e, z, httpCallBack);
                return;
            }
        }
        if (str4 == null) {
            httpCallBack.failed(i, getErrorMsg(i, str5), str6, getHttpDetailInfo(str7, requestMethod, str6, str3, i, str5, str4));
            return;
        }
        try {
            if (i2 == 0) {
                restfullFailedCallBack(i, httpCallBack, str4, getHttpDetailInfo(str7, requestMethod, str6, str3, i, str5, str4));
            } else {
                kzFailedCallBack(i, httpCallBack, str4, getHttpDetailInfo(str7, requestMethod, str6, str3, i, str5, str4));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            if (i2 != 0 || i != 107000400) {
                httpCallBack.failed(i, getErrorMsg(i, str4), str6, getHttpDetailInfo(str7, requestMethod, str6, str3, i, str5, str4));
            } else {
                if (httpCallBack.businessFailed(i, str4)) {
                    return;
                }
                httpCallBack.failed(i, getErrorMsg(i, str4), str6, getHttpDetailInfo(str7, requestMethod, str6, str3, i, str5, str4));
            }
        }
    }

    public static String getErrorMsg(int i, String str) {
        if (i < 107000400 || i >= 107000504) {
            return str + ",网络错误:" + i;
        }
        return str + ",服务端错误:" + i;
    }

    private static String getHeadJson(Map<String, Object> map) {
        String uuid;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Content-Type", "application/json; charset=UTF-8");
        try {
            CPVDUser user = CPVDUserData.getUser(XhBaseApplication.mContext);
            jsonObject.addProperty("SchoolId", user != null ? user.getSchoolId() + "" : "null");
            uuid = user.getDeviceNum();
        } catch (Exception e) {
            e.printStackTrace();
            uuid = null;
        }
        Locale locale = Locale.getDefault();
        Object[] objArr = new Object[5];
        objArr[0] = XhBaseApplication.sPackageName;
        objArr[1] = XhBaseApplication.sVersionName;
        objArr[2] = Build.MODEL;
        objArr[3] = Build.VERSION.RELEASE;
        if (TextUtils.isEmpty(uuid)) {
            uuid = XHDeviceUtil.getUUID(XhBaseApplication.mContext);
        }
        objArr[4] = uuid;
        jsonObject.addProperty("User-Agent", String.format(locale, "%s/%s (%s; android; %s; %s)", objArr));
        String xHSessionId = XhBaseApplication.getXHSessionId();
        if (!TextUtils.isEmpty(xHSessionId)) {
            jsonObject.addProperty("Authorization", String.format(Locale.getDefault(), "Bearer %s", xHSessionId));
        }
        String xHUserId = XhBaseApplication.getXHUserId();
        if (!TextUtils.isEmpty(xHUserId)) {
            jsonObject.addProperty("UserId", xHUserId);
        }
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                jsonObject.addProperty(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        return jsonObject.toString();
    }

    private static String getHttpDetailInfo(String str, HttpRequest.RequestMethod requestMethod, String str2, String str3, int i, String str4, String str5) throws JSONException {
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

    private static String getRESTFullErrorMsg(int i, String str, String str2) {
        if (i < 107000400 || i >= 107000504) {
            return str + ",网关错误:" + i;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            return jSONObject.opt("msg") + ",服务端错误:" + jSONObject.opt("traceId") + "," + jSONObject.opt("code");
        } catch (JSONException e) {
            e.printStackTrace();
            return "Json格式不对,服务端错误:" + i;
        }
    }

    private static String getRequestUrl(String str, JsonObject jsonObject) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("?");
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            sb.append(URLEncoder.encode(entry.getKey()));
            sb.append("=");
            sb.append(URLEncoder.encode(jsonElementToString(entry.getValue())));
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

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

    public static boolean judgeNetworkState(final XHRequestCallBack.HttpCallBack httpCallBack, boolean z) {
        if (XHNetworkUtil.isNetworkAvailable(XhBaseApplication.mContext)) {
            return false;
        }
        if (httpCallBack == null) {
            return true;
        }
        if (z) {
            XhBaseApplication.mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    httpCallBack.failed(-108000001, XHHttpBaseReq.getErrorMsg(-108000001, "没有网络连接，请连接网络！"), null);
                }
            });
            return true;
        }
        httpCallBack.failed(-108000001, getErrorMsg(-108000001, "没有网络连接，请连接网络！"), null);
        return true;
    }

    private static void kzFailedCallBack(int i, XHRequestCallBack.HttpCallBack httpCallBack, String str, String str2) throws JSONException {
        String string;
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has("I")) {
            string = jSONObject.get("I").toString();
            if (!TextUtils.isEmpty(string)) {
                httpCallBack.failed(i, getErrorMsg(i, jSONObject.get("I").toString()), str, str2);
                return;
            }
        } else {
            string = "";
        }
        if (jSONObject.has("M")) {
            string = jSONObject.get("M").toString();
        }
        httpCallBack.failed(i, getErrorMsg(i, string), str, str2);
    }

    private static void printHttpResponseXHLog(int i, int i2, String str, String str2) {
        XHLog.d("XHHttpBaseReq", "HttpResponse: id = " + i + ";code = " + i2 + ";description = " + str + ";jsonParam = " + str2);
    }

    private static <E> int requestImp(String str, String str2, HttpRequest.RequestMethod requestMethod, E e, boolean z, Map<String, Object> map, String str3, XHRequestCallBack.HttpCallBack httpCallBack) {
        String json;
        JsonObject asJsonObject;
        String requestUrl;
        if (XHAppConfigProxy.getInstance().getRequestStrategy() != HttpConst.RequestStrategy.STRATEGY_LIB_CURL) {
            return ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestProxy.createOkHttp().setServerType(str).setUrl(str2)).setMethod(requestMethod.name())).setBodyData(e).setExchangeToMainThread(z)).setHeaders(map)).setRequestJsonParams(str3)).setCallback(httpCallBack)).request()).getId();
        }
        if (judgeNetworkState(httpCallBack, z)) {
            return 0;
        }
        if (e != 0) {
            json = e instanceof String ? (String) e : new Gson().toJson(e);
        } else {
            json = null;
        }
        if (json != null) {
            try {
                asJsonObject = new JsonParser().parse(json).getAsJsonObject();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            asJsonObject = null;
        }
        if ((requestMethod == HttpRequest.RequestMethod.GET || requestMethod == HttpRequest.RequestMethod.DELETE) && asJsonObject != null && asJsonObject.size() > 0) {
            json = null;
            requestUrl = getRequestUrl(str2, asJsonObject);
        } else {
            requestUrl = str2;
        }
        return requestWithRESTFull(str, requestUrl, requestMethod, json, z, map, str3, httpCallBack);
    }

    public static <E> int requestWithKZ(String str, E e, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithKZ(null, str, e, true, httpCallBack);
    }

    public static <E> int requestWithKZ(String str, E e, boolean z, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithKZ(null, str, e, z, httpCallBack);
    }

    public static <E> int requestWithKZ(String str, String str2, E e, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithKZ(str, str2, e, true, httpCallBack);
    }

    public static <E> int requestWithKZ(String str, String str2, E e, boolean z, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithKZ(str, str2, e, z, null, null, httpCallBack);
    }

    private static <E> int requestWithKZ(final String str, final String str2, final E e, final boolean z, Map<String, Object> map, String str3, final XHRequestCallBack.HttpCallBack httpCallBack) {
        String str4;
        if (XHAppConfigProxy.getInstance().getRequestStrategy() != HttpConst.RequestStrategy.STRATEGY_LIB_CURL) {
            return ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestProxy.createOkHttp().setRequestType(1).setServerType(str).setUrl(str2)).setBodyData(e).setExchangeToMainThread(z)).setHeaders(map)).setRequestJsonParams(str3)).setCallback(httpCallBack)).request()).getId();
        }
        if (judgeNetworkState(httpCallBack, z)) {
            return 0;
        }
        if (mGson == null) {
            mGson = new Gson();
        }
        if (TextUtils.isEmpty(str)) {
            str4 = str2;
        } else {
            String actualIp = XHMicroServer.getInstance().getActualIp(str);
            String str5 = null;
            if (!TextUtils.isEmpty(actualIp)) {
                str5 = actualIp + str2;
            } else if (httpCallBack != null) {
                if (z) {
                    XhBaseApplication.mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpCallBack.failed(-2, XHHttpBaseReq.getErrorMsg(-2, "未取得实际ip"), null);
                        }
                    });
                } else {
                    httpCallBack.failed(-2, getErrorMsg(-2, "未取得实际ip"), null);
                }
                return 0;
            }
            str4 = str5;
        }
        boolean z2 = e instanceof String;
        final String str6 = str4;
        int iReqWithKZ = HttpReq.reqWithKZ(str4, getHeadJson(map), z2 ? (String) e : mGson.toJson(e), 0, str3, new HttpReq.IRequestCallBack() {
            @Override
            public void onEvent(int i, final int i2, final String str7, final String str8) {
                XHHttpBaseReq.printHttpResponseXHLog(i, i2, str7, str8);
                XHRequestCallBack.HttpCallBack httpCallBack2 = httpCallBack;
                if (httpCallBack2 == null) {
                    return;
                }
                boolean z3 = z;
                if (z3) {
                    XhBaseApplication.mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            XHHttpBaseReq.executeKZCallBack(i2, str8, httpCallBack, str, str2, str7, z, null, null, str6, e);
                        }
                    });
                } else {
                    XHHttpBaseReq.executeKZCallBack(i2, str8, httpCallBack2, str, str2, str7, z3, null, null, str6, e);
                }
            }

            @Override
            public void onProgress(int i, double d, double d2, String str7) {
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str7) {
            }
        });
        startRequestFailed(httpCallBack, iReqWithKZ, getHttpDetailInfo(str4, null, str3, z2 ? (String) e : mGson.toJson(e), -1, null, null), z);
        return iReqWithKZ;
    }

    public static int requestWithMap(String str, HttpRequest.RequestMethod requestMethod, String str2, Map<String, Object> map, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestImp(str, str2, requestMethod, map, true, null, null, httpCallBack);
    }

    public static int requestWithMap(String str, HttpRequest.RequestMethod requestMethod, String str2, Map<String, Object> map, Map<String, Object> map2, String str3, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestImp(str, str2, requestMethod, map, true, map2, str3, httpCallBack);
    }

    public static int requestWithMap(String str, HttpRequest.RequestMethod requestMethod, String str2, Map<String, Object> map, boolean z, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestImp(str, str2, requestMethod, map, z, null, null, httpCallBack);
    }

    public static int requestWithMap(String str, HttpRequest.RequestMethod requestMethod, String str2, Map<String, Object> map, boolean z, Map<String, Object> map2, String str3, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestImp(str, str2, requestMethod, map, z, map2, str3, httpCallBack);
    }

    public static int requestWithMap(String str, HttpRequest.RequestMethod requestMethod, Map<String, Object> map, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithMap((String) null, requestMethod, str, map, true, httpCallBack);
    }

    public static int requestWithMap(String str, HttpRequest.RequestMethod requestMethod, Map<String, Object> map, Map<String, Object> map2, String str2, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithMap(null, requestMethod, str, map, true, map2, str2, httpCallBack);
    }

    public static int requestWithMap(String str, HttpRequest.RequestMethod requestMethod, Map<String, Object> map, boolean z, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithMap((String) null, requestMethod, str, map, z, httpCallBack);
    }

    public static int requestWithMap(String str, HttpRequest.RequestMethod requestMethod, Map<String, Object> map, boolean z, Map<String, Object> map2, String str2, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithMap(null, requestMethod, str, map, z, map2, str2, httpCallBack);
    }

    public static <E> int requestWithObject(String str, HttpRequest.RequestMethod requestMethod, E e, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithObject((String) null, requestMethod, str, (Object) e, true, httpCallBack);
    }

    public static <E> int requestWithObject(String str, HttpRequest.RequestMethod requestMethod, E e, Map<String, Object> map, String str2, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithObject(null, requestMethod, str, e, true, map, str2, httpCallBack);
    }

    public static <E> int requestWithObject(String str, HttpRequest.RequestMethod requestMethod, E e, boolean z, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithObject((String) null, requestMethod, str, e, z, httpCallBack);
    }

    public static <E> int requestWithObject(String str, HttpRequest.RequestMethod requestMethod, E e, boolean z, Map<String, Object> map, String str2, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestWithObject(null, requestMethod, str, e, z, map, str2, httpCallBack);
    }

    public static <E> int requestWithObject(String str, HttpRequest.RequestMethod requestMethod, String str2, E e, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestImp(str, str2, requestMethod, e, true, null, null, httpCallBack);
    }

    public static <E> int requestWithObject(String str, HttpRequest.RequestMethod requestMethod, String str2, E e, Map<String, Object> map, String str3, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestImp(str, str2, requestMethod, e, true, map, str3, httpCallBack);
    }

    public static <E> int requestWithObject(String str, HttpRequest.RequestMethod requestMethod, String str2, E e, boolean z, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestImp(str, str2, requestMethod, e, z, null, null, httpCallBack);
    }

    public static <E> int requestWithObject(String str, HttpRequest.RequestMethod requestMethod, String str2, E e, boolean z, Map<String, Object> map, String str3, XHRequestCallBack.HttpCallBack httpCallBack) {
        return requestImp(str, str2, requestMethod, e, z, map, str3, httpCallBack);
    }

    private static int requestWithRESTFull(final String str, final String str2, final HttpRequest.RequestMethod requestMethod, final String str3, final boolean z, final Map<String, Object> map, final String str4, final XHRequestCallBack.HttpCallBack httpCallBack) {
        String str5;
        if (TextUtils.isEmpty(str)) {
            str5 = str2;
        } else {
            String actualIp = XHMicroServer.getInstance().getActualIp(str);
            if (TextUtils.isEmpty(actualIp)) {
                if (httpCallBack == null) {
                    return 0;
                }
                if (z) {
                    XhBaseApplication.mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpCallBack.failed(-2, XHHttpBaseReq.getErrorMsg(-2, "未取得实际ip"), null);
                        }
                    });
                    return 0;
                }
                httpCallBack.failed(-2, getErrorMsg(-2, "未取得实际ip"), null);
                return 0;
            }
            str5 = actualIp + str2;
        }
        final String str6 = str5;
        String str7 = str5;
        int iRequestWithRESTFul = HttpReq.requestWithRESTFul(str7, requestMethod, getHeadJson(map), str3, str4, new HttpReq.IRequestCallBack() {
            @Override
            public void onEvent(int i, final int i2, final String str8, final String str9) {
                XHHttpBaseReq.printHttpResponseXHLog(i, i2, str8, str9);
                XHRequestCallBack.HttpCallBack httpCallBack2 = httpCallBack;
                if (httpCallBack2 == null) {
                    return;
                }
                boolean z2 = z;
                if (z2) {
                    XhBaseApplication.mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            XHHttpBaseReq.executeCallBack(i2, httpCallBack, str9, str, str2, requestMethod, str3, str8, z, map, str4, str6);
                        }
                    });
                } else {
                    XHHttpBaseReq.executeCallBack(i2, httpCallBack2, str9, str, str2, requestMethod, str3, str8, z2, map, str4, str6);
                }
            }

            @Override
            public void onProgress(int i, double d, double d2, String str8) {
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str8) {
            }
        });
        startRequestFailed(httpCallBack, iRequestWithRESTFul, getHttpDetailInfo(str7, requestMethod, str4, str3, -1, null, null), z);
        return iRequestWithRESTFul;
    }

    private static void restfullFailedCallBack(int i, XHRequestCallBack.HttpCallBack httpCallBack, String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        if (i != 107000400) {
            if (jSONObject.has("msg")) {
                httpCallBack.failed(i, getErrorMsg(i, jSONObject.get("msg").toString()), str, str2);
                return;
            }
            if (!jSONObject.has("traceId")) {
                httpCallBack.failed(i, getErrorMsg(i, str), str, str2);
                return;
            }
            httpCallBack.failed(i, getErrorMsg(i, "traceId=" + jSONObject.get("traceId") + ",服务器错误！"), str, str2);
            return;
        }
        if (httpCallBack.businessFailed(i, str)) {
            return;
        }
        if (jSONObject.has("msg")) {
            httpCallBack.failed(i, getErrorMsg(i, jSONObject.get("msg").toString()), str, str2);
            return;
        }
        if (!jSONObject.has("traceId")) {
            httpCallBack.failed(i, getErrorMsg(i, str), str, str2);
            return;
        }
        httpCallBack.failed(i, getErrorMsg(i, "traceId=" + jSONObject.get("traceId") + ",服务器错误！"), str, str2);
    }

    private static void startRequestFailed(final XHRequestCallBack.HttpCallBack httpCallBack, final int i, final String str, boolean z) {
        if (i > 0 || httpCallBack == null) {
            return;
        }
        if (z) {
            XhBaseApplication.mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    XHRequestCallBack.HttpCallBack httpCallBack2 = httpCallBack;
                    int i2 = i;
                    httpCallBack2.failed(i2, XHHttpBaseReq.getErrorMsg(i2, "请求下发失败"), str);
                }
            });
        } else {
            httpCallBack.failed(i, getErrorMsg(i, "请求下发失败"), str);
        }
    }
}
