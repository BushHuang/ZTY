package com.xuehai.launcher.common.error;

import android.os.DeadObjectException;
import android.view.View;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.widget.dialog.CustomDialog;
import com.xuehai.launcher.common.widget.dialog.DialogProvider;
import com.zaze.utils.StackTraceHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/common/error/DeadObjectExceptionHandler;", "Lcom/xuehai/launcher/common/error/ThrowableHandler;", "()V", "handleException", "", "tag", "", "tr", "", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeadObjectExceptionHandler implements ThrowableHandler {
    private static CustomDialog errorDialog;

    private static final void m58handleException$lambda1() {
        CustomDialog customDialog = errorDialog;
        if (customDialog != null) {
            customDialog.dismiss();
        }
        CustomDialog customDialogBuildCustomDialog = DialogProvider.Builder.message$default(new DialogProvider.Builder(), "智通云发生错误, 稍后将自动退出", 0, 2, null).applicationOverlay(true).positive("立即退出", new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            public final void invoke2(View view) {
                Intrinsics.checkNotNullParameter(view, "it");
                XHAppUtil.killAllProcess();
            }
        }).buildCustomDialog(BaseApplication.INSTANCE.getInstance());
        errorDialog = customDialogBuildCustomDialog;
        if (customDialogBuildCustomDialog != null) {
            customDialogBuildCustomDialog.show();
        }
        ThreadPlugins.runInWorkThread(new Runnable() {
            @Override
            public final void run() {
                XHAppUtil.killAllProcess();
            }
        }, 5000L);
    }

    @Override
    public void handleException(String tag, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (!(tr instanceof DeadObjectException)) {
            if (!((tr != null ? tr.getCause() : null) instanceof DeadObjectException)) {
                return;
            }
        }
        MyLog.saveAppErrorLog("智通云发生异常: " + StackTraceHelper.INSTANCE.getErrorMsg(tr));
        ThreadPlugins.runInUIThread$default(new Runnable() {
            @Override
            public final void run() {
                DeadObjectExceptionHandler.m58handleException$lambda1();
            }
        }, 0L, 2, null);
    }
}
