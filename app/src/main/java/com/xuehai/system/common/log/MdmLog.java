package com.xuehai.system.common.log;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0007J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0007J \u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0007J)\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u0016J\u0018\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0007J\u0018\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0007J \u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/xuehai/system/common/log/MdmLog;", "", "()V", "TAG", "", "logClient", "Lcom/xuehai/system/common/log/MdmLogClient;", "getLogClient", "()Lcom/xuehai/system/common/log/MdmLogClient;", "setLogClient", "(Lcom/xuehai/system/common/log/MdmLogClient;)V", "d", "", "tag", "msg", "e", "tr", "", "i", "log", "isSuccess", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "v", "w", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MdmLog {
    public static final MdmLog INSTANCE = new MdmLog();
    public static final String TAG = "MdmLog";
    private static MdmLogClient logClient;

    private MdmLog() {
    }

    @JvmStatic
    public static final void d(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        MdmLogClient mdmLogClient = logClient;
        if (mdmLogClient != null) {
            mdmLogClient.d(tag, msg);
        } else {
            Log.d(tag, msg);
        }
    }

    @JvmStatic
    public static final void e(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        MdmLogClient mdmLogClient = logClient;
        if (mdmLogClient != null) {
            mdmLogClient.e(tag, msg);
        } else {
            Log.e(tag, msg);
        }
    }

    @JvmStatic
    public static final void e(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tr, "tr");
        MdmLogClient mdmLogClient = logClient;
        if (mdmLogClient != null) {
            mdmLogClient.e(tag, msg, tr);
        } else {
            Log.e(tag, msg, tr);
        }
    }

    @JvmStatic
    public static final void i(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        MdmLogClient mdmLogClient = logClient;
        if (mdmLogClient != null) {
            mdmLogClient.i(tag, msg);
        } else {
            Log.i(tag, msg);
        }
    }

    @JvmStatic
    public static final void log(String tag, String msg, Boolean isSuccess) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (isSuccess == null) {
            v(tag, msg);
            return;
        }
        if (isSuccess.booleanValue()) {
            d(tag, msg + " >> true");
            return;
        }
        e(tag, msg + " >> false");
    }

    public static void log$default(String str, String str2, Boolean bool, int i, Object obj) {
        if ((i & 4) != 0) {
            bool = null;
        }
        log(str, str2, bool);
    }

    @JvmStatic
    public static final void v(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        MdmLogClient mdmLogClient = logClient;
        if (mdmLogClient != null) {
            mdmLogClient.v(tag, msg);
        } else {
            Log.v(tag, msg);
        }
    }

    @JvmStatic
    public static final void w(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        MdmLogClient mdmLogClient = logClient;
        if (mdmLogClient != null) {
            mdmLogClient.w(tag, msg);
        } else {
            Log.w(tag, msg);
        }
    }

    @JvmStatic
    public static final void w(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tr, "tr");
        MdmLogClient mdmLogClient = logClient;
        if (mdmLogClient != null) {
            mdmLogClient.w(tag, msg, tr);
        } else {
            Log.w(tag, msg, tr);
        }
    }

    public final MdmLogClient getLogClient() {
        return logClient;
    }

    public final void setLogClient(MdmLogClient mdmLogClient) {
        logClient = mdmLogClient;
    }
}
