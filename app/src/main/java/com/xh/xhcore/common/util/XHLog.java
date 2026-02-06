package com.xh.xhcore.common.util;

import com.xh.logutils.LogLevel;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Deprecated(message = "后期只会维护不会更新新功能，需要体验新功能请切换至LogUtils")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/xh/xhcore/common/util/XHLog;", "", "()V", "Companion", "xhlog_release"}, k = 1, mv = {1, 1, 15})
public final class XHLog {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\u0007J;\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0018\b\u0002\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\rJ;\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0018\b\u0002\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\rJ)\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0010\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\fH\u0002¢\u0006\u0002\u0010\u0011J;\u0010\u0012\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0018\b\u0002\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\rJ\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0006H\u0007J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J;\u0010\u001b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0018\b\u0002\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\rJ;\u0010\u001c\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0018\b\u0002\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/xh/xhcore/common/util/XHLog$Companion;", "", "()V", "addTagBlackList", "", "tag", "", "list", "", "d", "msg", "args", "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V", "e", "formatString", "format", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "i", "registerLogClass", "logClassName", "setIsDebug", "isDebug", "", "setLogLevel", "level", "Lcom/xh/logutils/LogLevel;", "v", "w", "xhlog_release"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static void d$default(Companion companion, String str, String str2, Object[] objArr, int i, Object obj) {
            if ((i & 4) != 0) {
                objArr = new Object[0];
            }
            companion.d(str, str2, objArr);
        }

        public static void e$default(Companion companion, String str, String str2, Object[] objArr, int i, Object obj) {
            if ((i & 4) != 0) {
                objArr = new Object[0];
            }
            companion.e(str, str2, objArr);
        }

        private final String formatString(String format, Object[] args) {
            try {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = format != null ? format : "";
                Object[] objArrCopyOf = Arrays.copyOf(args, args.length);
                String str2 = String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
                Intrinsics.checkExpressionValueIsNotNull(str2, "java.lang.String.format(format, *args)");
                return str2;
            } catch (Exception unused) {
                if (format == null) {
                    format = "";
                }
                return format;
            }
        }

        public static void i$default(Companion companion, String str, String str2, Object[] objArr, int i, Object obj) {
            if ((i & 4) != 0) {
                objArr = new Object[0];
            }
            companion.i(str, str2, objArr);
        }

        public static void v$default(Companion companion, String str, String str2, Object[] objArr, int i, Object obj) {
            if ((i & 4) != 0) {
                objArr = new Object[0];
            }
            companion.v(str, str2, objArr);
        }

        public static void w$default(Companion companion, String str, String str2, Object[] objArr, int i, Object obj) {
            if ((i & 4) != 0) {
                objArr = new Object[0];
            }
            companion.w(str, str2, objArr);
        }

        @JvmStatic
        public final void addTagBlackList(String tag) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            LogUtils.INSTANCE.addTagBlackList(tag);
        }

        @JvmStatic
        public final void addTagBlackList(List<String> list) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            LogUtils.INSTANCE.addTagBlackList(list);
        }

        @JvmStatic
        public final void d(String str, String str2) {
            d$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        public final void d(String tag, String msg, Object... args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            LogUtils.Companion.d$default(LogUtils.INSTANCE, tag, formatString(msg, args), null, 4, null);
        }

        @JvmStatic
        public final void e(String str, String str2) {
            e$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        public final void e(String tag, String msg, Object... args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            LogUtils.Companion.e$default(LogUtils.INSTANCE, tag, formatString(msg, args), null, 4, null);
        }

        @JvmStatic
        public final void i(String str, String str2) {
            i$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        public final void i(String tag, String msg, Object... args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            LogUtils.Companion.i$default(LogUtils.INSTANCE, tag, formatString(msg, args), null, 4, null);
        }

        @JvmStatic
        public final void registerLogClass(String logClassName) {
            Intrinsics.checkParameterIsNotNull(logClassName, "logClassName");
            LogUtils.INSTANCE.registerLogClass(logClassName);
        }

        @JvmStatic
        public final void setIsDebug(boolean isDebug) {
            LogUtils.INSTANCE.setDebug(isDebug);
        }

        @JvmStatic
        public final void setLogLevel(LogLevel level) {
            Intrinsics.checkParameterIsNotNull(level, "level");
            LogUtils.INSTANCE.setLogLevel(level);
        }

        @JvmStatic
        public final void v(String str, String str2) {
            v$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        public final void v(String tag, String msg, Object... args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            LogUtils.Companion.v$default(LogUtils.INSTANCE, tag, formatString(msg, args), null, 4, null);
        }

        @JvmStatic
        public final void w(String str, String str2) {
            w$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        public final void w(String tag, String msg, Object... args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            LogUtils.Companion.w$default(LogUtils.INSTANCE, tag, formatString(msg, args), null, 4, null);
        }
    }

    @JvmStatic
    public static final void addTagBlackList(String str) {
        INSTANCE.addTagBlackList(str);
    }

    @JvmStatic
    public static final void addTagBlackList(List<String> list) {
        INSTANCE.addTagBlackList(list);
    }

    @JvmStatic
    public static final void d(String str, String str2) {
        Companion.d$default(INSTANCE, str, str2, null, 4, null);
    }

    @JvmStatic
    public static final void d(String str, String str2, Object... objArr) {
        INSTANCE.d(str, str2, objArr);
    }

    @JvmStatic
    public static final void e(String str, String str2) {
        Companion.e$default(INSTANCE, str, str2, null, 4, null);
    }

    @JvmStatic
    public static final void e(String str, String str2, Object... objArr) {
        INSTANCE.e(str, str2, objArr);
    }

    @JvmStatic
    public static final void i(String str, String str2) {
        Companion.i$default(INSTANCE, str, str2, null, 4, null);
    }

    @JvmStatic
    public static final void i(String str, String str2, Object... objArr) {
        INSTANCE.i(str, str2, objArr);
    }

    @JvmStatic
    public static final void registerLogClass(String str) {
        INSTANCE.registerLogClass(str);
    }

    @JvmStatic
    public static final void setIsDebug(boolean z) {
        INSTANCE.setIsDebug(z);
    }

    @JvmStatic
    public static final void setLogLevel(LogLevel logLevel) {
        INSTANCE.setLogLevel(logLevel);
    }

    @JvmStatic
    public static final void v(String str, String str2) {
        Companion.v$default(INSTANCE, str, str2, null, 4, null);
    }

    @JvmStatic
    public static final void v(String str, String str2, Object... objArr) {
        INSTANCE.v(str, str2, objArr);
    }

    @JvmStatic
    public static final void w(String str, String str2) {
        Companion.w$default(INSTANCE, str, str2, null, 4, null);
    }

    @JvmStatic
    public static final void w(String str, String str2, Object... objArr) {
        INSTANCE.w(str, str2, objArr);
    }
}
