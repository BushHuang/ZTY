package com.xh.xhcore.common.statistic;

import android.text.TextUtils;
import com.xh.xhcore.common.config.WholeLinkEnvironment;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.config.XmConfig;
import com.xh.xhcore.common.http.strategy.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b;\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001dB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010a\u001a\u00020_2\u0006\u0010b\u001a\u00020\u0004H\u0007J\u0010\u0010c\u001a\u00020_2\u0006\u0010b\u001a\u00020\u0004H\u0007R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u00020\u00048\u0002X\u0083D¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u001c\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\u0007R\u001c\u0010\r\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0002\u001a\u0004\b\u000f\u0010\u0007R\u0016\u0010\u0010\u001a\u00020\u00048\u0002X\u0083D¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0002R\u001c\u0010\u0012\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\u0007R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0007R\u0014\u0010\u0017\u001a\u00020\u0018X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0002\u001a\u0004\b\u001e\u0010\u0007R\u001c\u0010\u001f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0002\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020#¢\u0006\b\n\u0000\u001a\u0004\b'\u0010%R\u0011\u0010(\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0014\u0010*\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0007R\u0014\u0010,\u001a\u00020\u0018X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001aR\u001c\u0010.\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b/\u0010\u0002\u001a\u0004\b0\u0010\u0007R\u0016\u00101\u001a\u00020\u00048\u0002X\u0083D¢\u0006\b\n\u0000\u0012\u0004\b2\u0010\u0002R\u001c\u00103\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u0010\u0002\u001a\u0004\b5\u0010\u0007R\u000e\u00106\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u00107\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0007R\u0014\u00109\u001a\u00020\u0018X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u001aR\u0014\u0010;\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0007R\u0014\u0010=\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0007R\u0014\u0010?\u001a\u00020\u0018X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u001aR\u0014\u0010A\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0007R\u0014\u0010C\u001a\u00020\u0018X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u001aR\u000e\u0010E\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010F\u001a\u00020#X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bG\u0010%R\u001c\u0010H\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bI\u0010\u0002\u001a\u0004\bJ\u0010\u0007R\u0016\u0010K\u001a\u00020\u00048\u0002X\u0083D¢\u0006\b\n\u0000\u0012\u0004\bL\u0010\u0002R\u001c\u0010M\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bN\u0010\u0002\u001a\u0004\bO\u0010\u0007R\u0014\u0010P\u001a\u00020#X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010%R\u001c\u0010R\u001a\u00020\u00048FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u0007\"\u0004\bT\u0010UR\u0011\u0010V\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bW\u0010\u0007R\u0014\u0010X\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bY\u0010\u0007R\u0014\u0010Z\u001a\u00020\u0018X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\u001aR\u0014\u0010\\\u001a\u00020\u0018X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b]\u0010\u001aR\u0011\u0010^\u001a\u00020_¢\u0006\b\n\u0000\u001a\u0004\b^\u0010`¨\u0006e"}, d2 = {"Lcom/xh/xhcore/common/statistic/ConstStatistics;", "", "()V", "APM_LOG_PATH", "", "getAPM_LOG_PATH$annotations", "getAPM_LOG_PATH", "()Ljava/lang/String;", "APM_LOG_PATH_MAIN", "getAPM_LOG_PATH_MAIN$annotations", "APM_LOG_PATH_PRODUCTION", "getAPM_LOG_PATH_PRODUCTION$annotations", "getAPM_LOG_PATH_PRODUCTION", "APP_ERROR_LOG_PATH", "getAPP_ERROR_LOG_PATH$annotations", "getAPP_ERROR_LOG_PATH", "APP_ERROR_LOG_PATH_MAIN", "getAPP_ERROR_LOG_PATH_MAIN$annotations", "APP_ERROR_LOG_PATH_PRODUCTION", "getAPP_ERROR_LOG_PATH_PRODUCTION$annotations", "getAPP_ERROR_LOG_PATH_PRODUCTION", "APP_USAGE_NAME", "getAPP_USAGE_NAME", "APP_USAGE_TYPE", "", "getAPP_USAGE_TYPE", "()I", "BACKTRACE", "BURY_LOG_PATH", "getBURY_LOG_PATH$annotations", "getBURY_LOG_PATH", "BURY_LOG_PATH_PRODUCTION", "getBURY_LOG_PATH_PRODUCTION$annotations", "getBURY_LOG_PATH_PRODUCTION", "BURY_SAMPLING_PERIOD_CLIENT", "", "getBURY_SAMPLING_PERIOD_CLIENT", "()J", "BURY_SAMPLING_PERIOD_UPLOAD", "getBURY_SAMPLING_PERIOD_UPLOAD", "BURY_UPLOAD_CLIENT_TIMES", "getBURY_UPLOAD_CLIENT_TIMES", "CONNECTION_TIME_CONSUMING_NAME", "getCONNECTION_TIME_CONSUMING_NAME", "CONNECTION_TIME_CONSUMING_TYPE", "getCONNECTION_TIME_CONSUMING_TYPE", "CRASH_LOG_PATH", "getCRASH_LOG_PATH$annotations", "getCRASH_LOG_PATH", "CRASH_LOG_PATH_MAIN", "getCRASH_LOG_PATH_MAIN$annotations", "CRASH_LOG_PATH_PRODUCTION", "getCRASH_LOG_PATH_PRODUCTION$annotations", "getCRASH_LOG_PATH_PRODUCTION", "CRASH_TYPE", "DOWNLOAD_SPEED_NAME", "getDOWNLOAD_SPEED_NAME", "DOWNLOAD_SPEED_TYPE", "getDOWNLOAD_SPEED_TYPE", "DOWNLOAD_TIMES_NAME", "getDOWNLOAD_TIMES_NAME", "DOWNLOAD_TRAFFIC_NAME", "getDOWNLOAD_TRAFFIC_NAME", "DOWNLOAD_TRAFFIC_TYPE", "getDOWNLOAD_TRAFFIC_TYPE", "HTTPDNS_LOOKUP_NAME", "getHTTPDNS_LOOKUP_NAME", "HTTPDNS_LOOKUP_TYPE", "getHTTPDNS_LOOKUP_TYPE", "LOG_TYPE", "MAX_BURY_FILE_SIZE", "getMAX_BURY_FILE_SIZE", "NETWORK_ERROR_LOG_PATH", "getNETWORK_ERROR_LOG_PATH$annotations", "getNETWORK_ERROR_LOG_PATH", "NETWORK_ERROR_LOG_PATH_MAIN", "getNETWORK_ERROR_LOG_PATH_MAIN$annotations", "NETWORK_ERROR_LOG_PATH_PRODUCTION", "getNETWORK_ERROR_LOG_PATH_PRODUCTION$annotations", "getNETWORK_ERROR_LOG_PATH_PRODUCTION", "ONE_DAY", "getONE_DAY", "STATISTICS_DIR", "getSTATISTICS_DIR", "setSTATISTICS_DIR", "(Ljava/lang/String;)V", "STATISTICS_DIR_PRODUCTION", "getSTATISTICS_DIR_PRODUCTION", "UPLOAD_SPEED_NAME", "getUPLOAD_SPEED_NAME", "UPLOAD_SPEED_TYPE", "getUPLOAD_SPEED_TYPE", "WHOLE_LINK_BASE_TYPE", "getWHOLE_LINK_BASE_TYPE", "isDebugBury", "", "()Z", "isFile", "logPath", "isRealTime", "CrashType", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ConstStatistics {
    private static final String APM_LOG_PATH;
    private static final String APM_LOG_PATH_MAIN;
    private static final String APM_LOG_PATH_PRODUCTION;
    private static final String APP_ERROR_LOG_PATH;
    private static final String APP_ERROR_LOG_PATH_MAIN;
    private static final String APP_ERROR_LOG_PATH_PRODUCTION;
    private static final String APP_USAGE_NAME;
    private static final int APP_USAGE_TYPE;
    public static final String BACKTRACE = "backtrace";
    private static final String BURY_LOG_PATH;
    private static final String BURY_LOG_PATH_PRODUCTION;
    private static final long BURY_SAMPLING_PERIOD_CLIENT;
    private static final long BURY_SAMPLING_PERIOD_UPLOAD;
    private static final int BURY_UPLOAD_CLIENT_TIMES;
    private static final String CONNECTION_TIME_CONSUMING_NAME;
    private static final int CONNECTION_TIME_CONSUMING_TYPE;
    private static final String CRASH_LOG_PATH;
    private static final String CRASH_LOG_PATH_MAIN;
    private static final String CRASH_LOG_PATH_PRODUCTION;
    public static final String CRASH_TYPE = "Crash type";
    private static final String DOWNLOAD_SPEED_NAME;
    private static final int DOWNLOAD_SPEED_TYPE;
    private static final String DOWNLOAD_TIMES_NAME;
    private static final String DOWNLOAD_TRAFFIC_NAME;
    private static final int DOWNLOAD_TRAFFIC_TYPE;
    private static final String HTTPDNS_LOOKUP_NAME;
    private static final int HTTPDNS_LOOKUP_TYPE;
    public static final String LOG_TYPE = "logType";
    private static final String NETWORK_ERROR_LOG_PATH;
    private static final String NETWORK_ERROR_LOG_PATH_MAIN;
    private static final String NETWORK_ERROR_LOG_PATH_PRODUCTION;
    private static final long ONE_DAY;
    private static String STATISTICS_DIR;
    private static final String STATISTICS_DIR_PRODUCTION;
    private static final String UPLOAD_SPEED_NAME;
    private static final int UPLOAD_SPEED_TYPE;
    private static final int WHOLE_LINK_BASE_TYPE;
    private static final boolean isDebugBury;
    public static final ConstStatistics INSTANCE = new ConstStatistics();
    private static final long MAX_BURY_FILE_SIZE = 5242880;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/common/statistic/ConstStatistics$CrashType;", "", "type", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "java", "native", "anr", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum CrashType {
        java("java"),
        f0native("native"),
        anr("anr");

        private final String type;

        CrashType(String str) {
            this.type = str;
        }

        public final String getType() {
            return this.type;
        }
    }

    static {
        boolean z = XHAppConfigProxy.getInstance().getWholeLinkEnvironment() == WholeLinkEnvironment.DEBUG;
        isDebugBury = z;
        BURY_SAMPLING_PERIOD_CLIENT = !z ? 60000L : 3000L;
        long j = !isDebugBury ? 300000L : 9000L;
        BURY_SAMPLING_PERIOD_UPLOAD = j;
        BURY_UPLOAD_CLIENT_TIMES = (int) (j / BURY_SAMPLING_PERIOD_CLIENT);
        ONE_DAY = 86400000L;
        WHOLE_LINK_BASE_TYPE = 65536;
        CONNECTION_TIME_CONSUMING_TYPE = 65536 + 2;
        CONNECTION_TIME_CONSUMING_NAME = "connection_time_consuming";
        DOWNLOAD_SPEED_TYPE = 65536 + 3;
        DOWNLOAD_SPEED_NAME = "download_speed";
        UPLOAD_SPEED_TYPE = 65536 + 7;
        UPLOAD_SPEED_NAME = "upload_speed";
        DOWNLOAD_TRAFFIC_TYPE = 65536 + 8;
        DOWNLOAD_TRAFFIC_NAME = "download_traffic";
        DOWNLOAD_TIMES_NAME = "10238";
        APP_USAGE_TYPE = 11;
        APP_USAGE_NAME = "app_usage";
        HTTPDNS_LOOKUP_TYPE = 2037;
        HTTPDNS_LOOKUP_NAME = "10122";
        String strStringPlus = Intrinsics.stringPlus(XHEnvironment.getExternalStorageDirectory().getAbsolutePath(), "/xuehai/log/statistics/");
        STATISTICS_DIR_PRODUCTION = strStringPlus;
        ConstStatistics constStatistics = INSTANCE;
        STATISTICS_DIR = strStringPlus;
        BURY_LOG_PATH = Intrinsics.stringPlus(constStatistics.getSTATISTICS_DIR(), "timing/file");
        BURY_LOG_PATH_PRODUCTION = Intrinsics.stringPlus(STATISTICS_DIR_PRODUCTION, "timing/file");
        APP_ERROR_LOG_PATH_MAIN = "timing/string/appUpload";
        APP_ERROR_LOG_PATH = Intrinsics.stringPlus(INSTANCE.getSTATISTICS_DIR(), APP_ERROR_LOG_PATH_MAIN);
        APP_ERROR_LOG_PATH_PRODUCTION = Intrinsics.stringPlus(STATISTICS_DIR_PRODUCTION, APP_ERROR_LOG_PATH_MAIN);
        APM_LOG_PATH_MAIN = "timing/string/apm";
        APM_LOG_PATH = Intrinsics.stringPlus(INSTANCE.getSTATISTICS_DIR(), APM_LOG_PATH_MAIN);
        APM_LOG_PATH_PRODUCTION = Intrinsics.stringPlus(STATISTICS_DIR_PRODUCTION, APM_LOG_PATH_MAIN);
        NETWORK_ERROR_LOG_PATH_MAIN = "realtime/string/networkError";
        NETWORK_ERROR_LOG_PATH = Intrinsics.stringPlus(INSTANCE.getSTATISTICS_DIR(), NETWORK_ERROR_LOG_PATH_MAIN);
        NETWORK_ERROR_LOG_PATH_PRODUCTION = Intrinsics.stringPlus(STATISTICS_DIR_PRODUCTION, NETWORK_ERROR_LOG_PATH_MAIN);
        CRASH_LOG_PATH_MAIN = "realtime/string/crash";
        CRASH_LOG_PATH = Intrinsics.stringPlus(INSTANCE.getSTATISTICS_DIR(), CRASH_LOG_PATH_MAIN);
        CRASH_LOG_PATH_PRODUCTION = Intrinsics.stringPlus(STATISTICS_DIR_PRODUCTION, CRASH_LOG_PATH_MAIN);
    }

    private ConstStatistics() {
    }

    public static final String getAPM_LOG_PATH() {
        return APM_LOG_PATH;
    }

    @JvmStatic
    public static void getAPM_LOG_PATH$annotations() {
    }

    @JvmStatic
    private static void getAPM_LOG_PATH_MAIN$annotations() {
    }

    public static final String getAPM_LOG_PATH_PRODUCTION() {
        return APM_LOG_PATH_PRODUCTION;
    }

    @JvmStatic
    public static void getAPM_LOG_PATH_PRODUCTION$annotations() {
    }

    public static final String getAPP_ERROR_LOG_PATH() {
        return APP_ERROR_LOG_PATH;
    }

    @JvmStatic
    public static void getAPP_ERROR_LOG_PATH$annotations() {
    }

    @JvmStatic
    private static void getAPP_ERROR_LOG_PATH_MAIN$annotations() {
    }

    public static final String getAPP_ERROR_LOG_PATH_PRODUCTION() {
        return APP_ERROR_LOG_PATH_PRODUCTION;
    }

    @JvmStatic
    public static void getAPP_ERROR_LOG_PATH_PRODUCTION$annotations() {
    }

    public static final String getBURY_LOG_PATH() {
        return BURY_LOG_PATH;
    }

    @JvmStatic
    public static void getBURY_LOG_PATH$annotations() {
    }

    public static final String getBURY_LOG_PATH_PRODUCTION() {
        return BURY_LOG_PATH_PRODUCTION;
    }

    @JvmStatic
    public static void getBURY_LOG_PATH_PRODUCTION$annotations() {
    }

    public static final String getCRASH_LOG_PATH() {
        return CRASH_LOG_PATH;
    }

    @JvmStatic
    public static void getCRASH_LOG_PATH$annotations() {
    }

    @JvmStatic
    private static void getCRASH_LOG_PATH_MAIN$annotations() {
    }

    public static final String getCRASH_LOG_PATH_PRODUCTION() {
        return CRASH_LOG_PATH_PRODUCTION;
    }

    @JvmStatic
    public static void getCRASH_LOG_PATH_PRODUCTION$annotations() {
    }

    public static final String getNETWORK_ERROR_LOG_PATH() {
        return NETWORK_ERROR_LOG_PATH;
    }

    @JvmStatic
    public static void getNETWORK_ERROR_LOG_PATH$annotations() {
    }

    @JvmStatic
    private static void getNETWORK_ERROR_LOG_PATH_MAIN$annotations() {
    }

    public static final String getNETWORK_ERROR_LOG_PATH_PRODUCTION() {
        return NETWORK_ERROR_LOG_PATH_PRODUCTION;
    }

    @JvmStatic
    public static void getNETWORK_ERROR_LOG_PATH_PRODUCTION$annotations() {
    }

    @JvmStatic
    public static final boolean isFile(String logPath) {
        Intrinsics.checkNotNullParameter(logPath, "logPath");
        String str = logPath;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return StringsKt.contains$default((CharSequence) str, (CharSequence) "file", false, 2, (Object) null);
    }

    @JvmStatic
    public static final boolean isRealTime(String logPath) {
        Intrinsics.checkNotNullParameter(logPath, "logPath");
        String str = logPath;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return StringsKt.contains$default((CharSequence) str, (CharSequence) "realtime", false, 2, (Object) null);
    }

    public final String getAPP_USAGE_NAME() {
        return APP_USAGE_NAME;
    }

    public final int getAPP_USAGE_TYPE() {
        return APP_USAGE_TYPE;
    }

    public final long getBURY_SAMPLING_PERIOD_CLIENT() {
        return BURY_SAMPLING_PERIOD_CLIENT;
    }

    public final long getBURY_SAMPLING_PERIOD_UPLOAD() {
        return BURY_SAMPLING_PERIOD_UPLOAD;
    }

    public final int getBURY_UPLOAD_CLIENT_TIMES() {
        return BURY_UPLOAD_CLIENT_TIMES;
    }

    public final String getCONNECTION_TIME_CONSUMING_NAME() {
        return CONNECTION_TIME_CONSUMING_NAME;
    }

    public final int getCONNECTION_TIME_CONSUMING_TYPE() {
        return CONNECTION_TIME_CONSUMING_TYPE;
    }

    public final String getDOWNLOAD_SPEED_NAME() {
        return DOWNLOAD_SPEED_NAME;
    }

    public final int getDOWNLOAD_SPEED_TYPE() {
        return DOWNLOAD_SPEED_TYPE;
    }

    public final String getDOWNLOAD_TIMES_NAME() {
        return DOWNLOAD_TIMES_NAME;
    }

    public final String getDOWNLOAD_TRAFFIC_NAME() {
        return DOWNLOAD_TRAFFIC_NAME;
    }

    public final int getDOWNLOAD_TRAFFIC_TYPE() {
        return DOWNLOAD_TRAFFIC_TYPE;
    }

    public final String getHTTPDNS_LOOKUP_NAME() {
        return HTTPDNS_LOOKUP_NAME;
    }

    public final int getHTTPDNS_LOOKUP_TYPE() {
        return HTTPDNS_LOOKUP_TYPE;
    }

    public final long getMAX_BURY_FILE_SIZE() {
        return MAX_BURY_FILE_SIZE;
    }

    public final long getONE_DAY() {
        return ONE_DAY;
    }

    public final String getSTATISTICS_DIR() {
        if ("1/".equals(XmConfig.getEnvironmentString())) {
            STATISTICS_DIR = STATISTICS_DIR_PRODUCTION;
        } else {
            STATISTICS_DIR = Intrinsics.stringPlus(STATISTICS_DIR_PRODUCTION, XmConfig.getEnvironmentString());
        }
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("STATISTICS_DIR = ", STATISTICS_DIR));
        return STATISTICS_DIR;
    }

    public final String getSTATISTICS_DIR_PRODUCTION() {
        return STATISTICS_DIR_PRODUCTION;
    }

    public final String getUPLOAD_SPEED_NAME() {
        return UPLOAD_SPEED_NAME;
    }

    public final int getUPLOAD_SPEED_TYPE() {
        return UPLOAD_SPEED_TYPE;
    }

    public final int getWHOLE_LINK_BASE_TYPE() {
        return WHOLE_LINK_BASE_TYPE;
    }

    public final boolean isDebugBury() {
        return isDebugBury;
    }

    public final void setSTATISTICS_DIR(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        STATISTICS_DIR = str;
    }
}
