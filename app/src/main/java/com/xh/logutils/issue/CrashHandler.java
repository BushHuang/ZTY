package com.xh.logutils.issue;

import android.text.TextUtils;
import com.xh.logutils.LogLevel;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.statistic.ConstStatistics;
import com.xh.xhcore.common.statistic.ErrorLogPath;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0002¨\u0006\u000b"}, d2 = {"Lcom/xh/logutils/issue/CrashHandler;", "Lcom/xh/logutils/issue/BaseIssueHandler;", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getLogLevel", "Lcom/xh/logutils/LogLevel;", "handle", "", "isTypeJava", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CrashHandler extends BaseIssueHandler {
    public CrashHandler(JSONObject jSONObject) {
        super(ErrorLogPath.crash, jSONObject);
        Intrinsics.checkNotNullParameter(jSONObject, "jsonObject");
    }

    private final boolean isTypeJava() {
        String strOptString = this.issueInfoUser.optString("Crash type");
        return !TextUtils.isEmpty(strOptString) && Intrinsics.areEqual(strOptString, ConstStatistics.CrashType.java.getType());
    }

    @Override
    protected LogLevel getLogLevel() {
        return LogLevel.LOG_LEVEL_ERROR;
    }

    @Override
    public void handle() {
        String string = toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString()");
        saveToFile(string);
        if (isTypeJava()) {
            logIssueInfo(string);
        } else if (XHAppConfigProxy.getInstance().isAppBuildTypeDebug()) {
            logIssueInfo(string);
        }
    }
}
