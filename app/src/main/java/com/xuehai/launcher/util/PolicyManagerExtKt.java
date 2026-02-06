package com.xuehai.launcher.util;

import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.system.mdm.proxy.PolicyManager;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0004"}, d2 = {"tryInitialize", "", "Lcom/xuehai/system/mdm/proxy/PolicyManager;", "tryUpgrade", "app_studentToBRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class PolicyManagerExtKt {
    public static final void tryInitialize(PolicyManager policyManager) throws Throwable {
        Intrinsics.checkNotNullParameter(policyManager, "<this>");
        PolicyManager.getRestrictionPolicyProxy().wipeRecentTasks();
        Pair<Integer, Boolean> pairIsZtyUpgraded = ZtyClientUtil.INSTANCE.isZtyUpgraded();
        int iIntValue = pairIsZtyUpgraded.component1().intValue();
        boolean zBooleanValue = pairIsZtyUpgraded.component2().booleanValue();
        if (!zBooleanValue && ZtyClientUtil.INSTANCE.isZtyClientInstalled()) {
            policyManager.lock(true);
            return;
        }
        MyLog.i("Debug[MDM]", "智通平台未安装或者MDM升级后(" + zBooleanValue + ")，MDM执行基础管控(系统管控 + 业务管控)");
        policyManager.lock(false);
        ZtyClientUtil.INSTANCE.saveZtyUpgradeVersion(iIntValue);
    }

    public static final void tryUpgrade(PolicyManager policyManager) throws Throwable {
        Intrinsics.checkNotNullParameter(policyManager, "<this>");
        Pair<Integer, Boolean> pairIsZtyUpgraded = ZtyClientUtil.INSTANCE.isZtyUpgraded();
        int iIntValue = pairIsZtyUpgraded.component1().intValue();
        if (pairIsZtyUpgraded.component2().booleanValue()) {
            MyLog.i("Debug[MDM]", "智通云已更新，MDM执行基础管控(系统管控 + 业务管控)");
            policyManager.lock(false);
            ZtyClientUtil.INSTANCE.saveZtyUpgradeVersion(iIntValue);
        }
    }
}
