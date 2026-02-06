package com.xuehai.launcher.common.http.download;

import com.xuehai.launcher.common.http.DownloadCallback;
import com.xuehai.launcher.common.http.LRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\",\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0003\u0010\u0007\"\u0004\b\b\u0010\t\"\u0017\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\",\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010\"\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u000b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\r\",\u0010\u0011\u001a\u0004\u0018\u00010\u000b*\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0014"}, d2 = {"downloadCallback", "Lcom/xuehai/launcher/common/http/DownloadCallback;", "Lcom/xuehai/launcher/common/http/LRequest;", "getDownloadCallback", "(Lcom/xuehai/launcher/common/http/LRequest;)Lcom/xuehai/launcher/common/http/DownloadCallback;", "value", "Lcom/xuehai/launcher/common/http/LRequest$Builder;", "(Lcom/xuehai/launcher/common/http/LRequest$Builder;)Lcom/xuehai/launcher/common/http/DownloadCallback;", "setDownloadCallback", "(Lcom/xuehai/launcher/common/http/LRequest$Builder;Lcom/xuehai/launcher/common/http/DownloadCallback;)V", "md5", "", "getMd5", "(Lcom/xuehai/launcher/common/http/LRequest;)Ljava/lang/String;", "(Lcom/xuehai/launcher/common/http/LRequest$Builder;)Ljava/lang/String;", "setMd5", "(Lcom/xuehai/launcher/common/http/LRequest$Builder;Ljava/lang/String;)V", "savePath", "getSavePath", "setSavePath", "common_studentToBRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class DownloadExtKt {
    public static final DownloadCallback getDownloadCallback(LRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return (DownloadCallback) builder.getExtMap$common_studentToBRelease().get("downloadCallback");
    }

    public static final DownloadCallback getDownloadCallback(LRequest lRequest) {
        Intrinsics.checkNotNullParameter(lRequest, "<this>");
        return (DownloadCallback) lRequest.getExtMap().get("downloadCallback");
    }

    public static final String getMd5(LRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return (String) builder.getExtMap$common_studentToBRelease().get("md5");
    }

    public static final String getMd5(LRequest lRequest) {
        Intrinsics.checkNotNullParameter(lRequest, "<this>");
        return (String) lRequest.getExtMap().get("md5");
    }

    public static final String getSavePath(LRequest.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return (String) builder.getExtMap$common_studentToBRelease().get("savePath");
    }

    public static final String getSavePath(LRequest lRequest) {
        Intrinsics.checkNotNullParameter(lRequest, "<this>");
        return (String) lRequest.getExtMap().get("savePath");
    }

    public static final void setDownloadCallback(LRequest.Builder builder, DownloadCallback downloadCallback) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.getExtMap$common_studentToBRelease().put("downloadCallback", downloadCallback);
    }

    public static final void setMd5(LRequest.Builder builder, String str) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.getExtMap$common_studentToBRelease().put("md5", str);
    }

    public static final void setSavePath(LRequest.Builder builder, String str) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.getExtMap$common_studentToBRelease().put("savePath", str);
    }
}
