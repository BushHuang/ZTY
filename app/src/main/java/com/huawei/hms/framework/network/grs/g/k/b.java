package com.huawei.hms.framework.network.grs.g.k;

import android.os.SystemClock;
import java.util.concurrent.Future;

public class b {

    private final Future<com.huawei.hms.framework.network.grs.g.d> f276a;
    private final long b = SystemClock.elapsedRealtime();

    public b(Future<com.huawei.hms.framework.network.grs.g.d> future) {
        this.f276a = future;
    }

    public Future<com.huawei.hms.framework.network.grs.g.d> a() {
        return this.f276a;
    }

    public boolean b() {
        return SystemClock.elapsedRealtime() - this.b <= 300000;
    }
}
