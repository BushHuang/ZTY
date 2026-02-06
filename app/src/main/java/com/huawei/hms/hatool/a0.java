package com.huawei.hms.hatool;

import android.text.TextUtils;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactory;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

public abstract class a0 {

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    public static b0 a(String str, byte[] bArr, Map<String, String> map) {
        return a(str, bArr, map, "POST");
    }

    public static b0 a(String str, byte[] bArr, Map<String, String> map, String str2) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        if (TextUtils.isEmpty(str)) {
            return new b0(-100, "");
        }
        int i = -102;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                str = a((String) str, bArr.length, (Map<String, String>) map, str2);
                try {
                    if (str == 0) {
                        b0 b0Var = new b0(-101, "");
                        t0.a((Closeable) null);
                        t0.a((Closeable) null);
                        if (str != 0) {
                            t0.a((HttpURLConnection) str);
                        }
                        return b0Var;
                    }
                    map = str.getOutputStream();
                    try {
                        bufferedOutputStream = new BufferedOutputStream(map);
                    } catch (a unused) {
                    } catch (UnknownHostException unused2) {
                    } catch (SSLHandshakeException unused3) {
                    } catch (SSLPeerUnverifiedException unused4) {
                    } catch (IOException unused5) {
                    } catch (SecurityException unused6) {
                    } catch (ConnectException unused7) {
                    }
                    try {
                        try {
                            bufferedOutputStream.write(bArr);
                            bufferedOutputStream.flush();
                            int responseCode = str.getResponseCode();
                            try {
                                b0 b0Var2 = new b0(responseCode, b(str));
                                t0.a((Closeable) bufferedOutputStream);
                                t0.a((Closeable) map);
                                if (str != 0) {
                                    t0.a((HttpURLConnection) str);
                                }
                                return b0Var2;
                            } catch (SecurityException unused8) {
                                i = responseCode;
                                bufferedOutputStream2 = bufferedOutputStream;
                                y.f("hmsSdk", "SecurityException with HttpClient. Please check INTERNET permission.");
                                b0 b0Var3 = new b0(i, "");
                                t0.a((Closeable) bufferedOutputStream2);
                                t0.a((Closeable) map);
                                if (str != 0) {
                                    t0.a((HttpURLConnection) str);
                                }
                                return b0Var3;
                            } catch (ConnectException unused9) {
                                i = responseCode;
                                bufferedOutputStream2 = bufferedOutputStream;
                                y.f("hmsSdk", "Network is unreachable or Connection refused");
                                b0 b0Var4 = new b0(i, "");
                                t0.a((Closeable) bufferedOutputStream2);
                                t0.a((Closeable) map);
                                if (str != 0) {
                                    t0.a((HttpURLConnection) str);
                                }
                                return b0Var4;
                            } catch (UnknownHostException unused10) {
                                i = responseCode;
                                bufferedOutputStream2 = bufferedOutputStream;
                                y.f("hmsSdk", "No address associated with hostname or No network");
                                b0 b0Var5 = new b0(i, "");
                                t0.a((Closeable) bufferedOutputStream2);
                                t0.a((Closeable) map);
                                if (str != 0) {
                                    t0.a((HttpURLConnection) str);
                                }
                                return b0Var5;
                            } catch (SSLHandshakeException unused11) {
                                i = responseCode;
                                bufferedOutputStream2 = bufferedOutputStream;
                                y.f("hmsSdk", "Chain validation failed,Certificate expired");
                                b0 b0Var6 = new b0(i, "");
                                t0.a((Closeable) bufferedOutputStream2);
                                t0.a((Closeable) map);
                                if (str != 0) {
                                    t0.a((HttpURLConnection) str);
                                }
                                return b0Var6;
                            } catch (SSLPeerUnverifiedException unused12) {
                                i = responseCode;
                                bufferedOutputStream2 = bufferedOutputStream;
                                y.f("hmsSdk", "Certificate has not been verified,Request is restricted!");
                                b0 b0Var7 = new b0(i, "");
                                t0.a((Closeable) bufferedOutputStream2);
                                t0.a((Closeable) map);
                                if (str != 0) {
                                    t0.a((HttpURLConnection) str);
                                }
                                return b0Var7;
                            } catch (IOException unused13) {
                                i = responseCode;
                                bufferedOutputStream2 = bufferedOutputStream;
                                y.f("hmsSdk", "events PostRequest(byte[]): IOException occurred.");
                                b0 b0Var8 = new b0(i, "");
                                t0.a((Closeable) bufferedOutputStream2);
                                t0.a((Closeable) map);
                                if (str != 0) {
                                    t0.a((HttpURLConnection) str);
                                }
                                return b0Var8;
                            }
                        } catch (ConnectException unused14) {
                        } catch (UnknownHostException unused15) {
                        } catch (SSLHandshakeException unused16) {
                        } catch (IOException unused17) {
                        } catch (SecurityException unused18) {
                        } catch (SSLPeerUnverifiedException unused19) {
                        }
                    } catch (a unused20) {
                        bufferedOutputStream2 = bufferedOutputStream;
                        y.f("hmsSdk", "PostRequest(byte[]): No ssl socket factory set!");
                        b0 b0Var9 = new b0(-101, "");
                        t0.a((Closeable) bufferedOutputStream2);
                        t0.a((Closeable) map);
                        if (str != 0) {
                            t0.a((HttpURLConnection) str);
                        }
                        return b0Var9;
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream2 = bufferedOutputStream;
                        t0.a((Closeable) bufferedOutputStream2);
                        t0.a((Closeable) map);
                        if (str != 0) {
                            t0.a((HttpURLConnection) str);
                        }
                        throw th;
                    }
                } catch (a unused21) {
                    map = 0;
                } catch (SecurityException unused22) {
                    map = 0;
                } catch (ConnectException unused23) {
                    map = 0;
                } catch (UnknownHostException unused24) {
                    map = 0;
                } catch (SSLHandshakeException unused25) {
                    map = 0;
                } catch (SSLPeerUnverifiedException unused26) {
                    map = 0;
                } catch (IOException unused27) {
                    map = 0;
                } catch (Throwable th2) {
                    th = th2;
                    map = 0;
                }
            } catch (a unused28) {
                str = 0;
                map = 0;
            } catch (SecurityException unused29) {
                str = 0;
                map = 0;
            } catch (ConnectException unused30) {
                str = 0;
                map = 0;
            } catch (UnknownHostException unused31) {
                str = 0;
                map = 0;
            } catch (SSLHandshakeException unused32) {
                str = 0;
                map = 0;
            } catch (SSLPeerUnverifiedException unused33) {
                str = 0;
                map = 0;
            } catch (IOException unused34) {
                str = 0;
                map = 0;
            } catch (Throwable th3) {
                th = th3;
                str = 0;
                map = 0;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static HttpURLConnection a(String str, int i, Map<String, String> map, String str2) throws ProtocolException, IllegalArgumentException, a {
        if (TextUtils.isEmpty(str)) {
            y.b("hmsSdk", "CreateConnection: invalid urlPath.");
            return null;
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        a(httpURLConnection);
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(i));
        httpURLConnection.setRequestProperty("Connection", "close");
        if (map != null && map.size() >= 1) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key != null && !TextUtils.isEmpty(key)) {
                    httpURLConnection.setRequestProperty(key, entry.getValue());
                }
            }
        }
        return httpURLConnection;
    }

    public static void a(HttpURLConnection httpURLConnection) throws IllegalArgumentException, a {
        String str;
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            SecureSSLSocketFactory secureSSLSocketFactory = null;
            try {
                secureSSLSocketFactory = SecureSSLSocketFactory.getInstance(b.i());
            } catch (IOException unused) {
                str = "getSocketFactory(): IO Exception!";
                y.f("hmsSdk", str);
                if (secureSSLSocketFactory != null) {
                }
            } catch (IllegalAccessException unused2) {
                str = "getSocketFactory(): Illegal Access Exception ";
                y.f("hmsSdk", str);
                if (secureSSLSocketFactory != null) {
                }
            } catch (KeyStoreException unused3) {
                str = "getSocketFactory(): Key Store exception";
                y.f("hmsSdk", str);
                if (secureSSLSocketFactory != null) {
                }
            } catch (NoSuchAlgorithmException unused4) {
                str = "getSocketFactory(): Algorithm Exception!";
                y.f("hmsSdk", str);
                if (secureSSLSocketFactory != null) {
                }
            } catch (GeneralSecurityException unused5) {
                str = "getSocketFactory(): General Security Exception";
                y.f("hmsSdk", str);
                if (secureSSLSocketFactory != null) {
                }
            }
            if (secureSSLSocketFactory != null) {
                throw new a("No ssl socket factory set");
            }
            httpsURLConnection.setSSLSocketFactory(secureSSLSocketFactory);
            httpsURLConnection.setHostnameVerifier(new StrictHostnameVerifier());
        }
    }

    public static String b(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            try {
                inputStream = httpURLConnection.getInputStream();
                return t0.a(inputStream);
            } catch (IOException unused) {
                y.f("hmsSdk", "When Response Content From Connection inputStream operation exception! " + httpURLConnection.getResponseCode());
                t0.a((Closeable) inputStream);
                return "";
            }
        } finally {
            t0.a((Closeable) inputStream);
        }
    }
}
