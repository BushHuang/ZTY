package com.obs.services;

import com.obs.services.model.AuthTypeEnum;
import com.obs.services.model.HttpProtocolTypeEnum;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import okhttp3.Dispatcher;

public class ObsConfiguration implements Cloneable {
    private int bufferSize;
    private int connectionRequestTimeout;
    private Dispatcher httpDispatcher;
    private HttpProxyConfiguration httpProxy;
    private boolean isNio;
    private KeyManagerFactory keyManagerFactory;
    private String sslProvider;
    private TrustManagerFactory trustManagerFactory;
    private boolean useReaper;
    private int connectionTimeout = 60000;
    private int maxConnections = 1000;
    private int maxErrorRetry = 3;
    private int socketTimeout = 60000;
    private int endpointHttpPort = 80;
    private int endpointHttpsPort = 443;
    private boolean httpsOnly = true;
    private String endPoint = "";
    private boolean pathStyle = false;
    private boolean validateCertificate = false;
    private boolean verifyResponseContentType = true;
    private boolean isStrictHostnameVerification = false;
    private int uploadStreamRetryBufferSize = -1;
    private int socketWriteBufferSize = -1;
    private int socketReadBufferSize = -1;
    private int readBufferSize = -1;
    private int writeBufferSize = -1;
    private int idleConnectionTime = 30000;
    private int maxIdleConnections = 1000;
    private AuthTypeEnum authType = AuthTypeEnum.OBS;
    private boolean keepAlive = true;
    private String signatString = "";
    private String defaultBucketLocation = "";
    private boolean authTypeNegotiation = true;
    private boolean cname = false;
    private String delimiter = "/";
    private HttpProtocolTypeEnum httpProtocolType = HttpProtocolTypeEnum.HTTP1_1;

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Deprecated
    public void disableNio() {
        this.isNio = false;
    }

    @Deprecated
    public void enableNio() {
        this.isNio = true;
    }

    public AuthTypeEnum getAuthType() {
        return this.authType;
    }

    @Deprecated
    public int getBufferSize() {
        return this.bufferSize;
    }

    @Deprecated
    public int getConnectionRequestTimeout() {
        return this.connectionRequestTimeout;
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    @Deprecated
    public String getDefaultBucketLocation() {
        return this.defaultBucketLocation;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public String getEndPoint() {
        String str = this.endPoint;
        if (str == null || str.trim().equals("")) {
            throw new IllegalArgumentException("EndPoint is not set");
        }
        return this.endPoint.trim();
    }

    @Deprecated
    public int getEndpointHttpPort() {
        return this.endpointHttpPort;
    }

    @Deprecated
    public int getEndpointHttpsPort() {
        return this.endpointHttpsPort;
    }

    public Dispatcher getHttpDispatcher() {
        return this.httpDispatcher;
    }

    public HttpProtocolTypeEnum getHttpProtocolType() {
        return this.httpProtocolType;
    }

    public HttpProxyConfiguration getHttpProxy() {
        return this.httpProxy;
    }

    public int getIdleConnectionTime() {
        return this.idleConnectionTime;
    }

    public KeyManagerFactory getKeyManagerFactory() {
        return this.keyManagerFactory;
    }

    public int getMaxConnections() {
        return this.maxConnections;
    }

    public int getMaxErrorRetry() {
        return this.maxErrorRetry;
    }

    public int getMaxIdleConnections() {
        return this.maxIdleConnections;
    }

    public int getReadBufferSize() {
        return this.readBufferSize;
    }

    @Deprecated
    public String getSignatString() {
        return this.signatString;
    }

    public int getSocketReadBufferSize() {
        return this.socketReadBufferSize;
    }

    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    public int getSocketWriteBufferSize() {
        return this.socketWriteBufferSize;
    }

    public String getSslProvider() {
        return this.sslProvider;
    }

    public TrustManagerFactory getTrustManagerFactory() {
        return this.trustManagerFactory;
    }

    @Deprecated
    public int getUploadStreamRetryBufferSize() {
        return this.uploadStreamRetryBufferSize;
    }

    public int getWriteBufferSize() {
        return this.writeBufferSize;
    }

    public boolean isAuthTypeNegotiation() {
        return this.authTypeNegotiation;
    }

    public boolean isCname() {
        return this.cname;
    }

    @Deprecated
    public boolean isDisableDnsBucket() {
        return isPathStyle();
    }

    @Deprecated
    public boolean isHttpsOnly() {
        return this.httpsOnly;
    }

    public boolean isKeepAlive() {
        return this.keepAlive;
    }

    @Deprecated
    public boolean isNio() {
        return this.isNio;
    }

    @Deprecated
    public boolean isPathStyle() {
        return this.pathStyle;
    }

    public boolean isStrictHostnameVerification() {
        return this.isStrictHostnameVerification;
    }

    @Deprecated
    public boolean isUseReaper() {
        return this.useReaper;
    }

    public boolean isValidateCertificate() {
        return this.validateCertificate;
    }

    public boolean isVerifyResponseContentType() {
        return this.verifyResponseContentType;
    }

    public void setAuthType(AuthTypeEnum authTypeEnum) {
        this.authType = authTypeEnum;
    }

    public void setAuthTypeNegotiation(boolean z) {
        this.authTypeNegotiation = z;
    }

    @Deprecated
    public void setBufferSize(int i) {
        this.bufferSize = i;
    }

    public void setCname(boolean z) {
        this.cname = z;
    }

    @Deprecated
    public void setConnectionRequestTimeout(int i) {
        this.connectionRequestTimeout = i;
    }

    public void setConnectionTimeout(int i) {
        this.connectionTimeout = i;
    }

    @Deprecated
    public void setDefaultBucketLocation(String str) {
        this.defaultBucketLocation = str;
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }

    @Deprecated
    public void setDisableDnsBucket(boolean z) {
        setPathStyle(z);
    }

    public void setEndPoint(String str) {
        this.endPoint = str;
    }

    @Deprecated
    public void setEndpointHttpPort(int i) {
        this.endpointHttpPort = i;
    }

    @Deprecated
    public void setEndpointHttpsPort(int i) {
        this.endpointHttpsPort = i;
    }

    public void setHttpDispatcher(Dispatcher dispatcher) {
        this.httpDispatcher = dispatcher;
    }

    public void setHttpProtocolType(HttpProtocolTypeEnum httpProtocolTypeEnum) {
        this.httpProtocolType = httpProtocolTypeEnum;
    }

    public void setHttpProxy(HttpProxyConfiguration httpProxyConfiguration) {
        this.httpProxy = httpProxyConfiguration;
    }

    public void setHttpProxy(String str, int i, String str2, String str3) {
        this.httpProxy = new HttpProxyConfiguration(str, i, str2, str3, null);
    }

    @Deprecated
    public void setHttpProxy(String str, int i, String str2, String str3, String str4) {
        this.httpProxy = new HttpProxyConfiguration(str, i, str2, str3, str4);
    }

    @Deprecated
    public void setHttpsOnly(boolean z) {
        this.httpsOnly = z;
    }

    public void setIdleConnectionTime(int i) {
        this.idleConnectionTime = i;
    }

    public void setIsStrictHostnameVerification(boolean z) {
        this.isStrictHostnameVerification = z;
    }

    public void setKeepAlive(boolean z) {
        this.keepAlive = z;
    }

    public void setKeyManagerFactory(KeyManagerFactory keyManagerFactory) {
        this.keyManagerFactory = keyManagerFactory;
    }

    public void setMaxConnections(int i) {
        this.maxConnections = i;
    }

    public void setMaxErrorRetry(int i) {
        this.maxErrorRetry = i;
    }

    public void setMaxIdleConnections(int i) {
        this.maxIdleConnections = i;
    }

    @Deprecated
    public void setPathStyle(boolean z) {
        this.pathStyle = z;
    }

    public void setReadBufferSize(int i) {
        this.readBufferSize = i;
    }

    @Deprecated
    public void setSignatString(String str) {
        this.signatString = str;
    }

    public void setSocketReadBufferSize(int i) {
        this.socketReadBufferSize = i;
    }

    public void setSocketTimeout(int i) {
        this.socketTimeout = i;
    }

    public void setSocketWriteBufferSize(int i) {
        this.socketWriteBufferSize = i;
    }

    public void setSslProvider(String str) {
        this.sslProvider = str;
    }

    public void setTrustManagerFactory(TrustManagerFactory trustManagerFactory) {
        this.trustManagerFactory = trustManagerFactory;
    }

    @Deprecated
    public void setUploadStreamRetryBufferSize(int i) {
        this.uploadStreamRetryBufferSize = i;
    }

    @Deprecated
    public void setUseReaper(boolean z) {
        this.useReaper = z;
    }

    public void setValidateCertificate(boolean z) {
        this.validateCertificate = z;
    }

    public void setVerifyResponseContentType(boolean z) {
        this.verifyResponseContentType = z;
    }

    public void setWriteBufferSize(int i) {
        this.writeBufferSize = i;
    }
}
