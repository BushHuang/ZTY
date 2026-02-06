package com.xuehai.system.mdm.device;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u0004H\u0002J\u0016\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/xuehai/system/mdm/device/DevicePasswordManager;", "", "()V", "TAG", "", "resetPassword", "getDevicePolicyManager", "Landroid/app/admin/DevicePolicyManager;", "context", "Landroid/content/Context;", "refreshResetPassword", "", "admin", "Landroid/content/ComponentName;", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DevicePasswordManager {
    public static final String TAG = "DevicePasswordManager";
    public static final DevicePasswordManager INSTANCE = new DevicePasswordManager();
    private static String resetPassword = "";

    private DevicePasswordManager() {
    }

    private final DevicePolicyManager getDevicePolicyManager(Context context) {
        Object systemService = context.getSystemService("device_policy");
        if (systemService != null) {
            return (DevicePolicyManager) systemService;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.admin.DevicePolicyManager");
    }

    private final String refreshResetPassword() {
        return Build.VERSION.SDK_INT < 23 ? ((Intrinsics.areEqual("SM-P355C", Build.MODEL) || Intrinsics.areEqual("SM-P350", Build.MODEL)) && TextUtils.isEmpty(resetPassword)) ? "123456" : "" : "";
    }

    public final void resetPassword(Context context, ComponentName admin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(admin, "admin");
        try {
            Object systemService = context.getSystemService("keyguard");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.KeyguardManager");
            }
            KeyguardManager keyguardManager = (KeyguardManager) systemService;
            MdmLog.i("DevicePasswordManager", "keyguardManager.isKeyguardSecure: " + keyguardManager.isKeyguardSecure());
            if (Build.VERSION.SDK_INT >= 23) {
                MdmLog.i("DevicePasswordManager", "keyguardManager.isDeviceSecure: " + keyguardManager.isDeviceSecure());
            }
            if (keyguardManager.isKeyguardSecure()) {
                MdmLog.i("DevicePasswordManager", "需要清理锁屏密码！ " + Build.MODEL + '(' + Build.DISPLAY + ')');
                DevicePolicyManager devicePolicyManager = getDevicePolicyManager(context);
                StringBuilder sb = new StringBuilder();
                sb.append("setPasswordQuality: ");
                devicePolicyManager.setPasswordQuality(admin, 0);
                sb.append(Unit.INSTANCE);
                MdmLog.i("DevicePasswordManager", sb.toString());
                resetPassword = refreshResetPassword();
                MdmLog.i("DevicePasswordManager", "resetPassword " + resetPassword + " :" + devicePolicyManager.resetPassword(resetPassword, 0));
            }
        } catch (Exception e) {
            MdmLog.w("DevicePasswordManager", "resetPassword 发生异常", e);
        }
    }
}
