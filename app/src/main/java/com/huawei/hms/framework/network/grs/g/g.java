package com.huawei.hms.framework.network.grs.g;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.net.ssl.HttpsURLConnection;

public class g extends a implements Callable<d> {
    private static final String i = "g";

    public g(String str, int i2, c cVar, Context context, String str2, GrsBaseInfo grsBaseInfo, com.huawei.hms.framework.network.grs.e.c cVar2) {
        super(str, i2, cVar, context, str2, grsBaseInfo, cVar2);
    }

    @Override
    public d call() throws Throwable {
        HttpsURLConnection httpsURLConnection;
        HttpsURLConnection httpsURLConnectionA;
        long j;
        long jCurrentTimeMillis;
        long jElapsedRealtime;
        Logger.i(i, "Post call execute");
        long jCurrentTimeMillis2 = 0;
        HttpsURLConnection httpsURLConnection2 = null;
        InputStream inputStream = null;
        byte[] bArr = null;
        try {
            try {
                try {
                    jElapsedRealtime = SystemClock.elapsedRealtime();
                    try {
                        jCurrentTimeMillis2 = System.currentTimeMillis();
                        httpsURLConnectionA = com.huawei.hms.framework.network.grs.h.f.a.a(c(), a(), e());
                        try {
                        } catch (IOException e) {
                            e = e;
                            long j2 = jCurrentTimeMillis2;
                            jCurrentTimeMillis2 = jElapsedRealtime;
                            j = j2;
                            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                            jCurrentTimeMillis = System.currentTimeMillis();
                            Logger.w(i, "RequestCallableV2 run task catch IOException", e);
                            this.f265a = new d(e, jElapsedRealtime2 - jCurrentTimeMillis2);
                            if (httpsURLConnectionA != null) {
                                try {
                                    httpsURLConnectionA.disconnect();
                                } catch (RuntimeException unused) {
                                    jCurrentTimeMillis2 = j;
                                    Logger.w(i, "RequestCallableV2 disconnect HttpsURLConnection catch RuntimeException");
                                    this.f265a.b(c());
                                    this.f265a.a(d());
                                    this.f265a.b(jCurrentTimeMillis2);
                                    this.f265a.a(jCurrentTimeMillis);
                                    if (b() != null) {
                                    }
                                    return this.f265a;
                                } catch (Throwable unused2) {
                                    jCurrentTimeMillis2 = j;
                                    Logger.w(i, "RequestCallableV2 disconnect HttpsURLConnection catch Throwable");
                                    this.f265a.b(c());
                                    this.f265a.a(d());
                                    this.f265a.b(jCurrentTimeMillis2);
                                    this.f265a.a(jCurrentTimeMillis);
                                    if (b() != null) {
                                    }
                                    return this.f265a;
                                }
                            }
                            jCurrentTimeMillis2 = j;
                            this.f265a.b(c());
                            this.f265a.a(d());
                            this.f265a.b(jCurrentTimeMillis2);
                            this.f265a.a(jCurrentTimeMillis);
                            if (b() != null) {
                            }
                            return this.f265a;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        httpsURLConnectionA = null;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (httpsURLConnection2 != null) {
                        try {
                            httpsURLConnection2.disconnect();
                        } catch (RuntimeException unused3) {
                            Logger.w(i, "RequestCallableV2 disconnect HttpsURLConnection catch RuntimeException");
                        } catch (Throwable unused4) {
                            Logger.w(i, "RequestCallableV2 disconnect HttpsURLConnection catch Throwable");
                        }
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                httpsURLConnectionA = null;
                j = 0;
            }
            if (httpsURLConnectionA == null) {
                Logger.w(i, "create HttpsURLConnection instance by url return null.");
                if (httpsURLConnectionA != null) {
                    try {
                        httpsURLConnectionA.disconnect();
                    } catch (RuntimeException unused5) {
                        Logger.w(i, "RequestCallableV2 disconnect HttpsURLConnection catch RuntimeException");
                    } catch (Throwable unused6) {
                        Logger.w(i, "RequestCallableV2 disconnect HttpsURLConnection catch Throwable");
                    }
                }
                return null;
            }
            httpsURLConnectionA.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpsURLConnectionA.setRequestMethod("POST");
            httpsURLConnectionA.setDoOutput(true);
            httpsURLConnectionA.setDoInput(true);
            String strA = b() != null ? b().a() : "";
            if (TextUtils.isEmpty(strA)) {
                strA = "&";
            }
            httpsURLConnectionA.setRequestProperty("If-None-Match", strA);
            httpsURLConnectionA.connect();
            com.huawei.hms.framework.network.grs.h.f.a.a(httpsURLConnectionA, f().a("services", ""));
            int responseCode = httpsURLConnectionA.getResponseCode();
            if (responseCode == 200) {
                try {
                    inputStream = httpsURLConnectionA.getInputStream();
                    byte[] byteArray = IoUtils.toByteArray(inputStream);
                    IoUtils.closeSecure(inputStream);
                    bArr = byteArray;
                } catch (Throwable th2) {
                    IoUtils.closeSecure(inputStream);
                    throw th2;
                }
            }
            Map headerFields = httpsURLConnectionA.getHeaderFields();
            httpsURLConnectionA.disconnect();
            long jElapsedRealtime3 = SystemClock.elapsedRealtime();
            jCurrentTimeMillis = System.currentTimeMillis();
            this.f265a = new d(responseCode, headerFields, bArr == null ? new byte[0] : bArr, jElapsedRealtime3 - jElapsedRealtime);
            if (httpsURLConnectionA != null) {
                try {
                    httpsURLConnectionA.disconnect();
                } catch (RuntimeException unused7) {
                    Logger.w(i, "RequestCallableV2 disconnect HttpsURLConnection catch RuntimeException");
                    this.f265a.b(c());
                    this.f265a.a(d());
                    this.f265a.b(jCurrentTimeMillis2);
                    this.f265a.a(jCurrentTimeMillis);
                    if (b() != null) {
                    }
                    return this.f265a;
                } catch (Throwable unused8) {
                    Logger.w(i, "RequestCallableV2 disconnect HttpsURLConnection catch Throwable");
                    this.f265a.b(c());
                    this.f265a.a(d());
                    this.f265a.b(jCurrentTimeMillis2);
                    this.f265a.a(jCurrentTimeMillis);
                    if (b() != null) {
                    }
                    return this.f265a;
                }
            }
            this.f265a.b(c());
            this.f265a.a(d());
            this.f265a.b(jCurrentTimeMillis2);
            this.f265a.a(jCurrentTimeMillis);
            if (b() != null) {
                b().a(this.f265a);
            }
            return this.f265a;
        } catch (Throwable th3) {
            th = th3;
            httpsURLConnection2 = httpsURLConnection;
        }
    }
}
