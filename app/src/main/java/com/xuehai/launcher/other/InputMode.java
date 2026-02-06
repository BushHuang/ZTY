package com.xuehai.launcher.other;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/xuehai/launcher/other/InputMode;", "", "()V", "input", "Ljava/lang/StringBuilder;", "getInput", "()Ljava/lang/StringBuilder;", "setInput", "(Ljava/lang/StringBuilder;)V", "a", "", "b", "clear", "isDefault", "", "isDev", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InputMode {
    private StringBuilder input = new StringBuilder();

    public final void a() {
        this.input.append("A");
    }

    public final void b() {
        this.input.append("B");
    }

    public final void clear() {
        StringsKt.clear(this.input);
    }

    public final StringBuilder getInput() {
        return this.input;
    }

    public final boolean isDefault() {
        return Intrinsics.areEqual("ABBAAB", this.input.toString());
    }

    public final boolean isDev() {
        return Intrinsics.areEqual("ABBBBBAAABBB", this.input.toString());
    }

    public final void setInput(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<set-?>");
        this.input = sb;
    }
}
