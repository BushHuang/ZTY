package com.xh.xhcore.common.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import com.xh.view.XHAlertDialog;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.XHAppUtilKt;
import com.xuehai.provider.core.db.CPVDCustomConfig;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0010H\u0002J\u001e\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/xh/xhcore/common/helper/PlatformRunningCheckHelper;", "", "()V", "ACTION_KEEP_RUNNING_TIME", "", "LAUNCHER_MDM_STU", "LAUNCHER_MDM_TCH", "LAUNCHER_STUDENT", "LAUNCHER_TEACHER", "TAG", "checkPlatformRunning", "", "context", "Landroid/content/Context;", "checkPlatformRunningNow", "getLauncherKeepRunningTime", "", "getPlatformRunningTime", "isLauncherKeepRunning", "", "sendUpdateBroadcast", "setPlatformRunningTime", "time", "showNotRunningAlertDialog", "sureAction", "Lkotlin/Function0;", "startPlatform", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlatformRunningCheckHelper {
    private static final String ACTION_KEEP_RUNNING_TIME = "com.xuehai.launcher.action.KEEP_RUNNING_TIME";
    public static final PlatformRunningCheckHelper INSTANCE = new PlatformRunningCheckHelper();
    private static final String LAUNCHER_MDM_STU = "com.xuehai.launcher";
    private static final String LAUNCHER_MDM_TCH = "com.xuehai.response_launcher_teacher";
    private static final String LAUNCHER_STUDENT = "com.xh.zhitongyunstu";
    private static final String LAUNCHER_TEACHER = "com.xh.zhitongyuntch";
    private static final String TAG = "PlatformRunningCheckHelper";

    private PlatformRunningCheckHelper() {
    }

    private final long getLauncherKeepRunningTime() {
        Object objM228constructorimpl;
        String config = CPVDCustomConfig.getConfig("launcherKeepRunningTime");
        String str = config;
        if (str == null || str.length() == 0) {
            return 0L;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM228constructorimpl = Result.m228constructorimpl(Long.valueOf(Long.parseLong(config)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM228constructorimpl = Result.m228constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m231exceptionOrNullimpl(objM228constructorimpl) != null) {
            objM228constructorimpl = 0L;
        }
        return ((Number) objM228constructorimpl).longValue();
    }

    private final long getPlatformRunningTime() {
        Object objM228constructorimpl;
        String config = CPVDCustomConfig.getConfig("checkPlatformRunningTimeKey", "check_platform_running_time");
        String str = config;
        if (str == null || str.length() == 0) {
            return 0L;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM228constructorimpl = Result.m228constructorimpl(Long.valueOf(Long.parseLong(config)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM228constructorimpl = Result.m228constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m231exceptionOrNullimpl(objM228constructorimpl) != null) {
            objM228constructorimpl = 0L;
        }
        return ((Number) objM228constructorimpl).longValue();
    }

    private final boolean isLauncherKeepRunning() {
        long launcherKeepRunningTime = getLauncherKeepRunningTime();
        if (launcherKeepRunningTime > 0) {
            return Math.abs(System.currentTimeMillis() - launcherKeepRunningTime) <= 600000;
        }
        LogUtils.Companion.i$default(LogUtils.INSTANCE, "PlatformRunningCheckHelper", " 平台保持运行写入的时间戳为 0", null, 4, null);
        return true;
    }

    private final void sendUpdateBroadcast(Context context) {
        context.sendBroadcast(new Intent("com.xuehai.launcher.action.KEEP_RUNNING_TIME"));
    }

    private final void setPlatformRunningTime(long time) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("check_platform_running_time", Long.valueOf(time));
        CPVDCustomConfig.INSTANCE.saveConfig("checkPlatformRunningTimeKey", JsonUtil.toJsonString(linkedHashMap));
    }

    private final void showNotRunningAlertDialog(Context context, final Function0<Unit> sureAction) {
        new XHAlertDialog(context).showNoNegativeDialog("平台未运行提醒", "检测到平台未运行，点击即将唤醒平台", "唤醒平台", new XHAlertDialog.DialogActionListener() {
            @Override
            public void onNegativeAction(Dialog p0) {
            }

            @Override
            public void onPositiveAction(Dialog dialog) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                sureAction.invoke();
            }

            @Override
            public void onReportAction(Dialog p0) {
            }
        }, false);
    }

    private final void startPlatform(Context context) {
        try {
            Intent intent = new Intent("com.xh.zhitongyun.empty");
            if (XHAppUtilKt.isInstalled(context, "com.xh.zhitongyunstu")) {
                intent.setPackage("com.xh.zhitongyunstu");
            } else if (XHAppUtilKt.isInstalled(context, "com.xh.zhitongyuntch")) {
                intent.setPackage("com.xh.zhitongyuntch");
            }
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            context.startActivity(intent);
        } catch (Exception unused) {
            LogUtils.Companion.e$default(LogUtils.INSTANCE, "PlatformRunningCheckHelper", "平台未安装或启动失败", null, 4, null);
        }
    }

    public final void checkPlatformRunning(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!XHAppConfigProxy.getInstance().isCheckPlatformAutoKeepRunning()) {
            LogUtils.Companion.i$default(LogUtils.INSTANCE, "PlatformRunningCheckHelper", Intrinsics.stringPlus(context.getPackageName(), " 关闭了自动检测平台是否运行中"), null, 4, null);
            return;
        }
        if (Math.abs(System.currentTimeMillis() - getPlatformRunningTime()) > 300000) {
            checkPlatformRunningNow(context);
            long jCurrentTimeMillis = System.currentTimeMillis();
            String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Long.valueOf(300000 + jCurrentTimeMillis));
            LogUtils.Companion.i$default(LogUtils.INSTANCE, "PlatformRunningCheckHelper", "下次检测平台是否运行时间点为，time = " + ((Object) str) + " 之后", null, 4, null);
            setPlatformRunningTime(jCurrentTimeMillis);
        }
    }

    public final void checkPlatformRunningNow(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        sendUpdateBroadcast(context);
        if (isLauncherKeepRunning()) {
            LogUtils.Companion.i$default(LogUtils.INSTANCE, "PlatformRunningCheckHelper", "平台运行中...", null, 4, null);
        } else {
            LogUtils.Companion.i$default(LogUtils.INSTANCE, "PlatformRunningCheckHelper", "显示平台未运行弹窗，即将唤醒平台提示", null, 4, null);
            showNotRunningAlertDialog(context, new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    LogUtils.Companion.i$default(LogUtils.INSTANCE, "PlatformRunningCheckHelper", "点击弹窗按钮，即将唤醒平台", null, 4, null);
                    PlatformRunningCheckHelper.INSTANCE.startPlatform(context);
                }
            });
        }
    }
}
