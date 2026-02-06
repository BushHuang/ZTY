package com.xh.xhcore.common.http.strategy.xh.upload.domain.exception;

import com.obs.services.exception.ObsException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0016\u0018\u0000 \u00152\u00060\u0001j\u0002`\u0002:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/exception/OsException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "errorCode", "", "getErrorCode", "()I", "setErrorCode", "(I)V", "errorMessage", "", "getErrorMessage", "()Ljava/lang/String;", "setErrorMessage", "(Ljava/lang/String;)V", "exception", "getException", "()Ljava/lang/Exception;", "setException", "(Ljava/lang/Exception;)V", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class OsException extends Exception {

    public static final Companion INSTANCE = new Companion(null);
    private static final long serialVersionUID = -31038623;
    private int errorCode = -1;
    private String errorMessage = "未知异常";
    private Exception exception;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/exception/OsException$Companion;", "", "()V", "serialVersionUID", "", "fromOsException", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/exception/OsException;", "e", "Lcom/obs/services/exception/ObsException;", "errorCode", "", "errorMessage", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final OsException fromOsException(int errorCode, String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            OsException osException = new OsException();
            osException.setException(new IllegalArgumentException(errorMessage));
            osException.setErrorCode(errorCode);
            osException.setErrorMessage(errorMessage);
            return osException;
        }

        @JvmStatic
        public final OsException fromOsException(ObsException e) {
            Intrinsics.checkNotNullParameter(e, "e");
            OsException osException = new OsException();
            osException.setException(e);
            osException.setErrorCode(107005006);
            String message = e.getMessage();
            if (message == null) {
                message = "OBS异常";
            }
            osException.setErrorMessage(message);
            return osException;
        }
    }

    @JvmStatic
    public static final OsException fromOsException(int i, String str) {
        return INSTANCE.fromOsException(i, str);
    }

    @JvmStatic
    public static final OsException fromOsException(ObsException obsException) {
        return INSTANCE.fromOsException(obsException);
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final Exception getException() {
        return this.exception;
    }

    public final void setErrorCode(int i) {
        this.errorCode = i;
    }

    public final void setErrorMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.errorMessage = str;
    }

    public final void setException(Exception exc) {
        this.exception = exc;
    }
}
