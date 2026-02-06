package com.xuehai.launcher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.xuehai.launcher.common.cache.SessionData;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.util.DeviceModelUtil;
import com.xuehai.launcher.common.util.IntentUtil;
import com.xuehai.launcher.common.util.app.ApplicationUtil;
import com.xuehai.mdm.config.DeviceSupport;
import com.xuehai.system.mdm.proxy.PolicyManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/receiver/BootReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "startSelf", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BootReceiver extends BroadcastReceiver {
    private final void startSelf(Context context) {
        if (SessionData.INSTANCE.isOnTop() || !ClientConfig.INSTANCE.isStudentClient()) {
            return;
        }
        if (DeviceModelUtil.isHuaweiHEMDevice()) {
            if (!PolicyManager.getLicensePolicyProxy().isFreeLicenseActivated()) {
                MyLog.i("[MDM]", "还未激活时不拉起，进入桌面");
                return;
            } else if (!IntentUtil.INSTANCE.isMyLauncherDefault()) {
                MyLog.i("[MDM]", "还未设置为默认桌面时不拉起，进入桌面");
                return;
            }
        }
        MyLog.i("[MDM]", "当前不在前台, 拉起自己");
        if (Build.VERSION.SDK_INT >= 24) {
            context = context.createDeviceProtectedStorageContext();
        }
        Context context2 = context;
        ApplicationUtil applicationUtil = ApplicationUtil.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(context2, "ctx");
        ApplicationUtil.startApp$default(applicationUtil, context2, ClientConfig.INSTANCE.getPackageName(), 0, 4, (Object) null);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || context == null || new DeviceSupport(context).isLenovoSupport()) {
            return;
        }
        String action = intent.getAction();
        if (Intrinsics.areEqual("android.intent.action.BOOT_COMPLETED", action)) {
            MyLog.i("[MDM]", "收到系统启动广播");
            startSelf(context);
            return;
        }
        if (Intrinsics.areEqual("android.intent.action.ACTION_SHUTDOWN", action)) {
            MyLog.i("[MDM]", "收到系统关机广播");
            return;
        }
        if (Intrinsics.areEqual("android.intent.action.LOCKED_BOOT_COMPLETED", action)) {
            MyLog.i("[MDM]", "收到系统启动但还属于锁定状态");
            startSelf(context);
        } else if (Intrinsics.areEqual("android.intent.action.USER_UNLOCKED", action)) {
            MyLog.i("[MDM]", "收到系统启动解锁屏幕状态");
            startSelf(context);
        }
    }
}
