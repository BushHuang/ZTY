package com.xh.xhcore.common.http.strategy.xh.upload.domain.aksk;

import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.upload.aksk.AesEncryptUtils;
import com.xh.xhcore.common.http.strategy.xh.upload.aksk.AesEncryptionAlgorithm;
import com.xh.xhcore.common.oss.OssConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u001a\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\fH\u0007J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\"\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0017\u001a\u0004\u0018\u00010\fH\u0002J\"\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0017\u001a\u0004\u0018\u00010\fH\u0002J\u001a\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0002J \u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u0004H\u0002J*\u0010&\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\fH\u0003JB\u0010(\u001a\u00020\u000e2\b\u0010)\u001a\u0004\u0018\u00010\u00042\b\u0010#\u001a\u0004\u0018\u00010\u00042\b\u0010$\u001a\u0004\u0018\u00010\u00042\b\u0010%\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0017\u001a\u0004\u0018\u00010\fH\u0002J\u001a\u0010*\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\b\u0010+\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010,\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u0010\u0010-\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u001a\u0010'\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\fH\u0007J\u001a\u0010.\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00042\b\u0010/\u001a\u0004\u0018\u00010\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0012\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/aksk/OsDomainAkSkUtil;", "", "()V", "AES_KEY", "", "KEY_AK", "KEY_DOMAIN", "KEY_SK", "KEY_TIME", "KEY_TOKEN", "callbacks", "", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/aksk/OnOsAkSkCallback;", "isRunning", "", "tag", "weakReference", "Ljava/lang/ref/WeakReference;", "", "deleteFile", "", "domain", "getAkSk", "callback", "getFile", "Ljava/io/File;", "getLocal", "minValidTime", "", "getMemory", "innerFailed", "code", "", "msg", "innerSuccess", "ak", "sk", "token", "internalAkSk", "reset", "isValid", "time", "parse", "body", "read", "request", "write", "content", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OsDomainAkSkUtil {
    private static final String AES_KEY = "xhyadfaf342sx@fs";
    private static final String KEY_AK = "KEY_AK";
    private static final String KEY_DOMAIN = "KEY_DOMAIN";
    private static final String KEY_SK = "KEY_SK";
    private static final String KEY_TIME = "KEY_TIME";
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static boolean isRunning = false;
    private static final String tag = "OsDomainAkSkUtil";
    private static WeakReference<Map<String, String>> weakReference;
    public static final OsDomainAkSkUtil INSTANCE = new OsDomainAkSkUtil();
    private static final List<OnOsAkSkCallback> callbacks = new ArrayList();

    private OsDomainAkSkUtil() {
    }

    private final void deleteFile(String domain) {
        try {
            File file = getFile(domain);
            if (file == null) {
                return;
            }
            file.delete();
        } catch (Exception e) {
            LogUtils.INSTANCE.e("OsDomainAkSkUtil", e);
        }
    }

    @JvmStatic
    public static final void getAkSk(String domain, OnOsAkSkCallback callback) {
        Intrinsics.checkNotNullParameter(domain, "domain");
        internalAkSk(1000L, false, domain, callback);
    }

    private final File getFile(String domain) {
        File file = new File(XhBaseApplication.mContext.getCacheDir().getAbsolutePath(), XhBaseApplication.getXHUserId() + ".obsaksk_" + domain);
        if (file.exists() || file.createNewFile()) {
            return file;
        }
        LogUtils.Companion.e$default(LogUtils.INSTANCE, "OsDomainAkSkUtil", "文件创建失败", null, 4, null);
        return null;
    }

    private final boolean getLocal(String domain, long minValidTime, OnOsAkSkCallback callback) throws Throwable {
        String str = read(domain);
        String str2 = str;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("KEY_TIME");
            String strOptString2 = jSONObject.optString("KEY_AK");
            String strOptString3 = jSONObject.optString("KEY_SK");
            String strOptString4 = jSONObject.optString("KEY_TOKEN");
            if (isValid(strOptString, strOptString2, strOptString3, strOptString4, minValidTime, callback)) {
                weakReference = new WeakReference<>(MapsKt.mutableMapOf(new Pair("KEY_AK", strOptString2), new Pair("KEY_SK", strOptString3), new Pair("KEY_TIME", strOptString), new Pair("KEY_TOKEN", strOptString4)));
                LogUtils.Companion.d$default(LogUtils.INSTANCE, "OsDomainAkSkUtil", "本地已存储", null, 4, null);
                return true;
            }
        }
        return false;
    }

    private final boolean getMemory(String domain, long minValidTime, OnOsAkSkCallback callback) {
        WeakReference<Map<String, String>> weakReference2 = weakReference;
        if (weakReference2 != null) {
            Map<String, String> map = weakReference2 == null ? null : weakReference2.get();
            if (map != null) {
                if (!Intrinsics.areEqual(domain, map.get("KEY_DOMAIN"))) {
                    LogUtils.Companion.d$default(LogUtils.INSTANCE, "OsDomainAkSkUtil", "内存中的domain不一致", null, 4, null);
                    return false;
                }
                if (isValid(map.get("KEY_TIME"), map.get("KEY_AK"), map.get("KEY_SK"), map.get("KEY_TOKEN"), minValidTime, callback)) {
                    LogUtils.Companion.d$default(LogUtils.INSTANCE, "OsDomainAkSkUtil", "内存中已经存在", null, 4, null);
                    return true;
                }
            }
        }
        return false;
    }

    private final void innerFailed(int code, String msg) {
        synchronized (callbacks) {
            Iterator<T> it = callbacks.iterator();
            while (it.hasNext()) {
                ((OnOsAkSkCallback) it.next()).failed(code, msg);
            }
            callbacks.clear();
            isRunning = false;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void innerSuccess(String ak, String sk, String token) {
        synchronized (callbacks) {
            Iterator<T> it = callbacks.iterator();
            while (it.hasNext()) {
                ((OnOsAkSkCallback) it.next()).success(ak, sk, token);
            }
            callbacks.clear();
            isRunning = false;
            Unit unit = Unit.INSTANCE;
        }
    }

    @JvmStatic
    private static final void internalAkSk(long minValidTime, boolean reset, String domain, OnOsAkSkCallback callback) {
        synchronized (callbacks) {
            if (isRunning) {
                if (callback != null) {
                    callbacks.add(callback);
                }
                return;
            }
            if (reset) {
                weakReference = null;
                INSTANCE.deleteFile(domain);
            } else {
                if (INSTANCE.getMemory(domain, minValidTime, callback)) {
                    return;
                }
                if (INSTANCE.getLocal(domain, minValidTime, callback)) {
                    return;
                }
            }
            if (callback != null) {
                callbacks.add(callback);
            }
            isRunning = true;
            INSTANCE.request(domain);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final boolean isValid(String time, String ak, String sk, String token, long minValidTime, OnOsAkSkCallback callback) {
        Long longOrNull;
        String str = time;
        if (!(str == null || StringsKt.isBlank(str))) {
            String str2 = ak;
            if (!(str2 == null || StringsKt.isBlank(str2))) {
                String str3 = sk;
                if (!(str3 == null || StringsKt.isBlank(str3))) {
                    String str4 = token;
                    if (!(str4 == null || StringsKt.isBlank(str4)) && (longOrNull = StringsKt.toLongOrNull(time)) != null && longOrNull.longValue() - minValidTime > System.currentTimeMillis()) {
                        if (callback != null) {
                            callback.success(ak, sk, token);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final void parse(String domain, String body) throws Throwable {
        String str = body;
        if (!(str == null || StringsKt.isBlank(str))) {
            try {
                String strOptString = new JSONObject(body).optString("response");
                String str2 = strOptString;
                if (!(str2 == null || StringsKt.isBlank(str2))) {
                    Key keyLoadAesKey = AesEncryptUtils.loadAesKey("xhyadfaf342sx@fs");
                    String strDecrypt = new AesEncryptionAlgorithm(keyLoadAesKey, keyLoadAesKey).decrypt(strOptString);
                    String str3 = strDecrypt;
                    if (str3 == null || StringsKt.isBlank(str3)) {
                        LogUtils.Companion.w$default(LogUtils.INSTANCE, "OsDomainAkSkUtil", Intrinsics.stringPlus("AkSk返回的数据，解密失败 data = ", strOptString), null, 4, null);
                        innerFailed(-1, Intrinsics.stringPlus("AkSk返回的数据，解密失败 data = ", strOptString));
                        return;
                    }
                    JSONObject jSONObject = new JSONObject(strDecrypt);
                    String strOptString2 = jSONObject.optString("accessKey");
                    String strOptString3 = jSONObject.optString("accessSecret");
                    String strOptString4 = jSONObject.optString("expiration");
                    String strOptString5 = jSONObject.optString("securityToken");
                    String str4 = strOptString4;
                    if (!(str4 == null || StringsKt.isBlank(str4))) {
                        String str5 = strOptString2;
                        if (!(str5 == null || StringsKt.isBlank(str5))) {
                            String str6 = strOptString3;
                            if (!(str6 == null || StringsKt.isBlank(str6))) {
                                String str7 = strOptString5;
                                if (!(str7 == null || StringsKt.isBlank(str7))) {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
                                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                                    Date date = simpleDateFormat.parse(strOptString4);
                                    Long lValueOf = date == null ? null : Long.valueOf(date.getTime());
                                    if (lValueOf != null && lValueOf.longValue() > 0) {
                                        String string = lValueOf.toString();
                                        Intrinsics.checkNotNullExpressionValue(strOptString2, "ak");
                                        Intrinsics.checkNotNullExpressionValue(strOptString3, "sk");
                                        Intrinsics.checkNotNullExpressionValue(strOptString5, "token");
                                        weakReference = new WeakReference<>(MapsKt.mutableMapOf(new Pair("KEY_AK", strOptString2), new Pair("KEY_SK", strOptString3), new Pair("KEY_TIME", string), new Pair("KEY_TOKEN", strOptString5), new Pair("KEY_DOMAIN", domain)));
                                        String string2 = new JSONObject().put("KEY_AK", strOptString2).put("KEY_SK", strOptString3).put("KEY_TIME", string).put("KEY_TOKEN", strOptString5).toString();
                                        Intrinsics.checkNotNullExpressionValue(string2, "JSONObject().put(KEY_AK,…_TOKEN, token).toString()");
                                        LogUtils.Companion.d$default(LogUtils.INSTANCE, "OsDomainAkSkUtil", Intrinsics.stringPlus("数据解析成功，写入数据到本地 = ", string2), null, 4, null);
                                        write(domain, string2);
                                        innerSuccess(strOptString2, strOptString3, strOptString5);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    LogUtils.Companion.w$default(LogUtils.INSTANCE, "OsDomainAkSkUtil", Intrinsics.stringPlus("AkSk返回的数据中，存在异常的数据 data = ", strOptString), null, 4, null);
                    innerFailed(-1, Intrinsics.stringPlus("AkSk返回的数据中，存在异常的数据 data = ", strOptString));
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LogUtils.Companion.w$default(LogUtils.INSTANCE, "OsDomainAkSkUtil", Intrinsics.stringPlus("AkSk返回的数据中，存在异常的数据 body = ", body), null, 4, null);
        innerFailed(-1, Intrinsics.stringPlus("AkSk返回的数据中，存在异常的数据 body = ", body));
    }

    private final String read(String domain) throws Throwable {
        FileInputStream fileInputStream;
        String str;
        File file;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                file = getFile(domain);
            } catch (Exception e) {
                e = e;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                if (fileInputStream2 != null) {
                }
                throw th;
            }
            if (file == null) {
                return null;
            }
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                str = new String(bArr, Charsets.UTF_8);
            } catch (Exception e2) {
                e = e2;
                LogUtils.INSTANCE.e("OsDomainAkSkUtil", e);
                str = (String) null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            }
            fileInputStream.close();
            return str;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    private final void request(final String domain) {
        ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestOkHttpProxy.createOkHttp().setUrl(Intrinsics.stringPlus(OssConfig.ALI_AK_SK_HOST, "/XHFileServer/sts/certificate"))).setExchangeToMainThread(false)).addQueryParam("appName", XHAppConfigProxy.getInstance().getAppId()).addQueryParam("objStorageDomain", domain).setMethod("GET")).setCallback(new XHRequestCallBack.HttpCallBack<String>() {
            @Override
            public void failed(int code, String msg) {
                LogUtils.Companion.w$default(LogUtils.INSTANCE, "OsDomainAkSkUtil", "接口请求失败 code = " + code + "  msg = " + ((Object) msg), null, 4, null);
                OsDomainAkSkUtil.INSTANCE.innerFailed(code, msg);
            }

            @Override
            public void success(String t) throws Throwable {
                OsDomainAkSkUtil.INSTANCE.parse(domain, t);
            }
        })).request();
    }

    @JvmStatic
    public static final void reset(String domain, OnOsAkSkCallback callback) {
        Intrinsics.checkNotNullParameter(domain, "domain");
        internalAkSk(1000L, true, domain, callback);
    }

    private final boolean write(String domain, String content) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File file = getFile(domain);
                if (file == null) {
                    return false;
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                if (content == null) {
                    content = "";
                }
                try {
                    byte[] bytes = content.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    fileOutputStream2.write(bytes);
                    fileOutputStream2.close();
                    return true;
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    LogUtils.INSTANCE.e("OsDomainAkSkUtil", e);
                    if (fileOutputStream == null) {
                        return false;
                    }
                    fileOutputStream.close();
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }
}
