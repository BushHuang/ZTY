package com.xuehai.launcher.common.config;

import com.xuehai.launcher.common.base.BaseApplication;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0007J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006J\b\u0010\u0010\u001a\u00020\u0011H\u0007J\b\u0010\u0012\u001a\u00020\u000fH\u0007J\u0018\u0010\u0013\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/xuehai/launcher/common/config/Permission;", "", "()V", "VERSION", "", "defaultPermission", "", "getDefaultPermission", "()J", "setDefaultPermission", "(J)V", "addPermission", "ownPermission", "permission", "checkPermission", "", "initPermission", "", "isDebugMode", "removePermission", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Permission {
    public static final Permission INSTANCE = new Permission();
    public static final int VERSION = 20;
    private static long defaultPermission;

    private Permission() {
    }

    @JvmStatic
    public static final long addPermission(long ownPermission, long permission) {
        return !INSTANCE.checkPermission(ownPermission, permission) ? ownPermission | permission : ownPermission;
    }

    @JvmStatic
    public static final void initPermission() {
        defaultPermission = 0L;
    }

    @JvmStatic
    public static final boolean isDebugMode() {
        return BaseApplication.INSTANCE.getInstance().isDebug();
    }

    @JvmStatic
    public static final long removePermission(long ownPermission, long permission) {
        return INSTANCE.checkPermission(ownPermission, permission) ? ownPermission & (permission ^ (-1)) : ownPermission;
    }

    public final boolean checkPermission(long ownPermission, long permission) {
        return (ownPermission & permission) > 0;
    }

    public final long getDefaultPermission() {
        return defaultPermission;
    }

    public final void setDefaultPermission(long j) {
        defaultPermission = j;
    }
}
