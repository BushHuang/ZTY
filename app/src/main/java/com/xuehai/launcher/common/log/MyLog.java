package com.xuehai.launcher.common.log;

import android.os.Build;
import com.xh.logutils.LogManager;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xuehai.launcher.common.constants.FilePath;
import com.xuehai.launcher.common.error.DeadObjectExceptionHandler;
import com.xuehai.launcher.common.error.DumpExceptionHandler;
import com.xuehai.launcher.common.error.ExceptionHandler;
import com.xuehai.launcher.common.error.LogException;
import com.xuehai.system.common.UnSupportException;
import com.zaze.utils.date.DateUtil;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007J\u001e\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012J\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007J$\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u0017J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u000eH\u0007J\u0016\u0010\u001a\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eJ\u0018\u0010\u001b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007J \u0010\u001b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001d"}, d2 = {"Lcom/xuehai/launcher/common/log/MyLog;", "", "()V", "exceptionHandler", "Lcom/xuehai/launcher/common/error/ExceptionHandler;", "listeners", "Ljava/util/HashSet;", "Lcom/xuehai/launcher/common/log/MyLog$LogListener;", "Lkotlin/collections/HashSet;", "getListeners", "()Ljava/util/HashSet;", "d", "", "tag", "", "msg", "e", "tr", "", "i", "printList", "tip", "list", "", "saveAppErrorLog", "errorMsg", "v", "w", "LogListener", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyLog {
    public static final MyLog INSTANCE = new MyLog();
    private static final ExceptionHandler exceptionHandler;
    private static final HashSet<LogListener> listeners;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J \u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&¨\u0006\r"}, d2 = {"Lcom/xuehai/launcher/common/log/MyLog$LogListener;", "", "d", "", "tag", "", "msg", "e", "tr", "", "i", "v", "w", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface LogListener {
        void d(String tag, String msg);

        void e(String tag, String msg);

        void e(String tag, String msg, Throwable tr);

        void i(String tag, String msg);

        void v(String tag, String msg);

        void w(String tag, String msg);

        void w(String tag, String msg, Throwable tr);
    }

    static {
        ExceptionHandler exceptionHandler2 = new ExceptionHandler(new DumpExceptionHandler(FilePath.getErrorLogDir() + "/mdm.error"));
        exceptionHandler2.addHandler(new DeadObjectExceptionHandler());
        exceptionHandler = exceptionHandler2;
        listeners = new HashSet<>();
    }

    private MyLog() {
    }

    @JvmStatic
    public static final void d(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Iterator<T> it = listeners.iterator();
        while (it.hasNext()) {
            ((LogListener) it.next()).d(tag, msg);
        }
        LogUtils.Companion.d$default(LogUtils.INSTANCE, tag, msg, null, 4, null);
    }

    @JvmStatic
    public static final void e(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Iterator<T> it = listeners.iterator();
        while (it.hasNext()) {
            ((LogListener) it.next()).e(tag, msg);
        }
        LogUtils.Companion.e$default(LogUtils.INSTANCE, tag, msg, null, 4, null);
    }

    @JvmStatic
    public static final void i(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Iterator<T> it = listeners.iterator();
        while (it.hasNext()) {
            ((LogListener) it.next()).i(tag, msg);
        }
        LogUtils.Companion.i$default(LogUtils.INSTANCE, tag, msg, null, 4, null);
    }

    @JvmStatic
    public static final void saveAppErrorLog(String errorMsg) {
        Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
        StringBuilder sb = new StringBuilder();
        sb.append(DateUtil.timeMillisToString$default(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss", null, 4, null));
        sb.append("\n");
        sb.append("当前系统版本: " + Build.DISPLAY + '\n');
        sb.append(errorMsg);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        w("Error[MDM]", string);
        LogManager.getInstance().uploadLog(sb.toString());
    }

    @JvmStatic
    public static final void w(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Iterator<T> it = listeners.iterator();
        while (it.hasNext()) {
            ((LogListener) it.next()).w(tag, msg);
        }
        LogUtils.Companion.w$default(LogUtils.INSTANCE, tag, msg, null, 4, null);
    }

    @JvmStatic
    public static final void w(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tr, "tr");
        Iterator<T> it = listeners.iterator();
        while (it.hasNext()) {
            ((LogListener) it.next()).w(tag, msg, tr);
        }
        LogUtils.INSTANCE.w(tag, msg, tr);
        exceptionHandler.handleException("mdm_error", new LogException(msg, tr));
    }

    public final void e(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tr, "tr");
        Iterator<T> it = listeners.iterator();
        while (it.hasNext()) {
            ((LogListener) it.next()).e(tag, msg, tr);
        }
        LogUtils.INSTANCE.e(tag, msg, tr);
        if (tr instanceof UnSupportException) {
            return;
        }
        exceptionHandler.handleException("mdm_error", new LogException(msg, tr));
    }

    public final HashSet<LogListener> getListeners() {
        return listeners;
    }

    public final void printList(String tag, String tip, Collection<? extends Object> list) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(tip, "tip");
        Intrinsics.checkNotNullParameter(list, "list");
        if (list.isEmpty()) {
            d(tag, tip + " : " + tip + " is empty");
        }
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(",");
            if (sb.length() > 500) {
                d(tag, tip + " : " + ((Object) sb));
                StringsKt.clear(sb);
            }
        }
        if (sb.length() > 0) {
            d(tag, tip + " : " + ((Object) sb));
        }
    }

    public final void v(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Iterator<T> it = listeners.iterator();
        while (it.hasNext()) {
            ((LogListener) it.next()).v(tag, msg);
        }
        LogUtils.Companion.v$default(LogUtils.INSTANCE, tag, msg, null, 4, null);
    }
}
