package com.analysys;

import android.content.Context;
import com.analysys.strategy.PolicyManager;
import com.analysys.utils.SharedUtil;

public class aa extends x {

    private Context f10a = null;

    @Override
    public boolean a(Context context) {
        if (context == null) {
            return false;
        }
        this.f10a = context;
        int i = SharedUtil.getInt(context, "userDebug", -1);
        if (i == 1 || i == 2 || PolicyManager.getEventCount(this.f10a) < g.a(context).b()) {
            return true;
        }
        long j = SharedUtil.getLong(this.f10a, "uploadTime", 0L);
        long intervalTime = PolicyManager.getIntervalTime(this.f10a);
        long jAbs = Math.abs(System.currentTimeMillis() - j);
        if (intervalTime < jAbs) {
            return true;
        }
        p.a(context).a(intervalTime - jAbs);
        return false;
    }
}
