package com.xuehai.provider.constants;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/xuehai/provider/constants/BroadcastAction;", "", "()V", "APPLICATION_EXIT", "", "APP_INSTALL", "APP_UNINSTALL", "BROADCAST_PREFIX", "CACHE_UPDATE", "KEY_CACHE_KEY", "KEY_FROM", "SESSION_RESET_SUCCESS", "clearAppData", "packageName", "messageTrans", "library_release"}, k = 1, mv = {1, 1, 15})
public final class BroadcastAction {
    public static final String APPLICATION_EXIT = "com.xuehai.cpvd.application_exit";
    public static final String APP_INSTALL = "com.xuehai.cpvd.app.install";
    public static final String APP_UNINSTALL = "com.xuehai.cpvd.app.uninstall";
    public static final String BROADCAST_PREFIX = "com.xuehai.cpvd";
    public static final String CACHE_UPDATE = "com.xuehai.cpvd.cache_update";
    public static final BroadcastAction INSTANCE = new BroadcastAction();
    public static final String KEY_CACHE_KEY = "key_cache_key";
    public static final String KEY_FROM = "key_from";
    public static final String SESSION_RESET_SUCCESS = "com.xuehai.cpvd.session.reset.success";

    private BroadcastAction() {
    }

    @JvmStatic
    public static final String clearAppData(String packageName) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        return packageName + ".action.clear";
    }

    @JvmStatic
    public static final String messageTrans(String packageName) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        return "com.xh.push.message.trans." + packageName;
    }
}
