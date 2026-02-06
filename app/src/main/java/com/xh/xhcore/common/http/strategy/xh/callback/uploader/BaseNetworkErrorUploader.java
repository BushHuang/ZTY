package com.xh.xhcore.common.http.strategy.xh.callback.uploader;

import android.os.Build;
import android.text.TextUtils;
import com.xh.logutils.LogManager;
import com.xh.xhcore.common.extension.ThrowableExtensionKt;
import com.xh.xhcore.common.http.XHErrorCodeUtil;
import com.xh.xhcore.common.util.JsonUtil;
import java.security.cert.CertPathValidatorException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u0000 42\u00020\u0001:\u00014B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020\u001eH&J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u0005J\u0010\u0010&\u001a\u00020\u00002\b\u0010(\u001a\u0004\u0018\u00010)J\u0014\u0010&\u001a\u00020\u00002\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050*J\u0016\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u0006J\u0010\u0010.\u001a\u00020/2\u0006\u0010(\u001a\u00020)H\u0002J\b\u00100\u001a\u00020\u0000H&J\u0006\u00101\u001a\u00020\u0005J\u000e\u00102\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rJ\u0006\u00103\u001a\u00020/J\u0012\u00103\u001a\u00020/2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016R6\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0014\"\u0004\b$\u0010\u0016¨\u00065"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/callback/uploader/BaseNetworkErrorUploader;", "Lcom/xh/xhcore/common/http/strategy/xh/callback/uploader/INetworkErrorUploader;", "()V", "basicHttpDetailMap", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "getBasicHttpDetailMap", "()Ljava/util/LinkedHashMap;", "setBasicHttpDetailMap", "(Ljava/util/LinkedHashMap;)V", "code", "", "getCode", "()I", "setCode", "(I)V", "description", "getDescription", "()Ljava/lang/String;", "setDescription", "(Ljava/lang/String;)V", "errorMessage", "", "getErrorMessage", "()Ljava/util/List;", "setErrorMessage", "(Ljava/util/List;)V", "isLastFail", "", "()Z", "setLastFail", "(Z)V", "proxyDescription", "getProxyDescription", "setProxyDescription", "allowUploadNetworkErrorLog", "appendErrorMessage", "appendedErrorMessage", "throwable", "", "", "appendHttpDetail", "key", "value", "appendSSLHandshakeExceptionErrorMessage", "", "fillHttpDetail", "getNetworkErrorDetail", "getTheDescription", "uploadNetworkError", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseNetworkErrorUploader implements INetworkErrorUploader {

    public static final Companion INSTANCE = new Companion(null);
    private boolean isLastFail;
    private int code = -1;
    private String description = "";
    private List<String> errorMessage = new ArrayList();
    private String proxyDescription = "";
    private LinkedHashMap<String, Object> basicHttpDetailMap = new LinkedHashMap<>();

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001`\bH\u0007¨\u0006\t"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/callback/uploader/BaseNetworkErrorUploader$Companion;", "", "()V", "logAndUploadNetworkError", "", "networkErrorDetail", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void logAndUploadNetworkError(LinkedHashMap<String, Object> networkErrorDetail) {
            Intrinsics.checkNotNullParameter(networkErrorDetail, "networkErrorDetail");
            LogManager.getInstance().uploadNetworkErrorLog(networkErrorDetail);
        }
    }

    private final void appendSSLHandshakeExceptionErrorMessage(Throwable throwable) {
        CertPathValidatorException certPathValidatorException;
        if (!(throwable instanceof SSLHandshakeException) || (certPathValidatorException = (CertPathValidatorException) ThrowableExtensionKt.getSpecifiedCause(throwable, CertPathValidatorException.class)) == null) {
            return;
        }
        appendErrorMessage(Intrinsics.stringPlus("certPath = ", certPathValidatorException.getCertPath())).appendErrorMessage(Intrinsics.stringPlus("index = ", Integer.valueOf(certPathValidatorException.getIndex())));
        if (Build.VERSION.SDK_INT >= 24) {
            appendErrorMessage(Intrinsics.stringPlus("reason = ", certPathValidatorException.getReason()));
        }
    }

    @JvmStatic
    public static final void logAndUploadNetworkError(LinkedHashMap<String, Object> linkedHashMap) {
        INSTANCE.logAndUploadNetworkError(linkedHashMap);
    }

    public abstract boolean allowUploadNetworkErrorLog();

    public final BaseNetworkErrorUploader appendErrorMessage(String appendedErrorMessage) {
        Intrinsics.checkNotNullParameter(appendedErrorMessage, "appendedErrorMessage");
        this.errorMessage.add(appendedErrorMessage);
        return this;
    }

    public final BaseNetworkErrorUploader appendErrorMessage(Throwable throwable) {
        if (throwable != null) {
            appendSSLHandshakeExceptionErrorMessage(throwable);
            List<String> listThrowableToList = LogManager.getInstance().throwableToList(throwable);
            Intrinsics.checkNotNullExpressionValue(listThrowableToList, "getInstance().throwableToList(throwable)");
            appendErrorMessage(listThrowableToList);
        }
        return this;
    }

    public final BaseNetworkErrorUploader appendErrorMessage(List<String> appendedErrorMessage) {
        Intrinsics.checkNotNullParameter(appendedErrorMessage, "appendedErrorMessage");
        this.errorMessage.addAll(appendedErrorMessage);
        return this;
    }

    public final BaseNetworkErrorUploader appendHttpDetail(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.basicHttpDetailMap.put(key, value);
        return this;
    }

    public abstract BaseNetworkErrorUploader fillHttpDetail();

    public final LinkedHashMap<String, Object> getBasicHttpDetailMap() {
        return this.basicHttpDetailMap;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<String> getErrorMessage() {
        return this.errorMessage;
    }

    public final String getNetworkErrorDetail() {
        String strJSONObjectToString = JsonUtil.JSONObjectToString(new JSONObject(this.basicHttpDetailMap));
        Intrinsics.checkNotNullExpressionValue(strJSONObjectToString, "JSONObjectToString(JSONO…pDetailMap as Map<*, *>))");
        return strJSONObjectToString;
    }

    public final String getProxyDescription() {
        return this.proxyDescription;
    }

    public final String getTheDescription(int code) {
        String errorMsgInfo = !TextUtils.isEmpty(this.description) ? this.description : XHErrorCodeUtil.getErrorMsgInfo(code);
        if (!TextUtils.isEmpty(this.proxyDescription)) {
            errorMsgInfo = errorMsgInfo + " " + this.proxyDescription;
        }
        Intrinsics.checkNotNullExpressionValue(errorMsgInfo, "result");
        return errorMsgInfo;
    }

    public final boolean getIsLastFail() {
        return this.isLastFail;
    }

    public final void setBasicHttpDetailMap(LinkedHashMap<String, Object> linkedHashMap) {
        Intrinsics.checkNotNullParameter(linkedHashMap, "<set-?>");
        this.basicHttpDetailMap = linkedHashMap;
    }

    public final void setCode(int i) {
        this.code = i;
    }

    public final void setDescription(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.description = str;
    }

    public final void setErrorMessage(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.errorMessage = list;
    }

    public final void setLastFail(boolean z) {
        this.isLastFail = z;
    }

    public final void setProxyDescription(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.proxyDescription = str;
    }

    public final void uploadNetworkError() {
        try {
            appendHttpDetail("isLastFail", String.valueOf(this.isLastFail)).fillHttpDetail().appendHttpDetail("ErrorCode", String.valueOf(this.code)).appendHttpDetail("Description", getTheDescription(this.code)).appendHttpDetail("ErrorMessage", this.errorMessage);
            if (allowUploadNetworkErrorLog()) {
                INSTANCE.logAndUploadNetworkError(this.basicHttpDetailMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadNetworkError(Throwable throwable) {
        appendErrorMessage(throwable);
        uploadNetworkError();
    }
}
