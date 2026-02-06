package com.obs.services.internal.utils;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import com.obs.services.ObsConfiguration;
import com.obs.services.exception.ObsException;
import com.obs.services.internal.Constants;
import com.obs.services.internal.ObsProperties;
import com.obs.services.internal.ServiceException;
import com.obs.services.model.AuthTypeEnum;
import com.obs.services.model.HttpProtocolTypeEnum;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.Headers;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ServiceUtils {
    protected static final String _iso8601DateParserString = "yyyy-MM-dd";
    protected static final String iso8601DateMidnightParserString = "yyyy-MM-dd'T'00:00:00'Z'";
    protected static final String iso8601DateParserString = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    protected static final String iso8601DateParser_WalrusString = "yyyy-MM-dd'T'HH:mm:ss";
    private static final ILogger log = LoggerBuilder.getLogger((Class<?>) ServiceUtils.class);
    private static Pattern pattern = Pattern.compile("^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$");
    protected static final String rfc822DateParserString = "EEE, dd MMM yyyy HH:mm:ss z";

    public static void asserParameterNotNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void asserParameterNotNull(String str, String str2) {
        if (!isValid(str)) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static void asserParameterNotNull2(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static ObsProperties changeFromObsConfiguration(ObsConfiguration obsConfiguration) throws NumberFormatException {
        ObsProperties obsProperties = new ObsProperties();
        String endPoint = obsConfiguration.getEndPoint();
        while (true) {
            int iLastIndexOf = endPoint.lastIndexOf("/");
            if (iLastIndexOf != endPoint.length() - 1) {
                break;
            }
            endPoint = endPoint.substring(0, iLastIndexOf);
        }
        if (endPoint.startsWith("http://")) {
            obsConfiguration.setHttpsOnly(false);
            endPoint = endPoint.substring(7);
        } else if (endPoint.startsWith("https://")) {
            obsConfiguration.setHttpsOnly(true);
            endPoint = endPoint.substring(8);
        }
        int iLastIndexOf2 = endPoint.lastIndexOf(":");
        if (iLastIndexOf2 > 0) {
            int i = Integer.parseInt(endPoint.substring(iLastIndexOf2 + 1));
            if (obsConfiguration.isHttpsOnly()) {
                obsConfiguration.setEndpointHttpsPort(i);
            } else {
                obsConfiguration.setEndpointHttpPort(i);
            }
            endPoint = endPoint.substring(0, iLastIndexOf2);
        }
        if (pattern.matcher(endPoint).matches()) {
            obsConfiguration.setPathStyle(true);
        }
        if (obsConfiguration.isPathStyle() || obsConfiguration.isCname()) {
            obsConfiguration.setAuthTypeNegotiation(false);
            if (obsConfiguration.getAuthType() == AuthTypeEnum.OBS) {
                obsConfiguration.setAuthType(AuthTypeEnum.V2);
            }
        }
        obsConfiguration.setEndPoint(endPoint);
        obsProperties.setProperty("obs-endpoint", obsConfiguration.getEndPoint());
        obsProperties.setProperty("obs-endpoint-http-port", String.valueOf(obsConfiguration.getEndpointHttpPort()));
        obsProperties.setProperty("obs.https-only", String.valueOf(obsConfiguration.isHttpsOnly()));
        obsProperties.setProperty("obs.disable-dns-buckets", String.valueOf(obsConfiguration.isPathStyle()));
        obsProperties.setProperty("obs-endpoint-https-port", String.valueOf(obsConfiguration.getEndpointHttpsPort()));
        obsProperties.setProperty("httpclient.socket-timeout-ms", String.valueOf(obsConfiguration.getSocketTimeout()));
        obsProperties.setProperty("httpclient.max-connections", String.valueOf(obsConfiguration.getMaxConnections()));
        obsProperties.setProperty("httpclient.retry-max", String.valueOf(obsConfiguration.getMaxErrorRetry()));
        obsProperties.setProperty("httpclient.connection-timeout-ms", String.valueOf(obsConfiguration.getConnectionTimeout()));
        obsProperties.setProperty("httpclient.proxy-enable", String.valueOf(Boolean.FALSE));
        obsProperties.setProperty("uploads.stream-retry-buffer-size", String.valueOf(obsConfiguration.getUploadStreamRetryBufferSize() > 0 ? obsConfiguration.getUploadStreamRetryBufferSize() : 524288));
        obsProperties.setProperty("httpclient.validate-certificate", String.valueOf(obsConfiguration.isValidateCertificate()));
        obsProperties.setProperty("obs.verify-content-type", String.valueOf(obsConfiguration.isVerifyResponseContentType()));
        obsProperties.setProperty("httpclient.write-buffer-size", String.valueOf(obsConfiguration.getWriteBufferSize()));
        obsProperties.setProperty("httpclient.read-buffer-size", String.valueOf(obsConfiguration.getReadBufferSize()));
        obsProperties.setProperty("socket.write-buffer-size", String.valueOf(obsConfiguration.getSocketWriteBufferSize()));
        obsProperties.setProperty("socket.read-buffer-size", String.valueOf(obsConfiguration.getSocketReadBufferSize()));
        obsProperties.setProperty("httpclient.strict-hostname-verification", String.valueOf(obsConfiguration.isStrictHostnameVerification()));
        obsProperties.setProperty("httpclient.idle-connection-time", String.valueOf(obsConfiguration.getIdleConnectionTime()));
        obsProperties.setProperty("httpclient.max-idle-connections", String.valueOf(obsConfiguration.getMaxIdleConnections()));
        obsProperties.setProperty("httpclient.ssl-provider", obsConfiguration.getSslProvider() == null ? "" : obsConfiguration.getSslProvider());
        obsProperties.setProperty("httpclient.keep-alive", String.valueOf(obsConfiguration.isKeepAlive()));
        obsProperties.setProperty("filesystem.delimiter", obsConfiguration.getDelimiter() != null ? obsConfiguration.getDelimiter() : "/");
        obsProperties.setProperty("httpclient.protocol", (obsConfiguration.getHttpProtocolType() == null ? HttpProtocolTypeEnum.HTTP1_1 : obsConfiguration.getHttpProtocolType()).getCode());
        obsProperties.setProperty("httpclient.is-cname", String.valueOf(obsConfiguration.isCname()));
        obsProperties.setProperty("httpclient.auth-type-negotiation", String.valueOf(obsConfiguration.isAuthTypeNegotiation()));
        if (obsConfiguration.getHttpProxy() != null) {
            obsProperties.setProperty("httpclient.proxy-enable", String.valueOf(Boolean.TRUE));
            obsProperties.setProperty("httpclient.proxy-host", obsConfiguration.getHttpProxy().getProxyAddr());
            obsProperties.setProperty("httpclient.proxy-port", String.valueOf(obsConfiguration.getHttpProxy().getProxyPort()));
            obsProperties.setProperty("httpclient.proxy-user", obsConfiguration.getHttpProxy().getProxyUName());
            obsProperties.setProperty("httpclient.proxy-password", obsConfiguration.getHttpProxy().getUserPaaswd());
            obsProperties.setProperty("httpclient.proxy-domain", obsConfiguration.getHttpProxy().getDomain());
            obsProperties.setProperty("httpclient.proxy-workstation", obsConfiguration.getHttpProxy().getWorkstation());
        }
        return obsProperties;
    }

    public static ObsException changeFromServiceException(ServiceException serviceException) {
        String str;
        if (serviceException.getResponseCode() < 0) {
            return new ObsException("OBS servcie Error Message. " + serviceException.getMessage(), serviceException.getCause());
        }
        StringBuilder sb = new StringBuilder();
        if (serviceException.getMessage() != null) {
            str = "Error message:" + serviceException.getMessage();
        } else {
            str = "";
        }
        sb.append(str);
        sb.append("OBS servcie Error Message.");
        ObsException obsException = new ObsException(sb.toString(), serviceException.getXmlMessage(), serviceException.getCause());
        obsException.setErrorCode(serviceException.getErrorCode());
        obsException.setErrorMessage(serviceException.getErrorMessage() == null ? serviceException.getMessage() : serviceException.getErrorMessage());
        obsException.setErrorRequestId(serviceException.getErrorRequestId());
        obsException.setErrorHostId(serviceException.getErrorHostId());
        obsException.setResponseCode(serviceException.getResponseCode());
        obsException.setResponseStatus(serviceException.getResponseStatus());
        obsException.setResponseHeaders(serviceException.getResponseHeaders());
        obsException.setErrorIndicator(serviceException.getErrorIndicator());
        return obsException;
    }

    public static Map<String, Object> cleanRestMetadataMap(Map<String, List<String>> map, String str, String str2) throws UnsupportedEncodingException {
        String strDecode;
        ArrayList arrayList;
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) "Cleaning up REST metadata items");
        }
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        if (map != null) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (key != null && value != null) {
                    Object iso8601Date = value.size() == 1 ? value.get(0) : value;
                    if ("Date".equalsIgnoreCase(key) || "Last-Modified".equalsIgnoreCase(key)) {
                        if (log.isDebugEnabled()) {
                            log.debug((CharSequence) ("Parsing date string '" + iso8601Date + "' into Date object for key: " + key));
                        }
                        try {
                            iso8601Date = parseRfc822Date(iso8601Date.toString());
                        } catch (ParseException e) {
                            try {
                                iso8601Date = parseIso8601Date(iso8601Date.toString());
                            } catch (ParseException unused) {
                                if (log.isWarnEnabled()) {
                                    log.warn("Date string is not RFC 822 compliant for metadata field " + key, e);
                                }
                            }
                        }
                        treeMap.put(key, iso8601Date);
                    } else if (key.toLowerCase().startsWith(str)) {
                        try {
                            if (key.toLowerCase().startsWith(str2)) {
                                key = URLDecoder.decode(key.substring(str2.length(), key.length()), "UTF-8");
                                if (log.isDebugEnabled()) {
                                    log.debug((CharSequence) ("Removed meatadata header prefix " + str2 + " from key: " + key + "=>" + key));
                                }
                            } else {
                                key = key.substring(str.length(), key.length());
                            }
                        } catch (UnsupportedEncodingException unused2) {
                            if (log.isDebugEnabled()) {
                                log.debug((CharSequence) ("Error to decode value of key:" + key));
                            }
                        }
                        if (iso8601Date instanceof List) {
                            arrayList = new ArrayList(value.size());
                            Iterator<String> it = value.iterator();
                            while (it.hasNext()) {
                                String next = it.next();
                                arrayList.add(next != null ? URLDecoder.decode(next, "UTF-8") : null);
                            }
                            iso8601Date = arrayList;
                            treeMap.put(key, iso8601Date);
                        } else {
                            strDecode = URLDecoder.decode(iso8601Date.toString(), "UTF-8");
                            iso8601Date = strDecode;
                            treeMap.put(key, iso8601Date);
                        }
                    } else {
                        if (key.toLowerCase().startsWith("x-obs-")) {
                            try {
                                if (key.toLowerCase().startsWith("x-obs-meta-")) {
                                    key = URLDecoder.decode(key.substring(11, key.length()), "UTF-8");
                                    if (log.isDebugEnabled()) {
                                        log.debug((CharSequence) ("Removed meatadata header prefix x-obs-meta- from key: " + key + "=>" + key));
                                    }
                                } else {
                                    key = key.substring(6, key.length());
                                }
                                if (iso8601Date instanceof List) {
                                    arrayList = new ArrayList(value.size());
                                    Iterator<String> it2 = value.iterator();
                                    while (it2.hasNext()) {
                                        String next2 = it2.next();
                                        arrayList.add(next2 != null ? URLDecoder.decode(next2, "UTF-8") : null);
                                    }
                                    iso8601Date = arrayList;
                                } else {
                                    strDecode = URLDecoder.decode(iso8601Date.toString(), "UTF-8");
                                    iso8601Date = strDecode;
                                }
                            } catch (UnsupportedEncodingException unused3) {
                                if (log.isDebugEnabled()) {
                                    log.debug((CharSequence) ("Error to decode value of key:" + key));
                                }
                            }
                        } else if (Constants.ALLOWED_RESPONSE_HTTP_HEADER_METADATA_NAMES.contains(key.toLowerCase(Locale.getDefault()))) {
                            if (log.isDebugEnabled()) {
                                log.debug((CharSequence) ("Leaving HTTP header item unchanged: " + key + "=" + value));
                            }
                        } else if (log.isDebugEnabled()) {
                            log.debug((CharSequence) ("Ignoring metadata item: " + key + "=" + value));
                        }
                        treeMap.put(key, iso8601Date);
                    }
                }
            }
        }
        return treeMap;
    }

    public static Map<String, String> cleanRestMetadataMapV2(Map<String, String> map, String str, String str2) throws UnsupportedEncodingException {
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) "Cleaning up REST metadata items");
        }
        IdentityHashMap identityHashMap = new IdentityHashMap();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String string = key != null ? key.toString() : "";
                if (string.toLowerCase().startsWith(str)) {
                    try {
                        if (key.toLowerCase().startsWith(str2)) {
                            key = URLDecoder.decode(key.substring(str2.length(), key.length()), "UTF-8");
                            if (log.isDebugEnabled()) {
                                log.debug((CharSequence) ("Removed meatadata header prefix " + str2 + " from key: " + key + "=>" + key));
                            }
                        } else {
                            key = key.substring(str.length(), key.length());
                        }
                        value = URLDecoder.decode(value.toString(), "UTF-8");
                    } catch (UnsupportedEncodingException unused) {
                        if (log.isDebugEnabled()) {
                            log.debug((CharSequence) ("Error to decode value of key:" + key));
                        }
                    }
                } else if (key.toLowerCase().startsWith("x-obs-")) {
                    try {
                        if (key.toLowerCase().startsWith("x-obs-meta-")) {
                            key = URLDecoder.decode(key.substring(11, key.length()), "UTF-8");
                            if (log.isDebugEnabled()) {
                                log.debug((CharSequence) ("Removed meatadata header prefix x-obs-meta- from key: " + key + "=>" + key));
                            }
                        } else {
                            key = key.substring(6, key.length());
                        }
                        value = URLDecoder.decode(value.toString(), "UTF-8");
                    } catch (UnsupportedEncodingException unused2) {
                        if (log.isDebugEnabled()) {
                            log.debug((CharSequence) ("Error to decode value of key:" + key));
                        }
                    }
                } else if (Constants.ALLOWED_RESPONSE_HTTP_HEADER_METADATA_NAMES.contains(string.toLowerCase(Locale.getDefault()))) {
                    if (log.isDebugEnabled()) {
                        log.debug((CharSequence) ("Leaving HTTP header item unchanged: " + string + "=" + value));
                    }
                    identityHashMap.put(new String(string), value);
                } else if (log.isDebugEnabled()) {
                    log.debug((CharSequence) ("Ignoring metadata item: " + string + "=" + value));
                }
                string = key;
                identityHashMap.put(new String(string), value);
            }
        }
        return identityHashMap;
    }

    public static void closeStream(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                if (log.isWarnEnabled()) {
                    log.warn(e);
                }
            }
        }
    }

    public static String computeMD5(String str) throws ServiceException {
        try {
            return toBase64(computeMD5Hash(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException unused) {
            throw new ServiceException("Failed to get MD5 for requestXmlElement:" + str);
        } catch (IOException unused2) {
            throw new ServiceException("Failed to get MD5 for requestXmlElement:" + str);
        } catch (NoSuchAlgorithmException unused3) {
            throw new ServiceException("Failed to get MD5 for requestXmlElement:" + str);
        }
    }

    public static byte[] computeMD5Hash(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bArr = new byte[16384];
            while (true) {
                int i = bufferedInputStream.read(bArr, 0, 16384);
                if (i == -1) {
                    return messageDigest.digest();
                }
                messageDigest.update(bArr, 0, i);
            }
        } finally {
            closeStream(bufferedInputStream);
        }
    }

    public static byte[] computeMD5Hash(InputStream inputStream, long j, long j2) throws Throwable {
        long j3;
        int i;
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            long j4 = 0;
            if (j2 > 0) {
                try {
                    long jSkip = bufferedInputStream2.skip(j2);
                    if (log.isDebugEnabled()) {
                        log.debug((CharSequence) ("computeMD5Hash: Skip " + jSkip + " bytes"));
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    closeStream(bufferedInputStream);
                    throw th;
                }
            }
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bArr = new byte[16384];
            if (16384 > j) {
                j3 = j;
                while (j4 < j && (i = bufferedInputStream2.read(bArr, 0, (int) j3)) != -1) {
                    messageDigest.update(bArr, 0, i);
                    j4 += i;
                    j3 = j - j4;
                    if (j3 > 16384) {
                    }
                }
                byte[] bArrDigest = messageDigest.digest();
                closeStream(bufferedInputStream2);
                return bArrDigest;
            }
            j3 = 16384;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static byte[] computeMD5Hash(byte[] bArr) throws NoSuchAlgorithmException, IOException {
        return computeMD5Hash(new ByteArrayInputStream(bArr));
    }

    public static String formatIso8601Date(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(Constants.GMT_TIMEZONE);
        return simpleDateFormat.format(date);
    }

    public static String formatIso8601MidnightDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00'Z'");
        simpleDateFormat.setTimeZone(Constants.GMT_TIMEZONE);
        return simpleDateFormat.format(date);
    }

    public static String formatRfc822Date(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(Constants.GMT_TIMEZONE);
        return simpleDateFormat.format(date);
    }

    public static byte[] fromBase64(String str) throws UnsupportedEncodingException {
        return ReflectUtils.fromBase64(str);
    }

    public static byte[] fromHex(String str) {
        if ((str.length() & 1) != 0 || str.replaceAll("[a-fA-F0-9]", "").length() > 0) {
            throw new IllegalArgumentException("'" + str + "' is not a hex string");
        }
        byte[] bArr = new byte[(str.length() + 1) / 2];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i + 2;
            bArr[i2] = (byte) Integer.parseInt(str.substring(i, i3), 16);
            i = i3;
            i2++;
        }
        return bArr;
    }

    public static String generateHostnameForBucket(String str, boolean z, String str2) {
        if (!isBucketNameValidDNSName(str)) {
            throw new IllegalArgumentException("the bucketName is illegal");
        }
        if (z) {
            return str2;
        }
        return str + "." + str2;
    }

    public static SimpleDateFormat getExpirationDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(Constants.GMT_TIMEZONE);
        return simpleDateFormat;
    }

    public static SimpleDateFormat getHeaderDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        simpleDateFormat.setTimeZone(Constants.GMT_TIMEZONE);
        return simpleDateFormat;
    }

    public static SimpleDateFormat getLongDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        simpleDateFormat.setTimeZone(Constants.GMT_TIMEZONE);
        return simpleDateFormat;
    }

    public static SimpleDateFormat getShortDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        simpleDateFormat.setTimeZone(Constants.GMT_TIMEZONE);
        return simpleDateFormat;
    }

    public static boolean isBucketNameValidDNSName(String str) {
        if (str == null || str.length() > 63 || str.length() < 3 || !Pattern.matches("^[a-z0-9][a-z0-9.-]+$", str) || Pattern.matches("([0-9]{1,3}\\.){3}[0-9]{1,3}", str)) {
            return false;
        }
        String[] strArrSplit = str.split("\\.");
        for (int i = 0; i < strArrSplit.length; i++) {
            if (Pattern.matches("^-.*", strArrSplit[i]) || Pattern.matches(".*-$", strArrSplit[i]) || Pattern.matches("^$", strArrSplit[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValid(String str) {
        return (str == null || str.trim().equals("")) ? false : true;
    }

    public static boolean isValid2(String str) {
        return (str == null || str.equals("")) ? false : true;
    }

    public static String join(List<?> list, String str) {
        return join(list, str, false);
    }

    public static String join(List<?> list, String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String string = list.get(i).toString();
            if (z) {
                string = string.trim();
            }
            sb.append(string);
            if (i < list.size() - 1) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String join(Headers headers, String str, List<String> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : headers.toMultimap().entrySet()) {
            if (!list.contains(entry.getKey())) {
                sb.append(entry.getValue());
            }
            if (r4.size() - 1 > 0) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String join(int[] iArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iArr.length; i++) {
            sb.append(iArr[i]);
            if (i < iArr.length - 1) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String join(Object[] objArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objArr.length; i++) {
            sb.append(objArr[i]);
            if (i < objArr.length - 1) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static XMLReader loadXMLReader() throws ServiceException {
        try {
            return XMLReaderFactory.createXMLReader();
        } catch (Exception e) {
            e = e;
            String[] strArr = {"org.apache.crimson.parser.XMLReaderImpl", "org.xmlpull.v1.sax2.Driver"};
            for (int i = 0; i < 2; i++) {
                try {
                    return XMLReaderFactory.createXMLReader(strArr[i]);
                } catch (Exception unused) {
                }
            }
            throw new ServiceException("Failed to initialize a SAX XMLReader", e);
        }
    }

    public static Date parseIso8601Date(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        TimeZone timeZone = Constants.GMT_TIMEZONE;
        simpleDateFormat.setTimeZone(timeZone);
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException unused) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            simpleDateFormat2.setTimeZone(timeZone);
            try {
                return simpleDateFormat2.parse(str);
            } catch (ParseException e) {
                SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
                simpleDateFormat3.setTimeZone(timeZone);
                try {
                    return simpleDateFormat3.parse(str);
                } catch (Exception unused2) {
                    throw e;
                }
            }
        }
    }

    public static Date parseRfc822Date(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(Constants.GMT_TIMEZONE);
        return simpleDateFormat.parse(str);
    }

    public static String signWithHmacSha1(String str, String str2) throws NoSuchAlgorithmException, InvalidKeyException, ServiceException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), "HmacSHA1");
            try {
                Mac mac = Mac.getInstance("HmacSHA1");
                try {
                    mac.init(secretKeySpec);
                    try {
                        return toBase64(mac.doFinal(str2.getBytes("UTF-8")));
                    } catch (UnsupportedEncodingException e) {
                        throw new ServiceException("Unable to get bytes from canonical string", e);
                    }
                } catch (InvalidKeyException e2) {
                    throw new RuntimeException("Could not initialize the MAC algorithm", e2);
                }
            } catch (NoSuchAlgorithmException e3) {
                throw new ServiceException("Could not find sha1 algorithm", e3);
            }
        } catch (UnsupportedEncodingException e4) {
            throw new ServiceException("Unable to get bytes from secret string", e4);
        }
    }

    public static String toBase64(byte[] bArr) {
        return ReflectUtils.toBase64(bArr);
    }

    public static String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b);
            if (hexString.length() == 1) {
                sb.append("0");
            } else if (hexString.length() == 8) {
                hexString = hexString.substring(6);
            }
            sb.append(hexString);
        }
        return sb.toString().toLowerCase(Locale.getDefault());
    }

    public static String toString(InputStream inputStream) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        String string = null;
        if (inputStream != null) {
            StringBuilder sb = new StringBuilder();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    } catch (Throwable th2) {
                        th = th2;
                        closeStream(bufferedReader);
                        closeStream(inputStream);
                        throw th;
                    }
                }
                string = sb.toString();
                closeStream(bufferedReader);
                closeStream(inputStream);
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
            }
        }
        return string;
    }

    public static String toValid(String str) {
        return str == null ? "" : str;
    }
}
