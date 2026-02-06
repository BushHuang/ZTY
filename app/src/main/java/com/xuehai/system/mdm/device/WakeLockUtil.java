package com.xuehai.system.mdm.device;

import android.content.Context;
import android.os.PowerManager;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0018\u00010\u0006R\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007J\u0016\u0010\n\u001a\b\u0018\u00010\u0006R\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0016\u0010\r\u001a\b\u0018\u00010\u0006R\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u001e\u0010\u000e\u001a\b\u0018\u00010\u0006R\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0016\u0010\u0011\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0018\u00010\u0006R\u00020\u0007H\u0007¨\u0006\u0012"}, d2 = {"Lcom/xuehai/system/mdm/device/WakeLockUtil;", "", "()V", "acquire", "", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "timeout", "", "newPartialWakeLock", "context", "Landroid/content/Context;", "newScreenWakeLock", "newWakeLock", "levelAndFlags", "", "release", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WakeLockUtil {
    public static final WakeLockUtil INSTANCE = new WakeLockUtil();

    private WakeLockUtil() {
    }

    @JvmStatic
    public static final void acquire(PowerManager.WakeLock wakeLock, long timeout) {
        if (wakeLock != null) {
            try {
                wakeLock.acquire(timeout);
            } catch (Exception unused) {
            }
        }
    }

    public static void acquire$default(PowerManager.WakeLock wakeLock, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 3000;
        }
        acquire(wakeLock, j);
    }

    @JvmStatic
    public static final PowerManager.WakeLock newPartialWakeLock(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return newWakeLock(context, 1);
    }

    @JvmStatic
    public static final PowerManager.WakeLock newScreenWakeLock(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return newWakeLock(context, 268435462);
    }

    @JvmStatic
    public static final PowerManager.WakeLock newWakeLock(Context context, int levelAndFlags) {
        Intrinsics.checkNotNullParameter(context, "context");
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager != null) {
            return powerManager.newWakeLock(levelAndFlags, INSTANCE.getClass().getName());
        }
        return null;
    }

    @JvmStatic
    public static final void release(PowerManager.WakeLock wakeLock) {
        boolean z = true;
        if (wakeLock != null) {
            try {
                if (!wakeLock.isHeld()) {
                    z = false;
                }
            } catch (Exception unused) {
                return;
            }
        }
        if (z) {
            wakeLock.release();
        }
    }
}
