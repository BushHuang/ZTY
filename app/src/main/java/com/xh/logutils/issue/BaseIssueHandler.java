package com.xh.logutils.issue;

import android.text.TextUtils;
import com.xh.logutils.CrashOrErrorStatisticsInfo;
import com.xh.logutils.IIssueHandler;
import com.xh.logutils.LogLevel;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.ErrorLogPath;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u000f2\u00020\u00012\u00020\u0002:\u0001\u000fB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0004¨\u0006\u0010"}, d2 = {"Lcom/xh/logutils/issue/BaseIssueHandler;", "Lcom/xh/logutils/CrashOrErrorStatisticsInfo;", "Lcom/xh/logutils/IIssueHandler;", "errorLogPath", "Lcom/xh/xhcore/common/statistic/ErrorLogPath;", "jsonObject", "Lorg/json/JSONObject;", "(Lcom/xh/xhcore/common/statistic/ErrorLogPath;Lorg/json/JSONObject;)V", "getLogLevel", "Lcom/xh/logutils/LogLevel;", "handle", "", "logIssueInfo", "issueInfo", "", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseIssueHandler extends CrashOrErrorStatisticsInfo implements IIssueHandler {
    public static final String ISSUE_INFO = "issueInfo";

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LogLevel.values().length];
            iArr[LogLevel.LOG_LEVEL_VERBOSE.ordinal()] = 1;
            iArr[LogLevel.LOG_LEVEL_DEBUG.ordinal()] = 2;
            iArr[LogLevel.LOG_LEVEL_INFO.ordinal()] = 3;
            iArr[LogLevel.LOG_LEVEL_WARNING.ordinal()] = 4;
            iArr[LogLevel.LOG_LEVEL_ERROR.ordinal()] = 5;
            iArr[LogLevel.LOG_LEVEL_NONE.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        LogUtils.INSTANCE.registerTagIgnoreMethod(Intrinsics.stringPlus(BaseIssueHandler.class.getName(), ".logIssueInfo"));
    }

    public BaseIssueHandler(ErrorLogPath errorLogPath, JSONObject jSONObject) {
        super(errorLogPath, jSONObject);
        Intrinsics.checkNotNullParameter(errorLogPath, "errorLogPath");
        Intrinsics.checkNotNullParameter(jSONObject, "jsonObject");
    }

    protected LogLevel getLogLevel() {
        return LogLevel.LOG_LEVEL_DEBUG;
    }

    @Override
    public void handle() {
        String string = toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString()");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        saveToFile(string);
        logIssueInfo(string);
    }

    protected final void logIssueInfo(String issueInfo) {
        Intrinsics.checkNotNullParameter(issueInfo, "issueInfo");
        int i = WhenMappings.$EnumSwitchMapping$0[getLogLevel().ordinal()];
        if (i == 1) {
            LogUtils.Companion.v$default(LogUtils.INSTANCE, "issueInfo", issueInfo, null, 4, null);
            return;
        }
        if (i == 2) {
            LogUtils.Companion.d$default(LogUtils.INSTANCE, "issueInfo", issueInfo, null, 4, null);
            return;
        }
        if (i == 3) {
            LogUtils.Companion.i$default(LogUtils.INSTANCE, "issueInfo", issueInfo, null, 4, null);
        } else if (i == 4) {
            LogUtils.Companion.w$default(LogUtils.INSTANCE, "issueInfo", issueInfo, null, 4, null);
        } else {
            if (i != 5) {
                return;
            }
            LogUtils.Companion.e$default(LogUtils.INSTANCE, "issueInfo", issueInfo, null, 4, null);
        }
    }
}
