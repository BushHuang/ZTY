package com.obs.services.internal;

import com.obs.log.ILogger;
import com.obs.log.InterfaceLogBean;
import com.obs.log.LoggerBuilder;
import com.obs.services.internal.consensus.CacheManager;
import com.obs.services.internal.consensus.SegmentLock;
import com.obs.services.internal.handler.XmlResponsesSaxParser;
import com.obs.services.internal.io.UnrecoverableIOException;
import com.obs.services.internal.security.ProviderCredentialThreadContext;
import com.obs.services.internal.security.ProviderCredentials;
import com.obs.services.internal.utils.IAuthentication;
import com.obs.services.internal.utils.JSONChange;
import com.obs.services.internal.utils.RestUtils;
import com.obs.services.internal.utils.ServiceUtils;
import com.obs.services.internal.utils.V4Authentication;
import com.obs.services.model.AuthTypeEnum;
import com.obs.services.model.HttpMethodEnum;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.URI;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class RestStorageService {
    private static final String REQUEST_TIMEOUT_CODE = "RequestTimeout";
    private static final ILogger log = LoggerBuilder.getLogger((Class<?>) RestStorageService.class);
    private static final Set<Class<? extends IOException>> nonRetriableClasses;
    protected CacheManager apiVersionCache;
    protected volatile ProviderCredentials credentials;
    protected OkHttpClient httpClient;
    protected KeyManagerFactory keyManagerFactory;
    protected ObsProperties obsProperties;
    protected SegmentLock segmentLock;
    protected Semaphore semaphore;
    protected AtomicBoolean shuttingDown = new AtomicBoolean(false);
    protected TrustManagerFactory trustManagerFactory;

    static class AnonymousClass3 {
        static final int[] $SwitchMap$com$obs$services$model$HttpMethodEnum;

        static {
            int[] iArr = new int[HttpMethodEnum.values().length];
            $SwitchMap$com$obs$services$model$HttpMethodEnum = iArr;
            try {
                iArr[HttpMethodEnum.PUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$obs$services$model$HttpMethodEnum[HttpMethodEnum.POST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$obs$services$model$HttpMethodEnum[HttpMethodEnum.HEAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$obs$services$model$HttpMethodEnum[HttpMethodEnum.GET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$obs$services$model$HttpMethodEnum[HttpMethodEnum.DELETE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$obs$services$model$HttpMethodEnum[HttpMethodEnum.OPTIONS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static class RequestContext {
        String bucketName;
        boolean doSignature;
        int internalErrorCount;
        Exception lastException;
        String method;
        InterfaceLogBean reqBean;
        Map<String, String> requestParameters;
        int retryMaxCount;

        private RequestContext() {
            this.internalErrorCount = 0;
        }
    }

    private static class ResponseContext {
        ServiceException ex;
        Response response;

        private ResponseContext() {
        }
    }

    static {
        HashSet hashSet = new HashSet();
        nonRetriableClasses = hashSet;
        hashSet.add(UnknownHostException.class);
        nonRetriableClasses.add(SSLException.class);
        nonRetriableClasses.add(ConnectException.class);
    }

    protected RestStorageService() {
    }

    private ServiceException handleThrowable(Request request, Response response, InterfaceLogBean interfaceLogBean, Call call, Throwable th) throws IOException {
        ServiceException serviceException;
        if (th instanceof ServiceException) {
            serviceException = (ServiceException) th;
        } else {
            serviceException = new ServiceException("Request Error: " + th, th);
        }
        serviceException.setRequestHost(request.header("Host"));
        serviceException.setRequestVerb(request.method());
        serviceException.setRequestPath(request.url().toString());
        if (response != null) {
            ServiceUtils.closeStream(response);
            serviceException.setResponseCode(response.code());
            serviceException.setResponseStatus(response.message());
            serviceException.setResponseDate(response.header("Date"));
            serviceException.setErrorIndicator(response.header("x-reserved-indicator"));
            serviceException.setResponseHeaders(ServiceUtils.cleanRestMetadataMapV2(convertHeadersToMap(response.headers()), getRestHeaderPrefix(), getRestMetadataPrefix()));
            if (!ServiceUtils.isValid(serviceException.getErrorRequestId())) {
                serviceException.setRequestAndHostIds(response.header(getIHeaders().requestIdHeader()), response.header(getIHeaders().requestId2Header()));
            }
        }
        if (log.isWarnEnabled()) {
            log.warn(serviceException);
        }
        if (call != null) {
            call.cancel();
        }
        return serviceException;
    }

    private boolean isLocationHostOnly(String str) {
        String path = URI.create(str).getPath();
        return str.indexOf("?") < 0 && (path == null || path.isEmpty() || path.equals("/"));
    }

    private boolean isProviderCredentialsInValid(ProviderCredentials providerCredentials) {
        return (providerCredentials != null && ServiceUtils.isValid(providerCredentials.getAccessKey()) && ServiceUtils.isValid(providerCredentials.getSecretKey())) ? false : true;
    }

    private void performRequestAsync(Request request, RequestContext requestContext, ObsCallback<Response, ServiceException> obsCallback) throws InterruptedException {
        performRequestAsync(request, requestContext, obsCallback, false);
    }

    private void performRequestAsync(final Request request, final RequestContext requestContext, final ObsCallback<Response, ServiceException> obsCallback, final boolean z) throws InterruptedException {
        Call callNewCall = this.httpClient.newCall(request);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        callNewCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException iOException) {
                boolean zIsInfoEnabled;
                try {
                    if (iOException instanceof UnrecoverableIOException) {
                        if (requestContext.lastException == null) {
                            throw iOException;
                        }
                        throw requestContext.lastException;
                    }
                    requestContext.lastException = iOException;
                    requestContext.internalErrorCount++;
                    if (RestStorageService.this.retryRequest(iOException, requestContext.internalErrorCount, requestContext.retryMaxCount, request, call)) {
                        Thread.sleep(((int) Math.pow(2.0d, requestContext.internalErrorCount)) * 50);
                        RestStorageService.this.performRequestAsync(RestStorageService.this.authorizeHttpRequest(request, requestContext.bucketName, null), requestContext, (ObsCallback<Response, ServiceException>) obsCallback);
                        if (zIsInfoEnabled) {
                            return;
                        } else {
                            return;
                        }
                    }
                    if (!(iOException instanceof ConnectException) && !(iOException instanceof InterruptedIOException)) {
                        throw iOException;
                    }
                    ServiceException serviceException = new ServiceException("Request error. ", iOException);
                    serviceException.setResponseCode(408);
                    serviceException.setErrorCode("RequestTimeOut");
                    serviceException.setErrorMessage(iOException.getMessage());
                    serviceException.setResponseStatus("Request error. ");
                    throw serviceException;
                } catch (Throwable th) {
                    try {
                        obsCallback.onFailure(RestStorageService.this.handleThrowable(request, null, requestContext.reqBean, call, th));
                        if (RestStorageService.log.isInfoEnabled()) {
                            RestStorageService.log.info((CharSequence) ("OkHttp cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms to apply http request"));
                        }
                    } finally {
                        if (RestStorageService.log.isInfoEnabled()) {
                            RestStorageService.log.info((CharSequence) ("OkHttp cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms to apply http request"));
                        }
                    }
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ILogger iLogger;
                StringBuilder sb;
                int iCode;
                String strString;
                boolean zIsInfoEnabled;
                try {
                    iCode = response.code();
                    requestContext.reqBean.setRespParams("[responseCode: " + iCode + "][request-id: " + response.header(RestStorageService.this.getIHeaders().requestIdHeader(), "") + "]");
                    String strHeader = response.header("Content-Type");
                    if (RestStorageService.log.isDebugEnabled()) {
                        RestStorageService.log.debug((CharSequence) ("Response for '" + requestContext.method + "'. Content-Type: " + strHeader + ", ResponseCode:" + iCode + ", Headers: " + response.headers()));
                    }
                    if (RestStorageService.log.isTraceEnabled() && response.body() != null) {
                        RestStorageService.log.trace((CharSequence) ("Entity length: " + response.body().contentLength()));
                    }
                    strString = null;
                } catch (Throwable th) {
                    try {
                        obsCallback.onFailure(RestStorageService.this.handleThrowable(request, response, requestContext.reqBean, call, th));
                        if (!RestStorageService.log.isInfoEnabled()) {
                            return;
                        }
                        iLogger = RestStorageService.log;
                        sb = new StringBuilder();
                    } finally {
                        if (RestStorageService.log.isInfoEnabled()) {
                            RestStorageService.log.info((CharSequence) ("OkHttp cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms to apply http request"));
                        }
                    }
                }
                if (iCode >= 300 && iCode < 400 && iCode != 304) {
                    String strHeader2 = response.header("Location");
                    if (!ServiceUtils.isValid(strHeader2)) {
                        ServiceException serviceException = new ServiceException("Try to redirect, but location is null!");
                        requestContext.reqBean.setResponseInfo("Request Error:" + serviceException.getMessage(), "|" + iCode + "|" + response.message() + "|");
                        throw serviceException;
                    }
                    if (strHeader2.indexOf("?") < 0) {
                        strHeader2 = RestStorageService.this.addRequestParametersToUrlPath(strHeader2, requestContext.requestParameters, z);
                    }
                    requestContext.internalErrorCount++;
                    if (requestContext.internalErrorCount > requestContext.retryMaxCount) {
                        try {
                            if (response.body() != null) {
                                strString = response.body().string();
                            }
                        } catch (IOException unused) {
                        }
                        throw new ServiceException("Exceeded 3xx redirect limit (" + requestContext.retryMaxCount + ").", strString);
                    }
                    ServiceUtils.closeStream(response);
                    if (requestContext.doSignature && RestStorageService.this.isLocationHostOnly(strHeader2)) {
                        RestStorageService.this.performRequestAsync(RestStorageService.this.authorizeHttpRequest(request, requestContext.bucketName, strHeader2), requestContext, (ObsCallback<Response, ServiceException>) obsCallback);
                    } else {
                        Request.Builder builderNewBuilder = request.newBuilder();
                        RestStorageService.this.setHost(builderNewBuilder, request, strHeader2);
                        RestStorageService.this.performRequestAsync(builderNewBuilder.build(), requestContext, (ObsCallback<Response, ServiceException>) obsCallback);
                    }
                    if (zIsInfoEnabled) {
                        return;
                    } else {
                        return;
                    }
                }
                if ((iCode < 400 || iCode >= 500) && iCode != 304) {
                    if (iCode < 500) {
                        if (RestStorageService.log.isInfoEnabled()) {
                            requestContext.reqBean.setRespTime(new Date());
                            requestContext.reqBean.setResultCode("0");
                            RestStorageService.log.info(requestContext.reqBean);
                        }
                        obsCallback.onSuccess(response);
                        if (RestStorageService.log.isInfoEnabled()) {
                            iLogger = RestStorageService.log;
                            sb = new StringBuilder();
                            sb.append("OkHttp cost ");
                            sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
                            sb.append(" ms to apply http request");
                            iLogger.info((CharSequence) sb.toString());
                            return;
                        }
                        return;
                    }
                    requestContext.reqBean.setResponseInfo("Internal Server error(s).", String.valueOf(iCode));
                    if (RestStorageService.log.isErrorEnabled()) {
                        RestStorageService.log.error(requestContext.reqBean);
                    }
                    requestContext.internalErrorCount++;
                    RestStorageService.this.sleepOnInternalError(requestContext.internalErrorCount, requestContext.retryMaxCount, response, requestContext.reqBean);
                    RestStorageService.this.performRequestAsync(RestStorageService.this.authorizeHttpRequest(request, requestContext.bucketName, null), requestContext, (ObsCallback<Response, ServiceException>) obsCallback);
                    if (RestStorageService.log.isInfoEnabled()) {
                        RestStorageService.log.info((CharSequence) ("OkHttp cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms to apply http request"));
                        return;
                    }
                    return;
                }
                String strString2 = response.body() != null ? response.body().string() : null;
                ServiceException serviceException2 = new ServiceException("Request Error.", strString2);
                if (!"RequestTimeout".equals(serviceException2.getErrorCode())) {
                    throw serviceException2;
                }
                requestContext.internalErrorCount++;
                if (requestContext.internalErrorCount >= requestContext.retryMaxCount) {
                    if (!RestStorageService.log.isErrorEnabled()) {
                        throw serviceException2;
                    }
                    RestStorageService.log.error((CharSequence) ("Exceeded maximum number of retries for RequestTimeout errors: " + requestContext.retryMaxCount));
                    throw serviceException2;
                }
                if (RestStorageService.log.isWarnEnabled()) {
                    RestStorageService.log.warn((CharSequence) ("Retrying connection that failed with RequestTimeout error, attempt number " + requestContext.internalErrorCount + " of " + requestContext.retryMaxCount));
                }
                RestStorageService.this.performRequestAsync(RestStorageService.this.authorizeHttpRequest(request, requestContext.bucketName, null), requestContext, (ObsCallback<Response, ServiceException>) obsCallback);
                if (RestStorageService.log.isInfoEnabled()) {
                    RestStorageService.log.info((CharSequence) ("OkHttp cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms to apply http request"));
                }
            }
        });
    }

    private URI setHost(Request.Builder builder, Request request, String str) {
        URI uriCreate;
        if (str == null) {
            uriCreate = request.url().uri();
        } else {
            uriCreate = URI.create(str);
            builder.url(str);
        }
        String str2 = "";
        if (getHttpsOnly()) {
            int httpsPort = getHttpsPort();
            if (httpsPort != 443) {
                str2 = ":" + httpsPort;
            }
        } else {
            int httpPort = getHttpPort();
            if (httpPort != 80) {
                str2 = ":" + httpPort;
            }
        }
        builder.header("Host", uriCreate.getHost() + str2);
        return uriCreate;
    }

    protected void addRequestHeadersToConnection(Request.Builder builder, Map<String, String> map) {
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (ServiceUtils.isValid(key) && value != null) {
                    String strTrim = key.trim();
                    if (Constants.ALLOWED_REQUEST_HTTP_HEADER_METADATA_NAMES.contains(strTrim.toLowerCase(Locale.getDefault())) || strTrim.startsWith(getRestHeaderPrefix())) {
                        builder.addHeader(strTrim, value);
                        if (log.isDebugEnabled()) {
                            log.debug((CharSequence) ("Added request header to connection: " + strTrim + "=" + value));
                        }
                    }
                }
            }
        }
    }

    protected String addRequestParametersToUrlPath(String str, Map<String, String> map) {
        return addRequestParametersToUrlPath(str, map, false);
    }

    protected String addRequestParametersToUrlPath(String str, Map<String, String> map, boolean z) throws ServiceException {
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!z) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(str.indexOf("?") >= 0 ? "&" : "?");
                    sb.append(RestUtils.encodeUrlString(key));
                    str = sb.toString();
                } else if (isPathStyle()) {
                    str = str + "/" + key;
                } else {
                    str = str + key;
                }
                if (ServiceUtils.isValid(value)) {
                    String str2 = str + "=" + RestUtils.encodeUrlString(value);
                    if (log.isDebugEnabled()) {
                        log.debug((CharSequence) ("Added request parameter: " + key + "=" + value));
                    }
                    str = str2;
                } else if (log.isDebugEnabled()) {
                    log.debug((CharSequence) ("Added request parameter without value: " + key));
                }
            }
        }
        return str;
    }

    protected Request authorizeHttpRequest(Request request, String str, String str2) throws ServiceException {
        String str3;
        IAuthentication iAuthenticationMakeAuthorizationString;
        Headers headersBuild = request.headers().newBuilder().removeAll("Authorization").build();
        Request.Builder builderNewBuilder = request.newBuilder();
        builderNewBuilder.headers(headersBuild);
        URI host = setHost(builderNewBuilder, request, str2);
        String host2 = host.getHost();
        ProviderCredentials providerCredentials = ProviderCredentialThreadContext.getInstance().getProviderCredentials();
        if (isProviderCredentialsInValid(providerCredentials)) {
            providerCredentials = getProviderCredentials();
        } else {
            providerCredentials.setAuthType(getProviderCredentials().getAuthType());
        }
        ProviderCredentials providerCredentials2 = providerCredentials;
        if (isProviderCredentialsInValid(providerCredentials2)) {
            if (log.isInfoEnabled()) {
                log.info((CharSequence) "Service has no Credential and is un-authenticated, skipping authorization");
            }
            return request;
        }
        ?? DateHeader = getIHeaders().dateHeader();
        String strHeader = request.header(DateHeader);
        boolean z = providerCredentials2.getAuthType() == AuthTypeEnum.V4;
        if (strHeader != null) {
            try {
                DateHeader = z ? ServiceUtils.getLongDateFormat().parse(strHeader) : ServiceUtils.parseRfc822Date(strHeader);
            } catch (ParseException e) {
                throw new ServiceException(DateHeader + " is not well-format", e);
            }
        } else {
            DateHeader = new Date();
        }
        builderNewBuilder.header("Date", ServiceUtils.formatRfc822Date(DateHeader));
        String securityToken = providerCredentials2.getSecurityToken();
        if (ServiceUtils.isValid(securityToken)) {
            builderNewBuilder.header(getIHeaders().securityTokenHeader(), securityToken);
        }
        String rawPath = host.getRawPath();
        String endpoint = getEndpoint();
        if ((!isPathStyle() || isCname()) && host2 != null && !z) {
            if (isCname()) {
                rawPath = "/" + host2 + rawPath;
            } else if (ServiceUtils.isValid(str) && !endpoint.equals(host2) && host2.indexOf(str) >= 0) {
                rawPath = "/" + str + rawPath;
            }
        }
        String rawQuery = host.getRawQuery();
        if (rawQuery == null || rawQuery.length() <= 0) {
            str3 = rawPath;
        } else {
            str3 = rawPath + "?" + rawQuery;
        }
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) ("For creating canonical string, using uri: " + str3));
        }
        if (z) {
            builderNewBuilder.header(getIHeaders().contentSha256Header(), "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
            iAuthenticationMakeAuthorizationString = V4Authentication.makeServiceCanonicalString(request.method(), convertHeadersToMap(builderNewBuilder.build().headers()), str3, providerCredentials2, DateHeader);
            if (log.isDebugEnabled()) {
                log.debug((CharSequence) ("CanonicalRequest:" + iAuthenticationMakeAuthorizationString.getCanonicalRequest()));
            }
        } else {
            iAuthenticationMakeAuthorizationString = Constants.AUTHTICATION_MAP.get(providerCredentials2.getAuthType()).makeAuthorizationString(request.method(), convertHeadersToMap(builderNewBuilder.build().headers()), str3, Constants.ALLOWED_RESOURCE_PARAMTER_NAMES, providerCredentials2);
        }
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) ("StringToSign ('|' is a newline): " + iAuthenticationMakeAuthorizationString.getStringToSign().replace('\n', '|')));
        }
        builderNewBuilder.header("Authorization", iAuthenticationMakeAuthorizationString.getAuthorization());
        builderNewBuilder.header("User-Agent", "obs-sdk-java/3.19.5.3");
        return builderNewBuilder.build();
    }

    protected Map<String, String> convertHeadersToMap(Headers headers) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        for (Map.Entry<String, List<String>> entry : headers.toMultimap().entrySet()) {
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                identityHashMap.put(new String(entry.getKey()), it.next());
            }
        }
        return identityHashMap;
    }

    protected CacheManager getApiVersionCache() {
        return this.apiVersionCache;
    }

    protected String getEndpoint() {
        return this.obsProperties.getStringProperty("obs-endpoint", "");
    }

    protected String getFileSystemDelimiter() {
        return this.obsProperties.getStringProperty("filesystem.delimiter", "/");
    }

    protected int getHttpPort() {
        return this.obsProperties.getIntProperty("obs-endpoint-http-port", 80);
    }

    protected boolean getHttpsOnly() {
        return this.obsProperties.getBoolProperty("obs.https-only", true);
    }

    protected int getHttpsPort() {
        return this.obsProperties.getIntProperty("obs-endpoint-https-port", 443);
    }

    protected IConvertor getIConvertor() {
        return Constants.CONVERTOR_MAP.get(getProviderCredentials().getAuthType());
    }

    protected IHeaders getIHeaders() {
        return Constants.HEADERS_MAP.get(getProviderCredentials().getAuthType());
    }

    protected ProviderCredentials getProviderCredentials() {
        return this.credentials;
    }

    protected String getRestHeaderPrefix() {
        return getIHeaders().headerPrefix();
    }

    protected String getRestMetadataPrefix() {
        return getIHeaders().headerMetaPrefix();
    }

    protected XmlResponsesSaxParser getXmlResponseSaxParser() throws ServiceException {
        return new XmlResponsesSaxParser();
    }

    protected void initHttpClient(Dispatcher dispatcher) {
        OkHttpClient.Builder builderInitHttpClientBuilder = RestUtils.initHttpClientBuilder(this, this.obsProperties, this.keyManagerFactory, this.trustManagerFactory, dispatcher);
        if (this.obsProperties.getBoolProperty("httpclient.proxy-enable", true)) {
            RestUtils.initHttpProxy(builderInitHttpClientBuilder, this.obsProperties.getStringProperty("httpclient.proxy-host", null), this.obsProperties.getIntProperty("httpclient.proxy-port", -1), this.obsProperties.getStringProperty("httpclient.proxy-user", null), this.obsProperties.getStringProperty("httpclient.proxy-password", null), this.obsProperties.getStringProperty("httpclient.proxy-domain", null), this.obsProperties.getStringProperty("httpclient.proxy-workstation", null));
        }
        this.httpClient = builderInitHttpClientBuilder.build();
        this.semaphore = new Semaphore(this.obsProperties.getIntProperty("httpclient.max-connections", 1000));
    }

    protected boolean isAuthTypeNegotiation() {
        return this.obsProperties.getBoolProperty("httpclient.auth-type-negotiation", true);
    }

    protected boolean isCname() {
        return this.obsProperties.getBoolProperty("httpclient.is-cname", false);
    }

    protected boolean isKeepAlive() {
        return this.obsProperties.getBoolProperty("httpclient.keep-alive", true);
    }

    protected boolean isPathStyle() {
        return this.obsProperties.getBoolProperty("obs.disable-dns-buckets", false);
    }

    protected Response performRequest(Request request, Map<String, String> map, String str) throws ServiceException {
        return performRequest(request, map, str, true);
    }

    protected Response performRequest(Request request, Map<String, String> map, String str, boolean z) throws ServiceException {
        return performRequest(request, map, str, z, false);
    }

    protected Response performRequest(Request request, Map<String, String> map, String str, boolean z, boolean z2) throws ServiceException {
        Request request2;
        Throwable th;
        Response response;
        Call call;
        int intProperty;
        Request request3;
        IOException iOException;
        Call call2;
        Response response2;
        boolean z3;
        boolean z4;
        Request request4;
        Call callNewCall;
        Request requestAuthorizeHttpRequest;
        boolean z5;
        long jCurrentTimeMillis;
        String str2;
        String str3;
        String str4;
        Request request5;
        String str5;
        int i;
        String str6;
        String str7;
        String str8;
        IOException iOException2;
        Response responseExecute;
        int iCode;
        String str9;
        String str10;
        Request requestBuild;
        String str11 = "Request error. ";
        ?? r11 = "|";
        String str12 = " ms to apply http request";
        String str13 = "OkHttp cost ";
        String str14 = "";
        InterfaceLogBean interfaceLogBean = new InterfaceLogBean("performRequest", "", "");
        String str15 = null;
        try {
            if (log.isDebugEnabled()) {
                try {
                    log.debug((CharSequence) ("Performing " + request.method() + " request for '" + request.url()));
                    ILogger iLogger = log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Headers: ");
                    sb.append(request.headers());
                    iLogger.debug((CharSequence) sb.toString());
                    intProperty = this.obsProperties.getIntProperty("httpclient.retry-max", 3);
                    request3 = request;
                    iOException = null;
                    call2 = null;
                    response2 = null;
                    z3 = false;
                    int i2 = 0;
                    z4 = false;
                    request4 = r11;
                    while (true) {
                        if (!z3) {
                            callNewCall = call2;
                            requestAuthorizeHttpRequest = request3;
                            z5 = false;
                        } else if (z) {
                            try {
                                z5 = z3;
                                callNewCall = call2;
                                requestAuthorizeHttpRequest = authorizeHttpRequest(request3, str, str15);
                            } catch (Throwable th2) {
                                th = th2;
                                request2 = request3;
                                call = call2;
                                response = response2;
                            }
                        } else {
                            try {
                                Request.Builder builderNewBuilder = request3.newBuilder();
                                boolean z6 = z3;
                                callNewCall = call2;
                                try {
                                    builderNewBuilder.headers(request3.headers().newBuilder().removeAll("Authorization").build());
                                    setHost(builderNewBuilder, request3, null);
                                    z5 = z6;
                                    requestAuthorizeHttpRequest = builderNewBuilder.build();
                                } catch (Throwable th3) {
                                    th = th3;
                                    th = th;
                                    request2 = request3;
                                    response = response2;
                                    call = callNewCall;
                                    throw handleThrowable(request2, response, interfaceLogBean, call, th);
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                callNewCall = call2;
                            }
                        }
                        try {
                            jCurrentTimeMillis = System.currentTimeMillis();
                            callNewCall = this.httpClient.newCall(requestAuthorizeHttpRequest);
                            try {
                                this.semaphore.acquire();
                                responseExecute = callNewCall.execute();
                            } catch (IOException e) {
                                str4 = str11;
                                request5 = request4;
                                String str16 = str12;
                                String str17 = str13;
                                str5 = str14;
                                try {
                                    if (e instanceof UnrecoverableIOException) {
                                        if (iOException != null) {
                                            throw iOException;
                                        }
                                        throw e;
                                    }
                                    int i3 = i2 + 1;
                                    i = intProperty;
                                    str6 = null;
                                    request4 = requestAuthorizeHttpRequest;
                                    try {
                                        if (!retryRequest(e, i3, intProperty, requestAuthorizeHttpRequest, callNewCall)) {
                                            str2 = str16;
                                            str3 = str17;
                                            try {
                                                if (!(e instanceof ConnectException) && !(e instanceof InterruptedIOException)) {
                                                    throw e;
                                                }
                                                ServiceException serviceException = new ServiceException(str4, e);
                                                serviceException.setResponseCode(408);
                                                serviceException.setErrorCode("RequestTimeOut");
                                                serviceException.setErrorMessage(e.getMessage());
                                                serviceException.setResponseStatus(str4);
                                                throw serviceException;
                                            } catch (Throwable th5) {
                                                th = th5;
                                                this.semaphore.release();
                                                if (log.isInfoEnabled()) {
                                                }
                                                throw th;
                                            }
                                        }
                                        Thread.sleep(((int) Math.pow(2.0d, i3)) * 50);
                                        try {
                                            this.semaphore.release();
                                            if (log.isInfoEnabled()) {
                                                ILogger iLogger2 = log;
                                                StringBuilder sb2 = new StringBuilder();
                                                str8 = str17;
                                                sb2.append(str8);
                                                sb2.append(System.currentTimeMillis() - jCurrentTimeMillis);
                                                str7 = str16;
                                                sb2.append(str7);
                                                iLogger2.info((CharSequence) sb2.toString());
                                            } else {
                                                str7 = str16;
                                                str8 = str17;
                                            }
                                            iOException2 = e;
                                            i2 = i3;
                                            request3 = request4;
                                            z3 = z5;
                                        } catch (Throwable th6) {
                                            th = th6;
                                            th = th;
                                            request2 = request4;
                                            response = response2;
                                            call = callNewCall;
                                            throw handleThrowable(request2, response, interfaceLogBean, call, th);
                                        }
                                    } catch (Throwable th7) {
                                        th = th7;
                                        str2 = str16;
                                        str3 = str17;
                                        this.semaphore.release();
                                        if (log.isInfoEnabled()) {
                                            log.info((CharSequence) (str3 + (System.currentTimeMillis() - jCurrentTimeMillis) + str2));
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th8) {
                                    th = th8;
                                    request4 = requestAuthorizeHttpRequest;
                                }
                            } catch (Throwable th9) {
                                th = th9;
                                request4 = requestAuthorizeHttpRequest;
                                str2 = str12;
                                str3 = str13;
                                this.semaphore.release();
                                if (log.isInfoEnabled()) {
                                }
                                throw th;
                            }
                        } catch (Throwable th10) {
                            th = th10;
                            request4 = requestAuthorizeHttpRequest;
                        }
                        try {
                            this.semaphore.release();
                            if (log.isInfoEnabled()) {
                                str4 = str11;
                                request5 = request4;
                            } else {
                                ILogger iLogger3 = log;
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(str13);
                                str4 = str11;
                                request5 = request4;
                                sb3.append(System.currentTimeMillis() - jCurrentTimeMillis);
                                sb3.append(str12);
                                iLogger3.info((CharSequence) sb3.toString());
                            }
                            iCode = responseExecute.code();
                            interfaceLogBean.setRespParams("[responseCode: " + iCode + "][request-id: " + responseExecute.header(getIHeaders().requestIdHeader(), str14) + "]");
                            String strHeader = responseExecute.header("Content-Type");
                            if (log.isDebugEnabled()) {
                                str5 = str14;
                            } else {
                                ILogger iLogger4 = log;
                                StringBuilder sb4 = new StringBuilder();
                                str5 = str14;
                                sb4.append("Response for '");
                                sb4.append(requestAuthorizeHttpRequest.method());
                                sb4.append("'. Content-Type: ");
                                sb4.append(strHeader);
                                sb4.append(", ResponseCode:");
                                sb4.append(iCode);
                                sb4.append(", Headers: ");
                                sb4.append(responseExecute.headers());
                                iLogger4.debug((CharSequence) sb4.toString());
                            }
                            if (log.isTraceEnabled() || responseExecute.body() == null) {
                                str9 = str12;
                                str10 = str13;
                            } else {
                                ILogger iLogger5 = log;
                                StringBuilder sb5 = new StringBuilder();
                                sb5.append("Entity length: ");
                                str9 = str12;
                                str10 = str13;
                                sb5.append(responseExecute.body().contentLength());
                                iLogger5.trace((CharSequence) sb5.toString());
                            }
                            if (z2 || !"application/json".equalsIgnoreCase(strHeader)) {
                                if (iCode >= 300 || iCode >= 400 || iCode == 304) {
                                    if ((iCode < 400 && iCode < 500) || iCode == 304) {
                                        String strString = responseExecute.body() != null ? responseExecute.body().string() : null;
                                        ServiceException serviceException2 = new ServiceException("Request Error.", strString);
                                        if (!"RequestTimeout".equals(serviceException2.getErrorCode())) {
                                            throw serviceException2;
                                        }
                                        i2++;
                                        if (i2 >= intProperty) {
                                            if (!log.isErrorEnabled()) {
                                                throw serviceException2;
                                            }
                                            log.error((CharSequence) ("Exceeded maximum number of retries for RequestTimeout errors: " + intProperty));
                                            throw serviceException2;
                                        }
                                        if (log.isWarnEnabled()) {
                                            log.warn((CharSequence) ("Retrying connection that failed with RequestTimeout error, attempt number " + i2 + " of " + intProperty));
                                        }
                                    } else if (iCode >= 500) {
                                        interfaceLogBean.setResponseInfo("Internal Server error(s).", String.valueOf(iCode));
                                        if (log.isErrorEnabled()) {
                                            log.error(interfaceLogBean);
                                        }
                                        i2++;
                                        sleepOnInternalError(i2, intProperty, responseExecute, interfaceLogBean);
                                    }
                                    response2 = responseExecute;
                                    i = intProperty;
                                    request3 = requestAuthorizeHttpRequest;
                                    z3 = z5;
                                    str7 = str9;
                                } else {
                                    String strHeader2 = responseExecute.header("Location");
                                    if (!ServiceUtils.isValid(strHeader2)) {
                                        ServiceException serviceException3 = new ServiceException("Try to redirect, but location is null!");
                                        String str18 = "Request Error:" + serviceException3.getMessage();
                                        ?? sb6 = new StringBuilder();
                                        ?? r5 = request5;
                                        sb6.append(r5);
                                        sb6.append(iCode);
                                        sb6.append(r5);
                                        sb6.append(responseExecute.message());
                                        sb6.append(r5);
                                        interfaceLogBean.setResponseInfo(str18, sb6.toString());
                                        throw serviceException3;
                                    }
                                    if (strHeader2.indexOf("?") < 0) {
                                        strHeader2 = addRequestParametersToUrlPath(strHeader2, map, z2);
                                    }
                                    if (z && isLocationHostOnly(strHeader2)) {
                                        requestBuild = authorizeHttpRequest(requestAuthorizeHttpRequest, str, strHeader2);
                                    } else {
                                        Request.Builder builderNewBuilder2 = requestAuthorizeHttpRequest.newBuilder();
                                        setHost(builderNewBuilder2, requestAuthorizeHttpRequest, strHeader2);
                                        requestBuild = builderNewBuilder2.build();
                                    }
                                    Request request6 = requestBuild;
                                    i2++;
                                    if (i2 > intProperty) {
                                        try {
                                            break;
                                        } catch (IOException unused) {
                                        }
                                    } else {
                                        try {
                                            ServiceUtils.closeStream(responseExecute);
                                            response2 = responseExecute;
                                            request3 = request6;
                                            i = intProperty;
                                            str7 = str9;
                                            z3 = true;
                                        } catch (Throwable th11) {
                                            th = th11;
                                            response = responseExecute;
                                            request2 = request6;
                                        }
                                    }
                                    th = th11;
                                    response = responseExecute;
                                    request2 = request6;
                                    call = callNewCall;
                                }
                                str6 = null;
                                iOException2 = iOException;
                                str8 = str10;
                                if (z4) {
                                    if (log.isInfoEnabled()) {
                                        interfaceLogBean.setRespTime(new Date());
                                        interfaceLogBean.setResultCode("0");
                                        log.info(interfaceLogBean);
                                    }
                                    return response2;
                                }
                                str15 = str6;
                                str14 = str5;
                                str11 = str4;
                                request4 = request5;
                                str13 = str8;
                                iOException = iOException2;
                                intProperty = i;
                                str12 = str7;
                                call2 = callNewCall;
                            } else {
                                if (iCode >= 400 && iCode < 500) {
                                    String strString2 = responseExecute.body() != null ? responseExecute.body().string() : null;
                                    OefExceptionMessage oefExceptionMessage = (OefExceptionMessage) JSONChange.jsonToObj(new OefExceptionMessage(), ServiceUtils.toValid(strString2));
                                    ServiceException serviceException4 = new ServiceException("Request Error." + ServiceUtils.toValid(strString2));
                                    serviceException4.setErrorMessage(oefExceptionMessage.getMessage());
                                    serviceException4.setErrorCode(oefExceptionMessage.getCode());
                                    serviceException4.setErrorRequestId(oefExceptionMessage.getRequest_id());
                                    throw serviceException4;
                                }
                                if (iCode >= 500) {
                                    interfaceLogBean.setResponseInfo("Internal Server error(s).", String.valueOf(iCode));
                                    if (log.isErrorEnabled()) {
                                        log.error(interfaceLogBean);
                                    }
                                    String strString3 = responseExecute.body() != null ? responseExecute.body().string() : null;
                                    throw new ServiceException("Encountered too many 5xx errors (" + i2 + "), aborting request.", strString3);
                                }
                            }
                            response2 = responseExecute;
                            i = intProperty;
                            request3 = requestAuthorizeHttpRequest;
                            z3 = z5;
                            str7 = str9;
                            str6 = null;
                            z4 = true;
                            iOException2 = iOException;
                            str8 = str10;
                            if (z4) {
                            }
                        } catch (Throwable th12) {
                            th = th12;
                            response = responseExecute;
                            request2 = requestAuthorizeHttpRequest;
                            call = callNewCall;
                            th = th;
                            throw handleThrowable(request2, response, interfaceLogBean, call, th);
                        }
                    }
                } catch (Throwable th13) {
                    th = th13;
                    request2 = request;
                    response = null;
                    call = null;
                    th = th;
                    throw handleThrowable(request2, response, interfaceLogBean, call, th);
                }
            } else {
                intProperty = this.obsProperties.getIntProperty("httpclient.retry-max", 3);
                request3 = request;
                iOException = null;
                call2 = null;
                response2 = null;
                z3 = false;
                int i22 = 0;
                z4 = false;
                request4 = r11;
                while (true) {
                    if (!z3) {
                    }
                    jCurrentTimeMillis = System.currentTimeMillis();
                    callNewCall = this.httpClient.newCall(requestAuthorizeHttpRequest);
                    this.semaphore.acquire();
                    responseExecute = callNewCall.execute();
                    this.semaphore.release();
                    if (log.isInfoEnabled()) {
                    }
                    iCode = responseExecute.code();
                    interfaceLogBean.setRespParams("[responseCode: " + iCode + "][request-id: " + responseExecute.header(getIHeaders().requestIdHeader(), str14) + "]");
                    String strHeader3 = responseExecute.header("Content-Type");
                    if (log.isDebugEnabled()) {
                    }
                    if (log.isTraceEnabled()) {
                        str9 = str12;
                        str10 = str13;
                        if (z2) {
                            if (iCode >= 300) {
                                if (iCode < 400) {
                                    if (iCode >= 500) {
                                    }
                                    response2 = responseExecute;
                                    i = intProperty;
                                    request3 = requestAuthorizeHttpRequest;
                                    z3 = z5;
                                    str7 = str9;
                                    str6 = null;
                                    z4 = true;
                                    iOException2 = iOException;
                                    str8 = str10;
                                } else {
                                    if (iCode >= 500) {
                                    }
                                    response2 = responseExecute;
                                    i = intProperty;
                                    request3 = requestAuthorizeHttpRequest;
                                    z3 = z5;
                                    str7 = str9;
                                    str6 = null;
                                    z4 = true;
                                    iOException2 = iOException;
                                    str8 = str10;
                                }
                            }
                        }
                        if (z4) {
                        }
                    }
                    str15 = str6;
                    str14 = str5;
                    str11 = str4;
                    request4 = request5;
                    str13 = str8;
                    iOException = iOException2;
                    intProperty = i;
                    str12 = str7;
                    call2 = callNewCall;
                }
            }
        } catch (Throwable th14) {
            request2 = request;
            th = th14;
            response = null;
            call = null;
        }
        throw handleThrowable(request2, response, interfaceLogBean, call, th);
        String strString4 = null;
        throw new ServiceException("Exceeded 3xx redirect limit (" + intProperty + ").", strString4);
    }

    protected Response performRequestAsync(Request request, Map<String, String> map, String str) throws ServiceException {
        return performRequestAsync(request, map, str, true);
    }

    protected Response performRequestAsync(Request request, Map<String, String> map, String str, boolean z) throws InterruptedException, ServiceException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ResponseContext responseContext = new ResponseContext();
        try {
            performRequestAsync(request, map, str, z, new ObsCallback<Response, ServiceException>() {
                @Override
                public void onFailure(ServiceException serviceException) {
                    responseContext.ex = serviceException;
                    countDownLatch.countDown();
                }

                @Override
                public void onSuccess(Response response) {
                    responseContext.response = response;
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
            if (responseContext.ex == null) {
                return responseContext.response;
            }
            throw responseContext.ex;
        } catch (InterruptedException e) {
            throw new ServiceException(e);
        }
    }

    protected void performRequestAsync(Request request, Map<String, String> map, String str, boolean z, ObsCallback<Response, ServiceException> obsCallback) throws InterruptedException, ServiceException {
        Request requestBuild;
        InterfaceLogBean interfaceLogBean = new InterfaceLogBean("performRequest", "", "");
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) ("Performing " + request.method() + " request for '" + request.url()));
            ILogger iLogger = log;
            StringBuilder sb = new StringBuilder();
            sb.append("Headers: ");
            sb.append(request.headers());
            iLogger.debug((CharSequence) sb.toString());
        }
        RequestContext requestContext = new RequestContext();
        requestContext.reqBean = interfaceLogBean;
        requestContext.method = request.method();
        requestContext.retryMaxCount = this.obsProperties.getIntProperty("httpclient.retry-max", 3);
        requestContext.bucketName = str;
        requestContext.requestParameters = map;
        requestContext.doSignature = z;
        if (z) {
            requestBuild = authorizeHttpRequest(request, str, null);
        } else {
            Request.Builder builderNewBuilder = request.newBuilder();
            builderNewBuilder.headers(request.headers().newBuilder().removeAll("Authorization").build());
            setHost(builderNewBuilder, request, null);
            requestBuild = builderNewBuilder.build();
        }
        performRequestAsync(requestBuild, requestContext, obsCallback);
    }

    protected Response performRequestWithoutSignature(Request request, Map<String, String> map, String str) throws ServiceException {
        return performRequest(request, map, str, false);
    }

    protected Response performRequesttWithoutSignatureAsync(Request request, Map<String, String> map, String str) throws ServiceException {
        return performRequestAsync(request, map, str, false);
    }

    protected Response performRestDelete(String str, String str2, Map<String, String> map) throws ServiceException {
        return performRestDelete(str, str2, map, true);
    }

    protected Response performRestDelete(String str, String str2, Map<String, String> map, Map<String, String> map2) throws ServiceException {
        return performRestDelete(str, str2, map, map2, false);
    }

    protected Response performRestDelete(String str, String str2, Map<String, String> map, Map<String, String> map2, boolean z) throws ServiceException {
        Request.Builder builder = setupConnection(HttpMethodEnum.DELETE, str, str2, map, null, z);
        renameMetadataKeys(builder, map2);
        Response responsePerformRequest = performRequest(builder.build(), map, str, true, z);
        responsePerformRequest.close();
        return responsePerformRequest;
    }

    protected Response performRestDelete(String str, String str2, Map<String, String> map, boolean z) throws ServiceException {
        Response responsePerformRequest = performRequest(setupConnection(HttpMethodEnum.DELETE, str, str2, map, null).build(), map, str);
        if (z) {
            responsePerformRequest.close();
        }
        return responsePerformRequest;
    }

    protected Response performRestForApiVersion(String str, String str2, Map<String, String> map, Map<String, String> map2) throws ServiceException {
        Request.Builder builder = setupConnection(HttpMethodEnum.HEAD, str, str2, map, null, false, true);
        addRequestHeadersToConnection(builder, map2);
        return performRequestWithoutSignature(builder.build(), map, str);
    }

    protected Response performRestGet(String str, String str2, Map<String, String> map, Map<String, String> map2) throws ServiceException {
        return performRestGet(str, str2, map, map2, false);
    }

    protected Response performRestGet(String str, String str2, Map<String, String> map, Map<String, String> map2, boolean z) throws ServiceException {
        Request.Builder builder = setupConnection(HttpMethodEnum.GET, str, str2, map, null, z);
        addRequestHeadersToConnection(builder, map2);
        return performRequest(builder.build(), map, str, true, z);
    }

    protected Response performRestGetForListBuckets(String str, String str2, Map<String, String> map, Map<String, String> map2) throws ServiceException {
        Request.Builder builder = setupConnection(HttpMethodEnum.GET, str, str2, map, null, false, true);
        addRequestHeadersToConnection(builder, map2);
        return performRequest(builder.build(), map, str, true, false);
    }

    protected Response performRestHead(String str, String str2, Map<String, String> map, Map<String, String> map2) throws ServiceException {
        Request.Builder builder = setupConnection(HttpMethodEnum.HEAD, str, str2, map, null);
        addRequestHeadersToConnection(builder, map2);
        return performRequest(builder.build(), map, str);
    }

    protected Response performRestOptions(String str, String str2, Map<String, String> map, Map<String, String> map2, boolean z) throws ServiceException {
        Request.Builder builder = setupConnection(HttpMethodEnum.OPTIONS, str, str2, map2, null);
        addRequestHeadersToConnection(builder, map);
        Response responsePerformRequest = performRequest(builder.build(), map2, str);
        if (z) {
            responsePerformRequest.close();
        }
        return responsePerformRequest;
    }

    protected Response performRestPost(String str, String str2, Map<String, String> map, Map<String, String> map2, RequestBody requestBody, boolean z) throws ServiceException {
        return performRestPost(str, str2, map, map2, requestBody, z, false);
    }

    protected Response performRestPost(String str, String str2, Map<String, String> map, Map<String, String> map2, RequestBody requestBody, boolean z, boolean z2) throws ServiceException {
        Request.Builder builder = setupConnection(HttpMethodEnum.POST, str, str2, map2, requestBody, z2);
        renameMetadataKeys(builder, map);
        Response responsePerformRequest = performRequest(builder.build(), map2, str, true, z2);
        if (z) {
            responsePerformRequest.close();
        }
        return responsePerformRequest;
    }

    protected Response performRestPut(String str, String str2, Map<String, String> map, Map<String, String> map2, RequestBody requestBody, boolean z) throws ServiceException {
        return performRestPut(str, str2, map, map2, requestBody, z, false);
    }

    protected Response performRestPut(String str, String str2, Map<String, String> map, Map<String, String> map2, RequestBody requestBody, boolean z, boolean z2) throws ServiceException {
        Request.Builder builder = setupConnection(HttpMethodEnum.PUT, str, str2, map2, requestBody, z2);
        renameMetadataKeys(builder, map);
        Response responsePerformRequest = performRequest(builder.build(), map2, str, true, z2);
        if (z) {
            responsePerformRequest.close();
        }
        return responsePerformRequest;
    }

    protected void renameMetadataKeys(Request.Builder builder, Map<String, String> map) {
        HashMap map2 = new HashMap();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (ServiceUtils.isValid(key)) {
                    String strTrim = key.trim();
                    if (!strTrim.startsWith(getRestHeaderPrefix()) && !strTrim.startsWith("x-obs-") && !Constants.ALLOWED_REQUEST_HTTP_HEADER_METADATA_NAMES.contains(strTrim.toLowerCase(Locale.getDefault()))) {
                        strTrim = getRestMetadataPrefix() + strTrim;
                    }
                    try {
                        if (strTrim.startsWith(getRestMetadataPrefix())) {
                            strTrim = RestUtils.uriEncode(strTrim, true);
                        }
                        if (value == null) {
                            value = "";
                        }
                        map2.put(strTrim, RestUtils.uriEncode(value, true));
                    } catch (ServiceException unused) {
                        if (log.isDebugEnabled()) {
                            log.debug((CharSequence) ("Ignore metadata key:" + strTrim));
                        }
                    }
                }
            }
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            builder.addHeader((String) entry2.getKey(), (String) entry2.getValue());
            if (log.isDebugEnabled()) {
                log.debug((CharSequence) ("Added request header to connection: " + ((String) entry2.getKey()) + "=" + ((String) entry2.getValue())));
            }
        }
    }

    protected boolean retryRequest(IOException iOException, int i, int i2, Request request, Call call) {
        if (i > i2 || nonRetriableClasses.contains(iOException.getClass())) {
            return false;
        }
        Iterator<Class<? extends IOException>> it = nonRetriableClasses.iterator();
        while (it.hasNext()) {
            if (it.next().isInstance(iOException)) {
                return false;
            }
        }
        return !call.isCanceled();
    }

    protected void setProviderCredentials(ProviderCredentials providerCredentials) {
        this.credentials = providerCredentials;
    }

    protected Request.Builder setupConnection(HttpMethodEnum httpMethodEnum, String str, String str2, Map<String, String> map, RequestBody requestBody) throws ServiceException {
        return setupConnection(httpMethodEnum, str, str2, map, requestBody, false);
    }

    protected Request.Builder setupConnection(HttpMethodEnum httpMethodEnum, String str, String str2, Map<String, String> map, RequestBody requestBody, boolean z) throws ServiceException {
        return setupConnection(httpMethodEnum, str, str2, map, requestBody, z, false);
    }

    protected Request.Builder setupConnection(HttpMethodEnum httpMethodEnum, String str, String str2, Map<String, String> map, RequestBody requestBody, boolean z, boolean z2) throws ServiceException {
        String string;
        String str3;
        String str4;
        String str5;
        boolean zIsPathStyle = isPathStyle();
        String endpoint = getEndpoint();
        boolean zIsCname = isCname();
        String strGenerateHostnameForBucket = (zIsCname || z2) ? endpoint : ServiceUtils.generateHostnameForBucket(RestUtils.encodeUrlString(str), zIsPathStyle, endpoint);
        if (!strGenerateHostnameForBucket.equals(endpoint) || zIsCname || str.length() <= 0) {
            string = "/";
        } else {
            string = "/" + RestUtils.encodeUrlString(str);
        }
        if (str2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append((!zIsPathStyle || zIsCname) ? "" : "/");
            sb.append(RestUtils.encodeUrlString(str2));
            string = sb.toString();
        }
        if (getHttpsOnly()) {
            int httpsPort = getHttpsPort();
            if (httpsPort == 443) {
                str5 = "";
            } else {
                str5 = ":" + httpsPort;
            }
            str4 = "https://" + strGenerateHostnameForBucket + str5 + string;
        } else {
            int httpPort = getHttpPort();
            if (httpPort == 80) {
                str3 = "";
            } else {
                str3 = ":" + httpPort;
            }
            str4 = "http://" + strGenerateHostnameForBucket + str3 + string;
        }
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) ("OBS URL: " + str4));
        }
        String strAddRequestParametersToUrlPath = addRequestParametersToUrlPath(str4, map, z);
        Request.Builder builder = new Request.Builder();
        builder.url(strAddRequestParametersToUrlPath);
        if (requestBody == null) {
            requestBody = RequestBody.create((MediaType) null, "");
        }
        switch (AnonymousClass3.$SwitchMap$com$obs$services$model$HttpMethodEnum[httpMethodEnum.ordinal()]) {
            case 1:
                builder.put(requestBody);
                break;
            case 2:
                builder.post(requestBody);
                break;
            case 3:
                builder.head();
                break;
            case 4:
                builder.get();
                break;
            case 5:
                builder.delete(requestBody);
                break;
            case 6:
                builder.method("OPTIONS", null);
                break;
            default:
                throw new IllegalArgumentException("Unrecognised HTTP method name: " + httpMethodEnum);
        }
        if (!isKeepAlive()) {
            builder.addHeader("Connection", "Close");
        }
        return builder;
    }

    protected void shutdown() throws IllegalAccessException, NoSuchMethodException, SecurityException, IOException, IllegalArgumentException, InvocationTargetException {
        shutdownImpl();
    }

    protected void shutdownImpl() throws IllegalAccessException, NoSuchMethodException, SecurityException, IOException, IllegalArgumentException, InvocationTargetException {
        if (this.shuttingDown.compareAndSet(false, true)) {
            this.credentials = null;
            this.obsProperties = null;
            OkHttpClient okHttpClient = this.httpClient;
            if (okHttpClient != null) {
                try {
                    Method method = okHttpClient.getClass().getMethod("dispatcher", new Class[0]);
                    if (method != null) {
                        Method declaredMethod = method.invoke(this.httpClient, new Object[0]).getClass().getDeclaredMethod("executorService", new Class[0]);
                        declaredMethod.setAccessible(true);
                        Object objInvoke = declaredMethod.invoke(this.httpClient.dispatcher(), new Object[0]);
                        if (objInvoke instanceof ExecutorService) {
                            ((ExecutorService) objInvoke).shutdown();
                        }
                    }
                } catch (Exception unused) {
                }
                if (this.httpClient.connectionPool() != null) {
                    this.httpClient.connectionPool().evictAll();
                }
                this.httpClient = null;
            }
        }
        CacheManager cacheManager = this.apiVersionCache;
        if (cacheManager != null) {
            cacheManager.clear();
            this.apiVersionCache = null;
        }
        SegmentLock segmentLock = this.segmentLock;
        if (segmentLock != null) {
            segmentLock.clear();
            this.segmentLock = null;
        }
    }

    protected void sleepOnInternalError(int i, int i2, Response response, InterfaceLogBean interfaceLogBean) throws InterruptedException, IOException, ServiceException {
        String strString;
        if (i > i2) {
            try {
                strString = response.body().string();
            } catch (IOException unused) {
                strString = null;
            }
            throw new ServiceException("Encountered too many 5xx errors (" + i + "), aborting request.", strString);
        }
        ServiceUtils.closeStream(response);
        long jPow = ((int) Math.pow(2.0d, i)) * 50;
        if (log.isWarnEnabled()) {
            log.warn((CharSequence) ("Encountered " + i + " Internal Server error(s), will retry in " + jPow + "ms"));
        }
        try {
            Thread.sleep(jPow);
        } catch (InterruptedException unused2) {
        }
    }
}
