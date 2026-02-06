package com.xuehai.system.mdm;

import com.xuehai.launcher.common.constants.FilePath;
import com.xuehai.launcher.common.error.DumpExceptionHandler;
import com.xuehai.launcher.common.error.ExceptionHandler;
import com.xuehai.launcher.common.error.LogException;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.system.common.UnSupportException;
import com.xuehai.system.common.log.MdmLogClient;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J \u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J \u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/xuehai/system/mdm/MyMdmLogClient;", "Lcom/xuehai/system/common/log/MdmLogClient;", "()V", "exceptionHandler", "Lcom/xuehai/launcher/common/error/ExceptionHandler;", "d", "", "tag", "", "msg", "e", "tr", "", "i", "v", "w", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyMdmLogClient extends MdmLogClient {
    private final ExceptionHandler exceptionHandler = new ExceptionHandler(new DumpExceptionHandler(FilePath.getErrorLogDir() + "/mdm_sdk.error"));

    @Override
    public void d(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        MyLog.d(tag, msg);
    }

    @Override
    public void e(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        MyLog.e(tag, msg);
    }

    @Override
    public void e(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tr, "tr");
        MyLog.INSTANCE.e(tag, msg, tr);
        if (tr instanceof UnSupportException) {
            return;
        }
        this.exceptionHandler.handleException("mdm_sdk_error", new LogException(msg, tr));
    }

    @Override
    public void i(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        MyLog.i(tag, msg);
    }

    @Override
    public void v(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        MyLog.INSTANCE.v(tag, msg);
    }

    @Override
    public void w(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        MyLog.w(tag, msg);
    }

    @Override
    public void w(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tr, "tr");
        MyLog.w(tag, msg, tr);
    }
}
