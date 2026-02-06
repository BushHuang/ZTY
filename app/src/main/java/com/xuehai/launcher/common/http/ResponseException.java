package com.xuehai.launcher.common.http;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/xuehai/launcher/common/http/ResponseException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "response", "Lcom/xuehai/launcher/common/http/LResponse;", "(Lcom/xuehai/launcher/common/http/LResponse;)V", "message", "", "getMessage", "()Ljava/lang/String;", "getResponse", "()Lcom/xuehai/launcher/common/http/LResponse;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ResponseException extends Exception {
    private final LResponse response;

    public ResponseException(LResponse lResponse) {
        Intrinsics.checkNotNullParameter(lResponse, "response");
        this.response = lResponse;
    }

    @Override
    public String getMessage() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.getDefault(), "%s(%d)", Arrays.copyOf(new Object[]{this.response.getResponseBody(), Integer.valueOf(this.response.getCode())}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    public final LResponse getResponse() {
        return this.response;
    }
}
