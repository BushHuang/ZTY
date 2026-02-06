package com.xuehai.launcher.common.cache;

import com.xuehai.launcher.common.config.KeepConfig;
import com.xuehai.launcher.common.network.NetworkManger;
import com.xuehai.system.mdm.proxy.PolicyManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\u0004J\u0006\u0010\u0010\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0005\"\u0004\b\t\u0010\u0007R\u001a\u0010\n\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0005\"\u0004\b\u000b\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/xuehai/launcher/common/cache/SessionData;", "", "()V", "isOnTop", "", "()Z", "setOnTop", "(Z)V", "isOsVersionRemoteValid", "setOsVersionRemoteValid", "isZtyUpdating", "setZtyUpdating", "isCooperationZS", "isNationServiceVersion", "isOnline", "isOsVersionValid", "isQw", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SessionData {
    public static final SessionData INSTANCE = new SessionData();
    private static boolean isOnTop;
    private static volatile boolean isOsVersionRemoteValid;
    private static volatile boolean isZtyUpdating;

    private SessionData() {
    }

    public final boolean isCooperationZS() {
        return Intrinsics.areEqual(KeepConfig.getPurpose(), "6");
    }

    public final boolean isNationServiceVersion() {
        return Intrinsics.areEqual(KeepConfig.getServiceVersionCode(), "national_version");
    }

    public final boolean isOnTop() {
        return isOnTop;
    }

    public final boolean isOnline() {
        return NetworkManger.INSTANCE.isEnable();
    }

    public final boolean isOsVersionRemoteValid() {
        return isOsVersionRemoteValid;
    }

    public final boolean isOsVersionValid() {
        return isOsVersionRemoteValid || PolicyManager.INSTANCE.getPlugins().getDeviceSupportCompat().getDeviceConfiguration().isOsVersionValid();
    }

    public final boolean isQw() {
        return Intrinsics.areEqual(KeepConfig.getPurpose(), "9");
    }

    public final boolean isZtyUpdating() {
        return isZtyUpdating;
    }

    public final void setOnTop(boolean z) {
        isOnTop = z;
    }

    public final void setOsVersionRemoteValid(boolean z) {
        isOsVersionRemoteValid = z;
    }

    public final void setZtyUpdating(boolean z) {
        isZtyUpdating = z;
    }
}
