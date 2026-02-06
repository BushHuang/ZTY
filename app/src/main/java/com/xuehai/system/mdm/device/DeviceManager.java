package com.xuehai.system.mdm.device;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.PowerManager;
import com.xh.common.lib.sdk.samsung.AdminReceiver;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.mdm.proxy.PolicyPlugins;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\rH\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\rH\u0002J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\rJ\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\rJ\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\rH\u0007J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\rH\u0007J\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\rJ\u000e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/xuehai/system/mdm/device/DeviceManager;", "", "()V", "TAG", "", "addDeviceAdmin", "", "context", "Landroid/app/Activity;", "requestCode", "", "brightScreen", "", "Landroid/content/Context;", "getAdminReceiver", "Landroid/content/ComponentName;", "getDevicePolicyManager", "Landroid/app/admin/DevicePolicyManager;", "isAdminActiveInSystem", "lockNow", "removeDeviceAdmin", "resetPassword", "unlock", "wipe", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceManager {
    public static final DeviceManager INSTANCE = new DeviceManager();
    public static final String TAG = "DeviceManager";

    private DeviceManager() {
    }

    @JvmStatic
    public static final ComponentName getAdminReceiver(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ComponentName(context, (Class<?>) AdminReceiver.class);
    }

    private final DevicePolicyManager getDevicePolicyManager(Context context) {
        Object systemService = context.getSystemService("device_policy");
        if (systemService != null) {
            return (DevicePolicyManager) systemService;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.admin.DevicePolicyManager");
    }

    @JvmStatic
    public static final void removeDeviceAdmin(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        new PolicyPlugins(context).getDevicePolicyProxy().removeDeviceAdmin(context, getAdminReceiver(context));
    }

    @JvmStatic
    public static final void resetPassword(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Intrinsics.areEqual(context.getPackageName(), "com.xuehai.launcher")) {
            DevicePasswordManager.INSTANCE.resetPassword(context, getAdminReceiver(context));
        }
    }

    public final boolean addDeviceAdmin(Activity context, int requestCode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Activity activity = context;
        return new PolicyPlugins(activity).getDevicePolicyProxy().addDeviceAdmin(context, getAdminReceiver(activity), requestCode);
    }

    public final void brightScreen(Context context) {
        PowerManager.WakeLock wakeLockNewWakeLock;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (powerManager == null || (wakeLockNewWakeLock = powerManager.newWakeLock(268435466, getClass().getName())) == null) {
                return;
            }
            wakeLockNewWakeLock.acquire(10000L);
            if (wakeLockNewWakeLock.isHeld()) {
                wakeLockNewWakeLock.release();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final boolean isAdminActiveInSystem(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            boolean zIsAdminActive = getDevicePolicyManager(context).isAdminActive(getAdminReceiver(context));
            MdmLog.i("DeviceManager", "设备管理器激活状态 " + zIsAdminActive + "  " + System.currentTimeMillis());
            return zIsAdminActive;
        } catch (Exception e) {
            MdmLog.w("DeviceManager", "获取设备管理器激活状态 发生异常", e);
            return true;
        }
    }

    public final void lockNow(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        getDevicePolicyManager(context).lockNow();
    }

    public final void unlock(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Object systemService = context.getSystemService("keyguard");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.KeyguardManager");
            }
            ((KeyguardManager) systemService).newKeyguardLock("unLock").disableKeyguard();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final boolean wipe(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            MdmLog.i("DeviceManager", "恢复出厂设置");
            getDevicePolicyManager(context).wipeData(0);
            return true;
        } catch (SecurityException e) {
            MdmLog.w("DeviceManager", "wipeData 发生异常", e);
            return false;
        }
    }
}
