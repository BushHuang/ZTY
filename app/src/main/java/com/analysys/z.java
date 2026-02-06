package com.analysys;

import android.content.Context;
import com.analysys.strategy.PolicyManager;
import com.analysys.utils.SharedUtil;

public class z extends x {
    private boolean b(Context context) {
        if (PolicyManager.getEventCount(context) < g.a(context).b()) {
            return true;
        }
        long j = SharedUtil.getLong(context, "uploadTime", 0L);
        long intervalTime = PolicyManager.getIntervalTime(context);
        long jAbs = Math.abs(System.currentTimeMillis() - j);
        if (intervalTime < jAbs) {
            return true;
        }
        p.a(context).a(intervalTime - jAbs);
        return false;
    }

    private boolean c(Context context) {
        long intervalTime = PolicyManager.getIntervalTime(context);
        long jAbs = Math.abs(System.currentTimeMillis() - SharedUtil.getLong(context, "uploadTime", 0L));
        if (intervalTime < jAbs) {
            return true;
        }
        p.a(context).a(intervalTime - jAbs);
        return false;
    }

    @Override
    public boolean a(Context context) {
        if (context == null) {
            return false;
        }
        int i = SharedUtil.getInt(context, "serviceDebug", -1);
        if (i == 1 || i == 2) {
            return true;
        }
        long j = SharedUtil.getLong(context, "policyNo", -1L);
        if (j == 0) {
            return b(context);
        }
        if (j == 1) {
            return true;
        }
        if (j == 2) {
            return c(context);
        }
        return false;
    }
}
