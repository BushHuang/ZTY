package com.huawei.hms.framework.network.grs.h;

import com.huawei.hms.framework.common.Logger;

public class e {

    private static final String f283a = "e";

    public static boolean a(Long l) {
        if (l == null) {
            Logger.v(f283a, "Method isTimeExpire input param expireTime is null.");
            return true;
        }
        try {
        } catch (NumberFormatException unused) {
            Logger.v(f283a, "isSpExpire spValue NumberFormatException.");
        }
        if (l.longValue() - System.currentTimeMillis() >= 0) {
            Logger.i(f283a, "isSpExpire false.");
            return false;
        }
        Logger.i(f283a, "isSpExpire true.");
        return true;
    }

    public static boolean a(Long l, long j) {
        if (l == null) {
            Logger.v(f283a, "Method isTimeWillExpire input param expireTime is null.");
            return true;
        }
        try {
            if (l.longValue() - (System.currentTimeMillis() + j) >= 0) {
                Logger.v(f283a, "isSpExpire false.");
                return false;
            }
        } catch (NumberFormatException unused) {
            Logger.v(f283a, "isSpExpire spValue NumberFormatException.");
        }
        return true;
    }
}
