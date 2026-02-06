package com.huawei.hms.framework.network.grs;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class d {

    private static final Map<String, c> f258a = new ConcurrentHashMap(16);
    private static final Object b = new Object();

    public static c a(GrsBaseInfo grsBaseInfo, Context context) {
        synchronized (b) {
            int iUniqueCode = grsBaseInfo.uniqueCode();
            c cVar = f258a.get(context.getPackageName() + iUniqueCode);
            if (cVar == null) {
                c cVar2 = new c(context, grsBaseInfo);
                f258a.put(context.getPackageName() + iUniqueCode, cVar2);
                return cVar2;
            }
            if (cVar.a((Object) new c(grsBaseInfo))) {
                return cVar;
            }
            c cVar3 = new c(context, grsBaseInfo);
            f258a.put(context.getPackageName() + iUniqueCode, cVar3);
            return cVar3;
        }
    }
}
