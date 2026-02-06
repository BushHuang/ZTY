package com.xuehai.launcher.common.error;

import com.xuehai.launcher.common.log.LogHandler;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.zaze.utils.FileUtil;
import com.zaze.utils.StackTraceHelper;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/common/error/DumpExceptionHandler;", "Lcom/xuehai/launcher/common/error/ThrowableHandler;", "filePath", "", "(Ljava/lang/String;)V", "handleException", "", "tag", "tr", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DumpExceptionHandler implements ThrowableHandler {
    private final String filePath;

    public DumpExceptionHandler(String str) {
        Intrinsics.checkNotNullParameter(str, "filePath");
        this.filePath = str;
    }

    private static final void m60handleException$lambda0(DumpExceptionHandler dumpExceptionHandler, String str, Throwable th) {
        Intrinsics.checkNotNullParameter(dumpExceptionHandler, "this$0");
        Intrinsics.checkNotNullParameter(str, "$tag");
        FileUtil.writeToFile(new File(dumpExceptionHandler.filePath), LogHandler.INSTANCE.formatLog(str, StackTraceHelper.INSTANCE.getErrorMsg(th)), 10485760L);
    }

    @Override
    public void handleException(final String tag, final Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        ThreadPlugins.runInLogThread(new Runnable() {
            @Override
            public final void run() {
                DumpExceptionHandler.m60handleException$lambda0(this.f$0, tag, tr);
            }
        });
    }
}
