package com.zaze.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/zaze/utils/StackTraceHelper;", "", "()V", "STACK_CALLERS", "", "", "callerStackTraceElement", "Ljava/lang/StackTraceElement;", "currentStackTraceElement", "getErrorMsg", "e", "", "getStackTraceElement", "position", "", "registerStackCaller", "", "logCaller", "util_release"}, k = 1, mv = {1, 4, 1})
public final class StackTraceHelper {
    public static final StackTraceHelper INSTANCE = new StackTraceHelper();
    private static final Set<String> STACK_CALLERS = new HashSet();

    static {
        String name = StackTraceHelper.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "StackTraceHelper::class.java.name");
        registerStackCaller(name);
    }

    private StackTraceHelper() {
    }

    @JvmStatic
    public static final StackTraceElement callerStackTraceElement() {
        StackTraceElement stackTraceElement;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        int length = stackTrace.length;
        int i = 0;
        while (true) {
            stackTraceElement = null;
            if (i >= length) {
                break;
            }
            StackTraceElement stackTraceElement2 = stackTrace[i];
            Set<String> set = STACK_CALLERS;
            boolean z = true;
            if (!(set instanceof Collection) || !set.isEmpty()) {
                Iterator<T> it = set.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String str = (String) it.next();
                    Intrinsics.checkNotNullExpressionValue(stackTraceElement2, "stackTraceElement");
                    String className = stackTraceElement2.getClassName();
                    Intrinsics.checkNotNullExpressionValue(className, "stackTraceElement.className");
                    if (StringsKt.startsWith$default(className, str, false, 2, (Object) null)) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                stackTraceElement = stackTraceElement2;
                break;
            }
            i++;
        }
        return stackTraceElement != null ? stackTraceElement : INSTANCE.currentStackTraceElement();
    }

    @JvmStatic
    public static final void registerStackCaller(String logCaller) {
        Intrinsics.checkNotNullParameter(logCaller, "logCaller");
        STACK_CALLERS.add(logCaller);
    }

    public final StackTraceElement currentStackTraceElement() {
        return getStackTraceElement(1);
    }

    public final String getErrorMsg(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter((Writer) stringWriter, false);
        try {
            e.printStackTrace(printWriter);
            printWriter.flush();
            String string = stringWriter.toString();
            printWriter.close();
            Intrinsics.checkNotNullExpressionValue(string, "try {\n                e.…ter.close()\n            }");
            return string;
        } catch (Throwable th) {
            printWriter.close();
            throw th;
        }
    }

    public final StackTraceElement getStackTraceElement(int position) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[position];
        Intrinsics.checkNotNullExpressionValue(stackTraceElement, "Throwable().stackTrace[position]");
        return stackTraceElement;
    }
}
