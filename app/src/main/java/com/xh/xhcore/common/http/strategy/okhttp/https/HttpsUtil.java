package com.xh.xhcore.common.http.strategy.okhttp.https;

import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.statistic.XHEnvironment;
import com.xh.xhcore.common.util.XHFileUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Iterator;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0012H\u0007J)\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u0012H\u0007¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004H\u0003J\b\u0010\u001d\u001a\u00020\u001bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001e"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/https/HttpsUtil;", "", "()V", "CERTIFICATE_FILE_NAME_CLIENT_KEY", "", "CERTIFICATE_FILE_NAME_CLIENT_PEM", "CERTIFICATE_TYPE", "CLIENT_PASSWORD", "KEYSTORE_TYPE_CLIENT", "getKEYSTORE_TYPE_CLIENT", "()Ljava/lang/String;", "SSL_PROTOCOL", "XUE_HAI_LOCAL_PATCH", "getXUE_HAI_LOCAL_PATCH", "getCertificateKeyStore", "Ljava/security/KeyStore;", "certificates", "", "Ljava/io/InputStream;", "([Ljava/io/InputStream;)Ljava/security/KeyStore;", "getClientKeyStore", "certificatesClient", "getSSLSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "certificatesServer", "([Ljava/io/InputStream;Ljava/io/InputStream;)Ljavax/net/ssl/SSLSocketFactory;", "writeFileIfAbsent", "", "fileName", "writeFileIfAbsentForCurlHttps", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HttpsUtil {
    public static final HttpsUtil INSTANCE = new HttpsUtil();
    private static final String CLIENT_PASSWORD = "654321";
    private static final String SSL_PROTOCOL = "TLS";
    private static final String KEYSTORE_TYPE_CLIENT = "BKS";
    private static final String CERTIFICATE_TYPE = "X.509";
    private static final String CERTIFICATE_FILE_NAME_CLIENT_PEM = "client.pem";
    private static final String CERTIFICATE_FILE_NAME_CLIENT_KEY = "client.key";
    private static final String XUE_HAI_LOCAL_PATCH = XHEnvironment.getExternalStorageDirectory().getAbsolutePath() + ((Object) File.separator) + "xuehai" + ((Object) File.separator);

    private HttpsUtil() {
    }

    @JvmStatic
    public static final KeyStore getCertificateKeyStore(InputStream[] certificates) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null);
        if (certificates != null) {
            int i = 0;
            if (!(certificates.length == 0)) {
                CertificateFactory certificateFactory = CertificateFactory.getInstance(CERTIFICATE_TYPE);
                Iterator it = ArrayIteratorKt.iterator(certificates);
                while (it.hasNext()) {
                    InputStream inputStream = (InputStream) it.next();
                    int i2 = i + 1;
                    String string = Integer.toString(i);
                    Intrinsics.checkNotNullExpressionValue(string, "toString(index++)");
                    keyStore.setCertificateEntry(string, certificateFactory.generateCertificate(inputStream));
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    i = i2;
                }
            }
        }
        Intrinsics.checkNotNullExpressionValue(keyStore, "keyStore");
        return keyStore;
    }

    @JvmStatic
    public static final KeyStore getClientKeyStore(InputStream certificatesClient) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        Intrinsics.checkNotNullParameter(certificatesClient, "certificatesClient");
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE_CLIENT);
        char[] charArray = CLIENT_PASSWORD.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        keyStore.load(certificatesClient, charArray);
        Intrinsics.checkNotNullExpressionValue(keyStore, "clientKeyStore");
        return keyStore;
    }

    @JvmStatic
    public static final SSLSocketFactory getSSLSocketFactory(InputStream[] certificatesServer, InputStream certificatesClient) {
        TrustManager[] trustManagers;
        KeyManager[] keyManagers;
        try {
            if (certificatesServer == null) {
                X509TrustManager x509TrustManagerPlatformTrustManager = Util.platformTrustManager();
                Intrinsics.checkNotNullExpressionValue(x509TrustManagerPlatformTrustManager, "platformTrustManager()");
                trustManagers = new TrustManager[]{x509TrustManagerPlatformTrustManager};
            } else {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(getCertificateKeyStore(certificatesServer));
                trustManagers = trustManagerFactory.getTrustManagers();
                Intrinsics.checkNotNullExpressionValue(trustManagers, "trustManagerFactory.trustManagers");
            }
            if (certificatesClient == null) {
                keyManagers = null;
            } else {
                KeyStore clientKeyStore = getClientKeyStore(certificatesClient);
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                char[] charArray = CLIENT_PASSWORD.toCharArray();
                Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
                keyManagerFactory.init(clientKeyStore, charArray);
                keyManagers = keyManagerFactory.getKeyManagers();
            }
            SSLContext sSLContext = Platform.get().getSSLContext();
            sSLContext.init(keyManagers, trustManagers, null);
            return sSLContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    private static final void writeFileIfAbsent(String fileName) throws Throwable {
        String strStringPlus = Intrinsics.stringPlus(XUE_HAI_LOCAL_PATCH, fileName);
        try {
            InputStream inputStreamOpen = XhBaseApplication.mContext.getAssets().open(fileName);
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "mContext.assets.open(fileName)");
            XHFileUtil.writeFileFromISIfAbsent(strStringPlus, inputStreamOpen, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated(message = "")
    @JvmStatic
    public static final void writeFileIfAbsentForCurlHttps() {
    }

    public final String getKEYSTORE_TYPE_CLIENT() {
        return KEYSTORE_TYPE_CLIENT;
    }

    public final String getXUE_HAI_LOCAL_PATCH() {
        return XUE_HAI_LOCAL_PATCH;
    }
}
