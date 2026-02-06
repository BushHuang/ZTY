package com.xh.logutils.issue;

import com.xh.logutils.LogLevel;
import com.xh.xhcore.common.statistic.ErrorLogPath;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/xh/logutils/issue/APPHandler;", "Lcom/xh/logutils/issue/BaseIssueHandler;", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getLogLevel", "Lcom/xh/logutils/LogLevel;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class APPHandler extends BaseIssueHandler {
    public APPHandler(JSONObject jSONObject) {
        super(ErrorLogPath.appUpload, jSONObject);
        Intrinsics.checkNotNullParameter(jSONObject, "jsonObject");
    }

    @Override
    protected LogLevel getLogLevel() {
        return LogLevel.LOG_LEVEL_INFO;
    }
}
