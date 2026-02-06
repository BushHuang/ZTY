package com.analysys;

import android.content.Context;
import android.text.TextUtils;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

class o {
    static long a(String str) {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        try {
            URL url = new URL(str);
            if (str.startsWith("https://")) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpURLConnection = httpsURLConnection;
                if (CommonUtils.getSSLSocketFactory(AnalysysUtil.getContext()) != null) {
                    httpsURLConnection.setSSLSocketFactory(CommonUtils.getSSLSocketFactory(AnalysysUtil.getContext()));
                    httpURLConnection = httpsURLConnection;
                }
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            httpURLConnection2 = httpURLConnection;
            httpURLConnection2.setRequestMethod("GET");
            httpURLConnection2.setConnectTimeout(20000);
            httpURLConnection2.setReadTimeout(20000);
            httpURLConnection2.connect();
            long date = httpURLConnection2.getDate();
        } catch (Throwable th) {
            try {
                ExceptionUtil.exceptionPrint(th);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return 0L;
            } finally {
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
            }
        }
    }

    static String a(Context context, String str, String str2, String str3, Map<String, String> map) {
        OutputStream outputStream;
        HttpsURLConnection httpsURLConnection;
        OutputStream outputStream2;
        try {
            HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) new URL(str).openConnection();
            try {
                if (CommonUtils.getSSLSocketFactory(context) != null) {
                    httpsURLConnection2.setSSLSocketFactory(CommonUtils.getSSLSocketFactory(context));
                }
                httpsURLConnection2.setDoInput(true);
                httpsURLConnection2.setDoOutput(true);
                httpsURLConnection2.setUseCaches(false);
                httpsURLConnection2.setRequestMethod("POST");
                httpsURLConnection2.setConnectTimeout(20000);
                httpsURLConnection2.setReadTimeout(20000);
                httpsURLConnection2.setRequestProperty("spv", str3);
                if (map != null) {
                    for (String str4 : map.keySet()) {
                        httpsURLConnection2.setRequestProperty(str4, map.get(str4));
                    }
                }
                httpsURLConnection2.connect();
                if (TextUtils.isEmpty(str2)) {
                    outputStream2 = null;
                } else {
                    outputStream2 = httpsURLConnection2.getOutputStream();
                    try {
                        outputStream2.write(str2.getBytes("UTF-8"));
                    } catch (Throwable th) {
                        outputStream = outputStream2;
                        th = th;
                        httpsURLConnection = httpsURLConnection2;
                        try {
                            ExceptionUtil.exceptionPrint(th);
                            return null;
                        } finally {
                            if (httpsURLConnection != null) {
                                httpsURLConnection.disconnect();
                            }
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th2) {
                                    ExceptionUtil.exceptionPrint(th2);
                                }
                            }
                        }
                    }
                }
                if (httpsURLConnection2.getResponseCode() != 200) {
                    if (httpsURLConnection2 != null) {
                        httpsURLConnection2.disconnect();
                    }
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Throwable th3) {
                            ExceptionUtil.exceptionPrint(th3);
                        }
                    }
                    return null;
                }
                String stream = CommonUtils.readStream(httpsURLConnection2.getInputStream());
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
                if (outputStream2 != null) {
                    try {
                        outputStream2.close();
                    } catch (Throwable th4) {
                        ExceptionUtil.exceptionPrint(th4);
                    }
                }
                return stream;
            } catch (Throwable th5) {
                th = th5;
                httpsURLConnection = httpsURLConnection2;
                outputStream = null;
            }
        } catch (Throwable th6) {
            th = th6;
            outputStream = null;
            httpsURLConnection = null;
        }
    }

    static String a(String str, String str2, String str3, Map<String, String> map) {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream = null;
        try {
            byte[] bArr = new byte[1024];
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("spv", str3);
            if (map != null) {
                for (String str4 : map.keySet()) {
                    httpURLConnection.setRequestProperty(str4, map.get(str4));
                }
            }
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            printWriter.print(str2);
            printWriter.flush();
            printWriter.close();
            if (200 != httpURLConnection.getResponseCode()) {
                return 413 == httpURLConnection.getResponseCode() ? "413" : "";
            }
            InputStream inputStream2 = httpURLConnection.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int i = inputStream2.read(bArr);
                        if (-1 == i) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                    } catch (Throwable th) {
                        inputStream = inputStream2;
                        th = th;
                        try {
                            ExceptionUtil.exceptionPrint(th);
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th2) {
                                    ExceptionUtil.exceptionPrint(th2);
                                }
                            }
                            if (byteArrayOutputStream == null) {
                                return "";
                            }
                            try {
                                byteArrayOutputStream.close();
                                return "";
                            } catch (Throwable th3) {
                                ExceptionUtil.exceptionPrint(th3);
                                return "";
                            }
                        } finally {
                        }
                    }
                }
                byteArrayOutputStream.flush();
                String string = byteArrayOutputStream.toString("utf-8");
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (Throwable th4) {
                        ExceptionUtil.exceptionPrint(th4);
                    }
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th5) {
                    ExceptionUtil.exceptionPrint(th5);
                }
                return string;
            } catch (Throwable th6) {
                inputStream = inputStream2;
                th = th6;
                byteArrayOutputStream = null;
            }
        } catch (Throwable th7) {
            th = th7;
            byteArrayOutputStream = null;
        }
    }
}
