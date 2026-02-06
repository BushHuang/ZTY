package com.obs.services.internal.utils;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import com.obs.services.internal.ObsProperties;
import com.obs.services.internal.RestStorageService;
import com.obs.services.internal.ServiceException;
import com.obs.services.model.HttpProtocolTypeEnum;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Authenticator;
import okhttp3.ConnectionPool;
import okhttp3.Credentials;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class RestUtils {
    private static final ILogger log = LoggerBuilder.getLogger((Class<?>) RestUtils.class);
    private static Pattern chinesePattern = Pattern.compile("[一-龥]");
    private static final HostnameVerifier ALLOW_ALL_HOSTNAME = new HostnameVerifier() {
        @Override
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };
    private static final X509TrustManager TRUST_ALL_MANAGER = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

    private static class WrapperedSSLSocketFactory extends SSLSocketFactory {
        private SSLSocketFactory delegate;
        private int socketReadWriteBufferSize;

        WrapperedSSLSocketFactory(SSLSocketFactory sSLSocketFactory, int i) {
            this.delegate = sSLSocketFactory;
            this.socketReadWriteBufferSize = i;
        }

        private Socket doWrap(Socket socket) throws SocketException {
            if (socket != null) {
                int i = this.socketReadWriteBufferSize;
                if (i > 0) {
                    socket.setReceiveBufferSize(i);
                    socket.setReceiveBufferSize(this.socketReadWriteBufferSize);
                }
                socket.setTcpNoDelay(true);
            }
            return socket;
        }

        @Override
        public Socket createSocket() throws IOException {
            return doWrap(this.delegate.createSocket());
        }

        @Override
        public Socket createSocket(String str, int i) throws IOException {
            return doWrap(this.delegate.createSocket(str, i));
        }

        @Override
        public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
            return doWrap(this.delegate.createSocket(str, i, inetAddress, i2));
        }

        @Override
        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return doWrap(this.delegate.createSocket(inetAddress, i));
        }

        @Override
        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return doWrap(this.delegate.createSocket(inetAddress, i, inetAddress2, i2));
        }

        @Override
        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return doWrap(this.delegate.createSocket(socket, str, i, z));
        }

        @Override
        public String[] getDefaultCipherSuites() {
            return this.delegate.getDefaultCipherSuites();
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return this.delegate.getSupportedCipherSuites();
        }
    }

    private static class WrapperedSocketFactory extends SocketFactory {
        private SocketFactory delegate;
        private int socketReadWriteBufferSize;

        WrapperedSocketFactory(SocketFactory socketFactory, int i) {
            this.delegate = socketFactory;
            this.socketReadWriteBufferSize = i;
        }

        private Socket doWrap(Socket socket) throws SocketException {
            if (socket != null) {
                int i = this.socketReadWriteBufferSize;
                if (i > 0) {
                    socket.setReceiveBufferSize(i);
                    socket.setReceiveBufferSize(this.socketReadWriteBufferSize);
                }
                socket.setTcpNoDelay(true);
            }
            return socket;
        }

        @Override
        public Socket createSocket() throws IOException {
            return doWrap(this.delegate.createSocket());
        }

        @Override
        public Socket createSocket(String str, int i) throws IOException {
            return doWrap(this.delegate.createSocket(str, i));
        }

        @Override
        public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
            return doWrap(this.delegate.createSocket(str, i, inetAddress, i2));
        }

        @Override
        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return doWrap(this.delegate.createSocket(inetAddress, i));
        }

        @Override
        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return doWrap(this.delegate.createSocket(inetAddress, i, inetAddress2, i2));
        }
    }

    private static SSLContext createSSLContext(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr) throws Exception {
        SSLContext sSLContext;
        try {
            try {
                try {
                    sSLContext = SSLContext.getInstance("TLSv1.2");
                } catch (Exception unused) {
                    sSLContext = SSLContext.getInstance("TLS");
                }
            } catch (Exception unused2) {
                sSLContext = SSLContext.getInstance("TLSv1.1");
            }
        } catch (Exception unused3) {
            sSLContext = SSLContext.getInstance("TLSv1.0");
        }
        sSLContext.init(keyManagerArr, trustManagerArr, new SecureRandom());
        return sSLContext;
    }

    private static SSLContext createSSLContext(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, String str) throws Exception {
        SSLContext sSLContext;
        try {
            try {
                try {
                    sSLContext = SSLContext.getInstance("TLSv1.2", str);
                } catch (Exception unused) {
                    sSLContext = SSLContext.getInstance("TLS", str);
                }
            } catch (Exception unused2) {
                sSLContext = SSLContext.getInstance("TLSv1.1", str);
            }
        } catch (Exception unused3) {
            sSLContext = SSLContext.getInstance("TLSv1.0", str);
        }
        sSLContext.init(keyManagerArr, trustManagerArr, new SecureRandom());
        return sSLContext;
    }

    public static String encodeUrlPath(String str, String str2) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        String[] strArrSplit = str.split(str2);
        for (int i = 0; i < strArrSplit.length; i++) {
            sb.append(encodeUrlString(strArrSplit[i]));
            if (i < strArrSplit.length - 1) {
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    public static String encodeUrlString(String str) throws ServiceException {
        try {
            return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20").replaceAll("%7E", "~").replaceAll("\\*", "%2A");
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("Unable to encode path: " + str, e);
        }
    }

    public static OkHttpClient.Builder initHttpClientBuilder(RestStorageService restStorageService, ObsProperties obsProperties, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory, Dispatcher dispatcher) {
        X509TrustManager x509TrustManager;
        TrustManager[] trustManagers;
        KeyManager[] keyManagers;
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(Protocol.HTTP_1_1);
        if (HttpProtocolTypeEnum.getValueFromCode(obsProperties.getStringProperty("httpclient.protocol", HttpProtocolTypeEnum.HTTP1_1.getCode())) == HttpProtocolTypeEnum.HTTP2_0) {
            arrayList.add(Protocol.HTTP_2);
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (dispatcher == null) {
            int intProperty = obsProperties.getIntProperty("httpclient.max-connections", 1000);
            Dispatcher dispatcher2 = new Dispatcher();
            dispatcher2.setMaxRequests(intProperty);
            dispatcher2.setMaxRequestsPerHost(intProperty);
            builder.dispatcher(dispatcher2);
        } else {
            try {
                try {
                    builder.getClass().getMethod("dispatcher", dispatcher.getClass()).invoke(builder, dispatcher);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception unused) {
                builder.getClass().getMethod("dispatcher", Class.forName("okhttp3.AbsDispatcher")).invoke(builder, dispatcher);
            }
        }
        ConnectionPool connectionPool = new ConnectionPool(obsProperties.getIntProperty("httpclient.max-idle-connections", 1000), obsProperties.getIntProperty("httpclient.idle-connection-time", 30000), TimeUnit.MILLISECONDS);
        OkHttpClient.Builder builderRetryOnConnectionFailure = builder.protocols(arrayList).followRedirects(false).followSslRedirects(false).retryOnConnectionFailure(false);
        SSLContext sSLContextCreateSSLContext = null;
        builderRetryOnConnectionFailure.cache(null).connectTimeout(obsProperties.getIntProperty("httpclient.connection-timeout-ms", 60000), TimeUnit.MILLISECONDS).writeTimeout(obsProperties.getIntProperty("httpclient.socket-timeout-ms", 60000), TimeUnit.MILLISECONDS).readTimeout(obsProperties.getIntProperty("httpclient.socket-timeout-ms", 60000), TimeUnit.MILLISECONDS).connectionPool(connectionPool).hostnameVerifier(obsProperties.getBoolProperty("httpclient.strict-hostname-verification", false) ? HttpsURLConnection.getDefaultHostnameVerifier() : ALLOW_ALL_HOSTNAME);
        int iMax = Math.max(obsProperties.getIntProperty("socket.read-buffer-size", -1), obsProperties.getIntProperty("socket.write-buffer-size", -1));
        builder.socketFactory(new WrapperedSocketFactory(SocketFactory.getDefault(), iMax));
        try {
            if (obsProperties.getBoolProperty("httpclient.validate-certificate", false)) {
                keyManagers = keyManagerFactory == null ? null : keyManagerFactory.getKeyManagers();
                if (trustManagerFactory == null || trustManagerFactory.getTrustManagers().length < 1) {
                    trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    trustManagerFactory.init((KeyStore) null);
                }
                trustManagers = trustManagerFactory.getTrustManagers();
                x509TrustManager = (X509TrustManager) trustManagers[0];
            } else {
                x509TrustManager = TRUST_ALL_MANAGER;
                trustManagers = new TrustManager[]{x509TrustManager};
                keyManagers = null;
            }
            String stringProperty = obsProperties.getStringProperty("httpclient.ssl-provider", "");
            if (ServiceUtils.isValid(stringProperty)) {
                try {
                    sSLContextCreateSSLContext = createSSLContext(keyManagers, trustManagers, stringProperty);
                } catch (Exception e2) {
                    if (log.isErrorEnabled()) {
                        log.error("Exception happened in create ssl context with provider" + stringProperty, e2);
                    }
                }
            }
            if (sSLContextCreateSSLContext == null) {
                sSLContextCreateSSLContext = createSSLContext(keyManagers, trustManagers);
            }
            builder.sslSocketFactory(new WrapperedSSLSocketFactory(sSLContextCreateSSLContext.getSocketFactory(), iMax), x509TrustManager);
        } catch (Exception e3) {
            if (log.isErrorEnabled()) {
                log.error((CharSequence) ("Exception happened in HttpClient.configSSL,and e = " + e3));
            }
        }
        return builder;
    }

    public static void initHttpProxy(OkHttpClient.Builder builder, String str, int i, final String str2, final String str3, String str4, String str5) {
        if (str == null || i == -1) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info((CharSequence) ("Using Proxy: " + str + ":" + i));
        }
        builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i)));
        if (str2 == null || str2.trim().equals("")) {
            return;
        }
        builder.proxyAuthenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                return response.request().newBuilder().header("Proxy-Authorization", Credentials.basic(str2, str3)).build();
            }
        });
    }

    public static String uriEncode(CharSequence charSequence, boolean z) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        try {
            if (z) {
                while (i < charSequence.length()) {
                    char cCharAt = charSequence.charAt(i);
                    String string = Character.toString(cCharAt);
                    Matcher matcher = chinesePattern.matcher(string);
                    if (matcher == null || !matcher.find()) {
                        sb.append(cCharAt);
                    } else {
                        sb.append(URLEncoder.encode(string, "UTF-8"));
                    }
                    i++;
                }
            } else {
                while (i < charSequence.length()) {
                    char cCharAt2 = charSequence.charAt(i);
                    if ((cCharAt2 >= 'A' && cCharAt2 <= 'Z') || ((cCharAt2 >= 'a' && cCharAt2 <= 'z') || ((cCharAt2 >= '0' && cCharAt2 <= '9') || cCharAt2 == '_' || cCharAt2 == '-' || cCharAt2 == '~' || cCharAt2 == '.'))) {
                        sb.append(cCharAt2);
                    } else if (cCharAt2 == '/') {
                        sb.append("%2F");
                    } else {
                        sb.append(URLEncoder.encode(Character.toString(cCharAt2), "UTF-8"));
                    }
                    i++;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            throw new ServiceException("Unable to encode input: " + ((Object) charSequence));
        }
    }
}
