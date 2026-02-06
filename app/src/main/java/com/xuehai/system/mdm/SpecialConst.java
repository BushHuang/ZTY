package com.xuehai.system.mdm;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/xuehai/system/mdm/SpecialConst;", "", "()V", "RESPONSE", "", "TASK_REWARD", "WAKEUP_ACTIVITY", "ZTY_NEGATIVE_SCREEN_TASK_REWARD", "ZTY_STU", "ZTY_WHITELIST_ACTIVITY", "", "getZTY_WHITELIST_ACTIVITY", "()Ljava/util/Set;", "aidl_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SpecialConst {
    public static final String RESPONSE = "com.xh.arespunc";
    public static final String TASK_REWARD = "com.xh.module.taskrewardstu";
    public static final String WAKEUP_ACTIVITY = "com.xh.arespunc.main.view.WakeUpResponseActivity";
    public static final String ZTY_NEGATIVE_SCREEN_TASK_REWARD = "com.xh.zhitongyunstu.negative.screen.taskrewardstu";
    public static final String ZTY_STU = "com.xh.zhitongyunstu";
    public static final SpecialConst INSTANCE = new SpecialConst();
    private static final Set<String> ZTY_WHITELIST_ACTIVITY = SetsKt.setOf((Object[]) new String[]{"com.xuehai.launcher.EmptyActivity", "com.xuehai.launcher.lock.LockScreenActivity", "com.xuehai.launcher.lock.NightForbidLockScreenActivity"});

    private SpecialConst() {
    }

    public final Set<String> getZTY_WHITELIST_ACTIVITY() {
        return ZTY_WHITELIST_ACTIVITY;
    }
}
