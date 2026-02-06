package com.xuehai.launcher.common.crack;

import android.os.Process;
import com.xuehai.launcher.common.log.MyLog;
import java.io.FileInputStream;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/xuehai/launcher/common/crack/XposedChecker;", "", "()V", "result", "Lcom/xuehai/launcher/common/crack/CheckResult;", "getResult", "()Lcom/xuehai/launcher/common/crack/CheckResult;", "detectByClassLoader", "", "detectByMaps", "detectByStackTrace", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XposedChecker {
    public static final String TAG = "XposedChecker";
    private final CheckResult result = new CheckResult();

    public final void detectByClassLoader() {
        try {
            ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedHelpers").newInstance();
        } catch (Throwable th) {
            MyLog.w("XposedChecker", "detectByClassLoader flag ", th);
            if (th instanceof ClassNotFoundException) {
                return;
            }
            this.result.addError("XposedChecker hit detectByClassLoader : " + th);
        }
    }

    public final void detectByMaps() {
        HashSet hashSet = new HashSet();
        String string = CheckerUtil.readByBytes$default(new FileInputStream("/proc/" + Process.myPid() + "/maps"), 0, 2, null).toString();
        Intrinsics.checkNotNullExpressionValue(string, "buffer.toString()");
        for (String str : StringsKt.split$default((CharSequence) string, new String[]{"\n"}, false, 0, 6, (Object) null)) {
            if (StringsKt.endsWith$default(str, ".so", false, 2, (Object) null) || StringsKt.endsWith$default(str, ".jar", false, 2, (Object) null)) {
                if (StringsKt.contains((CharSequence) str, (CharSequence) "xposed", true)) {
                    hashSet.add(str);
                    MyLog.d("XposedChecker", "line " + str);
                }
            }
        }
        if (!hashSet.isEmpty()) {
            MyLog.e("XposedChecker", "has Xposed lib " + hashSet.size());
            this.result.addError("XposedChecker hit detectByMaps: 加载了 xposed 的so 和jar");
        }
    }

    public final void detectByStackTrace() {
        if (Thread.currentThread().getId() == Process.myPid()) {
            throw new IllegalThreadStateException("必须在主线程调用");
        }
        Throwable th = new Throwable("detect Xposed");
        th.printStackTrace();
        StackTraceElement[] stackTrace = th.getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "stackTrace");
        int length = stackTrace.length;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < length; i2++) {
            String string = stackTrace[i2].toString();
            Intrinsics.checkNotNullExpressionValue(string, "stackTrace[it].toString()");
            String str = string;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "de.robv.android.xposed", false, 2, (Object) null)) {
                i++;
            }
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "com.android.internal.os.ZygoteInit", false, 2, (Object) null)) {
                if (stackTrace.length - 1 == i2) {
                    z = true;
                    z2 = true;
                } else {
                    z = true;
                }
            }
        }
        MyLog.i("XposedChecker", "hasXposed flag : " + i + "; hasZygote: " + z + "; bottomStackIsZygote: " + z2);
        CheckResult checkResult = this.result;
        StringBuilder sb = new StringBuilder();
        sb.append("XposedChecker detectByStackTrace: ");
        sb.append(ArraysKt.toList(stackTrace));
        checkResult.addMessageNoError(sb.toString());
        if (i > 0) {
            this.result.addError("XposedChecker hit detectByStackTrace: 包含了xposed的栈信息 " + i);
            return;
        }
        if (z2) {
            if (z) {
                return;
            }
            this.result.addError("XposedChecker hit detectByStackTrace: 没有Zygote栈信息");
        } else {
            this.result.addError("XposedChecker hit detectByStackTrace: 最底下栈没有包含zygote信息 " + z2);
        }
    }

    public final CheckResult getResult() {
        return this.result;
    }
}
