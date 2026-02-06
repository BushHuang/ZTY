package com.xuehai.launcher.common.log;

import android.os.Build;
import android.os.Process;
import com.xuehai.launcher.common.config.ClientConfig;
import com.zaze.utils.JsonUtil;
import com.zaze.utils.date.DateUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0003J\u0006\u0010\u001e\u001a\u00020\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0019\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\b¨\u0006 "}, d2 = {"Lcom/xuehai/launcher/common/log/LogEntity;", "", "tag", "", "message", "(Ljava/lang/String;Ljava/lang/String;)V", "appVersion", "getAppVersion", "()Ljava/lang/String;", "getMessage", "osVersion", "kotlin.jvm.PlatformType", "getOsVersion", "pid", "", "getPid", "()I", "getTag", "time", "getTime", "component1", "component2", "copy", "equals", "", "other", "hashCode", "print", "", "toJsonLog", "toLog", "toString", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LogEntity {
    private final String appVersion;
    private final String message;
    private final String osVersion;
    private final int pid;
    private final String tag;
    private final String time;

    public LogEntity(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(str2, "message");
        this.tag = str;
        this.message = str2;
        this.pid = Process.myPid();
        this.appVersion = ClientConfig.INSTANCE.getAppVersion();
        this.osVersion = Build.DISPLAY;
        this.time = DateUtil.timeMillisToString$default(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss", null, 4, null);
    }

    public static LogEntity copy$default(LogEntity logEntity, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = logEntity.tag;
        }
        if ((i & 2) != 0) {
            str2 = logEntity.message;
        }
        return logEntity.copy(str, str2);
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getMessage() {
        return this.message;
    }

    public final LogEntity copy(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        return new LogEntity(tag, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LogEntity)) {
            return false;
        }
        LogEntity logEntity = (LogEntity) other;
        return Intrinsics.areEqual(this.tag, logEntity.tag) && Intrinsics.areEqual(this.message, logEntity.message);
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getOsVersion() {
        return this.osVersion;
    }

    public final int getPid() {
        return this.pid;
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getTime() {
        return this.time;
    }

    public int hashCode() {
        return (this.tag.hashCode() * 31) + this.message.hashCode();
    }

    public final void print() {
        MyLog.INSTANCE.v("[MDM]", toLog());
    }

    public final String toJsonLog() {
        String strObjToJson = JsonUtil.objToJson(new LogEntity(this.tag, this.message));
        Intrinsics.checkNotNullExpressionValue(strObjToJson, "objToJson(LogEntity(tag, message))");
        return strObjToJson;
    }

    public final String toLog() {
        return this.tag + ' ' + this.time + ' ' + this.appVersion + '(' + this.pid + '/' + this.osVersion + "): " + this.message;
    }

    public String toString() {
        return "LogEntity(tag=" + this.tag + ", message=" + this.message + ')';
    }
}
