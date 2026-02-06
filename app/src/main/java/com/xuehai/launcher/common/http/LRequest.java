package com.xuehai.launcher.common.http;

import android.text.TextUtils;
import com.xuehai.launcher.common.http.LRequestHeader;
import com.xuehai.launcher.common.log.MyLog;
import com.zaze.utils.HttpUtil;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001)B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010%\u001a\u00020\u001cJ\u0006\u0010&\u001a\u00020'J\u0006\u0010(\u001a\u00020\u0003R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0011\u0010!\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0011\u0010#\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\f¨\u0006*"}, d2 = {"Lcom/xuehai/launcher/common/http/LRequest;", "", "builder", "Lcom/xuehai/launcher/common/http/LRequest$Builder;", "(Lcom/xuehai/launcher/common/http/LRequest$Builder;)V", "body", "Lcom/xuehai/launcher/common/http/LRequestBody;", "getBody", "()Lcom/xuehai/launcher/common/http/LRequestBody;", "connectTimeout", "", "getConnectTimeout", "()I", "extMap", "Ljava/util/HashMap;", "", "getExtMap", "()Ljava/util/HashMap;", "header", "Lcom/xuehai/launcher/common/http/LRequestHeader;", "getHeader", "()Lcom/xuehai/launcher/common/http/LRequestHeader;", "method", "getMethod", "()Ljava/lang/String;", "readTimeout", "getReadTimeout", "saveError", "", "getSaveError", "()Z", "toast", "getToast", "url", "getUrl", "writeTimeout", "getWriteTimeout", "hasTimeout", "log", "", "newBuilder", "Builder", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LRequest {
    private final LRequestBody body;
    private final int connectTimeout;
    private final HashMap<String, Object> extMap;
    private final LRequestHeader header;
    private final String method;
    private final int readTimeout;
    private final boolean saveError;
    private final boolean toast;
    private final String url;
    private final int writeTimeout;

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u00105\u001a\u00020\u0007J\u0014\u00106\u001a\u00020\u00002\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\u0006\u00107\u001a\u00020\u0000J\u0014\u00108\u001a\u00020\u00002\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\u0016\u0010\u001a\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\u0017J\u001a\u0010;\u001a\u00020\u00002\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010=J\u000e\u0010>\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010?\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010@\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010&\u001a\u00020\u00002\u0006\u0010A\u001a\u00020'J\u000e\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0010J\u000e\u0010,\u001a\u00020\u00002\u0006\u0010E\u001a\u00020'J(\u0010/\u001a\u00020\u00002\u0006\u0010/\u001a\u00020\u00172\u0016\b\u0002\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0017\u0018\u00010=H\u0007R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0016X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0017X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0012\"\u0004\b%\u0010\u0014R\u001a\u0010&\u001a\u00020'X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020'X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010)\"\u0004\b.\u0010+R\u001a\u0010/\u001a\u00020\u0017X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010 \"\u0004\b1\u0010\"R\u001a\u00102\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0012\"\u0004\b4\u0010\u0014¨\u0006G"}, d2 = {"Lcom/xuehai/launcher/common/http/LRequest$Builder;", "", "()V", "defaultBuilder", "Lcom/xuehai/launcher/common/http/LRequestHeader$DefaultBuilder;", "(Lcom/xuehai/launcher/common/http/LRequestHeader$DefaultBuilder;)V", "request", "Lcom/xuehai/launcher/common/http/LRequest;", "(Lcom/xuehai/launcher/common/http/LRequest;)V", "body", "Lcom/xuehai/launcher/common/http/LRequestBody;", "getBody$common_studentToBRelease", "()Lcom/xuehai/launcher/common/http/LRequestBody;", "setBody$common_studentToBRelease", "(Lcom/xuehai/launcher/common/http/LRequestBody;)V", "connectTimeout", "", "getConnectTimeout$common_studentToBRelease", "()I", "setConnectTimeout$common_studentToBRelease", "(I)V", "extMap", "Ljava/util/HashMap;", "", "getExtMap$common_studentToBRelease", "()Ljava/util/HashMap;", "header", "Lcom/xuehai/launcher/common/http/LRequestHeader;", "getHeader$common_studentToBRelease", "()Lcom/xuehai/launcher/common/http/LRequestHeader;", "method", "getMethod$common_studentToBRelease", "()Ljava/lang/String;", "setMethod$common_studentToBRelease", "(Ljava/lang/String;)V", "readTimeout", "getReadTimeout$common_studentToBRelease", "setReadTimeout$common_studentToBRelease", "saveError", "", "getSaveError$common_studentToBRelease", "()Z", "setSaveError$common_studentToBRelease", "(Z)V", "toast", "getToast$common_studentToBRelease", "setToast$common_studentToBRelease", "url", "getUrl$common_studentToBRelease", "setUrl$common_studentToBRelease", "writeTimeout", "getWriteTimeout$common_studentToBRelease", "setWriteTimeout$common_studentToBRelease", "build", "delete", "get", "head", "name", "value", "headers", "headerMap", "", "patch", "post", "put", "needSave", "setTimeout", "", "timeMillis", "enable", "map", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        private LRequestBody body;
        private int connectTimeout;
        private final HashMap<String, Object> extMap;
        private final LRequestHeader header;
        private String method;
        private int readTimeout;
        private boolean saveError;
        private boolean toast;
        private String url;
        private int writeTimeout;

        public Builder() {
            this.url = "";
            this.method = "GET";
            this.extMap = new HashMap<>();
            this.header = new LRequestHeader();
        }

        public Builder(LRequest lRequest) {
            Intrinsics.checkNotNullParameter(lRequest, "request");
            this.url = "";
            this.method = "GET";
            this.extMap = new HashMap<>();
            this.url = lRequest.getUrl();
            this.method = lRequest.getMethod();
            LRequestHeader header = lRequest.getHeader();
            this.header = header;
            header.headers(lRequest.getHeader().headers);
            this.body = lRequest.getBody();
            this.extMap.putAll(lRequest.getExtMap());
            this.toast = lRequest.getToast();
            this.saveError = lRequest.getSaveError();
        }

        public Builder(LRequestHeader.DefaultBuilder defaultBuilder) {
            Intrinsics.checkNotNullParameter(defaultBuilder, "defaultBuilder");
            this.url = "";
            this.method = "GET";
            this.extMap = new HashMap<>();
            this.header = new LRequestHeader(defaultBuilder);
        }

        public static Builder delete$default(Builder builder, LRequestBody lRequestBody, int i, Object obj) {
            if ((i & 1) != 0) {
                lRequestBody = null;
            }
            return builder.delete(lRequestBody);
        }

        public static Builder head$default(Builder builder, LRequestBody lRequestBody, int i, Object obj) {
            if ((i & 1) != 0) {
                lRequestBody = null;
            }
            return builder.head(lRequestBody);
        }

        public static Builder url$default(Builder builder, String str, Map map, int i, Object obj) {
            if ((i & 2) != 0) {
                map = null;
            }
            return builder.url(str, map);
        }

        public final LRequest build() {
            return new LRequest(this, null);
        }

        public final Builder delete() {
            return delete$default(this, null, 1, null);
        }

        public final Builder delete(LRequestBody body) {
            this.method = "DELETE";
            this.body = body;
            return this;
        }

        public final Builder get() {
            this.method = "GET";
            return this;
        }

        public final LRequestBody getBody() {
            return this.body;
        }

        public final int getConnectTimeout() {
            return this.connectTimeout;
        }

        public final HashMap<String, Object> getExtMap$common_studentToBRelease() {
            return this.extMap;
        }

        public final LRequestHeader getHeader() {
            return this.header;
        }

        public final String getMethod() {
            return this.method;
        }

        public final int getReadTimeout() {
            return this.readTimeout;
        }

        public final boolean getSaveError() {
            return this.saveError;
        }

        public final boolean getToast() {
            return this.toast;
        }

        public final String getUrl() {
            return this.url;
        }

        public final int getWriteTimeout() {
            return this.writeTimeout;
        }

        public final Builder head() {
            return head$default(this, null, 1, null);
        }

        public final Builder head(LRequestBody body) {
            this.method = "HEAD";
            this.body = body;
            return this;
        }

        public final Builder header(String name, String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                this.header.header(name, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this;
        }

        public final Builder headers(Map<String, ? extends Object> headerMap) {
            Intrinsics.checkNotNullParameter(headerMap, "headerMap");
            this.header.headers(headerMap);
            return this;
        }

        public final Builder patch(LRequestBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            this.method = "PATCH";
            this.body = body;
            return this;
        }

        public final Builder post(LRequestBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            this.method = "POST";
            this.body = body;
            return this;
        }

        public final Builder put(LRequestBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            this.method = "PUT";
            this.body = body;
            return this;
        }

        public final Builder saveError(boolean needSave) {
            this.saveError = needSave;
            return this;
        }

        public final void setBody$common_studentToBRelease(LRequestBody lRequestBody) {
            this.body = lRequestBody;
        }

        public final void setConnectTimeout$common_studentToBRelease(int i) {
            this.connectTimeout = i;
        }

        public final void setMethod$common_studentToBRelease(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.method = str;
        }

        public final void setReadTimeout$common_studentToBRelease(int i) {
            this.readTimeout = i;
        }

        public final void setSaveError$common_studentToBRelease(boolean z) {
            this.saveError = z;
        }

        public final void setTimeout(int timeMillis) {
            if (timeMillis > 0) {
                this.connectTimeout = timeMillis;
                this.readTimeout = timeMillis;
                this.writeTimeout = timeMillis;
            }
        }

        public final void setToast$common_studentToBRelease(boolean z) {
            this.toast = z;
        }

        public final void setUrl$common_studentToBRelease(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.url = str;
        }

        public final void setWriteTimeout$common_studentToBRelease(int i) {
            this.writeTimeout = i;
        }

        public final Builder toast(boolean enable) {
            this.toast = enable;
            return this;
        }

        public final Builder url(String str) {
            Intrinsics.checkNotNullParameter(str, "url");
            return url$default(this, str, null, 2, null);
        }

        public final Builder url(String url, Map<String, String> map) {
            Intrinsics.checkNotNullParameter(url, "url");
            if (!TextUtils.isEmpty(url)) {
                String strBuildGetRequest = HttpUtil.buildGetRequest(url, map);
                Intrinsics.checkNotNullExpressionValue(strBuildGetRequest, "buildGetRequest(url, map)");
                this.url = strBuildGetRequest;
            }
            return this;
        }
    }

    private LRequest(Builder builder) {
        this.url = builder.getUrl();
        this.method = builder.getMethod();
        this.header = builder.getHeader();
        this.body = builder.getBody();
        this.connectTimeout = builder.getConnectTimeout();
        this.readTimeout = builder.getReadTimeout();
        this.writeTimeout = builder.getWriteTimeout();
        this.saveError = builder.getSaveError();
        this.toast = builder.getToast();
        this.extMap = builder.getExtMap$common_studentToBRelease();
    }

    public LRequest(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    public final LRequestBody getBody() {
        return this.body;
    }

    public final int getConnectTimeout() {
        return this.connectTimeout;
    }

    public final HashMap<String, Object> getExtMap() {
        return this.extMap;
    }

    public final LRequestHeader getHeader() {
        return this.header;
    }

    public final String getMethod() {
        return this.method;
    }

    public final int getReadTimeout() {
        return this.readTimeout;
    }

    public final boolean getSaveError() {
        return this.saveError;
    }

    public final boolean getToast() {
        return this.toast;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getWriteTimeout() {
        return this.writeTimeout;
    }

    public final boolean hasTimeout() {
        return (this.connectTimeout + this.readTimeout) + this.writeTimeout > 0;
    }

    public final void log() {
        StringBuilder sb = new StringBuilder();
        sb.append("url: ");
        sb.append(this.url);
        sb.append('(');
        sb.append(this.method);
        sb.append("); postData : ");
        LRequestBody lRequestBody = this.body;
        sb.append(lRequestBody != null ? lRequestBody.getContent() : null);
        MyLog.d("Http[MDM]", sb.toString());
        this.header.log();
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }
}
