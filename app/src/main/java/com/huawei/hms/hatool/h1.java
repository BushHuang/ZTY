package com.huawei.hms.hatool;

import android.content.Context;

public final class h1 {
    public static h1 b;
    public static final Object c = new Object();

    public Context f298a;

    static {
        new String[]{"ABTesting", "_default_config_tag", "_openness_config_tag", "_hms_config_tag"};
    }

    public static h1 a() {
        if (b == null) {
            b();
        }
        return b;
    }

    public static synchronized void b() {
        if (b == null) {
            b = new h1();
        }
    }

    public void a(Context context) {
        synchronized (c) {
            if (this.f298a != null) {
                y.f("hmsSdk", "DataManager already initialized.");
                return;
            }
            this.f298a = context;
            i.c().b().a(this.f298a);
            i.c().b().j(context.getPackageName());
            z0.a().a(context);
        }
    }

    public void a(String str) {
        y.c("hmsSdk", "HiAnalyticsDataManager.setAppid(String appid) is execute.");
        Context context = this.f298a;
        if (context == null) {
            y.e("hmsSdk", "sdk is not init");
        } else {
            i.c().b().i(s0.a("appID", str, "[a-zA-Z0-9_][a-zA-Z0-9. _-]{0,255}", context.getPackageName()));
        }
    }
}
