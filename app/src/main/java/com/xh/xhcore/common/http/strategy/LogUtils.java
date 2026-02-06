package com.xh.xhcore.common.http.strategy;

import android.text.TextUtils;
import com.xh.logutils.Log;
import com.xh.logutils.LogLevel;
import com.xh.xhcore.common.util.XHLog;
import com.xh.xhcore.strategy.ILogStrategy;
import com.xh.xhcore.strategy.NormalLogStrategy;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/LogUtils;", "", "()V", "Companion", "xhlog_release"}, k = 1, mv = {1, 1, 15})
public final class LogUtils {
    public static final long LOG_LENGTH_LIMIT = 4096;
    private static final int MAX_LOG_LENGTH = 2048;
    public static final String TAG = "LogUtils";

    public static final Companion INSTANCE = new Companion(null);
    public static boolean DEBUG = true;
    private static boolean isDebug = true;
    private static LogLevel logLevel = LogLevel.LOG_LEVEL_VERBOSE;
    private static List<String> fqcnIgnore = CollectionsKt.mutableListOf(LogUtils.class.getName(), XHLog.class.getName(), Log.class.getName());
    private static HashSet<String> tagIgnore = new HashSet<>();
    private static HashSet<String> tagBlackMap = new HashSet<>();
    private static final Lazy logStrategy$delegate = LazyKt.lazy(new Function0<NormalLogStrategy>() {
        @Override
        public final NormalLogStrategy invoke() {
            return new NormalLogStrategy();
        }
    });

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0010\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u000bH\u0007J\u0018\u0010$\u001a\u00020%2\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010(H\u0007J\u001a\u0010)\u001a\u0004\u0018\u00010\u000b2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u000bH\u0002J\u0012\u0010-\u001a\u00020%2\b\u0010.\u001a\u0004\u0018\u00010\u000bH\u0007J(\u0010-\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u000b2\b\u0010.\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010/\u001a\u0004\u0018\u000100H\u0007J\u001e\u0010-\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u000b2\b\u0010/\u001a\u0004\u0018\u000100H\u0007J\u0012\u0010-\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u000100H\u0007J\u0012\u00101\u001a\u00020%2\b\u0010.\u001a\u0004\u0018\u00010\u000bH\u0007J(\u00101\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u000b2\b\u0010.\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010/\u001a\u0004\u0018\u000100H\u0007J\u001e\u00101\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u000b2\b\u0010/\u001a\u0004\u0018\u000100H\u0007J\u0012\u00101\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u000100H\u0007J\u0012\u00102\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u000100H\u0007J\u0012\u00103\u001a\u00020%2\b\u0010.\u001a\u0004\u0018\u00010\u000bH\u0007J(\u00103\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u000b2\b\u0010.\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010/\u001a\u0004\u0018\u000100H\u0007J\u001e\u00103\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u000b2\b\u0010/\u001a\u0004\u0018\u000100H\u0007J\u0012\u00103\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u000100H\u0007J \u00104\u001a\u00020%2\u0006\u00105\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u000bH\u0002J,\u00106\u001a\u00020%2\u0006\u00105\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u000b2\b\u00107\u001a\u0004\u0018\u00010\u000b2\b\u0010/\u001a\u0004\u0018\u000100H\u0002J \u00108\u001a\u00020%2\u0006\u00105\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000bH\u0002J\u0010\u00109\u001a\u00020%2\u0006\u0010:\u001a\u00020\u000bH\u0007J\u0010\u0010;\u001a\u00020%2\u0006\u0010<\u001a\u00020\u000bH\u0007J\u0010\u0010=\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u000bH\u0002J\u0012\u0010>\u001a\u00020%2\b\u0010.\u001a\u0004\u0018\u00010\u000bH\u0007J(\u0010>\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u000b2\b\u0010.\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010/\u001a\u0004\u0018\u000100H\u0007J\u001e\u0010>\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u000b2\b\u0010/\u001a\u0004\u0018\u000100H\u0007J\u0012\u0010>\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u000100H\u0007J\u0012\u0010?\u001a\u00020%2\b\u0010.\u001a\u0004\u0018\u00010\u000bH\u0007J(\u0010?\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u000b2\b\u0010.\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010/\u001a\u0004\u0018\u000100H\u0007J\u001e\u0010?\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u000b2\b\u0010/\u001a\u0004\u0018\u000100H\u0007J\u0012\u0010?\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u000100H\u0007R\u0018\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b0\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0014@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u000b0!j\b\u0012\u0004\u0012\u00020\u000b`\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\u000b0!j\b\u0012\u0004\u0012\u00020\u000b`\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/LogUtils$Companion;", "", "()V", "DEBUG", "", "DEBUG$annotations", "LOG_LENGTH_LIMIT", "", "MAX_LOG_LENGTH", "", "TAG", "", "fqcnIgnore", "", "kotlin.jvm.PlatformType", "<set-?>", "isDebug", "()Z", "setDebug", "(Z)V", "Lcom/xh/logutils/LogLevel;", "logLevel", "getLogLevel", "()Lcom/xh/logutils/LogLevel;", "setLogLevel", "(Lcom/xh/logutils/LogLevel;)V", "logStrategy", "Lcom/xh/xhcore/strategy/ILogStrategy;", "getLogStrategy", "()Lcom/xh/xhcore/strategy/ILogStrategy;", "logStrategy$delegate", "Lkotlin/Lazy;", "tagBlackMap", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "tagIgnore", "addTagBlackList", "", "tag", "list", "", "createStackElementTag", "stackTraceElement", "Ljava/lang/StackTraceElement;", "defaultValue", "d", "msg", "tr", "", "e", "getStackTraceString", "i", "log", "level", "printAndFormatMsg", "message", "printMessage", "registerLogClass", "logClassName", "registerTagIgnoreMethod", "classAndMethod", "transformTagByStackTraceElement", "v", "w", "xhlog_release"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        static final KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "logStrategy", "getLogStrategy()Lcom/xh/xhcore/strategy/ILogStrategy;"))};

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
        public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[LogLevel.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[LogLevel.LOG_LEVEL_VERBOSE.ordinal()] = 1;
                $EnumSwitchMapping$0[LogLevel.LOG_LEVEL_DEBUG.ordinal()] = 2;
                $EnumSwitchMapping$0[LogLevel.LOG_LEVEL_INFO.ordinal()] = 3;
                $EnumSwitchMapping$0[LogLevel.LOG_LEVEL_ERROR.ordinal()] = 4;
                $EnumSwitchMapping$0[LogLevel.LOG_LEVEL_WARNING.ordinal()] = 5;
            }
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = "请切换至setLogLevel(LogLevel.LOG_LEVEL_NONE)来调用，原有setDEBUG功能已失效")
        public static void DEBUG$annotations() {
        }

        private final String createStackElementTag(StackTraceElement stackTraceElement, String defaultValue) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.CHINESE;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.CHINESE");
            String str = String.format(locale, "[%s](%s:%d)", Arrays.copyOf(new Object[]{stackTraceElement.getMethodName(), stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber())}, 3));
            Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(locale, format, *args)");
            return defaultValue + str;
        }

        public static void d$default(Companion companion, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 4) != 0) {
                th = (Throwable) null;
            }
            companion.d(str, str2, th);
        }

        public static void d$default(Companion companion, String str, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "";
            }
            companion.d(str, th);
        }

        public static void e$default(Companion companion, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 4) != 0) {
                th = (Throwable) null;
            }
            companion.e(str, str2, th);
        }

        public static void e$default(Companion companion, String str, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "";
            }
            companion.e(str, th);
        }

        private final ILogStrategy getLogStrategy() {
            Lazy lazy = LogUtils.logStrategy$delegate;
            Companion companion = LogUtils.INSTANCE;
            KProperty kProperty = $$delegatedProperties[0];
            return (ILogStrategy) lazy.getValue();
        }

        public static void i$default(Companion companion, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 4) != 0) {
                th = (Throwable) null;
            }
            companion.i(str, str2, th);
        }

        public static void i$default(Companion companion, String str, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "";
            }
            companion.i(str, th);
        }

        private final void log(LogLevel level, String tag, String msg) {
            int i = WhenMappings.$EnumSwitchMapping$0[level.ordinal()];
            if (i == 1) {
                getLogStrategy().logV(tag, msg);
                return;
            }
            if (i == 2) {
                getLogStrategy().logD(tag, msg);
                return;
            }
            if (i == 3) {
                getLogStrategy().logI(tag, msg);
                return;
            }
            if (i == 4) {
                getLogStrategy().logE(tag, msg);
            } else if (i != 5) {
                getLogStrategy().logV(tag, msg);
            } else {
                getLogStrategy().logW(tag, msg);
            }
        }

        private final void printAndFormatMsg(LogLevel level, String tag, String message, Throwable tr) {
            Companion companion = this;
            if (companion.getLogLevel().getValue() < level.getValue()) {
                return;
            }
            HashSet hashSet = LogUtils.tagBlackMap;
            boolean z = false;
            if (!(hashSet instanceof Collection) || !hashSet.isEmpty()) {
                Iterator it = hashSet.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (StringsKt.startsWith$default(tag, (String) it.next(), false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                }
            }
            if (!z || companion.isDebug()) {
                companion.printMessage(level, tag, Intrinsics.stringPlus(message, companion.getStackTraceString(tr)));
            }
        }

        private final void printMessage(LogLevel level, String tag, String message) {
            if (message.length() < 2048) {
                log(level, tag, message);
                return;
            }
            int i = 0;
            int length = message.length();
            while (i < length) {
                int iIndexOf$default = StringsKt.indexOf$default((CharSequence) message, '\n', i, false, 4, (Object) null);
                if (iIndexOf$default == -1) {
                    iIndexOf$default = length;
                }
                while (true) {
                    int iMin = Math.min(iIndexOf$default, i + 2048);
                    if (message == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring = message.substring(i, iMin);
                    Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    log(level, tag, strSubstring);
                    if (iMin >= iIndexOf$default) {
                        break;
                    } else {
                        i = iMin;
                    }
                }
            }
        }

        private final String transformTagByStackTraceElement(String defaultValue) {
            if (!isDebug()) {
                return defaultValue;
            }
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "Throwable().stackTrace");
            for (StackTraceElement stackTraceElement : stackTrace) {
                List list = LogUtils.fqcnIgnore;
                boolean z = true;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String str = (String) it.next();
                        Intrinsics.checkExpressionValueIsNotNull(stackTraceElement, "stackTrace");
                        String className = stackTraceElement.getClassName();
                        Intrinsics.checkExpressionValueIsNotNull(className, "stackTrace.className");
                        Intrinsics.checkExpressionValueIsNotNull(str, "it");
                        if (StringsKt.startsWith$default(className, str, false, 2, (Object) null)) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z) {
                    HashSet hashSet = LogUtils.tagIgnore;
                    StringBuilder sb = new StringBuilder();
                    Intrinsics.checkExpressionValueIsNotNull(stackTraceElement, "element");
                    sb.append(stackTraceElement.getClassName());
                    sb.append('.');
                    sb.append(stackTraceElement.getMethodName());
                    if (!hashSet.contains(sb.toString())) {
                        defaultValue = LogUtils.INSTANCE.createStackElementTag(stackTraceElement, defaultValue);
                    }
                    return defaultValue != null ? defaultValue : "";
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }

        public static void v$default(Companion companion, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 4) != 0) {
                th = (Throwable) null;
            }
            companion.v(str, str2, th);
        }

        public static void v$default(Companion companion, String str, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "";
            }
            companion.v(str, th);
        }

        public static void w$default(Companion companion, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 4) != 0) {
                th = (Throwable) null;
            }
            companion.w(str, str2, th);
        }

        public static void w$default(Companion companion, String str, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "";
            }
            companion.w(str, th);
        }

        @JvmStatic
        public final void addTagBlackList(String tag) {
            String str = tag;
            if (str == null || str.length() == 0) {
                return;
            }
            LogUtils.tagBlackMap.add(tag);
        }

        @JvmStatic
        public final void addTagBlackList(List<String> list) {
            List<String> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                return;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                LogUtils.INSTANCE.addTagBlackList((String) it.next());
            }
        }

        @JvmStatic
        public final void d(String msg) {
            d$default(this, "", msg, null, 4, null);
        }

        @JvmStatic
        public final void d(String str, String str2) {
            d$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        public final void d(String tag, String msg, Throwable tr) {
            Companion companion = this;
            companion.printAndFormatMsg(LogLevel.LOG_LEVEL_DEBUG, Intrinsics.stringPlus(tag, companion.transformTagByStackTraceElement(TextUtils.isEmpty(tag) ? "LogUtils" : "")), msg, tr);
        }

        @JvmStatic
        public final void d(String tag, Throwable tr) {
            d(tag, "", tr);
        }

        @JvmStatic
        public final void d(Throwable tr) {
            d("", tr);
        }

        @JvmStatic
        public final void e(String msg) {
            e$default(this, "", msg, null, 4, null);
        }

        @JvmStatic
        public final void e(String str, String str2) {
            e$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        public final void e(String tag, String msg, Throwable tr) {
            Companion companion = this;
            companion.printAndFormatMsg(LogLevel.LOG_LEVEL_ERROR, Intrinsics.stringPlus(tag, companion.transformTagByStackTraceElement(TextUtils.isEmpty(tag) ? "LogUtils" : "")), msg, tr);
        }

        @JvmStatic
        public final void e(String tag, Throwable tr) {
            e(tag, "", tr);
        }

        @JvmStatic
        public final void e(Throwable tr) {
            e("", tr);
        }

        public final LogLevel getLogLevel() {
            return LogUtils.logLevel;
        }

        @JvmStatic
        public final String getStackTraceString(Throwable tr) {
            if (tr == null) {
                return "";
            }
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            tr.printStackTrace(printWriter);
            printWriter.flush();
            String string = stringWriter.toString();
            Intrinsics.checkExpressionValueIsNotNull(string, "sw.toString()");
            return string;
        }

        @JvmStatic
        public final void i(String msg) {
            i$default(this, "", msg, null, 4, null);
        }

        @JvmStatic
        public final void i(String str, String str2) {
            i$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        public final void i(String tag, String msg, Throwable tr) {
            Companion companion = this;
            companion.printAndFormatMsg(LogLevel.LOG_LEVEL_INFO, Intrinsics.stringPlus(tag, companion.transformTagByStackTraceElement(TextUtils.isEmpty(tag) ? "LogUtils" : "")), msg, tr);
        }

        @JvmStatic
        public final void i(String tag, Throwable tr) {
            i(tag, "", tr);
        }

        @JvmStatic
        public final void i(Throwable tr) {
            i("", tr);
        }

        public final boolean isDebug() {
            return LogUtils.isDebug;
        }

        @JvmStatic
        public final void registerLogClass(String logClassName) {
            Intrinsics.checkParameterIsNotNull(logClassName, "logClassName");
            LogUtils.fqcnIgnore.add(logClassName);
        }

        @JvmStatic
        public final void registerTagIgnoreMethod(String classAndMethod) {
            Intrinsics.checkParameterIsNotNull(classAndMethod, "classAndMethod");
            if (TextUtils.isEmpty(classAndMethod)) {
                return;
            }
            LogUtils.tagIgnore.add(classAndMethod);
        }

        @JvmStatic
        public final void setDebug(boolean z) {
            LogUtils.isDebug = z;
        }

        @JvmStatic
        public final void setLogLevel(LogLevel logLevel) {
            Intrinsics.checkParameterIsNotNull(logLevel, "<set-?>");
            LogUtils.logLevel = logLevel;
        }

        @JvmStatic
        public final void v(String msg) {
            v$default(this, "", msg, null, 4, null);
        }

        @JvmStatic
        public final void v(String str, String str2) {
            v$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        public final void v(String tag, String msg, Throwable tr) {
            Companion companion = this;
            companion.printAndFormatMsg(LogLevel.LOG_LEVEL_VERBOSE, Intrinsics.stringPlus(tag, companion.transformTagByStackTraceElement(TextUtils.isEmpty(tag) ? "LogUtils" : "")), msg, tr);
        }

        @JvmStatic
        public final void v(String tag, Throwable tr) {
            v(tag, "", tr);
        }

        @JvmStatic
        public final void v(Throwable tr) {
            v("", tr);
        }

        @JvmStatic
        public final void w(String msg) {
            w$default(this, "", msg, null, 4, null);
        }

        @JvmStatic
        public final void w(String str, String str2) {
            w$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        public final void w(String tag, String msg, Throwable tr) {
            Companion companion = this;
            companion.printAndFormatMsg(LogLevel.LOG_LEVEL_WARNING, Intrinsics.stringPlus(tag, companion.transformTagByStackTraceElement(TextUtils.isEmpty(tag) ? "LogUtils" : "")), msg, tr);
        }

        @JvmStatic
        public final void w(String tag, Throwable tr) {
            w(tag, "", tr);
        }

        @JvmStatic
        public final void w(Throwable tr) {
            w("", tr);
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
    public static final void d(String str) {
        INSTANCE.d(str);
    }

    @JvmStatic
    public static final void d(String str, String str2) {
        Companion.d$default(INSTANCE, str, str2, null, 4, null);
    }

    @JvmStatic
    public static final void d(String str, String str2, Throwable th) {
        INSTANCE.d(str, str2, th);
    }

    @JvmStatic
    public static final void d(String str, Throwable th) {
        INSTANCE.d(str, th);
    }

    @JvmStatic
    public static final void d(Throwable th) {
        INSTANCE.d(th);
    }

    @JvmStatic
    public static final void e(String str) {
        INSTANCE.e(str);
    }

    @JvmStatic
    public static final void e(String str, String str2) {
        Companion.e$default(INSTANCE, str, str2, null, 4, null);
    }

    @JvmStatic
    public static final void e(String str, String str2, Throwable th) {
        INSTANCE.e(str, str2, th);
    }

    @JvmStatic
    public static final void e(String str, Throwable th) {
        INSTANCE.e(str, th);
    }

    @JvmStatic
    public static final void e(Throwable th) {
        INSTANCE.e(th);
    }

    @JvmStatic
    public static final String getStackTraceString(Throwable th) {
        return INSTANCE.getStackTraceString(th);
    }

    @JvmStatic
    public static final void i(String str) {
        INSTANCE.i(str);
    }

    @JvmStatic
    public static final void i(String str, String str2) {
        Companion.i$default(INSTANCE, str, str2, null, 4, null);
    }

    @JvmStatic
    public static final void i(String str, String str2, Throwable th) {
        INSTANCE.i(str, str2, th);
    }

    @JvmStatic
    public static final void i(String str, Throwable th) {
        INSTANCE.i(str, th);
    }

    @JvmStatic
    public static final void i(Throwable th) {
        INSTANCE.i(th);
    }

    @JvmStatic
    public static final void registerLogClass(String str) {
        INSTANCE.registerLogClass(str);
    }

    @JvmStatic
    public static final void registerTagIgnoreMethod(String str) {
        INSTANCE.registerTagIgnoreMethod(str);
    }

    @JvmStatic
    public static final void setDebug(boolean z) {
        isDebug = z;
    }

    @JvmStatic
    public static final void setLogLevel(LogLevel logLevel2) {
        logLevel = logLevel2;
    }

    @JvmStatic
    public static final void v(String str) {
        INSTANCE.v(str);
    }

    @JvmStatic
    public static final void v(String str, String str2) {
        Companion.v$default(INSTANCE, str, str2, null, 4, null);
    }

    @JvmStatic
    public static final void v(String str, String str2, Throwable th) {
        INSTANCE.v(str, str2, th);
    }

    @JvmStatic
    public static final void v(String str, Throwable th) {
        INSTANCE.v(str, th);
    }

    @JvmStatic
    public static final void v(Throwable th) {
        INSTANCE.v(th);
    }

    @JvmStatic
    public static final void w(String str) {
        INSTANCE.w(str);
    }

    @JvmStatic
    public static final void w(String str, String str2) {
        Companion.w$default(INSTANCE, str, str2, null, 4, null);
    }

    @JvmStatic
    public static final void w(String str, String str2, Throwable th) {
        INSTANCE.w(str, str2, th);
    }

    @JvmStatic
    public static final void w(String str, Throwable th) {
        INSTANCE.w(str, th);
    }

    @JvmStatic
    public static final void w(Throwable th) {
        INSTANCE.w(th);
    }
}
