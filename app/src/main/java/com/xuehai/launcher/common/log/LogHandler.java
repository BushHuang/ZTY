package com.xuehai.launcher.common.log;

import com.zaze.utils.FileUtil;
import com.zaze.utils.JsonUtil;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\t\u001a\u00020\u0004¨\u0006\r"}, d2 = {"Lcom/xuehai/launcher/common/log/LogHandler;", "", "()V", "formatLog", "", "tag", "message", "printLog", "", "filePath", "readLog", "", "Lcom/xuehai/launcher/common/log/LogEntity;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LogHandler {
    public static final LogHandler INSTANCE = new LogHandler();

    private LogHandler() {
    }

    public final String formatLog(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        return new LogEntity(tag, message).toJsonLog() + ',';
    }

    public final void printLog(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        List<LogEntity> log = readLog(filePath);
        if (log != null) {
            Iterator<T> it = log.iterator();
            while (it.hasNext()) {
                ((LogEntity) it.next()).print();
            }
        }
    }

    public final List<LogEntity> readLog(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        try {
            StringBuffer byBytes = FileUtil.readByBytes(new FileInputStream(filePath));
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            sb.append((Object) byBytes.deleteCharAt(byBytes.length() - 1));
            sb.append(']');
            return JsonUtil.parseJsonToList(sb.toString(), LogEntity.class);
        } catch (Exception unused) {
            return null;
        }
    }
}
