package com.xh.xhcore.common.http.strategy;

import java.util.HashMap;
import java.util.Map;

public class HttpConst {
    public static final String AUTHORIZATION = "Authorization";
    public static final String CLIENT_SPAN_ID = "X-B3-SpanId";
    public static final String CLIENT_TRACE_ID = "X-B3-TraceId";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_MD5 = "Content-MD5";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String ETAG = "ETag";
    public static final String HEAD_LIST_KEY = "headList";
    public static final boolean HTTPS_ENABLE = true;
    public static final boolean HTTPS_ENVIRONMENT_TEST = false;
    public static final String HTTP_SIGNATURE_KEY = "sign";
    public static final String JSON_HTTP_RESPONSE_KEY = "httpResponse";
    public static final String JSON_TIMEOUT_KEY = "timeout";
    public static final String LINE_SPLIT = "\r\n";
    public static final String NETWORK_ERROR_TAG = "networkErrorTag";
    public static final String POST_DATA = "postData";
    public static final String REQUEST_METHOD = "requestMethod";
    public static final String TIMESTAMP_KEY = "t";
    public static final String UPLOAD_OS_TYPE_OBS = "1";
    public static final String UPLOAD_OS_TYPE_OSS = "0";
    public static final boolean UPLOAD_WHOLE_NETWORK_ERROR_ENABLE = true;
    public static final String XHCORE_VERSION = "XHCore-Version";
    public static final String XH_DOWNLOAD_FILE_TMP = ".tmp";
    public static final String XH_HTTP_HEAD_MD5 = "XueHai-MD5";
    public static final String XH_NETWORK_EXECUTE_STATE_END = "xhNetworkEnd";
    public static final String XH_NETWORK_EXECUTE_STATE_START = "xhNetworkStart";
    public static final String XH_NETWORK_EXECUTE_STATE_TAG = "xhNetworkExecuteStateTag";
    public static final String XH_OSS_DOMAIN = "xh.oss.com";
    public static final String XH_USER_AGENT_KEY = "User-Agent";
    public static final String XH_USER_AGENT_VALUE = "XueHaiNetworkClient/1.0";
    public static final Map<Integer, String> httpCodeToDescription = new HashMap<Integer, String>() {
        {
            put(100, "Continue");
            put(101, "Switching Protocols");
            put(200, "OK");
            put(201, "Created");
            put(202, "Accepted");
            put(203, "Non-Authoritative Information");
            put(204, "No Content");
            put(205, "Reset Content");
            put(206, "Partial Content");
            put(300, "Multiple Choices");
            put(301, "Moved Permanently");
            put(302, "Found");
            put(303, "See Other");
            put(304, "Not Modified");
            put(305, "Use Proxy");
            put(306, "Unused Code");
            put(307, "Temporary Redirect");
            put(400, "Bad Request");
            put(401, "Unauthorized");
            put(402, "Payment Required");
            put(403, "Forbidden");
            put(404, "Not Found");
            put(405, "Menthod Not Allowed");
            put(406, "Not Acceptable");
            put(407, "Proxy Authentication Required");
            put(408, "Reqeust Timeout");
            put(409, "Conflict");
            put(410, "Gone");
            put(411, "Length Required");
            put(412, "Precondition Failed");
            put(413, "Request Entity Too Large");
            put(414, "Request-URI Too Long");
            put(415, "Unsupported Media Type");
            put(416, "Request Range Not Satisfialbe");
            put(417, "Expectation Failed");
            put(500, "Internal Server Error");
            put(501, "Not Implemented");
            put(502, "Bad Gateway");
            put(503, "Service Unavailable");
            put(504, "Gateway Timeout");
        }
    };

    public class HttpCode {
        public static final int HTTP_ACCEPTED = 202;
        public static final int HTTP_BAD_GATEWAY = 502;
        public static final int HTTP_BAD_REQUEST = 400;
        public static final int HTTP_CONFLICT = 409;
        public static final int HTTP_CONTINUE = 100;
        public static final int HTTP_CREATED = 201;
        public static final int HTTP_EXPECTATION_FAILED = 417;
        public static final int HTTP_FORBIDDEN = 403;
        public static final int HTTP_FOUND = 302;
        public static final int HTTP_GATEWAY_TIMEOUT = 504;
        public static final int HTTP_GONE = 410;
        public static final int HTTP_HTTP_VERSION_NOT_SUPPORTED = 505;
        public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
        public static final int HTTP_LENGTH_REQUIRED = 411;
        public static final int HTTP_METHOD_NOT_ALLOWED = 405;
        public static final int HTTP_MOVED_PERMANENTLY = 301;
        public static final int HTTP_MULTIPLE_CHOICES = 300;
        public static final int HTTP_NON_AUTHORATATIVE = 203;
        public static final int HTTP_NOT_ACCEPTABLE = 406;
        public static final int HTTP_NOT_FOUND = 404;
        public static final int HTTP_NOT_IMPLEMENTED = 501;
        public static final int HTTP_NOT_MODIFIED = 304;
        public static final int HTTP_NO_CONTENT = 204;
        public static final int HTTP_OK = 200;
        public static final int HTTP_PARTIAL_CONTENT = 206;
        public static final int HTTP_PAYMENT_REQD = 402;
        public static final int HTTP_PRECONDITION_FAILED = 412;
        public static final int HTTP_PROXY_AUTH_REQD = 407;
        public static final int HTTP_REQUEST_RANGE_NOT_SATISFIABLE = 416;
        public static final int HTTP_REQUEST_TIMEOUT = 408;
        public static final int HTTP_REQ_ENTITY_TOO_LARGE = 413;
        public static final int HTTP_REQ_URI_TOO_LONG = 414;
        public static final int HTTP_RESET_CONTENT = 205;
        public static final int HTTP_SEE_OTHER = 303;
        public static final int HTTP_SERVICE_UNAVAILABLE = 503;
        public static final int HTTP_SWITCHING_PROCOTOLS = 101;
        public static final int HTTP_TEMPORARY_REDIRECT = 307;
        public static final int HTTP_UNAUTHORIZED = 401;
        public static final int HTTP_UNSUPPORTED_MEDIA_TYPE = 415;
        public static final int HTTP_UNUSED_3XX = 306;
        public static final int HTTP_USE_PROXY = 305;

        public HttpCode() {
        }
    }

    public enum RequestStrategy {
        STRATEGY_LIB_CURL,
        STRATEGY_OK_HTTP
    }
}
