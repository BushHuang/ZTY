package com.huawei.secure.android.common.ssl;

import android.content.Context;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import com.huawei.secure.android.common.ssl.WebViewSSLCheckThread;
import com.huawei.secure.android.common.ssl.util.g;
import com.huawei.secure.android.common.ssl.util.j;
import java.security.cert.X509Certificate;

public class WebViewSSLCheck {

    private static final String f413a = "WebViewSSLCheck";

    public static void checkServerCertificateNew(SslErrorHandler sslErrorHandler, SslError sslError, Context context) {
        checkServerCertificateNew(sslErrorHandler, sslError, null, context, null);
    }

    public static void checkServerCertificateNew(SslErrorHandler sslErrorHandler, SslError sslError, String str, Context context, WebViewSSLCheckThread.Callback callback) {
        g.c(f413a, " error type : " + sslError.getPrimaryError() + " , cn is : " + sslError.getCertificate().getIssuedTo().getCName());
        X509Certificate x509CertificateA = com.huawei.secure.android.common.ssl.util.b.a(sslError.getCertificate());
        X509Certificate x509CertificateA2 = new j(context).a();
        g.a(f413a, "checkServerCertificateNew: error certificate is : " + x509CertificateA);
        if (com.huawei.secure.android.common.ssl.util.b.a(x509CertificateA2, x509CertificateA)) {
            g.c(f413a, "checkServerCertificateNew: proceed");
            if (callback != null) {
                callback.onProceed(context, str);
                return;
            } else {
                sslErrorHandler.proceed();
                return;
            }
        }
        g.b(f413a, "checkServerCertificateNew: cancel");
        if (callback != null) {
            callback.onCancel(context, str);
        } else {
            sslErrorHandler.cancel();
        }
    }

    public static boolean checkServerCertificateNew(String str, SslError sslError) {
        return checkServerCertificateNew(com.huawei.secure.android.common.ssl.util.b.a(str), sslError);
    }

    public static boolean checkServerCertificateNew(X509Certificate x509Certificate, SslError sslError) {
        return com.huawei.secure.android.common.ssl.util.b.a(x509Certificate, com.huawei.secure.android.common.ssl.util.b.a(sslError.getCertificate()));
    }
}
