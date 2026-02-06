package com.huawei.hms.framework.network.grs.h;

import android.os.SystemClock;
import com.huawei.hms.framework.common.Logger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class d {

    private static final Map<String, a> f281a = new ConcurrentHashMap(16);

    public static class a {

        private final long f282a;
        private final long b;

        public a(long j, long j2) {
            this.f282a = j;
            this.b = j2;
        }

        public boolean a() {
            return SystemClock.elapsedRealtime() - this.b <= this.f282a;
        }
    }

    public static a a(String str) {
        Logger.v("RequestUtil", "map size of get is before:" + f281a.size());
        a aVar = f281a.get(str);
        Logger.v("RequestUtil", "map size of get is after:" + f281a.size());
        return aVar;
    }

    public static void a(String str, a aVar) {
        Logger.v("RequestUtil", "map size of put is before:" + f281a.size());
        f281a.put(str, aVar);
        Logger.v("RequestUtil", "map size of put is after:" + f281a.size());
    }
}
