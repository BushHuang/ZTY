package com.xuehai.launcher.common.http;

import android.os.Build;
import android.text.TextUtils;
import com.google.gson.JsonParser;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.cache.SessionData;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.config.KeepConfig;
import com.xuehai.launcher.common.http.LRequest;
import com.xuehai.launcher.common.http.LRequestHeader;
import com.xuehai.launcher.common.http.response.CheckDeviceResponse;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.util.DeviceActiveManager;
import com.xuehai.launcher.common.util.SettingsHelper;
import com.xuehai.system.mdm.proxy.PolicyManager;
import com.zaze.utils.JsonUtil;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0006J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bJ\b\u0010\r\u001a\u00020\u0006H\u0002J&\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\b¨\u0006\u0014"}, d2 = {"Lcom/xuehai/launcher/common/http/NetStore;", "", "()V", "checkBrushCode", "Lcom/xuehai/launcher/common/http/LResponse;", "code", "", "checkDevice", "Lio/reactivex/Observable;", "Lcom/xuehai/launcher/common/http/response/CheckDeviceResponse;", "deviceId", "checkOSVersion", "", "getUrl", "requestPlatformApk", "url", "appId", "versionCode", "", "requestServerTimestamp", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NetStore {
    public static final NetStore INSTANCE = new NetStore();

    private NetStore() {
    }

    private static final CheckDeviceResponse m71checkDevice$lambda4(LResponse lResponse) throws ResponseException {
        Intrinsics.checkNotNullParameter(lResponse, "response");
        if (!lResponse.isSuccessful()) {
            throw new ResponseException(lResponse);
        }
        CheckDeviceResponse checkDeviceResponse = (CheckDeviceResponse) JsonUtil.parseJson(lResponse.getResponseBody(), CheckDeviceResponse.class);
        if (checkDeviceResponse.getPurpose() != -1) {
            KeepConfig.updatePurpose(checkDeviceResponse.getPurpose());
        }
        String serviceVersionCode = checkDeviceResponse.getServiceVersionCode();
        if (!(serviceVersionCode == null || serviceVersionCode.length() == 0)) {
            KeepConfig.updateServiceVersionCode(checkDeviceResponse.getServiceVersionCode());
        }
        return checkDeviceResponse;
    }

    private static final Boolean m72checkOSVersion$lambda1() {
        return true;
    }

    private static final Map m73checkOSVersion$lambda2() {
        HashMap map = new HashMap();
        try {
            String str = ClientConfig.INSTANCE.getPackageName() + '/' + ClientConfig.INSTANCE.getAppVersion() + " (" + Build.MODEL + "; android; " + Build.VERSION.RELEASE + "; " + (DeviceActiveManager.INSTANCE.isAdminActive(BaseApplication.INSTANCE.getInstance()) ? PolicyManager.getDevicePolicyProxy().getSerialNumber() : BaseApplication.INSTANCE.getInstance().getDeviceId()) + ')';
            map.put("Content-Type", "application/json");
            map.put("Accept", "*/*");
            map.put("User-Agent", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private static final Boolean m74checkOSVersion$lambda3(LResponse lResponse) {
        Intrinsics.checkNotNullParameter(lResponse, "response");
        if (lResponse.isSuccessful() && !TextUtils.isEmpty(lResponse.getResponseBody())) {
            try {
                SessionData.INSTANCE.setOsVersionRemoteValid(new JSONObject(lResponse.getResponseBody()).getBoolean("isValid"));
                return Boolean.valueOf(SessionData.INSTANCE.isOsVersionValid());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private final String getUrl() {
        return BaseApplication.INSTANCE.getInstance().isProduction() ? "https://ztp.yunzuoye.net" : "http://ztp.xht.com";
    }

    private static final LResponse m76requestServerTimestamp$lambda0(LResponse lResponse) {
        Intrinsics.checkNotNullParameter(lResponse, "response");
        if (lResponse.isSuccessful() && !TextUtils.isEmpty(lResponse.getResponseBody())) {
            try {
                SettingsHelper.INSTANCE.updateSystemTime(new JsonParser().parse(lResponse.getResponseBody()).getAsJsonObject().get("dateTime").getAsString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lResponse;
    }

    public final LResponse checkBrushCode(String code) throws JSONException {
        Intrinsics.checkNotNullParameter(code, "code");
        MyLog.i("Http[MDM]", "校验code");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", code);
            jSONObject.put("deviceId", BaseApplication.INSTANCE.getInstance().getDeviceId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LRequest.Builder builderUrl$default = LRequest.Builder.url$default(new LRequest.Builder(), getUrl() + "/api/v2/pub/platform/brushCode", null, 2, null);
        LRequestBody lRequestBodyCreate = LRequestBody.create(jSONObject);
        Intrinsics.checkNotNullExpressionValue(lRequestBodyCreate, "create(jsonObject)");
        return RequestHelper.INSTANCE.requestWithRestful(builderUrl$default.patch(lRequestBodyCreate).build());
    }

    public final Observable<CheckDeviceResponse> checkDevice(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        MyLog.i("[MDM]", "远程校验设备号: " + deviceId);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("deviceId", deviceId);
        linkedHashMap.put("verifySystem", "true");
        String str = Build.DISPLAY;
        Intrinsics.checkNotNullExpressionValue(str, "DISPLAY");
        linkedHashMap.put("system", str);
        linkedHashMap.put("imei", PolicyManager.getDevicePolicyProxy().getImei());
        if (ClientConfig.INSTANCE.is2C()) {
            linkedHashMap.put("tocPlatform", "true");
        }
        Observable map = RequestHelper.INSTANCE.requestWithRestfulByRx(new LRequest.Builder().url(getUrl() + "/api/v1/pub/platform/check/device", linkedHashMap).build()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return NetStore.m71checkDevice$lambda4((LResponse) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "RequestHelper.requestWit…ption(response)\n        }");
        return map;
    }

    public final Observable<Boolean> checkOSVersion() {
        boolean zIsOsVersionValid = SessionData.INSTANCE.isOsVersionValid();
        boolean zIsOsVersionRemoteValid = SessionData.INSTANCE.isOsVersionRemoteValid();
        MyLog.i("[MDM]", "校验系统版本: " + Build.DISPLAY + "；isOsVersionValid = " + zIsOsVersionValid + "；isOsVersionRemoteValid = " + zIsOsVersionRemoteValid);
        if (zIsOsVersionRemoteValid) {
            Observable<Boolean> observableFromCallable = Observable.fromCallable(new Callable() {
                @Override
                public final Object call() {
                    return NetStore.m72checkOSVersion$lambda1();
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable { true }");
            return observableFromCallable;
        }
        Observable map = RequestHelper.INSTANCE.requestWithRestfulByRx(new LRequest.Builder(new LRequestHeader.DefaultBuilder() {
            @Override
            public final Map getDefaultHeaderMap() {
                return NetStore.m73checkOSVersion$lambda2();
            }
        }).url(getUrl() + "/api/v1/pub/platform/check/system", MapsKt.mapOf(new Pair("system", Build.DISPLAY))).build()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return NetStore.m74checkOSVersion$lambda3((LResponse) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "RequestHelper.requestWit…          false\n        }");
        return map;
    }

    public final Observable<LResponse> requestPlatformApk(String url, String appId, int versionCode) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        MyLog.i("[MDM]", "请求下载智通平台");
        HashMap map = new HashMap();
        map.put("appId", appId);
        map.put("versionCode", String.valueOf(versionCode));
        String str = url;
        if (str == null || str.length() == 0) {
            url = getUrl() + "/api/v2/platform/apk";
        }
        return RequestHelper.INSTANCE.requestWithRestfulByRx(new LRequest.Builder().url(url, map).build());
    }

    public final Observable<LResponse> requestServerTimestamp() {
        MyLog.i("[MDM]", "请求服务器时间");
        Observable map = RequestHelper.INSTANCE.requestWithRestfulByRx(LRequest.Builder.url$default(new LRequest.Builder(), getUrl() + "/api/v1/platform/dateTime", null, 2, null).build()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return NetStore.m76requestServerTimestamp$lambda0((LResponse) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "RequestHelper.requestWit…       response\n        }");
        return map;
    }
}
