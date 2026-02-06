package com.huawei.hms.framework.network.grs.local.model;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class a {

    private String f285a;
    private final Map<String, c> b = new ConcurrentHashMap(16);

    public c a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return this.b.get(str);
        }
        Logger.w("ApplicationBean", "In getServing(String serviceName), the serviceName is Empty or null");
        return null;
    }

    public void a() {
        Map<String, c> map = this.b;
        if (map != null) {
            map.clear();
        }
    }

    public void a(long j) {
    }

    public void a(String str, c cVar) {
        if (TextUtils.isEmpty(str) || cVar == null) {
            return;
        }
        this.b.put(str, cVar);
    }

    public String b() {
        return this.f285a;
    }

    public void b(String str) {
        this.f285a = str;
    }
}
