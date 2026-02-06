package com.xuehai.system.mdm;

import android.os.Build;
import android.text.Html;
import android.view.View;
import com.xuehai.launcher.App;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.TaskPlugins;
import com.xuehai.launcher.common.util.LauncherSPUtil;
import com.xuehai.launcher.common.widget.CustomToast;
import com.xuehai.launcher.common.widget.dialog.DialogProvider;
import com.xuehai.system.mdm.device.DeviceManager;
import com.xuehai.system.mdm.proxy.PolicyManager;
import com.zaze.utils.StackTraceHelper;
import com.zaze.utils.date.DateUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J \u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0013H\u0016J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J\u0006\u0010\u001b\u001a\u00020\u0011J\u0018\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J \u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/xuehai/system/mdm/MdmErrorCheck;", "Lcom/xuehai/launcher/common/log/MyLog$LogListener;", "()V", "TAG", "", "adminErrorReboot", "adminResultKey", "errorCount", "", "maxErrorCount", "rebootCount", "rebootRunnable", "Ljava/lang/Runnable;", "rebootType", "snErrorReboot", "startAdminTimeKey", "checkError", "", "e", "", "checkSNError", "checkStartAdminError", "d", "tag", "msg", "tr", "i", "observerMdmError", "v", "w", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MdmErrorCheck implements MyLog.LogListener {
    private static final String TAG = "MdmErrorCheck";
    private static final String adminErrorReboot = "admin_error";
    public static final String adminResultKey = "admin_result_key";
    private static int errorCount = 0;
    private static final int maxErrorCount = 3;
    public static final String rebootCount = "reboot_count";
    private static final String snErrorReboot = "sn_error";
    public static final String startAdminTimeKey = "start_admin_time_key";
    public static final MdmErrorCheck INSTANCE = new MdmErrorCheck();
    private static String rebootType = "sn_error";
    private static final Runnable rebootRunnable = new Runnable() {
        @Override
        public final void run() {
            MdmErrorCheck.m223rebootRunnable$lambda0();
        }
    };

    private MdmErrorCheck() {
    }

    private final void checkError(Throwable e) {
        if ((e instanceof SecurityException) && Intrinsics.areEqual(Build.MODEL, "SM-P200") && !ClientConfig.INSTANCE.isTeacherClient()) {
            if (DateUtil.getDayStart(System.currentTimeMillis()) > DateUtil.getDayStart(LauncherSPUtil.getUpdateTimeByKey("MdmErrorCheck"))) {
                MyLog.saveAppErrorLog("检测到异常" + StackTraceHelper.INSTANCE.getErrorMsg(e));
                LauncherSPUtil.setUpdateTimeByKey("MdmErrorCheck");
            }
            MyLog.i("SDK[MDM]", "检测到异常,当前系统版本: " + Build.DISPLAY);
            String str = Build.DISPLAY;
            Intrinsics.checkNotNullExpressionValue(str, "DISPLAY");
            if (!StringsKt.endsWith$default(str, "B2BF", false, 2, (Object) null)) {
                String str2 = Build.DISPLAY;
                Intrinsics.checkNotNullExpressionValue(str2, "DISPLAY");
                if (!StringsKt.endsWith$default(str2, "B2BF.DM", false, 2, (Object) null)) {
                    MyLog.i("SDK[MDM]", "检测到异常 : 不是定制包, 不重启: " + Build.DISPLAY);
                    return;
                }
            }
            if (Build.DISPLAY.compareTo("PPR1.180610.011.P200ZCU1ASG7_B2BF") > 0) {
                checkSNError(e);
                checkStartAdminError(e);
            } else {
                MyLog.i("SDK[MDM]", "检测到异常: 系统版本过低, 不重启: " + Build.DISPLAY);
            }
        }
    }

    private static final void m221checkSNError$lambda1() {
        PolicyManager.getDevicePolicyProxy().getSerialNumber();
    }

    private static final void m223rebootRunnable$lambda0() {
        MyLog.i("MdmErrorCheck", "start reboot");
        MyLog.saveAppErrorLog("检测到异常\n 重启处理" + rebootType);
        if (Intrinsics.areEqual(rebootType, "admin_error")) {
            LauncherSPUtil.put("reboot_count", Integer.valueOf(((Number) LauncherSPUtil.get("reboot_count", 0)).intValue() + 1));
        }
        PolicyManager.getDevicePolicyProxy().reboot();
    }

    public final void checkSNError(Throwable e) {
        Intrinsics.checkNotNullParameter(e, "e");
        String message = e.getMessage();
        if ((message == null || StringsKt.contains$default((CharSequence) message, (CharSequence) "No active admin owned by", false, 2, (Object) null)) ? false : true) {
            return;
        }
        if (!DeviceManager.INSTANCE.isAdminActiveInSystem(App.INSTANCE.getInstance())) {
            MyLog.i("SDK[MDM]", "Admin is not Active: " + Build.DISPLAY);
            return;
        }
        errorCount++;
        MyLog.w("MdmErrorCheck", e.getMessage() + " : " + errorCount);
        if (errorCount <= 3) {
            TaskPlugins.postTask(new Runnable() {
                @Override
                public final void run() {
                    MdmErrorCheck.m221checkSNError$lambda1();
                }
            }, 1000L);
            return;
        }
        errorCount = 0;
        rebootType = "sn_error";
        TaskPlugins.postTask(rebootRunnable, 5000L);
        DialogProvider.Builder.applicationOverlay$default(new DialogProvider.Builder().message(Html.fromHtml(BaseApplication.INSTANCE.getInstance().getString(2131689555)), 16).positive("立即重启", new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            public final void invoke2(View view) {
                Intrinsics.checkNotNullParameter(view, "it");
                TaskPlugins.postTask$default(MdmErrorCheck.rebootRunnable, 0L, 2, (Object) null);
            }
        }), false, 1, null).buildCustomDialog(BaseApplication.INSTANCE.getInstance()).show();
    }

    public final void checkStartAdminError(Throwable e) {
        Intrinsics.checkNotNullParameter(e, "e");
        String message = e.getMessage();
        if ((message == null || StringsKt.contains$default((CharSequence) message, (CharSequence) "用户取消激活设备", false, 2, (Object) null)) ? false : true) {
            return;
        }
        Integer num = (Integer) LauncherSPUtil.get("reboot_count", 0);
        long updateTimeByKey = LauncherSPUtil.getUpdateTimeByKey("admin_result_key") - LauncherSPUtil.getUpdateTimeByKey("start_admin_time_key");
        MyLog.i("SDK[MDM]", "激活开始到失败时间间隔" + updateTimeByKey);
        MyLog.i("SDK[MDM]", "平板重启次数" + num);
        if (1 <= updateTimeByKey && updateTimeByKey < 1500) {
            Intrinsics.checkNotNullExpressionValue(num, "rebootCount");
            if (num.intValue() <= 3) {
                rebootType = "admin_error";
                TaskPlugins.postTask(rebootRunnable, 5000L);
                CustomToast.showToast("重启后新功能生效，即将重启平板");
            }
        }
    }

    @Override
    public void d(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
    }

    @Override
    public void e(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
    }

    @Override
    public void e(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tr, "tr");
        checkError(tr);
    }

    @Override
    public void i(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
    }

    public final void observerMdmError() {
        MyLog.INSTANCE.getListeners().add(this);
    }

    @Override
    public void v(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
    }

    @Override
    public void w(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
    }

    @Override
    public void w(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tr, "tr");
    }
}
