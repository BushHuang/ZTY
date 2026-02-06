package com.xuehai.launcher.common.crack;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\rJ\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/xuehai/launcher/common/crack/CheckResult;", "", "()V", "flags", "", "messageBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getMessageBuilder", "()Ljava/lang/StringBuilder;", "setMessageBuilder", "(Ljava/lang/StringBuilder;)V", "addError", "", "message", "", "addMessageNoError", "clear", "isError", "", "isSafely", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CheckResult {
    private int flags;
    private StringBuilder messageBuilder = new StringBuilder();

    public final void addError(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.flags++;
        this.messageBuilder.append(message + " \n");
    }

    public final void addMessageNoError(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.messageBuilder.append(message + " \n");
    }

    public final void clear() {
        this.flags = 0;
        StringsKt.clear(this.messageBuilder);
    }

    public final StringBuilder getMessageBuilder() {
        return this.messageBuilder;
    }

    public final boolean isError() {
        return this.flags > 0;
    }

    public final boolean isSafely() {
        return !isError();
    }

    public final void setMessageBuilder(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<set-?>");
        this.messageBuilder = sb;
    }
}
